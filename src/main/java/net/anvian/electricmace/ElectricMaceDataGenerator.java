package net.anvian.electricmace;

import net.anvian.electricmace.datagen.ModEnchantmentProvider;
import net.anvian.electricmace.datagen.ModItemProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ElectricMaceDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModItemProvider::new);
        pack.addProvider(ModEnchantmentProvider::new);
    }
}
