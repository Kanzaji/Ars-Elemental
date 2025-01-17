package alexthw.ars_elemental.recipe.jei;

import alexthw.ars_elemental.recipe.NetheriteUpgradeRecipe;
import com.hollingsworth.arsnouveau.client.jei.EnchantingApparatusRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class SpellBookUpgradeRecipeCategory extends EnchantingApparatusRecipeCategory<NetheriteUpgradeRecipe> {
    public SpellBookUpgradeRecipeCategory(IGuiHelper helper) {
        super(helper);
    }

    @Override
    public RecipeType<NetheriteUpgradeRecipe> getRecipeType() {
        return JeiArsExtraPlugin.SPELLBOOK_NETHERITE_TYPE;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, NetheriteUpgradeRecipe recipe, IFocusGroup focuses) {
        MultiProvider provider = multiProvider.apply(recipe);
        List<Ingredient> inputs = recipe.pedestalItems;
        double angleBetweenEach = 360.0 / inputs.size();
        List<ItemStack> outputs = new ArrayList<>();

        if (recipe.reagent != null) {
            builder.addSlot(RecipeIngredientRole.INPUT, 48, 45).addIngredients(recipe.reagent);
            for (ItemStack input : recipe.reagent.getItems()) {
                var temp = input.copy();
                temp.getOrCreateTag().putBoolean("ae_netherite", true);
                outputs.add(temp);
            }
        }
        for (Ingredient input : inputs) {
            builder.addSlot(RecipeIngredientRole.INPUT, (int) point.x, (int) point.y)
                    .addIngredients(input);
            point = rotatePointAbout(point, center, angleBetweenEach);
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 10).addIngredients(VanillaTypes.ITEM_STACK, outputs);
    }
}
