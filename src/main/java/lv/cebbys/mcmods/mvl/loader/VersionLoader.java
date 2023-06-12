package lv.cebbys.mcmods.mvl.loader;

import lv.cebbys.mcmods.mvl.dto.MinecraftVersion;
import org.jetbrains.annotations.Nullable;

public interface VersionLoader {
    @Nullable
    MinecraftVersion getVersion();
}
