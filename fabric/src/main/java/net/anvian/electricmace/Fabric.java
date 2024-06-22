package net.anvian.electricmace;

import net.fabricmc.api.ModInitializer;

public class Fabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Fabric)");
        Common.init();
    }
}
