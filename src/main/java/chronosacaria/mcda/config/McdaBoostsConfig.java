package chronosacaria.mcda.config;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "mcda_boosts")
public class McdaBoostsConfig implements ConfigData {

    static {
        AutoConfig.register(McdaBoostsConfig.class, JanksonConfigSerializer::new);
    }

    public static final McdaBoostsConfig config = AutoConfig.getConfigHolder(McdaBoostsConfig.class).getConfig();

    @Comment ("Battle Robe Boosts")
    public double BattleRobeSetAttackDamageBoost = 0.0D;
    public double BattleRobeSetAttackSpeedBoost = 0.0D;
    public double BattleRobeSetMovementBoost = 0.0D;

    @Comment ("Splendid Robe Boosts")
    public double SplendidRobeSetAttackDamageBoost = 0.075D;
    public double SplendidRobeSetAttackSpeedBoost = 0.0D;
    public double SplendidRobeSetMovementBoost = 0.0D;

    @Comment ("Beehive Armour Boosts")
    public double BeehiveArmourSetAttackDamageBoost = 0.00D;
    public double BeehiveArmourSetAttackSpeedBoost = 0.0D;
    public double BeehiveArmourSetMovementBoost = 0.0D;

    @Comment ("Beenest Armour Boosts")
    public double BeenestArmourSetAttackDamageBoost = 0.0375D;
    public double BeenestArmourSetAttackSpeedBoost = 0.0D;
    public double BeenestArmourSetMovementBoost = 0.0D;

    @Comment ("Hero Armour Boosts")
    public double ChampionArmourSetAttackDamageBoost = 0.0375D;
    public double ChampionArmourSetAttackSpeedBoost = 0.0D;
    public double ChampionArmourSetMovementBoost = 0.0D;

    @Comment ("Champion Armour Boosts")
    public double HeroArmourSetAttackDamageBoost = 0.075D;
    public double HeroArmourSetAttackSpeedBoost = 0.0375D;
    public double HeroArmourSetMovementBoost = 0.0D;

    @Comment ("Dark Armour Boosts")
    public double DarkArmourSetAttackDamageBoost = 0.0375D;
    public double DarkArmourSetAttackSpeedBoost = 0.0D;
    public double DarkArmourSetMovementBoost = 0.0D;

    @Comment ("Titan's Shroud Boosts")
    public double TitansShroudSetAttackDamageBoost = 0.075D;
    public double TitansShroudSetAttackSpeedBoost = 0.0375D;
    public double TitansShroudSetMovementBoost = 0.0D;

    @Comment ("Royal Armour Boosts")
    public double RoyalArmourSetAttackDamageBoost = 0.075D;
    public double RoyalArmourSetAttackSpeedBoost = 0.0375D;
    public double RoyalArmourSetMovementBoost = 0.0D;

    @Comment ("Evocation Robe Boosts")
    public double EvocationRobeSetAttackDamageBoost = 0.05D;
    public double EvocationRobeSetAttackSpeedBoost = 0.0D;
    public double EvocationRobeSetMovementBoost = 0.0D;

    @Comment ("Ember Robe Boosts")
    public double EmberRobeSetAttackDamageBoost = 0.05D;
    public double EmberRobeSetAttackSpeedBoost = 0.0D;
    public double EmberRobeSetMovementBoost = 0.0D;

    @Comment ("Verdant Robe Boosts")
    public double VerdantRobeSetAttackDamageBoost = 0.05D;
    public double VerdantRobeSetAttackSpeedBoost = 0.0D;
    public double VerdantRobeSetMovementBoost = 0.0D;

    @Comment ("Fox Armour Boosts")
    public double FoxArmourSetAttackDamageBoost = 0.025D;
    public double FoxArmourSetAttackSpeedBoost = 0.0D;
    public double FoxArmourSetMovementBoost = 0.0D;

    @Comment ("Arctic Fox Armour Boosts")
    public double ArcticFoxArmourSetAttackDamageBoost = 0.025D;
    public double ArcticFoxArmourSetAttackSpeedBoost = 0.0D;
    public double ArcticFoxArmourSetMovementBoost = 0.0D;

    @Comment ("Ghostly Armour Boosts")
    public double GhostlyArmourSetAttackDamageBoost = 0.0375D;
    public double GhostlyArmourSetAttackSpeedBoost = 0.0D;
    public double GhostlyArmourSetMovementBoost = 0.0D;

    @Comment ("Ghost Kindler Armour Boosts")
    public double GhostKindlerArmourSetAttackDamageBoost = 0.0375D;
    public double GhostKindlerArmourSetAttackSpeedBoost = 0.0D;
    public double GhostKindlerArmourSetMovementBoost = 0.0D;

    @Comment ("Grim Armour Boosts")
    public double GrimArmourSetAttackDamageBoost = 0.0375D;
    public double GrimArmourSetAttackSpeedBoost = 0.0D;
    public double GrimArmourSetMovementBoost = 0.0D;

    @Comment ("Wither Armour Boosts")
    public double WitherArmourSetAttackDamageBoost = 0.0375D;
    public double WitherArmourSetAttackSpeedBoost = 0.0D;
    public double WitherArmourSetMovementBoost = 0.0D;

    @Comment ("Guard's Armour Boosts")
    public double GuardsArmourSetAttackDamageBoost = 0.0D;
    public double GuardsArmourSetAttackSpeedBoost = 0.0D;
    public double GuardsArmourSetMovementBoost = 0.0D;

    @Comment ("Curious Armour Boosts")
    public double CuriousArmourSetAttackDamageBoost = 0.0D;
    public double CuriousArmourSetAttackSpeedBoost = 0.0D;
    public double CuriousArmourSetMovementBoost = 0.0D;

    @Comment ("Hunter's Armour Boosts")
    public double HuntersArmourSetAttackDamageBoost = 0.0D;
    public double HuntersArmourSetAttackSpeedBoost = 0.0D;
    public double HuntersArmourSetMovementBoost = 0.0D;

    @Comment ("Archer's Armour Boosts")
    public double ArchersArmourSetAttackDamageBoost = 0.0D;
    public double ArchersArmourSetAttackSpeedBoost = 0.0D;
    public double ArchersArmourSetMovementBoost = 0.0D;

    @Comment ("Mercenary Armour Boosts")
    public double MercenaryArmourSetAttackDamageBoost = 0.0D;
    public double MercenaryArmourSetAttackSpeedBoost = 0.0D;
    public double MercenaryArmourSetMovementBoost = 0.0D;

    @Comment ("Renegade Armour Boosts")
    public double RenegadeArmourSetAttackDamageBoost = 0.05D;
    public double RenegadeArmourSetAttackSpeedBoost = 0.0D;
    public double RenegadeArmourSetMovementBoost = 0.0D;

    @Comment ("Hungry Horror Armour Boosts")
    public double HungryHorrorArmourSetAttackDamageBoost = 0.05D;
    public double HungryHorrorArmourSetAttackSpeedBoost = 0.0D;
    public double HungryHorrorArmourSetMovementBoost = 0.0625D;

    @Comment ("White Mystery Armour Boosts")
    public double WhiteMysteryArmourSetAttackDamageBoost = 0.0D;
    public double WhiteMysteryArmourSetAttackSpeedBoost = 0.0D;
    public double WhiteMysteryArmourSetMovementBoost = 0.0D;

    @Comment ("Blue Mystery Armour Boosts")
    public double BlueMysteryArmourSetAttackDamageBoost = 0.0D;
    public double BlueMysteryArmourSetAttackSpeedBoost = 0.0D;
    public double BlueMysteryArmourSetMovementBoost = 0.0D;

    @Comment ("Green Mystery Armour Boosts")
    public double GreenMysteryArmourSetAttackDamageBoost = 0.0D;
    public double GreenMysteryArmourSetAttackSpeedBoost = 0.0D;
    public double GreenMysteryArmourSetMovementBoost = 0.0D;

    @Comment ("Purple Mystery Armour Boosts")
    public double PurpleMysteryArmourSetAttackDamageBoost = 0.0D;
    public double PurpleMysteryArmourSetAttackSpeedBoost = 0.0D;
    public double PurpleMysteryArmourSetMovementBoost = 0.0D;

    @Comment ("Red Mystery Armour Boosts")
    public double RedMysteryArmourSetAttackDamageBoost = 0.0D;
    public double RedMysteryArmourSetAttackSpeedBoost = 0.0D;
    public double RedMysteryArmourSetMovementBoost = 0.0D;

    @Comment ("Ocelot Armour Boosts")
    public double OcelotArmourSetAttackDamageBoost = 0.0D;
    public double OcelotArmourSetAttackSpeedBoost = 0.0D;
    public double OcelotArmourSetMovementBoost = 0.075D;

    @Comment ("Shadow Walker Armour Boosts")
    public double ShadowWalkerArmourSetAttackDamageBoost = 0.0D;
    public double ShadowWalkerArmourSetAttackSpeedBoost = 0.0D;
    public double ShadowWalkerArmourSetMovementBoost = 0.075D;

    @Comment ("Phantom Armour Boosts")
    public double PhantomArmourSetAttackDamageBoost = 0.075D;
    public double PhantomArmourSetAttackSpeedBoost = 0.0D;
    public double PhantomArmourSetMovementBoost = 0.0D;

    @Comment ("Frost Bite Armour Boosts")
    public double FrostBiteArmourSetAttackDamageBoost = 0.075D;
    public double FrostBiteArmourSetAttackSpeedBoost = 0.0D;
    public double FrostBiteArmourSetMovementBoost = 0.0D;

    @Comment ("Plate Armour Boosts")
    public double PlateArmourSetAttackDamageBoost = 0.05D;
    public double PlateArmourSetAttackSpeedBoost = 0.0D;

    @Comment ("Full Metal Armour Boosts")
    public double FullMetalArmourSetAttackDamageBoost = 0.05D;
    public double FullMetalArmourSetAttackSpeedBoost = 0.0D;

    @Comment ("Reinforced Mail Boosts")
    public double ReinforcedMailSetAttackDamageBoost = 0.0D;
    public double ReinforcedMailSetAttackSpeedBoost = 0.0D;
    public double ReinforcedMailSetMovementBoost = 0.0D;

    @Comment ("Stalwart Armour Boosts")
    public double StalwartArmourSetAttackDamageBoost = 0.0D;
    public double StalwartArmourSetAttackSpeedBoost = 0.0D;
    public double StalwartArmourSetMovementBoost = 0.0D;

    @Comment("Scale Mail Boosts")
    public double ScaleMailArmourSetAttackDamageBoost = 0.0375D;
    public double ScaleMailArmourSetAttackSpeedBoost = 0.0D;
    public double ScaleMailArmourSetMovementBoost = 0.0D;

    @Comment("Highland Armour Boosts")
    public double HighlandArmourSetAttackDamageBoost = 0.0375D;
    public double HighlandArmourSetAttackSpeedBoost = 0.0D;
    public double HighlandArmourSetMovementBoost = 0.0D;

    @Comment("Snow Armour Boosts")
    public double SnowArmourSetAttackDamageBoost = 0.0D;
    public double SnowArmourSetAttackSpeedBoost = 0.0D;
    public double SnowArmourSetMovementBoost = 0.0D;

    @Comment("Frost Armour Boosts")
    public double FrostArmourSetAttackDamageBoost = 0.0D;
    public double FrostArmourSetAttackSpeedBoost = 0.0D;
    public double FrostArmourSetMovementBoost = 0.0D;

    @Comment("Soul Robe Boosts")
    public double SoulRobeSetAttackDamageBoost = 0.0D;
    public double SoulRobeSetAttackSpeedBoost = 0.0D;
    public double SoulRobeSetMovementBoost = 0.0D;

    @Comment("Soul Dancer Robe Boosts")
    public double SoulDancerRobeSetAttackDamageBoost = 0.0D;
    public double SoulDancerRobeSetAttackSpeedBoost = 0.0D;
    public double SoulDancerRobeSetMovementBoost = 0.0D;

    @Comment("Spelunker's Armour Boosts")
    public double SpelunkerArmourSetAttackDamageBoost = 0.0D;
    public double SpelunkerArmourSetAttackSpeedBoost = 0.0D;
    public double SpelunkerArmourSetMovementBoost = 0.0D;

    @Comment("Cave Crawler Armour Boosts")
    public double CaveCrawlerArmourSetAttackDamageBoost = 0.0D;
    public double CaveCrawlerArmourSetAttackSpeedBoost = 0.0D;
    public double CaveCrawlerArmourSetMovementBoost = 0.025D;

    @Comment("Thief Armour Boosts")
    public double ThiefArmourSetAttackDamageBoost = 0.0D;
    public double ThiefArmourSetAttackSpeedBoost = 0.0D;
    public double ThiefArmourSetMovementBoost = 0.0D;

    @Comment("Spider Armour Boosts")
    public double SpiderArmourSetAttackDamageBoost = 0.0D;
    public double SpiderArmourSetAttackSpeedBoost = 0.0375D;
    public double SpiderArmourSetMovementBoost = 0.0D;

    @Comment("Wolf Armour Boosts")
    public double WolfArmourSetAttackDamageBoost = 0.025D;
    public double WolfArmourSetAttackSpeedBoost = 0.025D;
    public double WolfArmourSetMovementBoost = 0.0D;

    @Comment("Black Wolf Armour Boosts")
    public double BlackWolfArmourSetAttackDamageBoost = 0.025D;
    public double BlackWolfArmourSetAttackSpeedBoost = 0.025D;
    public double BlackWolfArmourSetMovementBoost = 0.0D;


    public double getBattleRobeSetAttackBoost(){return BattleRobeSetAttackDamageBoost;}
    public double getBattleRobeSetAttackSpeedBoost(){return BattleRobeSetAttackSpeedBoost;}
    public double getBattleRobeSetMovementBoost(){return BattleRobeSetMovementBoost;}

    public double getSplendidRobeSetAttackDamageBoost() {return SplendidRobeSetAttackDamageBoost;}
    public double getSplendidRobeSetAttackSpeedBoost() {return SplendidRobeSetAttackSpeedBoost;}
    public double getSplendidRobeSetMovementBoost() {return SplendidRobeSetMovementBoost;}

    public double getBeehiveArmourSetAttackBoost(){return BeehiveArmourSetAttackDamageBoost; }
    public double getBeehiveArmourSetAttackSpeedBoost(){return BeehiveArmourSetAttackSpeedBoost;}
    public double getBeehiveArmourSetMovementBoost(){return BeehiveArmourSetMovementBoost;}

    public double getBeenestArmourSetAttackDamageBoost() {return BeenestArmourSetAttackDamageBoost;}
    public double getBeenestArmourSetAttackSpeedBoost() {return BeenestArmourSetAttackSpeedBoost;}
    public double getBeenestArmourSetMovementBoost() {return BeenestArmourSetMovementBoost;}

    public double getHeroArmourSetAttackDamageBoost() {return HeroArmourSetAttackDamageBoost;}
    public double getHeroArmourSetAttackSpeedBoost() {return HeroArmourSetAttackSpeedBoost;}
    public double getHeroArmourSetMovementBoost() {return HeroArmourSetMovementBoost;}

    public double getChampionArmourSetAttackDamageBoost() {return ChampionArmourSetAttackDamageBoost;}
    public double getChampionArmourSetAttackSpeedBoost() {return ChampionArmourSetAttackSpeedBoost;}
    public double getChampionArmourSetMovementBoost() {return ChampionArmourSetMovementBoost;}

    public double getDarkArmourSetAttackDamageBoost() {return DarkArmourSetAttackDamageBoost;}
    public double getDarkArmourSetAttackSpeedBoost() {return DarkArmourSetAttackSpeedBoost;}
    public double getDarkArmourSetMovementBoost() {return DarkArmourSetMovementBoost;}

    public double getTitansShroudSetAttackDamageBoost() {return TitansShroudSetAttackDamageBoost;}
    public double getTitansShroudSetAttackSpeedBoost() {return TitansShroudSetAttackSpeedBoost;}
    public double getTitansShroudSetMovementBoost() {return TitansShroudSetMovementBoost;}

    public double getRoyalArmourSetAttackDamageBoost() {return RoyalArmourSetAttackDamageBoost;}
    public double getRoyalArmourSetAttackSpeedBoost() {return RoyalArmourSetAttackSpeedBoost;}
    public double getRoyalArmourSetMovementBoost() {return RoyalArmourSetMovementBoost;}

    public double getEvocationRobeSetAttackDamageBoost() {return EvocationRobeSetAttackDamageBoost; }
    public double getEvocationRobeSetAttackSpeedBoost() {return EvocationRobeSetAttackSpeedBoost; }
    public double getEvocationRobeSetMovementBoost() {return EvocationRobeSetMovementBoost; }

    public double getEmberRobeSetAttackDamageBoost() {return EmberRobeSetAttackDamageBoost; }
    public double getEmberRobeSetAttackSpeedBoost() {return EmberRobeSetAttackSpeedBoost; }
    public double getEmberRobeSetMovementBoost() {return EmberRobeSetMovementBoost; }

    public double getVerdantRobeSetAttackDamageBoost() {return VerdantRobeSetAttackDamageBoost; }
    public double getVerdantRobeSetAttackSpeedBoost() {return VerdantRobeSetAttackSpeedBoost; }
    public double getVerdantRobeSetMovementBoost() {return VerdantRobeSetMovementBoost; }

    public double getFoxArmourSetAttackDamageBoost() {return FoxArmourSetAttackDamageBoost; }
    public double getFoxArmourSetAttackSpeedBoost() {return FoxArmourSetAttackSpeedBoost; }
    public double getFoxArmourSetMovementBoost() {return FoxArmourSetMovementBoost; }

    public double getArcticFoxArmourSetAttackDamageBoost() {return ArcticFoxArmourSetAttackDamageBoost; }
    public double getArcticFoxArmourSetAttackSpeedBoost() {return ArcticFoxArmourSetAttackSpeedBoost; }
    public double getArcticFoxArmourSetMovementBoost() {return ArcticFoxArmourSetMovementBoost; }

    public double getGhostlyArmourSetAttackDamageBoost() {
        return GhostlyArmourSetAttackDamageBoost;
    }

    public double getGhostlyArmourSetAttackSpeedBoost() {
        return GhostlyArmourSetAttackSpeedBoost;
    }

    public double getGhostlyArmourSetMovementBoost() {
        return GhostlyArmourSetMovementBoost;
    }

    public double getGhostKindlerArmourSetAttackDamageBoost() {
        return GhostKindlerArmourSetAttackDamageBoost;
    }

    public double getGhostKindlerArmourSetAttackSpeedBoost() {
        return GhostKindlerArmourSetAttackSpeedBoost;
    }

    public double getGhostKindlerArmourSetMovementBoost() {
        return GhostKindlerArmourSetMovementBoost;
    }

    public double getGrimArmourSetAttackDamageBoost() {
        return GrimArmourSetAttackDamageBoost;
    }

    public double getGrimArmourSetAttackSpeedBoost() {
        return GrimArmourSetAttackSpeedBoost;
    }

    public double getGrimArmourSetMovementBoost() {
        return GrimArmourSetMovementBoost;
    }

    public double getWitherArmourSetAttackDamageBoost() {
        return WitherArmourSetAttackDamageBoost;
    }

    public double getWitherArmourSetAttackSpeedBoost() {
        return WitherArmourSetAttackSpeedBoost;
    }

    public double getWitherArmourSetMovementBoost() {
        return WitherArmourSetMovementBoost;
    }

    public double getGuardsArmourSetAttackDamageBoost() {
        return GuardsArmourSetAttackDamageBoost;
    }

    public double getGuardsArmourSetAttackSpeedBoost() {
        return GuardsArmourSetAttackSpeedBoost;
    }

    public double getGuardsArmourSetMovementBoost() {
        return GuardsArmourSetMovementBoost;
    }

    public double getCuriousArmourSetAttackDamageBoost() {
        return CuriousArmourSetAttackDamageBoost;
    }

    public double getCuriousArmourSetAttackSpeedBoost() {
        return CuriousArmourSetAttackSpeedBoost;
    }

    public double getCuriousArmourSetMovementBoost() {
        return CuriousArmourSetMovementBoost;
    }

    public double getHuntersArmourSetAttackDamageBoost() {
        return HuntersArmourSetAttackDamageBoost;
    }

    public double getHuntersArmourSetAttackSpeedBoost() {
        return HuntersArmourSetAttackSpeedBoost;
    }

    public double getHuntersArmourSetMovementBoost() {
        return HuntersArmourSetMovementBoost;
    }

    public double getArchersArmourSetAttackDamageBoost() {
        return ArchersArmourSetAttackDamageBoost;
    }

    public double getArchersArmourSetAttackSpeedBoost() {
        return ArchersArmourSetAttackSpeedBoost;
    }

    public double getArchersArmourSetMovementBoost() {
        return ArchersArmourSetMovementBoost;
    }

    public double getMercenaryArmourSetAttackDamageBoost() {
        return MercenaryArmourSetAttackDamageBoost;
    }

    public double getMercenaryArmourSetAttackSpeedBoost() {
        return MercenaryArmourSetAttackSpeedBoost;
    }

    public double getMercenaryArmourSetMovementBoost() {
        return MercenaryArmourSetMovementBoost;
    }

    public double getRenegadeArmourSetAttackDamageBoost() {
        return RenegadeArmourSetAttackDamageBoost;
    }

    public double getRenegadeArmourSetAttackSpeedBoost() {
        return RenegadeArmourSetAttackSpeedBoost;
    }

    public double getRenegadeArmourSetMovementBoost() {
        return RenegadeArmourSetMovementBoost;
    }

    public double getHungryHorrorArmourSetAttackDamageBoost() {
        return HungryHorrorArmourSetAttackDamageBoost;
    }

    public double getHungryHorrorArmourSetAttackSpeedBoost() {
        return HungryHorrorArmourSetAttackSpeedBoost;
    }

    public double getHungryHorrorArmourSetMovementBoost() {
        return HungryHorrorArmourSetMovementBoost;
    }

    public double getBlueMysteryArmourSetAttackDamageBoost() {
        return BlueMysteryArmourSetAttackDamageBoost;
    }

    public double getBlueMysteryArmourSetAttackSpeedBoost() {
        return BlueMysteryArmourSetAttackSpeedBoost;
    }

    public double getBlueMysteryArmourSetMovementBoost() {
        return BlueMysteryArmourSetMovementBoost;
    }

    public double getGreenMysteryArmourSetAttackDamageBoost() {
        return GreenMysteryArmourSetAttackDamageBoost;
    }

    public double getGreenMysteryArmourSetAttackSpeedBoost() {
        return GreenMysteryArmourSetAttackSpeedBoost;
    }

    public double getGreenMysteryArmourSetMovementBoost() {
        return GreenMysteryArmourSetMovementBoost;
    }

    public double getPurpleMysteryArmourSetAttackDamageBoost() {
        return PurpleMysteryArmourSetAttackDamageBoost;
    }

    public double getPurpleMysteryArmourSetAttackSpeedBoost() {
        return PurpleMysteryArmourSetAttackSpeedBoost;
    }

    public double getPurpleMysteryArmourSetMovementBoost() {
        return PurpleMysteryArmourSetMovementBoost;
    }

    public double getRedMysteryArmourSetAttackDamageBoost() {
        return RedMysteryArmourSetAttackDamageBoost;
    }

    public double getRedMysteryArmourSetAttackSpeedBoost() {
        return RedMysteryArmourSetAttackSpeedBoost;
    }

    public double getRedMysteryArmourSetMovementBoost() {
        return RedMysteryArmourSetMovementBoost;
    }

    public double getWhiteMysteryArmourSetAttackDamageBoost() {
        return WhiteMysteryArmourSetAttackDamageBoost;
    }

    public double getWhiteMysteryArmourSetAttackSpeedBoost() {
        return WhiteMysteryArmourSetAttackSpeedBoost;
    }

    public double getWhiteMysteryArmourSetMovementBoost() {
        return WhiteMysteryArmourSetMovementBoost;
    }

    public double getOcelotArmourSetAttackDamageBoost() {
        return OcelotArmourSetAttackDamageBoost;
    }

    public double getOcelotArmourSetAttackSpeedBoost() {
        return OcelotArmourSetAttackSpeedBoost;
    }

    public double getOcelotArmourSetMovementBoost() {
        return OcelotArmourSetMovementBoost;
    }

    public double getShadowWalkerArmourSetAttackDamageBoost() {
        return ShadowWalkerArmourSetAttackDamageBoost;
    }

    public double getShadowWalkerArmourSetAttackSpeedBoost() {
        return ShadowWalkerArmourSetAttackSpeedBoost;
    }

    public double getShadowWalkerArmourSetMovementBoost() {
        return ShadowWalkerArmourSetMovementBoost;
    }

    public double getPhantomArmourSetAttackDamageBoost() {
        return PhantomArmourSetAttackDamageBoost;
    }

    public double getPhantomArmourSetAttackSpeedBoost() {
        return PhantomArmourSetAttackSpeedBoost;
    }

    public double getPhantomArmourSetMovementBoost() {
        return PhantomArmourSetMovementBoost;
    }

    public double getFrostBiteArmourSetAttackDamageBoost() {
        return FrostBiteArmourSetAttackDamageBoost;
    }

    public double getFrostBiteArmourSetAttackSpeedBoost(){
        return FrostBiteArmourSetAttackSpeedBoost;
    }

    public double getFrostBiteArmourSetMovementBoost(){
        return FrostBiteArmourSetMovementBoost;
    }

    public double getPlateArmourSetAttackDamageBoost(){
        return PlateArmourSetAttackDamageBoost;
    }

    public double getPlateArmourSetAttackSpeedBoost() {
        return PlateArmourSetAttackSpeedBoost;
    }

    public double getFullMetalArmourSetAttackDamageBoost(){
        return FullMetalArmourSetAttackDamageBoost;
    }

    public double getFullMetalArmourSetAttackSpeedBoost() {
        return FullMetalArmourSetAttackSpeedBoost;
    }

    public double getReinforcedMailSetAttackDamageBoost(){
        return ReinforcedMailSetAttackDamageBoost;
    }

    public double getReinforcedMailSetAttackSpeedBoost(){
        return ReinforcedMailSetAttackSpeedBoost;
    }

    public double getReinforcedMailSetMovementBoost(){
        return ReinforcedMailSetMovementBoost;
    }

    public double getStalwartArmourSetAttackDamageBoost(){
        return StalwartArmourSetAttackDamageBoost;
    }

    public double getStalwartArmourSetAttackSpeedBoost(){
        return StalwartArmourSetAttackSpeedBoost;
    }
    public double getStalwartArmourSetMovementBoost(){
        return StalwartArmourSetMovementBoost;
    }

    public double getScaleMailArmourSetAttackDamageBoost() {
        return ScaleMailArmourSetAttackDamageBoost;
    }

    public double getScaleMailArmourSetAttackSpeedBoost() {
        return ScaleMailArmourSetAttackSpeedBoost;
    }

    public double getScaleMailArmourSetMovementBoost() {
        return ScaleMailArmourSetMovementBoost;
    }

    public double getHighlandArmourSetAttackDamageBoost() {
        return HighlandArmourSetAttackDamageBoost;
    }

    public double getHighlandArmourSetAttackSpeedBoost() {
        return HighlandArmourSetAttackSpeedBoost;
    }

    public double getHighlandArmourSetMovementBoost() {
        return HighlandArmourSetMovementBoost;
    }

    public double getSnowArmourSetAttackDamageBoost() {
        return SnowArmourSetAttackDamageBoost;
    }

    public double getSnowArmourSetAttackSpeedBoost() {
        return SnowArmourSetAttackSpeedBoost;
    }

    public double getSnowArmourSetMovementBoost() {
        return SnowArmourSetMovementBoost;
    }

    public double getFrostArmourSetAttackDamageBoost() {
        return FrostArmourSetAttackDamageBoost;
    }

    public double getFrostArmourSetAttackSpeedBoost() {
        return FrostArmourSetAttackSpeedBoost;
    }

    public double getFrostArmourSetMovementBoost() {
        return FrostArmourSetMovementBoost;
    }

    public double getSoulRobeSetAttackDamageBoost() {
        return SoulRobeSetAttackDamageBoost;
    }

    public double getSoulRobeSetAttackSpeedBoost() {
        return SoulRobeSetAttackSpeedBoost;
    }

    public double getSoulRobeSetMovementBoost() {
        return SoulRobeSetMovementBoost;
    }

    public double getSoulDancerRobeSetAttackDamageBoost() {
        return SoulDancerRobeSetAttackDamageBoost;
    }

    public double getSoulDancerRobeSetAttackSpeedBoost() {
        return SoulDancerRobeSetAttackSpeedBoost;
    }

    public double getSoulDancerRobeSetMovementBoost() {
        return SoulDancerRobeSetMovementBoost;
    }

    public double getSpelunkerArmourSetAttackDamageBoost() {
        return SpelunkerArmourSetAttackDamageBoost;
    }

    public double getSpelunkerArmourSetAttackSpeedBoost() {
        return SpelunkerArmourSetAttackSpeedBoost;
    }

    public double getSpelunkerArmourSetMovementBoost() {
        return SpelunkerArmourSetMovementBoost;
    }

    public double getCaveCrawlerArmourSetAttackDamageBoost() {
        return CaveCrawlerArmourSetAttackDamageBoost;
    }

    public double getCaveCrawlerArmourSetAttackSpeedBoost() {
        return CaveCrawlerArmourSetAttackSpeedBoost;
    }

    public double getCaveCrawlerArmourSetMovementBoost() {
        return CaveCrawlerArmourSetMovementBoost;
    }

    public double getThiefArmourSetAttackDamageBoost() {
        return ThiefArmourSetAttackDamageBoost;
    }

    public double getThiefArmourSetAttackSpeedBoost() {
        return ThiefArmourSetAttackSpeedBoost;
    }

    public double getThiefArmourSetMovementBoost() {
        return ThiefArmourSetMovementBoost;
    }

    public double getSpiderArmourSetAttackDamageBoost() {
        return SpiderArmourSetAttackDamageBoost;
    }

    public double getSpiderArmourSetAttackSpeedBoost() {
        return SpiderArmourSetAttackSpeedBoost;
    }

    public double getSpiderArmourSetMovementBoost() {
        return SpiderArmourSetMovementBoost;
    }

    public double getWolfArmourSetAttackDamageBoost() {
        return WolfArmourSetAttackDamageBoost;
    }

    public double getWolfArmourSetAttackSpeedBoost() {
        return WolfArmourSetAttackSpeedBoost;
    }

    public double getWolfArmourSetMovementBoost() {
        return WolfArmourSetMovementBoost;
    }

    public double getBlackWolfArmourSetAttackDamageBoost() {
        return BlackWolfArmourSetAttackDamageBoost;
    }

    public double getBlackWolfArmourSetAttackSpeedBoost() {
        return BlackWolfArmourSetAttackSpeedBoost;
    }

    public double getBlackWolfArmourSetMovementBoost() {
        return BlackWolfArmourSetMovementBoost;
    }
}

