package de.dasphiller.putilsfabric.timer

import de.dasphiller.putilsfabric.challenge.ChallengeManager
import de.dasphiller.putilsfabric.challenge.active
import kotlinx.coroutines.cancel
import net.minecraft.network.packet.s2c.play.OverlayMessageS2CPacket
import net.minecraft.world.GameMode
import net.minecraft.world.event.GameEvent
import net.silkmc.silk.core.Silk
import net.silkmc.silk.core.annotations.DelicateSilkApi
import net.silkmc.silk.core.task.infiniteMcCoroutineTask
import net.silkmc.silk.core.text.literalText
import kotlin.time.Duration.Companion.seconds

object Timer {
    var s: Int = 0
    var m: Int = 0
    var h: Int = 0
    var running: Boolean = false
    var firstStart: Boolean = true

    @OptIn(DelicateSilkApi::class)
    operator fun invoke() {
        val server = Silk.currentServer
        infiniteMcCoroutineTask(period = 1.seconds) {
            if (running) {
                server?.playerManager?.playerList?.forEach {
                    it.sendMessage(literalText(
                        if (m == 0 && h == 0) {
                            "${s}s"
                        } else if (h == 0) {
                            "${m}min ${s}s"
                        } else "${h}h ${m}m ${s}s"
                    ) {
                        italic = false
                        color = 0x17e71b
                    }, true)
                }
            } else {
                server?.playerManager?.playerList?.forEach {
                    it.sendMessage(literalText(
                        if (m == 0 && h == 0) {
                            "${s}s"
                        } else if (h == 0) {
                            "${m}min ${s}s"
                        } else "${h}h ${m}m ${s}s"
                    ) {
                        italic = !firstStart
                        color = if (firstStart) 0xFF4433 else 0xFFE324
                    }, true)
                }
            }
        }
    }

    @OptIn(DelicateSilkApi::class)
    fun start() {
        if (running) return
        firstStart = true
        running = true
        ChallengeManager.challenges.forEach {
            if (it.active) {
                it.onTimerStart()
            }
        }
        Silk.currentServer?.playerManager?.playerList?.forEach {
            it.changeGameMode(GameMode.SURVIVAL)
        }
        infiniteMcCoroutineTask(period = 1.seconds, delay = 1.seconds) {
            if (running) {
                s++
                if (s == 60) {
                    m++
                    s = 0
                }
                if (m == 60) {
                    h++
                    m = 0
                }
            } else {
                this.cancel()
            }
        }
    }

    @OptIn(DelicateSilkApi::class)
    fun stop() {
        if (!running) return
        running = false
        ChallengeManager.challenges.forEach {
            if (it.active) {
                it.onTimerStop()
            }
        }
        Silk.currentServer?.playerManager?.playerList?.forEach {
            it.changeGameMode(GameMode.ADVENTURE)
        }
    }

    fun reset() {
        stop()
        s = 0
        m = 0
        h = 0
    }
}