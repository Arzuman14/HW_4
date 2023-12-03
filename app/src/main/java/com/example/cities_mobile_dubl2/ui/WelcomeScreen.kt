package com.example.cities_mobile_dubl2.ui

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.cities_mobile_dubl2.MainActivity
import com.example.cities_mobile_dubl2.R
import com.example.cities_mobile_dubl2.constants.SECOND_SCREEN_ROUTE
import com.example.cities_mobile_dubl2.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import android.location.LocationListener
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.cities_mobile_dubl2.isCelsius
import kotlinx.coroutines.delay
import java.io.IOException
import java.util.Locale
import kotlin.reflect.typeOf


@Composable
fun WelcomeScreen(navController: NavController, activity: MainActivity, viewModel: WeatherViewModel) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text  ="City Trip",
            fontSize = 100.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = Color.Black
        )

        if (activity.checkLocationPermission()) {
            val weatherData = viewModel.weatherData.value
            val currentCord1 = viewModel.location.value?.first
            val currentCord2 = viewModel.location.value?.second

            if (weatherData != null) {

                Text(
                    text = if (isCelsius) {"Current Temperature in your Location   : ${weatherData[0].current.temp_c}°C"} else { "Current Temperature in your Location : ${celsiusToFahrenheit(weatherData[0].current.temp_c)}" },
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )


            } else {
                Text(
                    text = "No weather available",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

        } else {
            Text(
                text = "No location available",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate(SECOND_SCREEN_ROUTE)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = stringResource(id = R.string.explore_cities))
        }
    }
}

fun celsiusToFahrenheit(celsius: Float): Double {
    val fahrenheit = celsius * 9 / 5 + 32
    return "%.2f".format(fahrenheit).toDouble()
}


private fun fetchWeatherForCurrentLocation(activity: MainActivity, viewModel: WeatherViewModel) {
    val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    val locationListener = LocationListener { location ->
        val latitude = location.latitude
        val longitude = location.longitude

        viewModel.setLocation(latitude, longitude)
        activity.lifecycleScope.launch {
            delay(5000)
            viewModel.fetchWeather("current_location")
        }
    }

    try {
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            0, 0f,
            locationListener
        )
    } catch (e: SecurityException) {
        e.printStackTrace()
    }
}