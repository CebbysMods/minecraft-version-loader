package lv.cebbys.mcmods.mvl.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DetectedVersionLoaderConfig {
    private final String detectedVersionClass;
    private final String tryDetectVersionMethod;
    private final String getVersionMethod;
}
