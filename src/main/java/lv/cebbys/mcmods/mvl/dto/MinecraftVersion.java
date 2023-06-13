package lv.cebbys.mcmods.mvl.dto;

import lombok.Data;

@Data
public class MinecraftVersion {
    private final int major;
    private final int minor;
    private final int patch;
    private final String versionString;

    public MinecraftVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        if (patch == 0) {
            versionString = String.format("%d.%d", major, minor);
        } else {
            versionString = String.format("%d.%d.%d", major, minor, patch);
        }
    }

    @Override
    public String toString() {
        return getVersionString();
    }
}
