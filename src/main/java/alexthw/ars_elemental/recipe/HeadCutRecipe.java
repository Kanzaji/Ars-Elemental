package alexthw.ars_elemental.recipe;

import alexthw.ars_elemental.registry.ModRegistry;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import static com.hollingsworth.arsnouveau.api.RegistryHelper.getRegistryName;

public class HeadCutRecipe extends CustomRecipe {

    public ResourceLocation mob;
    public ItemStack result; // Result item
    public int chance;

    public HeadCutRecipe(ItemStack result, ResourceLocation mob, int chance, ResourceLocation pId) {
        super(pId);
        this.mob = mob;
        this.result = result;
        this.chance = chance;
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        return false;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRegistry.HEAD_CUT_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRegistry.HEAD_CUT.get();
    }

    public JsonElement asRecipe() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("type", "ars_elemental:head_cut");
        jsonobject.addProperty("mob", this.mob.toString());
        jsonobject.addProperty("drop_chance", this.chance);

        JsonObject resultObj = new JsonObject();
        resultObj.addProperty("item", getRegistryName(result.getItem()).toString());
        jsonobject.add("output", resultObj);
        return jsonobject;
    }

    public static class Serializer implements RecipeSerializer<HeadCutRecipe> {

        @Override
        public HeadCutRecipe fromJson(ResourceLocation pRecipeId, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            ResourceLocation mob = ResourceLocation.tryParse(GsonHelper.getAsString(json, "mob"));
            int chance = GsonHelper.getAsInt(json, "drop_chance");
            return new HeadCutRecipe(output, mob, chance, pRecipeId);
        }

        @Override
        public @Nullable HeadCutRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            ItemStack output = pBuffer.readItem();
            ResourceLocation mob = pBuffer.readResourceLocation();
            int chance = pBuffer.readInt();
            return new HeadCutRecipe(output, mob, chance, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, HeadCutRecipe pRecipe) {
            pBuffer.writeItem(pRecipe.result);
            pBuffer.writeResourceLocation(pRecipe.mob);
            pBuffer.writeInt(pRecipe.chance);
        }
    }
}
