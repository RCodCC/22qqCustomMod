package me.twotwoqq.cmod.mixin;

import me.twotwoqq.cmod.Main;
import me.twotwoqq.cmod.utils.EntityUtils;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/LivingEntityRenderer;renderLabelIfPresent(Lnet/minecraft/entity/Entity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", ordinal = 1), method = "renderLabelIfPresent(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/text/Text;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V")
    public void render(Args args) {
        Entity entity = args.get(0);
        MutableText text = args.get(1);

        if (entity instanceof PlayerEntity player) {
            Main.playerPops.putIfAbsent(player.getUuid(), 0);

            if (Main.playerpopsToggle) {
                text.append(Text.of(" §b| " + (Main.playerPops.containsKey(player.getUuid()) ? Main.playerPops.get(player.getUuid()) + " pops" : "0" + " pops")));
            }

            if (Main.playerpingToggle) {
                text.append(" §b| " + EntityUtils.getPing(player) + " ms");
            }

            args.set(1, text);
        }
    }
}