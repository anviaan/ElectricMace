package net.anvian.electricmace;

import net.anvian.anvianslib.util.LibUtil;

public class Common {
    public static void init() {
        LibUtil.setupTelemetry(Constants.MOD_ID, "1.8.3");
    }
}