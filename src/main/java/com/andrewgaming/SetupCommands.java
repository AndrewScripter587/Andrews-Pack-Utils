package com.andrewgaming;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.command.argument.*;


import static net.minecraft.server.command.CommandManager.*;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.entity.Entity;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.Optional;

public class SetupCommands{
    public static void Init() {
        Logger LOGGER = AndrewsPackUtilities.LOGGER;
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("aputils")
                .then(literal("calc")
                        .then(literal("add")
                                .then(argument("value1", DoubleArgumentType.doubleArg())
                                        .then(argument("value2", DoubleArgumentType.doubleArg())
                                                .executes(context -> {
                                                    // For versions below 1.19, replace "Text.literal" with "new LiteralText".
                                                    // For versions below 1.20, remode "() ->" directly.
                                                    final double value1 = DoubleArgumentType.getDouble(context, "value1");
                                                    final double value2 = DoubleArgumentType.getDouble(context, "value2");
                                                    final double result = value1 + value2;
                                                    context.getSource().sendFeedback(() -> Text.literal(value1 + " + " + value2 + " = " + result), false);

                                                    return (int) result;
                                                })
                                        )
                                ))
                        .then(literal("sub")
                                .then(argument("value1", DoubleArgumentType.doubleArg())
                                        .then(argument("value2", DoubleArgumentType.doubleArg())
                                                .executes(context -> {
                                                    // For versions below 1.19, replace "Text.literal" with "new LiteralText".
                                                    // For versions below 1.20, remode "() ->" directly.
                                                    final double value1 = DoubleArgumentType.getDouble(context, "value1");
                                                    final double value2 = DoubleArgumentType.getDouble(context, "value2");
                                                    final double result = value1 + value2;
                                                    context.getSource().sendFeedback(() -> Text.literal(String.valueOf(value1) + " - " + String.valueOf(value2) + " = " + result), false);

                                                    return (int) result;
                                                })
                                        )
                                )
                        )
                        .then(literal("mul")
                                .then(argument("value1", DoubleArgumentType.doubleArg())
                                        .then(argument("value2", DoubleArgumentType.doubleArg())
                                                .executes(context -> {
                                                    // For versions below 1.19, replace "Text.literal" with "new LiteralText".
                                                    // For versions below 1.20, remode "() ->" directly.
                                                    final double value1 = DoubleArgumentType.getDouble(context, "value1");
                                                    final double value2 = DoubleArgumentType.getDouble(context, "value2");
                                                    final double result = value1 * value2;
                                                    context.getSource().sendFeedback(() -> Text.literal(String.valueOf(value1) + " * " + String.valueOf(value2) + " = " + result), false);

                                                    return (int) result;
                                                })
                                        )
                                )
                        )
                        .then(literal("div")
                                .then(argument("value1", DoubleArgumentType.doubleArg())
                                        .then(argument("value2", DoubleArgumentType.doubleArg())
                                                .executes(context -> {
                                                    // For versions below 1.19, replace "Text.literal" with "new LiteralText".
                                                    // For versions below 1.20, remode "() ->" directly.
                                                    final double value1 = DoubleArgumentType.getDouble(context, "value1");
                                                    final double value2 = DoubleArgumentType.getDouble(context, "value2");
                                                    final double result = value1 / value2;
                                                    context.getSource().sendFeedback(() -> Text.literal(String.valueOf(value1) + " / " + String.valueOf(value2) + " = " + result), false);

                                                    return (int) result;
                                                })
                                        )
                                )
                        )
                        .then(literal("power")
                                .then(argument("value1", DoubleArgumentType.doubleArg())
                                        .then(argument("value2", DoubleArgumentType.doubleArg())
                                                .executes(context -> {
                                                    // For versions below 1.19, replace "Text.literal" with "new LiteralText".
                                                    // For versions below 1.20, remode "() ->" directly.
                                                    final double value1 = DoubleArgumentType.getDouble(context, "value1");
                                                    final double value2 = DoubleArgumentType.getDouble(context, "value2");
                                                    final double result = Math.pow((double) value1, value2);
                                                    context.getSource().sendFeedback(() -> Text.literal(value1 + " ^ " + value2 + " = " + result), false);

                                                    return (int) result;
                                                })
                                        )
                                )
                        )
                        .then(literal("sqrt")
                                .then(argument("value1", DoubleArgumentType.doubleArg())
                                        .executes(context -> {
                                            // For versions below 1.19, replace "Text.literal" with "new LiteralText".
                                            // For versions below 1.20, remode "() ->" directly.
                                            final double value1 = DoubleArgumentType.getDouble(context, "value1");
                                            final double result = Math.sqrt(value1);
                                            context.getSource().sendFeedback(() -> Text.literal("Sqrt of " + value1 + " = " + result), false);

                                            return (int) result;
                                        })
                                )
                        )


                )
                .then(literal("heartbeat")
                        .executes(context -> {
                            context.getSource().sendFeedback(() -> Text.literal("This subcommand exists for datapacks to detect this mod being installed. This subcommand does nothing else other than return 1."), false);
                            return 1;
                        })
                )
                .then(literal("loader")
                        .executes(context -> {
                            context.getSource().sendFeedback(() -> Text.literal("This subcommand exists for datapacks to detect which loader version of the mod is installed. This subcommand does nothing else other than return 1 on neoforge, and 0 on fabric."), false);
                            return 0;
                        })
                )
                .then(literal("velocity")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(argument("entities",EntityArgumentType.entities())
                                .then(argument("Vector",Vec3ArgumentType.vec3(false))
                                        .executes(context -> {
                                            try {
                                                Vec3d velocity = Vec3ArgumentType.getVec3(context, "Vector");
                                                Collection<? extends Entity> entities = EntityArgumentType.getEntities(context, "entities");
                                                for (Entity entityIndex : entities) {
                                                    entityIndex.setVelocity(velocity);
                                                    entityIndex.velocityModified = true;
                                                    if (entityIndex instanceof ServerPlayerEntity) {
                                                        ((ServerPlayerEntity) entityIndex).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(entityIndex));
                                                    }
                                                    // Don't feel like setting up an instance of LOGGER.
                                                    System.out.println("The new velocity is " + entityIndex.getVelocity().toString());
                                                }
                                            } catch (Throwable e) {
                                                context.getSource().sendFeedback(() -> Text.literal("An error occurred: " + e), false);
                                                return -1;
                                            }
                                            return 1;
                                        })
                                        .then(literal("add")
                                                .executes(context -> {
                                                    try {
                                                        Vec3d velocity = Vec3ArgumentType.getVec3(context, "Vector");
                                                        Collection<? extends Entity> entities = EntityArgumentType.getEntities(context, "entities");
                                                        for (Entity entityIndex : entities) {
                                                            
                                                            entityIndex.addVelocity(velocity);
                                                            entityIndex.velocityModified = true;
                                                            entityIndex.velocityDirty = true;
                                                            if (entityIndex instanceof ServerPlayerEntity) {
                                                                ((ServerPlayerEntity) entityIndex).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(entityIndex));
                                                            }

                                                            // Don't feel like setting up an instance of LOGGER.
                                                            System.out.println("The new velocity is " + entityIndex.getVelocity().toString());
                                                        }
                                                    } catch (Throwable e) {
                                                        context.getSource().sendFeedback(() -> Text.literal("An error occurred: " + e), false);
                                                        return -1;
                                                    }
                                                    return 1;
                                                })
                                        )
                                        .then(literal("set")
                                                .executes(context -> {
                                                    try {
                                                        Vec3d velocity = Vec3ArgumentType.getVec3(context, "Vector");
                                                        Collection<? extends Entity> entities = EntityArgumentType.getEntities(context, "entities");
                                                        for (Entity entityIndex : entities) {
                                                            entityIndex.setVelocity(velocity);
                                                            entityIndex.velocityModified = true;
                                                            if (entityIndex instanceof ServerPlayerEntity) {
                                                                ((ServerPlayerEntity) entityIndex).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(entityIndex));
                                                            }
                                                            // Don't feel like setting up an instance of LOGGER.
                                                            AndrewsPackUtilities.LOGGER.info("The new velocity is " + entityIndex.getVelocity().toString());
                                                        }
                                                    } catch (Throwable e) {
                                                        context.getSource().sendFeedback(() -> Text.literal("An error occurred: " + e), false);
                                                        return -1;
                                                    }
                                                    return 1;
                                                })
                                        )

                                )
                        )
                )
                .then(literal("attack_cooldown")
                        .then(argument("player",EntityArgumentType.player())
                                .executes(context -> {
                                    ServerPlayerEntity player = EntityArgumentType.getPlayer(context,"player");
                                    float cooldown = player.getAttackCooldownProgress(0f);
                                    context.getSource().sendFeedback(() -> Text.literal("The attack cooldown progress of %s is %s".formatted(context.getSource().getName(),cooldown)),true);
                                    return (int) (cooldown * 100);
                                })
                        )
                )
                .then(literal("despawn")
                        .requires(source -> source.hasPermissionLevel(2))
                        .then(argument("entities",EntityArgumentType.entities())
                                .executes(context -> {
                                    Collection<? extends Entity> entities = EntityArgumentType.getEntities(context,"entities");
                                    for (Entity entity : entities) {
                                        if (entity.isPlayer()) {
                                            context.getSource().sendFeedback(() -> Text.literal("§cPlayers aren't allowed, but the provided target selector references one or more player(s)."),false);
                                            AndrewsPackUtilities.LOGGER.info("Command failed because a player was included in the target selector. To bypass this (Don't unless you want crashes and/or major issues to happen!), add 'force' to the end of the command you used. Requires permission level 4 to use 'force'.");
                                            return -1;
                                        }
                                    }
                                    for (Entity entity : entities) {
                                        AndrewsPackUtilities.LOGGER.info("Despawning %s (UUID %s)".formatted(entity.getType().toString(),entity.getUuidAsString()));
                                        entity.remove(Entity.RemovalReason.DISCARDED);
                                    }
                                    context.getSource().sendFeedback(() -> Text.literal(("Successfully despawned %s " + (entities.toArray().length == 1 ? "entity" : "entities") + ".").formatted(entities.toArray().length)),true);
                                    return 1;
                                })
                                .then(literal("force")
                                        .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(4))
                                        .executes(context -> {
                                            Collection<? extends Entity> entities = EntityArgumentType.getEntities(context,"entities");
                                            context.getSource().sendFeedback(() -> Text.literal("Forcefully attempting to despawn. This WILL cause issues if a player is in the provided target selector."),true);
                                            for (Entity entity : entities) {
                                                AndrewsPackUtilities.LOGGER.info("Despawning %s (UUID %s)".formatted(entity.getType().toString(),entity.getUuidAsString()));
                                                entity.remove(Entity.RemovalReason.DISCARDED);
                                            }
                                            context.getSource().sendFeedback(() -> Text.literal(("Successfully despawned %s " + (entities.toArray().length == 1 ? "entity" : "entities") + ".").formatted(entities.toArray().length)),true);
                                            return 1;
                                        })
                                )
                        )
                )
                        .then(literal("check_damage")
                                .requires(source -> source.hasPermissionLevel(2))
                                .then(argument("target", EntityArgumentType.entity())
                                        .executes(context -> checkDamage(context.getSource(), EntityArgumentType.getEntity(context, "target"), null))
                                        .then(argument("damage_predicate", StringArgumentType.string())
                                                .executes(context -> checkDamage(context.getSource(), EntityArgumentType.getEntity(context, "target"), StringArgumentType.getString(context, "damage_predicate")))
                                        )
                                )
                        )
        ));
// Register a server tick event to reset the damage flags
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (net.minecraft.server.world.ServerWorld world : server.getWorlds()) {
                for (Entity entity : world.iterateEntities()) {
                    if (entity instanceof LivingEntity) {
                        if (entity instanceof IEntityDamageAccessor) {
                            ((IEntityDamageAccessor) entity).resetDamageFlags();
                        }
                    }
                }
            }
        });
    }
    private static int checkDamage(ServerCommandSource source, Entity target, String damagePredicate) throws CommandSyntaxException {
        try {
            if (!(target instanceof IEntityDamageAccessor)) {
                source.sendError(Text.literal("Damage detection is not available because the required mixin didn't load, likely because it doesn't work on your Minecraft version."));
                return 0;
            }
            if (((IEntityDamageAccessor) target).hasTakenDamageThisTick()) {
                if (damagePredicate == null) {
                    source.sendFeedback(() -> Text.literal("The provided entity took damage!"), false);
                    return 1;
                } else {

                    DamageSource lastSource = ((IEntityDamageAccessor) target).getLastDamageSourceThisTick();
                    if (lastSource != null) {
                        Optional<RegistryKey<DamageType>> damageTypeId = lastSource.getTypeRegistryEntry().getKey();
                        String damageTypeIdStringified = damageTypeId.orElse(DamageTypes.GENERIC).getValue().toString();
                        if (damagePredicate.equals(damageTypeIdStringified)) {
                            source.sendFeedback(() -> Text.literal("The provided entity took damage of type: " + damageTypeIdStringified), true);
                            return 1;
                        } else {
                            source.sendError(Text.literal("The entity took damage, but it did not match the specified damage type."));
                            source.sendFeedback(() -> Text.literal("The type of damage that WAS taken is: " + damageTypeIdStringified), true);
                            return 0;
                        }
                    } else {
                        source.sendError(Text.literal("The entity took damage, but the damage source could not be determined."));
                        return 0;
                    }
                }
            } else {
                source.sendError(Text.literal("The entity didn't take damage!"));
                return 0;
            }

        } catch (Exception e) {
            source.sendError(Text.literal("Received Exception: " + e.toString()));
        } catch (Throwable e) {
            source.sendError(Text.literal("Received Throwable: " + e.toString()));
        }
        return 0;
    }
}
