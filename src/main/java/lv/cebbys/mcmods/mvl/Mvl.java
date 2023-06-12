package lv.cebbys.mcmods.mvl;

import lv.cebbys.mcmods.mvl.api.MinecraftVersionLoader;
import lv.cebbys.mcmods.mvl.dto.MinecraftVersion;
import net.fabricmc.api.ModInitializer;

public class Mvl implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        MinecraftVersion version = MinecraftVersionLoader.getMinecraftVersion();
    }
}
