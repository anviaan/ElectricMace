package net.anvian.electricmace;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class NeoForge {

    public NeoForge(IEventBus eventBus) {
        Constants.LOG.info("Hello from " + Constants.MOD_ID + " (Fabric)");
        Common.init();
    }
}