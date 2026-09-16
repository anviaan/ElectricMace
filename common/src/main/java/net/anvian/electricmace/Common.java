package net.anvian.electricmace;

import net.anvian.anvianslib.util.LibUtil;

public class Common {
    public static void init() {
        try {
            LibUtil.setupTelemetry(Constants.MOD_ID, "1.8.3");
        } catch (LinkageError error) {
            Constants.LOG.warn("Skipping telemetry because Anvians Lib is incompatible with this Minecraft version");
        }
    }
}