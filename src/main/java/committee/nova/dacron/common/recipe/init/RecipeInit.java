package committee.nova.dacron.common.recipe.init;

import committee.nova.dacron.Dacron;
import committee.nova.dacron.common.recipe.impl.SmithingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeInit {
    public static void init() {
        Registry.register(Registry.RECIPE_SERIALIZER, SmithingRecipe.SmithingRecipeSerializer.ID, SmithingRecipe.SmithingRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(Dacron.MODID, SmithingRecipe.Type.ID), SmithingRecipe.Type.INSTANCE);
    }
}
