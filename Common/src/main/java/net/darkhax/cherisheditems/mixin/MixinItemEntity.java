package net.darkhax.cherisheditems.mixin;

import net.darkhax.cherisheditems.CherishedItemsCommon;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class MixinItemEntity {

//    @Inject(method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V", at = @At("RETURN"))
//    private void init(EntityType<? extends ItemEntity> type, Level level, CallbackInfo cbi) {
//        CherishedItemsCommon.onItemEntityCreated((ItemEntity) (Object) this);
//    }

    @Inject(method = "<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V", at = @At("RETURN"))
    private void init(Level level, double posX, double posY, double posZ, ItemStack stack, CallbackInfo cbi) {
        CherishedItemsCommon.onItemEntityCreated((ItemEntity) (Object) this);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;DDD)V", at = @At("RETURN"))
    private void init(Level level, double posX, double posY, double posZ, ItemStack stack, double velX, double velY, double velZ, CallbackInfo cbi) {
        CherishedItemsCommon.onItemEntityCreated((ItemEntity) (Object) this);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/entity/item/ItemEntity;)V", at = @At("RETURN"))
    private void init(ItemEntity original, CallbackInfo cbi) {
        CherishedItemsCommon.onItemEntityCreated((ItemEntity) (Object) this);
    }
}