package com.yungnickyoung.minecraft.faketnt.block;


import com.yungnickyoung.minecraft.faketnt.entity.PrimedFakeTnt;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class FakeTntBlock extends Block {
    public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

    public FakeTntBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, false));
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl) {
        if (blockState2.is(blockState.getBlock())) {
            return;
        }
        if (level.hasNeighborSignal(blockPos)) {
            explode(level, blockPos);
            level.removeBlock(blockPos, false);
        }
    }

    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos2, boolean bl) {
        if (level.hasNeighborSignal(blockPos)) {
            explode(level, blockPos);
            level.removeBlock(blockPos, false);
        }
    }

    @Override
    public void playerWillDestroy(Level level, BlockPos blockPos, BlockState blockState, Player player) {
        if (!level.isClientSide() && !player.isCreative() && blockState.getValue(UNSTABLE)) {
            explode(level, blockPos);
        }
        super.playerWillDestroy(level, blockPos, blockState, player);
    }

    @Override
    public void wasExploded(Level level, BlockPos blockPos, Explosion explosion) {
        if (level.isClientSide) {
            return;
        }
        PrimedFakeTnt primedTnt = new PrimedFakeTnt(level, (double)blockPos.getX() + 0.5, blockPos.getY(), (double)blockPos.getZ() + 0.5, explosion.getSourceMob());
        int i = primedTnt.getFuse();
        primedTnt.setFuse((short)(level.random.nextInt(i / 4) + i / 8));
        level.addFreshEntity(primedTnt);
    }

    public static void explode(Level level, BlockPos blockPos) {
        explode(level, blockPos, null);
    }

    private static void explode(Level level, BlockPos blockPos, @Nullable LivingEntity livingEntity) {
        if (level.isClientSide) {
            return;
        }
        PrimedFakeTnt primedTnt = new PrimedFakeTnt(level, (double)blockPos.getX() + 0.5, blockPos.getY(), (double)blockPos.getZ() + 0.5, livingEntity);
        level.addFreshEntity(primedTnt);
        level.playSound(null, primedTnt.getX(), primedTnt.getY(), primedTnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0f, 1.0f);
        level.gameEvent(livingEntity, GameEvent.PRIME_FUSE, blockPos);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player2, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        ItemStack itemStack = player2.getItemInHand(interactionHand);
        if (itemStack.is(Items.FLINT_AND_STEEL) || itemStack.is(Items.FIRE_CHARGE)) {
            explode(level, blockPos, player2);
            level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 11);
            Item item = itemStack.getItem();
            if (!player2.isCreative()) {
                if (itemStack.is(Items.FLINT_AND_STEEL)) {
                    itemStack.hurtAndBreak(1, player2, player -> player.broadcastBreakEvent(interactionHand));
                } else {
                    itemStack.shrink(1);
                }
            }
            player2.awardStat(Stats.ITEM_USED.get(item));
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return super.use(blockState, level, blockPos, player2, interactionHand, blockHitResult);
    }

    @Override
    public void onProjectileHit(Level level, BlockState blockState, BlockHitResult blockHitResult, Projectile projectile) {
        if (!level.isClientSide) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            Entity entity = projectile.getOwner();
            if (projectile.isOnFire() && projectile.mayInteract(level, blockPos)) {
                explode(level, blockPos, entity instanceof LivingEntity ? (LivingEntity)entity : null);
                level.removeBlock(blockPos, false);
            }
        }
    }

    @Override
    public boolean dropFromExplosion(Explosion explosion) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(UNSTABLE);
    }
}
