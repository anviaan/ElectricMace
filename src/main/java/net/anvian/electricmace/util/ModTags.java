package net.anvian.electricmace.util;

import net.anvian.electricmace.ElectricMace;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Enchantments {
        public static final TagKey<Enchantment> ELECTRIC_MACE = createTag("electric_mace");

        private static TagKey<Enchantment> createTag(String name) {
            return TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(ElectricMace.MODID, name));
        }
    }
}
