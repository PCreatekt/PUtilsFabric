package de.dasphiller.putilsfabric.mixins.game.challenge;

import de.dasphiller.putilsfabric.challenge.impl.randomizer.SneakRandomMobChallenge;
import de.dasphiller.putilsfabric.timer.Timer;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.PlayerManager;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class SneakRandomMobChallengeMixin {

    @Inject(method = "adjustMovementForSneaking", at = @At("HEAD"))
    public void onSneak(Vec3d vec3d, MovementType movementType, CallbackInfoReturnable<Vec3d> cir) {
        PlayerEntity player = (PlayerEntity) (Object) (this);
        if (SneakRandomMobChallenge.INSTANCE.getEnabled() && Timer.INSTANCE.getRunning()) {
            SneakRandomMobChallenge.INSTANCE.onChallenge(player);
        }
    }
}
