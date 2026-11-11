package com.example.wheaterapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.ui.Modifier

@Composable
fun CityDialog(onDismiss: () -> Unit, onConfirm: (city: String) -> Unit, viewModel: CityDialogViewModel) {
    val cityName = remember { mutableStateOf("") }
    Dialog(onDismissRequest = { onDismiss() } ) {
        Surface( shape = RoundedCornerShape(16.dp) ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Adicionar cidade favorita:")
                    Icon(imageVector = Icons.Filled.Close,
                        contentDescription = "",
                        modifier = Modifier.clickable { onDismiss() })
                }
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Nome da cidade") },
                    //value = cityName.value,
                    value = viewModel.query,
                    //onValueChange = { cityName.value = it })
                    onValueChange = {viewModel.onQueryChanged(it)}
                )

                if (viewModel.query.isBlank()){Text("Digite uma Cidade! ")}
                else if (viewModel.query.length < 3){Text("Precisa ter, pelo menos, 3 caracteres!")}
                else if (viewModel.isLoading){
                    Box(modifier = Modifier.fillMaxWidth().padding(16.dp),contentAlignment = Alignment.Center){
                        CircularProgressIndicator()}}
                else if (viewModel.suggestions.isNotEmpty()){
                    LazyColumn(modifier = Modifier.heightIn(max = 200.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)){
                        items(viewModel.suggestions) { city ->
                            ListItem(
                                headlineContent = { Text(city.name) },
                                modifier = Modifier.clickable { onConfirm(city.name) }
                            )
                        }
                    }
                }
                else{Text("Nenhuma cidade encontrada")}

                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = { onConfirm(viewModel.query)},
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) { Text(text = "OK") }
            }
        }
    }
}