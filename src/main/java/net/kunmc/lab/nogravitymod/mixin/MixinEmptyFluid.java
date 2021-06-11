package net.kunmc.lab.nogravitymod.mixin;

import net.minecraft.fluid.EmptyFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tags.ITag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EmptyFluid.class)
public class MixinEmptyFluid {
    @Inject(method = "getActualHeight", at = @At("RETURN"), cancellable = true)
    public void getActualHeight(FluidState fluidState, IBlockReader blockReader, BlockPos pos, CallbackInfoReturnable<Float> info) {
//        info.setReturnValue(1F);
    }

    @Inject(method = "isEmpty", at = @At("RETURN"), cancellable = true)
    protected void isEmpty(CallbackInfoReturnable<Boolean> info) {
//        info.setReturnValue(false);
    }
}
