package de.dasphiller.putilsfabric.challenge

import de.dasphiller.putilsfabric.timer.Timer
import de.dasphiller.putilsfabric.util.ChallengeUtil
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.world.GameMode
import net.silkmc.silk.core.Silk
import net.silkmc.silk.core.annotations.DelicateSilkApi
import net.silkmc.silk.core.text.literalText
import net.silkmc.silk.core.text.sendText

abstract class Challenge {
    @OptIn(DelicateSilkApi::class)
    val server = Silk.currentServer!!
    abstract val name: String
    abstract val id: String
    abstract val icon: ItemConvertible
    abstract val description: String
    abstract var enabled: Boolean
    abstract val devType: DevType
    abstract val challengeType: List<ChallengeType>
    abstract val challengeDifficulty: List<ChallengeDifficulty>

    open fun onEnable() {

    }

    open fun onDisable() {

    }

    open fun onTimerStart() {

    }

    open fun onTimerStop() {

    }

    open fun lose(player: PlayerEntity) {
        if (player.isSpectator) return
        if (!Timer.running) return
        server?.playerManager?.playerList?.forEach {
            Timer.stop()
            it.changeGameMode(GameMode.SPECTATOR)
            it.sendText(literalText("The Challenge has ended") {
                color = 0xdf3939
                bold = true
            })
        }
    }

    val challengeUtil = ChallengeUtil()
    val onlinePlayers = server.playerManager.playerList
}

var Challenge.active
    get() = enabled
    set(value) = challengeUtil.setStatus(id, value)