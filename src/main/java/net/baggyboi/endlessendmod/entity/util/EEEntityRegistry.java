package net.baggyboi.endlessendmod.entity.util;

import com.google.common.base.Predicates;
import net.baggyboi.endlessendmod.EndlessEndMod;
import net.baggyboi.endlessendmod.entity.custom.SentinelEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.FlyingMob.*;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.function.Predicate;
@Mod.EventBusSubscriber(modid = EndlessEndMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EEEntityRegistry {
    public static final DeferredRegister<EntityType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EndlessEndMod.MOD_ID);
    public static final RegistryObject<EntityType<SentinelEntity>> SENTINEL = DEF_REG.register("sentinel", () -> registerEntity(EntityType.Builder.of(SentinelEntity::new, MobCategory.CREATURE).sized(0.8F, 0.8F), "sentinel"));
    private static final EntityType registerEntity(EntityType.Builder builder, String entityName) {
        return (EntityType) builder.build(entityName);
    }
    @SubscribeEvent
    public static void initializeAttributes(EntityAttributeCreationEvent event) {
        SpawnPlacements.register(SENTINEL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SentinelEntity::canSentinelSpawn);
        event.put(SENTINEL.get(), SentinelEntity.createAttributes().build());
    }

    public static Predicate<LivingEntity> buildPredicateFromTag(TagKey<EntityType<?>> entityTag){
        if(entityTag == null){
            return Predicates.alwaysFalse();
        }else{
            return (com.google.common.base.Predicate<LivingEntity>) e -> e.isAlive() && e.getType().is(entityTag);
        }
    }

    public static Predicate<LivingEntity> buildPredicateFromTagTameable(TagKey<EntityType<?>> entityTag, LivingEntity owner){
        if(entityTag == null){
            return Predicates.alwaysFalse();
        }else{
            return (com.google.common.base.Predicate<LivingEntity>) e -> e.isAlive() && e.getType().is(entityTag) && !owner.isAlliedTo(e);
        }
    }

    public static boolean rollSpawn(int rolls, RandomSource random, MobSpawnType reason){
        if(reason == MobSpawnType.SPAWNER){
            return true;
        }else{
            return rolls <= 0 || random.nextInt(rolls) == 0;
        }
    }

    public static boolean createLeavesSpawnPlacement(LevelReader level, BlockPos pos, EntityType<?> type){
        BlockPos blockpos = pos.above();
        BlockPos blockpos1 = pos.below();
        FluidState fluidstate = level.getFluidState(pos);
        BlockState blockstate = level.getBlockState(pos);
        BlockState blockstate1 = level.getBlockState(blockpos1);
        if (!blockstate1.isValidSpawn(level, blockpos1, SpawnPlacements.Type.ON_GROUND, type) && !blockstate1.is(BlockTags.LEAVES)) {
            return false;
        } else {
            return NaturalSpawner.isValidEmptySpawnBlock(level, pos, blockstate, fluidstate, type) && NaturalSpawner.isValidEmptySpawnBlock(level, blockpos, level.getBlockState(blockpos), level.getFluidState(blockpos), type);
        }
    }
}
