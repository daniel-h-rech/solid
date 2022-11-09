package rech.haeser.daniel.solid.l

import mu.KotlinLogging

open class Rectangle(var width: Int, var height: Int) {
    override fun toString(): String {
        return "${javaClass.simpleName}(width=$width, height=$height)"
    }
}

class InconsistentSquare(width: Int, height: Int) : Rectangle(width, height)

data class Square private constructor(val width: Int, val height: Int) {
    constructor(width: Int) : this(width = width, height = width)
}

private val logger = KotlinLogging.logger {}

fun main() {
    val inconsistentSquare = InconsistentSquare(2, 3)
    inconsistentSquare.height = 4
    logger.info { "$inconsistentSquare" }

    val square = Square(2)
    logger.info { "$square" }
}
