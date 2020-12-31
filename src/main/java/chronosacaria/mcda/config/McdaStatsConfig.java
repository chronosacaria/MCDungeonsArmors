package chronosacaria.mcda.config;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "mcda_stats")
public class McdaStatsConfig implements ConfigData {

    static {
        AutoConfig.register(McdaStatsConfig.class, JanksonConfigSerializer::new);
    }

    public static final McdaStatsConfig config = AutoConfig.getConfigHolder(McdaStatsConfig.class).getConfig();

    //Leather Armours
    @Comment ("Archer's Armor: Feet, Legs, Body, Head")
    public int[] ArcherArmor = {1,2,3,1};
    @Comment ("Hunter's Armor: Feet, Legs, Body, Head")
    public int[] HunterArmor = {2,4,6,2};
    @Comment ("Ocelot Armor: Feet, Legs, Body, Head")
    public int[] OcelotArmor = {2,4,6,2};
    @Comment ("Shadow Walker Armor: Feet, Legs, Body, Head")
    public int[] ShadowWalkerArmor = {2,4,6,2};
    @Comment ("Spelunker's Armor: Feet, Legs, Body, Head")
    public int[] SpelunkerArmor = {2,4,6,2};
    @Comment ("Cave Crawler's Armor: Feet, Legs, Body, Head")
    public int[] CaveCrawlerArmor = {2,5,6,2};
    @Comment ("Wolf Armor: Feet, Legs, Body, Head")
    public int[] WolfArmor = {2,4,6,2};
    @Comment ("Black Wolf Armor: Feet, Legs, Body, Head")
    public int[] BlackWolfArmor = {2,5,6,2};
    @Comment ("Fox Armor: Feet, Legs, Body, Head")
    public int[] FoxArmor = {2,4,6,2};

    //Fabric Armours
    @Comment ("Battle Robe: Feet, Legs, Body, Head")
    public int[] BattleRobe = {1,2,3,1};
    @Comment ("Spendid Robe: Feet, Legs, Body, Head")
    public int[] SpendidRobe = {2,3,3,2};
    @Comment ("Evocation Robe: Feet, Legs, Body, Head")
    public int[] EvocationRobe = {1,3,3,1};
    @Comment ("Ember Robe: Feet, Legs, Body, Head")
    public int[] EmberRobe = {1,3,3,1};
    @Comment ("Verdant Robe: Feet, Legs, Body, Head")
    public int[] VerdantRobe = {1,3,3,1};
    @Comment ("Thief Armor: Feet, Legs, Body, Head")
    public int[] ThiefArmor = {2,4,5,2};
    @Comment ("Spider Armor: Feet, Legs, Body, Head")
    public int[] SpiderArmor = {2,4,5,2};
    @Comment ("Soul Robe: Feet, Legs, Body, Head")
    public int[] SoulRobe = {1,5,6,1,};
    @Comment ("Soul Dancer Robe: Feet, Legs, Body, Head")
    public int[] SoulDancerRobe = {2,5,6,2};

    //Bone Armours
    @Comment ("Phantom Armor: Feet, Legs, Body, Head")
    public int[] PhantomArmor = {3,5,6,2};
    @Comment ("Forst Bite: Feet, Legs, Body, Head")
    public int[] FrostBiteArmor = {3,5,7,3};
    @Comment ("Grim Armor: Feet, Legs, Body, Head")
    public int[] GrimArmor = {3,5,6,2};
    @Comment ("Wither Armor: Feet, Legs, Body, Head")
    public int[] WitherArmor = {3,5,7,3};

    //Light Plate Armor
    @Comment ("Scale Mail: Feet, Legs, Body, Head")
    public int[] ScaleMail = {2,4,5,2};
    @Comment ("Highland Armor: Feet, Legs, Body, Head")
    public int[] HighlandArmor = {2,5,6,2};
    @Comment ("Beehive Armor: Feet, Legs, Body, Head")
    public int[] BeehiveArmor = {2,5,6,2};
    @Comment ("Beenest Armor: Feet, Legs, Body, Head")
    public int[] BeenestArmor = {2,4,5,2};
    @Comment ("Ghostly Armor: Feet, Legs, Body, Head")
    public int[] GhostlyArmor = {2,4,5,2};
    @Comment ("Ghost Kindler Armor: Feet, Legs, Body, Head")
    public int[] GhostKindlerArmor = {2,5,6,2};

    //Medium Plate Armor
    @Comment ("Reinforced Mail: Feet, Legs, Body, Head")
    public int[] ReinforcedMail = {3,4,6,3};
    @Comment ("Stalwart Mail: Feet, Legs, Body, Head")
    public int[] StalwartMail = {3,5,7,3};
    @Comment ("Guard's Armor: Feet, Legs, Body, Head")
    public int[] GuardsArmor = {3,5,7,3};
    @Comment ("Curious Armor: Feet, Legs, Body, Head")
    public int[] CuriousArmor = {3,5,7,3};
    @Comment ("Snow Armor: Feet, Legs, Body, Head")
    public int[] SnowArmor = {3,5,7,3};
    @Comment ("Frost Armor: Feet, Legs, Body, Head")
    public int[] FrostArmor = {3,5,7,3};
    @Comment ("Mercenary Armor: Feet, Legs, Body, Head")
    public int[] MercenaryArmor = {3,4,5,3};
    @Comment ("Renegade Armor: Feet, Legs, Body, Head")
    public int[] RenegadeArmor = {3,5,7,3};
    @Comment ("Hungry Horror Armor: Feet, Legs, Body, Head")
    public int[] HungryHorrorArmor = {3,5,7,3};
    @Comment ("Emerald Gear: Feet, Legs, Body, Head")
    public int[] EmeraldGearArmor = {3,5,7,3};
    @Comment ("Opulent Armor: Feet, Legs, Body, Head")
    public int[] OpulentArmor = {3,5,7,3};

    //Heavy Plate Armor
    @Comment ("Dark Armor: Feet, Legs, Body, Head")
    public int[] DarkArmor = {3,6,8,3};
    @Comment ("Titan's Shroud: Feet, Legs, Body, Head")
    public int[] TitanSroud = {3,6,8,3};
    @Comment ("Royal Guard: Feet, Legs, Body, Head")
    public int[] RoyalGuard = {3,6,8,3};
    @Comment ("Plate Armor: Feet, Legs, Body, Head")
    public int[] PlateArmor = {3,6,8,3};
    @Comment ("Full Metal Armor: Feet, Legs, Body, Head")
    public int[] FullMetalArmor = {4,6,8,4};
    @Comment ("Mystery Armor: Feet, Legs, Body, Head")
    public int[] MysteryArmor = {3,6,8,3};
    @Comment ("Champion Armor: Feet, Legs, Body, Head")
    public int[] ChampionArmor = {3,6,8,3};
    @Comment ("Hero's Armor: Feet, Legs, Body, Head")
    public int[] HerosArmor = {4,7,9,4};
    @Comment ("Gilded Glory: Feet, Legs, Body, Head")
    public int[] GildedGloryArmor = {3,6,8,3};

    public int[] getArcherArmor() {return ArcherArmor;}
    public int[] getHunterArmor() {return HunterArmor;}
    public int[] getOcelotArmor() {return OcelotArmor;}
    public int[] getShadowWalkerArmor() {return ShadowWalkerArmor;}
    public int[] getSpelunkerArmor() {return SpelunkerArmor;}
    public int[] getCaveCrawlerArmor() {return CaveCrawlerArmor;}
    public int[] getWolfArmor() {return WolfArmor;}
    public int[] getBlackWolfArmor() {return BlackWolfArmor;}
    public int[] getFoxArmor() {return FoxArmor;}

    public int[] getBattleRobe() {return BattleRobe;}
    public int[] getSpendidRobe() {return SpendidRobe;}
    public int[] getEvocationRobe(){return EvocationRobe;}
    public int[] getEmberRobe() {return EmberRobe;}
    public int[] getVerdantRobe() {return VerdantRobe;}
    public int[] getThiefArmor() {return ThiefArmor;}
    public int[] getSpiderArmor() {return SpiderArmor;}
    public int[] getSoulRobe() {return SoulRobe;}
    public int[] getSoulDancerRobe() {return SoulDancerRobe;}

    public int[] getPhantomArmor() {return PhantomArmor;}
    public int[] getFrostBiteArmor() {return FrostBiteArmor;}
    public int[] getGrimArmor() {return GrimArmor;}
    public int[] getWitherArmor() {return WitherArmor;}

    public int[] getScaleMail() {return ScaleMail;}
    public int[] getHighlandArmor() {return HighlandArmor;}
    public int[] getBeehiveArmor() {return BeehiveArmor;}
    public int[] getBeenestArmor() {return BeenestArmor;}
    public int[] getGhostlyArmor() {return GhostlyArmor;}
    public int[] getGhostKindlerArmor() {return GhostKindlerArmor;}

    public int[] getReinforcedMail() {return ReinforcedMail;}
    public int[] getStalwartMail() {return StalwartMail;}
    public int[] getGuardsArmor() {return GuardsArmor;}
    public int[] getCuriousArmor() {return CuriousArmor;}
    public int[] getSnowArmor() {return SnowArmor;}
    public int[] getFrostArmor() {return FrostArmor;}
    public int[] getMercenaryArmor() {return MercenaryArmor;}
    public int[] getRenegadeArmor() {return RenegadeArmor;}
    public int[] getHungryHorrorArmor() {return HungryHorrorArmor;}
    public int[] getEmeraldGearArmor() {return EmeraldGearArmor;}
    public int[] getOpulentArmor() {return OpulentArmor;}

    public int[] getDarkArmor() {return DarkArmor;}
    public int[] getTitanSroud() {return TitanSroud;}
    public int[] getRoyalGuard() {return RoyalGuard;}
    public int[] getPlateArmor() {return PlateArmor;}
    public int[] getFullMetalArmor() {return FullMetalArmor;}
    public int[] getMysteryArmor() {return MysteryArmor;}
    public int[] getChampionArmor() {return ChampionArmor;}
    public int[] getHerosArmor() {return HerosArmor;}
    public int[] getGildedGloryArmor() {return GildedGloryArmor;}
}

