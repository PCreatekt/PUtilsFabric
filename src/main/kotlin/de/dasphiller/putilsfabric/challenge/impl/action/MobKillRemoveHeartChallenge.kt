package de.dasphiller.putilsfabric.challenge.impl.action

import com.google.common.collect.Multimap
import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeDifficulty
import de.dasphiller.putilsfabric.challenge.ChallengeType
import de.dasphiller.putilsfabric.challenge.DevType
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items

object MobKillRemoveHeartChallenge : Challenge() {
    override val name: String = "MobKillRemoveHeart"
    override val id: String = "mobkillremoveheart"
    override val icon: ItemConvertible = Items.IRON_SWORD
    override val description: String = "If you kill a mob you lose a heart"
    override var enabled: Boolean = false
    override val devType: DevType = DevType.UNSTABLE
    override val challengeType: List<ChallengeType> = listOf(ChallengeType.ACTION)
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.HARD)
}