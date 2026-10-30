package com.example.wheaterapp.db.fb

import com.example.wheaterapp.model.User
import com.google.android.gms.maps.model.LatLng

class FBUser {

    var name : String? = null
    var email : String? = null

    fun toUser(): User {
        val nome = name ?: "Fulano"

        val emailValido = email ?: requireNotNull(email) {"email não encontrado"}

        return User(nome, emailValido)
    }

    fun User.toFBUser() : FBUser {
        val fbUser = FBUser()
        fbUser.name = this.name
        fbUser.email = this.email
        return fbUser
    }
}