package com.andrewgaming;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.argument.*;
import net.fabricmc.api.ModInitializer;

import static com.fasterxml.jackson.databind.type.LogicalType.Array;
import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static net.fabricmc.loader.impl.FabricLoaderImpl.MOD_ID;
import static net.minecraft.server.command.CommandManager.*;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.network.packet.Packet;
import net.minecraft.server.command.ParticleCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.InvalidIdentifierException;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.dragon.*;
import net.minecraft.entity.boss.dragon.phase.*;
import com.andrewgaming.AndrewsPackUtilities;

import java.util.Collection;

public class SetupCommands{
    public static void Init() {
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
// Have to give up on this for now
//                .then(literal("packet")
//
//                        .then(argument("player",EntityArgumentType.player())
//                                .then(literal("set_entity_motion")
//                                .executes(context -> {
//                                            ServerPlayerEntity player = EntityArgumentType.getPlayer(context,"client");
//                                            Packet<?> packetToSend = new net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket(player);
//                                            player.networkHandler.sendPacket(packetToSend);
//                                            return 1;
//                                        }
//                                ))
//                        )
//                )
        ));
    }
}
