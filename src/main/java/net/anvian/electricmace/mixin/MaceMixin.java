package net.anvian.electricmace.mixin;

import net.anvian.electricmace.util.ModTags;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(MaceItem.class)
public class MaceMixin {
    @Inject(method = "postHit", at = @At("HEAD"), cancellable = true)
    private void inject(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        if (attacker instanceof ServerPlayerEntity && MaceItem.shouldDealAdditionalDamage(attacker) && EnchantmentHelper.hasAnyEnchantmentsIn(stack, ModTags.Enchantments.ELECTRIC_MACE)) {
            ServerWorld serverWorld = (ServerWorld) attacker.getWorld();
            if (serverWorld.isThundering()) {
                Box searchArea = new Box(target.getBlockPos()).expand(5.0, 5.0, 5.0);
                List<LivingEntity> nearbyMobs = getNearbyMobs(serverWorld, searchArea);
                if (!nearbyMobs.isEmpty()) {
                    for (LivingEntity mob : nearbyMobs) {
                        damageAndSpawnParticles(attacker.getWorld(), serverWorld, mob);
                        spawnLightningBolt(serverWorld, mob);
                    }
                }
            }
        }
        cir.setReturnValue(true);
    }

    @Unique
    private List<LivingEntity> getNearbyMobs(World world, Box searchArea) {
        return world.getEntitiesByClass(LivingEntity.class, searchArea, entity -> !(entity instanceof PlayerEntity));
    }

    @Unique
    private void damageAndSpawnParticles(World world, ServerWorld serverWorld, LivingEntity mob) {
        mob.damage(world.getDamageSources().lightningBolt(), 3.0f);
        serverWorld.spawnParticles(ParticleTypes.FLASH, mob.getX(), mob.getY(), mob.getZ(), 10, 0.5, 0.5, 0.5, 0.0);
    }

    @Unique
    private void spawnLightningBolt(ServerWorld serverWorld, LivingEntity mob) {
        LightningEntity lightningBolt = EntityType.LIGHTNING_BOLT.create(serverWorld);
        if (lightningBolt != null) {
            lightningBolt.refreshPositionAfterTeleport(mob.getX(), mob.getY(), mob.getZ());
            serverWorld.spawnEntity(lightningBolt);
        }
    }
}
