package net.baggyboi.endlessendmod.config;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class CommonConfig {


    public final ForgeConfigSpec.IntValue sentinelSpawnWeight;
    public final ForgeConfigSpec.IntValue sentinelSpawnRolls;
    public CommonConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("general");

    sentinelSpawnWeight = buildInt(builder, "sentinelSpawnWeight", "spawns", EEConfig.sentinelSpawnWeight, 0, 1000, "Spawn Weight, added to a pool of other mobs for each biome. Higher number = higher chance of spawning. 0 = disable spawn");
    sentinelSpawnRolls = buildInt(builder, "sentinelSpawnRolls", "spawns", EEConfig.sentinelSpawnRolls, 0, Integer.MAX_VALUE, "Random roll chance to enable mob spawning. Higher number = lower chance of spawning");

    }

    private static ForgeConfigSpec.BooleanValue buildBoolean(ForgeConfigSpec.Builder builder, String name, String catagory, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

    private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, String catagory, int defaultValue, int min, int max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }

    private static ForgeConfigSpec.DoubleValue buildDouble(ForgeConfigSpec.Builder builder, String name, String catagory, double defaultValue, double min, double max, String comment) {
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }
}