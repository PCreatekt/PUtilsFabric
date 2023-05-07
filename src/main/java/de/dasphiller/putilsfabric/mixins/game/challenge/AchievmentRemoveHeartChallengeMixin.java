package de.dasphiller.putilsfabric.mixins.game.challenge;

import de.dasphiller.putilsfabric.challenge.impl.action.AdvancementRemoveHeartChallenge;
import de.dasphiller.putilsfabric.timer.Timer;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(PlayerAdvancementTracker.class)
public class AchievmentRemoveHeartChallengeMixin {

    @Inject(method = "sendUpdate", at = @At("HEAD"))
    public void onChallenge(ServerPlayerEntity serverPlayerEntity, CallbackInfo ci) {
        if (AdvancementRemoveHeartChallenge.INSTANCE.getEnabled() && Timer.INSTANCE.getRunning()) {
            Objects.requireNonNull(serverPlayerEntity.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).addPersistentModifier(new EntityAttributeModifier("thisnamedosentmatter", -2, EntityAttributeModifier.Operation.ADDITION));
        }
    }//hi philipp - this was my sister haha
}
