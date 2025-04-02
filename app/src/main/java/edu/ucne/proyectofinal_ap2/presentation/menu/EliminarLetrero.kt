package edu.ucne.proyectofinal_ap2.presentation.menu
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavController

@Composable
fun EliminarLetreroScreen(navController: NavController) {
    val context = LocalContext.current
    var letreros by remember { mutableStateOf<List<Pair<String, String>>>(emptyList()) }

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("letreros").get()
            .addOnSuccessListener { result ->
                letreros = result.documents.map {
                    it.id to (it.getString("nombre") ?: "Sin nombre")
                }
            }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Eliminar Letrero") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                }
            }
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(8.dp))

            letreros.forEach { (id, nombre) ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            Firebase.firestore.collection("letreros").document(id).delete()
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Eliminado correctamente", Toast.LENGTH_SHORT).show()
                                    letreros = letreros.filterNot { it.first == id }
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show()
                                }
                        },
                    elevation = 4.dp
                ) {
                    Text(nombre, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}