package net.anvian.electricmace.util;

import net.anvian.anvianslib.util.RegistryUtil;
import net.anvian.electricmace.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModTags {
    public static class Enchantments {
        public static final TagKey<Enchantment> ELECTRICMACE_ENCHANTMENTS =
                RegistryUtil.tag(Registries.ENCHANTMENT, Constants.MOD_ID, "electricmace_enchantments");
    }
}
