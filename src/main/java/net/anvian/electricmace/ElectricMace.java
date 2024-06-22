package net.anvian.electricmace;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElectricMace implements ModInitializer {
    public static final String MODID = "electricmace";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello from Electric Mace Mod!");
    }
}