package lv.cebbys.mcmods.mvl.exception;

public class VersionLocationException extends RuntimeException {
    public static VersionLocationException noneFound() {
        return new VersionLocationException(
                "Minecraft version location failure - No minecraft version was loaded successfully"
        );
    }

    public static VersionLocationException severalFound(int count) {
        return new VersionLocationException(String.format(
                "Minecraft version location failure - %d different minecraft versions loaded", count
        ));
    }
    private VersionLocationException(String message) {
        super(message);
    }
}
