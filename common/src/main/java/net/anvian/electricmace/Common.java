package net.anvian.electricmace;

import net.anvian.electricmace.platform.Services;

public class Common {
    public static void init() {
        if (Services.PLATFORM.isModLoaded(Constants.MOD_ID)) {
            Constants.LOG.info("Hello from " + Constants.MOD_NAME);
        }
    }
}