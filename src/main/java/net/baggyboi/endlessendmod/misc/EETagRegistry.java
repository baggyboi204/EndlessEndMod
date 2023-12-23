package net.baggyboi.endlessendmod.misc;

import net.baggyboi.endlessendmod.EndlessEndMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.Structure;

public class EETagRegistry {
    public static final TagKey<Block> SENTINEL_SPAWNS = registerBlockTag("sentinel_spawns");

    private static TagKey<EntityType<?>> registerEntityTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(EndlessEndMod.MOD_ID, name));
    }

    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(EndlessEndMod.MOD_ID, name));
    }

    private static TagKey<Block> registerBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, new ResourceLocation(EndlessEndMod.MOD_ID, name));
    }

    private static TagKey<Biome> registerBiomeTag(String name) {
        return TagKey.create(Registries.BIOME, new ResourceLocation(EndlessEndMod.MOD_ID, name));
    }

    private static TagKey<Structure> registerStructureTag(String name) {
        return TagKey.create(Registries.STRUCTURE, new ResourceLocation(EndlessEndMod.MOD_ID, name));
    }
}
