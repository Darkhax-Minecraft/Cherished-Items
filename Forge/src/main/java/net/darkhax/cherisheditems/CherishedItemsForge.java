package net.darkhax.cherisheditems;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Constants.MOD_ID)
public class CherishedItemsForge {

    public CherishedItemsForge() {

        CherishedItemsCommon.init(FMLPaths.CONFIGDIR.get().resolve(Constants.MOD_ID + ".json").toFile());
    }
}