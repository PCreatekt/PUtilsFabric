package de.dasphiller.putilsfabric.challenge.impl.randomizer

import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeDifficulty
import de.dasphiller.putilsfabric.challenge.ChallengeType
import de.dasphiller.putilsfabric.challenge.DevType
import de.dasphiller.putilsfabric.timer.Timer
import de.hglabor.notify.events.server.player.PlayerDamageEvent
import me.obsilabor.alert.kotlin.subscribeToEvent
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.silkmc.silk.core.text.sendText
import kotlin.random.Random
import kotlin.random.nextInt

object RandomDamageChallenge : Challenge() {
    override val name: String = "RandomDamage"
    override val id: String = "randomdamage"
    override val icon: ItemConvertible = Items.RED_DYE
    override val description: String = "The damage is random"
    override var enabled: Boolean = false
    override val devType: DevType = DevType.STABLE
    override val challengeType: List<ChallengeType> = listOf(ChallengeType.RANDOMIZER)
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.MEDIUM)

    fun onChallenge(entity: PlayerEntity, damage: Float) {
        entity.health = entity.health - damage
        if (entity.health == 0F) {
            lose(entity)
        }
    }



    override fun onTimerStart() {

    }
}