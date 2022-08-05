package chronosacaria.mcda.config;

import chronosacaria.mcda.items.itemhelpers.DropHelper;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.EnumMap;

@Config(name = "mcda_item_drops")
public class McdaItemDropsConfig implements ConfigData {
    public EnumMap<DropHelper, Integer> dropAmounts = new EnumMap<>(DropHelper.class);

    public McdaItemDropsConfig(){
        for (DropHelper dropHelper : DropHelper.values())
            dropAmounts.put(dropHelper, 1);
        dropAmounts.replace(DropHelper.GOAT_PELT, 2);
    }
}
