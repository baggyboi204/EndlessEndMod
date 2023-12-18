package net.baggyboi.endlessendmod.entity;

import net.baggyboi.endlessendmod.EndlessEndMod;
import net.baggyboi.endlessendmod.entity.custom.SentinelEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EndlessEndMod.MOD_ID);

    public static final RegistryObject<EntityType<SentinelEntity>> SENTINEL =
            ENTITY_TYPES.register("sentinel", () -> EntityType.Builder.of(SentinelEntity::new, MobCategory.MONSTER)
                    .sized(1.0f, 3.0f).build("sentinel"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
