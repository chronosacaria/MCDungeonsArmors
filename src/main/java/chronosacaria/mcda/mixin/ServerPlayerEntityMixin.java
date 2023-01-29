
package chronosacaria.mcda.mixin;

import chronosacaria.mcda.effects.ArmorEffects;
import chronosacaria.mcda.effects.EnchantmentEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    public void mcda$onArmorsAbilitiesTick(CallbackInfo ci) {
        if(!((Object) this instanceof ServerPlayerEntity playerEntity))
            return;
        if (!playerEntity.isAlive())
            return;

        World world = playerEntity.getEntityWorld();
        // Effects from Enchantments
        if (world.getTime() % 30 == 0) {
            EnchantmentEffects.applyCowardice(playerEntity);
            EnchantmentEffects.applyFrenzied(playerEntity);
            EnchantmentEffects.applyReckless(playerEntity);
        }

        // Effects from Armor Sets every 30 ticks
        if (world.getTime() % 30 == 0) {
            ArmorEffects.applyFireResistance(playerEntity); // Sprout & Living Vines Armour
            ArmorEffects.applyHaste(playerEntity); // Cave Crawler (below Y level 32) & Highland (above Y level 100)
            ArmorEffects.applyHeroOfTheVillage(playerEntity); // Hero's Armour & Gilded Glory
            ArmorEffects.applyHungerPain(playerEntity); // Hungry Horror Armour
            ArmorEffects.applyInvisibility(playerEntity); // Thief Armour Sneaking
            ArmorEffects.applyLuck(playerEntity); // Opulent Armour
            ArmorEffects.applySlowFalling(playerEntity); // Phantom and Frost Bite Armour
            ArmorEffects.applySprintingSpeed(playerEntity); // Shadow Walker Armour
            ArmorEffects.applyStalwartBulwarkResistanceEffect(playerEntity); // Stalwart Mail Armour
            ArmorEffects.applyWaterBreathing(playerEntity); // Glow Squid Armour Water Breathing Underwater
        }

        // Effects from Armor Sets on non-standard ticks
        ArmorEffects.applyRenegadesRushEffect(playerEntity); // Renegade Armour
    }

    @Inject(method = "consumeItem", at = @At("HEAD"))
    public void mcda$consumeItem(CallbackInfo ci){

        ServerPlayerEntity playerEntity = (ServerPlayerEntity) (Object) this;

        ArmorEffects.sweetBerrySpeed(playerEntity);
    }
}
