package net.kunmc.lab.nogravitymod.mixin;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Inject(method = "isInWater", at = @At("RETURN"), cancellable = true)
    public void isInWater(CallbackInfoReturnable<Boolean> info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity)(Object)this;
        info.setReturnValue(true);
    }

    @Inject(method = "canSwim", at = @At("RETURN"), cancellable = true)
    public void canSwim(CallbackInfoReturnable<Boolean> info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity playerEntity = (PlayerEntity)(Object)this;
        info.setReturnValue(true);
    }

    @Inject(method = "updateSwimming", at = @At("RETURN"), cancellable = true)
    public void updateSwimming(CallbackInfo info) {
        if (!((Object)this instanceof ClientPlayerEntity)) {
            return;
        }
        ClientPlayerEntity playerEntity = (ClientPlayerEntity)(Object)this;
    }
}
