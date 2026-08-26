package acuario

// Data Class para representar la decoración
data class Decoracion(val material: String)

// Data Class con múltiples propiedades
data class Decoracion2(val material: String, val madera: String, val lugar: String)

// Enum Class para las direcciones
enum class Direccion(val grados: Int) {
    NORTE(0),
    SUR(180),
    ESTE(90),
    OESTE(270)
}

fun hacerDecoraciones() {
    val d1 = Decoracion("granito")
    println(d1)

    val d2 = Decoracion("granito")
    println(d2)

    // Comparación de objetos (data class compara el contenido)
    println("¿d1 es igual a d2?: ${d1 == d2}")

    // Destructuración de Data Class
    val d5 = Decoracion2("granito", "madera flotante", "divertido")
    val (material, madera, lugar) = d5
    println("Material: $material, Madera: $madera, Lugar: $lugar")
}