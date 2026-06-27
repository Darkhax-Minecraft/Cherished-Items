package net.darkhax.cherisheditems.fabric.mixin;

import net.darkhax.cherisheditems.common.CherishedItems;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ItemEntity.class)
public class MixinItemEntity {

    @ModifyConstant(method = "tick", constant = @Constant(intValue = 6000))
    private int tick(int constant) {
        return constant == 6000 ? CherishedItems.getModifiedDespawnTime(this.getItem(), constant) : constant;
    }

    @Shadow
    public ItemStack getItem() {
        return null;
    }
}