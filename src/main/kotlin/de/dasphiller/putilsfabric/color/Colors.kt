package de.dasphiller.putilsfabric.color

fun color(color: String): Int {
    return when (color) {
        "red" -> 0xf44336

        "darkgreen" -> 0x8fce00

        "darkblue" -> 0x0b5394

        "orange" -> 0xe69138

        "lightblue" -> 0x229cf7

        "limeyellow" -> 0xabef10

        "blue" -> 0x2986cc

        "darkred" -> 0xcc0000

        else -> 0xf44336
    }
}