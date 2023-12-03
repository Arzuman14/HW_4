package com.example.cities_mobile_dubl2.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cities_mobile_dubl2.constants.WELCOME_SCREEN_ROUTE
import com.example.cities_mobile_dubl2.isCelsius
import com.example.cities_mobile_dubl2.viewmodel.WeatherViewModel

@Composable
fun Settings(navController: NavHostController, viewModel: WeatherViewModel = viewModel() ) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {


            var selectedOption by remember { mutableStateOf(isCelsius) }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

                Text(
                    text = "Temperature unit preference",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )


            Spacer(modifier = Modifier.width(20.dp))

                RadioButton (
                    selected = selectedOption == isCelsius ,
                    onClick = {selectedOption = isCelsius
                              isCelsius=true},
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
                )
                Text ("(°C)")

             Spacer(modifier = Modifier.width(20.dp))

             RadioButton (
                 selected = selectedOption == !isCelsius ,
                 onClick = {selectedOption = !isCelsius
                           isCelsius = false},
                 colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
             )
             Text ("(°F) ")



        }

        Box() {
            Button(
                onClick = {
                    navController.navigate(WELCOME_SCREEN_ROUTE)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            ) {
                Text(text = "HOME")
            }
        }
    }
}