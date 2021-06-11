package net.kunmc.lab.nogravitymod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow protected boolean inWater;

    @Inject(method = "isInWater", at = @At("RETURN"), cancellable = true)
    public void isInWater(CallbackInfoReturnable<Boolean> info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        info.setReturnValue(true);
    }

    @Inject(method = "canSwim", at = @At("RETURN"), cancellable = true)
    public void canSwim(CallbackInfoReturnable<Boolean> info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        info.setReturnValue(true);
    }

    @Inject(method = "isOffsetPositionInLiquid", at = @At("RETURN"), cancellable = true)
    public void isOffsetPositionInLiquid(double x, double y, double z, CallbackInfoReturnable<Boolean> info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        info.setReturnValue(false);
    }

    @Inject(method = "playSwimSound", at = @At("HEAD"), cancellable = true)
    protected void playSwimSound(float volume, CallbackInfo info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        if (!inWater) {
            info.cancel();
        }
    }
}
