package com.example.wheaterapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.wheaterapp.ui.theme.WheaterAppTheme
/* -------- */
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WheaterAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterPage(modifier: Modifier = Modifier) {
    var userName by rememberSaveable { mutableStateOf("") }
    var userEmail by rememberSaveable { mutableStateOf("") }
    var userPassword by rememberSaveable { mutableStateOf("") }
    var userRepeatedPassword by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity

    Column(
        modifier = modifier
            .padding(24.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally


    ) {

        Text (
            text = "Cadastro",
            fontWeight = FontWeight.Bold,
            color = Color.Magenta,
            modifier = modifier.align(CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )


        OutlinedTextField(
            value = userName,
            onValueChange = {userName = it},
            label = { Text("Nome: ")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = userEmail,
            onValueChange = {userEmail = it},
            label = {Text("Email: ")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = userPassword,
            onValueChange = {userPassword = it},
            visualTransformation = PasswordVisualTransformation(),
            label = {Text("Senha: ")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = userRepeatedPassword,
            onValueChange = {userRepeatedPassword = it},
            visualTransformation = PasswordVisualTransformation(),
            label = {Text("Confirme a Senha: ")},
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Button(
                onClick = {
                    userName = ""
                    userEmail = ""
                    userPassword = ""
                    userRepeatedPassword = ""
                }
            ) {
                Text("Limpar Fomrulário")
            }

            Button(
                onClick = {
                    Toast.makeText(activity, "Registrado com sucesso!",Toast.LENGTH_LONG).show()
                    activity.finish() },
                enabled = userEmail.isNotEmpty()
                        && userName.isNotEmpty()
                        && userPassword.isNotEmpty()
                        && userRepeatedPassword.isNotEmpty()
                        && userPassword == userRepeatedPassword
            ) {
                Text("Registrar")
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WheaterAppTheme {
        RegisterPage()
    }
}