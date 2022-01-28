package com.yungnickyoung.minecraft.faketnt.entity;

import com.yungnickyoung.minecraft.faketnt.init.FTModEntities;
import com.yungnickyoung.minecraft.faketnt.init.FTModParticles;
import com.yungnickyoung.minecraft.faketnt.init.FTModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PrimedFakeTnt extends Entity {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID = SynchedEntityData.defineId(PrimedFakeTnt.class, EntityDataSerializers.INT);
    private static final int DEFAULT_FUSE_TIME = 80;
    @Nullable
    private LivingEntity owner;

    public PrimedFakeTnt(EntityType<? extends PrimedFakeTnt> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public PrimedFakeTnt(Level level, double d, double e, double f, @Nullable LivingEntity livingEntity) {
        this(FTModEntities.FAKE_TNT, level);
        this.setPos(d, e, f);
        double g = level.random.nextDouble() * 6.2831854820251465;
        this.setDeltaMovement(-Math.sin(g) * 0.02, 0.2f, -Math.cos(g) * 0.02);
        this.setFuse(DEFAULT_FUSE_TIME);
        this.xo = d;
        this.yo = e;
        this.zo = f;
        this.owner = livingEntity;
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_FUSE_ID, DEFAULT_FUSE_TIME);
    }

    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }

    @Override
    public boolean isPickable() {
        return !this.isRemoved();
    }

    @Override
    public void tick() {
        if (!this.isNoGravity()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.04, 0.0));
        }
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
        if (this.onGround) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
        }
        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i == 0) {
            this.explode();
        } else if (i < 0) {
            this.discard();
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level.isClientSide) {
                this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    private void explode() {
        if (this.level.isClientSide) {
            this.level.playLocalSound(this.getX(), this.getY(0.0625), this.getZ(), FTModSounds.SURPRISE_SOUND, SoundSource.BLOCKS, 0.5f, 1.0f, false);
            for (int i = 0; i < random.nextInt(5) + 5; i++) {
                this.level.addParticle(FTModParticles.BLUE_CONFETTI, this.getX(), this.getY() + 0.5, this.getZ(), 0, 0, 0);
            }
            for (int i = 0; i < random.nextInt(5) + 5; i++) {
                this.level.addParticle(FTModParticles.GREEN_CONFETTI, this.getX(), this.getY() + 0.5, this.getZ(), 0, 0, 0);
            }
            for (int i = 0; i < random.nextInt(5) + 5; i++) {
                this.level.addParticle(FTModParticles.PINK_CONFETTI, this.getX(), this.getY() + 0.5, this.getZ(), 0, 0, 0);
            }
            for (int i = 0; i < random.nextInt(5) + 5; i++) {
                this.level.addParticle(FTModParticles.PURPLE_CONFETTI, this.getX(), this.getY() + 0.5, this.getZ(), 0, 0, 0);
            }
            for (int i = 0; i < random.nextInt(5) + 5; i++) {
                this.level.addParticle(FTModParticles.YELLOW_CONFETTI, this.getX(), this.getY() + 0.5, this.getZ(), 0, 0, 0);
            }
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putShort("Fuse", (short)this.getFuse());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        this.setFuse(compoundTag.getShort("Fuse"));
    }

    @Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    @Override
    protected float getEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.15f;
    }

    public void setFuse(int i) {
        this.entityData.set(DATA_FUSE_ID, i);
    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
