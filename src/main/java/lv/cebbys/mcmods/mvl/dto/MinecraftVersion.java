package lv.cebbys.mcmods.mvl.dto;

import lombok.Data;

@Data
public class MinecraftVersion {
    private final int release;
    private final int major;
    private final int minor;
    private final String versionString;

    public MinecraftVersion(int release, int major, int minor) {
        this.release = release;
        this.major = major;
        this.minor = minor;
        if (minor == 0) {
            versionString = String.format("%d.%d", release, major);
        } else {
            versionString = String.format("%d.%d.%d", release, major, minor);
        }
    }

    @Override
    public String toString() {
        return getVersionString();
    }
}
