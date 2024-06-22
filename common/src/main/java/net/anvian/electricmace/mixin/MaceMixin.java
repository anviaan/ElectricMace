package net.anvian.electricmace.mixin;

import net.anvian.electricmace.util.ModTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MaceItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(MaceItem.class)
public class MaceMixin {
    @Inject(method = "hurtEnemy", at = @At("HEAD"), cancellable = true)
    private void inject(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        if (attacker instanceof ServerPlayer && MaceItem.canSmashAttack(attacker) && EnchantmentHelper.hasTag(stack, ModTags.Enchantments.ELECTRIC_MACE)) {
            ServerLevel serverWorld = (ServerLevel) attacker.level();
            if (serverWorld.isThundering()) {
                AABB searchArea = new AABB(target.blockPosition()).inflate(5.0, 5.0, 5.0);
                List<LivingEntity> nearbyMobs = electricMace$getNearbyMobs(serverWorld, searchArea);
                if (!nearbyMobs.isEmpty()) {
                    for (LivingEntity mob : nearbyMobs) {
                        electricMace$damageAndSpawnParticles(attacker.level(), serverWorld, mob);
                        electricMace$spawnLightningBolt(serverWorld, mob);
                    }
                }
            }
        }
        cir.setReturnValue(true);
    }

    @Unique
    private List<LivingEntity> electricMace$getNearbyMobs(Level level, AABB searchArea) {
        return level.getEntitiesOfClass(LivingEntity.class, searchArea, entity -> !(entity instanceof Player));
    }

    @Unique
    private void electricMace$damageAndSpawnParticles(Level level, ServerLevel serverLevel, LivingEntity mob) {
        mob.hurt(level.damageSources().lightningBolt(), 3.0f);
        serverLevel.sendParticles(ParticleTypes.FLASH, mob.getX(), mob.getY(), mob.getZ(), 10, 0.5, 0.5, 0.5, 0.0);
    }

    @Unique
    private void electricMace$spawnLightningBolt(ServerLevel serverLevel, LivingEntity mob) {
        LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(serverLevel);
        if (lightningBolt != null) {
            lightningBolt.moveTo(mob.getX(), mob.getY(), mob.getZ());
            serverLevel.addFreshEntity(lightningBolt);
        }
    }
}
