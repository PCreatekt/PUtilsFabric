package de.dasphiller.putilsfabric.challenge.impl.action

import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeDifficulty
import de.dasphiller.putilsfabric.challenge.ChallengeType
import de.dasphiller.putilsfabric.challenge.DevType
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.silkmc.silk.core.Silk
import net.silkmc.silk.core.annotations.DelicateSilkApi

object DamageInvClearChallenge : Challenge() {
    override val name: String = "DamageInvClear"
    override val id: String = "damageinvclear"
    override val icon: ItemConvertible = Items.SHIELD
    override val description: String = "If you take damage every player will lose their inventory"
    override var enabled: Boolean = false
    override val devType: DevType = DevType.STABLE
    override val challengeType: List<ChallengeType> = listOf(ChallengeType.ACTION)
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.HARD)


    @OptIn(DelicateSilkApi::class)
    fun onChallenge() {
        Silk.currentServer?.playerManager?.playerList?.forEach {
            it.inventory.clear()
        }
    }
}