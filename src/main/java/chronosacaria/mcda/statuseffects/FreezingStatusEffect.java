package chronosacaria.mcda.statuseffects;

import chronosacaria.mcda.Mcda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Random;

public class FreezingStatusEffect extends StatusEffect {
    public FreezingStatusEffect(StatusEffectCategory type, int color, String id) {
        super(type, color);
        Registry.register(Registry.STATUS_EFFECT, new Identifier(Mcda.MOD_ID, id), this);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier){
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

        if (!entity.isDead()) {
            int freezingDamage = 3;
            World world = entity.getWorld();
            Random random = world.getRandom();

            entity.damage(DamageSource.FREEZE, (float) freezingDamage);

            if (world.isClient()) {
                boolean bl = entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ();
                if (bl && random.nextBoolean()) {
                    world.addParticle(ParticleTypes.SNOWFLAKE, entity.getX(), entity.getPos().getY() + 1, entity.getZ(),
                            MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F,
                            0.05D,
                            MathHelper.nextBetween(random, -1.0F, 1.0F) * 0.083333336F);
                }
            }
        }
    }
}
