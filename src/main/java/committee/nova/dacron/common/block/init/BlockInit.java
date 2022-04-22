package committee.nova.dacron.common.block.init;

import committee.nova.dacron.Dacron;
import committee.nova.dacron.common.sound.init.SoundEventInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;

import static committee.nova.dacron.common.util.Utilities.prefixId;

public class BlockInit {
    public static final String[] blockNames = new String[]{
            "ancient_debris", prefixId("block")
    };
    public static final HashMap<String, Block> blockList = new HashMap<>();
    public static BlockSoundGroup ANCIENT_DEBRIS = new BlockSoundGroup(1.0f, 1.0f, SoundEventInit.getSound(2), SoundEventInit.getSound(3),
            SoundEventInit.getSound(4), SoundEventInit.getSound(5), SoundEventInit.getSound(6));
    public static BlockSoundGroup NETHERITE = new BlockSoundGroup(1.0f, 1.0f, SoundEventInit.getSound(7), SoundEventInit.getSound(8),
            SoundEventInit.getSound(9), SoundEventInit.getSound(10), SoundEventInit.getSound(11));

    public static void init() {
        putBlock();
        blockList.forEach((name, block) -> {
            final Identifier id = new Identifier(Dacron.MODID, name);
            Registry.register(Registry.BLOCK, id, block);
            Registry.register(Registry.ITEM, id, new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        });
    }

    public static void putBlock() {
        blockList.put(blockNames[0], new Block(Block.Settings.of(Material.METAL, MaterialColor.BLACK).strength(30F, 1200F)) {
            @Override
            public BlockSoundGroup getSoundGroup(BlockState state) {
                return ANCIENT_DEBRIS;
            }
        });
        blockList.put(blockNames[1], new Block(Block.Settings.of(Material.METAL, MaterialColor.BLACK).strength(50F, 1200F)) {
            @Override
            public BlockSoundGroup getSoundGroup(BlockState state) {
                return NETHERITE;
            }
        });
    }
}
