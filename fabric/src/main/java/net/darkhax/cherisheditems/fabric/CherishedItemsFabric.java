package net.darkhax.cherisheditems.fabric;

import net.darkhax.cherisheditems.common.CherishedItems;
import net.fabricmc.api.ModInitializer;

public class CherishedItemsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        CherishedItems.init();
    }
}