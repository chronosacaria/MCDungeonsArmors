package chronosacaria.mcda.registry;

import chronosacaria.mcda.Mcda;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundsRegistry {
    /*
     *   Sound used for DODGE_SOUND is Bamboo Swing, C3 by InspectorJ,
     *   and it can be found at the following address:
     *   https://freesound.org/people/InspectorJ/sounds/394454/.
     *   It was used under the CC BY 3.0. The License may be
     *   viewed here: https://creativecommons.org/licenses/by/3.0/.
     */

    public static final Identifier DODGE_SOUND = new Identifier(Mcda.MOD_ID, "dodge");
    public static SoundEvent DODGE_SOUND_EVENT = SoundEvent.of(DODGE_SOUND);

    public static void init(){
        Registry.register(Registries.SOUND_EVENT, DODGE_SOUND, DODGE_SOUND_EVENT);
    }
}
