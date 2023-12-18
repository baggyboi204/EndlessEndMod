package net.baggyboi.endlessendmod.item;

import net.baggyboi.endlessendmod.EndlessEndMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EndlessEndMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ENDLESS_END_TAB = CREATIVE_MODE_TABS.register("endless_end_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.OCCULENCE.get()))
                    .title(Component.translatable("creativetab.endless_end_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.OCCULENCE.get());
                        pOutput.accept(ModItems.RAW_OCCULENCE.get());
                        pOutput.accept(ModItems.SENTINEL_SPAWN_EGG.get());

                        pOutput.accept(Items.DIAMOND);


                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}