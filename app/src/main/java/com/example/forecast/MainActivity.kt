package com.example.forecast

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.forecast.ui.theme.ForecastTheme
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient( this )

        setContent {
            var hasLocationPermission = remember { mutableStateOf( false ) }
            val requestPermissionLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission()) {
                hasLocationPermission.value = it
            }

            var lat = remember { mutableStateOf( 0.0 ) }
            var lon = remember { mutableStateOf( 0.0 ) }

            var goToNext = remember { mutableStateOf( false ) }

            // ask for permission
            LaunchedEffect(Unit) {
                requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
                hasLocationPermission.value = true
            }

            // if we have permission, then grab the location
            if( goToNext.value ) {
                if(
                    ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        ACCESS_COARSE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    fusedLocationProviderClient
                        .getCurrentLocation( Priority.PRIORITY_BALANCED_POWER_ACCURACY, null )
                        .addOnSuccessListener { Location ->
                            lat.value = Location.latitude
                            lon.value = Location.longitude
                        }
                }
            }

            // if we have the location, then let's go next:
            ForecastTheme {
                val viewModel = hiltViewModel<CurrentConditionsViewModel>()
                navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    viewModel = viewModel,
                    hasLocationPermission = hasLocationPermission.value,
//                    latitudeLongitude = it,
                    lat = lat.value,
                    lon = lon.value,
                    goToNext = goToNext
                )
            }
        }
    }

    fun test() {
        Log.e( "TAG", "TESTING!!!" )
    }
}
