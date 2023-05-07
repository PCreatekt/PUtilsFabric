package de.dasphiller.putilsfabric.challenge

import de.dasphiller.putilsfabric.challenge.impl.action.DamageInvClearChallenge
import de.dasphiller.putilsfabric.challenge.impl.action.MobKillRemoveHeartChallenge
import de.dasphiller.putilsfabric.challenge.impl.action.NoJumpChallenge
import de.dasphiller.putilsfabric.challenge.impl.action.PlayerDeathChallenge
import de.dasphiller.putilsfabric.challenge.impl.other.SpeedChallenge
import de.dasphiller.putilsfabric.challenge.impl.other.TenSecondsBoostChallenge
import de.dasphiller.putilsfabric.challenge.impl.randomizer.BlockRandomMobChallenge
import de.dasphiller.putilsfabric.challenge.impl.randomizer.DamageRandomEffectChallenge
import de.dasphiller.putilsfabric.challenge.impl.randomizer.RandomDamageChallenge
import de.dasphiller.putilsfabric.challenge.impl.randomizer.SneakRandomMobChallenge

object ChallengeManager {

    lateinit var challenges: ArrayList<Challenge>

    operator fun invoke() {
        challenges = arrayListOf(
            BlockRandomMobChallenge,
            RandomDamageChallenge,
            NoJumpChallenge,
            PlayerDeathChallenge,
            SpeedChallenge,
            DamageRandomEffectChallenge,
            TenSecondsBoostChallenge,
            DamageInvClearChallenge,
            MobKillRemoveHeartChallenge,
        )
    }

    fun enableChallenges() {
        challenges.forEach {
            if (it.active) {
                it.onEnable()
            }
        }
    }

    fun disableChallenges() {
        challenges.forEach {
            if (it.active) {
                it.onDisable()
            }
        }
    }
}