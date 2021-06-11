package net.kunmc.lab.nogravitymod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(method = "calculateFallDamage", at = @At("RETURN"), cancellable = true)
    protected void calculateFallDamage(float distance, float damageMultiplier, CallbackInfoReturnable<Integer> info) {
        if (!((Object)this instanceof PlayerEntity)) {
            return;
        }
        info.setReturnValue(0);
    }
}
