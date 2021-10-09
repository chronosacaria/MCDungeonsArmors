package chronosacaria.mcda.registry;

import chronosacaria.mcda.statuseffects.FreezingStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class StatusEffectsRegistry {
    public static StatusEffect FREEZING;

    public static void init(){
        FREEZING = new FreezingStatusEffect(StatusEffectCategory.HARMFUL, 0xadd8e6, "freezing");
    }
}
