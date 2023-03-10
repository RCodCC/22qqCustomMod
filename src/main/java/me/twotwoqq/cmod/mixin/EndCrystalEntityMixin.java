package me.twotwoqq.cmod.mixin;

import me.twotwoqq.cmod.Main;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.EndCrystalEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndCrystalEntity.class)
public abstract class EndCrystalEntityMixin {

    @Shadow
    public abstract void kill();

    @Inject(at = @At("HEAD"), method = "damage")
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (Main.coToggle) {
            this.kill();
        }
    }
}
