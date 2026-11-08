package com.example.wheaterapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.wheaterapp.model.City
import androidx.compose.runtime.toMutableStateList
import com.example.wheaterapp.db.fb.FBDatabase
import com.example.wheaterapp.model.User
import com.google.android.gms.maps.model.LatLng
import com.example.wheaterapp.db.fb.FBCity
import com.example.wheaterapp.db.fb.FBUser
import com.example.wheaterapp.db.fb.toFBCity
import androidx.lifecycle.ViewModelProvider
import com.example.wheaterapp.api.WeatherService

private fun getCities() = List(20){
        i -> City(name= "Cidade $i", weather = "Carregando Clima...")
}

class MainViewModel (private val db: FBDatabase, private val service : WeatherService) : ViewModel(), FBDatabase.Listener {

    init {
        db.setListener(this)
    }

    fun remove(city: City) {
        db.remove(city.toFBCity())
    }
    fun add(name: String, location : LatLng? = null) {
        db.add(City(name = name, location = location).toFBCity())
    }

    fun addCity(name: String) {
        service.getLocation(name) { lat, lng ->
            if (lat != null && lng != null) {
                db.add(City(name=name, location=LatLng(lat, lng)).toFBCity())
            }
        }
    }
    fun addCity(location: LatLng) {
        service.getName(location.latitude, location.longitude) { name ->
            if (name != null) {
                db.add(City(name = name, location = location).toFBCity())
            }
        }
    }


    private val _cities = getCities().toMutableStateList()
    private val _user = mutableStateOf<User?>(null)

    val user : User?
        get() = _user.value

    val cities
        get() = _cities.toList()

    override fun onUserLoaded(user: FBUser) {
        _user.value  = user.toUser()
    }

    override fun onUserSignOut() {
        // De acordo com a prática, não precisa implementar, mas vou tester:
        _user.value = null
    }

    override fun onCityAdded(city: FBCity) {
        val _city : City = city.toCity()
        _cities.add(_city)
    }

    override fun onCityUpdated(city: FBCity) {
        TODO("Not yet implemented")
    }

    override fun onCityRemoved(city: FBCity) {
        val _city : City = city.toCity()
        _cities.remove(_city)
    }

    /**
    fun remove(city: City) {
        _cities.remove(city)
    }
    fun add(name: String) {
        _cities.add(City(name = name))
    }

    fun add(name: String, location: LatLng? = null) {
        _cities.add(City(name = name, location = location))
    }
    **/
}

class MainViewModelFactory(private val db : FBDatabase, private val service : WeatherService) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(db, service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}