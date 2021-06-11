package net.kunmc.lab.nogravitymod.mixin;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
    @Inject(method = "getWaterBrightness", at = @At("RETURN"), cancellable = true)
    public void getWaterBrightness(CallbackInfoReturnable<Float> info) {
//        info.setReturnValue(0F);
    }

    @Inject(method = "isUsingSwimmingAnimation", at = @At("RETURN"), cancellable = true)
    private void isUsingSwimmingAnimation(CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(true);
    }
}
