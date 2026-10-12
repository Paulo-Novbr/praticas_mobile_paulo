package com.example.wheaterapp.ui

import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.wheaterapp.MainViewModel
import com.google.maps.android.compose.GoogleMap
import androidx.compose.foundation.layout.fillMaxSize
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.maps.android.compose.rememberCameraPositionState
import androidx.compose.runtime.mutableStateOf
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun MapPage(modifier: Modifier = Modifier.Companion, viewModel: MainViewModel) {

        val recife = remember { MarkerState(LatLng(-8.05, -34.9)) }
        val caruaru = remember { MarkerState( LatLng(-8.27, -35.98)) }
        val joaopessoa = remember { MarkerState( LatLng(-7.12, -34.84)) }

        val camPosState = rememberCameraPositionState()
        val context = LocalContext.current
        val hasLocationPermission by remember {
            mutableStateOf(ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        }

        GoogleMap(modifier = modifier.fillMaxSize(),
            cameraPositionState = camPosState,

            onMapClick = {
                val lat = "%.2f".format(it.latitude)
                val lng = "%.2f".format(it.longitude)
                viewModel.add("Cidade@$lat:$lng", location = it)
            },
            properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
            uiSettings = MapUiSettings(myLocationButtonEnabled = true)

        ) {viewModel.cities.forEach {
            if (it.location != null) {
                val markerState = remember { MarkerState(position = it.location) }
                Marker(state = markerState, title = it.name, snippet = "${it.location}")
            }
        }

            Marker(
                state = recife,
                title = "Recife",
                snippet = "Marcador em Recife",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )

            Marker(
                state = caruaru,
                title = "Caruaru",
                snippet = "Marcador em Caruaru",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )

            Marker(
                state = joaopessoa,
                title = "João Pessoa",
                snippet = "Marcador em João Pessoa",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
            )
    }
}