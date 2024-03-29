package net.anvian.electricmace.mixin;

import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.screen.*;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilMenuMixin extends ForgingScreenHandler {
    @Shadow
    @Final
    private Property levelCost;

    @Shadow
    public abstract void updateResult();

    public AnvilMenuMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Inject(method = "updateResult", at = @At("HEAD"), cancellable = true)
    private void inject(CallbackInfo info) {
        ItemStack itemStack1 = this.input.getStack(0).copy();
        ItemStack itemStack2 = this.input.getStack(1);

        if (itemStack1.getItem() instanceof MaceItem && itemStack2.getItem() instanceof EnchantedBookItem) {
            ItemEnchantmentsComponent enchantments = EnchantmentHelper.getEnchantments(itemStack2);

            enchantments.getEnchantments().forEach((enchantment) -> {
                if (enchantment.value() == Enchantments.CHANNELING) {
                    itemStack1.addEnchantment(Enchantments.CHANNELING, 1);
                    this.levelCost.set(10);
                    this.output.setStack(0, itemStack1);

                    sendContentUpdates();
                    info.cancel();
                }
            });
        }
    }
}
