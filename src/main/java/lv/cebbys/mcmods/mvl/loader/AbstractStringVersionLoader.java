package lv.cebbys.mcmods.mvl.loader;

import lv.cebbys.mcmods.mvl.dto.MinecraftVersion;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public abstract class AbstractStringVersionLoader implements VersionLoader {

    private MinecraftVersion version;

    protected abstract String getVersionString() throws Throwable;

    private MinecraftVersion getVersionFromString(String versionString) {
        var parts = Arrays.stream(versionString.split("\\."))
                .map((var part) -> Integer.parseInt(part, 10))
                .collect(Collectors.toCollection(LinkedList::new));
        if (parts.size() == 2) {
            parts.add(0);
        }
        if (parts.size() != 3) {
            return null;
        }
        return new MinecraftVersion(parts.get(0), parts.get(1), parts.get(2));
    }

    @Override
    public @Nullable MinecraftVersion getVersion() {
        try {
            if (version == null) {
                var string = getVersionString();
                version = getVersionFromString(string);
            }
            return version;
        } catch (Throwable t) {
            return null;
        }
    }
}
