package com.andrewgaming;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.*;
import net.minecraft.command.argument.*;
import net.fabricmc.api.ModInitializer;

import static net.fabricmc.loader.impl.FabricLoaderImpl.MOD_ID;
import static net.minecraft.server.command.CommandManager.*;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;

public class SetupCommands {
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
                                                    context.getSource().sendFeedback(() -> Text.literal(String.valueOf(value1) + " + " + String.valueOf(value2) + " = " + result), false);

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
                                .then(argument("Vector",Vec3ArgumentType.vec3())

                                )
                        )
                )
        ));
    }
}
