package de.dasphiller.putilsfabric.mixins.game;

import net.minecraft.data.Main;
import org.apache.commons.io.FileUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.io.IOException;

@Mixin(Main.class)
public class DeleteWorldMixin {

    @Inject(
            method = "main",
            at = @At("HEAD"),
            remap = false
    )
    private static void onStart(String[] strings, CallbackInfo ci)throws IOException {
        System.out.println("Deleting worlds...");
        FileUtils.deleteDirectory(new File("world"));
    }
}
