package com.example.wheaterapp.ui

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import com.example.wheaterapp.api.WeatherService
import com.example.wheaterapp.model.City
import com.google.android.gms.maps.model.LatLng

import androidx.lifecycle.ViewModelProvider
import com.example.wheaterapp.MainViewModel

class CityDialogViewModel( private val service: WeatherService ) : ViewModel() {
    var query by mutableStateOf("")
    var isLoading by mutableStateOf(false)
        private set


    var suggestions by mutableStateOf<List<City>>(emptyList())
        private set
    fun onQueryChanged(value: String) {
        query = value
        if (value.length < 3) {
            suggestions = emptyList()
        } else {

            isLoading = true

            service.cancelAll()
            service.search(query) { res ->
                val cities = res?.map {
                    City(it.name!!, location = LatLng(it.lat!!, it.lon!!))
                }
                cities?.let { suggestions = cities.toList() }
                isLoading = false
            }

        }
    }
}

class CityDialogVM(private val ws : WeatherService) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityDialogViewModel::class.java)) {
            return CityDialogViewModel(ws) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}