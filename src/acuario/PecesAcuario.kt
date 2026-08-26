package acuario

// Interfaz para el comportamiento del color
interface ColorPez {
    val color: String
}

// Implementaciones concretas de ColorPez (Objetos Singleton)
object ColorPezGris : ColorPez {
    override val color = "gris"
}

object ColorPezDorado : ColorPez {
    override val color = "dorado"
}

// Interfaz AccionPez
interface AccionPez {
    fun comer()
}

// Implementación de comportamiento de comida
class ComerPeces : AccionPez {
    override fun comer() {
        println("cazar y comer peces")
    }
}

class ComerAlgas : AccionPez {
    override fun comer() {
        println("comer algas")
    }
}

// Delegación usando la palabra clave 'by'
class Tiburon(colorPez: ColorPez = ColorPezGris) :
    ColorPez by colorPez,
    AccionPez by ComerPeces()

class PezPayaso(colorPez: ColorPez = ColorPezDorado) :
    ColorPez by colorPez,
    AccionPez by ComerAlgas()