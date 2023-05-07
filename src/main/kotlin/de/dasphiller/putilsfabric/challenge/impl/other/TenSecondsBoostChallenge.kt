package de.dasphiller.putilsfabric.challenge.impl.other

import de.dasphiller.putilsfabric.challenge.Challenge
import de.dasphiller.putilsfabric.challenge.ChallengeDifficulty
import de.dasphiller.putilsfabric.challenge.ChallengeType
import de.dasphiller.putilsfabric.challenge.DevType
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.util.math.Vec3d
import net.silkmc.silk.core.entity.modifyVelocity
import net.silkmc.silk.core.task.infiniteMcCoroutineTask
import java.awt.event.FocusEvent.Cause
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

object TenSecondsBoostChallenge : Challenge() {
    override val name: String = "TenSecondsBoost"
    override val id: String = "tensecondsboost"
    override val icon: ItemConvertible = Items.DRAGON_BREATH
    override val description: String = "Every 10 seconds you get boosted up"
    override var enabled: Boolean = false
    override val devType: DevType = DevType.STABLE
    override val challengeType: List<ChallengeType> = listOf(ChallengeType.OTHER)
    override val challengeDifficulty: List<ChallengeDifficulty> = listOf(ChallengeDifficulty.MEDIUM)

    private val task = infiniteMcCoroutineTask(period = 10.seconds) {
        onlinePlayers.forEach {
            val random = Random.nextDouble(0.6, 1.5)
            it.modifyVelocity(Vec3d(0.0, random, 0.0))
        }
    }

    override fun onTimerStart() {
       task.start()
    }

    override fun onTimerStop() {
        task.cancel()
    }
}