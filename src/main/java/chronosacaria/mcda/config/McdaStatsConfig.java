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
    @Comment ("Hunter's Armor: Feet, Legs, Body, Head")
    public int[] HunterArmor = {1,2,3,1};
    public float HunterArmorToughness = 0F;
    public float HunterArmorKnockback = 0F;
    @Comment ("Archer's Armor: Feet, Legs, Body, Head")
    public int[] ArcherArmor = {2,4,6,2};
    public float ArcherArmorToughness = 0F;
    public float ArcherArmorKnockback = 0F;
    @Comment ("Ocelot Armor: Feet, Legs, Body, Head")
    public int[] OcelotArmor = {2,4,6,2};
    public float OcelotArmorToughness = 0F;
    public float OcelotArmorKnockback = 0F;
    @Comment ("Shadow Walker Armor: Feet, Legs, Body, Head")
    public int[] ShadowWalkerArmor = {2,4,6,2};
    public float ShadowWalkerArmorToughness = 0F;
    public float ShadowWalkerArmorKnockback = 0F;
    @Comment ("Spelunker's Armor: Feet, Legs, Body, Head")
    public int[] SpelunkerArmor = {2,4,6,2};
    public float SpelunkerArmorToughness = 0F;
    public float SpelunkerArmorKnockback = 0F;
    @Comment ("Cave Crawler's Armor: Feet, Legs, Body, Head")
    public int[] CaveCrawlerArmor = {2,5,6,2};
    public float CaveCrawlerArmorToughness = 0F;
    public float CaveCrawlerArmorKnockback = 0F;
    @Comment ("Wolf Armor: Feet, Legs, Body, Head")
    public int[] WolfArmor = {2,4,6,2};
    public float WolfArmorToughness = 0F;
    public float WolfArmorKnockback = 0F;
    @Comment ("Black Wolf Armor: Feet, Legs, Body, Head")
    public int[] BlackWolfArmor = {2,5,6,2};
    public float BlackWolfArmorToughness = 0F;
    public float BlackWolfArmorKnockback = 0F;
    @Comment ("Fox Armor: Feet, Legs, Body, Head")
    public int[] FoxArmor = {2,4,6,2};
    public float FoxArmorToughness = 0F;
    public float FoxArmorKnockback = 0F;
    @Comment ("Climbing Gear: Feet, Legs, Body, Head")
    public int[] ClimbingGear = {1,5,6,1,};
    public float ClimbingGearToughness = 0F;
    public float ClimbingGearKnockback = 0F;
    @Comment ("Rugged Climbing Gear: Feet, Legs, Body, Head")
    public int[] RuggedClimbingGear = {2,5,6,2};
    public float RuggedClimbingGearToughness = 0F;
    public float RuggedClimbingGearKnockback = 0F;
    @Comment ("Goat Armor: Feet, Legs, Body, Head")
    public int[] GoatArmor = {2,5,6,2};
    public float GoatArmorToughness = 0F;
    public float GoatArmorKnockback = 0.2F;

    //Fabric Armours
    @Comment ("Battle Robe: Feet, Legs, Body, Head")
    public int[] BattleRobe = {1,2,3,1};
    public float BattleRobeToughness = 0F;
    public float BattleRobeKnockback = 0F;
    @Comment ("Splendid Robe: Feet, Legs, Body, Head")
    public int[] SplendidRobe = {2,3,3,2};
    public float SplendidRobeToughness = 0F;
    public float SplendidRobeKnockback = 0F;
    @Comment ("Evocation Robe: Feet, Legs, Body, Head")
    public int[] EvocationRobe = {1,3,3,1};
    public float EvocationRobeToughness = 0F;
    public float EvocationRobeKnockback = 0F;
    @Comment ("Ember Robe: Feet, Legs, Body, Head")
    public int[] EmberRobe = {1,3,3,1};
    public float EmberRobeToughness = 0F;
    public float EmberRobeKnockback = 0F;
    @Comment ("Verdant Robe: Feet, Legs, Body, Head")
    public int[] VerdantRobe = {1,3,3,1};
    public float VerdantRobeToughness = 0F;
    public float VerdantRobeKnockback = 0F;
    @Comment ("Thief Armor: Feet, Legs, Body, Head")
    public int[] ThiefArmor = {2,4,5,2};
    public float ThiefArmorToughness = 0F;
    public float ThiefArmorKnockback = 0F;
    @Comment ("Spider Armor: Feet, Legs, Body, Head")
    public int[] SpiderArmor = {2,4,5,2};
    public float SpiderArmorToughness = 0F;
    public float SpiderArmorKnockback = 0F;
    @Comment ("Soul Robe: Feet, Legs, Body, Head")
    public int[] SoulRobe = {1,5,6,1,};
    public float SoulRobeToughness = 2.0F;
    public float SoulRobeKnockback = 0F;
    @Comment ("Soul Dancer Robe: Feet, Legs, Body, Head")
    public int[] SoulDancerRobe = {2,5,6,2};
    public float SouldancerRobeToughness = 2.0F;
    public float SouldancerRobeKnockback = 0F;

    //Bone Armours
    @Comment ("Phantom Armor: Feet, Legs, Body, Head")
    public int[] PhantomArmor = {3,5,6,2};
    public float PhantomArmorToughness = 0F;
    public float PhantomArmorKnockback = 0F;
    @Comment ("Frost Bite: Feet, Legs, Body, Head")
    public int[] FrostBiteArmor = {3,5,7,3};
    public float FrostBiteArmorToughness = 0F;
    public float FrostBiteArmorKnockback = 0F;
    @Comment ("Grim Armor: Feet, Legs, Body, Head")
    public int[] GrimArmor = {3,5,6,2};
    public float GrimArmorToughness = 0F;
    public float GrimArmorKnockback = 0F;
    @Comment ("Wither Armor: Feet, Legs, Body, Head")
    public int[] WitherArmor = {3,5,7,3};
    public float WitherArmorToughness = 2.0F;
    public float WitherArmorKnockback = 0F;

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
    public int[] TitanShroud = {3,6,8,3};
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
    public float getArcherArmorToughness() { return ArcherArmorToughness; }
    public float getArcherArmorKnockback() { return ArcherArmorKnockback; }
    public int[] getHunterArmor() {return HunterArmor;}
    public float getHunterArmorToughness() { return HunterArmorToughness; }
    public float getHunterArmorKnockback() { return HunterArmorKnockback; }
    public int[] getOcelotArmor() {return OcelotArmor;}
    public float getOcelotArmorToughness() { return OcelotArmorToughness; }
    public float getOcelotArmorKnockback() { return OcelotArmorKnockback; }
    public int[] getShadowWalkerArmor() {return ShadowWalkerArmor;}
    public float getShadowWalkerArmorToughness() { return ShadowWalkerArmorToughness; }
    public float getShadowWalkerArmorKnockback() { return ShadowWalkerArmorKnockback; }
    public int[] getSpelunkerArmor() {return SpelunkerArmor;}
    public float getSpelunkerArmorToughness() { return SpelunkerArmorToughness; }
    public float getSpelunkerArmorKnockback() { return SpelunkerArmorKnockback; }
    public int[] getCaveCrawlerArmor() {return CaveCrawlerArmor;}
    public float getCaveCrawlerArmorToughness() { return CaveCrawlerArmorToughness; }
    public float getCaveCrawlerArmorKnockback() { return CaveCrawlerArmorKnockback; }
    public int[] getWolfArmor() {return WolfArmor;}
    public float getWolfArmorToughness() { return WolfArmorToughness; }
    public float getWolfArmorKnockback() { return WolfArmorKnockback; }
    public int[] getBlackWolfArmor() {return BlackWolfArmor;}
    public float getBlackWolfArmorToughness() { return BlackWolfArmorToughness; }
    public float getBlackWolfArmorKnockback() { return BlackWolfArmorKnockback; }
    public int[] getFoxArmor() {return FoxArmor;}
    public float getFoxArmorToughness() { return FoxArmorToughness; }
    public float getFoxArmorKnockback() { return FoxArmorKnockback; }
    public int[] getClimbingGearArmor() {return ClimbingGear;}
    public float getClimbingGearKnockback() { return ClimbingGearKnockback; }
    public float getClimbingGearToughness() { return ClimbingGearToughness; }
    public int[] getRuggedClimbingGearArmor() {return RuggedClimbingGear;}
    public float getRuggedClimbingGearToughness() { return RuggedClimbingGearToughness; }
    public float getRuggedClimbingGearKnockback() { return RuggedClimbingGearKnockback; }
    public int[] getGoatArmor() {return GoatArmor;}
    public float getGoatArmorToughness() { return GoatArmorToughness; }
    public float getGoatArmorKnockback() { return GoatArmorKnockback; }

    public int[] getBattleRobe() {return BattleRobe;}
    public float getBattleRobeToughness() { return BattleRobeToughness; }
    public float getBattleRobeKnockback() { return BattleRobeKnockback; }
    public int[] getSplendidRobe() {return SplendidRobe;}
    public float getSplendidRobeToughness() { return SplendidRobeToughness; }
    public float getSplendidRobeKnockback() { return SplendidRobeKnockback; }
    public int[] getEvocationRobe(){return EvocationRobe;}
    public float getEvocationRobeToughness() { return EvocationRobeToughness; }
    public float getEvocationRobeKnockback() { return EvocationRobeKnockback; }
    public int[] getEmberRobe() {return EmberRobe;}
    public float getEmberRobeToughness() { return EmberRobeToughness; }
    public float getEmberRobeKnockback() { return EmberRobeKnockback; }
    public int[] getVerdantRobe() {return VerdantRobe;}
    public float getVerdantRobeToughness() { return VerdantRobeToughness; }
    public float getVerdantRobeKnockback() { return VerdantRobeKnockback; }
    public int[] getThiefArmor() {return ThiefArmor;}
    public float getThiefArmorToughness() { return ThiefArmorToughness; }
    public float getThiefArmorKnockback() { return ThiefArmorKnockback; }
    public int[] getSpiderArmor() {return SpiderArmor;}
    public float getSpiderArmorToughness() { return SpiderArmorToughness; }
    public float getSpiderArmorKnockback() { return SpiderArmorKnockback; }
    public int[] getSoulRobe() {return SoulRobe;}
    public float getSoulRobeToughness() { return SoulRobeToughness; }
    public float getSoulRobeKnockback() { return SoulRobeKnockback; }
    public int[] getSoulDancerRobe() {return SoulDancerRobe;}
    public float getSouldancerRobeToughness() { return SouldancerRobeToughness; }
    public float getSouldancerRobeKnockback() { return SouldancerRobeKnockback; }

    public int[] getPhantomArmor() {return PhantomArmor;}
    public float getPhantomArmorToughness() { return PhantomArmorToughness; }
    public float getPhantomArmorKnockback() { return PhantomArmorKnockback; }
    public int[] getFrostBiteArmor() {return FrostBiteArmor;}
    public float getFrostBiteArmorToughness() { return FrostBiteArmorToughness; }
    public float getFrostBiteArmorKnockback() { return FrostBiteArmorKnockback; }
    public int[] getGrimArmor() {return GrimArmor;}
    public float getGrimArmorToughness() { return GrimArmorToughness; }
    public float getGrimArmorKnockback() { return GrimArmorKnockback; }
    public int[] getWitherArmor() {return WitherArmor;}
    public float getWitherArmorToughness() { return WitherArmorToughness; }
    public float getWitherArmorKnockback() { return WitherArmorKnockback; }

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
    public int[] getTitanShroud() {return TitanShroud;}
    public int[] getRoyalGuard() {return RoyalGuard;}
    public int[] getPlateArmor() {return PlateArmor;}
    public int[] getFullMetalArmor() {return FullMetalArmor;}
    public int[] getMysteryArmor() {return MysteryArmor;}
    public int[] getChampionArmor() {return ChampionArmor;}
    public int[] getHerosArmor() {return HerosArmor;}
    public int[] getGildedGloryArmor() {return GildedGloryArmor;}
}

