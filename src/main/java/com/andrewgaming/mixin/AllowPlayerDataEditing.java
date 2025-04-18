package com.andrewgaming.mixin;

import net.minecraft.command.EntityDataObject;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(EntityDataObject.class)
public class AllowPlayerDataEditing {


	@ModifyConstant(method = "setNbt", constant = @Constant(ordinal = 0, classValue = PlayerEntity.class))
	private static boolean playerCheckBypass(Object obj, Class objclass){
		return false;
	}

}