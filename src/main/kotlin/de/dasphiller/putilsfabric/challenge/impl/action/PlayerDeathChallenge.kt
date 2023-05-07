package de.dasphiller.putilsfabric.challenge.impl.action

import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeDifficulty
import de.dasphiller.putilsfabric.challenge.ChallengeType
import de.dasphiller.putilsfabric.challenge.DevType
import de.dasphiller.putilsfabric.timer.Timer
import de.hglabor.notify.events.server.player.PlayerDeathEvent
import me.obsilabor.alert.kotlin.subscribeToEvent
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items

object PlayerDeathChallenge : Challenge() {
    override val name: String = "PlayerDeath"
    override val id: String = "playerdeath"
    override val icon: ItemConvertible = Items.SKELETON_SKULL
    override val description: String = "If you die the challenge ends"
    override var enabled: Boolean = true
    override val devType: DevType = DevType.STABLE
    override val challengeType: List<ChallengeType> = listOf()
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.HARD)

    private val death = subscribeToEvent<PlayerDeathEvent> {
        if (enabled) {
            if (Timer.running) {
                lose(it.player)
            }
        }
    }

    override fun onTimerStart() {
        death
    }

}