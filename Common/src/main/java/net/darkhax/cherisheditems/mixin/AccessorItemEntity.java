package net.darkhax.cherisheditems.mixin;

import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemEntity.class)
public interface AccessorItemEntity {

    @Accessor("age")
    void cherisheditems$setDespawnTime(int newTime);
}