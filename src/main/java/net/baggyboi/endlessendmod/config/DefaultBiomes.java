package net.baggyboi.endlessendmod.config;

import com.github.alexthe666.citadel.config.biome.BiomeEntryType;
import com.github.alexthe666.citadel.config.biome.SpawnBiomeData;

public class DefaultBiomes {
    public static final SpawnBiomeData EMPTY = new SpawnBiomeData();
    public static final SpawnBiomeData SENTINEL = new SpawnBiomeData()
            .addBiomeEntry(BiomeEntryType.BIOME_TAG, false, "minecraft:is_overworld", 0)
            .addBiomeEntry(BiomeEntryType.BIOME_TAG, false, "forge:is_swamp", 0)
            .addBiomeEntry(BiomeEntryType.BIOME_TAG, false, "minecraft:is_end",0)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, true, "minecraft:is_nether", 0)
            .addBiomeEntry(BiomeEntryType.REGISTRY_NAME, false, "minecraft:mangrove_swamp", 1);

}
