package com.example.tareatresnavegacion.screens

import android.text.TextUtils
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


fun obtenerMensaje(nombre: String, id: Int): String {
    val idIsnd = listOf(19475, 19508, 19523, 19666, 21637, 21767, 22098, 22154, 22180, 22208, 22210)
    Log.i("MyActivity", "${id}")
    return when {
        id <= 10 -> "Bienvenido al laboratorio de ISND, estimado coordinador $nombre."
        id <= 100 -> "Permiso autorizado para el profesor $nombre."
        id <= 15000 -> "Acceso denegado a egresados."
        id in idIsnd -> "Alumno $nombre autorizado para uso del laboratorio."
        else -> "Este laboratorio es de uso exclusivo para la carrera ISND."
    }
}

@Composable
fun HomeView(navController: NavHostController){
    var nombre by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(text = "SIE IEST")
        TextField(value = nombre, onValueChange = {
            nombre = it
        }, label= {
            Text(text = "Ingresa nombre:")
        }, placeholder =  {
            Text(text = "Porfavor escribe tu nombre")
        }, leadingIcon = {
            Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Icon")
        })
        TextField(value = id, onValueChange = {
            if(it.toIntOrNull() != null) {
                id = it
            } else if (TextUtils.isEmpty(it)) {
                id = ""
            }
        }, label= {
            Text(text = "ID Iest:")
        }, placeholder =  {
            Text(text = "Porfavor escribe tu ID IEST")
        }, leadingIcon = {
            Icon(imageVector = Icons.Default.ThumbUp, contentDescription = "Icon")
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Button(onClick = {
            navController.navigate("MessageView/${nombre}/${id.toInt()}")
        }) {
            Text(text = "Entrar")
        }
    }
}

@Composable
fun MessageView(navController: NavHostController, nombre: String, id: Int) {
    val message = obtenerMensaje(nombre, id)
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "${message}")
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults
                .buttonColors(Color(0xFFFF7700))
        ) {
            Text(text = "Regresar")
        }
   }
}
