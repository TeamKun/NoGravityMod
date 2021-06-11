package net.kunmc.lab.nogravitymod.mixin;

import net.minecraft.fluid.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FluidState.class)
public abstract class MixinFluidState {
    @Shadow public abstract Fluid getFluid();

    @Shadow public abstract boolean isEmpty();

    @Inject(method = "isTagged", at = @At("RETURN"), cancellable = true)
    public void isTagged(ITag<Fluid> tagIn, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(info.getReturnValue() || getFluid() instanceof EmptyFluid && tagIn.equals(FluidTags.WATER));
    }
}
