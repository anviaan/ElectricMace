package net.anvian.electricmace.util;

import net.anvian.electricmace.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModTags {
    public static class Enchantments {
        public static final TagKey<Enchantment> ELECTRIC_MACE = createTag("electric_mace");

        private static TagKey<Enchantment> createTag(String name) {
            return TagKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
        }
    }
}