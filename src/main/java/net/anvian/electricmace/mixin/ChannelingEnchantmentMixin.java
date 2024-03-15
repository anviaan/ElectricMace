package net.anvian.electricmace.mixin;

import net.minecraft.enchantment.ChannelingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ChannelingEnchantment.class)
public class ChannelingEnchantmentMixin extends Enchantment {
    protected ChannelingEnchantmentMixin(Rarity rarity, TagKey<Item> applicableItems, EquipmentSlot[] slotTypes) {
        super(rarity, applicableItems, slotTypes);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isOf(Items.MACE) || super.isAcceptableItem(stack);
    }
}
