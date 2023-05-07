package de.dasphiller.putilsfabric.challenge.impl.randomizer

import de.dasphiller.putilsfabric.challenge.*
import de.dasphiller.putilsfabric.timer.Timer
import de.hglabor.notify.events.server.player.PlayerBreakBlockEvent
import me.obsilabor.alert.kotlin.subscribeToEvent
import net.minecraft.entity.SpawnReason
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.server.world.ServerWorld

object BlockRandomMobChallenge : Challenge() {
    override val name: String = "BlockRandomMob"
    override val id: String = "blockrandommob"
    override val icon: ItemConvertible = Items.AXOLOTL_SPAWN_EGG
    override val description: String = "If you break a block a random mob will spawn"
    override var enabled: Boolean = false
    override val devType: DevType = DevType.STABLE
    override val challengeType: List<ChallengeType> = listOf(ChallengeType.RANDOMIZER)
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.HARD)

    private val blockBreak = subscribeToEvent<PlayerBreakBlockEvent>(isActiveCallback = { false }) {
        if (active) {
            if (Timer.running) {
                val list = Registries.ENTITY_TYPE.toList()
                list.random().spawn(it.player.world as ServerWorld, it.pos, SpawnReason.SPAWN_EGG)
            }
        }
    }

    override fun onTimerStart() {
        blockBreak
    }

    override fun onTimerStop() {

    }

}