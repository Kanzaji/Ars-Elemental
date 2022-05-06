package alexthw.ars_elemental.common.rituals;

import com.hollingsworth.arsnouveau.api.ritual.AbstractRitual;
import com.hollingsworth.arsnouveau.client.particle.ParticleColor;
import com.hollingsworth.arsnouveau.common.entity.LightningEntity;
import com.hollingsworth.arsnouveau.common.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class TeslaRitual extends AbstractRitual {
    @Override
    protected void tick() {
        if (getWorld() instanceof ServerLevel level && level.getGameTime() % 100 == 0 && this.tile != null) {
            List<LivingEntity> entities = level.getEntitiesOfClass(LivingEntity.class, new AABB(tile.getBlockPos()).inflate(5, 3, 5), living -> !(living instanceof Player));
            for (LivingEntity entity : entities) {
                Vec3 pos = entity.position();
                LightningEntity lightningBoltEntity = new LightningEntity(ModEntities.LIGHTNING_ENTITY, level);
                lightningBoltEntity.setPos(pos.x(), pos.y(), pos.z());
                lightningBoltEntity.setCause(null);
                level.addFreshEntity(lightningBoltEntity);
                setNeedsMana(true);
            }
        }
    }

    @Override
    public ParticleColor getCenterColor() {
        return new ParticleColor(
                100 + rand.nextInt(155),
                50 + rand.nextInt(200),
                rand.nextInt(25));
    }

    @Override
    public int getManaCost() {
        return 150;
    }

    @Override
    public String getLangName() {
        return "Tesla";
    }

    @Override
    public String getID() {
        return ID;
    }

    public static String ID = "ae_tesla_coil";

}