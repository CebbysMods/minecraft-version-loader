package lv.cebbys.mcmods.mvl.api;

import lombok.extern.slf4j.Slf4j;
import lv.cebbys.mcmods.mvl.dto.DetectedVersionLoaderConfig;
import lv.cebbys.mcmods.mvl.dto.MinecraftVersion;
import lv.cebbys.mcmods.mvl.exception.VersionLocationException;
import lv.cebbys.mcmods.mvl.loader.DetectedVersionClassVersionLoader;
import lv.cebbys.mcmods.mvl.loader.VersionLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class MinecraftVersionLoader {
    private static final List<DetectedVersionLoaderConfig> DETECTED_VERSION_LOADER_CONFIGS;
    private static final List<VersionLoader> VERSION_LOADERS;

    /**
     * Iterates trough different minecraft version loaders that try to load minecraft version
     * following their implementation. Only one unique minecraft version can be loaded, in
     * case if different versions are loaded an exception is thrown. In case if no version
     * is loaded then exception is thrown too.
     *
     * @return Identified minecraft version
     */
    public static MinecraftVersion getMinecraftVersion() {
        var versions = VERSION_LOADERS.stream()
                .map(VersionLoader::getVersion)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet())
                .stream().toList();
        if (versions.size() > 1) {
            throw VersionLocationException.severalFound(versions.size());
        } else if (versions.size() < 1) {
            throw VersionLocationException.noneFound();
        }
        return versions.get(0);
    }

    private static void registerDetectedVersionConfigs(Registry<DetectedVersionLoaderConfig> registry) {
        registry.register(new DetectedVersionLoaderConfig(
                "net.minecraft.DetectedVersion",
                "tryDetectVersion",
                "getName"
        ));
        registry.register(new DetectedVersionLoaderConfig(
                "net.minecraft.class_3797",
                "method_16672",
                "getName"
        ));
        registry.register(new DetectedVersionLoaderConfig(
                "net.minecraft.class_3797",
                "method_16672",
                "method_48019"
        ));
    }

    private static void registerVersionLoaders(Registry<VersionLoader> registry) {
        for (var config : DETECTED_VERSION_LOADER_CONFIGS) {
            registry.register(new DetectedVersionClassVersionLoader(config));
        }
    }

    @FunctionalInterface
    private interface Registry<T> {
        void register(T loader);
    }

    static {
        var detectedVersionConfigRegistry = new ArrayList<DetectedVersionLoaderConfig>();
        registerDetectedVersionConfigs(detectedVersionConfigRegistry::add);
        DETECTED_VERSION_LOADER_CONFIGS = List.copyOf(detectedVersionConfigRegistry);

        var registry = new ArrayList<VersionLoader>();
        registerVersionLoaders(registry::add);
        VERSION_LOADERS = List.copyOf(registry);
    }
}
