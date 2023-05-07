package de.dasphiller.putilsfabric.challenge.impl.randomizer

import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeDifficulty
import de.dasphiller.putilsfabric.challenge.ChallengeType
import de.dasphiller.putilsfabric.challenge.DevType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.server.world.ServerWorld

object SneakRandomMobChallenge : Challenge() {
    override val name: String = "SneakRandomMob"
    override val id: String = "sneakrandommob"
    override val icon: ItemConvertible = Items.LEATHER_BOOTS
    override val description: String = "If you sneak a random mob will spawn"
    override var enabled: Boolean = false
    override val devType: DevType = DevType.DEV
    override val challengeType: List<ChallengeType> = listOf(ChallengeType.RANDOMIZER)
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.MEDIUM)

    fun onChallenge(player: PlayerEntity) {
        Registries.ENTITY_TYPE.toList().random().spawn(/* serverWorld = */ player.world as ServerWorld, /* blockPos = */
            player.blockPos, /* spawnReason = */
            SpawnReason.NATURAL)
    }
}