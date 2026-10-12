package com.example.wheaterapp

import androidx.lifecycle.ViewModel
import com.example.wheaterapp.model.City
import androidx.compose.runtime.toMutableStateList
import com.google.android.gms.maps.model.LatLng

private fun getCities() = List(20){
        i -> City(name= "Cidade $i", weather = "Carregando Clima...")
}

class MainViewModel : ViewModel() {


    private val _cities = getCities().toMutableStateList()

    val cities
        get() = _cities.toList()
    fun remove(city: City) {
        _cities.remove(city)
    }
    fun add(name: String) {
        _cities.add(City(name = name))
    }

    fun add(name: String, location: LatLng? = null) {
        _cities.add(City(name = name, location = location))
    }
}