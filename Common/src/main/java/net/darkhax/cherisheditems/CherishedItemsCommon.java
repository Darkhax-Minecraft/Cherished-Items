package net.darkhax.cherisheditems;

import net.darkhax.cherisheditems.mixin.AccessorItemEntity;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.io.File;

public class CherishedItemsCommon {

    public static final TagKey<Item> TAG_EXTENDED_DESPAWN_TIME = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Constants.MOD_ID, "extended_despawn_time"));
    public static final TagKey<Item> TAG_SHORTENED_DESPAWN_TIME = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(Constants.MOD_ID, "shortened_despawn_time"));

    public static Config config;

    public static void init(File configFile) {

        config = Config.load(configFile);
    }

    public static void onItemEntityCreated(ItemEntity entity) {

        if (config != null && entity.getAge() == 0 && entity instanceof AccessorItemEntity accessor) {

            final ItemStack stack = entity.getItem();

            if (config.defaultTime.enabled) {
                accessor.cherisheditems$setDespawnTime(config.defaultTime.despawnTime);
            }

            if (config.extendedTag.enabled && stack.is(TAG_EXTENDED_DESPAWN_TIME)) {
                accessor.cherisheditems$setDespawnTime(config.extendedTag.despawnTime);
            }

            if (config.shortenedTag.enabled && stack.is(TAG_SHORTENED_DESPAWN_TIME)) {
                accessor.cherisheditems$setDespawnTime(config.shortenedTag.despawnTime);
            }

            if (config.renamedItems.enabled && stack.hasCustomHoverName()) {
                accessor.cherisheditems$setDespawnTime(entity.getAge() - config.renamedItems.despawnTime);
            }

            if (config.enchantedItems.enabled && stack.isEnchanted()) {
                accessor.cherisheditems$setDespawnTime(entity.getAge() - config.enchantedItems.despawnTime);
            }
        }
    }

    private static void setAgeWithRestrictions(AccessorItemEntity item, int time) {

        item.cherisheditems$setDespawnTime(Math.max(-32767, time));
    }
}
