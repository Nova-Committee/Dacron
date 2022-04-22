package committee.nova.dacron.common.world.init;

import committee.nova.dacron.common.block.init.BlockInit;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class WorldGenInit {
    public static void initFeatures() {
        for (Biome biome : Registry.BIOME) {
            addToBiome(biome);
        }
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> addToBiome(biome));
    }

    private static void addToBiome(Biome biome) {
        if (biome.getCategory() == Biome.Category.NETHER) {
            addOre(biome, OreFeatureConfig.Target.NETHERRACK, 3, 3, 8, 119, BlockInit.blockList.get(BlockInit.blockNames[0]).getDefaultState());
        }
    }

    private static void addOre(Biome biome, OreFeatureConfig.Target canReplaceIn, int veinSize, int veinsPerChunk, int minY, int maxY, BlockState ore) {
        biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Biome.configureFeature(Feature.ORE,
                new OreFeatureConfig(canReplaceIn, ore, veinSize), Decorator.COUNT_RANGE,
                new RangeDecoratorConfig(veinsPerChunk, minY, minY, maxY)));
    }
}
