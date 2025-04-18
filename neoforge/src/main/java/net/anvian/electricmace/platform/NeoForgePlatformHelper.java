package net.anvian.electricmace.platform;

import net.anvian.electricmace.platform.services.IPlatformHelper;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }


    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public Path getGameConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}