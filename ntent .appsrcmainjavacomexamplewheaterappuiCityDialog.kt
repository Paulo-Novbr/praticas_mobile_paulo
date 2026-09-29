warning: in the working copy of 'app/src/main/java/com/example/wheaterapp/MainActivity.kt', LF will be replaced by CRLF the next time Git touches it
[1mdiff --git a/app/src/main/java/com/example/wheaterapp/MainActivity.kt b/app/src/main/java/com/example/wheaterapp/MainActivity.kt[m
[1mindex 0432faf..3ef9e51 100644[m
[1m--- a/app/src/main/java/com/example/wheaterapp/MainActivity.kt[m
[1m+++ b/app/src/main/java/com/example/wheaterapp/MainActivity.kt[m
[36m@@ -1,6 +1,9 @@[m
 package com.example.wheaterapp[m
 [m
[32m+[m
 import android.os.Bundle[m
[32m+[m[32mimport com.example.wheaterapp.ui.CityDialog[m
[32m+[m[32mimport androidx.compose.runtime.*[m
 import androidx.activity.ComponentActivity[m
 import androidx.activity.compose.setContent[m
 import androidx.activity.enableEdgeToEdge[m
[36m@@ -36,8 +39,15 @@[m [mclass MainActivity : ComponentActivity() {[m
         enableEdgeToEdge()[m
         setContent {[m
             val viewModel : MainViewModel by viewModels()[m
[32m+[m[32m            var showDialog by remember { mutableStateOf(false) }[m
             val navController = rememberNavController()[m
             WheaterAppTheme {[m
[32m+[m[32m                if (showDialog) CityDialog([m
[32m+[m[32m                    onDismiss = { showDialog = false },[m
[32m+[m[32m                    onConfirm = { city ->[m
[32m+[m[32m                        if (city.isNotBlank()) viewModel.add(city)[m
[32m+[m[32m                        showDialog = false[m
[32m+[m[32m                    })[m
                 Scaffold([m
                     topBar = {[m
                         TopAppBar([m
[36m@@ -62,8 +72,9 @@[m [mclass MainActivity : ComponentActivity() {[m
                         BottomNavBar(navController = navController, items)[m
                     },[m
                     floatingActionButton = {[m
[31m-                        FloatingActionButton(onClick = { }) {[m
[32m+[m[32m                        FloatingActionButton(onClick = { showDialog = true}) {[m
                             Icon(Icons.Default.Add, contentDescription = "Adicionar")[m
[32m+[m
                         }[m
                     }[m
                 ) { innerPadding ->[m
