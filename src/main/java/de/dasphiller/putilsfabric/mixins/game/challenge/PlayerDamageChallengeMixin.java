package de.dasphiller.putilsfabric.mixins.game.challenge;

import de.dasphiller.putilsfabric.challenge.impl.action.DamageInvClearChallenge;
import de.dasphiller.putilsfabric.challenge.impl.randomizer.RandomDamageChallenge;
import de.dasphiller.putilsfabric.timer.Timer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(PlayerEntity.class)
public class PlayerDamageChallengeMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void onDamage(DamageSource damageSource, float f, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity entity = (PlayerEntity) (Object) (this);
        if (RandomDamageChallenge.INSTANCE.getEnabled() && Timer.INSTANCE.getRunning()) {
            cir.setReturnValue(true);
            Random random = new Random();
            int n = random.nextInt((19 - 1) + 1) + 1;
            RandomDamageChallenge.INSTANCE.onChallenge(entity, n);
        }
        if (!Timer.INSTANCE.getRunning()) {
            cir.setReturnValue(true);
        }
        if (DamageInvClearChallenge.INSTANCE.getEnabled() && Timer.INSTANCE.getRunning()) {
            DamageInvClearChallenge.INSTANCE.onChallenge();
        }
    }
}
