package net.kunmc.lab.nogravitymod.mixin;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
    @Inject(method = "isUsingSwimmingAnimation", at = @At("RETURN"), cancellable = true)
    private void isUsingSwimmingAnimation(CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(true);
    }
}
