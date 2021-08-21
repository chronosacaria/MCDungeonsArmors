package chronosacaria.mcda.registry;

import chronosacaria.mcda.statuseffects.FreezingStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;

public class StatusEffectsRegistry {
    public static StatusEffect FREEZING;

    public static void init(){
        FREEZING = new FreezingStatusEffect(StatusEffectType.HARMFUL, 0xadd8e6, "freezing");
    }
}
