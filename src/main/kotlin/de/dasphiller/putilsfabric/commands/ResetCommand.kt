package de.dasphiller.putilsfabric.commands

import de.dasphiller.putilsfabric.color.color
import net.silkmc.silk.commands.command
import net.silkmc.silk.core.Silk
import net.silkmc.silk.core.annotations.DelicateSilkApi
import net.silkmc.silk.core.text.literalText

@OptIn(DelicateSilkApi::class)
val resetCommand = command("reset") {
    runs {
        Silk.currentServer?.playerManager?.playerList?.forEach {
            it.networkHandler.disconnect(literalText("The worlds will get deleted") {
                color = color("red")
            })
        }
        Silk.currentServer?.stop(true)
    }
}