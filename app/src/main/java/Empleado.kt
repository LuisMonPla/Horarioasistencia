data class Empleado(
    val id: Int,
    val nombre: String,
    val semana: Int,
    val miercoles: Double,
    val jueves: Double,
    val viernes: Double,
    val sabado: Double,
    val domingo: Double,
    val lunes: Double,
    val horaExtra: Double,
    var rostro: ByteArray? // ByteArray para representar una imagen
)
