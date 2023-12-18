package net.baggyboi.endlessendmod.event;

import net.baggyboi.endlessendmod.EndlessEndMod;
import net.baggyboi.endlessendmod.entity.ModEntities;
import net.baggyboi.endlessendmod.entity.custom.SentinelEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EndlessEndMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SENTINEL.get(), SentinelEntity.createAttributes().build());
    }
}