package de.dasphiller.putilsfabric.challenge.impl.action

import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeDifficulty
import de.dasphiller.putilsfabric.challenge.ChallengeType
import de.dasphiller.putilsfabric.challenge.DevType
import me.obsilabor.alert.kotlin.subscribeToEvent
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items

object NoJumpChallenge : Challenge() {
    override val name: String = "NoJump"
    override val id: String = "nojump"
    override val icon: ItemConvertible = Items.DIAMOND_BOOTS
    override val description: String = "You are not allowed to jump"
    override var enabled: Boolean = false
    override val devType: DevType = DevType.STABLE
    override val challengeType: List<ChallengeType> = listOf(ChallengeType.ACTION)
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.MEDIUM)

    fun onChallenge(playerEntity: PlayerEntity) {
        lose(playerEntity)
    }
}