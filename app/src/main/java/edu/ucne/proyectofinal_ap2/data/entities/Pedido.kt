package edu.ucne.proyectofinal_ap2.data.entities


data class Pedido(
    val id: String = "",
    val material: String = "",
    val medida: String = "",
    val alto: String = "",
    val ancho: String = "",
    val logoUrl: String = "",
    val precio: Double = 0.0,
    val estado: String = "pendiente",
    val fecha: Long? = null,
    val nombre: String = "",
    val apellido: String = "",
    val telefono: String = "",
    val direccion: String = ""
)

