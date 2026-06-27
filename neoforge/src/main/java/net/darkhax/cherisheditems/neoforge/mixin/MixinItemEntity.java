package net.darkhax.cherisheditems.neoforge.mixin;

import net.darkhax.cherisheditems.common.CherishedItems;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class MixinItemEntity {

    @Shadow
    public int lifespan;

    @Inject(method = "setItem", at = @At("RETURN"))
    public void setItem(ItemStack itemStack, CallbackInfo ci) {
        if (this.lifespan == 6000) {
            this.lifespan = CherishedItems.getModifiedDespawnTime(itemStack, this.lifespan);
        }
    }
}