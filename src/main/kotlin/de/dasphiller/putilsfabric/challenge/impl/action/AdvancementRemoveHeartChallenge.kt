package de.dasphiller.putilsfabric.challenge.impl.action

import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeDifficulty
import de.dasphiller.putilsfabric.challenge.ChallengeType
import de.dasphiller.putilsfabric.challenge.DevType
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items

object AdvancementRemoveHeartChallenge : Challenge() {
    override val name: String = "AchievmentDamage"
    override val id: String = "achievmentdamage"
    override val icon: ItemConvertible = Items.EMERALD
    override val description: String = "You lose a heart when you recive an achievment"
    override var enabled: Boolean = false
    override val devType: DevType = DevType.DEV
    override val challengeType: List<ChallengeType> = listOf(ChallengeType.ACTION)
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.MEDIUM)

}