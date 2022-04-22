package committee.nova.dacron.common.item.base;

import committee.nova.dacron.common.item.api.INonflammable;
import committee.nova.dacron.common.item.armor.ArmorMaterials;
import committee.nova.dacron.common.item.tool.ToolMaterials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;

public class ItemBases {
    public static Item.Settings set(byte type) {
        return new Item.Settings().group(type == 0 ? ItemGroup.TOOLS : type == 1 ? ItemGroup.COMBAT : ItemGroup.MATERIALS);
    }

    public static class NetheriteSwordItem extends SwordItem implements INonflammable {
        public NetheriteSwordItem() {
            super(ToolMaterials.NETHERITE, 3, -2.4F, set((byte) 1));
        }
    }

    public static class NetheriteShovelItem extends ShovelItem implements INonflammable {
        public NetheriteShovelItem() {
            super(ToolMaterials.NETHERITE, 1.5F, -3.0F, set((byte) 0));
        }
    }

    public static class NetheritePickaxeItem extends PickaxeItem implements INonflammable {
        public NetheritePickaxeItem() {
            super(ToolMaterials.NETHERITE, 1, -2.8F, set((byte) 0));
        }
    }

    public static class NetheriteAxeItem extends AxeItem implements INonflammable {
        public NetheriteAxeItem() {
            super(ToolMaterials.NETHERITE, 5.0F, -3.0F, set((byte) 0));
        }
    }

    public static class NetheriteHoeItem extends HoeItem implements INonflammable {
        public NetheriteHoeItem() {
            super(ToolMaterials.NETHERITE, 0F, set((byte) 0));
        }
    }

    public static class NetheriteArmorItem extends ArmorItem implements INonflammable {
        public NetheriteArmorItem(EquipmentSlot slot) {
            super(ArmorMaterials.NETHERITE, slot, set((byte) 1));
        }
    }

    public static class NetheriteMaterialItem extends Item implements INonflammable {
        public NetheriteMaterialItem() {
            super(set((byte) 2));
        }
    }
}
