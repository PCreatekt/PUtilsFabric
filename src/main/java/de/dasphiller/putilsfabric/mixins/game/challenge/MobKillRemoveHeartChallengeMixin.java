package de.dasphiller.putilsfabric.mixins.game.challenge;

import de.dasphiller.putilsfabric.challenge.impl.action.MobKillRemoveHeartChallenge;
import de.dasphiller.putilsfabric.timer.Timer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PlayerEntity.class)
public abstract class MobKillRemoveHeartChallengeMixin {

    @Inject(method = "onKilledOther", at = @At("HEAD"))
    public void onKill(ServerWorld serverWorld, LivingEntity livingEntity, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity) (Object) (this);
        if (MobKillRemoveHeartChallenge.INSTANCE.getEnabled() && Timer.INSTANCE.getRunning()) {
            Objects.requireNonNull(player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH)).addPersistentModifier(new EntityAttributeModifier("thisnamedosentmatter", -2, EntityAttributeModifier.Operation.ADDITION));
        }
    }
}
