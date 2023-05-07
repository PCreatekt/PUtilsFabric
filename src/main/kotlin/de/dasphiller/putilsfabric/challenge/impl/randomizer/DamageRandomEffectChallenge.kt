package de.dasphiller.putilsfabric.challenge.impl.randomizer

import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeDifficulty
import de.dasphiller.putilsfabric.challenge.ChallengeType
import de.dasphiller.putilsfabric.challenge.DevType
import de.dasphiller.putilsfabric.timer.Timer
import de.hglabor.notify.events.server.player.PlayerDamageEvent
import me.obsilabor.alert.kotlin.subscribeToEvent
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import kotlin.random.Random
import kotlin.random.nextInt

object DamageRandomEffectChallenge : Challenge() {
    override val name: String = "DamageRandomEffect"
    override val id: String = "damagerandomeffect"
    override val icon: ItemConvertible = Items.POTION
    override val description: String = "If you take damage you get a random effect"
    override var enabled: Boolean = false
    override val devType: DevType = DevType.STABLE
    override val challengeType: List<ChallengeType> = listOf(ChallengeType.RANDOMIZER)
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.MEDIUM)

    private val damage = subscribeToEvent<PlayerDamageEvent> {
        if (Timer.running) {
            val random = Random.nextInt(1..33)
            random.rem(7)
            random.rem(19)
            if (it.player.activeStatusEffects?.contains(StatusEffect.byRawId(random)!!) == true) {

            } else


                it.player.addStatusEffect(
                    StatusEffectInstance(
                        StatusEffect.byRawId(random)!!,
                    )
                )
        }
    }

    override fun onTimerStart() {
        damage
    }

    override fun onTimerStop() {
        damage.unregister()
    }
}