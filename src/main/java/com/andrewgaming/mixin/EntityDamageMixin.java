package com.andrewgaming.mixin;

import com.andrewgaming.IEntityDamageAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class EntityDamageMixin implements IEntityDamageAccessor {
    @Unique
    private DamageSource lastDamageSourceThisTick = null;

    @Unique
    private boolean hasTakenDamageThisTick = false;

    @Shadow
    public abstract boolean isAlive();

    @Inject(method = "damage" ,at = @At(value = "HEAD"))
    private void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (isAlive()) { // Only update if the entity is alive
            this.lastDamageSourceThisTick = source;
            this.hasTakenDamageThisTick = true;
        }
    }

    @Unique
    public DamageSource getLastDamageSourceThisTick() {
        return this.lastDamageSourceThisTick;
    }

    @Unique
    public boolean hasTakenDamageThisTick() {
        return this.hasTakenDamageThisTick;
    }

    @Unique
    public void resetDamageFlags() {
        this.lastDamageSourceThisTick = null;
        this.hasTakenDamageThisTick = false;
    }
}