package acuario

// Interfaz AccionPez
interface AccionPez {
    fun comer()
}

// Clase Abstracta Pez
abstract class Pez {
    abstract val color: String
}

// Clases concretas que implementan la clase abstracta Pez y la interfaz AccionPez
class Tiburon : Pez(), AccionPez {
    override val color = "gris"
    override fun comer() {
        println("cazar y comer peces")
    }
}

class PezPayaso : Pez(), AccionPez {
    override val color = "dorado"
    override fun comer() {
        println("comer algas")
    }
}