package committee.nova.dacron.common.recipe.factory;

import com.google.gson.JsonObject;
import committee.nova.dacron.common.recipe.impl.SmithingRecipe;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.CriteriaMerger;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.RecipeUnlockedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SmithingRecipeJsonFactory {
    private final Ingredient base;
    private final Ingredient addition;
    private final Item result;
    private final Advancement.Task builder = Advancement.Task.create();
    private final RecipeSerializer<?> serializer;

    public SmithingRecipeJsonFactory(RecipeSerializer<?> serializer, Ingredient base, Ingredient addition, Item result) {
        this.serializer = serializer;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    public static SmithingRecipeJsonFactory create(Ingredient base, Ingredient addition, Item result) {
        return new SmithingRecipeJsonFactory(SmithingRecipe.SmithingRecipeSerializer.INSTANCE, base, addition, result);
    }

    public SmithingRecipeJsonFactory criterion(String criterionName, CriterionConditions conditions) {
        this.builder.criterion(criterionName, conditions);
        return this;
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, String recipeId) {
        this.offerTo(exporter, new Identifier(recipeId));
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier recipeId) {
        this.validate(recipeId);
        this.builder.parent(new Identifier("recipes/root")).criterion("has_the_recipe", new RecipeUnlockedCriterion.Conditions(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).criteriaMerger(CriteriaMerger.OR);
        final ItemGroup group = this.result.getGroup();
        exporter.accept(new SmithingRecipeJsonProvider(recipeId, this.serializer, this.base, this.addition, this.result, this.builder, new Identifier(recipeId.getNamespace(), "recipes/" + ((group == null) ? "" : group.getName()) + "/" + recipeId.getPath())));
    }

    private void validate(Identifier recipeId) {
        if (this.builder.getCriteria().isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + recipeId);
        }
    }

    public static class SmithingRecipeJsonProvider
            implements RecipeJsonProvider {
        private final Identifier recipeId;
        private final Ingredient base;
        private final Ingredient addition;
        private final Item result;
        private final Advancement.Task builder;
        private final Identifier advancementId;
        private final RecipeSerializer<?> serializer;

        public SmithingRecipeJsonProvider(Identifier recipeId, RecipeSerializer<?> serializer, Ingredient base, Ingredient addition, Item result, Advancement.Task builder, Identifier advancementId) {
            this.recipeId = recipeId;
            this.serializer = serializer;
            this.base = base;
            this.addition = addition;
            this.result = result;
            this.builder = builder;
            this.advancementId = advancementId;
        }

        @Override
        public void serialize(JsonObject json) {
            json.add("base", this.base.toJson());
            json.add("addition", this.addition.toJson());
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item", Registry.ITEM.getId(this.result).toString());
            json.add("result", jsonObject);
        }

        @Override
        public Identifier getRecipeId() {
            return this.recipeId;
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return this.serializer;
        }

        @Override
        @Nullable
        public JsonObject toAdvancementJson() {
            return this.builder.toJson();
        }

        @Override
        @Nullable
        public Identifier getAdvancementId() {
            return this.advancementId;
        }
    }
}
