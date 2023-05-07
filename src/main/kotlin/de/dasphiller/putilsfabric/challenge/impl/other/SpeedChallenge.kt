package de.dasphiller.putilsfabric.challenge.impl.other

import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeDifficulty
import de.dasphiller.putilsfabric.challenge.ChallengeType
import de.dasphiller.putilsfabric.challenge.DevType
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.silkmc.silk.core.Silk
import net.silkmc.silk.core.annotations.DelicateSilkApi

object SpeedChallenge : Challenge() {
    override val name: String = "Speed"
    override val id: String = "speed"
    override val icon: ItemConvertible = Items.FEATHER
    override val description: String = "You will be fast as fuck"
    override var enabled: Boolean = false
    override val devType: DevType = DevType.STABLE
    override val challengeType: List<ChallengeType> = listOf(ChallengeType.OTHER)
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.FUN)

    @OptIn(DelicateSilkApi::class)
    override fun onTimerStart() {
        Silk.currentServer?.playerManager?.playerList?.forEach {
            it.addStatusEffect(StatusEffectInstance(StatusEffect.byRawId(1), StatusEffectInstance.INFINITE, 19))
        }
    }
}

