package net.anvian.electricmace;

import net.anvian.anvianslib.config.TelemetryConfigManager;
import net.anvian.anvianslib.util.LibUtil;
import net.anvian.electricmace.platform.Services;

public class Common {
    public static void init() {
        LibUtil.generateConfigPath(Constants.MOD_ID, Services.PLATFORM.getGameConfigDirectory());

        TelemetryConfigManager.initialize(Services.PLATFORM.getGameConfigDirectory().resolve(Constants.MOD_ID).toFile());
        if (TelemetryConfigManager.getConfig().enableTelemetry) {
            TelemetryConfigManager.sendTelemetryData(
                    Constants.MOD_ID,
                    "1.7",
                    LibUtil.getMinecraftVersion(),
                    Services.PLATFORM.getPlatformName(),
                    !Services.PLATFORM.isDevelopmentEnvironment()
            );
        }
    }
}