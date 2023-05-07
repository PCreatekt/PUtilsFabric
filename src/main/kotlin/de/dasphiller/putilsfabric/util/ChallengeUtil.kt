package de.dasphiller.putilsfabric.util

import de.dasphiller.putilsfabric.challenge.ChallengeManager
import de.dasphiller.putilsfabric.timer.Timer
import net.minecraft.world.GameMode
import net.silkmc.silk.core.Silk
import net.silkmc.silk.core.annotations.DelicateSilkApi

@OptIn(DelicateSilkApi::class)
class ChallengeUtil {
    fun setStatus(id: String, value: Boolean) {
        ChallengeManager.challenges.forEach {
            if (it.id == id) {
                it.enabled = value
            }
        }
    }

    fun win() {
        if (!Timer.running) return
        val server = Silk.currentServer
        server?.playerManager?.playerList?.forEach {
            it.changeGameMode(GameMode.SPECTATOR)
        }
        Timer.stop()
    }
}