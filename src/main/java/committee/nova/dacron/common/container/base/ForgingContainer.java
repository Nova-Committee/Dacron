package committee.nova.dacron.common.container.base;

import net.minecraft.block.BlockState;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Container;
import net.minecraft.container.ContainerType;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.BasicInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ForgingContainer extends Container {
    protected final CraftingResultInventory output = new CraftingResultInventory();
    protected final BlockContext context;
    protected final Inventory input = new BasicInventory(2) {
        @Override
        public void markDirty() {
            super.markDirty();
            ForgingContainer.this.onContentChanged(this);
        }
    };
    protected final PlayerEntity player;

    public ForgingContainer(@Nullable ContainerType<?> type, int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(type, syncId);
        int i;
        this.context = context;
        this.player = playerInventory.player;
        this.addSlot(new Slot(this.input, 0, 27, 47));
        this.addSlot(new Slot(this.input, 1, 76, 47));
        this.addSlot(new Slot(this.output, 2, 134, 47) {

            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                return ForgingContainer.this.canTakeOutput(playerEntity, this.hasStack());
            }

            @Override
            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
                return ForgingContainer.this.onTakeOutput(player, stack);
            }
        });
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    protected abstract boolean canTakeOutput(PlayerEntity var1, boolean var2);

    protected abstract ItemStack onTakeOutput(PlayerEntity var1, ItemStack var2);

    protected abstract boolean canUse(BlockState var1);

    public abstract void updateResult();

    @Override
    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.input) {
            this.updateResult();
        }
    }

    @Override
    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((World world, BlockPos blockPos) -> this.dropInventory(player, world, this.input));
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.context.run((world, blockPos) -> {
            if (!this.canUse(world.getBlockState((BlockPos) blockPos))) {
                return false;
            }
            return player.squaredDistanceTo((double) blockPos.getX() + 0.5, (double) blockPos.getY() + 0.5, (double) blockPos.getZ() + 0.5) <= 64.0;
        }, true);
    }

    protected boolean method_30025(ItemStack itemStack) {
        return false;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot) this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index == 2) {
                if (!this.insertItem(itemStack2, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onStackChanged(itemStack2, itemStack);
            } else if (index == 0 || index == 1) {
                if (!this.insertItem(itemStack2, 3, 39, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index < 39) {
                int i;
                int n = i = this.method_30025(itemStack) ? 1 : 0;
                if (!this.insertItem(itemStack2, i, 2, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, itemStack2);
        }
        return itemStack;
    }


}
