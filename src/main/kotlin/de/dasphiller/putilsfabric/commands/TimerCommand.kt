package de.dasphiller.putilsfabric.commands

import de.dasphiller.putilsfabric.gui.challengeGui
import de.dasphiller.putilsfabric.timer.Timer
import net.silkmc.silk.commands.command
import net.silkmc.silk.core.text.literalText
import net.silkmc.silk.core.text.sendText
import net.silkmc.silk.igui.openGui

val timerCommand = command("timer") {
    runs {
        source.player?.openGui(challengeGui(), 3)
    }
    literal("resume") {
        runs {
            Timer.start()
            source.player?.sendText(literalText("The timer has been started") {
                color = 0x66cdaa
            })
        }
    }
    literal("pause") {
        runs {
            Timer.stop()
            source.player?.sendText(literalText("The timer has been stopped") {
                color = 0xff0000
            })
        }
    }
    literal("reset") {
        runs {
            Timer.reset()
            source.player?.sendText(literalText("The timer has been resetted") {
                color = 0xff0000
            })
        }
    }
}