package net.baggyboi.endlessendmod.item;

import net.baggyboi.endlessendmod.EndlessEndMod;
import net.baggyboi.endlessendmod.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.common.ForgeSpawnEggItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EndlessEndMod.MOD_ID);

    public static final RegistryObject<Item> OCCULENCE = ITEMS.register("occulence",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_OCCULENCE = ITEMS.register("raw_occulence",
            () -> new Item(new Item.Properties()));


    public static final RegistryObject<Item> SENTINEL_SPAWN_EGG = ITEMS.register("sentinel_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SENTINEL, 0xb5b982, 0x8200f8, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}