package net.kunmc.lab.nogravitymod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.util.ObfuscationUtil;

import java.lang.reflect.Field;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {
    @Shadow
    protected boolean eyesInWaterPlayer;

    @Inject(method = "updateEyesInWaterPlayer", at = @At("RETURN"), cancellable = true)
    protected void updateEyesInWaterPlayer(CallbackInfoReturnable<Boolean> info) {
        this.eyesInWaterPlayer = true;
        info.setReturnValue(true);
    }

    @Inject(method = "travel", at = @At(value = "HEAD"))
    public void travel(Vector3d travelVector, CallbackInfo info) throws NoSuchFieldException, IllegalAccessException {
        PlayerEntity instance = (PlayerEntity)(Object)this;
        Field isJumpingField;
        try {
            isJumpingField = LivingEntity.class.getDeclaredField("field_70703_bu");
        } catch (NoSuchFieldException ignored) {
            isJumpingField = LivingEntity.class.getDeclaredField("isJumping");
        }
        isJumpingField.setAccessible(true);
        boolean isJumping = isJumpingField.getBoolean(instance);
        if (instance.isSwimming() && !instance.isPassenger()) {
            double lookVecY = instance.getLookVec().y;
            double multiply = lookVecY < -0.2D ? 0.085D : 0.06D;
            if (lookVecY > 0.0D && !isJumping && instance.world.getBlockState(new BlockPos(instance.getPosX(), instance.getPosY() + 1.0D - 0.1D, instance.getPosZ())).getFluidState().isEmpty()) {
                Vector3d motion = instance.getMotion();
                instance.setMotion(motion.add(0, (lookVecY - motion.y) * multiply, 0));
            }
        }
    }
}
