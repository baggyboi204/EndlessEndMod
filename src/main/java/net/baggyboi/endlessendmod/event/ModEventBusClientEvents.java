package net.baggyboi.endlessendmod.event;

import net.baggyboi.endlessendmod.EndlessEndMod;
import net.baggyboi.endlessendmod.entity.client.ModModelLayers;
import net.baggyboi.endlessendmod.entity.client.SentinelModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EndlessEndMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.SENTINEL_LAYER, SentinelModel::createBodyLayer);
    }
}