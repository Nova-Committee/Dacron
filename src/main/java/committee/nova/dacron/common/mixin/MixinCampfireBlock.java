package committee.nova.dacron.common.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CampfireBlock.class)
public abstract class MixinCampfireBlock {
    @Shadow
    @Final
    public static BooleanProperty LIT;

    @Inject(method = "activate", at = @At("HEAD"), cancellable = true)
    public void onActivate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<Boolean> cir) {
        final Iterable<ItemStack> stacks = player.getItemsHand();
        if (!state.get(LIT)) return;
        if (!(player.getStackInHand(Hand.MAIN_HAND).getItem() instanceof ShovelItem) && !(player.getStackInHand(Hand.OFF_HAND).getItem() instanceof ShovelItem))
            return;
        world.setBlockState(pos, state.with(LIT, false), 3);
        if (!world.isClient)
            world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
        cir.setReturnValue(true);
    }
}
