package com.andrewgaming;

import net.minecraft.entity.damage.DamageSource;

public interface IEntityDamageAccessor {
    DamageSource getLastDamageSourceThisTick();
    boolean hasTakenDamageThisTick();
    void resetDamageFlags();
}