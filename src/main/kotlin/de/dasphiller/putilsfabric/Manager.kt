package de.dasphiller.putilsfabric

import de.dasphiller.putilsfabric.challenge.ChallengeManager
import de.dasphiller.putilsfabric.commands.settingsCommand
import de.dasphiller.putilsfabric.commands.timerCommand
import de.dasphiller.putilsfabric.timer.Timer
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

val logger: Logger = LogManager.getLogger("putilsfabric")

object Manager: ModInitializer, DedicatedServerModInitializer, ClientModInitializer {

    override fun onInitialize() {
        timerCommand
        settingsCommand
        ServerLifecycleEvents.SERVER_STARTED.register {
            Timer()
            ChallengeManager()
        }
    }

    override fun onInitializeClient() {
        // Client initialization
    }

    override fun onInitializeServer() {
        // Dedicated server initialization
    }
}