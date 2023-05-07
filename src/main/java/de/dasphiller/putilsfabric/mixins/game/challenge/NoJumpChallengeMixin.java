package de.dasphiller.putilsfabric.mixins.game.challenge;

import de.dasphiller.putilsfabric.challenge.impl.action.NoJumpChallenge;
import de.dasphiller.putilsfabric.timer.Timer;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class NoJumpChallengeMixin {

    @Inject(method = "jump", at = @At("HEAD"))
    public void onJump(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) (this);
        if (NoJumpChallenge.INSTANCE.getEnabled() && Timer.INSTANCE.getRunning()) {
            NoJumpChallenge.INSTANCE.onChallenge(player);
        }
    }
}
