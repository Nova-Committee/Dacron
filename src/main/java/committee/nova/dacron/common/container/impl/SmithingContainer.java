package committee.nova.dacron.common.container.impl;

import committee.nova.dacron.common.container.base.ForgingContainer;
import committee.nova.dacron.common.recipe.impl.SmithingRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SmithingContainer extends ForgingContainer {
    public static final TranslatableText CONTAINER_NAME = new TranslatableText("container.dacron.smithing");
    private final World world;
    private final List<SmithingRecipe> recipeList;
    @Nullable
    private SmithingRecipe recipe;

    public SmithingContainer(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, BlockContext.EMPTY);
    }

    public SmithingContainer(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(null, syncId, playerInventory, context);
        //todo: null?
        this.world = playerInventory.player.world;
        this.recipeList = this.world.getRecipeManager().getAllMatches(SmithingRecipe.Type.INSTANCE, playerInventory, world);
    }

    @Override
    protected boolean canUse(BlockState state) {
        return state.getBlock() == Blocks.SMITHING_TABLE;
    }

    @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return this.recipe != null && this.recipe.matches(this.input, this.world);
    }

    @Override
    protected ItemStack onTakeOutput(PlayerEntity player, ItemStack stack) {
        stack.onCraft(player.world, player, stack.getCount());
        this.output.unlockLastRecipe(player);
        this.method_29539(0);
        this.method_29539(1);
        this.context.run((World world, BlockPos blockPos) -> world.playLevelEvent(1044, blockPos, 0));
        return stack;
    }

    private void method_29539(int i) {
        ItemStack itemStack = this.input.getInvStack(i);
        itemStack.decrement(1);
        this.input.setInvStack(i, itemStack);
    }

    @Override
    public void updateResult() {
        List<SmithingRecipe> list = this.world.getRecipeManager().getAllMatches(SmithingRecipe.Type.INSTANCE, this.input, this.world);
        if (list.isEmpty()) {
            this.output.setInvStack(0, ItemStack.EMPTY);
        } else {
            this.recipe = list.get(0);
            ItemStack itemStack = this.recipe.craft(this.input);
            this.output.setLastRecipe(this.recipe);
            this.output.setInvStack(0, itemStack);
        }
    }

    @Override
    protected boolean method_30025(ItemStack itemStack) {
        return this.recipeList.stream().anyMatch(smithingRecipe -> smithingRecipe.method_30029(itemStack));
    }

    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.output && super.canInsertIntoSlot(stack, slot);
    }
}
