package de.dasphiller.putilsfabric.challenge

import net.minecraft.text.Text
import net.silkmc.silk.core.text.literalText

enum class DevType(val type: Text) {
    STABLE(literalText("Stable") {
        italic = false
        color = 0x5cf51a
    }),
    UNSTABLE(literalText("Unstable") {
        italic = false
        color = 0xcc0000
    }),
    DEV(literalText("In development") {
        italic = false
        color = 0x00ffff
    })
}