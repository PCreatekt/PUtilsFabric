package de.dasphiller.putilsfabric.commands

import de.dasphiller.putilsfabric.challenge.ChallengeManager
import de.dasphiller.putilsfabric.challenge.active
import de.dasphiller.putilsfabric.gui.challengeGui
import net.fabricmc.api.Environment
import net.fabricmc.loader.api.FabricLoader
import net.silkmc.silk.commands.command
import net.silkmc.silk.igui.openGui

val settingsCommand = command("settings") {
    requiresPermissionLevel(4)
    runs {
        source.player?.openGui(challengeGui(), 2)
    }
}