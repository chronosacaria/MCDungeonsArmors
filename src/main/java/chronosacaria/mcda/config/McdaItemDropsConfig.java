package chronosacaria.mcda.config;

import chronosacaria.mcda.items.itemhelpers.DropHelper;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.EnumMap;

@Config(name = "mcda_item_drops")
public class McdaItemDropsConfig implements ConfigData {
    public EnumMap<DropHelper, Float> maxDropAmounts = new EnumMap<>(DropHelper.class);

    public McdaItemDropsConfig(){
        for (DropHelper dropHelper : DropHelper.values())
            maxDropAmounts.put(dropHelper, 1.0F);
        maxDropAmounts.replace(DropHelper.GOAT_PELT, 2.0F);
    }
}
