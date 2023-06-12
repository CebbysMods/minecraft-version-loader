package lv.cebbys.mcmods.mvl.loader;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lv.cebbys.mcmods.mvl.dto.DetectedVersionLoaderConfig;

@RequiredArgsConstructor
@Getter(AccessLevel.PROTECTED)
public class DetectedVersionClassVersionLoader extends AbstractStringVersionLoader {
    private final String detectedVersionClass;
    private final String tryDetectVersionMethod;
    private final String versionMethod;

    public DetectedVersionClassVersionLoader(DetectedVersionLoaderConfig config) {
        this(config.getDetectedVersionClass(), config.getTryDetectVersionMethod(), config.getGetVersionMethod());
    }

    @Override
    protected String getVersionString() throws Throwable {
        var classLoader = this.getClass().getClassLoader();
        var detectVersionClass = classLoader.loadClass(getDetectedVersionClass());
        var tryDetectVersionMethod = detectVersionClass.getMethod(getTryDetectVersionMethod());
        tryDetectVersionMethod.setAccessible(true);
        var detectedVersion = tryDetectVersionMethod.invoke(null);
        var getNameMethod = detectedVersion.getClass().getDeclaredMethod(getVersionMethod());
        getNameMethod.setAccessible(true);
        return getNameMethod.invoke(detectedVersion).toString();
    }
}
