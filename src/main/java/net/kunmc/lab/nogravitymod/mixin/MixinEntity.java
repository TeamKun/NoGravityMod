package net.kunmc.lab.nogravitymod.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Shadow public abstract boolean isInWater();

    @Shadow protected boolean eyesInWater;

    @Shadow public float fallDistance;

    @Shadow protected boolean inWater;

    @Inject(method = "isInWater", at = @At("RETURN"), cancellable = true)
    public void isInWater(CallbackInfoReturnable<Boolean> info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity instance = (PlayerEntity)(Object)this;
        info.setReturnValue(true);
    }

    @Inject(method = "canSwim", at = @At("RETURN"), cancellable = true)
    public void canSwim(CallbackInfoReturnable<Boolean> info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity instance = (PlayerEntity)(Object)this;
        info.setReturnValue(true);
    }

    @Inject(method = "updateSwimming", at = @At("RETURN"), cancellable = true)
    public void updateSwimming(CallbackInfo info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity instance = (PlayerEntity)(Object)this;
    }

    @Inject(method = "updateWaterState", at = @At("RETURN"), cancellable = true)
    void updateWaterState(CallbackInfo info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
    }

    @Inject(method = "isOffsetPositionInLiquid", at = @At("RETURN"), cancellable = true)
    public void isOffsetPositionInLiquid(double x, double y, double z, CallbackInfoReturnable<Boolean> info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity instance = (PlayerEntity)(Object)this;
        info.setReturnValue(false);
    }

    @Inject(method = "playSwimSound", at = @At("HEAD"), cancellable = true)
    protected void playSwimSound(float volume, CallbackInfo info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity instance = (PlayerEntity)(Object)this;
        if (!inWater) {
            info.cancel();
        }
    }
}
