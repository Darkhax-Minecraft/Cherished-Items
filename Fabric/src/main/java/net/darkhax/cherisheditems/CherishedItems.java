package net.darkhax.cherisheditems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class CherishedItems implements ModInitializer {

    @Override
    public void onInitialize() {

        CherishedItemsCommon.init(FabricLoader.getInstance().getConfigDir().resolve(Constants.MOD_ID + ".json").toFile());
    }
}