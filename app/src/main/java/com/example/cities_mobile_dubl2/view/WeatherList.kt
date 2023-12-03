package com.example.cities_mobile_dubl2.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cities_mobile_dubl2.data.cities
import com.example.cities_mobile_dubl2.isCelsius
import com.example.cities_mobile_dubl2.model.City
import com.example.cities_mobile_dubl2.model.WeatherData
import com.example.cities_mobile_dubl2.ui.celsiusToFahrenheit

@Composable
fun WeatherList(weatherList: List<WeatherData>) {
    LazyColumn {
        itemsIndexed(weatherList) { index, weather ->
            var humidity = "Humidity: ${weather.current.humidity}"
            var currentWeather = "Temperature: ${weather.current.temp_c}°C"
            if (!isCelsius){ currentWeather = "Temperature : ${celsiusToFahrenheit(weather.current.temp_c)}°F"}
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
                    .padding(5.dp)
            ) {
                CityItem(city = City(name = cities[index].name, description = cities[index].description, imageRes = cities[index].imageRes), currentWeather, humidity)
            }
        }
    }




}
