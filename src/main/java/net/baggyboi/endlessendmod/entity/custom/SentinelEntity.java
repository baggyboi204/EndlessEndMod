package net.baggyboi.endlessendmod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class SentinelEntity extends FlyingMob implements Enemy {

    private float allowedHeightOffset = 0.5F;
    private static final EntityDataAccessor<Boolean> DATA_IS_CHARGING;
    private int explosionPower = 1;
    private int nextHeightOffsetChangeTick;

    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
    }

    public SentinelEntity(EntityType<? extends SentinelEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 5;
        this.moveControl = new SentinelMoveControl(this);
    }

    //ANIMATIONS START
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(5, new RandomFloatAroundGoal(this));
        this.goalSelector.addGoal(7, new SentinelLookGoal(this));
        this.goalSelector.addGoal(7, new SentinelShootFireballGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, true));

    }
    //GOALS END

    static class SentinelLookGoal extends Goal {
        private final SentinelEntity sentinel;

        public SentinelLookGoal(SentinelEntity pSentinel) {
            this.sentinel = pSentinel;
            this.setFlags(EnumSet.of(Flag.LOOK));
        }


        public boolean canUse() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            if (this.sentinel.getTarget() == null) {
                Vec3 $$0 = this.sentinel.getDeltaMovement();
                this.sentinel.setYRot(-((float)Mth.atan2($$0.x, $$0.z)) * 57.295776F);
                this.sentinel.yBodyRot = this.sentinel.getYRot();
            } else {
                LivingEntity $$1 = this.sentinel.getTarget();
                double $$2 = 64.0;
                if ($$1.distanceToSqr(this.sentinel) < 4096.0) {
                    double $$3 = $$1.getX() - this.sentinel.getX();
                    double $$4 = $$1.getZ() - this.sentinel.getZ();
                    this.sentinel.setYRot(-((float)Mth.atan2($$3, $$4)) * 57.295776F);
                    this.sentinel.yBodyRot = this.sentinel.getYRot();
                }
            }

        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.FOLLOW_RANGE, 100.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 2f);

    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BEACON_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.BEACON_ACTIVATE;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BEACON_DEACTIVATE;
    }

    @Override
    public void aiStep() {
        if (!this.onGround() && this.getDeltaMovement().y < 0.0) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.6, 1.0));
        }

        // Check if the entity is falling and reset fall distance to prevent fall damage
        if (this.fallDistance > 0.0F) {
            this.fallDistance = 0.0F;
        }

        super.aiStep();
    }

    protected void customServerAiStep() {
        --this.nextHeightOffsetChangeTick;
        if (this.nextHeightOffsetChangeTick <= 0) {
            this.nextHeightOffsetChangeTick = 100;
            this.allowedHeightOffset = (float) this.random.triangle(0.5, 3.4455);
        }

        LivingEntity $$0 = this.getTarget();
        if ($$0 != null && $$0.getEyeY() > this.getEyeY() + (double) this.allowedHeightOffset && this.canAttack($$0)) {
            Vec3 $$1 = this.getDeltaMovement();
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, (0.30000001192092896 - $$1.y) * 0.30000001192092896, 0.0));
            this.hasImpulse = true;
        }

        super.customServerAiStep();
    }
    private static class RandomFloatAroundGoal extends Goal {
        private final SentinelEntity sentinel;

        public RandomFloatAroundGoal(SentinelEntity pSentinelEntity) {
            this.sentinel = pSentinelEntity;
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            MoveControl $$0 = this.sentinel.getMoveControl();
            if (!$$0.hasWanted()) {
                return true;
            } else {
                double $$1 = $$0.getWantedX() - this.sentinel.getX();
                double $$2 = $$0.getWantedY() - this.sentinel.getY();
                double $$3 = $$0.getWantedZ() - this.sentinel.getZ();
                double $$4 = $$1 * $$1 + $$2 * $$2 + $$3 * $$3;
                return $$4 < 1.0 || $$4 > 3600.0;
            }
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void start() {
            RandomSource $$0 = this.sentinel.getRandom();
            double $$1 = this.sentinel.getX() + (double)(($$0.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double $$2 = this.sentinel.getY() + (double)(($$0.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double $$3 = this.sentinel.getZ() + (double)(($$0.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.sentinel.getMoveControl().setWantedPosition($$1, $$2, $$3, 1.0);
        }
    }
    private static class SentinelMoveControl extends MoveControl {
        private final SentinelEntity sentinel;
        private int floatDuration;

        public SentinelMoveControl(SentinelEntity pSentinel) {
            super(pSentinel);
            this.sentinel = pSentinel;
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.sentinel.getRandom().nextInt(5) + 2;
                    Vec3 $$0 = new Vec3(this.wantedX - this.sentinel.getX(), this.wantedY - this.sentinel.getY(), this.wantedZ - this.sentinel.getZ());
                    double $$1 = $$0.length();
                    $$0 = $$0.normalize();
                    if (this.canReach($$0, Mth.ceil($$1))) {
                        this.sentinel.setDeltaMovement(this.sentinel.getDeltaMovement().add($$0.scale(0.1)));
                    } else {
                        this.operation = Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 pPos, int pLength) {
            AABB $$2 = this.sentinel.getBoundingBox();

            for(int $$3 = 1; $$3 < pLength; ++$$3) {
                $$2 = $$2.move(pPos);
                if (!this.sentinel.level().noCollision(this.sentinel, $$2)) {
                    return false;
                }
            }

            return true;
        }
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 2.6F;
    }

    static {
        DATA_IS_CHARGING = SynchedEntityData.defineId(SentinelEntity.class, EntityDataSerializers.BOOLEAN);
    }
    public boolean isCharging() {
        return (Boolean)this.entityData.get(DATA_IS_CHARGING);
    }
    public void setCharging(boolean pCharging) {
        this.entityData.set(DATA_IS_CHARGING, pCharging);
    }
    public int getExplosionPower() {
        return this.explosionPower;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_IS_CHARGING, false);
    }


    private static class SentinelShootFireballGoal extends Goal {
        private final SentinelEntity sentinel;
        public int chargeTime;

        public SentinelShootFireballGoal(SentinelEntity pSentinel) {
            this.sentinel = pSentinel;
        }

        public boolean canUse() {
            return this.sentinel.getTarget() != null;
        }

        public void start() {
            this.chargeTime = 0;
        }

        public void stop() {
            this.sentinel.setCharging(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity $$0 = this.sentinel.getTarget();
            if ($$0 != null) {
                double $$1 = 64.0;
                if ($$0.distanceToSqr(this.sentinel) < 4096.0 && this.sentinel.hasLineOfSight($$0)) {
                    Level $$2 = this.sentinel.level();
                    ++this.chargeTime;
                    if (this.chargeTime == 10 && !this.sentinel.isSilent()) {
                        $$2.levelEvent((Player)null, 1015, this.sentinel.blockPosition(), 0);
                    }

                    if (this.chargeTime == 20) {
                        double $$3 = 4.0;
                        Vec3 $$4 = this.sentinel.getViewVector(1.0F);
                        double $$5 = $$0.getX() - (this.sentinel.getX() + $$4.x);
                        double $$6 = $$0.getY(0.5) - (0.5 + this.sentinel.getY(0.5));
                        double $$7 = $$0.getZ() - (this.sentinel.getZ() + $$4.z);
                        if (!this.sentinel.isSilent()) {
                            $$2.levelEvent((Player)null, 1016, this.sentinel.blockPosition(), 0);
                        }

                        LargeFireball $$8 = new LargeFireball($$2, this.sentinel, $$5, $$6, $$7, this.sentinel.getExplosionPower());
                        $$8.setPos(this.sentinel.getX() + $$4.x, this.sentinel.getY(0.5) + 0.5, $$8.getZ() + $$4.z);
                        $$2.addFreshEntity($$8);
                        this.chargeTime = -40;
                    }
                } else if (this.chargeTime > 0) {
                    --this.chargeTime;
                }

                this.sentinel.setCharging(this.chargeTime > 10);
            }
        }

    }

}