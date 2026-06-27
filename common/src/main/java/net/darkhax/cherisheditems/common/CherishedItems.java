package net.darkhax.cherisheditems.common;

import net.darkhax.pricklemc.common.api.config.ConfigManager;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CherishedItems {

    public static final String MOD_ID = "cherisheditems";
    public static final String MOD_NAME = "Cherished Items";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final TagKey<Item> TAG_EXTENDED_DESPAWN_TIME = TagKey.create(Registries.ITEM, id("extended_despawn_time"));
    public static final TagKey<Item> TAG_SHORTENED_DESPAWN_TIME = TagKey.create(Registries.ITEM, id("shortened_despawn_time"));

    public static final Config CONFIG = ConfigManager.load(MOD_ID, new Config());

    public static void init() {
        if (CONFIG.default_time.enabled) {
            LOG.info("Default item despawn set to {}", CONFIG.default_time.despawn_time);
        }
    }

    public static int getModifiedDespawnTime(ItemStack stack, int time) {
        if (CONFIG.default_time.enabled) {
            time = CONFIG.default_time.despawn_time;
        }
        if (CONFIG.extended_tag.enabled && stack.is(TAG_EXTENDED_DESPAWN_TIME)) {
            time = CONFIG.extended_tag.despawn_time;
        }
        if (CONFIG.shortened_tag.enabled && stack.is(TAG_SHORTENED_DESPAWN_TIME)) {
            time = CONFIG.shortened_tag.despawn_time;
        }
        if (CONFIG.renamed_items.enabled && stack.has(DataComponents.CUSTOM_NAME)) {
            time = CONFIG.renamed_items.despawn_time;
        }
        if (CONFIG.enchanted_items.enabled && stack.isEnchanted()) {
            time = CONFIG.enchanted_items.despawn_time;
        }
        return time;
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}