package committee.nova.dacron.common.item.init;

import committee.nova.dacron.Dacron;
import committee.nova.dacron.common.item.base.ItemBases;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

import static committee.nova.dacron.common.util.Utilities.prefixId;

public class ItemInit {
    public static final String[] names = new String[]{
            prefixId("shovel"), prefixId("pickaxe"), prefixId("axe"), prefixId("hoe"),
            prefixId("helmet"), prefixId("chestplate"), prefixId("leggings"), prefixId("boots"), prefixId("sword"), prefixId("ingot"), prefixId("scrap")
    };
    public static final HashMap<String, Item> itemList = new HashMap<>();

    public static void init() {
        putItem();
        itemList.forEach((name, item) -> Registry.register(Registry.ITEM, new Identifier(Dacron.MODID, name), item));
    }

    public static void putItem() {
        itemList.put(names[0], new ItemBases.NetheriteShovelItem());
        itemList.put(names[1], new ItemBases.NetheritePickaxeItem());
        itemList.put(names[2], new ItemBases.NetheriteAxeItem());
        itemList.put(names[3], new ItemBases.NetheriteHoeItem());
        itemList.put(names[4], new ItemBases.NetheriteArmorItem(EquipmentSlot.HEAD));
        itemList.put(names[5], new ItemBases.NetheriteArmorItem(EquipmentSlot.CHEST));
        itemList.put(names[6], new ItemBases.NetheriteArmorItem(EquipmentSlot.LEGS));
        itemList.put(names[7], new ItemBases.NetheriteArmorItem(EquipmentSlot.FEET));
        itemList.put(names[8], new ItemBases.NetheriteSwordItem());
        itemList.put(names[9], new ItemBases.NetheriteMaterialItem());
        //todo:beacon payment
        itemList.put(names[10], new ItemBases.NetheriteMaterialItem());
    }
}
