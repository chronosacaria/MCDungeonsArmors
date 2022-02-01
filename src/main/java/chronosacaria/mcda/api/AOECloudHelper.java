package chronosacaria.mcda.api;

import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;

public class AOECloudHelper {

    public static void spawnParticleCloud(LivingEntity attacker, LivingEntity victim, float radius, int duration, ParticleEffect particleEffect){
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(victim.world, victim.getX(), victim.getY(), victim.getZ());
        areaEffectCloudEntity.setOwner(attacker);
        areaEffectCloudEntity.setRadius(radius);
        areaEffectCloudEntity.setDuration(duration);
        areaEffectCloudEntity.setParticleType(particleEffect);
        attacker.world.spawnEntity(areaEffectCloudEntity);
    }
}
