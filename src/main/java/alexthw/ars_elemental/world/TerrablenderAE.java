package alexthw.ars_elemental.world;

import alexthw.ars_elemental.ArsElemental;
import com.hollingsworth.arsnouveau.common.world.biome.ArchwoodRegion;
import com.hollingsworth.arsnouveau.setup.Config;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Regions;

import java.util.function.Consumer;

public class TerrablenderAE {
    public static void registerBiomes() {
        Regions.register(new ArchwoodRegion(new ResourceLocation(ArsElemental.MODID, "overworld"), Config.ARCHWOOD_FOREST_WEIGHT.get()){
            @Override
            public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
                this.addModifiedVanillaOverworldBiomes(mapper, (builder -> {
                    builder.replaceBiome(Biomes.STONY_PEAKS, ModWorldgen.FLASHING_FOREST.getKey());
                    builder.replaceBiome(Biomes.SAVANNA, ModWorldgen.BLAZING_FOREST.getKey());
                    builder.replaceBiome(Biomes.SWAMP, ModWorldgen.CASCADING_FOREST.getKey());
                    builder.replaceBiome(Biomes.FLOWER_FOREST, ModWorldgen.FLOURISHING_FOREST.getKey());
                }));
            }
        });
    }
}
