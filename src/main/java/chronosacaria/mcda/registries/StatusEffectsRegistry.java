package chronosacaria.mcda.registries;

import chronosacaria.mcda.statuseffects.FreezingStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class StatusEffectsRegistry {
    public static StatusEffect FREEZING;

    public static void register(){
        FREEZING = new FreezingStatusEffect(StatusEffectCategory.HARMFUL, 0xadd8e6, "freezing");
    }
}
