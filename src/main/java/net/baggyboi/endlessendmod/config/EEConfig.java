package net.baggyboi.endlessendmod.config;

import com.google.common.collect.Lists;
import net.baggyboi.endlessendmod.EndlessEndMod;
import net.minecraftforge.fml.config.ModConfig;

import java.util.List;
public class EEConfig {
    public static int sentinelSpawnWeight = 12;
    public static int sentinelSpawnRolls = 0;

    public static void bake(ModConfig config) {
        try {
    sentinelSpawnWeight = ConfigHolder.COMMON.sentinelSpawnWeight.get();
    sentinelSpawnRolls = ConfigHolder.COMMON.sentinelSpawnRolls.get();
        } catch (Exception e) {
            EndlessEndMod.LOGGER.warn("An exception was caused trying to load the config for the Endless End Mod.");
            e.printStackTrace();
        }
    }

}
