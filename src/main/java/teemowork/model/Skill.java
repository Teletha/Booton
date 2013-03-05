/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package teemowork.model;

import static teemowork.model.SkillKey.*;
import static teemowork.model.Status.*;
import static teemowork.model.Version.*;
import teemowork.model.variable.VariableHolder;
import teemowork.model.variable.VariableResolver;
import teemowork.model.variable.VariableResolver.Diff;
import teemowork.model.variable.VariableResolver.Fixed;
import teemowork.model.variable.VariableResolver.Per1Level;
import teemowork.model.variable.VariableResolver.Per3Level;
import teemowork.model.variable.VariableResolver.Per3LevelAdditional;
import teemowork.model.variable.VariableResolver.Per3LevelForKarma;
import teemowork.model.variable.VariableResolver.Per4Level;
import teemowork.model.variable.VariableResolver.Per4LevelForTrundle;
import teemowork.model.variable.VariableResolver.Per5Level;
import teemowork.model.variable.VariableResolver.Per6Level;

/**
 * @version 2013/03/02 11:34:54
 */
public class Skill {

    /** The skill name. */
    public static final Skill EssenceTheft = new Skill("Essence Theft", Passive);

    /** The skill name. */
    public static final Skill OrbOfDeception = new Skill("Orb of Deception", Q);

    /** The skill name. */
    public static final Skill FoxFire = new Skill("Fox-Fire", W);

    /** The skill name. */
    public static final Skill Charm = new Skill("Charm", E);

    /** The skill name. */
    public static final Skill SpiritRush = new Skill("Spirit Rush", R);

    /** The skill name. */
    public static final Skill TwinDisciplines = new Skill("Twin Disciplines", Passive);

    /** The skill name. */
    public static final Skill MarkOftheAssassin = new Skill("Mark of the Assassin", Q);

    /** The skill name. */
    public static final Skill TwilightShroud = new Skill("Twilight Shroud", W);

    /** The skill name. */
    public static final Skill CrescentSlash = new Skill("Crescent Slash", E);

    /** The skill name. */
    public static final Skill ShadowDance = new Skill("Shadow Dance", R);

    /** The skill name. */
    public static final Skill Trample = new Skill("Trample", Passive);

    /** The skill name. */
    public static final Skill Pulverize = new Skill("Pulverize", Q);

    /** The skill name. */
    public static final Skill Headbutt = new Skill("Headbutt", W);

    /** The skill name. */
    public static final Skill TriumphantRoar = new Skill("Triumphant Roar", E);

    /** The skill name. */
    public static final Skill UnbreakableWill = new Skill("Unbreakable Will", R);

    /** The skill name. */
    public static final Skill CursedTouch = new Skill("Cursed Touch", Passive);

    /** The skill name. */
    public static final Skill BandageToss = new Skill("Bandage Toss", Q);

    /** The skill name. */
    public static final Skill Despair = new Skill("Despair", W);

    /** The skill name. */
    public static final Skill Tantrum = new Skill("Tantrum", E);

    /** The skill name. */
    public static final Skill CurseOftheSadMummy = new Skill("Curse of the Sad Mummy", R);

    /** The skill name. */
    public static final Skill Rebirth = new Skill("Rebirth", Passive);

    /** The skill name. */
    public static final Skill FlashFrost = new Skill("Flash Frost", Q);

    /** The skill name. */
    public static final Skill Crystalize = new Skill("Crystalize", W);

    /** The skill name. */
    public static final Skill Frostbite = new Skill("Frostbite", E);

    /** The skill name. */
    public static final Skill GlacialStorm = new Skill("Glacial Storm", R);

    /** The skill name. */
    public static final Skill Pyromania = new Skill("Pyromania", Passive);

    /** The skill name. */
    public static final Skill Disintegrate = new Skill("Disintegrate", Q);

    /** The skill name. */
    public static final Skill Incinerate = new Skill("Incinerate", W);

    /** The skill name. */
    public static final Skill MoltenShield = new Skill("Molten Shield", E);

    /** The skill name. */
    public static final Skill SummonTibbers = new Skill("Summon: Tibbers", R);

    /** The skill name. */
    public static final Skill Focus = new Skill("Focus", Passive);

    /** The skill name. */
    public static final Skill FrostShot = new Skill("Frost Shot", Q);

    /** The skill name. */
    public static final Skill Volley = new Skill("Volley", W);

    /** The skill name. */
    public static final Skill Hawkshot = new Skill("Hawkshot", E);

    /** The skill name. */
    public static final Skill EnchantedCrystalArrow = new Skill("Enchanted Crystal Arrow", R);

    /** The skill name. */
    public static final Skill ManaBarrier = new Skill("Mana Barrier", Passive);

    /** The skill name. */
    public static final Skill RocketGrab = new Skill("Rocket Grab", Q);

    /** The skill name. */
    public static final Skill Overdrive = new Skill("Overdrive", W);

    /** The skill name. */
    public static final Skill PowerFist = new Skill("Power Fist", E);

    /** The skill name. */
    public static final Skill StaticField = new Skill("Static Field", R);

    /** The skill name. */
    public static final Skill Blaze = new Skill("Blaze", Passive);

    /** The skill name. */
    public static final Skill Sear = new Skill("Sear", Q);

    /** The skill name. */
    public static final Skill PillarOfFlame = new Skill("Pillar of Flame", W);

    /** The skill name. */
    public static final Skill Conflagration = new Skill("Conflagration", E);

    /** The skill name. */
    public static final Skill Pyroclasm = new Skill("Pyroclasm", R);

    /** The skill name. */
    public static final Skill Headshot = new Skill("Headshot", Passive);

    /** The skill name. */
    public static final Skill PiltoverPeacemaker = new Skill("Piltover Peacemaker", Q);

    /** The skill name. */
    public static final Skill YordleSnapTrap = new Skill("Yordle Snap Trap", W);

    /** The skill name. */
    public static final Skill CaliberNet = new Skill("90 Caliber Net", E);

    /** The skill name. */
    public static final Skill AceinTheHole = new Skill("Ace in the Hole", R);

    /** The skill name. */
    public static final Skill DeadlyCadence = new Skill("Deadly Cadence", Passive);

    /** The skill name. */
    public static final Skill NoxiousBlast = new Skill("Noxious Blast", Q);

    /** The skill name. */
    public static final Skill Miasma = new Skill("Miasma", W);

    /** The skill name. */
    public static final Skill TwinFang = new Skill("Twin Fang", E);

    /** The skill name. */
    public static final Skill PetrifyingGaze = new Skill("Petrifying Gaze", R);

    /** The skill name. */
    public static final Skill Carnivore = new Skill("Carnivore", Passive);

    /** The skill name. */
    public static final Skill Rupture = new Skill("Rupture", Q);

    /** The skill name. */
    public static final Skill FeralScream = new Skill("Feral Scream", W);

    /** The skill name. */
    public static final Skill VorpalSpikes = new Skill("Vorpal Spikes", E);

    /** The skill name. */
    public static final Skill Feast = new Skill("Feast", R);

    /** The skill name. */
    public static final Skill HextechShrapnelShells = new Skill("Hextech Shrapnel Shells", Passive);

    /** The skill name. */
    public static final Skill PhosphorusBomb = new Skill("Phosphorus Bomb", Q);

    /** The skill name. */
    public static final Skill Valkyrie = new Skill("Valkyrie", W);

    /** The skill name. */
    public static final Skill GatlingGun = new Skill("Gatling Gun", E);

    /** The skill name. */
    public static final Skill MissileBarrage = new Skill("Missile Barrage", R);

    /** The skill name. */
    public static final Skill Hemorrhage = new Skill("Hemorrhage", Passive);

    /** The skill name. */
    public static final Skill Decimate = new Skill("Decimate", Q);

    /** The skill name. */
    public static final Skill CripplingStrike = new Skill("Crippling Strike", W);

    /** The skill name. */
    public static final Skill Apprehend = new Skill("Apprehend", E);

    /** The skill name. */
    public static final Skill NoxianGuillotine = new Skill("Noxian Guillotine", R);

    /** The skill name. */
    public static final Skill MoonsilverBlade = new Skill("Moonsilver Blade", Passive);

    /** The skill name. */
    public static final Skill CrescentStrike = new Skill("Crescent Strike", Q);

    /** The skill name. */
    public static final Skill PaleCascade = new Skill("Pale Cascade", W);

    /** The skill name. */
    public static final Skill Moonfall = new Skill("Moonfall", E);

    /** The skill name. */
    public static final Skill LunarRush = new Skill("Lunar Rush", R);

    /** The skill name. */
    public static final Skill AdrenalineRush = new Skill("Adrenaline Rush", Passive);

    /** The skill name. */
    public static final Skill InfectedCleaver = new Skill("Infected Cleaver", Q);

    /** The skill name. */
    public static final Skill BurningAgony = new Skill("Burning Agony", W);

    /** The skill name. */
    public static final Skill Masochism = new Skill("Masochism", E);

    /** The skill name. */
    public static final Skill Sadism = new Skill("Sadism", R);

    /** The skill name. */
    public static final Skill WickedBlades = new Skill("Wicked Blades", Passive);

    /** The skill name. */
    public static final Skill SpinningAxe = new Skill("Spinning Axe", Q);

    /** The skill name. */
    public static final Skill BloodRush = new Skill("Blood Rush", W);

    /** The skill name. */
    public static final Skill StandAside = new Skill("Stand Aside", E);

    /** The skill name. */
    public static final Skill WhirlingDeath = new Skill("Whirling Death", R);

    /** The skill name. */
    public static final Skill SpiderSwarm = new Skill("Spider Swarm", Passive);

    /** The skill name. */
    public static final Skill Neurotoxin = new Skill("Neurotoxin", Q);

    /** The skill name. */
    public static final Skill VenomousBite = new Skill("Venomous Bite", Q);

    /** The skill name. */
    public static final Skill VolatileSpiderling = new Skill("Volatile Spiderling", W);

    /** The skill name. */
    public static final Skill SkitteringFrenzy = new Skill("Skittering Frenzy", W);

    /** The skill name. */
    public static final Skill Cocoon = new Skill("Cocoon", E);

    /** The skill name. */
    public static final Skill Rappel = new Skill("Rappel", E);

    /** The skill name. */
    public static final Skill SpiderForm = new Skill("Spider Form", R);

    /** The skill name. */
    public static final Skill HumanForm = new Skill("Human Form", R);

    /** The skill name. */
    public static final Skill ShadowWalk = new Skill("Shadow Walk", Passive);

    /** The skill name. */
    public static final Skill HateSpike = new Skill("Hate Spike", Q);

    /** The skill name. */
    public static final Skill DarkFrenzy = new Skill("Dark Frenzy", W);

    /** The skill name. */
    public static final Skill Ravage = new Skill("Ravage", E);

    /** The skill name. */
    public static final Skill AgonysEmbrace = new Skill("Agony's Embrace", R);

    /** The skill name. */
    public static final Skill RisingSpellForce = new Skill("Rising Spell Force", Passive);

    /** The skill name. */
    public static final Skill MysticShot = new Skill("Mystic Shot", Q);

    /** The skill name. */
    public static final Skill EssenceFlux = new Skill("Essence Flux", W);

    /** The skill name. */
    public static final Skill ArcaneShift = new Skill("Arcane Shift", E);

    /** The skill name. */
    public static final Skill TrueshotBarrage = new Skill("Trueshot Barrage", R);

    /** The skill name. */
    public static final Skill Dread = new Skill("Dread", Passive);

    /** The skill name. */
    public static final Skill Terrify = new Skill("Terrify", Q);

    /** The skill name. */
    public static final Skill Drain = new Skill("Drain", W);

    /** The skill name. */
    public static final Skill DarkWind = new Skill("Dark Wind", E);

    /** The skill name. */
    public static final Skill Crowstorm = new Skill("Crowstorm", R);

    /** The skill name. */
    public static final Skill Duelist = new Skill("Duelist", Passive);

    /** The skill name. */
    public static final Skill Lunge = new Skill("Lunge", Q);

    /** The skill name. */
    public static final Skill Riposte = new Skill("Riposte", W);

    /** The skill name. */
    public static final Skill BurstOfSpeed = new Skill("Burst of Speed", E);

    /** The skill name. */
    public static final Skill BladeWaltz = new Skill("Blade Waltz", R);

    /** The skill name. */
    public static final Skill NimbleFighter = new Skill("Nimble Fighter", Passive);

    /** The skill name. */
    public static final Skill UrchinStrike = new Skill("Urchin Strike", Q);

    /** The skill name. */
    public static final Skill SeastoneTrident = new Skill("Seastone Trident", W);

    /** The skill name. */
    public static final Skill Playful = new Skill("Playful", E);

    /** The skill name. */
    public static final Skill Trickster = new Skill("Trickster", E);

    /** The skill name. */
    public static final Skill ChumTheWaters = new Skill("Chum the Waters", R);

    /** The skill name. */
    public static final Skill RunicSkin = new Skill("Runic Skin", Passive);

    /** The skill name. */
    public static final Skill ResoluteSmite = new Skill("Resolute Smite", Q);

    /** The skill name. */
    public static final Skill Bulwark = new Skill("Bulwark", W);

    /** The skill name. */
    public static final Skill RighteousGust = new Skill("Righteous Gust", E);

    /** The skill name. */
    public static final Skill IdolOfDurand = new Skill("Idol of Durand", R);

    /** The skill name. */
    public static final Skill GrogSoakedBlade = new Skill("Grog Soaked Blade", Passive);

    /** The skill name. */
    public static final Skill Parrrley = new Skill("Parrrley", Q);

    /** The skill name. */
    public static final Skill RemoveScurvy = new Skill("Remove Scurvy", W);

    /** The skill name. */
    public static final Skill RaiseMorale = new Skill("Raise Morale", E);

    /** The skill name. */
    public static final Skill CannonBarrage = new Skill("Cannon Barrage", R);

    /** The skill name. */
    public static final Skill Perseverance = new Skill("Perseverance", Passive);

    /** The skill name. */
    public static final Skill DecisiveStrike = new Skill("Decisive Strike", Q);

    /** The skill name. */
    public static final Skill Courage = new Skill("Courage", W);

    /** The skill name. */
    public static final Skill Judgment = new Skill("Judgment", E);

    /** The skill name. */
    public static final Skill DemacianJustice = new Skill("Demacian Justice", R);

    /** The skill name. */
    public static final Skill HappyHour = new Skill("Happy Hour", Passive);

    /** The skill name. */
    public static final Skill BarrelRoll = new Skill("Barrel Roll", Q);

    /** The skill name. */
    public static final Skill DrunkenRage = new Skill("Drunken Rage", W);

    /** The skill name. */
    public static final Skill BodySlam = new Skill("Body Slam", E);

    /** The skill name. */
    public static final Skill ExplosiveCask = new Skill("Explosive Cask", R);

    /** The skill name. */
    public static final Skill TrueGrit = new Skill("True Grit", Passive);

    /** The skill name. */
    public static final Skill Buckshot = new Skill("Buckshot", Q);

    /** The skill name. */
    public static final Skill Smokescreen = new Skill("Smokescreen", W);

    /** The skill name. */
    public static final Skill Quickdraw = new Skill("Quickdraw", E);

    /** The skill name. */
    public static final Skill CollateralDamage = new Skill("Collateral Damage", R);

    /** The skill name. */
    public static final Skill Warpath = new Skill("Warpath", Passive);

    /** The skill name. */
    public static final Skill Rampage = new Skill("Rampage", Q);

    /** The skill name. */
    public static final Skill SpiritOfDread = new Skill("Spirit of Dread", W);

    /** The skill name. */
    public static final Skill DevastatingCharge = new Skill("Devastating Charge", E);

    /** The skill name. */
    public static final Skill OnslaughtOfShadows = new Skill("Onslaught of Shadows", R);

    /** The skill name. */
    public static final Skill TechmaturgicalRepairBots = new Skill("Techmaturgical Repair Bots", Passive);

    /** The skill name. */
    public static final Skill H28GEvolutionTurret = new Skill("H-28G Evolution Turret", Q);

    /** The skill name. */
    public static final Skill HextechMicroRockets = new Skill("Hextech Micro-Rockets", W);

    /** The skill name. */
    public static final Skill CH1ConcussionGrenade = new Skill("CH-1 Concussion Grenade", E);

    /** The skill name. */
    public static final Skill UPGRADE = new Skill("UPGRADE!!!", R);

    /** The skill name. */
    public static final Skill IonianFervor = new Skill("Ionian Fervor", Passive);

    /** The skill name. */
    public static final Skill Bladesurge = new Skill("Bladesurge", Q);

    /** The skill name. */
    public static final Skill HitenStyle = new Skill("Hiten Style", W);

    /** The skill name. */
    public static final Skill EquilibriumStrike = new Skill("Equilibrium Strike", E);

    /** The skill name. */
    public static final Skill TranscendentBlades = new Skill("Transcendent Blades", R);

    /** The skill name. */
    public static final Skill Tailwind = new Skill("Tailwind", Passive);

    /** The skill name. */
    public static final Skill HowlingGale = new Skill("Howling Gale", Q);

    /** The skill name. */
    public static final Skill Zephyr = new Skill("Zephyr", W);

    /** The skill name. */
    public static final Skill EyeOfTheStorm = new Skill("Eye Of The Storm", E);

    /** The skill name. */
    public static final Skill Monsoon = new Skill("Monsoon", R);

    /** The skill name. */
    public static final Skill MartialCadence = new Skill("Martial Cadence", Passive);

    /** The skill name. */
    public static final Skill DragonStrike = new Skill("Dragon Strike", Q);

    /** The skill name. */
    public static final Skill GoldenAegis = new Skill("Golden Aegis", W);

    /** The skill name. */
    public static final Skill DemacianStandard = new Skill("Demacian Standard", E);

    /** The skill name. */
    public static final Skill Cataclysm = new Skill("Cataclysm", R);

    /** The skill name. */
    public static final Skill RelentlessAssault = new Skill("Relentless Assault", Passive);

    /** The skill name. */
    public static final Skill LeapStrike = new Skill("Leap Strike", Q);

    /** The skill name. */
    public static final Skill Empower = new Skill("Empower", W);

    /** The skill name. */
    public static final Skill CounterStrike = new Skill("Counter Strike", E);

    /** The skill name. */
    public static final Skill GrandmastersMight = new Skill("Grandmaster's Might", R);

    /** The skill name. */
    public static final Skill HextechCapacitor = new Skill("Hextech Capacitor", Passive);

    /** The skill name. */
    public static final Skill ToTheSkies = new Skill("To the Skies!", Q);

    /** The skill name. */
    public static final Skill ShockBlast = new Skill("Shock Blast", Q);

    /** The skill name. */
    public static final Skill LightningField = new Skill("Lightning Field", W);

    /** The skill name. */
    public static final Skill HyperCharge = new Skill("Hyper Charge", W);

    /** The skill name. */
    public static final Skill ThunderingBlow = new Skill("Thundering Blow", E);

    /** The skill name. */
    public static final Skill AccelerationGate = new Skill("Acceleration Gate", E);

    /** The skill name. */
    public static final Skill TransformMercuryCannon = new Skill("Transform: Mercury Cannon", R);

    /** The skill name. */
    public static final Skill TransformMercuryHammer = new Skill("Transform: Mercury Hammer", R);

    /** The skill name. */
    public static final Skill InnerFlame = new Skill("Inner Flame", Passive);

    /** The skill name. */
    public static final Skill HeavenlyWave = new Skill("Heavenly Wave", Q);

    /** The skill name. */
    public static final Skill SpiritBond = new Skill("Spirit Bond", W);

    /** The skill name. */
    public static final Skill SoulShield = new Skill("Soul Shield", E);

    /** The skill name. */
    public static final Skill Mantra = new Skill("Mantra", R);

    /** The skill name. */
    public static final Skill DeathDefied = new Skill("Death Defied", Passive);

    /** The skill name. */
    public static final Skill LayWaste = new Skill("Lay Waste", Q);

    /** The skill name. */
    public static final Skill WallOfPain = new Skill("Wall of Pain", W);

    /** The skill name. */
    public static final Skill Defile = new Skill("Defile", E);

    /** The skill name. */
    public static final Skill Requiem = new Skill("Requiem", R);

    /** The skill name. */
    public static final Skill VoidStone = new Skill("Void Stone", Passive);

    /** The skill name. */
    public static final Skill NullSphere = new Skill("Null Sphere", Q);

    /** The skill name. */
    public static final Skill NetherBlade = new Skill("Nether Blade", W);

    /** The skill name. */
    public static final Skill ForcePulse = new Skill("Force Pulse", E);

    /** The skill name. */
    public static final Skill Riftwalk = new Skill("Riftwalk", R);

    /** The skill name. */
    public static final Skill Voracity = new Skill("Voracity", Passive);

    /** The skill name. */
    public static final Skill BouncingBlade = new Skill("Bouncing Blade", Q);

    /** The skill name. */
    public static final Skill SinisterSteel = new Skill("Sinister Steel", W);

    /** The skill name. */
    public static final Skill Shunpo = new Skill("Shunpo", E);

    /** The skill name. */
    public static final Skill DeathLotus = new Skill("Death Lotus", R);

    /** The skill name. */
    public static final Skill HolyFervor = new Skill("Holy Fervor", Passive);

    /** The skill name. */
    public static final Skill Reckoning = new Skill("Reckoning", Q);

    /** The skill name. */
    public static final Skill DivineBlessing = new Skill("Divine Blessing", W);

    /** The skill name. */
    public static final Skill RighteousFury = new Skill("Righteous Fury", E);

    /** The skill name. */
    public static final Skill Intervention = new Skill("Intervention", R);

    /** The skill name. */
    public static final Skill MarkOftheStorm = new Skill("Mark of the Storm", Passive);

    /** The skill name. */
    public static final Skill ThunderingShuriken = new Skill("Thundering Shuriken", Q);

    /** The skill name. */
    public static final Skill ElectricalSurge = new Skill("Electrical Surge", W);

    /** The skill name. */
    public static final Skill LightningRush = new Skill("Lightning Rush", E);

    /** The skill name. */
    public static final Skill SlicingMaelstrom = new Skill("Slicing Maelstrom", R);

    /** The skill name. */
    public static final Skill UnseenThreat = new Skill("Unseen Threat", Passive);

    /** The skill name. */
    public static final Skill TasteTheirFear = new Skill("Taste Their Fear", Q);

    /** The skill name. */
    public static final Skill VoidSpike = new Skill("Void Spike", W);

    /** The skill name. */
    public static final Skill Leap = new Skill("Leap", E);

    /** The skill name. */
    public static final Skill VoidAssault = new Skill("Void Assault", R);

    /** The skill name. */
    public static final Skill IcathianSurprise = new Skill("Icathian Surprise", Passive);

    /** The skill name. */
    public static final Skill CausticSpittle = new Skill("Caustic Spittle", Q);

    /** The skill name. */
    public static final Skill BioArcaneBarrage = new Skill("Bio-Arcane Barrage", W);

    /** The skill name. */
    public static final Skill VoidOoze = new Skill("Void Ooze", E);

    /** The skill name. */
    public static final Skill LivingArtillery = new Skill("Living Artillery", R);

    /** The skill name. */
    public static final Skill MirrorImage = new Skill("Mirror Image", Passive);

    /** The skill name. */
    public static final Skill SigilOfSilence = new Skill("Sigil of Silence", Q);

    /** The skill name. */
    public static final Skill Distortion = new Skill("Distortion", W);

    /** The skill name. */
    public static final Skill EtherealChains = new Skill("Ethereal Chains", E);

    /** The skill name. */
    public static final Skill Mimic = new Skill("Mimic", R);

    /** The skill name. */
    public static final Skill Flurry = new Skill("Flurry", Passive);

    /** The skill name. */
    public static final Skill SonicWave = new Skill("Sonic Wave", Q);

    /** The skill name. */
    public static final Skill ResonatingStrike = new Skill("Resonating Strike", Q);

    /** The skill name. */
    public static final Skill Safeguard = new Skill("Safeguard", W);

    /** The skill name. */
    public static final Skill IronWill = new Skill("Iron Will", W);

    /** The skill name. */
    public static final Skill Tempest = new Skill("Tempest", E);

    /** The skill name. */
    public static final Skill Cripple = new Skill("Cripple", E);

    /** The skill name. */
    public static final Skill DragonsRage = new Skill("Dragon's Rage", R);

    /** The skill name. */
    public static final Skill Sunlight = new Skill("Sunlight", Passive);

    /** The skill name. */
    public static final Skill ShieldOfDaybreak = new Skill("Shield of Daybreak", Q);

    /** The skill name. */
    public static final Skill Eclipse = new Skill("Eclipse", W);

    /** The skill name. */
    public static final Skill ZenithBlade = new Skill("Zenith Blade", E);

    /** The skill name. */
    public static final Skill SolarFlare = new Skill("Solar Flare", R);

    /** The skill name. */
    public static final Skill PixFaerieCompanion = new Skill("Pix, Faerie Companion", Passive);

    /** The skill name. */
    public static final Skill Glitterlance = new Skill("Glitterlance", Q);

    /** The skill name. */
    public static final Skill Whimsy = new Skill("Whimsy", W);

    /** The skill name. */
    public static final Skill HelpPix = new Skill("Help, Pix!", E);

    /** The skill name. */
    public static final Skill WildGrowth = new Skill("Wild Growth", R);

    /** The skill name. */
    public static final Skill Illumination = new Skill("Illumination", Passive);

    /** The skill name. */
    public static final Skill LightBinding = new Skill("Light Binding", Q);

    /** The skill name. */
    public static final Skill PrismaticBarrier = new Skill("Prismatic Barrier", W);

    /** The skill name. */
    public static final Skill LucentSingularity = new Skill("Lucent Singularity", E);

    /** The skill name. */
    public static final Skill FinalSpark = new Skill("Final Spark", R);

    /** The skill name. */
    public static final Skill GraniteShield = new Skill("Granite Shield", Passive);

    /** The skill name. */
    public static final Skill SeismicShard = new Skill("Seismic Shard", Q);

    /** The skill name. */
    public static final Skill BrutalStrikes = new Skill("Brutal Strikes", W);

    /** The skill name. */
    public static final Skill GroundSlam = new Skill("Ground Slam", E);

    /** The skill name. */
    public static final Skill UnstoppableForce = new Skill("Unstoppable Force", R);

    /** The skill name. */
    public static final Skill SummonVoidling = new Skill("Summon Voidling", Passive);

    /** The skill name. */
    public static final Skill CallOftheVoid = new Skill("Call of the Void", Q);

    /** The skill name. */
    public static final Skill NullZone = new Skill("Null Zone", W);

    /** The skill name. */
    public static final Skill MaleficVisions = new Skill("Malefic Visions", E);

    /** The skill name. */
    public static final Skill NetherGrasp = new Skill("Nether Grasp", R);

    /** The skill name. */
    public static final Skill SapMagic = new Skill("Sap Magic", Passive);

    /** The skill name. */
    public static final Skill ArcaneSmash = new Skill("Arcane Smash", Q);

    /** The skill name. */
    public static final Skill TwistedAdvance = new Skill("Twisted Advance", W);

    /** The skill name. */
    public static final Skill SaplingToss = new Skill("Sapling Toss", E);

    /** The skill name. */
    public static final Skill VengefulMaelstrom = new Skill("Vengeful Maelstrom", R);

    /** The skill name. */
    public static final Skill DoubleStrike = new Skill("Double Strike", Passive);

    /** The skill name. */
    public static final Skill AlphaStrike = new Skill("Alpha Strike", Q);

    /** The skill name. */
    public static final Skill Meditate = new Skill("Meditate", W);

    /** The skill name. */
    public static final Skill WujuStyle = new Skill("Wuju Style", E);

    /** The skill name. */
    public static final Skill Highlander = new Skill("Highlander", R);

    /** The skill name. */
    public static final Skill Strut = new Skill("Strut", Passive);

    /** The skill name. */
    public static final Skill DoubleUp = new Skill("Double Up", Q);

    /** The skill name. */
    public static final Skill ImpureShots = new Skill("Impure Shots", W);

    /** The skill name. */
    public static final Skill MakeItRain = new Skill("Make It Rain", E);

    /** The skill name. */
    public static final Skill BulletTime = new Skill("Bullet Time", R);

    /** The skill name. */
    public static final Skill IronMan = new Skill("Iron Man", Passive);

    /** The skill name. */
    public static final Skill MaceOfSpades = new Skill("Mace of Spades", Q);

    /** The skill name. */
    public static final Skill CreepingDeath = new Skill("Creeping Death", W);

    /** The skill name. */
    public static final Skill SiphonOfDestruction = new Skill("Siphon of Destruction", E);

    /** The skill name. */
    public static final Skill ChildrenOftheGrave = new Skill("Children of the Grave", R);

    /** The skill name. */
    public static final Skill SoulSiphon = new Skill("Soul Siphon", Passive);

    /** The skill name. */
    public static final Skill DarkBinding = new Skill("Dark Binding", Q);

    /** The skill name. */
    public static final Skill TormentedSoil = new Skill("Tormented Soil", W);

    /** The skill name. */
    public static final Skill BlackShield = new Skill("Black Shield", E);

    /** The skill name. */
    public static final Skill SoulShackles = new Skill("Soul Shackles", R);

    /** The skill name. */
    public static final Skill SurgingTides = new Skill("Surging Tides", Passive);

    /** The skill name. */
    public static final Skill AquaPrison = new Skill("Aqua Prison", Q);

    /** The skill name. */
    public static final Skill EbbandFlow = new Skill("Ebb and Flow", W);

    /** The skill name. */
    public static final Skill TidecallersBlessing = new Skill("Tidecaller's Blessing", E);

    /** The skill name. */
    public static final Skill TidalWave = new Skill("Tidal Wave", R);

    /** The skill name. */
    public static final Skill SoulEater = new Skill("Soul Eater", Passive);

    /** The skill name. */
    public static final Skill SiphoningStrike = new Skill("Siphoning Strike", Q);

    /** The skill name. */
    public static final Skill Wither = new Skill("Wither", W);

    /** The skill name. */
    public static final Skill SpiritFire = new Skill("Spirit Fire", E);

    /** The skill name. */
    public static final Skill FuryOftheSands = new Skill("Fury of the Sands", R);

    /** The skill name. */
    public static final Skill StaggeringBlow = new Skill("Staggering Blow", Passive);

    /** The skill name. */
    public static final Skill DredgeLine = new Skill("Dredge Line", Q);

    /** The skill name. */
    public static final Skill TitansWrath = new Skill("Titan's Wrath", W);

    /** The skill name. */
    public static final Skill Riptide = new Skill("Riptide", E);

    /** The skill name. */
    public static final Skill DepthCharge = new Skill("Depth Charge", R);

    /** The skill name. */
    public static final Skill Prowl = new Skill("Prowl", Passive);

    /** The skill name. */
    public static final Skill JavelinToss = new Skill("Javelin Toss", Q);

    /** The skill name. */
    public static final Skill Takedown = new Skill("Takedown", Q);

    /** The skill name. */
    public static final Skill Bushwhack = new Skill("Bushwhack", W);

    /** The skill name. */
    public static final Skill Pounce = new Skill("Pounce", W);

    /** The skill name. */
    public static final Skill PrimalSurge = new Skill("Primal Surge", E);

    /** The skill name. */
    public static final Skill Swipe = new Skill("Swipe", E);

    /** The skill name. */
    public static final Skill AspectOfTheCougar = new Skill("Aspect Of The Cougar", R);

    /** The skill name. */
    public static final Skill UmbraBlades = new Skill("Umbra Blades", Passive);

    /** The skill name. */
    public static final Skill Duskbringer = new Skill("Duskbringer", Q);

    /** The skill name. */
    public static final Skill ShroudOfDarkness = new Skill("Shroud of Darkness", W);

    /** The skill name. */
    public static final Skill UnspeakableHorror = new Skill("Unspeakable Horror", E);

    /** The skill name. */
    public static final Skill Paranoia = new Skill("Paranoia", R);

    /** The skill name. */
    public static final Skill Visionary = new Skill("Visionary", Passive);

    /** The skill name. */
    public static final Skill Consume = new Skill("Consume", Q);

    /** The skill name. */
    public static final Skill BloodBoil = new Skill("Blood Boil", W);

    /** The skill name. */
    public static final Skill IceBlast = new Skill("Ice Blast", E);

    /** The skill name. */
    public static final Skill AbsoluteZero = new Skill("Absolute Zero", R);

    /** The skill name. */
    public static final Skill BerserkerRage = new Skill("Berserker Rage", Passive);

    /** The skill name. */
    public static final Skill Undertow = new Skill("Undertow", Q);

    /** The skill name. */
    public static final Skill ViciousStrikes = new Skill("Vicious Strikes", W);

    /** The skill name. */
    public static final Skill RecklessSwing = new Skill("Reckless Swing", E);

    /** The skill name. */
    public static final Skill Ragnarok = new Skill("Ragnarok", R);

    /** The skill name. */
    public static final Skill ClockworkWindup = new Skill("Clockwork Windup", Passive);

    /** The skill name. */
    public static final Skill CommandAttack = new Skill("Command: Attack", Q);

    /** The skill name. */
    public static final Skill CommandDissonance = new Skill("Command: Dissonance", W);

    /** The skill name. */
    public static final Skill CommandProtect = new Skill("Command: Protect", E);

    /** The skill name. */
    public static final Skill CommandShockwave = new Skill("Command: Shockwave", R);

    /** The skill name. */
    public static final Skill AegisProtection = new Skill("Aegis Protection", Passive);

    /** The skill name. */
    public static final Skill SpearShot = new Skill("Spear Shot", Q);

    /** The skill name. */
    public static final Skill AegisOfZeonia = new Skill("Aegis of Zeonia", W);

    /** The skill name. */
    public static final Skill HeartseekerStrike = new Skill("Heartseeker Strike", E);

    /** The skill name. */
    public static final Skill GrandSkyfall = new Skill("Grand Skyfall", R);

    /** The skill name. */
    public static final Skill ValiantFighter = new Skill("Valiant Fighter", Passive);

    /** The skill name. */
    public static final Skill DevastatingBlow = new Skill("Devastating Blow", Q);

    /** The skill name. */
    public static final Skill ParagonOfDemacia = new Skill("Paragon of Demacia", W);

    /** The skill name. */
    public static final Skill HeroicCharge = new Skill("Heroic Charge", E);

    /** The skill name. */
    public static final Skill DiplomaticImmunity = new Skill("Diplomatic Immunity", R);

    /** The skill name. */
    public static final Skill SpikedShell = new Skill("Spiked Shell", Passive);

    /** The skill name. */
    public static final Skill Powerball = new Skill("Powerball", Q);

    /** The skill name. */
    public static final Skill DefensiveBallCurl = new Skill("Defensive Ball Curl", W);

    /** The skill name. */
    public static final Skill PuncturingTaunt = new Skill("Puncturing Taunt", E);

    /** The skill name. */
    public static final Skill Tremors = new Skill("Tremors", R);

    /** The skill name. */
    public static final Skill ReignOfAnger = new Skill("Reign of Anger", Passive);

    /** The skill name. */
    public static final Skill CullTheMeek = new Skill("Cull the Meek", Q);

    /** The skill name. */
    public static final Skill RuthlessPredator = new Skill("Ruthless Predator", W);

    /** The skill name. */
    public static final Skill SliceandDice = new Skill("Slice and Dice", E);

    /** The skill name. */
    public static final Skill Dominus = new Skill("Dominus", R);

    /** The skill name. */
    public static final Skill UnseenPredator = new Skill("Unseen Predator", Passive);

    /** The skill name. */
    public static final Skill Savagery = new Skill("Savagery", Q);

    /** The skill name. */
    public static final Skill BattleRoar = new Skill("Battle Roar", W);

    /** The skill name. */
    public static final Skill BolaStrike = new Skill("Bola Strike", E);

    /** The skill name. */
    public static final Skill ThrillOftheHunt = new Skill("Thrill of the Hunt", R);

    /** The skill name. */
    public static final Skill RunicBlade = new Skill("Runic Blade", Passive);

    /** The skill name. */
    public static final Skill BrokenWings = new Skill("Broken Wings", Q);

    /** The skill name. */
    public static final Skill KiBurst = new Skill("Ki Burst", W);

    /** The skill name. */
    public static final Skill Valor = new Skill("Valor", E);

    /** The skill name. */
    public static final Skill BladeOftheExile = new Skill("Blade of the Exile", R);

    /** The skill name. */
    public static final Skill JunkyardTitan = new Skill("Junkyard Titan", Passive);

    /** The skill name. */
    public static final Skill Flamespitter = new Skill("Flamespitter", Q);

    /** The skill name. */
    public static final Skill ScrapShield = new Skill("Scrap Shield", W);

    /** The skill name. */
    public static final Skill ElectroHarpoon = new Skill("Electro-Harpoon", E);

    /** The skill name. */
    public static final Skill TheEqualizer = new Skill("The Equalizer", R);

    /** The skill name. */
    public static final Skill ArcaneMastery = new Skill("Arcane Mastery", Passive);

    /** The skill name. */
    public static final Skill Overload = new Skill("Overload", Q);

    /** The skill name. */
    public static final Skill RunePrison = new Skill("Rune Prison", W);

    /** The skill name. */
    public static final Skill SpellFlux = new Skill("Spell Flux", E);

    /** The skill name. */
    public static final Skill DesperatePower = new Skill("Desperate Power", R);

    /** The skill name. */
    public static final Skill Frost = new Skill("Frost", Passive);

    /** The skill name. */
    public static final Skill ArcticAssault = new Skill("Arctic Assault", Q);

    /** The skill name. */
    public static final Skill NorthernWinds = new Skill("Northern Winds", W);

    /** The skill name. */
    public static final Skill Permafrost = new Skill("Permafrost", E);

    /** The skill name. */
    public static final Skill GlacialPrison = new Skill("Glacial Prison", R);

    /** The skill name. */
    public static final Skill Backstab = new Skill("Backstab", Passive);

    /** The skill name. */
    public static final Skill Deceive = new Skill("Deceive", Q);

    /** The skill name. */
    public static final Skill JackInTheBox = new Skill("Jack In The Box", W);

    /** The skill name. */
    public static final Skill TwoShivPoison = new Skill("Two-Shiv Poison", E);

    /** The skill name. */
    public static final Skill Hallucinate = new Skill("Hallucinate", R);

    /** The skill name. */
    public static final Skill KiStrike = new Skill("Ki Strike", Passive);

    /** The skill name. */
    public static final Skill VorpalBlade = new Skill("Vorpal Blade", Q);

    /** The skill name. */
    public static final Skill Feint = new Skill("Feint", W);

    /** The skill name. */
    public static final Skill ShadowDash = new Skill("Shadow Dash", E);

    /** The skill name. */
    public static final Skill StandUnited = new Skill("Stand United", R);

    /** The skill name. */
    public static final Skill FuryOftheDragonborn = new Skill("Fury of the Dragonborn", Passive);

    /** The skill name. */
    public static final Skill TwinBite = new Skill("Twin Bite", Q);

    /** The skill name. */
    public static final Skill Burnout = new Skill("Burnout", W);

    /** The skill name. */
    public static final Skill FlameBreath = new Skill("Flame Breath", E);

    /** The skill name. */
    public static final Skill DragonsDescent = new Skill("Dragon's Descent", R);

    /** The skill name. */
    public static final Skill EmpoweredBulwark = new Skill("Empowered Bulwark", Passive);

    /** The skill name. */
    public static final Skill PoisonTrail = new Skill("Poison Trail", Q);

    /** The skill name. */
    public static final Skill MegaAdhesive = new Skill("Mega Adhesive", W);

    /** The skill name. */
    public static final Skill Fling = new Skill("Fling", E);

    /** The skill name. */
    public static final Skill InsanityPotion = new Skill("Insanity Potion", R);

    /** The skill name. */
    public static final Skill FeelNoPain = new Skill("Feel No Pain", Passive);

    /** The skill name. */
    public static final Skill CrypticGaze = new Skill("Cryptic Gaze", Q);

    /** The skill name. */
    public static final Skill DeathsCaress = new Skill("Death's Caress", W);

    /** The skill name. */
    public static final Skill Enrage = new Skill("Enrage", E);

    /** The skill name. */
    public static final Skill Cannibalism = new Skill("Cannibalism", R);

    /** The skill name. */
    public static final Skill FleetOfFoot = new Skill("Fleet of Foot", Passive);

    /** The skill name. */
    public static final Skill BoomerangBlade = new Skill("Boomerang Blade", Q);

    /** The skill name. */
    public static final Skill Ricochet = new Skill("Ricochet", W);

    /** The skill name. */
    public static final Skill SpellShield = new Skill("Spell Shield", E);

    /** The skill name. */
    public static final Skill OnTheHunt = new Skill("On The Hunt", R);

    /** The skill name. */
    public static final Skill Energize = new Skill("Energize", Passive);

    /** The skill name. */
    public static final Skill CrystalSlash = new Skill("Crystal Slash", Q);

    /** The skill name. */
    public static final Skill CrystallineExoskeleton = new Skill("Crystalline Exoskeleton", W);

    /** The skill name. */
    public static final Skill Fracture = new Skill("Fracture", E);

    /** The skill name. */
    public static final Skill Impale = new Skill("Impale", R);

    /** The skill name. */
    public static final Skill PowerChord = new Skill("Power Chord", Passive);

    /** The skill name. */
    public static final Skill HymnOfValor = new Skill("Hymn of Valor", Q);

    /** The skill name. */
    public static final Skill AriaOfPerseverance = new Skill("Aria of Perseverance", W);

    /** The skill name. */
    public static final Skill SongOfCelerity = new Skill("Song of Celerity", E);

    /** The skill name. */
    public static final Skill Crescendo = new Skill("Crescendo", R);

    /** The skill name. */
    public static final Skill Consecration = new Skill("Consecration", Passive);

    /** The skill name. */
    public static final Skill Starcall = new Skill("Starcall", Q);

    /** The skill name. */
    public static final Skill AstralBlessing = new Skill("Astral Blessing", W);

    /** The skill name. */
    public static final Skill Infuse = new Skill("Infuse", E);

    /** The skill name. */
    public static final Skill Wish = new Skill("Wish", R);

    /** The skill name. */
    public static final Skill CarrionRenewal = new Skill("Carrion Renewal", Passive);

    /** The skill name. */
    public static final Skill Decrepify = new Skill("Decrepify", Q);

    /** The skill name. */
    public static final Skill Nevermove = new Skill("Nevermove", W);

    /** The skill name. */
    public static final Skill Torment = new Skill("Torment", E);

    /** The skill name. */
    public static final Skill RavenousFlock = new Skill("Ravenous Flock", R);

    /** The skill name. */
    public static final Skill Transcendent = new Skill("Transcendent", Passive);

    /** The skill name. */
    public static final Skill DarkSphere = new Skill("Dark Sphere", Q);

    /** The skill name. */
    public static final Skill ForceOfWill = new Skill("Force of Will", W);

    /** The skill name. */
    public static final Skill ScatterTheWeak = new Skill("Scatter the Weak", E);

    /** The skill name. */
    public static final Skill UnleashedPower = new Skill("Unleashed Power", R);

    /** The skill name. */
    public static final Skill Mercy = new Skill("Mercy", Passive);

    /** The skill name. */
    public static final Skill NoxianDiplomacy = new Skill("Noxian Diplomacy", Q);

    /** The skill name. */
    public static final Skill Rake = new Skill("Rake", W);

    /** The skill name. */
    public static final Skill Cutthroat = new Skill("Cutthroat", E);

    /** The skill name. */
    public static final Skill ShadowAssault = new Skill("Shadow Assault", R);

    /** The skill name. */
    public static final Skill Gemcraft = new Skill("Gemcraft", Passive);

    /** The skill name. */
    public static final Skill Imbue = new Skill("Imbue", Q);

    /** The skill name. */
    public static final Skill Shatter = new Skill("Shatter", W);

    /** The skill name. */
    public static final Skill Dazzle = new Skill("Dazzle", E);

    /** The skill name. */
    public static final Skill Radiance = new Skill("Radiance", R);

    /** The skill name. */
    public static final Skill Camouflage = new Skill("Camouflage", Passive);

    /** The skill name. */
    public static final Skill BlindingDart = new Skill("Blinding Dart", Q);

    /** The skill name. */
    public static final Skill MoveQuick = new Skill("Move Quick", W);

    /** The skill name. */
    public static final Skill ToxicShot = new Skill("Toxic Shot", E);

    /** The skill name. */
    public static final Skill NoxiousTrap = new Skill("Noxious Trap", R);

    /** The skill name. */
    public static final Skill Damnation = new Skill("Damnation", Passive);

    /** The skill name. */
    public static final Skill DeathSentence = new Skill("Death Sentence", Q);

    /** The skill name. */
    public static final Skill DarkPassage = new Skill("Dark Passage", W);

    /** The skill name. */
    public static final Skill Flay = new Skill("Flay", E);

    /** The skill name. */
    public static final Skill TheBox = new Skill("The Box", R);

    /** The skill name. */
    public static final Skill DrawaBead = new Skill("Draw a Bead", Passive);

    /** The skill name. */
    public static final Skill RapidFire = new Skill("Rapid Fire", Q);

    /** The skill name. */
    public static final Skill RocketJump = new Skill("Rocket Jump", W);

    /** The skill name. */
    public static final Skill ExplosiveShot = new Skill("Explosive Shot", E);

    /** The skill name. */
    public static final Skill BusterShot = new Skill("Buster Shot", R);

    /** The skill name. */
    public static final Skill Decompose = new Skill("Decompose", Passive);

    /** The skill name. */
    public static final Skill RabidBite = new Skill("Rabid Bite", Q);

    /** The skill name. */
    public static final Skill Contaminate = new Skill("Contaminate", W);

    /** The skill name. */
    public static final Skill PillarOfFilth = new Skill("Pillar of Filth", E);

    /** The skill name. */
    public static final Skill Agony = new Skill("Agony", R);

    /** The skill name. */
    public static final Skill BattleFury = new Skill("Battle Fury", Passive);

    /** The skill name. */
    public static final Skill Bloodlust = new Skill("Bloodlust", Q);

    /** The skill name. */
    public static final Skill MockingShout = new Skill("Mocking Shout", W);

    /** The skill name. */
    public static final Skill SpinningSlash = new Skill("Spinning Slash", E);

    /** The skill name. */
    public static final Skill UndyingRage = new Skill("Undying Rage", R);

    /** The skill name. */
    public static final Skill LoadedDice = new Skill("Loaded Dice", Passive);

    /** The skill name. */
    public static final Skill WildCards = new Skill("Wild Cards", Q);

    /** The skill name. */
    public static final Skill PickACard = new Skill("Pick A Card", W);

    /** The skill name. */
    public static final Skill StackedDeck = new Skill("Stacked Deck", E);

    /** The skill name. */
    public static final Skill Destiny = new Skill("Destiny", R);

    /** The skill name. */
    public static final Skill DeadlyVenom = new Skill("Deadly Venom", Passive);

    /** The skill name. */
    public static final Skill Ambush = new Skill("Ambush", Q);

    /** The skill name. */
    public static final Skill VenomCask = new Skill("Venom Cask", W);

    /** The skill name. */
    public static final Skill Expunge = new Skill("Expunge", E);

    /** The skill name. */
    public static final Skill SprayandPray = new Skill("Spray and Pray", R);

    /** The skill name. */
    public static final Skill MonkeysAgility = new Skill("Monkey's Agility", Passive);

    /** The skill name. */
    public static final Skill TigerStance = new Skill("Tiger Stance", Q);

    /** The skill name. */
    public static final Skill TurtleStance = new Skill("Turtle Stance", W);

    /** The skill name. */
    public static final Skill BearStance = new Skill("Bear Stance", E);

    /** The skill name. */
    public static final Skill PhoenixStance = new Skill("Phoenix Stance", R);

    /** The skill name. */
    public static final Skill ZaunTouchedBoltAugmenter = new Skill("Zaun-Touched Bolt Augmenter", Passive);

    /** The skill name. */
    public static final Skill AcidHunter = new Skill("Acid Hunter", Q);

    /** The skill name. */
    public static final Skill TerrorCapacitor = new Skill("Terror Capacitor", W);

    /** The skill name. */
    public static final Skill NoxianCorrosiveCharge = new Skill("Noxian Corrosive Charge", E);

    /** The skill name. */
    public static final Skill HyperKineticPositionReverser = new Skill("Hyper-Kinetic Position Reverser", R);

    /** The skill name. */
    public static final Skill LivingVengeance = new Skill("Living Vengeance", Passive);

    /** The skill name. */
    public static final Skill PiercingArrow = new Skill("Piercing Arrow", Q);

    /** The skill name. */
    public static final Skill BlightedQuiver = new Skill("Blighted Quiver", W);

    /** The skill name. */
    public static final Skill HailOfArrows = new Skill("Hail of Arrows", E);

    /** The skill name. */
    public static final Skill ChainOfCorruption = new Skill("Chain of Corruption", R);

    /** The skill name. */
    public static final Skill NightHunter = new Skill("Night Hunter", Passive);

    /** The skill name. */
    public static final Skill Tumble = new Skill("Tumble", Q);

    /** The skill name. */
    public static final Skill SilverBolts = new Skill("Silver Bolts", W);

    /** The skill name. */
    public static final Skill Condemn = new Skill("Condemn", E);

    /** The skill name. */
    public static final Skill FinalHour = new Skill("Final Hour", R);

    /** The skill name. */
    public static final Skill Equilibrium = new Skill("Equilibrium", Passive);

    /** The skill name. */
    public static final Skill BalefulStrike = new Skill("Baleful Strike", Q);

    /** The skill name. */
    public static final Skill DarkMatter = new Skill("Dark Matter", W);

    /** The skill name. */
    public static final Skill EventHorizon = new Skill("Event Horizon", E);

    /** The skill name. */
    public static final Skill PrimordialBurst = new Skill("Primordial Burst", R);

    /** The skill name. */
    public static final Skill BlastShield = new Skill("Blast Shield", Passive);

    /** The skill name. */
    public static final Skill VaultBreaker = new Skill("Vault Breaker", Q);

    /** The skill name. */
    public static final Skill DentingBlows = new Skill("Denting Blows", W);

    /** The skill name. */
    public static final Skill ExcessiveForce = new Skill("Excessive Force", E);

    /** The skill name. */
    public static final Skill AssaultandBattery = new Skill("Assault and Battery", R);

    /** The skill name. */
    public static final Skill EvolvingTechnology = new Skill("Evolving Technology", Passive);

    /** The skill name. */
    public static final Skill PowerTransfer = new Skill("Power Transfer", Q);

    /** The skill name. */
    public static final Skill GravityField = new Skill("Gravity Field", W);

    /** The skill name. */
    public static final Skill DeathRay = new Skill("Death Ray", E);

    /** The skill name. */
    public static final Skill ChaosStorm = new Skill("Chaos Storm", R);

    /** The skill name. */
    public static final Skill CrimsonPact = new Skill("Crimson Pact", Passive);

    /** The skill name. */
    public static final Skill Transfusion = new Skill("Transfusion", Q);

    /** The skill name. */
    public static final Skill SanguinePool = new Skill("Sanguine Pool", W);

    /** The skill name. */
    public static final Skill TidesOfBlood = new Skill("Tides of Blood", E);

    /** The skill name. */
    public static final Skill Hemoplague = new Skill("Hemoplague", R);

    /** The skill name. */
    public static final Skill ChosenOftheStorm = new Skill("Chosen of the Storm", Passive);

    /** The skill name. */
    public static final Skill RollingThunder = new Skill("Rolling Thunder", Q);

    /** The skill name. */
    public static final Skill Frenzy = new Skill("Frenzy", W);

    /** The skill name. */
    public static final Skill MajesticRoar = new Skill("Majestic Roar", E);

    /** The skill name. */
    public static final Skill ThunderClaws = new Skill("Thunder Claws", R);

    /** The skill name. */
    public static final Skill EternalThirst = new Skill("Eternal Thirst", Passive);

    /** The skill name. */
    public static final Skill HungeringStrike = new Skill("Hungering Strike", Q);

    /** The skill name. */
    public static final Skill HuntersCall = new Skill("Hunters Call", W);

    /** The skill name. */
    public static final Skill BloodScent = new Skill("Blood Scent", E);

    /** The skill name. */
    public static final Skill InfiniteDuress = new Skill("Infinite Duress", R);

    /** The skill name. */
    public static final Skill StoneSkin = new Skill("Stone Skin", Passive);

    /** The skill name. */
    public static final Skill CrushingBlow = new Skill("Crushing Blow", Q);

    /** The skill name. */
    public static final Skill Decoy = new Skill("Decoy", W);

    /** The skill name. */
    public static final Skill NimbusStrike = new Skill("Nimbus Strike", E);

    /** The skill name. */
    public static final Skill Cyclone = new Skill("Cyclone", R);

    /** The skill name. */
    public static final Skill AscendedForm = new Skill("Ascended Form", Passive);

    /** The skill name. */
    public static final Skill Arcanopulse = new Skill("Arcanopulse", Q);

    /** The skill name. */
    public static final Skill LocusOfPower = new Skill("Locus of Power", W);

    /** The skill name. */
    public static final Skill MageChains = new Skill("Mage Chains", E);

    /** The skill name. */
    public static final Skill ArcaneBarrage = new Skill("Arcane Barrage", R);

    /** The skill name. */
    public static final Skill Challenge = new Skill("Challenge", Passive);

    /** The skill name. */
    public static final Skill ThreeTalonStrike = new Skill("Three Talon Strike", Q);

    /** The skill name. */
    public static final Skill BattleCry = new Skill("Battle Cry", W);

    /** The skill name. */
    public static final Skill AudaciousCharge = new Skill("Audacious Charge", E);

    /** The skill name. */
    public static final Skill CrescentSweep = new Skill("Crescent Sweep", R);

    /** The skill name. */
    public static final Skill UnholyCovenant = new Skill("Unholy Covenant", Passive);

    /** The skill name. */
    public static final Skill OmenOfWar = new Skill("Omen of War", Q);

    /** The skill name. */
    public static final Skill OmenOfPestilence = new Skill("Omen of Pestilence", W);

    /** The skill name. */
    public static final Skill OmenOfFamine = new Skill("Omen of Famine", E);

    /** The skill name. */
    public static final Skill OmenOfDeath = new Skill("Omen of Death", R);

    /** The skill name. */
    public static final Skill ContemptforTheWeak = new Skill("Contempt for the Weak", Passive);

    /** The skill name. */
    public static final Skill RazorShuriken = new Skill("Razor Shuriken", Q);

    /** The skill name. */
    public static final Skill LivingShadow = new Skill("Living Shadow", W);

    /** The skill name. */
    public static final Skill ShadowSlash = new Skill("Shadow Slash", E);

    /** The skill name. */
    public static final Skill DeathMark = new Skill("Death Mark", R);

    /** The skill name. */
    public static final Skill ShortFuse = new Skill("Short Fuse", Passive);

    /** The skill name. */
    public static final Skill BouncingBomb = new Skill("Bouncing Bomb", Q);

    /** The skill name. */
    public static final Skill SatchelCharge = new Skill("Satchel Charge", W);

    /** The skill name. */
    public static final Skill HexplosiveMinefield = new Skill("Hexplosive Minefield", E);

    /** The skill name. */
    public static final Skill MegaInfernoBomb = new Skill("Mega Inferno Bomb", R);

    /** The skill name. */
    public static final Skill HeightenedLearning = new Skill("Heightened Learning", Passive);

    /** The skill name. */
    public static final Skill TimeBomb = new Skill("Time Bomb", Q);

    /** The skill name. */
    public static final Skill Rewind = new Skill("Rewind", W);

    /** The skill name. */
    public static final Skill TimeWarp = new Skill("Time Warp", E);

    /** The skill name. */
    public static final Skill ChronoShift = new Skill("Chrono Shift", R);

    /** The skill name. */
    public static final Skill RiseOftheThorns = new Skill("Rise of the Thorns", Passive);

    /** The skill name. */
    public static final Skill DeadlyBloom = new Skill("Deadly Bloom", Q);

    /** The skill name. */
    public static final Skill RampantGrowth = new Skill("Rampant Growth", W);

    /** The skill name. */
    public static final Skill GraspingRoots = new Skill("Grasping Roots", E);

    /** The skill name. */
    public static final Skill Stranglethorns = new Skill("Stranglethorns", R);

    /** The skill name. */
    public static final Skill Harrier = new Skill("Harrier", Passive);

    /** The skill name. */
    public static final Skill BlindingAssault = new Skill("Blinding Assault", Q);

    /** The skill name. */
    public static final Skill HeightenedSenses = new Skill("Heightened Senses", W);

    /** The skill name. */
    public static final Skill Vault = new Skill("Vault", E);

    /** The skill name. */
    public static final Skill TagTeam = new Skill("Tag Team", R);

    /** The current writing skill. */
    private static Skill skill;

    /** The skill name. */
    public final String name;

    /** The skill system name. */
    public final String system;

    /** The skill key type. */
    public final SkillKey key;

    /** The descriptor. */
    private SkillStatus[] versions = new SkillStatus[Version.values().length];

    /**
     * <p>
     * Create new Skill.
     * </p>
     * 
     * @param name
     */
    Skill(String name, SkillKey key) {
        this.name = name;
        this.system = name.replaceAll(" of ", "Of").replaceAll("[\\s-,!':/]", "");
        this.key = key;
    }

    /**
     * <p>
     * Returns icon image path.
     * </p>
     * 
     * @return
     */
    public String getIcon() {
        return "src/main/resources/teemowork/skill/" + system + ".jpg";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * <p>
     * Returns minimum skill level.
     * </p>
     * 
     * @return
     */
    public int getMinLevel() {
        if (key == Passive || this == Mantra) {
            return 1;
        }

        if (this == SpiderForm || this == HumanForm || this == TransformMercuryCannon || this == TransformMercuryHammer) {
            return 1;
        }
        return 0;
    }

    /**
     * <p>
     * Returns maximum skill level.
     * </p>
     * 
     * @return
     */
    public int getMaxLevel() {
        if (key == Passive) {
            return 0;
        }

        if (this == Mantra) {
            return 1;
        }

        if (this == SpiderForm || this == HumanForm || this == TransformMercuryCannon || this == TransformMercuryHammer) {
            return 4;
        }

        if (this != PhoenixStance && (key == R || this == Takedown || this == Pounce || this == Swipe)) {
            return 3;
        } else {
            return 5;
        }
    }

    /**
     * <p>
     * Retrieve a status at the specified version.
     * </p>
     */
    public SkillStatus getStatus(Version version) {
        for (int i = version.ordinal(); 0 <= i; i--) {
            SkillStatus status = versions[i];

            if (status != null) {
                return status;
            }
        }
        return null;
    }

    /**
     * <p>
     * Update status.
     * </p>
     * 
     * @return A champion descriptor.
     */
    SkillStatus update() {
        return update(Version.P0000);
    }

    /**
     * <p>
     * Update status.
     * </p>
     * 
     * @return A champion descriptor.
     */
    SkillStatus update(Version version) {
        SkillStatus status = new SkillStatus(this, getStatus(version));

        versions[version.ordinal()] = status;
        skill = this;

        return status;
    }

    /**
     * <p>
     * Create skill AD amplifier. This pattern is used frequently.
     * </p>
     * 
     * @param rate An AD rate.
     * @return
     */
    private static final VariableHolder ad(double rate) {
        return amplify(AD, rate);
    }

    /**
     * <p>
     * Create skill AD amplifier. This pattern is used frequently.
     * </p>
     * 
     * @param rate An AD rate.
     * @return
     */
    private static final VariableHolder bounusAD(double rate) {
        return amplify(BounusAD, rate);
    }

    /**
     * <p>
     * Create skill AP amplifier. This pattern is used frequently.
     * </p>
     * 
     * @param rate An AP rate.
     * @return
     */
    private static final VariableHolder ap(double rate) {
        return amplify(AP, rate);
    }

    /**
     * <p>
     * Create skill amplifier.
     * </p>
     * 
     * @param status A status type.
     * @param base A base value of amplifier rate.
     * @return
     */
    private static final VariableHolder amplify(Status status, double base) {
        return amplify(status, base, 0);
    }

    /**
     * <p>
     * Create skill amplifier.
     * </p>
     * 
     * @param status A status type.
     * @param base A base value of amplifier rate.
     * @param diff A diff value of amplifier rate.
     * @return
     */
    private static final VariableHolder amplify(Status status, double base, double diff) {
        return amplify(status, new Diff(base, diff, skill.getMaxLevel()));
    }

    /**
     * <p>
     * Create skill amplifier.
     * </p>
     * 
     * @param status A status type.
     * @param base A base value of amplifier rate.
     * @param diff A diff value of amplifier rate.
     * @return
     */
    private static final VariableHolder amplify(Status status, VariableResolver resolver) {
        VariableHolder amplifier = new VariableHolder();
        amplifier.setStatus(status);
        amplifier.setResolver(resolver);

        return amplifier;
    }

    /**
     * <p>
     * Create skill amplifier.
     * </p>
     * 
     * @param status A status type.
     * @param base A base value of amplifier rate.
     * @param diff A diff value of amplifier rate.
     * @return
     */
    private static final VariableHolder amplify(Status status, double base, double diff, VariableHolder amplifier) {
        VariableHolder one = amplify(status, base, diff);
        one.getAmplifiers().add(amplifier);

        return one;
    }

    static {
        /** Ahri */
        EssenceTheft.update()
                .passive("スキルが敵ユニットに当たる度に" + EssenceTheft + "のチャージを1つ得る(1回のスキルで得られる上限は3チャージまで)。9チャージの状態でスキルを使用すると、チャージを全て消費して使用したスキルに{1}が追加される。")
                .variable(1, SV, 35)
                .cost(Charge, 9, 0)
                .conditional(1);
        OrbOfDeception.update()
                .active("指定方向にオーブを放ち当たった敵ユニットに{1}を与える。オーブは行きと帰りでそれぞれにヒット判定があり、帰りの場合は{2}を与える。")
                .variable(1, MagicDamage, 40, 25, ap(0.33))
                .variable(2, TrueDamage, 40, 25, ap(0.33))
                .cd(7, 0)
                .mana(70, 5)
                .range(880);
        FoxFire.update()
                .active("Ahriの周囲を回る3本の鬼火を放つ。鬼火は5秒間持続し、近くの敵ユニットに自動的に突撃して{1}を与える。鬼火が同一対象に突撃した場合、2発目以降は本来の50%分の魔法DMを与える(同一対象に3発hitで合計200%の魔法DM)。Ahriの通常攻撃範囲内に敵Championがいる場合、それらを優先して狙う。")
                .variable(1, MagicDamage, 40, 25, ap(0.4))
                .cd(9, -1)
                .mana(60, 0)
                .range(800);
        Charm.update()
                .active("指定方向に投げキッスを放ち、当たった敵ユニットに{1}と{2}を与え自分の方向に移動させる。また" + Status.Charm + "した対象には{3}を与える。")
                .variable(1, MagicDamage, 60, 30, ap(0.35))
                .variable(2, Status.Charm, 1, 0.25)
                .variable(3, MSSlowRatio, 50)
                .cd(12, 0)
                .mana(50, 15)
                .range(975);
        SpiritRush.update()
                .active("指定方向にダッシュした後、{2}の敵ユニット(敵Championを優先)3体に{1}を与える。このスキルは10秒の間、3回まで連続して使用できる(但し、一度使用する度に1秒のCDが発生する)。2～3発目はマナコスト無しで使用可能。")
                .variable(1, MagicDamage, 85, 40, ap(0.35))
                .variable(2, Radius, 500)
                .cd(110, -15)
                .mana(100, 0)
                .range(450);

        /** Akali */
        TwinDisciplines.update()
                .passive("{1}を得る。また通常攻撃に{2}が付与される。")
                .variable(1, SV, 6, 0, bounusAD(0.167))
                .variable(2, MagicDamage, 0, 0, amplify(AD, 0.06, 0, ap(0.00167)));
        MarkOftheAssassin.update()
                .active("対象の敵ユニットにカマを投げつけ{1}と6秒間マークを与える。マークが付いた対象に通常攻撃またはCrescent Slashでダメージを与えたとき、マークを消費して{2}を与え、{3}する。")
                .variable(1, MagicDamage, 45, 25, ap(0.4))
                .variable(2, MagicDamage, 45, 25, ap(0.4))
                .variable(3, RestoreEnergy, 20, 5)
                .cd(6, -0.5)
                .cost(Energy, 60, 0)
                .range(600);
        TwilightShroud.update()
                .active("指定地点に8秒間煙を発生させ{1}のユニットに以下の効果を与える。Akaliは{2}と{3}、{5}を得る。敵ユニットには{4}を与える。")
                .variable(1, Radius, 300)
                .variable(2, AR, 10, 10)
                .variable(3, MR, 10, 10)
                .variable(4, MSSlowRatio, 14, 4)
                .variable(5, Stealth)
                .cd(20, 0)
                .cost(Energy, 80, -5)
                .range(700);
        CrescentSlash.update()
                .active("{2}の敵ユニットに{1}を与える。")
                .variable(1, PhysicalDamage, 30, 25, ap(0.3), ad(0.6))
                .variable(2, Radius, 325, 0)
                .cd(7, -1)
                .cost(Energy, 60, -5);
        ShadowDance.update()
                .active("対象の敵ユニットまで高速で移動し{1}を与える。使用時にチャージを消費する。チャージは{2}毎に又は敵Championキル/アシストで増加し最大で3つまでチャージされる。チャージ増加時間はCD低減の影響を受ける。")
                .variable(1, MagicDamage, 100, 75, ap(0.5))
                .variable(2, CDRAwareTime, 25, -5)
                .cd(2, -0.5)
                .cost(Charge, 1, 0)
                .range(800);

        /** Alistar */
        Trample.update()
                .passive("スキルを使用すると3秒間{1}を得て、{2}の敵ユニットと建物に毎秒{3}を与える。ミニオンとモンスターに対しては与えるダメージが2倍になる。")
                .variable(3, MagicDamage, 6, 0, ap(0.1), amplify(Lv, 1))
                .variable(2, Radius, 182.5)
                .variable(1, IgnoreUnitCollision);
        Pulverize.update()
                .active("{4}の敵ユニットに{1}を与え、{2}後に{3}を与える。")
                .variable(1, MagicDamage, 60, 45, ap(0.5))
                .variable(2, Knockup, 1)
                .variable(3, Stun, 0.5, 0)
                .variable(4, Radius, 365)
                .cd(17, -1)
                .mana(70, 10);
        Headbutt.update()
                .active("対象の敵ユニットに突撃し{1}と{2}を与える。")
                .variable(1, MagicDamage, 55, 55, ap(0.7))
                .variable(2, Knockback, 650)
                .cd(14, -1)
                .mana(70, 10)
                .range(650);
        TriumphantRoar.update()
                .active("{1}する。{3}の味方ユニットは{2}する。近くの敵ユニットが死ぬと{4}する。")
                .variable(1, RestoreHealth, 60, 30, ap(0.2))
                .variable(2, RestoreHealth, 30, 15, ap(0.1))
                .variable(3, Radius, 575)
                .variable(4, CDDecrease, 2)
                .cd(12, 0)
                .mana(40, 10);
        UnbreakableWill.update()
                .active("7秒間Alistarは{1}を得て、{2}する。Disable中でも使用可能。使用時に自身にかかっているCCを全て解除する。")
                .variable(1, AD, 60, 10)
                .variable(2, DamageReductionRatio, 50, 10)
                .cd(120, -20)
                .mana(100, 0);

        /** Amumu */
        CursedTouch.update()
                .passive("通常攻撃した対象に3秒間{1}を与える。レベル1、7、13で低下値が上昇する。")
                .variable(1, MRReduction, new Per6Level(15, 5))
                .conditional(1);
        BandageToss.update()
                .active("指定方向に包帯を飛ばし、当たった敵ユニットに{1}及び{2}を与え、そこまで移動する。")
                .variable(1, MagicDamage, 80, 60, ap(0.7))
                .variable(2, Stun, 1)
                .mana(80, 10)
                .cd(16, -2)
                .range(1100)
                .update(P303)
                .variable(1, MagicDamage, 80, 50, ap(0.7));
        Despair.update()
                .active("毎秒、{2}の敵ユニットに{1}を与える。")
                .variable(1, MagicDamage, 8, 4, amplify(TargetMaxHealthRatio, 1.5, 0.3, ap(0.01)))
                .variable(2, Radius, 350)
                .mana(8)
                .cd(1)
                .type(SkillType.Toggle);
        Tantrum.update()
                .passive("{1}する。")
                .variable(1, PhysicalDamageReduction, 2, 2)
                .active("{3}の敵ユニットに{2}を与える。Amumuが通常攻撃でダメージを受けるたびに{4}。")
                .variable(2, MagicDamage, 75, 25, ap(0.5))
                .variable(3, Radius, 400)
                .variable(4, CDDecrease, 0.5)
                .mana(35)
                .cd(10, -1);
        CurseOftheSadMummy.update()
                .active("{2}の敵ユニットに{1}を与え、2秒間通常攻撃と移動を封じる。")
                .variable(1, MagicDamage, 150, 100, ap(0.8))
                .variable(2, Radius, 600)
                .mana(100, 50)
                .cd(150, -20);

        Rebirth.update()
                .passive("死亡時に卵になり6秒かけて復活する。復活中は{1}及び{2}を得る。復活中にHPが0になった場合は死亡する。レベル1、5、8、12、15で増加AR/MRが上昇する。")
                .variable(1, AR, new Per4Level(-40, 15))
                .variable(2, MR, new Per4Level(-40, 15))
                .cd(240)
                .conditional(1)
                .conditional(2);
        FlashFrost.update()
                .active("指定方向に貫通する氷を飛ばし、氷に触れた敵ユニットに{1}と3秒間{2}を与え、{4}状態にする。氷が飛んでいる最中に再度スキルを使用するか、最大距離まで飛ぶと氷が破裂し、破裂地点の{6}の敵ユニットにさらに{1}と{5}と3秒間{2}を与え、{4}状態にする。")
                .variable(1, MagicDamage, 60, 30, ap(0.5))
                .variable(2, MSSlowRatio, 20)
                .variable(4, Chill, 0)
                .variable(5, Stun, 1)
                .variable(6, Radius, 150)
                .mana(80, 20)
                .cd(12, -1)
                .range(1100);
        Crystalize.update()
                .active("指定地点に5秒間{1}の壁を作りユニットを通れなくする。また、指定地点の{2}。")
                .variable(1, Length, 400, 100)
                .variable(2, Visionable)
                .mana(70, 20)
                .cd(25)
                .range(1000);
        Frostbite.update()
                .active("対象の敵ユニットに{1}を与える。対象が" + Chill + "の場合は{2}を与える。")
                .variable(1, MagicDamage, 55, 30, ap(0.5))
                .variable(2, MagicDamage, 110, 60, ap(1.0))
                .mana(50, 10)
                .cd(5)
                .range(650);
        GlacialStorm.update()
                .active("指定地点の{6}の敵ユニットに毎秒{1}、1秒間の{2}と{3}を与え、{5}状態にする。")
                .variable(1, MagicDamage, 80, 40, ap(0.25))
                .variable(2, ASSlowRatio, 20, 0)
                .variable(3, MSSlowRatio, 20)
                .variable(5, Chill, 0)
                .variable(6, Radius, 300)
                .mana(75)
                .cd(6)
                .range(625)
                .type(SkillType.Toggle);

        /** Annie */
        Pyromania.update()
                .passive("スキルを使用するたびにスタックが1貯まり、4スタック時に" + MoltenShield + "以外のスキルを使用すると、スタックを全て消費してそのスキルに{1}が追加される。")
                .variable(1, Stun, 1.75);
        Disintegrate.update()
                .active("対象の敵ユニットに{1}を与える。このスキルでLHを取ると{2}する。")
                .variable(1, MagicDamage, 85, 40, ap(0.7))
                .variable(2, RestoreMana, 60, 5)
                .mana(60, 5)
                .cd(4)
                .range(625);
        Incinerate.update()
                .active("指定方向扇形45°の{1}の敵ユニットに{2}を与える。")
                .variable(1, Radius, 625)
                .variable(2, MagicDamage, 80, 50, ap(0.75))
                .mana(70, 10)
                .cd(8);
        MoltenShield.update()
                .active("{1}間{2}と{3}を得て、効果時間中に通常攻撃をしてきた敵ユニットに{4}を与える。")
                .variable(1, Time, 5)
                .variable(2, AR, 20, 10)
                .variable(3, MR, 20, 10)
                .variable(4, MagicDamage, 20, 10, ap(0.2))
                .mana(20)
                .cd(10);
        SummonTibbers.update()
                .active("指定地点の{1}の敵ユニットに{2}を与え、操作可能なTibbersを召喚する。Tibbersは{3}間持続し、{4}の敵ユニットに毎秒{5}を与える。")
                .variable(1, Radius, 150)
                .variable(2, MagicDamage, 200, 125, ap(0.7))
                .variable(3, Time, 45, 0)
                .variable(4, Radius, 200)
                .variable(5, MagicDamage, 35, 0, ap(0.2))
                .mana(125, 50)
                .cd(120)
                .range(600);

        /** Ashe */
        Focus.update()
                .passive("3秒毎に{1}する（最大100%）。通常攻撃を行うとリセットされる。上昇値は3レベル毎に増加する。")
                .variable(1, Critical, new Per3Level(3, 3))
                .conditional(1);
        FrostShot.update().active("通常攻撃に2秒間の{1}を付与する。").variable(1, MSSlowRatio, 15, 5).mana(8).type(SkillType.Toggle);
        Volley.update()
                .active("指定方向扇形57.5°の方向に非貫通の矢7本を飛ばし当たった敵ユニットに{1}と{2}(" + FrostShot + "のLvに依存)を与える。" + FrostShot + "を覚えていない場合はスローは発生しない。")
                .variable(1, PhysicalDamage, 40, 10, ad(1))
                .variable(2, MSSlowRatio, 0)
                .mana(60)
                .cd(16, -3)
                .range(1200);
        Hawkshot.update()
                .passive("敵を倒した際に追加で{1}を得る。")
                .variable(1, Gold, 1, 1)
                .active("指定地点に偵察鷹を放つ。鷹は5秒間指定した地点の{2}。また飛行中の鷹も{2}。")
                .variable(2, Visionable)
                .cd(60)
                .range(2500, 750);
        EnchantedCrystalArrow.update()
                .active("指定方向に敵Championにのみ当たる矢を飛ばし、当たった敵Championに{1}と{2}(飛距離に比例して１～3.5秒)と3秒間の{4}を与える。また敵Champion命中時に矢が爆発し、{5}の敵ユニットに{6}と3秒間の{4}を与える。飛行中の矢は{3}。")
                .variable(1, MagicDamage, 250, 175, ap(1))
                .variable(2, Stun, 0)
                .variable(3, Visionable)
                .variable(4, MSSlowRatio, 50)
                .variable(5, Radius, 250)
                .variable(6, MagicDamage, 125, 87.5, ap(0.5))
                .mana(150)
                .cd(100, -10)
                .range(-1);

        ManaBarrier.update()
                .passive(" HPが20%以下になると、10秒間持続する{1}を張る。")
                .variable(1, Shield, 0, 0, amplify(CurrentManaRatio, 50))
                .cd(90);
        RocketGrab.update()
                .active("指定方向に腕を飛ばし、当たった敵ユニットに{1}と{2}を与え自分の位置まで引き寄せる。またこのスキル命中時に対象の{3}。")
                .variable(1, MagicDamage, 80, 55, ap(1))
                .variable(2, Stun, 1)
                .variable(3, Visionable)
                .mana(120)
                .cd(20, -1)
                .range(925);
        Overdrive.update()
                .active("8秒間{1}、{2}する。")
                .variable(1, MSRatio, 16, 4)
                .variable(2, ASRatio, 30, 8)
                .mana(75)
                .cd(15);
        PowerFist.update()
                .active("次の通常攻撃に追加{1}を付与し、対象に{2}を与える。")
                .variable(1, PhysicalDamage, 0, 0, ad(1))
                .variable(2, Knockup, 1, 0)
                .mana(25)
                .cd(9, -1);
        StaticField.update()
                .passive("{1}の敵ユニット1体（対象はランダム）に2.5秒ごとに{2}を与える。")
                .variable(1, Radius, 450)
                .variable(2, MagicDamage, 100, 100, ap(0.25))
                .active("{3}の敵ユニットに{4}と{5}を与える。効果後はCDが解消されるまでPassiveの効果がなくなる。")
                .variable(3, Radius, 600)
                .variable(4, MagicDamage, 250, 125, ap(1))
                .variable(5, Silence, 0.5)
                .mana(150)
                .cd(30);

        Blaze.update()
                .passive("スキルが当たった敵ユニットを炎上させ、毎秒{1}与える。この効果は4秒間続く。炎上している敵ユニットにスキルが命中すると追加効果が発生する。(Minionに対しては毎秒80DMが上限)")
                .variable(1, MagicDamage, 0, 0, amplify(TargetMaxHealthRatio, 2));
        Sear.update()
                .active("指定方向に火球を投射し、当たった敵ユニットに{1}を与える。敵が炎上していた場合、{2}を与える。")
                .variable(1, MagicDamage, 80, 40, ap(0.65))
                .variable(2, Stun, 2)
                .mana(50)
                .cd(8, -0.5)
                .range(1025);
        PillarOfFlame.update()
                .active("指定地点に炎の柱を作り出し、0.5秒後に{1}の敵ユニットに{2}を与える。敵が炎上していた場合、代わりに{3}を与える。")
                .variable(1, Radius, 175)
                .variable(2, MagicDamage, 75, 45, ap(0.6))
                .variable(3, MagicDamage, 94, 56, ap(0.75))
                .mana(70, 10)
                .cd(10)
                .range(900);
        Conflagration.update()
                .active("対象の敵ユニットに{1}を与える。敵が炎上していた場合、{2}の敵にも{1}を与える。")
                .variable(1, MagicDamage, 70, 35, ap(0.55))
                .variable(2, Radius, 200)
                .mana(60, 5)
                .cd(12, -1)
                .range(625);
        Pyroclasm.update()
                .active("対象の敵ユニットに火炎弾を放つ。火炎弾は近くの敵ユニットに4回まで跳ね、その度に{1}を与える(最大5hit)。この跳ね返りは同一ユニットに何度も跳ね返る。敵が炎上していた場合、敵Championに優先して跳ね返るようになる。")
                .variable(1, MagicDamage, 150, 100, ap(0.5))
                .mana(100, 50)
                .cd(105, -15)
                .range(750);

        Headshot.update()
                .passive("通常攻撃{1}回毎にダメージが増加する(Minionには250%、Championには150%)。茂みから通常攻撃を行うと2回分としてカウントされる。レベル1、7、13でダメージが増加するまでの攻撃回数が減少する。")
                .variable(1, Count, new Per6Level(8, -1));
        PiltoverPeacemaker.update()
                .active("1秒詠唱後、指定方向に貫通する弾を発射し当たった敵ユニットに{1}を与える。ダメージは敵に当たるごとに10%減少していき最小で{2}を与える。")
                .variable(1, PhysicalDamage, 20, 40, ad(1.3))
                .variable(2, PhysicalDamage, 10, 20, ad(0.65))
                .mana(50, 10)
                .cd(10, -1)
                .range(1250);
        YordleSnapTrap.update()
                .active("指定地点に罠を仕掛ける。敵Championが罠の{4}に入ると発動して、対象に{1}かけて{2}と{3}を与え、9秒間対象の{5}。罠は3個まで置け、4分間持続する。")
                .variable(1, Time, 1.5)
                .variable(2, MagicDamage, 80, 50, ap(0.6))
                .variable(3, Snare, 1.5)
                .variable(4, Radius, 135)
                .variable(5, Visionable)
                .mana(50)
                .cd(20, -3)
                .range(800);
        CaliberNet.update()
                .active("指定方向にネットを飛ばし当たった敵ユニットに{1}と{3}間{2}を与え、Caitlynはネットを飛ばした方向の反対側にジャンプ({4})する。")
                .variable(1, MagicDamage, 80, 50, ap(0.8))
                .variable(2, MSSlowRatio, 50)
                .variable(3, Time, 1, 0.25)
                .variable(4, Distance, 400)
                .mana(75)
                .cd(18, -2)
                .range(850);
        AceinTheHole.update()
                .active("0.5秒詠唱後に対象の敵Championの視界を得て、更に1秒詠唱後対象に目掛けて敵Championにのみ当たる弾を発射し、当たった敵Championに{1}を与える。ターゲットとの射線を遮ると間に入った敵Championに当たる。")
                .variable(1, PhysicalDamage, 250, 225, bounusAD(2))
                .mana(100)
                .cd(90, -15)
                .range(2000, 500);

        /** Ryze */
        ArcaneMastery.update().passive("スキルを使用すると使用した以外のスキルの{1}する。").variable(1, CDDecrease, 1);
        Overload.update()
                .active("対象の敵ユニットに{1}を与える。")
                .passive("{2}を得る。")
                .variable(1, MagicDamage, 60, 25, ap(0.4), amplify(Mana, 0.065))
                .variable(2, CDR, 2, 2)
                .mana(60)
                .cd(3.5)
                .range(650);
        RunePrison.update()
                .active("対象の敵ユニットに{1}及び{2}を与える。")
                .variable(1, MagicDamage, 60, 35, ap(0.6), amplify(Mana, 0.045))
                .variable(2, Snare, 0.75, 0.25)
                .mana(80, 10)
                .cd(14)
                .range(625);
        SpellFlux.update()
                .active("対象の敵ユニットに魔法弾を飛ばし{1}及び{2}を与える。魔法弾は{3}の敵ユニット及び自身に4回まで跳ね返る(最大5hit)。この跳ね返りは同一ユニットに何度も跳ね返り、また自身から跳ね返った弾は敵Championを優先で狙う。")
                .variable(1, MagicDamage, 50, 20, ap(0.35), amplify(Mana, 0.01))
                .variable(2, MRReduction, 12, 3)
                .variable(3, Radius, 400)
                .mana(60, 10)
                .cd(14)
                .range(675);
        DesperatePower.update()
                .active("{1}間、{2}を得て{3}する。更にスキルに50%のスプラッシュダメージ({4})が付与される。")
                .variable(1, Time, 5, 1)
                .variable(2, SV, 15, 5)
                .variable(3, MS, 35, 10)
                .variable(4, Radius, 200)
                .cd(70, -10);

        /** Cassiopeia */
        DeadlyCadence.update().passive("スキル使用後の5秒間、全てのスキルのコストが1スタックにつき10%低減する。");
        NoxiousBlast.update()
                .active("指定地点に0.5秒後に毒を発生させ、{1}の敵ユニットに毒を与え3秒かけて{2}を与える。このスキルがChampionにヒットした場合、3秒間{3}する。")
                .variable(1, Radius, 75)
                .variable(2, MagicDamage, 75, 40, ap(0.8))
                .variable(3, MSRatio, 15, 2.5)
                .mana(35, 10)
                .cd(3)
                .range(850);
        Miasma.update()
                .active("指定地点に7秒間持続する毒霧を吹き出す。毒霧は徐々に範囲(100～175)が広がり、毒霧の上を通過した敵に2秒間持続する毒を付与し{1}と2秒間{3}を与える。また指定地点の{4}。")
                .variable(1, MagicDamage, 25, 10, ap(0.15))
                .variable(3, MSSlowRatio, 15, 5)
                .variable(4, Visionable)
                .mana(70, 10)
                .cd(9)
                .range(850);
        TwinFang.update()
                .active("対象の敵ユニットに{1}を与える。対象が毒を受けている場合、CDが0.5秒になる。")
                .variable(1, MagicDamage, 50, 35, ap(0.55))
                .mana(50, 10)
                .cd(5)
                .range(700);
        PetrifyingGaze.update()
                .active("眼からビームを放ち、指定方向扇形83°の範囲内の敵ユニットに{1}を与え、こちらを向いている敵に更に{2}を与える。後ろを向いていた場合2秒間{4}を与える。")
                .variable(1, MagicDamage, 200, 125, ap(0.6))
                .variable(2, Stun, 2)
                .variable(4, MSSlowRatio, 60)
                .mana(120, 40)
                .cd(130, -10)
                .range(750);

        /** Cho'Gath */
        Carnivore.update()
                .passive("敵ユニットを倒すと{1}、{2}する。")
                .variable(1, RestoreHealth, 17, 0, amplify(Lv, 3))
                .variable(2, RestoreMana, 3.25, 0, amplify(Lv, 0.25));
        Rupture.update()
                .active("指定地点に0.5秒後にトゲを出現させ、{1}の敵ユニットに{2}、{3}を与えて、3秒間{5}にする。また指定地点の視界を得る。")
                .variable(1, Radius, 175)
                .variable(2, MagicDamage, 80, 55, ap(1))
                .variable(3, Knockup, 1)
                .variable(5, MSSlowRatio, 60)
                .mana(90)
                .cd(9)
                .range(950);
        FeralScream.update()
                .active("前方扇形60°の領域の敵ユニットに{1}と{2}を与える。")
                .variable(1, MagicDamage, 75, 50, ap(0.7))
                .variable(2, Silence, 1.5, 0.25)
                .mana(70, 10)
                .cd(13)
                .range(700);
        VorpalSpikes.update()
                .active("通常攻撃時に前方にトゲを飛ばし当たった敵ユニットに{1}を与える。トゲを飛ばす範囲はUltのスタック数に比例し増加する。")
                .variable(1, MagicDamage, 20, 15, ap(0.3))
                .cd(0.5)
                .range(500)
                .type(SkillType.Toggle);
        Feast.update()
                .active("対象の敵ユニットに{1}を与える。対象がChampion以外の場合は{2}を与える。このスキルで敵を倒すとスタックが1増えて{3}と{4}を得る。死亡するとスタックが半分(端数切り上げ)消失する。")
                .variable(1, TrueDamage, 300, 175, ap(0.7))
                .variable(2, TrueDamage, 1000, 0, ap(0.7))
                .variable(3, Health, 0, 0, amplify(Stack, 90, 30))
                .variable(4, Range, 0, 0, amplify(Stack, 4, 2.15))
                .mana(100)
                .cd(60)
                .range(150);

        /** Corki */
        HextechShrapnelShells.update().passive("通常攻撃に{1}が付与される。建物には無効。").variable(1, TrueDamage, 0, 0, ad(0.1));
        PhosphorusBomb.update()
                .active("指定した{1}の敵ユニットに{2}を与え、6秒間指定地点の{3}。また、Championに当たった場合、6秒間そのChampionの{3}。このスキルで敵のステルスを看破する事はできない。")
                .variable(1, Radius, 150)
                .variable(2, MagicDamage, 80, 50, ap(0.5))
                .variable(3, Visionable)
                .mana(80, 10)
                .cd(8)
                .range(600);
        Valkyrie.update()
                .active("指定地点まで高速で移動し、通過地点を2.5秒間炎上させる。炎上地点の上にいる敵ユニットに毎秒{2}を与える。")
                .variable(2, MagicDamage, 60, 30, ap(0.4))
                .mana(100)
                .cd(26, -3)
                .range(800);
        GatlingGun.update()
                .active("4秒間、Corkiの前方にいる敵ユニットに0.5秒毎に{1}を与える(最大8hit)。ダメージを与える度に対象ユニットに{2}を与える。この効果は2秒間持続し、8回までスタックする。")
                .variable(1, PhysicalDamage, 10, 6, bounusAD(0.02))
                .variable(2, ARReduction, 1, 1)
                .mana(60, 5)
                .cd(16)
                .range(600);
        MissileBarrage.update()
                .active("指定方向にミサイルを発射し、当たった敵ユニットの{1}に{2}を与える。使用時にスタックを消費する。スタックは{3}毎に1つ増加し最大7つまでスタックされる。4発毎に大きいミサイル(効果範囲2倍、ダメージ50%上昇)を発射させる。スタック増加時間はCD低減の影響を受ける。")
                .variable(1, Radius, 150, 0)
                .variable(2, MagicDamage, 120, 70, ap(0.3), amplify(AD, 0.2))
                .variable(3, CDRAwareTime, 12)
                .mana(30, 5)
                .cd(1.2)
                .range(1200);

        /** Darius */
        Hemorrhage.update()
                .passive("通常攻撃またはスキルでダメージを与えた敵ユニットに出血スタックを付与する。出血スタックが付与された敵ユニットは毎秒{1}を受ける。出血スタックは最大5回までスタックし、5秒間持続する。また、出血スタックを受けている敵Champion数に応じて{2}していく。")
                .variable(1, MagicDamage, 2.4, 0, amplify(Lv, 0.3), bounusAD(0.06))
                .variable(2, MSRatio, 5)
                .conditional(2);
        Decimate.update()
                .active("斧を振り回し{3}の敵ユニットに{1}を与える。斧の刃に当たった敵Championに対しては{2}を与える。")
                .variable(1, PhysicalDamage, 70, 35, bounusAD(0.7))
                .variable(2, PhysicalDamage, 105, 52.5, bounusAD(1.05))
                .variable(3, Radius, 425)
                .mana(40)
                .cd(9, -1);
        CripplingStrike.update()
                .active("次の通常攻撃に{1}を追加し、2秒間{3}と{4}が付与される。対象の出血スタック数1個につき、このスキルの{5}する。")
                .variable(1, PhysicalDamage, 0, 0, ad(0.2))
                .variable(3, ASSlowRatio, 20, 5)
                .variable(4, MSSlowRatio, 20, 5)
                .variable(5, CDDecrease, 1)
                .mana(30, 5)
                .cd(8);
        Apprehend.update()
                .passive("{1}を得る。")
                .variable(1, ARPenRatio, 5, 5)
                .active("前方範囲内の敵ユニットをDariusがいる方向に引き寄せる。")
                .mana(45)
                .cd(24, -3)
                .range(550);
        NoxianGuillotine.update()
                .active("対象の敵Championに跳躍し、{1}を与える。対象の出血スタック数1個につき、このスキルのダメージが20%増加する(最大でダメージ2倍)。このスキルで敵Championのキルを取った場合、{3}する。")
                .variable(1, TrueDamage, 160, 90, bounusAD(0.75))
                .variable(3, CDDecrease)
                .mana(100)
                .cd(100, -10)
                .range(475);

        /** Diana */
        MoonsilverBlade.update()
                .passive("{1}する。通常攻撃3回毎に周囲にいる敵ユニットに{2}を与える。")
                .variable(1, ASRatio, 20)
                .variable(2, MagicDamage, new Per1Level(new double[] {20, 25, 30, 40, 50, 65, 80, 95, 110, 125, 140,
                        155, 175, 195, 215, 240, 265, 290}));
        CrescentStrike.update()
                .active("指定地点に弧を描くエネルギーを放ち、当たった敵ユニットと{2}のユニットに{1}とMoonlight(3秒)を与える。またMoonlightが付与されている敵ユニットの{3}。")
                .variable(1, MagicDamage, 60, 35, ap(0.7))
                .variable(2, Radius, 50)
                .variable(3, Visionable)
                .mana(55)
                .cd(10, -1)
                .range(830);
        PaleCascade.update()
                .active("5秒間{1}を張ると同時に、Dianaの周りを回る3つの球体を召喚する。敵ユニットが触れた球体は爆発し、{2}の敵ユニットに{3}を与える。球体が全て爆発するとシールドが張りなおされる。")
                .variable(1, Shield, 55, 25, ap(0.45))
                .variable(2, Radius, 400)
                .variable(3, MagicDamage, 20, 14, ap(0.2))
                .mana(60, 10)
                .cd(10);
        Moonfall.update()
                .active("{1}にいるすべての敵ユニットをDianaがいる方向に引き寄せた後、2秒間{3}を与える。")
                .variable(1, Radius, 500)
                .variable(3, MSSlowRatio, 35, 5)
                .mana(70)
                .cd(26, -2);
        LunarRush.update()
                .active("対象の敵ユニットの元までテレポートし、{1}を与える。対象にMoonlightが付与されていた場合、すべての敵ユニットに付与されたMoonlightを消費してこのスキルの{2}する。")
                .variable(1, MagicDamage, 100, 60, ap(0.6))
                .variable(2, CDDecrease)
                .mana(50, 15)
                .cd(25, -5)
                .range(825);

        /** Dr.Mundo */
        AdrenalineRush.update().passive("毎秒{1}する。").variable(1, RestoreHealth, 0, 0, amplify(Health, 0.003));
        InfectedCleaver.update()
                .active("指定方向に包丁を投げ、当たった敵ユニットに{1}と2秒間の{3}を与える。最小で{4}。ミニオンやモンスターへの最大DMは{5}。命中すると{6}する。")
                .variable(1, MagicDamage, 0, 0, amplify(TargetCurrentHealthRatio, 15, 3))
                .variable(3, MSSlowRatio, 40)
                .variable(4, MagicDamage, 80, 50)
                .variable(5, MagicDamage, 300, 100)
                .variable(6, RestoreHealth, 25, 5)
                .cost(Health, 50, 10)
                .cd(4)
                .range(1000);
        BurningAgony.update()
                .active("{1}の敵ユニットに毎秒{2}を与える。また{3}を得る。")
                .variable(1, Radius, 325)
                .variable(2, MagicDamage, 35, 15, ap(0.2))
                .variable(3, Tenacity, 10, 5)
                .cost(Health, 10, 5)
                .cd(4)
                .type(SkillType.Toggle);
        Masochism.update()
                .active("5秒間{1}を得る。")
                .variable(1, AD, 40, 15, amplify(MissingHealthPercentage, 0.4, 0.15))
                .cost(Health, 35, 10)
                .cd(7);
        Sadism.update()
                .active("12秒かけて{1}し、{2}する。")
                .variable(1, RestoreHealth, 0, 0, amplify(Health, 0.4, 0.15))
                .variable(2, MSRatio, 15, 10)
                .cd(75)
                .cost(CurrentHealthRatio, 20, 0);

        /** Draven */
        WickedBlades.update()
                .passive("クリティカル時または" + SpinningAxe + "使用時の通常攻撃に毎秒{1}が付与される。毎秒ダメージは4秒間持続する。")
                .variable(1, PhysicalDamage, 7.5, 0, amplify(Lv, 1));
        SpinningAxe.update()
                .active("次に行う通常攻撃に追加{1}が付与される。このスキルによる通常攻撃が敵ユニットに命中すると、斧がDravenの近くに跳ね返る。その斧をキャッチするとBlood RushのCDが解消され、更に次の通常攻撃もSpinning Axeの効果を受けるようになる。このスキルは連続で使用する事で最大2回分までチャージできる。")
                .variable(1, PhysicalDamage, 0, 0, amplify(AD, 0.45, 0.1))
                .mana(45)
                .cd(12, -1);
        BloodRush.update()
                .active("1.5秒間{1}し、3秒間{2}する。移動速度増加は1.5秒かけて元に戻る。")
                .variable(1, MSRatio, 40, 5)
                .variable(2, ASRatio, 20, 5)
                .mana(40)
                .cd(12);
        StandAside.update()
                .active("指定方向に貫通する斧を投げ、当たった敵ユニットに{1}と{2}と2秒間{3}を与える。このノックバックは斧から弾かれる形で左右に吹き飛ぶ。")
                .variable(1, PhysicalDamage, 70, 35, bounusAD(0.5))
                .variable(2, Knockback, 0)
                .variable(3, MSSlowRatio, 20, 5)
                .mana(70)
                .cd(18, -1)
                .range(1050);
        WhirlingDeath.update()
                .active("指定方向に地面を這う貫通する斧を投げ、当たった敵ユニットに{1}を与える。ダメージは敵に当たるごとに8%ずつ減り、最大で40%まで低下する。行きと帰りそれぞれに攻撃判定があり、斧が飛んでいる最中に再度このスキルを使用するか、敵Championに命中した時点で斧が反転してDravenの元に戻ってくる。反転した際、低下ダメージはリセットされる。また移動中の斧は{2}。")
                .variable(1, PhysicalDamage, 175, 100, bounusAD(1.1))
                .variable(2, Visionable)
                .mana(120)
                .cd(110)
                .range(-1);

        /** Elise */
        SpiderSwarm.update()
                .passive("Human Form時に使用したスキルが敵ユニットに命中するとSpiderlingのチャージを1得る。Spider Formになるとチャージ数に比例したSpiderlingを召喚する。召喚される数はSpider Formのレベルに比例し増加する。召喚されたSpiderlingは死亡するとチャージが減るが、再度Human Formに戻ると再度チャージ状態に戻る。");
        Neurotoxin.update()
                .active("対象の敵ユニットに毒を放ち{1}を与える。")
                .variable(1, MagicDamage, 50, 45, amplify(TargetCurrentHealthRatio, 8, 0, amplify(AP, 0.03)))
                .mana(80, 5)
                .cd(6)
                .range(475);
        VolatileSpiderling.update()
                .active("指定地点に蜘蛛を放つ。蜘蛛は敵ユニットに当たるか3秒間経過すると爆発し、範囲内の敵ユニットに{1}を与える。蜘蛛は指定地点に移動した後、最も近くにいる敵ユニットに向かって移動する。また{2}。")
                .variable(1, MagicDamage, 75, 50, ap(0.8))
                .variable(2, Visionable)
                .mana(60, 10)
                .cd(12)
                .range(950);
        Cocoon.update()
                .active("指定方向に糸を飛ばし当たった敵ユニットに{1}を与え、{2}。")
                .variable(1, Stun, 1.5)
                .variable(2, Visionable)
                .mana(50)
                .cd(14, -1)
                .range(1075);
        SpiderForm.update()
                .active("EliseがSpider Formに変身し射程125のMeleeになる。その間は通常攻撃に追加{1}が付与され、{2}と{3}を得て、{4}する。またこのスキルに比例しSpiderlingの最大チャージ数、攻撃力が増加し、Spiderlingが受けるAoEダメージが低減される。")
                .variable(1, MagicDamage, 10, 10, ap(0.3))
                .variable(2, AR, 10, 5)
                .variable(3, MR, 10, 5)
                .variable(4, MS, 10)
                .cd(4);
        VenomousBite.update()
                .active("対象の敵ユニットに飛びつき{1}を与える。")
                .variable(1, MagicDamage, 50, 45, amplify(TargetMissingHealthRatio, 8, 0, ap(0.03)))
                .cd(6)
                .range(475);
        SkitteringFrenzy.update()
                .passive("Spiderlingの{1}する。")
                .variable(1, ASRatio, 5, 5)
                .active("3秒間EliseとSpiderlingの{2}する。また、その間Spiderlingが攻撃を行うたびにEliseの{3}する。")
                .variable(2, ASRatio, 60, 20)
                .variable(3, RestoreHealth, 4, 0, ap(0.02))
                .cd(12);
        Rappel.update()
                .active("EliseとSpiderlingが上空に退避し(ターゲット不可になる)指定の方法で降下する。上空にいる間は射程内の視界を得る地面をクリックした場合: 最大2秒間上空に待機し、初期位置へ降下する。この間、敵ユニットをターゲットし裏側に降下できる。敵ユニットをクリックした場合: 即座に下降し裏側に降り立つ。")
                .cd(26, -2)
                .range(1075);
        HumanForm.update()
                .passive("通常攻撃に追加{1}が付与される。")
                .variable(1, MagicDamage, 10, 10, ap(0.3))
                .active("EliseがHuman Formに変身し射程550のRangedになる。")
                .cd(4);

        /** Evelynn */
        ShadowWalk.update()
                .passive("Evelynnが{2}状態になる。スキルを使うか、ダメージを受けるか与えるかすると、6秒間ステルスが解除された状態になる。敵Championに範囲700まで近づくとステルス状態でも敵Championに視認されるようになる。また、ステルス中は毎秒{1}していく。")
                .variable(1, RestoreMana, 0, 0, amplify(Mana, 0.01))
                .variable(2, Stealth);
        HateSpike.update()
                .active("視界内にいる最も近くにいる敵ユニット1体に向けて棘を放ち、直線状にいる敵ユニットに{1}を与える。Evelynnが敵ユニットをターゲットしている場合は、その対象に向けて棘が放たれる。")
                .variable(1, MagicDamage, 40, 20, ap(0.45), bounusAD(0.5))
                .mana(16, 6)
                .cd(1.5)
                .range(400);
        DarkFrenzy.update()
                .passive("敵Championにスキルを当てるたびに{1}する。この効果は3秒間持続し、最大4スタックする。")
                .variable(1, MS, 4, 4)
                .conditional(1)
                .active("3秒間{2}して、{3}と{4}を得る。敵Championキル/アシスト時に、このスキルの{5}する。")
                .variable(2, MSRatio, 30, 10)
                .variable(3, IgnoreSlow)
                .variable(4, IgnoreUnitCollision)
                .variable(5, CDDecrease)
                .cd(15);
        Ravage.update()
                .active("対象の敵ユニットに2回連続で{1}を与え、3秒間{2}する。")
                .variable(1, MagicDamage, 35, 20, ap(0.5), bounusAD(0.5))
                .variable(2, ASRatio, 60, 15)
                .mana(50, 5)
                .cd(9)
                .range(225);
        AgonysEmbrace.update()
                .active("指定{1}の敵ユニットに{2}と2秒間の{3}を与え、このスキルを命中させた敵Champion毎に6秒間持続する{4}を得る。")
                .variable(1, Radius, 500)
                .variable(2, MagicDamage, 0, 0, amplify(TargetCurrentHealthRatio, 15, 5, ap(0.01)))
                .variable(3, MSSlowRatio, 30, 20)
                .variable(4, Shield, 150, 75)
                .mana(100)
                .cd(150, -30)
                .range(650);

        /** Ezreal */
        RisingSpellForce.update()
                .passive("ユニット(敵味方問わず)にスキルを当てる度に5秒間{1}する。この効果は5回分までスタックする。")
                .variable(1, ASRatio, 10)
                .conditional(1);
        MysticShot.update()
                .active("指定方向に魔法の矢を飛ばし、当たった敵ユニットに{1}を与える。このスキルが命中すると、Ezrealのすべてのスキルの{2}。")
                .variable(1, PhysicalDamage, 35, 20, ap(0.2), ad(1))
                .variable(2, CDDecrease, 1)
                .mana(28, 3)
                .cd(6, -0.5)
                .range(1150);
        EssenceFlux.update()
                .active("指定方向にChampionにのみ当たる貫通するエネルギーを飛ばし、当たった味方Championには5秒間{1}し、敵Championには{2}を与える。")
                .variable(1, ASRatio, 20, 5)
                .variable(2, MagicDamage, 70, 45, ap(0.8))
                .mana(50, 10)
                .cd(9)
                .range(1000);
        ArcaneShift.update()
                .active("指定地点にテレポートし、テレポート先から一番近い敵ユニット({1})1体に{2}を与える。")
                .variable(1, Radius, 750)
                .variable(2, MagicDamage, 75, 50, ap(0.75))
                .mana(90)
                .cd(19, -2)
                .range(475);
        TrueshotBarrage.update()
                .active("1秒詠唱後、指定方向に射程無限の貫通する魔法の矢を飛ばし当たった敵ユニットに{1}を与える。ダメージは敵に当たるごとに10%ずつ減り、最大で30%まで低下する。また飛行中の矢は{2}。")
                .variable(1, MagicDamage, 350, 150, ap(0.9), bounusAD(1))
                .variable(2, Visionable)
                .mana(100)
                .cd(80)
                .range(-1);

        /** Fiddlesticks */
        Dread.update().passive("{1}の敵ユニットに{2}を与える。").variable(1, Radius, 1000).variable(2, Status.MRReduction, 10);
        Terrify.update().active("対象の敵ユニットに{1}を与える。").variable(1, Fear, 1, 0.5).mana(65, 10).cd(15, -1).range(575);
        Drain.update()
                .active("対象の敵ユニットに最大5秒間毎秒{1}を与え、{2}する。敵が離れる({3})と詠唱が中断される。")
                .variable(1, MagicDamage, 60, 30, ap(0.45))
                .variable(2, RestoreHealth, 0, 0, amplify(DealtDamageRatio, 60, 5))
                .variable(3, Radius, 750)
                .mana(80, 10)
                .cd(10, -1)
                .range(475)
                .type(SkillType.Channel);
        DarkWind.update()
                .active("対象の敵ユニットにカラスを飛ばし{1}と{2}を与える。カラスは{4}の敵ユニットに4回まで跳ね返りその度に効果を与える(最大5hit)。この跳ね返りは同一ユニットに何度も跳ね返る。ミニオンとモンスターに対しては{3}を与える。")
                .variable(1, MagicDamage, 65, 20, ap(0.45))
                .variable(2, Silence, 1.2)
                .variable(3, MagicDamage, 97.5, 30, ap(0.675))
                .variable(4, Radius, 450)
                .mana(50, 20)
                .cd(15, -1)
                .range(750);
        Crowstorm.update()
                .active("1.5秒詠唱後に指定地点にテレポートし、{1}の敵ユニットに5秒間毎秒{2}を与える。最大DMは{3}となる。")
                .variable(1, Radius, 600)
                .variable(2, MagicDamage, 125, 100, ap(0.45))
                .variable(3, MagicDamage, 625, 500, ap(2.25))
                .mana(150, 50)
                .cd(150, -10)
                .range(800)
                .type(SkillType.Channel);

        /** Fiora */
        Duelist.update()
                .passive("通常攻撃またはLungeでダメージを与えると、6秒かけて{1}する。対象がChampionの場合、この効果は4回までスタックする。")
                .variable(1, RestoreHealth, 7, 0, amplify(Lv, 1));
        Lunge.update()
                .active("対象の敵ユニットへダッシュし{1}を与える。このスキルは4秒の間、もう1度だけ使用できる。2度目は消費MN無しで使用可能。")
                .variable(1, PhysicalDamage, 40, 25, bounusAD(0.6))
                .mana(60)
                .cd(16, -2)
                .range(600);
        Riposte.update()
                .passive("{1}を得る。")
                .variable(1, AD, 15, 5)
                .active("1.5秒の間に受ける通常攻撃を一度だけ無効化し、その相手に{2}を与える。この効果は一部のミニオンとモンスターには発生しない。")
                .variable(2, MagicDamage, 60, 50, ap(1))
                .mana(45)
                .cd(10, -1);
        BurstOfSpeed.update()
                .active("3秒間{1}する。効果中に通常攻撃を行うかまたはLungeを使用すると3秒間{2}する。移動速度の増加は3回までスタックする。敵Championを倒すとこのスキルの{3}する。")
                .variable(1, ASRatio, 60, 15)
                .variable(2, MSRatio, 7, 2)
                .variable(3, CDDecrease)
                .mana(55)
                .cd(15, -1);
        BladeWaltz.update()
                .active("対象の敵Championにダッシュし{1}を与え、範囲内にいる敵Championをランダムに対象とし4回{1}を与える（合計5回）。最後の攻撃は最初に対象とした敵Championで固定。同一ユニットに複数回DMを与える場合、2回目以降は25%のダメージになる。単一対象への最大DMは{2}。")
                .variable(1, PhysicalDamage, 160, 170, bounusAD(1.15))
                .variable(2, PhysicalDamage, 320, 340, bounusAD(2.3))
                .mana(100)
                .cd(130, -10)
                .range(400);

        /** Fizz */
        NimbleFighter.update()
                .passive("{2}を得て、{1}する。この軽減は防御力計算より先に行われる。")
                .variable(1, AttackDamageReduction, new Per3Level(4, 2))
                .variable(2, IgnoreUnitCollision);
        UrchinStrike.update()
                .active("対象の敵ユニットに追加{1}が付与された通常攻撃を与え、その方向に駆け抜ける。移動距離は固定。")
                .variable(1, MagicDamage, 10, 30, ap(0.6))
                .mana(50, 5)
                .cd(10, -1)
                .range(550);
        SeastoneTrident.update()
                .passive("通常攻撃に{1}が付与される。このダメージは0.5秒毎に3秒間かけて与えられる。(Minionに対しては300DMが上限)")
                .variable(1, MagicDamage, 30, 10, ap(0.35), amplify(TargetMissingHealthRatio, 4, 1))
                .active("5秒間通常攻撃に{2}と{3}を付与する。このダメージはPassiveと重複する。")
                .variable(2, MagicDamage, 10, 5, ap(0.35))
                .variable(3, Wounds, 3)
                .mana(40)
                .cd(10);
        Playful.update()
                .active("指定地点にジャンプする。ジャンプ中はターゲットされない状態になる。0.75秒後にその場に降下し、{1}の敵ユニットに{2}と2秒間{3}を与える。")
                .variable(1, Radius, 250)
                .variable(2, MagicDamage, 70, 50, ap(0.75))
                .variable(3, MSSlowRatio, 40, 5)
                .mana(90, 10)
                .cd(16, -2)
                .range(400);
        Trickster.update()
                .active("Playfulのジャンプ中のみ使用可能。降下する場所を別の指定地点に変更し、その{1}の敵ユニットに{2}を与える。このスキルを使用した場合Playfulのダメージとスローは発生しない。")
                .variable(1, Radius, 150)
                .variable(2, MagicDamage, 70, 50, ap(0.75))
                .cd(16, -2)
                .range(400);
        ChumTheWaters.update()
                .active("指定地点に敵Championのみに命中する魚を投げ、命中した敵Championに魚がくっつき、{1}を与える。その1.5秒後に地面から鮫が現れ、魚が命中した対象を襲い、対象とその周囲({2})の敵ユニットに{3}を与え、{4}後に1.5秒間{5}を与える。魚がくっついていた敵Champion以外のユニットには{4}の代わりに{6}を与える。魚がChampionに当たらなかった場合は指定地点に魚が残り、その地点に鮫が現れる。また魚は視界を確保し、その上を敵Championが通り過ぎると、当たった場合と同様にその敵Championにくっつき、鮫が襲いかかる。")
                .variable(1, MSSlowRatio, 50, 10)
                .variable(2, Radius, 250)
                .variable(3, MagicDamage, 200, 125, ap(1))
                .variable(4, Knockup, 0)
                .variable(5, MSSlowRatio, 50, 10)
                .variable(6, Knockback, 0)
                .mana(100)
                .cd(100, -15)
                .range(1275);

        /** Galio */
        RunicSkin.update().passive("{1}を得る。").variable(1, AP, 0, 0, amplify(MR, 0.5));
        ResoluteSmite.update()
                .active("指定地点に魔法弾を飛ばし、{1}の敵ユニットに{2}と2.5秒間{3}を与える。")
                .variable(1, Radius, 175)
                .variable(2, MagicDamage, 80, 55, ap(0.6))
                .variable(3, MSSlowRatio, 24, 4)
                .mana(60, 5)
                .cd(7)
                .range(900);
        Bulwark.update()
                .active("4秒間対象のChampionは{1}と{2}を得て、効果中にその対象のChampionがダメージを受ける度にGalioの{3}する。自身に使用した場合はダメージを受けてから回復される。")
                .variable(1, AR, 30, 15)
                .variable(2, MR, 30, 15)
                .variable(3, RestoreHealth, 25, 15, ap(0.3))
                .mana(60)
                .cd(13)
                .range(800);
        RighteousGust.update()
                .active("指定方向に風を発生させ、当たった敵ユニットに{1}を与える。このスキルを使用すると指定した方向に5秒間持続する風が残り、その風の進行方向上にいる味方ユニットは{2}する。")
                .variable(1, MagicDamage, 60, 45, ap(0.5))
                .variable(2, MSRatio, 20, 8)
                .mana(70, 5)
                .cd(12)
                .range(1000);
        IdolOfDurand.update()
                .active("{1}の敵ユニットに{2}を与え、さらに2秒間詠唱を行う。詠唱中Galioは{3}して、詠唱中にGalioがダメージを受ける度にこのスキルのダメージが5%ずつ増加していく(最大40%増加)。また詠唱中にBulwarkを使用することが出来る。詠唱完了またはキャンセル時に、周囲の敵ユニットのTauntを解除するとともに{4}を与える。最大DMは{5}。")
                .variable(1, Radius, 600)
                .variable(2, Taunt, 2)
                .variable(3, DamageReductionRatio, 50)
                .variable(4, MagicDamage, 220, 110, ap(0.6))
                .variable(5, MagicDamage, 308, 154, ap(0.84))
                .mana(100, 50)
                .cd(170, -20)
                .type(SkillType.Channel);

        /** Gangplank */
        GrogSoakedBlade.update()
                .passive("通常攻撃時に対象にスタックを付与し、1スタックにつき毎秒{1}と{2}を与える。この効果は3秒間持続し、3回までスタックする。")
                .variable(1, MagicDamage, 3, 0, amplify(Lv, 1))
                .variable(2, MSSlowRatio, 7);
        Parrrley.update()
                .active("対象の敵ユニットに{1}（クリティカルあり）を与える。このスキルで敵ユニットを倒すと消費マナの半分のマナが回復し、追加で{2}得る。")
                .variable(1, PhysicalDamage, 20, 25, ad(1))
                .variable(2, Gold, 4, 1)
                .mana(50, 5)
                .cd(5)
                .range(625);
        RemoveScurvy.update()
                .active("自身のCC(Stun, Slow, Taunt, Fear, Snare, Silence, Suppression, Blind)を取り除き{1}する。StunなどのDisable中でも使用可能。")
                .variable(1, RestoreHealth, 80, 70, ap(1))
                .mana(65)
                .cd(22, -1);
        RaiseMorale.update()
                .passive("{1}を得て、{2}する。")
                .variable(1, AD, 8, 2)
                .variable(2, MSRatio, 3, 1)
                .active("7秒間{7}を得て、{3}する。{6}内の味方Championは{4}を得て{5}する。効果中はPassiveの効果が無効になる。")
                .variable(6, Radius, 1200)
                .variable(7, AD, 12, 7)
                .variable(3, MSRatio, 8, 3)
                .variable(4, AD, 6, 3.5)
                .variable(5, MSRatio, 4, 1.1)
                .mana(50, 5)
                .cd(25);
        CannonBarrage.update()
                .active("MAP内の指定した地点に砲撃を行い、その地点の視界({1})を得る。範囲内には7秒間砲弾が降り注ぎ（場所はランダム、計25発）、着弾した{2}にいる敵ユニットに{4}と1.25秒間{3}を与える。")
                .variable(1, Radius, 575)
                .variable(2, Radius, 275)
                .variable(3, MSSlowRatio, 25)
                .variable(4, MagicDamage, 75, 45, ap(0.2))
                .mana(100)
                .cd(120, -5)
                .range(-1);

        /** Garen */
        Perseverance.update()
                .passive("9秒間敵Minion以外からダメージを受けない状態が続くと、以降敵Minion以外からダメージを受けるまで毎秒{1}し続ける。")
                .variable(1, RestoreHealth, 0, 0, amplify(Health, 0.005));
        DecisiveStrike.update()
                .active("{1}間{2}し、スキル使用後6秒間に行った次の通常攻撃に追加{3}と{4}が付与される。またこのスキル使用時に自身にかかっているスローを解除する。")
                .variable(1, Time, 1.5, 0.75)
                .variable(2, MSRatio, 35)
                .variable(3, PhysicalDamage, 30, 25, ad(0.4))
                .variable(4, Silence, 1.5, 0.25)
                .cd(8);
        Courage.update()
                .passive("{1}し{2}する。")
                .variable(1, ARRatio, 20)
                .variable(2, MRRatio, 20)
                .active("{3}間{4}し、{5}を得る。")
                .variable(3, Time, 2, 1)
                .variable(4, DamageReductionRatio, 30)
                .variable(5, Tenacity, 30)
                .cd(24, -1);
        Judgment.update()
                .active("Garenが3秒間回転し、その間近くの敵ユニットに0.5秒毎に{1}を与える(最大6hit)。このスキルにはクリティカル判定があり、クリティカル時は追加{2}を与える。回転中は{3}を得るが、敵Minionをすり抜けている間は移動速度が20%低下する。Minionに与えるダメージは通常の75%。")
                .variable(1, PhysicalDamage, 10, 12.5, ad(0.35))
                .variable(2, PhysicalDamage, 0, 0, ad(0.175))
                .variable(3, IgnoreUnitCollision)
                .cd(13, -1);
        DemacianJustice.update()
                .active("対象の敵Championに{1}を与える。")
                .variable(1, MagicDamage, 175, 175, amplify(TargetMissingHealthRatio, new Fixed(new double[] {28.6,
                        33.3, 40})))
                .cd(160, -40)
                .range(400);

        /** Gragas */
        HappyHour.update().passive("スキル使用後に4秒かけて{1}する。").variable(1, RestoreHealth, 0, 0, amplify(Health, 0.02));
        BarrelRoll.update()
                .active("指定地点に樽を転がし、爆発時に{1}の敵ユニットに{2}と3秒間{3}を与える。樽は5秒経つか、スキルを再度使用すると爆発する。")
                .variable(1, Radius, 375)
                .variable(2, MagicDamage, 85, 50, ap(0.9))
                .variable(3, ASSlowRatio, 20, 5)
                .mana(80, 10)
                .cd(12, -1)
                .range(1100);
        DrunkenRage.update()
                .active("{1}する。さらに1秒詠唱後に20秒間{2}を得て、{3}するようになる。")
                .variable(1, RestoreMana, 30, 15)
                .variable(2, AD, 30, 10)
                .variable(3, DamageReductionRatio, 10, 2)
                .cd(25);
        BodySlam.update()
                .active("指定方向に突進し、衝突した敵ユニットとその周囲にいる敵ユニットに{1}と2.5秒間{2}を与える。衝突時に突進は止まる。衝突地点に複数の敵ユニットがいた場合、{3}を与える。")
                .variable(1, MagicDamage, 80, 40, ap(0.5), ad(0.66))
                .variable(2, MSSlowRatio, 35)
                .variable(3, MagicDamage, 50, 25, ap(0.5))
                .mana(75)
                .cd(7)
                .range(650);
        ExplosiveCask.update()
                .active("指定地点に爆発する樽を投げ、{1}内の敵ユニットに{2}を与え、{3}させる。")
                .variable(1, Radius, 400)
                .variable(2, MagicDamage, 200, 125, ap(1))
                .variable(3, Knockback, 800)
                .mana(100, 25)
                .cd(100, -10)
                .range(1050);

        /** Graves */
        TrueGrit.update()
                .passive("戦闘状態になると1秒ごとにスタックが1増加し、スタック数に比例して{1}と{2}を得る。この効果は10回までスタックし、3秒間戦闘を行わないとスタックが0になる。レベル1、7、13で1スタック毎の増加AR,MRが上昇する。")
                .variable(1, AR, new Per6Level(1, 1))
                .variable(2, MR, new Per6Level(1, 1));
        Buckshot.update()
                .active("指定方向扇形の範囲に貫通する弾を3発発射し、当たった敵ユニットに{1}を与える。同一対象に対して複数hitし、2発目以降は本来の35%分の物理DMを与える(3発hitで合計{2})。")
                .variable(1, PhysicalDamage, 60, 35, bounusAD(0.8))
                .variable(2, PhysicalDamage, 102, 59.5, bounusAD(1.36))
                .mana(60, 10)
                .cd(12, -1)
                .range(750);
        Smokescreen.update()
                .active("指定地点にスモーク弾を発射し範囲内の敵ユニットに{1}を与え、4秒間持続する煙幕を残す。煙幕内にいる敵Championに視界低下とス{2}を与える。")
                .variable(1, MagicDamage, 60, 50, ap(0.6))
                .variable(2, MSSlowRatio, 15, 5)
                .mana(70)
                .cd(20, -1)
                .range(700);
        Quickdraw.update()
                .active("指定方向にダッシュし4秒間{1}する。このスキルは自身が通常攻撃を行う毎に{2}する。対象が建物の場合は無効。")
                .variable(1, ASRatio, 30, 10)
                .variable(2, CDDecrease, 1)
                .mana(50)
                .cd(22, -2)
                .range(425);
        CollateralDamage.update()
                .active("指定方向にMinionを貫通する爆発弾を発射し、hitした敵ユニットに{1}を与える。敵Championにhitするか最大距離飛ぶとターゲット後方に扇形に爆発が広がり、範囲内の敵ユニットに{2}を与える。")
                .variable(1, PhysicalDamage, 250, 100, bounusAD(1.4))
                .variable(2, PhysicalDamage, 140, 110, bounusAD(1.2))
                .mana(100)
                .cd(100, -10)
                .range(1000);

        /** Hecarim */
        Warpath.update()
                .passive("{2}と{1}を得る。レベル1、3、6、9、12、15、18で増加割合が上昇する。")
                .variable(1, AD, 0, 0, amplify(BounusMS, new Per3LevelAdditional(0.1, 0.025)))
                .variable(2, IgnoreUnitCollision);
        Rampage.update()
                .active("武器を振り回し{2}の敵ユニットに{1}を与える。このスキルが敵ユニットに命中した場合、Hecarimは短時間の間1スタックを得て、1スタックにつきこのスキルの{4}する(最大2スタック)。スタックは6秒間増加がないと0になる。ミニオンやモンスターには{3}を与える。")
                .variable(1, PhysicalDamage, 50, 35, bounusAD(0.6))
                .variable(2, Radius, 200)
                .variable(3, PhysicalDamage, 18.5, 11.5, bounusAD(0.2))
                .variable(4, CDDecrease, 1)
                .mana(25)
                .cd(4);
        SpiritOfDread.update()
                .active("4秒間{1}の敵ユニットに毎秒{2}を与える。この効果を受けている敵ユニットがダメージを受けた場合、そのダメージの値に応じて{3}する。")
                .variable(1, Radius, 575)
                .variable(2, MagicDamage, 20, 11.25, ap(0.2))
                .variable(3, RestoreHealth, 0, 0, amplify(DealtDamageRatio, 10, 5))
                .mana(50, 10)
                .cd(14);
        DevastatingCharge.update()
                .active("3秒間{1}して(最大75%)、その後1秒間その移動速度を維持する。また次の通常攻撃のダメージはこのスキルを使用してからHecarimが移動した距離に比例し最小で{3}、最大で{4}を与えるようになり、{2}が付与される。")
                .variable(1, MSRatio, 20, 0, amplify(Duration, 18.3))
                .variable(2, Knockback, 300)
                .variable(3, PhysicalDamage, 40, 35, bounusAD(0.5))
                .variable(4, PhysicalDamage, 80, 70, bounusAD(1))
                .mana(60)
                .cd(24, -2);
        OnslaughtOfShadows.update()
                .active("亡霊の騎兵隊を従え指定地点に突撃し、Hecarimと騎兵に触れた敵ユニットに{1}を与える。指定した地点に到着すると衝撃波を放ち、{2}の敵ユニットに{3}と{4}を与える。Hecarimが指定した地点に到着しても、騎兵隊は常に最大距離まで突撃する。" + Terrified + "に陥ったユニットは強制的にHecarimから遠ざかるように移動する。この時、Hecarimとの距離に比例して移動速度が変化する。")
                .variable(1, MagicDamage, 100, 100, ap(0.8))
                .variable(2, Radius, 0)
                .variable(3, MagicDamage, 50, 75, ap(0.4))
                .variable(4, Terrified, 1)
                .mana(100)
                .cd(140, -20)
                .range(1000);

        /** Heimerdinger */
        TechmaturgicalRepairBots.update()
                .passive("{1}の味方ユニットとTurretは{2}を得る。レベル1、6、11、15でベースの増加HRegが上昇する。")
                .variable(1, Radius, 800)
                .variable(2, Hreg, new Per5Level(10, 5));
        H28GEvolutionTurret.update()
                .active("指定地点にTurretを設置する。使用時にスタックを消費する。設置後6秒間はTurretの攻撃速度が1.5倍になる。{1}毎にスタックが1つ増加し最大2つまでスタックされる。スタック増加時間はCD低減の影響を受ける。Turretが塔に与えるダメージは半分になる。Debuff(CCのみ)を無効化、Heimerdingerが攻撃するor攻撃されている場合、その対象を優先で攻撃。Lv2.攻撃したユニットに{6}と{7}を与える。この効果は2秒間持続し、50回までスタックする。Lv3.Turretの最大スタック数と設置できる上限が2に増える。Lv4.Turretの最大HP+125。Lv5.50%のスプラッシュダメージが付与される。　HP:{2} ダメージ:{3} 射程:525 AR:{4} MR:{5} AS:1.25 視界:625")
                .variable(1, CDRAwareTime, 25)
                .variable(2, Count, 295, 0, amplify(Lv, 15))
                .variable(3, MagicDamage, 30, 8, ap(0.2))
                .variable(4, Count, 30, 0, amplify(Lv, 1))
                .variable(5, Count, 80, 0, amplify(Lv, 1))
                .variable(6, ARReduction, 1)
                .variable(7, MRReduction, 1)
                .mana(70, 10)
                .cd(1)
                .range(250);
        HextechMicroRockets.update()
                .active("視界内にいる最も近い敵ユニット3体に{1}を与える。")
                .variable(1, MagicDamage, 85, 50, ap(0.55))
                .mana(65, 20)
                .cd(10)
                .range(1000);
        CH1ConcussionGrenade.update()
                .active("指定地点に手榴弾を投げ、破裂した{1}にいる敵ユニットに{2}と{3}を与え、真ん中のユニットにはさらに{4}を与える。また指定地点の視界を得る。")
                .variable(1, Radius, 250)
                .variable(2, MagicDamage, 80, 55, ap(0.6))
                .variable(3, Blind, 1, 0.5)
                .variable(4, Stun, 1)
                .mana(80, 10)
                .cd(13, -1)
                .range(925);
        UPGRADE.update()
                .passive("{1}を得る。")
                .variable(1, CDR, 10, 5)
                .active("設置したTurretのHPが最大まで回復し、10秒間Turretの攻撃に{2}が付与され、Hextech Micro-Rocketsの同時攻撃可能数が5体に増加し、CH-1 Concussion Grenadeの弾速が増加(+250)する。")
                .variable(2, MSSlowRatio, 20, 5)
                .mana(90)
                .cd(120, -15);

        /** Irelia */
        IonianFervor.update()
                .passive("Ireliaの視界内(範囲1200)に敵Championがいる数に応じて{1}を得る。効果の上限は最大3人まで。")
                .variable(1, Tenacity, new Fixed(new double[] {10, 25, 40}))
                .conditional(1);
        Bladesurge.update()
                .active("対象の敵ユニットに突進し、{1}を与える。このスキルで敵を倒したとき、このスキルの{2}されManaが35回復する。")
                .variable(1, PhysicalDamage, 20, 30, ad(1))
                .variable(2, CDDecrease)
                .mana(60, 5)
                .cd(14, -2)
                .type(SkillType.OnHitEffectable)
                .range(650);
        HitenStyle.update()
                .passive("通常攻撃を行う度に{1}する。")
                .variable(1, RestoreHealth, 5, 2)
                .active("6秒間通常攻撃に{2}が付与され、PassiveのHP回復量が倍になる。")
                .variable(2, TrueDamage, 15, 15)
                .mana(40)
                .cd(15);
        EquilibriumStrike.update()
                .active("対象の敵ユニットに{1}を与える。対象の残HP%がIreliaより高かった場合{2}を与え、低かった場合は{4}間{3}を与える。")
                .variable(1, MagicDamage, 80, 50)
                .variable(2, Stun, 1, 0.25)
                .variable(3, MSSlowRatio, 60, 0)
                .variable(4, Time, 1, 0.25)
                .mana(50, 5)
                .cd(8)
                .range(425);
        TranscendentBlades.update()
                .active("指定方向に貫通する刃を飛ばし、当たった敵ユニットに{1}を与える。このスキルは15秒の間、4回まで連続して使用できる(但し、一度使用する度に0.5秒のCDが発生する)。2〜4発目はマナコスト無しで使用可能。ミニオンやモンスターにダメージを与えると{2}し、Championにダメージを与えると{3}する。")
                .variable(1, PhysicalDamage, 80, 40, ap(0.5), bounusAD(0.6))
                .variable(2, RestoreHealth, 0, 0, amplify(DealtDamageRatio, 10))
                .variable(3, RestoreHealth, 0, 0, amplify(DealtDamageRatio, 25))
                .mana(100)
                .cd(70, -10)
                .range(1000);

        /** Janna */
        Tailwind.update().passive("すべての味方Championは{1}する。").variable(1, MSRatio, 3);
        HowlingGale.update()
                .active("指定方向に竜巻を発生させ、触れた敵ユニットに{1}と{2}を与える。竜巻は設置後に再度スキル使用ですぐに飛ばすことができるが、溜めた時間に比例して魔法DM、射程距離、打ち上げ時間が増加する。")
                .variable(1, MagicDamage, 60, 25, ap(0.75), amplify(Duration, 25))
                .variable(2, Knockup, 0.8, 0, amplify(Duration, 0.1))
                .mana(90, 15)
                .cd(14, -1)
                .range(1100);
        Zephyr.update()
                .passive("{1}し{2}を得る。")
                .variable(1, MSRatio, 4, 3)
                .variable(2, IgnoreUnitCollision, 0)
                .active("対象の敵ユニットに{3}と3秒間{4}を与える。またこのスキルがCDの間はPassiveの効果が無くなる。")
                .variable(3, MagicDamage, 60, 55, ap(0.6))
                .variable(4, MSSlowRatio, 24, 6)
                .mana(40, 10)
                .cd(12, -1)
                .range(600);
        EyeOfTheStorm.update()
                .active("対象の味方Championか塔に5秒間{1}を付与する。シールドが持続している間は対象は{2}を得る。")
                .variable(1, Shield, 80, 40, ap(0.9))
                .variable(2, AD, 14, 9)
                .mana(70, 10)
                .cd(10)
                .range(800);
        Monsoon.update()
                .active("{1}の敵ユニットを{2}して4秒間詠唱する。詠唱中は{1}の味方ユニットは毎秒{3}する。")
                .variable(1, Radius, 725)
                .variable(2, Knockback, 875)
                .variable(3, RestoreHealth, 70, 40, ap(0.35))
                .mana(150, 75)
                .cd(150, -15);

        /** Jarvan IV */
        MartialCadence.update()
                .passive("通常攻撃に{1}(最大400DM)が付与される。同一の対象には6秒に一度しか発動しない。レベル1、7、13で追加物理DMの増加値が上昇する。")
                .variable(1, PhysicalDamage, 0, 0, amplify(TargetCurrentHealthRatio, new Per6Level(6, 2)));
        DragonStrike.update()
                .active("槍を突き出して直線上の敵ユニットに{1}を与え、3秒間{2}を与える。また、Demacian Standardの旗にヒットした場合、旗の位置まで突進し、進路上の敵ユニットに{3}を与える。")
                .variable(1, PhysicalDamage, 70, 45, bounusAD(1.1))
                .variable(2, ARReductionRatio, 10, 4)
                .variable(3, Knockup, 0.75)
                .mana(45, 5)
                .cd(10, -1)
                .range(770);
        GoldenAegis.update()
                .active("5秒間{1}を付与すると同時に、{2}の敵ユニットに２秒間{3}を与える。")
                .variable(1, Shield, 50, 40, amplify(EnemyChampion, 20, 5))
                .variable(2, Radius, 600)
                .variable(3, MSSlowRatio, 15, 5)
                .mana(65)
                .cd(20, -2);
        DemacianStandard.update()
                .passive("{1}し{2}を得る。")
                .variable(1, ASRatio, 10, 3)
                .variable(2, AR, 10, 3)
                .active("指定地点に旗を投げ、{3}の敵ユニットに{4}を与える。旗は8秒間その場に残り視界を確保するとともに、{5}の味方ChampionにPassiveの効果を与える。(Jarvan IV自身はPassiveと合わせて倍の効果を受ける)")
                .variable(3, Radius, 150)
                .variable(4, MagicDamage, 60, 45, ap(0.8))
                .variable(5, Radius, 1200)
                .mana(55)
                .cd(13)
                .range(830);
        Cataclysm.update()
                .active("対象の敵Championまで跳躍して{1}を与え、3.5秒間その周囲に通行不可能の円形の地形を作る。再度このスキルを使用すると地形を破壊できる。")
                .variable(1, PhysicalDamage, 200, 125, bounusAD(1.5))
                .mana(100, 25)
                .cd(120, -15)
                .range(650);

        /** Jax */
        RelentlessAssault.update()
                .passive("通常攻撃を行う度にスタックが1増加し、スタック数に比例して{1}する(最大6スタック)。スタックは2.5秒増加がないと0になる。増加値は3レベル毎に上昇する。")
                .variable(1, ASRatio, new Per3Level(4, 2))
                .conditional(1);
        LeapStrike.update()
                .active("対象のユニットまで飛びかかる。対象が敵ユニットの場合、{1}を与える。")
                .variable(1, PhysicalDamage, 70, 40, ap(0.6), bounusAD(1))
                .mana(65)
                .cd(10, -1)
                .range(700);
        Empower.update()
                .active("使用後に最初に行った通常攻撃かLeap Strikeに追加{1}を付与する。建物には無効。")
                .variable(1, MagicDamage, 40, 35, ap(0.6))
                .mana(30)
                .cd(7, -1);
        CounterStrike.update()
                .active("2秒間、Jaxが受けるタワー以外の通常攻撃を無効化し、AoEダメージを25%低減、さらに効果終了時に{1}の敵ユニットに{2}と{3}を与える。スキルを使用してから1秒経つと再使用できるようになり、任意に効果を終了できる。通常攻撃を回避する度にこのスキルのダメージが20%ずつ増加する(上限は100%、最大で2倍ダメージ)。")
                .variable(1, Radius, 375)
                .variable(2, PhysicalDamage, 50, 25, bounusAD(0.5))
                .variable(3, Stun, 1)
                .mana(70, 5)
                .cd(18, -2);
        GrandmastersMight.update()
                .passive("通常攻撃3回目ごとに追加で{1}を与える。建物には無効。")
                .variable(1, MagicDamage, 100, 60, ap(0.7))
                .active("8秒間{2}と{3}を得る。")
                .variable(2, AR, 25, 10, bounusAD(0.3))
                .variable(3, MR, 25, 10, ap(0.2))
                .mana(100)
                .cd(80);

        /** Jayce */
        HextechCapacitor.update()
                .passive("Transformを使用すると1.25秒の間{1}し、{2}を得る。")
                .variable(1, MS, 40)
                .variable(2, IgnoreUnitCollision)
                .conditional(1);
        ToTheSkies.update()
                .active("対象の敵ユニットに飛びかかり、対象と周囲の敵ユニットに{1}と2秒間{2}を与える。")
                .variable(1, PhysicalDamage, 20, 45, bounusAD(1))
                .variable(2, MSSlowRatio, 30, 5)
                .mana(40, 5)
                .cd(16, -2)
                .range(600);
        ShockBlast.update()
                .active("指定方向に雷のオーブを飛ばし、敵ユニットに命中するか一定距離で爆発し、周囲の敵ユニット{1}を与える。オーブがAcceleration Gateによって生成されたゲートを通過した場合、弾速、射程距離、爆発範囲、与えるDMが各40%増加する。{2}")
                .variable(1, PhysicalDamage, 60, 55, bounusAD(1.2))
                .variable(2, PhysicalDamage, 84, 77, bounusAD(1.68))
                .mana(55, 5)
                .cd(8)
                .range(1050);
        LightningField.update()
                .passive("通常攻撃ごとに{1}する。")
                .variable(1, RestoreMana, 6, 2)
                .active("4秒間雷のオーラを身にまとい、{2}の敵ユニットに毎秒{3}を与える。")
                .variable(2, Radius, 285)
                .variable(3, MagicDamage, 25, 17.5, ap(0.25))
                .mana(40)
                .cd(10);
        HyperCharge.update()
                .active("Jayceの攻撃速度が最大まで上昇する。3回通常攻撃を行うと効果が解消される。また効果中は通常攻撃で与えるダメージが{1}%になる。")
                .variable(1, Percentage, 70, 15)
                .mana(40)
                .cd(14, -2);
        ThunderingBlow.update()
                .active("対象の敵ユニットに{1}と短い距離のノックバックを与える。ミニオンやモンスターに対しては{2}が上限。")
                .variable(1, MagicDamage, 0, 0, bounusAD(1), amplify(TargetMaxHealthRatio, 8, 3))
                .variable(2, MagicDamage, 200, 100)
                .mana(40)
                .cd(14, -1)
                .range(240);
        AccelerationGate.update()
                .active("4秒間持続するゲート（通りぬけ可能）を生成し、触れた味方ユニットは3秒間{1}する。移動速度は3秒かけて元に戻る。")
                .variable(1, MSRatio, 30, 5)
                .mana(50)
                .cd(14, -1)
                .range(650);
        TransformMercuryCannon.update()
                .active("Jayceの射程が500(Ranged)になる。また、次の通常攻撃は５秒間{1}と{2}を与える。")
                .variable(1, ARReductionRatio, 10, 5)
                .variable(2, MRReductionRatio, 10, 5)
                .cd(6);
        TransformMercuryHammer.update()
                .active("Jayceの射程が125(Melee)になり、その間は{1}と{2}を得る。また、次の通常攻撃に追加{3}を付与する。")
                .variable(1, AR, 5, 10)
                .variable(2, MR, 5, 10)
                .variable(3, MagicDamage, 20, 40)
                .cd(6);

        /** Karma */
        InnerFlame.update()
                .passive("{1}を得る。レベル1、3、6、9、12、15で最大値が上昇する。")
                .variable(1, AP, 0, 0, amplify(MissingHealthPercentage, new Per3LevelForKarma(0.3, 0.2)));
        HeavenlyWave.update()
                .active("指定方向扇形60°の{1}の敵ユニットに{2}を与える。Mantra Bonus:自身と効果範囲内の味方ユニットは{3}する。回復量は対象のHP残量によって変化する。")
                .variable(1, Radius, 600)
                .variable(2, MagicDamage, 70, 40, ap(0.6))
                .variable(3, RestoreHealth, 35, 20, amplify(MissingHealthRatio, 0.05, 0, ap(0.02)))
                .mana(70, 5)
                .cd(6);
        SpiritBond.update()
                .active("対象のユニットと自身を繋ぐビームを発生させる。ビームは5秒間持続し、自身及び味方ユニットは{1}し、敵ユニットには{2}を与える。ビームに触れたChampion(敵味方問わず)にも同様の効果を与え、それが敵ユニットだった場合は更に{3}を与える。ビームを繋ぐ対象がステルス状態または距離1000まで離れた場合、効果が途切れる。Mantra Bonus:MS増加/MS低下の効果が2倍になる。")
                .variable(1, MSRatio, 10, 2)
                .variable(2, MSSlowRatio, 10, 2)
                .variable(3, MagicDamage, 80, 45, ap(0.7))
                .mana(65, 10)
                .cd(15, -1)
                .range(800);
        SoulShield.update()
                .active("対象の味方ユニットに5秒間持続する{1}を付与する。Mantra Bonus:味方ユニットにシールドを付与した際、その味方ユニットの{2}にいる敵ユニットに{3}を与える。")
                .variable(1, Shield, 80, 40, ap(0.8))
                .variable(2, Radius, 600)
                .variable(3, MagicDamage, 80, 40, ap(0.8))
                .mana(80, 10)
                .cd(10)
                .range(650);
        Mantra.update()
                .active("次に使用するスキルにMantra Bonusを付与する。Lv1から使用でき、スキルポイントを割り振ることはできない。{1}毎にスタック数が1つ増加し最大で2つまでスタックされる。スタック増加時間はCD低減の影響を受ける。レベル1、7、13でスタック増加時間が短縮される。")
                .variable(1, CDRAwareTime, new Per6Level(30, -5))
                .cd(0.25);

        /** Karthus */
        DeathDefied.update().passive("死亡後7秒間スキルが使用可能。この状態ではスキルコストがなくなる。");
        LayWaste.update()
                .active("指定地点を0.5秒後に爆発させ{1}の敵ユニットに{2}を与える。対象が1体の場合は{3}を与える。また、指定地点の{4}。")
                .variable(1, Radius, 100)
                .variable(2, MagicDamage, 40, 20, ap(0.3))
                .variable(3, MagicDamage, 80, 40, ap(0.6))
                .variable(4, Visionable)
                .mana(20, 6)
                .cd(1)
                .range(875);
        WallOfPain.update()
                .active("指定地点に{3}の通りぬけ可能な壁を5秒間生成し、触れた敵ユニットに５秒間{1}と{2}を与える。スローの効果は5秒かけて元に戻る。また、指定地点の{4}。")
                .variable(1, MRReductionRatio, 15)
                .variable(2, MSSlowRatio, 40, 10)
                .variable(3, Length, 800, 100)
                .variable(4, Visionable)
                .mana(100)
                .cd(18)
                .range(1000);
        Defile.update()
                .passive("敵ユニットを倒すと{1}する。")
                .variable(1, RestoreMana, 20, 7)
                .active("{2}の敵ユニットに毎秒{3}を与える。")
                .variable(2, Radius, 550)
                .variable(3, MagicDamage, 30, 20, ap(0.25))
                .mana(30, 12)
                .cd(0.5)
                .type(SkillType.Toggle);
        Requiem.update()
                .active("3秒詠唱後にすべての敵Championに{1}を与える。")
                .variable(1, MagicDamage, 250, 150, ap(0.6))
                .mana(150, 25)
                .cd(200, -20)
                .range(-1);

        /** Kassadin */
        VoidStone.update()
                .passive("自身が受ける{1}して、4秒間軽減した分のダメージを攻撃速度(%)に加算する。")
                .variable(1, MagicDamageReductionRatio, 15);
        NullSphere.update()
                .active("対象の敵ユニットに{1}と{2}を与える。")
                .variable(1, MagicDamage, 80, 50, ap(0.7))
                .variable(2, Silence, 1, 0.4)
                .mana(70, 10)
                .cd(9)
                .range(650);
        NetherBlade.update()
                .passive("通常攻撃ごとに{1}する。対象がChampionの場合は{2}する。")
                .variable(1, RestoreMana, 8, 3)
                .variable(2, RestoreMana, 16, 6)
                .active("5秒間、通常攻撃に追加{3}が付与される。建物には無効。")
                .variable(3, MagicDamage, 30, 15, ap(0.3))
                .mana(25)
                .cd(12);
        ForcePulse.update()
                .active("指定方向扇形80°の{1}の敵ユニットに{2}と3秒間{3}を与える。近くのChampion（敵味方自分問わず）がスキルを使用するとスタックが増え、6スタックまで溜まると使用可能。スキル使用時にスタックは0になる。")
                .variable(1, Radius, 700)
                .variable(2, MagicDamage, 80, 50, ap(0.7))
                .variable(3, MSSlowRatio, 30, 5)
                .mana(80)
                .cd(6);
        Riftwalk.update()
                .active("指定地点にテレポートし、テレポート先の{1}の敵ユニットに{2}を与える。スキル使用時にスタックが増加し、1スタックごとに消費MNと魔法DMが増加していく。（最大10スタック）スタックは8秒間増加がないと0になる。")
                .variable(1, Radius, 150)
                .variable(2, MagicDamage, 60, 10, ap(0.8), amplify(Stack, 60, 10))
                .cost(Mana, new Diff(100, 0, 1), amplify(Stack, 100))
                .cd(7, -1)
                .range(700);

        /** Katarina */
        Voracity.update().passive("敵Championキル/アシスト時に、すべてのスキルの{1}する。").variable(1, CDDecrease, 15);
        BouncingBlade.update()
                .active("対象の敵ユニットにナイフを飛ばし{1}と４秒間Debuffを与える。ナイフは近くの敵ユニット({2})に4回まで跳ね返り、その度にダメージとDebuffを与える。ナイフが与えるダメージは跳ね返る度に10%低下する。Debuffが付与された敵ユニットにKatarinaが通常攻撃またはスキルでダメージを与えると、付与されたDebuffを消費して{3}を与える。")
                .variable(1, MagicDamage, 60, 25, ap(0.15))
                .variable(2, Radius, 400)
                .variable(3, MagicDamage, 15, 15, ap(0.15))
                .cd(10, -0.5)
                .range(675);
        SinisterSteel.update()
                .active("{1}にいる敵ユニットに{2}を与える。このスキルが敵Championに命中した場合、1秒間{3}する。")
                .variable(2, MagicDamage, 40, 35, ap(0.25))
                .variable(1, Radius, 375)
                .variable(3, MSRatio, 15, 5)
                .cd(4);
        Shunpo.update()
                .active("対象のユニットの後方までワープし、それが敵ユニットの場合は{1}を与える。またこのスキル使用後、Katarinaが受ける{2}する。この効果は1.5秒間持続する。")
                .variable(1, MagicDamage, 60, 25, ap(0.5))
                .variable(2, DamageReductionRatio, 15)
                .cd(12, -1.5)
                .range(700);
        DeathLotus.update()
                .active("Katarinaが最大2秒間回転する。その間は0.2秒毎に{2}にいる最も近い敵Champion3体にナイフを連続で飛ばし、都度{1}と3秒間HP回復量-50%を与える。敵一体に与える最大DMは{3}。")
                .variable(1, MagicDamage, 40, 10, ap(0.2), bounusAD(0.3))
                .variable(2, Radius, 550)
                .variable(3, MagicDamage, 400, 100, ap(2), bounusAD(3))
                .cd(60, -5)
                .type(SkillType.Channel);

        /** Kayle */
        HolyFervor.update()
                .passive("敵Championに通常攻撃を行う度に、{1}と{2}を与える。この効果は5秒間持続し、5回までスタックする。")
                .variable(1, ARReductionRatio, 3)
                .variable(2, MRReductionRatio, 3);
        Reckoning.update()
                .active("対象の敵ユニットに{1}と４秒間{2}を与える。このスキルのスローがかかった敵ユニットに対しては、Kayleが対象のユニットに与える{3}する。")
                .variable(1, MagicDamage, 60, 50, ap(1), bounusAD(1))
                .variable(2, MSSlowRatio, 35)
                .variable(3, DamageRatio, 6, 1)
                .mana(70, 5)
                .cd(8)
                .range(650);
        DivineBlessing.update()
                .active("対象の味方Championは{1}し、3秒間{2}する。")
                .variable(1, RestoreHealth, 60, 45, ap(0.35))
                .variable(2, MSRatio, 18, 3)
                .mana(60, 10)
                .cd(15)
                .range(1000);
        RighteousFury.update()
                .active("10秒間Kayleの通常攻撃の射程が525に伸びる(Ranged)。更に通常攻撃に追加{1}が付与され、スプラッシュ効果{2}が付く。塔を攻撃する時はスプラッシュ効果は発生しない。")
                .variable(1, MagicDamage, 20, 10, ap(0.4), amplify(AD, 0.2, 0.05))
                .variable(2, Radius, 300)
                .mana(45)
                .cd(16);
        Intervention.update()
                .active("対象の味方Championを{1}間無敵(ダメージ無効)にする。")
                .variable(1, Time, 2, 0.5)
                .mana(100, -25)
                .cd(90, -15)
                .range(1200);

        /** Kennen */
        MarkOftheStorm.update()
                .passive("スキルヒット時対象に雷スタックを追加する。スタックが3つ溜まると対象を{1}させ、{2}する。スタックは8秒間増加がないと0になる。同一の敵Championを7秒以内に2度スタンさせると、{3}を与える。")
                .variable(1, Stun, 1)
                .variable(2, RestoreEnergy, 25)
                .variable(3, Stun, 0.5);
        ThunderingShuriken.update()
                .active("指定方向に手裏剣を飛ばし、当たった敵ユニットに{1}と雷スタックを与える。")
                .variable(1, MagicDamage, 75, 40, ap(0.75))
                .cd(8, -1)
                .range(900)
                .cost(Energy, 65, -5);
        ElectricalSurge.update()
                .passive("5回毎の通常攻撃時に追加{1}と雷スタックを与える効果を追加する。")
                .variable(1, MagicDamage, 0, 0, ad(0.4))
                .active("{3}の雷スタックの付与されている敵ユニットに{2}と雷スタックを与える。")
                .variable(3, Radius, 800)
                .variable(2, MagicDamage, 65, 30, ap(0.55))
                .cd(14, -2)
                .cost(Energy, 45, 0);
        LightningRush.update()
                .active("2.5秒間{1}し、{6}を得る。この間は通常攻撃が不可能になり、Kennenに触れた敵ユニットに{2}と雷スタックを与え、一度だけ{3}する。また、このスキルを使用すると4秒間{4}と{5}を得る。Minionに与えるダメージは半分。")
                .variable(1, MS, 230)
                .variable(2, MagicDamage, 85, 40, ap(0.6))
                .variable(3, RestoreEnergy, 40)
                .variable(4, AR, 10, 10)
                .variable(5, MR, 10, 10)
                .variable(6, IgnoreUnitCollision)
                .cd(10, -1)
                .cost(Energy, 100, -5);
        SlicingMaelstrom.update()
                .active("{1}に嵐を発生させ、{4}間{2}毎に範囲内にいる敵Champion一人をランダムに雷を落とし{3}と雷スタックを与える。同一の対象には3回までヒットし、最大DMは{5}。また、範囲内に複数の対象がいる場合、同一の対象に連続してはヒットしない。雷スタックはヒットする毎に蓄積する。")
                .variable(1, Radius, 550)
                .variable(2, Time, new Fixed(new double[] {0.5, 0.4, 0.33}))
                .variable(3, MagicDamage, 80, 65, ap(0.4))
                .variable(4, Time, 3, 1)
                .variable(5, MagicDamage, 240, 195, ap(1.2))
                .cd(120);

        /** Kha'Zix */
        UnseenThreat.update()
                .passive("自身が敵チームの視界から消えた時に発動する。次の敵Championに対する通常攻撃かEvolved Void Spikeに追加{1}と2秒間{2}を付与する。この効果は敵チームの視界に現れても効果が消費されるまでは失われない。")
                .variable(1, MagicDamage, new Per1Level(new double[] {15, 20, 25, 35, 45, 55, 65, 75, 85, 95, 110, 125,
                        140, 150, 160, 170, 180, 190}), ap(0.5), null)
                .variable(2, MSSlowRatio, 25);
        TasteTheirFear.update()
                .passive("敵チームの中で孤立している敵ユニットにマークを付与する。")
                .active("対象の敵ユニットに{1}を与える。マークが付与されている敵ユニットのマークの範囲内に他の敵ユニットがいない場合、{2}を与える。進化すると孤立した敵ユニットに追加{3}を与え、このスキルの射程と通常攻撃の射程が50増加する。")
                .mana(25)
                .variable(1, PhysicalDamage, 70, 30, bounusAD(1.5))
                .variable(2, PhysicalDamage, 100, 45, bounusAD(2))
                .variable(3, PhysicalDamage, 0, 0, amplify(TargetMissingHealthRatio, 12))
                .cd(3.5)
                .range(325);
        VoidSpike.update()
                .active("指定方向に敵ユニットに命中すると爆発する針を発射し、{1}の敵ユニットに{2}を与える。自身が爆発範囲内にいる場合は更に{3}する。進化すると指定方向に対して扇形になるような3方向に針を発射するようになり、また爆発にUnseen Threatの追加魔法DMとスローを付与する。")
                .mana(60, 10)
                .variable(1, Radius, 0)
                .variable(2, PhysicalDamage, 75, 40, bounusAD(0.9))
                .variable(3, RestoreHealth, 40, 30, ap(0.5))
                .cd(8)
                .range(1000);
        Leap.update()
                .active("指定地点にジャンプし、{1}の敵ユニットに{2}を与える。進化すると射程が400増加し、またkillやassistを取った場合にこのスキルの{3}する。")
                .variable(1, Radius, 0)
                .variable(2, PhysicalDamage, 65, 35, bounusAD(0.8))
                .variable(3, CDDecrease, 0)
                .mana(50)
                .cd(22, -2)
                .range(600);
        VoidAssault.update()
                .passive("このスキルを取得、またはランクが上がる毎に、いずれかのスキルを選んで進化させることができる。")
                .active("使用後1秒間{2}状態になり、{1}する。この際にUnseen Threatの効果が発動する。また使用後10秒以内であれば、消費mana無しでもう一度だけこのスキルを使用することができる。進化すると10秒以内に再度使用可能な回数が2回に増加し、またステルス状態の間に受けるDMを40%軽減するようになる。")
                .variable(1, MSRatio, 40)
                .variable(2, Stealth)
                .mana(100)
                .cd(3);

        /** Kog'Maw */
        IcathianSurprise.update()
                .passive("死亡すると4秒後に自爆して周囲の敵ユニットに{1}を与える。自爆するまでの間は徐々に移動速度が増加する(最大時40%増加)。")
                .variable(1, TrueDamage, 100, 0, amplify(Lv, 25));
        CausticSpittle.update()
                .passive("{1}する。")
                .variable(1, ASRatio, 10, 5)
                .active("対象の敵ユニットに{2}を与え、4秒間{3}と{4}を与える。")
                .variable(2, MagicDamage, 60, 50, ap(0.7))
                .variable(3, ARReduction, 5, 5)
                .variable(4, MRReduction, 5, 5)
                .mana(60)
                .cd(8)
                .range(625);
        BioArcaneBarrage.update()
                .active("8秒間通常攻撃の射程が{1}増加し、通常攻撃時に{2}を追加で与える。")
                .variable(1, Range, 130, 20)
                .variable(2, MagicDamage, 0, 0, amplify(TargetMaxHealthRatio, 2, 1, ap(0.01)))
                .mana(50)
                .cd(17)
                .range(130, 20);
        VoidOoze.update()
                .active("指定方向に貫通する弾を発射し、当たった敵ユニットに{1}を与える。弾の通過点に4秒間持続する液体が残り、その上にいる敵ユニットに{2}を与える。")
                .variable(1, MagicDamage, 60, 50, ap(0.7))
                .variable(2, MSSlowRatio, 20, 8)
                .mana(80, 10)
                .cd(12)
                .range(1400);
        LivingArtillery.update()
                .active("指定地点を砲撃し、{1}の敵ユニットに{2}を敵Championには{3}を与え、4秒間そのユニットの視界を得る。このスキルを使うたびにスタックが増加し、1スタックにつきこのスキルの消費マナが40ずつ増加していく。スタックは6秒間持続する。")
                .variable(1, Radius, 200)
                .variable(2, MagicDamage, 80, 40, ap(0.3), bounusAD(0.5))
                .variable(3, MagicDamage, 180, 90, ap(0.3), bounusAD(0.5))
                .cost(Mana, new Diff(40, 0, 1), amplify(Stack, 40))
                .cd(2, -0.5)
                .range(1400, 300);

        /** LeBlanc */
        MirrorImage.update().passive("HPが40%以下になったとき0.5秒間ステルス状態になり、自分の分身を作り出す。分身は8秒間持続し、分身が敵にダメージを与えることはできない。").cd(60);
        SigilOfSilence.update()
                .active("対象の敵ユニットに{1}と3.5秒間持続するマークを付与する。マークが付いている間に再度スキルでダメージを与えると、マークを消費して追加{2}と{3}を付与する。")
                .variable(1, MagicDamage, 70, 40, ap(0.6))
                .variable(2, MagicDamage, 20, 20, ap(0.3))
                .variable(3, Silence, 2)
                .mana(70, 5)
                .cd(6)
                .range(700);
        Distortion.update()
                .active("指定地点まで高速で移動し、移動先の{1}にいる敵ユニットに{2}を与える。3秒間以内にもう一度このスキルを使用すると元居た地点に戻る。")
                .variable(1, Radius, 250)
                .variable(2, MagicDamage, 85, 40, ap(0.6))
                .mana(80, 5)
                .cd(18, -2)
                .range(600);
        EtherealChains.update()
                .active("指定方向に鎖を放ち、当たった敵ユニットに{1}と2秒間{2}を与え対象と鎖で繋がれる。2秒間対象が鎖の範囲内(範囲1000)に留まっていた場合、対象に追加{3}と{4}を与える。")
                .variable(1, MagicDamage, 40, 25, ap(0.5))
                .variable(2, MSSlowRatio, 25)
                .variable(3, MagicDamage, 40, 25, ap(0.5))
                .variable(4, Snare, 1, 0.3)
                .mana(80)
                .cd(10)
                .range(950);
        Mimic.update()
                .active("直前に使ったスキルを、威力を{1}増した状態で再使用する。Distortionとして使用した場合、それぞれDistortionとDistortion:Mimicを使用した地点に戻る事ができる。")
                .variable(1, Percentage, 10, 15)
                .mana(100, -50)
                .cd(40, -8);

        /** Lee Sin */
        Flurry.update()
                .passive("スキルを使用すると{1}し通常攻撃の度に{2}する。この効果は3秒経つか2回通常攻撃を行うと解消される。")
                .variable(1, ASRatio, 40)
                .variable(2, RestoreEnergy, 15)
                .conditional(1);
        SonicWave.update()
                .active("指定方向に気を飛ばし当たった敵ユニットに{1}を与える。このスキルが敵ユニットに当たった場合、3秒間Resonating Strikeが使用可能になる。また3秒間対象の視界を得る。")
                .variable(1, PhysicalDamage, 50, 30, bounusAD(0.9))
                .cd(11, -1)
                .range(975)
                .cost(Energy, 50, 0);
        ResonatingStrike.update()
                .active("Sonic Waveが当たった敵ユニットにダッシュし、{1}を与える。(追加ダメージはMinionに対して400DMが上限)")
                .variable(1, PhysicalDamage, 50, 0, bounusAD(0.5), amplify(Status.TargetMissingHealthRatio, 8))
                .cost(Energy, 30, 0)
                .range(1100);
        Safeguard.update()
                .active("対象の味方ユニットまで移動し、対象と自身に{1}を付与する。自身を対象とした場合はシールドのみが付与される。")
                .variable(1, Shield, 40, 40, ap(0.8))
                .cd(9)
                .cost(Energy, 50, 0)
                .range(700);
        IronWill.update().active("5秒間{1}と{2}を得る。").variable(1, LS, 5, 5).variable(2, SV, 5, 5).cost(Energy, 30, 0);
        Tempest.update()
                .active("{1}の敵ユニットに{2}を与え、4秒間そのユニットの{3}。このスキルが敵ユニットに当たった場合、3秒間Crippleが使用可能になる。")
                .variable(1, Radius, 450)
                .variable(2, MagicDamage, 60, 35, bounusAD(1))
                .variable(3, Visionable)
                .cd(10)
                .cost(Energy, 50, 0);
        Cripple.update()
                .active("Tempestが当たった敵ユニット４秒間{1}と{2}を与える。これらの速度低下は時間と共に戻っていく。")
                .variable(1, MSSlowRatio, 20, 10)
                .variable(2, ASSlowRatio, 20, 10);
        DragonsRage.update()
                .active("対象の敵Championに{1}を与え、{2}させる。ノックバックした対象に触れた敵ユニットにも{1}を与え、{3}を与える。")
                .variable(1, PhysicalDamage, 200, 200, bounusAD(2))
                .variable(2, Knockback, 1200)
                .variable(3, Knockup, 0)
                .cd(90, -15)
                .range(375);

        /** Leona */
        Sunlight.update()
                .passive("スキルでダメージを与えた敵ユニットに、3.5秒間持続するDebuffを付与する。この敵ユニットに対してLeona以外の味方Championがダメージを与えると、付与されたDebuffを消費して追加{1}を与える。追加魔法DMは2レベル毎に増加する。")
                .variable(1, MagicDamage, new Per3LevelAdditional(20, 15));
        ShieldOfDaybreak.update()
                .active("次の通常攻撃に追加{1}と{2}が付与される。")
                .variable(1, MagicDamage, 40, 30, ap(0.3))
                .variable(2, Stun, 1.25)
                .mana(45, 5)
                .cd(11, -1);
        Eclipse.update()
                .active("3秒間{1}と{2}を得て、効果終了時に{3}の敵ユニットに{4}を与える。魔法DMが敵ユニットに命中した場合、ARとMR増加の効果時間が3秒延長される。")
                .variable(1, AR, 30, 10)
                .variable(2, MR, 30, 10)
                .variable(3, Radius, 450)
                .variable(4, MagicDamage, 60, 50, ap(0.4))
                .mana(60)
                .cd(14);
        ZenithBlade.update()
                .active("指定方向に貫通するエネルギーを飛ばし当たった敵ユニットに{1}を与える。このスキルが敵Championに命中した場合、その敵Championの元までLeonaが移動する。また移動中は対象の敵ChampionにSnareを与える。複数の敵Championに命中した場合は最後に命中した敵Championの元に移動する。")
                .variable(1, MagicDamage, 60, 40, ap(0.4))
                .mana(60)
                .cd(13, -1)
                .range(875);
        SolarFlare.update()
                .active("わずかな間をおいて、指定地点を中心として{1}の敵ユニットに{2}を、{5}の敵ユニットに1.5秒間{3}を、{6}の敵ユニットに{4}を与える。")
                .variable(1, Radius, 125)
                .variable(2, MagicDamage, 150, 100, ap(0.8))
                .variable(5, Radius, 350)
                .variable(3, MSSlowRatio, 80)
                .variable(6, Radius, 50)
                .variable(4, Stun, 1.5)
                .mana(100, 50)
                .cd(90, -15)
                .range(1200);

        /** Lulu */
        PixFaerieCompanion.update()
                .passive("Pixという妖精がお供になる。Pixが付いている味方Championが通常攻撃を行った場合、Pixは同一の敵ユニットの方向に3発の弾を放ち1発毎に{1}を与える。この弾は敵ユニットを追尾するが、弾の進行方向上にいる敵ユニットにも当たる。建物を攻撃した場合はPixは弾を撃たない。")
                .variable(1, MagicDamage, 1, 0, amplify(Lv, 2));
        Glitterlance.update()
                .active("指定方向に貫通するエネルギーを発射し当たった敵ユニットに{1}と{2}間{3}を与える。スローの効果は時間と共に元に戻る。またPixがいる位置からもこのスキルが発射される(Pixが放つ方向はPixから見て指定した地点)。同一の対象に2回ヒットはしない。")
                .variable(1, MagicDamage, 80, 45, ap(0.5))
                .variable(2, Time, 1, 0.25)
                .variable(3, MSSlowRatio, 80)
                .mana(40, 10)
                .cd(7)
                .range(925);
        Whimsy.update()
                .active("対象の味方Championに使用した場合、5秒間対象の味方Championは{1}し{2}を得る。敵Championに使用した場合、{3}間無力な動物に変化させ(Polymorph)、その間通常攻撃とスキルを封じ、基本移動速度を60下げる。")
                .variable(1, MSRatio, 35)
                .variable(2, AP, 20, 10)
                .variable(3, Time, 1.5, 0.25)
                .mana(65, 5)
                .cd(18, -1.5)
                .range(650);
        HelpPix.update()
                .active("対象の味方ユニットに使用した場合、6秒間対象の味方ユニットにPixを付けると同時に{1}を付与する。敵ユニットに使用した場合、Pixが敵ユニットに付くと同時に{2}を与え、6秒間その敵ユニットの{3}。")
                .variable(1, Shield, 60, 45, ap(0.6))
                .variable(2, MagicDamage, 80, 50, ap(0.6))
                .variable(3, Visionable)
                .mana(60, 10)
                .cd(10)
                .range(650);
        WildGrowth.update()
                .active("対象の味方Championを7秒間巨大化させ、同時に対象の{3}の敵ユニットに{1}を与える。巨大化した味方Championは{2}を得て、{3}の敵ユニットに継続的に{4}を与える。スローの効果は範囲内から出ても1秒間持続する。")
                .variable(1, Knockup, 1.5)
                .variable(2, Health, 300, 150, ap(0.5))
                .variable(3, Radius, 0)
                .variable(4, MSSlowRatio, 30, 15)
                .mana(150)
                .cd(110, -15)
                .range(900);

        /** Lux */
        Illumination.update()
                .passive("スキルでダメージを与えた敵ユニットに6秒間持続するDebuffを付与する。この敵ユニットに対して通常攻撃かFinal Sparkでダメージを与えると、付与されたDebuffを消費して追加{1}を与える。")
                .variable(1, MagicDamage, 10, 0, amplify(Lv, 10));
        LightBinding.update()
                .active("指定方向に光の玉を飛ばし、当たった敵ユニットに{1}と{2}を与える。光の玉は一度だけ敵ユニットを貫通し、2体目のユニットには{3}と{4}を与える。")
                .variable(1, MagicDamage, 60, 50, ap(0.7))
                .variable(2, Snare, 2)
                .variable(3, MagicDamage, 50, 25, ap(0.35))
                .variable(4, Snare, 1)
                .mana(50, 10)
                .cd(15, -1)
                .range(1175);
        PrismaticBarrier.update()
                .active("指定方向に杖を投げ、自身と当たった味方Championに{1}を付与する。行きと帰りそれぞれに判定があり、シールドは3秒間持続する。")
                .variable(1, Shield, 80, 25, ap(0.35))
                .mana(60)
                .cd(14, -1)
                .range(1000);
        LucentSingularity.update()
                .active("指定地点に光の玉を設置し、{1}の敵ユニットに{2}を与える。光の玉は5秒経つか再度スキルを使用する事で爆発し、{1}の敵ユニットに{3}を与える。光の玉は{4}。")
                .variable(1, Radius, 350)
                .variable(2, MSSlowRatio, 20, 4)
                .variable(3, MagicDamage, 60, 45, ap(0.6))
                .variable(4, Visionable)
                .mana(70, 15)
                .cd(10)
                .range(1100);
        FinalSpark.update()
                .active("1秒詠唱後、指定方向の直線状にいるすべての敵ユニットに{1}を与える。また効果範囲内の視界を確保する。Hitした敵がIlluminationのデバフを受けていた場合はその分の追加ダメージを与えたうえ、新たにIlluminationのデバフを与える。")
                .variable(1, MagicDamage, 300, 100, ap(0.75))
                .mana(100)
                .cd(80, -20)
                .range(3000);

        /** Malphite */
        GraniteShield.update().passive("10秒間ダメージを受けないと{1}を得る。").variable(1, Shield, 0, 0, amplify(Health, 0.1));
        SeismicShard.update()
                .active("対象の敵ユニットに{1}と4秒間{2}を与える。また、このスキルで減少させた移動速度を自身の移動速度に加算する。移動速度増加は4秒間持続する。")
                .variable(1, MagicDamage, 70, 50, ap(0.6))
                .variable(2, MSSlowRatio, 14, 3)
                .mana(70, 5)
                .cd(8)
                .range(625);
        BrutalStrikes.update()
                .passive("通常攻撃時に対象の{1}にいる敵ユニットに{2}を与える。建物を攻撃する際にはスプラッシュ効果は発生しない。")
                .variable(1, Radius, 200)
                .variable(2, PhysicalDamage, 0, 0, amplify(AD, 0.3, 0.08))
                .active("6秒間{3}、{4}する。")
                .variable(3, ADRatio, 20, 5)
                .variable(4, ARRatio, 20, 5)
                .mana(50, 5)
                .cd(14);
        GroundSlam.update()
                .active("{1}の敵ユニットに{2}と3秒間{3}を与える。")
                .variable(1, Radius, 400)
                .variable(2, MagicDamage, 60, 40, ap(0.2), amplify(AR, 0.3))
                .variable(3, ASSlowRatio, 30, 5)
                .mana(50, 5)
                .cd(7);
        UnstoppableForce.update()
                .active("指定地点に突撃し{1}の敵ユニットに{2}を与えると共に{3}後、{4}を与える。")
                .variable(1, Radius, 325)
                .variable(2, MagicDamage, 200, 100, ap(1))
                .variable(3, Knockup, 1)
                .variable(4, Stun, 0.5)
                .mana(100)
                .cd(130, -15)
                .range(1000);

        /** Malzahar */
        SummonVoidling.update()
                .passive("スキルを4回使う度にVoidlingを召喚する。Voidlingは21秒間持続し、また召喚から7秒後にDMとARが1.5倍、14秒後にASが2倍に増加する。「Voidling」最大HP: {1} 通常攻撃DM: {2} AR: 30 MR: 50 AS: 0.831 MS: 451【備考】任意の操作不可。攻撃する優先順位は、Ultを掛けた相手、Malefic Visionsを掛けた相手、Malzaharがターゲットしている相手の順。")
                .variable(1, Count, 200, 0, amplify(Lv, 40))
                .variable(2, Count, 20, 0, amplify(Lv, 5), bounusAD(1));
        CallOftheVoid.update()
                .active("指定した地点の左右から挟み込む様に2本の波動が出現し、当たった敵ユニットに{1}と{2}を与える。また、指定した場所の視界を得る。")
                .variable(1, MagicDamage, 80, 55, ap(0.8))
                .variable(2, Silence, 1.4, 0.4)
                .mana(80, 10)
                .cd(9)
                .range(900);
        NullZone.update()
                .active("指定地点に5秒間持続する{1}のダメージゾーンを発生させ、上にいる敵ユニットに毎秒{2}を与える。(Minionに対しては毎秒120DMが上限)")
                .variable(1, Radius, 250)
                .variable(2, MagicDamage, 0, 0, amplify(TargetMaxHealthRatio, 4, 1, ap(0.01)))
                .mana(90, 10)
                .cd(14)
                .range(800);
        MaleficVisions.update()
                .active("対象の敵ユニットに4秒かけて{1}を与える。効果中に敵ユニットが死亡した場合、{2}し、近くの敵ユニットに効果が移り変わる。移る度に4秒のタイマーはリセットされる。")
                .variable(1, MagicDamage, 80, 60, ap(0.8))
                .variable(2, RestoreMana, 10, 4)
                .mana(60, 15)
                .cd(15, -2)
                .range(650);
        NetherGrasp.update()
                .active("対象の敵Championに2.5秒かけて{1}と{2}を与える。ダメージは0.5秒毎に計5回の判定がある。")
                .variable(1, MagicDamage, 250, 150, ap(1.3))
                .variable(2, Suppression, 2.5)
                .mana(150)
                .cd(120, -20)
                .range(700)
                .type(SkillType.Channel);

        /** Maokai */
        SapMagic.update()
                .passive("近くのChampion（敵味方自分問わず）がスキルを使用するとスタックが増え、5スタックまで溜まった状態で通常攻撃を行うと{1}する。このスキル発動時にスタックは0になる。建物を攻撃した場合は発動しない。")
                .variable(1, RestoreHealth, 0, 0, amplify(Health, 0.07));
        ArcaneSmash.update()
                .active("{1}の敵ユニットと指定方向の敵ユニットに{2}と2秒間{3}を与える。{4}の敵ユニットには更に{5}を与える。")
                .variable(1, Radius, 0)
                .variable(2, MagicDamage, 70, 45, ap(0.4))
                .variable(3, MSSlowRatio, 20, 7)
                .variable(4, Radius, 200)
                .variable(5, Knockback, 0)
                .mana(55)
                .cd(6)
                .range(700);
        TwistedAdvance.update()
                .active("対象の敵ユニットまで高速移動し{1}と{2}を与える。")
                .variable(1, MagicDamage, 80, 35, ap(0.8))
                .variable(2, Snare, 1, 0.25)
                .mana(75, 5)
                .cd(13)
                .range(650);
        SaplingToss.update()
                .active("指定地点に苗木を投げ、{1}の敵ユニットに{2}を与える。苗木は最大35秒間その場で待機し、敵ユニットが近付く({4})と相手に向かって移動を開始する、敵に接触するか数秒経つと爆発して{1}に{3}を与える。")
                .variable(1, Radius, 350)
                .variable(2, MagicDamage, 40, 35, ap(0.4))
                .variable(3, MagicDamage, 80, 50, ap(0.6))
                .variable(4, Radius, 500)
                .mana(70, 10)
                .cd(12)
                .range(1100);
        VengefulMaelstrom.update()
                .passive("指定地点の{1}にシールドを展開し、範囲内の味方Championが受けるダメージを20%低減させる(タワーからの攻撃以外）。解除すると範囲内の敵ユニットに{2}を与える。低減したダメージ量に比例して与えるダメージが増加する。")
                .variable(1, Radius, 0)
                .variable(2, MagicDamage, 100, 50, ap(0.5))
                .mana(75)
                .cd(40, -10)
                .range(575);

        /** Master Yi */
        DoubleStrike.update().passive("通常攻撃7回毎に2回分ダメージを与える。対象が建物の場合も有効。");
        AlphaStrike.update()
                .active("対象の敵ユニットと近くの敵ユニット3体({1})をランダムに対象とし{2}を与え、対象の近くにワープする。minionの場合は50%の確率で追加{3}を与える。")
                .variable(1, Radius, 600)
                .variable(2, MagicDamage, 100, 50, ap(1))
                .variable(3, MagicDamage, 260, 60)
                .mana(60, 10)
                .cd(18, -2)
                .range(600);
        Meditate.update()
                .active("5秒間詠唱を行い、毎秒{1}し、詠唱中は{2}と{3}を得る。")
                .variable(1, RestoreHealth, 40, 30, ap(0.4))
                .variable(2, AR, 100, 50)
                .variable(3, MR, 100, 50)
                .mana(50, 15)
                .cd(35)
                .type(SkillType.Channel);
        WujuStyle.update()
                .passive("{1}を得る。")
                .variable(1, AD, 15, 5)
                .active("10秒間{2}を得る。CDが解消されるまでPassiveの増加ダメージがなくなる。")
                .variable(2, AD, 30, 10)
                .mana(40)
                .cd(25, -2);
        Highlander.update()
                .active("{4}間{1}、{2}し、{3}を得る。効果中に敵Championを倒すとすべてのスキルの{5}する。")
                .variable(1, MSRatio, 40)
                .variable(2, ASRatio, 40)
                .variable(3, IgnoreSlow)
                .variable(4, Time, 8, 2)
                .variable(5, CDDecrease)
                .mana(100)
                .cd(75);

        /** Miss Fortune */
        Strut.update()
                .passive("5秒間ダメージを受けないと{1}する。以後、毎秒移動速度が9ずつ上昇し、5秒後に移動速度上昇値は上限の70に到達する。ダメージを受けると解除される。")
                .variable(1, MS, 25)
                .conditional(1);
        DoubleUp.update()
                .active("対象の敵ユニットに弾丸を飛ばし{1}を与える。弾は一度だけ跳ね返り、背後にいる敵ユニット一体(範囲500)を対象とし{2}を与える。")
                .variable(1, PhysicalDamage, 25, 35, ad(0.75))
                .variable(2, PhysicalDamage, 30, 42, ad(0.9))
                .mana(70, 5)
                .cd(9, -1)
                .range(625);
        ImpureShots.update()
                .passive("通常攻撃に{1}を与え、対象にスタックを付与する。1スタックにつき追加魔法DMが倍増していく。スタックは最大4スタック(最大4倍ダメージ)で5秒間持続する。")
                .variable(1, MagicDamage, 6, 2, ap(0.05))
                .active("6秒間{2}し、通常攻撃に{3}を付加する。")
                .variable(2, ASRatio, 30, 5)
                .variable(3, Wounds, 3)
                .mana(50)
                .cd(16);
        MakeItRain.update()
                .active("地点を指定した0.5秒後に2秒間銃弾の雨を降らし、{1}の敵ユニットに{2}と1秒間{3}を与える。")
                .variable(1, Radius, 400)
                .variable(2, MagicDamage, 90, 55, ap(0.8))
                .variable(3, MSSlowRatio, 20, 8)
                .mana(80)
                .cd(15)
                .range(800);
        BulletTime.update()
                .active("指定方向扇形の範囲に2秒間、弾幕砲火を浴びせて範囲内の敵ユニットに{1}を与える。弾は0.25秒毎に一発発射され、同一の対象に8回までヒットする。最大で{2}。")
                .variable(1, PhysicalDamage, 65, 30, ap(0.2), bounusAD(0.35))
                .variable(2, PhysicalDamage, 520, 240, ap(1.6), bounusAD(2.8))
                .mana(100)
                .cd(120, -10)
                .range(1400)
                .type(SkillType.Channel);

        /** Mordekaiser */
        IronMan.update()
                .passive("スキルで与えたダメージの17.5%(Championに対しては35%)をシールドに変換して(最大で{1})受けたダメージはHPより先にシールドがくらってくれる。1秒毎に3%ずつ低下していく。")
                .variable(1, Shield, 90, 0, amplify(Lv, 30));
        MaceOfSpades.update()
                .active("次に行う通常攻撃が{1}になり、更に近くの敵ユニット3体({2})にもダメージを与える。対象が1体だけの場合は{3}与える。")
                .variable(1, MagicDamage, 80, 30, ap(0.4), bounusAD(1))
                .variable(2, Radius, 600)
                .variable(3, MagicDamage, 132, 49.5, ap(0.66), bounusAD(1.65))
                .cd(8, -1)
                .cost(Health, 20, 5);
        CreepingDeath.update()
                .active("対象の味方ユニットに6秒間持続するシールドを付与する。付与されたユニットは{1}と{2}を得て、{3}の敵ユニットに毎秒{4}を与える。")
                .variable(1, AR, 10, 5)
                .variable(2, MR, 10, 5)
                .variable(3, Radius, 250)
                .variable(4, MagicDamage, 24, 12, ap(0.2))
                .cd(20, -2)
                .range(750)
                .cost(Health, 26, 6);
        SiphonOfDestruction.update()
                .active("指定方向扇形の範囲内の敵ユニットに{1}を与える。")
                .variable(1, MagicDamage, 70, 45, ap(0.6))
                .cd(6)
                .range(700)
                .cost(Health, 24, 12);
        ChildrenOftheGrave.update()
                .active("対象の敵Championに{1}を与え、その後10秒間、毎秒{2}を与える。10秒間で総計{4}を与え、{3}する。効果中に対象が死ぬとThe Spiritを生成し30秒間従わせる。（RまたはALT押しながらクリックで任意の操作可能）　The Spirit AD: 元になったChampのAD + MordekaiserのADの75%AP: 元になったChampのAP + MordekaiserのAPの75%HP: 元になったChampのHP + MordekaiserのHPの15%行動範囲: 1125 また、The Spiritを従えている間、Mordekaiserは元になったChampのADとAPの20％を得る。")
                .variable(1, MagicDamage, 0, 0, amplify(TargetMaxHealthRatio, 12, 2.5, ap(0.02)))
                .variable(2, MagicDamage, 0, 0, amplify(TargetMaxHealthRatio, 1.2, 0.25, ap(0.002)))
                .variable(3, RestoreHealth, 0, 0, amplify(DealtDamageRatio, 100))
                .variable(4, MagicDamage, 0, 0, amplify(TargetMaxHealthRatio, 24, 5, ap(0.04)))
                .cd(120, -15)
                .range(850);

        /** Morgana */
        SoulSiphon.update().passive("{1}を得る。レベル1、7、13で増加値が上昇する。").variable(1, SV, new Per6Level(10, 5));
        DarkBinding.update()
                .active("指定方向に魔法弾を飛ばし、当たった敵ユニットに{1}と{2}を与える。")
                .variable(1, MagicDamage, 80, 55, ap(0.9))
                .variable(2, Snare, 2, 0.25)
                .mana(60, 15)
                .cd(11)
                .range(1300);
        TormentedSoil.update()
                .active("指定地点に5秒間持続する黒い沼({3})を発生させ、上にいる敵ユニットに毎秒{1}と{2}を与える。MR低下は2秒間持続し、5回までスタックする。")
                .variable(1, MagicDamage, 25, 15, ap(0.2))
                .variable(2, MRReduction, 4, 1)
                .variable(3, Radius, 350)
                .mana(70, 15)
                .cd(10)
                .range(900);
        BlackShield.update()
                .active("対象の味方Championは5秒間{1}と{2}を得る。")
                .variable(1, MagicShield, 95, 65, ap(0.7))
                .variable(2, IgnoreCC)
                .mana(50)
                .cd(23, -2)
                .range(750);
        SoulShackles.update()
                .active("周囲の敵Championに{1}と３秒間{2}を与え対象と糸で繋がれる。3秒間対象が糸の範囲内({3})に留まっていた場合、対象に{1}と{4}を与える。")
                .variable(1, MagicDamage, 175, 75, ap(0.7))
                .variable(2, MSSlowRatio, 20)
                .variable(3, Radius, 1000)
                .variable(4, Stun, 1.5)
                .mana(100, 50)
                .cd(120, -10)
                .range(600);

        /** Nami */
        SurgingTides.update()
                .passive("スキルが味方Championに命中した際に、対象は1.5秒間{1}する。レベル1/7/13で増加量が上昇する。")
                .variable(1, MS, new Per6Level(40, 5))
                .conditional(1);
        AquaPrison.update()
                .active("指定地点に泡を投げ、範囲内の敵ユニットに{1}と{2}を与える。")
                .variable(1, MagicDamage, 75, 55, ap(0.65))
                .variable(2, Stun, 1.25)
                .mana(60)
                .cd(14, -1)
                .range(875);
        EbbandFlow.update()
                .active("対象の味方Championまたは敵Championに、Championにのみ3回まで跳ね返る({3})水流を発射する。水流が味方Championに命中した場合は{1}し、敵Championに命中した場合は{2}を与える。水流は同一のChampionには一度しか跳ね返らず、味方Championに命中した場合は一番近くの敵Championに、敵Championに命中した場合は一番近くの味方Championに跳ね返る。")
                .variable(1, RestoreHealth, 65, 30, ap(0.3))
                .variable(2, MagicDamage, 70, 40, ap(0.5))
                .variable(3, Radius, 875)
                .mana(70, 15)
                .cd(9)
                .range(725);
        TidecallersBlessing.update()
                .active("対象の味方Championの通常攻撃に{1}と1秒間の{2}を付与する。この効果は5秒経つか3回通常攻撃を行うと解消される。")
                .variable(1, MagicDamage, 25, 15, ap(0.2))
                .variable(2, MSSlowRatio, 15, 5)
                .mana(55, 5)
                .cd(11)
                .range(800);
        TidalWave.update()
                .active("指定方向に津波を発生させ、命中した敵ユニットに{1}と{2}を与えた後2～4秒間{3}与える。スローの効果時間は当たるまでの津波の移動距離に比例して効果時間が長くなる。")
                .variable(1, Knockup, 1)
                .variable(2, MagicDamage, 150, 100, ap(0.7))
                .variable(3, MSSlowRatio, 50, 10)
                .mana(100, 50)
                .cd(140, -20)
                .range(2550);

        /** Nasus */
        SoulEater.update().passive("{1}を得る。レベル1、7、13で増加値が上昇する。").variable(1, LS, new Per6Level(14, 3));
        SiphoningStrike.update()
                .active("次に行う通常攻撃に{1}を付与する。このスキルを使用しLHをとると増加ダメージが+3されていく。対象が敵Champion/SiegeまたはSuperMinion/Buffを持った中立クリープの場合、増加値は2倍(+6)になる。")
                .variable(1, PhysicalDamage, 30, 20, amplify(Stack, 3))
                .mana(20, 5)
                .cd(8, -1);
        Wither.update()
                .active("対象の敵Championに5秒間{1}と{2}を与える。")
                .variable(1, ASSlowRatio, 35, 0, amplify(Duration, 3, 3))
                .variable(2, MSSlowRatio, 35, 0, amplify(Duration, 3, 3))
                .mana(80)
                .cd(15, -1)
                .range(700);
        SpiritFire.update()
                .active("指定地点に魔法陣を呼び出し{1}の敵ユニットに{2}を与える。魔方陣は5秒間持続し、上にいる敵ユニットに{3}と毎秒{4}を与える。")
                .variable(1, Radius, 400)
                .variable(2, MagicDamage, 55, 40, ap(0.6))
                .variable(3, Status.ARReduction, 20, 5)
                .variable(4, MagicDamage, 11, 8, ap(0.12))
                .mana(70, 15)
                .cd(12)
                .range(650);
        FuryOftheSands.update()
                .active("15秒間自身の周りに砂嵐を発生させ{1}増加し、周囲の敵ユニットに毎秒{2}を与える。また効果中はこのスキルで与えたダメージの5%を自身の攻撃力に加える。毎秒ダメージの上限は240、増加攻撃力の上限は300。")
                .variable(1, Health, 300, 150)
                .variable(2, MagicDamage, 0, 0, amplify(TargetMaxHealthRatio, 3, 1, ap(0.01)))
                .mana(150)
                .cd(120);

        /** Nautilus */
        StaggeringBlow.update()
                .passive("通常攻撃に{1}と{2}が付与される。同一の対象には12秒に一度しか発動しない。レベル1、7、13で効果時間が上昇する。")
                .variable(1, PhysicalDamage, 2, 0, amplify(Lv, 6))
                .variable(2, Snare, new Per6Level(0.5, 0.25));
        DredgeLine.update()
                .active("指定方向に錨を投げ、最初に命中した敵ユニットに{1}を与えNautilusの方向に引き寄せる。またNautilus自身も敵ユニットの方向に移動する。錨が壁に命中した場合、壁の方向にNautilusが移動しこのスキルのCDが半分になる。")
                .variable(1, MagicDamage, 60, 45, ap(0.75))
                .mana(60, 10)
                .cd(18, -2)
                .range(950);
        TitansWrath.update()
                .active("10秒間{1}を得る。シールドが持続している間は通常攻撃時に対象とその周囲({2})にいる敵ユニットに{3}を与える。この魔法DMは2秒間かけて与えられる。")
                .variable(1, Shield, 100, 50, amplify(BounusHealth, 0.1))
                .variable(2, Radius, 350)
                .variable(3, MagicDamage, 30, 15, ap(0.4))
                .mana(80)
                .cd(26, -2);
        Riptide.update()
                .active("周囲を爆発させ命中した敵ユニットに{1}と２秒間{2}を与える。スローの効果は2秒かけて元に戻る。爆発はNautilusを中心に3回まで発生し、同一対象に対して複数hitする。2発目以降は本来の50%分の魔法DMを与える(3発hitで{3})。")
                .variable(1, MagicDamage, 60, 40, ap(0.5))
                .variable(2, MSSlowRatio, 30, 5)
                .variable(3, MagicDamage, 120, 80, ap(1))
                .mana(60, 10)
                .cd(10)
                .range(400);
        DepthCharge.update()
                .active("対象の敵Championに衝撃波を放ち、移動中の衝撃波に当たった敵ユニットに{1}と{2}を与える。衝撃波が対象の敵Championに当たると爆発し、対象とその周囲にいる敵ユニットに{3}と{2}を与える。対象の敵Championには{2}と同時に{4}を与える。")
                .variable(1, MagicDamage, 125, 50, ap(0.4))
                .variable(2, Knockup, 1)
                .variable(3, MagicDamage, 200, 125, ap(0.8))
                .variable(4, Stun, 1, 0.5)
                .mana(100)
                .cd(140, -30)
                .range(850);

        /** Nidalee */
        Prowl.update().passive("茂みに入ると{1}する。この効果は茂みから出ても2秒間持続する。").variable(1, MSRatio, 15).conditional(1);
        JavelinToss.update()
                .active("指定方向に槍を投げて当たった敵ユニットに{1}を与える。槍がhitした時のNidaleeとターゲットの間の距離に比例して与えるダメージが増加する。最大で{2}。")
                .variable(1, MagicDamage, 55, 40, ap(0.65))
                .variable(2, MagicDamage, 137.5, 100, ap(1.625))
                .mana(50, 10)
                .cd(5)
                .range(1500);
        Takedown.update()
                .active("次に行う通常攻撃のダメージが{1}増加する。対象が受けているダメージに比例してダメージが増加する。最大で{2}。")
                .variable(1, PhysicalDamage, 40, 30)
                .variable(2, PhysicalDamage, 120, 90, ad(2))
                .cd(5);
        Bushwhack.update()
                .active("指定地点に罠を仕掛ける。敵ユニットが罠を踏むと発動し、対象の敵ユニットとその周囲の敵ユニットに2秒かけて{1}を与え、12秒間{2}と{3}を与え{4}。罠は4分間持続する。罠を設置してから2秒間の間は罠は発動しない。")
                .variable(1, MagicDamage, 80, 45, ap(0.4))
                .variable(2, ARReductionRatio, 20, 5)
                .variable(3, MRReductionRatio, 20, 5)
                .variable(4, Visionable)
                .mana(60, 15)
                .cd(18)
                .range(900);
        Pounce.update()
                .active("前方に飛びかかり着地地点の敵ユニットに{1}を与える。")
                .variable(1, MagicDamage, 125, 50, ap(0.4))
                .cd(3.5)
                .range(350);
        PrimalSurge.update()
                .active("対象の味方Championの{1}し、7秒間{2}する。")
                .variable(1, RestoreHealth, 50, 35, ap(0.7))
                .variable(2, ASRatio, 20, 10)
                .mana(60, 20)
                .cd(10)
                .range(600);
        Swipe.update()
                .active("前方扇形180°{2}の敵ユニットに{1}を与える。")
                .variable(1, MagicDamage, 150, 75, ap(0.4))
                .variable(2, Radius, 300)
                .cd(6);
        AspectOfTheCougar.update()
                .active("HumanからCougarに、CougarからHumanに変身する。Cougar時はスキルの効果が変わり、通常攻撃の射程距離が125(Melee)になり、{1}、{2}、{3}を得る。Cougarスキルはこのスキルのレベルに比例し威力が増加する。")
                .variable(1, MS, 20)
                .variable(2, AR, 10, 10)
                .variable(3, MR, 10, 10)
                .cd(4);

        /** Nocturne */
        UmbraBlades.update()
                .passive("10秒に1度、通常攻撃のダメージが120%に増加し、{1}の敵ユニットにダメージを与える範囲攻撃になる。この効果がヒットした敵の数に応じて{2}する。また、通常攻撃を行うごとに、このスキルの{3}する。建物を攻撃する時はこの効果は発生しない。レベル1、7、13でHP回復量が上昇する。")
                .variable(1, Radius, 200)
                .variable(2, RestoreHealth, new Per6Level(10, 8))
                .variable(3, CDDecrease, 1)
                .cd(10);
        Duskbringer.update()
                .active("指定方向に影の刃を投げて当たった敵ユニットに{1}を与える。刃の軌跡には5秒間持続するDusk Trailが残り、刃がヒットした敵Championの動いた軌道上にもDusk Trailが残るようになる。Dusk Trailの上ではNocturneは{2}と{3}を得て、{4}する。")
                .variable(1, PhysicalDamage, 60, 45, bounusAD(0.75))
                .variable(2, AD, 15, 10)
                .variable(3, IgnoreUnitCollision)
                .variable(4, ASRatio, 15, 5)
                .mana(60, 5)
                .cd(10)
                .range(1200);
        ShroudOfDarkness.update()
                .passive("{1}する。")
                .variable(1, ASRatio, 20, 5)
                .active("Nocturneに1.5秒間持続する{2}を付与し、その間一度だけ敵のスキルを無効化する。無効化した場合、5秒間Passiveの増加攻撃速度が2倍になる。")
                .variable(2, Status.SpellShield)
                .mana(50)
                .cd(20, -2);
        UnspeakableHorror.update()
                .active("対象の敵ユニットに2秒間0.5秒毎に{1}、合計で{3}を与え、2秒間対象が一定範囲内(範囲465)に留まり続けていれば、更に{2}を与える。")
                .variable(1, MagicDamage, 12.5, 12.5, ap(0.25))
                .variable(2, Fear, 1, 0.25)
                .variable(3, MagicDamage, 50, 50, ap(1))
                .mana(60, 5)
                .cd(15, -1)
                .range(425);
        Paranoia.update()
                .active("4秒間すべての敵Championに{1}を与え、また自分以外の視界を得られなくする。効果中に範囲内にいる敵Championを指定すると対象の位置まで移動し、到着時に対象に{2}を与える。")
                .variable(1, Foggy, 300)
                .variable(2, PhysicalDamage, 150, 100, bounusAD(1.2))
                .mana(100)
                .cd(180, -40)
                .range(2000, 750);

        /** Nunu */
        Visionary.update().passive("通常攻撃を行うたびにスタックが増加する(最大5スタック)。5スタックの状態でスキルを使用すると、スタックを消費して対象のスキルの消費マナが0になる。");
        Consume.update()
                .active("対象の敵MinionかPet及び中立クリープ1体に{1}を与え、{2}する。")
                .variable(1, TrueDamage, 500, 100)
                .variable(2, RestoreHealth, 125, 55, ap(1))
                .mana(60)
                .cd(16, -2)
                .range(125);
        BloodBoil.update()
                .active("12秒間対象の味方ユニットは{1}し{2}する。自分以外に使用した場合は自身にも同様の効果が発生する。")
                .variable(1, ASRatio, 25, 5)
                .variable(2, MSRatio, 8, 1)
                .mana(50)
                .cd(15)
                .range(700);
        IceBlast.update()
                .active("対象の敵ユニットに{1}と3秒間{2}、{3}を与える。")
                .variable(1, MagicDamage, 85, 45, ap(1))
                .variable(2, ASSlowRatio, 25)
                .variable(3, MSSlowRatio, 20, 10)
                .mana(75, 10)
                .cd(6)
                .range(550);
        AbsoluteZero.update()
                .active("最大3秒詠唱を行い、詠唱完了またはキャンセル時に{1}の敵ユニットに{2}を与える。ダメージは詠唱した時間に比例して最大で{3}。また詠唱中は範囲内の敵ユニットに{4}と{5}を与える。")
                .variable(1, Radius, 550)
                .variable(2, MagicDamage, 78, 31, ap(0.3))
                .variable(3, MagicDamage, 625, 250, ap(2.5))
                .variable(4, ASSlowRatio, 25)
                .variable(5, MSSlowRatio, 50)
                .mana(150)
                .cd(150, -30);

        /** Olaf */
        BerserkerRage.update().passive("{1}する。").variable(1, ASRatio, 0, 0, amplify(MissingHealthPercentage, 1));
        Undertow.update()
                .active("指定地点に貫通する斧を投げ、当たった敵ユニットに{1}と2.5秒間{2}を与える。このスローは2.5秒かけて元に戻る。投げた斧は指定地点に7秒間留まり、斧を回収するとこのスキルの{3}する。")
                .variable(1, PhysicalDamage, 80, 45, bounusAD(1))
                .variable(2, MSSlowRatio, 24, 4)
                .variable(3, CDDecrease, 4.5)
                .mana(55, 5)
                .cd(8)
                .range(1000);
        ViciousStrikes.update()
                .active("6秒間{1}と{2}と{3}を得る。")
                .variable(1, AD, 7, 7, amplify(Health, 0.01))
                .variable(2, LS, 9, 3)
                .variable(3, SV, 9, 3)
                .mana(40, 5)
                .cd(16);
        RecklessSwing.update()
                .active("対象の敵ユニットに{1}を与える。")
                .variable(1, TrueDamage, 100, 60)
                .cost(Health, 40, 24)
                .cd(9, -1)
                .range(325);
        Ragnarok.update()
                .active("6秒間{1}、{2}、{3}と{4}を得る。既にCCを受けていた場合はそれらを解除する。StunなどのDisable中でも使用可能。")
                .variable(1, ARPen, 10, 10)
                .variable(2, AR, 30, 15)
                .variable(3, MR, 30, 15)
                .variable(4, IgnoreCC)
                .mana(100, -25)
                .cd(100);

        /** Orianna */
        ClockworkWindup.update()
                .passive("通常攻撃に追加{1}が付与される。4秒以内に同一の対象を連続して攻撃すると、追加魔法DMが20%上昇していく(最大+40%)。追加魔法の基礎DMは4/7/10/13/16レベル時に増加する。建物には無効。")
                .variable(1, MagicDamage, new Per3Level(10, 8), ap(0.15), null);
        CommandAttack.update()
                .active("指定した地点にBallを移動させ、移動中のBallに触れた敵ユニットと指定{1}にいる敵ユニットに{2}を与える。ダメージは敵に当たるごとに10%づつ減少する（最大60%減少）。BallはOriannaから一定距離離れなければその場に待機して{3}。")
                .variable(1, Radius)
                .variable(2, MagicDamage, 60, 30, ap(0.5))
                .variable(3, Visionable)
                .mana(50)
                .cd(6, -0.75)
                .range(825);
        CommandDissonance.update()
                .active("Ballの存在する地点にフィールドを展開しBallの{1}にいる敵ユニットに{2}を与える。フィールドは3秒間持続し、フィールドの上の味方ユニットは{3}し、敵ユニットには{4}を与える。フィールドから出た場合、この効果は2秒かけて元に戻る。")
                .variable(1, Radius, 250)
                .variable(2, MagicDamage, 70, 45, ap(0.7))
                .variable(3, MSRatio, 20, 5)
                .variable(4, MSSlowRatio, 20, 5)
                .mana(70, 10)
                .cd(9);
        CommandProtect.update()
                .passive("Ballが付いている味方Championは{1}と{2}を得る。")
                .variable(1, AR, 10, 5)
                .variable(2, MR, 10, 5)
                .active("対象の味方ChampionまでBallを移動させ、4秒間持続する{3}を付与する。また、移動中のBallに触れた敵ユニットに{4}を与える。Ballは対象がOriannaから一定距離離れなければ貼り付き続ける。")
                .variable(3, Shield, 80, 40, ap(0.4))
                .variable(4, MagicDamage, 60, 30, ap(0.3))
                .mana(60)
                .cd(9)
                .range(1120);
        CommandShockwave.update()
                .active("約0.5秒詠唱後にBallから衝撃波を発生させ、Ballの{1}にいる敵ユニットに{2}を与えると共に、Ballの方向に{3}させる。")
                .variable(1, Radius, 400)
                .variable(2, MagicDamage, 150, 75, ap(0.7))
                .variable(3, Knockback, 350)
                .mana(100, 25)
                .cd(120, -15);

        /** Pantheon */
        AegisProtection.update()
                .passive("通常攻撃を行うかスキルを使用するたびにスタックが1増加(最大4スタック)し、4スタック時に40DM以上の通常攻撃を受けるとスタックを消費して通常攻撃のダメージを無効化する。");
        SpearShot.update()
                .active("対象の敵ユニットに槍を投げ{1}を与える。")
                .variable(1, PhysicalDamage, 65, 40, bounusAD(1.4))
                .mana(45)
                .cd(4)
                .range(600);
        AegisOfZeonia.update()
                .active("対象の敵Championに飛びかかり{1}と{2}を与え、Aegis Protectionを発動する。")
                .variable(1, MagicDamage, 50, 25, ap(1))
                .variable(2, Stun, 1)
                .mana(55)
                .cd(13, -1)
                .range(600);
        HeartseekerStrike.update()
                .passive("敵ユニットのHPが15%以下の時は通常攻撃が必ずクリティカルになり、またSpear Shotのダメージが1.5倍になる。")
                .active("指定方向に0.75秒間槍を突き出し、範囲内の敵ユニットに{1}を最大3回与える(0.25秒毎に1ヒット)。対象がChampionの場合、与えるダメージが{2}になる。")
                .variable(1, PhysicalDamage, 13, 10, bounusAD(0.6))
                .variable(2, PhysicalDamage, 26, 20, bounusAD(1.2))
                .mana(45, 5)
                .cd(10, -1)
                .range(400)
                .type(SkillType.Channel);
        GrandSkyfall.update()
                .active("地点を指定して2秒後にジャンプし、その1.5秒後に指定地点の{1}に{2}と1秒間{3}を与えつつ落下する。DMは指定地点から離れるほど低減され、範囲最端では50%となる。ジャンプ前にキャンセルすると、消費した分のマナが回復し、このスキルのCDは10秒になる。")
                .variable(1, Radius, 1000)
                .variable(2, MagicDamage, 400, 300, ap(1))
                .variable(3, MSSlowRatio, 35)
                .mana(125)
                .cd(150, -15)
                .range(5500);

        /** Poppy */
        ValiantFighter.update().passive("現在HPの10%を超えるダメージを受けた際、その超過分のダメージを50%低減する。塔の攻撃には無効。");
        DevastatingBlow.update()
                .active("次に行う通常攻撃が魔法DMになり、追加{1}が付与される。")
                .variable(1, MagicDamage, 20, 20, ap(0.6), amplify(TargetMaxHealthRatio, 8))
                .mana(55)
                .cd(8, -1);
        ParagonOfDemacia.update()
                .passive("通常攻撃を行うか、ダメージを受ける度にスタックが1増加する(最大10)。スタック数に比例して{1}と{2}を得る。スタックは5秒間増加がないと0になる。")
                .variable(1, AD, 1.5, 0, amplify(Stack, 0.5))
                .variable(2, AR, 1.5, 0, amplify(Stack, 0.5))
                .conditional(1)
                .conditional(2)
                .active("スタックを最大(10)まで増加させ、5秒間{3}する。")
                .variable(3, MSRatio, 17, 2)
                .mana(70, 5)
                .cd(12);
        HeroicCharge.update()
                .active("対象の敵ユニットに突撃し{1}と{2}を与える。ノックバック時に壁にぶつかった場合、追加{3}と{4}を与える。")
                .variable(1, Knockback)
                .variable(2, MagicDamage, 50, 25, ap(0.4))
                .variable(3, MagicDamage, 75, 50, ap(0.4))
                .variable(4, Stun, 1.5)
                .mana(60, 5)
                .cd(12, -1)
                .range(525);
        DiplomaticImmunity.update()
                .active("対象の敵Championを{2}秒間ターゲットし、その対象に与える{1}する。効果中は対象以外からのすべての攻撃を無効化する(対象のPetからはダメージを受ける)。")
                .variable(1, DamageRatio, 20, 10)
                .variable(2, Time, 6, 1)
                .mana(100)
                .cd(140, -20)
                .range(900);

        /** Rammus */
        SpikedShell.update().passive("{1}を得る。").variable(1, AD, 0, 0, amplify(AR, 0.45));
        Powerball.update()
                .active("使用してから7秒間Rammusが回転し、その間徐々に移動速度が増加する(最大時85%増加)。回転中に最初に当たった敵ユニットとRammusの周囲({1})にいる敵ユニットに{2}、{3}、3秒間の{4}を与える。回転中にスキル再使用または、Defensive Ball Curlを使用すると回転がキャンセルされる。")
                .variable(1, Radius, 200)
                .variable(2, MagicDamage, 100, 50, ap(1))
                .variable(3, Knockup, 0.75)
                .variable(4, MSSlowRatio, 20, 5)
                .mana(70, 10)
                .cd(10);
        DefensiveBallCurl.update()
                .active("6秒間{1}と{2}を得て、Rammusを通常攻撃した敵ユニットに{3}を与える。効果中にPowerballを使用すると効果がキャンセルされる。また、このスキルを再使用することで効果をキャンセルできる。")
                .variable(1, AR, 40, 20)
                .variable(2, MR, 40, 20)
                .variable(3, MagicDamage, 15, 10, amplify(AR, 0.1))
                .mana(40)
                .cd(14);
        PuncturingTaunt.update()
                .active("対象の敵ユニットに{1}と{2}を与える。")
                .variable(1, Taunt, 1, 0.5)
                .variable(2, ARReduction, 10, 5)
                .mana(50, 10)
                .cd(12)
                .range(325);
        Tremors.update()
                .active("8秒間地震を発生させ{1}の敵ユニット及び建物に毎秒{2}を与える。")
                .variable(1, Radius, 300)
                .variable(2, MagicDamage, 65, 65, ap(0.3))
                .mana(120)
                .cd(60);

        /** Renekton */
        ReignOfAnger.update()
                .passive("通常攻撃ごとにFuryが5増加する。12秒間戦闘を行わないとFuryは毎秒2.5減少する。Furyの最大値は100、Furyが50以上ある場合にスキルを使用すると、Furyを50消費してスキルが強化される。また、RenektonのHPが50%以下になるとFuryの増加量が50%増加する。建物を攻撃した場合はFuryは増加しない。");
        CullTheMeek.update()
                .active("武器を振り回し{1}の敵ユニットに{2}を与え、{3}する(上限あり)。対象がChampionの場合、{4}する。当たったユニット1体につきFuryが5増加する(最大25)。Fury消費すると与えるダメージが50%増加し、HP回復量が2倍、回復上限が3倍に増加する。但し、Fury増加効果は無くなる。")
                .variable(1, Radius, 450)
                .variable(2, PhysicalDamage, 60, 30, bounusAD(0.8))
                .variable(3, RestoreHealth, 0, 0, amplify(DealtDamageRatio, 5))
                .variable(4, RestoreHealth, 0, 0, amplify(DealtDamageRatio, 20))
                .cd(8);
        RuthlessPredator.update()
                .active("次に行う通常攻撃が2回攻撃になり、{1}が付与される。Fury消費すると攻撃回数が3回に増加し、{2}を付与する。但し、Fury増加効果は無くなる。")
                .variable(1, Stun, 0.75)
                .variable(2, Stun, 1.5)
                .cd(13, -1)
                .type(SkillType.OnHitEffectable);
        SliceandDice.update()
                .active("指定方向にダッシュし触れた敵ユニットに{1}を与える(Slice)。このスキルが敵にヒットした場合、4秒間の間だけ再度使用できる(Dice)。Fury消費時:Diceで与えるダメージが50%増加し、4秒間{2}を与える。SliceではFuryを消費しない。")
                .variable(1, PhysicalDamage, 30, 30, bounusAD(0.9))
                .variable(2, ARReductionRatio, 15, 5)
                .cd(18, -1)
                .range(450);
        Dominus.update()
                .active("15秒間サイズが大きくなり、スキルの射程が増加する。更に{1}を得て{2}の敵ユニットに毎秒{3}を与える。また、毎秒5Furyを得る。")
                .variable(1, Health, 300, 150)
                .variable(2, Radius, 350)
                .variable(3, MagicDamage, 40, 30, ap(0.1))
                .cd(120);

        /** Rengar */
        UnseenPredator.update()
                .passive("ステルス状態または茂みの中から相手を攻撃する際には、通常攻撃の射程が増加し相手に飛びつくようになる。この効果はステルス状態が解除された、また茂みから出た後0.5秒の間でも発生し、またステルス状態や茂みの中の視界が取られていた場合でも発生する。増加後射程: 600(Bonetooth Necklaceが9 Trophies以上の場合は750)また、Rengarがスキルで敵ユニットにダメージを与える度に1 Ferocityを得て(最大5 Ferocity)、5 Ferocity時にはFerocityを消費して強化(Empowered)されたスキルを使用することが出来る。");
        Savagery.update()
                .active("次の通常攻撃に追加物理DMを付与し、次の通常攻撃から4秒間攻撃速度が増加する。追加物理DM: 30/60/90/120/150増加AS: 30/35/40/45/50%CD: 8/7.5/7/6.5/6sEmpoweredActive:次の通常攻撃に追加物理DMを付与し、次の通常攻撃から4秒間攻撃速度が増加する。攻撃速度増加効果は通常のSavageryと重複する。")
                .cd(8, -0.5);
        BattleRoar.update()
                .active("周囲の敵ユニットに魔法DMを与える。また、このスキルが敵ユニットに命中すると、3秒間自身のArmorとMagic Resistが増加する。魔法DM: 50/80/110/140/170 (+0.8) 効果範囲: 500増加AR/MR: 15/22/29/36/43CD: 15/14/13/12/11sEmpoweredActive:周囲の敵ユニットに魔法DMを与え、自身のHPを回復する。また、このスキルが敵ユニットに命中すると、3秒間自身のArmorとMagic Resistが増加する。ArmorとMagic Resistの増加効果は通常のBattle Roarと重複しない。")
                .cd(15, -1);
        BolaStrike.update()
                .active("対象の敵ユニットに投げ縄を投げ、物理DMとスロー(2.5s)を与える。スローは時間経過と共に元に戻る。物理DM: 60/105/150/195/240 + [増加攻撃力 × 70%]スロー: 50/55/60/65/70%CD: 12/11/10/9/8s Range: 575EmpoweredActive:対象の敵ユニットに投げ縄を投げ、物理DMとスネア(1s)とスロー(2.5s)を与える。スローは時間経過と共に元に戻る。")
                .cd(12, -1)
                .range(575);
        ThrillOftheHunt.update()
                .active("1秒後に自身が最大7秒間(Bonetooth Necklaceが14 Trophiesの場合10秒間)ステルス状態になり、自身を中心とした広範囲内の敵Champion全員の視界を得る。ステルス準備中に攻撃を行うかダメージを受ける度に、ステルス状態になるのに必要な時間が1秒増加する。ステルス準備開始から3秒経過するとダメージを受けていてもステルス状態になる。ステルス状態の間は移動速度が増加し、0.75秒毎に1 Ferocityを得る。これらの効果はステルス状態が解除されると終了する。")
                .cd(140, -35);

        /** Riven */
        RunicBlade.update()
                .passive("スキルを使用するごとに1チャージを得る。チャージがある状態で通常攻撃を行うと、チャージを消費して通常攻撃に{1}が付与される。チャージは最大3スタックされ、通常攻撃ごとに1チャージずつ消費される。チャージは5秒間増加または消費がないと0になる。建物には無効。")
                .variable(1, PhysicalDamage, 0, 0, amplify(AD, new Per3LevelAdditional(0.2, 0.05)));
        BrokenWings.update()
                .active("前方にステップし、{1}の敵ユニットを剣で切りつけて{2}を与える。このスキルは短期間の間、3回まで連続して使用できる。3度目の使用でジャンプを行い、着地時に{3}の敵ユニットに{2}と{4}を与える。また、スキルを使用する度にオートアタックタイマーがリセットされる。最大DMは{5}。")
                .variable(1, Radius, 112.5)
                .variable(2, PhysicalDamage, 30, 25, bounusAD(0.7))
                .variable(3, Radius, 150)
                .variable(4, Knockback, 225)
                .variable(5, PhysicalDamage, 90, 75, bounusAD(2.1))
                .cd(1)
                .range(260);
        KiBurst.update()
                .active("{1}の敵ユニットに{2}と{3}を与える。")
                .variable(1, Radius, 125)
                .variable(2, PhysicalDamage, 50, 30, bounusAD(1))
                .variable(3, Stun, 0.75)
                .cd(11, -1);
        Valor.update()
                .active("指定方向にダッシュ({1})し、2.5秒間{2}が付与される。")
                .variable(1, Distance, 325)
                .variable(2, Shield, 70, 30, bounusAD(1))
                .cd(10, -1)
                .range(325);
        BladeOftheExile.update()
                .active("15秒間折れた剣の刃を再生させ、{1}増加し、射程が増加する(通常攻撃: {2} Broken Wings: {3} Ki Burst: {4})。また、このスキルを再度使用することで一度だけ0.5秒後に指定方向に巨大な衝撃波を発生させ、範囲内の敵ユニットに{5}与える。対象が受けているダメージに比例して与えるダメージが増加して、最大DMは{6}。")
                .variable(1, AD, 0, 0, ad(0.2))
                .variable(2, Range, 200)
                .variable(3, Range, 325)
                .variable(4, Range, 270)
                .variable(5, PhysicalDamage, 80, 40, bounusAD(0.6))
                .variable(6, PhysicalDamage, 240, 120, bounusAD(1.8))
                .cd(110, -30);

        /** Rumble */
        JunkyardTitan.update()
                .passive("Ult以外のスキルを使うとHeatが20増加し、それが50以上になると「Danger Zone」状態に入り全てのスキルに追加効果がつくようになる。Heatが100になると6秒間スキルが使用不可能になり、通常攻撃に追加{1}が付与される。Heatは時間によって減少していく。追加魔法DMは建物には無効。")
                .variable(1, MagicDamage, 20, 0, ap(0.3), amplify(Lv, 5));
        Flamespitter.update()
                .active("3秒間前方の範囲を火炎放射器で焼き払い、範囲内の敵ユニットに毎秒{1}を与える。minionに与えるダメージは半分。Danger Zone中は{2}を与える。")
                .variable(1, MagicDamage, 30, 23.3, ap(0.45))
                .variable(2, MagicDamage, 37.5, 29.1, ap(0.56))
                .cd(6)
                .range(600);
        ScrapShield.update()
                .active("2秒間{1}を得て1秒間{2}する。Danger Zone中は{3}を得て1秒間{4}する。")
                .variable(1, Shield, 50, 30, ap(0.4))
                .variable(2, MSRatio, 10, 5)
                .variable(3, Shield, 62.5, 37.5, ap(0.5))
                .variable(4, MSRatio, 12.5, 6.25)
                .cd(6);
        ElectroHarpoon.update()
                .active("指定方向に銛を放ち当たった敵ユニットに{1}と3秒間{2}を与える。このスキルは3秒の間、2回まで連続して使用できる(但し、一度使用する度に1秒のCDが発生する)。2発目はHeatが増加しない。また、このスキルによるスローはスタックする。Danger Zone中では{3}と3秒間{4}を与える。")
                .variable(1, MagicDamage, 55, 30, ap(0.5))
                .variable(2, MSSlowRatio, 15, 5)
                .variable(3, MagicDamage, 68.75, 37.5, ap(0.625))
                .variable(4, MSSlowRatio, 18.75, 6.25)
                .cd(10)
                .range(850);
        TheEqualizer.update()
                .active("指定した位置から指定方向({4})にロケットを打ち出し、当たった敵ユニットに{1}と{2}を与える。また5秒間ロケットの着弾した地面に炎が残り、その上にいる敵ユニットに毎秒{3}と{2}を与える。")
                .variable(1, MagicDamage, 150, 75, ap(0.5))
                .variable(2, MSSlowRatio, 35)
                .variable(3, MagicDamage, 100, 40, ap(0.2))
                .variable(4, Distance, 1000)
                .cd(105, -15)
                .range(1750);

        /** Sejuani */
        Frost.update().passive("通常攻撃にFrostを付与する。Frost状態の敵ユニットはスロー(10%、3s)を受ける。");
        ArcticAssault.update()
                .active("指定方向に突進し、接触した全ての敵ユニットに魔法DMとFrostを与え、対象がMinionの場合は更にノックバックさせる。敵Championに当たるか最大距離だけ移動すると突進は止まる。")
                .mana(70, 5)
                .cd(19, -2)
                .range(700);
        NorthernWinds.update()
                .active("6秒間極寒の嵐を周囲に召還し、周囲の敵ユニットに毎秒魔法DMを与える。魔法DMは敵ユニットがFrostまたはPermafrostの時には50%増加する。")
                .mana(40)
                .cd(10);
        Permafrost.update()
                .active("周囲の敵ユニットのFrostをPermafrostにし、魔法DMを与える。Permafrost状態の敵ユニットは強力なスロー(3s)を受ける。")
                .mana(55)
                .cd(11);
        GlacialPrison.update()
                .active("指定方向に武器を投げ、最大距離飛ぶか敵Championに命中するとその場で氷が爆発し、周囲の敵ユニットに魔法DMとスタン(1s)を与え、Frostにする。武器が直撃した敵Championはスタン効果時間が2sになる。")
                .mana(100)
                .cd(130, -15)
                .range(1150);

        /** Shaco */
        Backstab.update().passive("対象の背後から攻撃した場合にダメージが20%増加する。");
        Deceive.update()
                .active("指定地点にテレポートし、その後3.5秒間ステルス状態になる。また、スキル使用後6秒以内に通常攻撃を行うと必ずクリティカルになる。その際のクリティカルダメージはレベル毎に変化する。")
                .mana(90, -10)
                .range(400);
        JackInTheBox.update()
                .active("指定地点に60秒持続する人形を設置する。人形は設置後2秒でステルス状態となり、敵ユニットがステルス状態の人形から範囲300以内に近づくと、人形のステルスが解除されると同時に近くの敵ユニットにFearを与え、5秒間通常攻撃を行った後に破壊される。")
                .mana(60)
                .cd(16)
                .range(425);
        TwoShivPoison.update()
                .passive("通常攻撃時にスロー(2s)を与える。対象がChampion以外の場合、更に命中率低下(値はスローと同じ)を与える。命中率低下を受けたユニットは一定確率で通常攻撃が外れる(ブラインドと同じ)。スロー,命中率低下: 10/15/20/25/30%")
                .active("対象の敵ユニットに魔法DMとスロー(Passiveと同じ効果、3s)を与える。効果後はCDが解消されるまでPassiveの効果が無くなる。")
                .mana(50, 5)
                .cd(8)
                .range(625);
        Hallucinate.update()
                .active("18秒間持続する自身のイリュージョン(敵からの見た目は本体と同じ)を作成する。（RまたはALT押しながらクリックで任意の操作可能）イリュージョンは本体の75%の攻撃力を持ち、150%のダメージを受ける。また本体の一部アイテムの効果を引き継ぐ。イリュージョン死亡または効果時間終了時に爆発し、近くの敵ユニットに魔法DMを与える。イリュージョンが塔に与えるダメージは半分。このスキルを使用してもステルスは解除されない。")
                .mana(100)
                .cd(100, -10);

        /** Shen */
        KiStrike.update()
                .passive("9秒に1度通常攻撃に「気」回復効果と追加魔法DMが付与される。このスキルはShenが通常攻撃を行う度にCDが1秒解消される。CD解消は建物を攻撃した場合は発生しない。LV1/7/13で「気」回復量が増加する。")
                .cd(9);
        VorpalBlade.update()
                .active("対象の敵ユニットに魔法DMと5秒間持続するDebuffを与える。Debuffが付与された対象に通常攻撃またはダメージスペルで攻撃をすると、攻撃した味方ChampionのHPが3秒かけて回復する。このスキルでLHを取った場合、回復HPの33%の回復効果を得る。")
                .cd(6, -0.5)
                .range(475);
        Feint.update().active("3秒間DMを軽減するシールドを自身に付与する。シールドが持続している間はKi StrikeのCD解消効果が1秒から2秒になる。").cd(9, -1);
        ShadowDash.update()
                .active("指定地点まで素早く移動し接触した敵Championに魔法DMとTaunt(1.5s)を与える。ShenはTaunt効果中の対象から受ける通常攻撃のダメージを半減する。またこのスキルが敵Championに命中する度に「気」が40回復する。")
                .cd(16, -2)
                .range(600);
        StandUnited.update().active("MAP内の味方Championを指定し対象に5秒間持続するシールドを付与する。その後3秒詠唱しそこまでワープする。").cd(200, -20);

        /** Shyvana */
        FuryOftheDragonborn.update()
                .passive("Shyvanaは通常攻撃時に次の効果を得る。・Twin Bite：CDを0.5秒解消させる。対象が建物の場合は無効。・Burnout：効果時間が1秒延長される。最大4秒延長できる。対象が建物の場合も有効。・Flame Breath：Flame BreathのAR低下を受けている敵ユニットに対し、Flame Breathのダメージの15%分の追加魔法DMが発生する。・Dragon's Descent：通常攻撃時に2Furyを得る。また、Human formの時には1.5秒毎に1Furyを得る。");
        TwinBite.update()
                .active("次に行う通常攻撃が2回攻撃になる。このスキルはOn-Hit Effectsの影響を受ける。2回目の物理DM: [攻撃力 × 80/85/90/95/100%]Dragon Form:対象の周囲にいる敵ユニットにも同様の効果を与える。")
                .cd(10, -1);
        Burnout.update()
                .active("3秒間周囲の敵ユニットに毎秒魔法DMを与え、その間Shyvanaの移動速度が増加する。移動速度上昇は時間経過と共に減少する。毎秒魔法DM: 25/40/55/70/85 + [増加攻撃力 × 20%] 効果範囲: 325MS増加: 30/35/40/45/50%Dragon Form:Shyvanaの通り道を5秒間炎上させ、その上にいる敵ユニットにも毎秒魔法DMを与える。")
                .cd(12);
        FlameBreath.update()
                .active("指定方向に火球を放ち当たった敵ユニットに魔法DMを与え、4秒間対象のARを15%減少させる。魔法DM: 80/125/170/215/260 (+0.6)Dragon Form:Shyvanaの前方の扇状の範囲を巻き込む範囲攻撃となる。")
                .cd(12, -1)
                .range(700);
        DragonsDescent.update()
                .passive("ARとMRが増加する。 AR/MR増加はDragon Formの時は2倍になる。増加AR,MR: 10/15/20また、このスキルのLv1取得時に100Furyを得る。")
                .active("このスキルはHuman formでFuryが100貯まった時のみ使用可能。Dragon Formに変身し、指定地点まで飛んで移動する。その際の移動経路上にいる敵ユニットに魔法DMを与え、移動地点の方向にノックバックさせる。Dragon Formでは毎秒6Fury減少し、0になるとHuman Formに戻る。")
                .range(1000);

        /** Singed */
        EmpoweredBulwark.update().passive("{1}を得る。").variable(1, Health, 0, 0, amplify(Mana, 0.25));
        PoisonTrail.update()
                .passive("Singedの通り道に3.25秒間持続する毒を撒き、触れた敵ユニットに3秒間毎秒{1}を与える。")
                .variable(1, MagicDamage, 22, 0, ap(0.3))
                .mana(13)
                .cd(1);
        MegaAdhesive.update()
                .active("指定地点に5秒間持続する粘着剤を撒き、{1}の敵ユニットに{2}を与え続ける。この効果は範囲外に出てからも1秒間持続する。")
                .variable(1, Radius, 350)
                .variable(2, MSSlowRatio, 35, 10)
                .mana(70, 10)
                .cd(14)
                .range(1000);
        Fling.update()
                .active("対象の敵ユニット{1}を与え、Singedの後ろに投げ飛ばす({2})。")
                .variable(1, MagicDamage, 100, 50, ap(1))
                .variable(2, Distance, 550)
                .mana(100, 10)
                .cd(10)
                .range(125);
        InsanityPotion.update()
                .active("25秒間{1}、{2}、{3}、{4}、{5}、{6}を得て、{7}する。")
                .variable(1, AP, 35, 15)
                .variable(2, AR, 35, 15)
                .variable(3, MR, 35, 15)
                .variable(4, Hreg, 35, 15)
                .variable(5, Mreg, 35, 15)
                .variable(6, Tenacity, 10, 5)
                .variable(7, MSRatio, 35, 15)
                .mana(150)
                .cd(100);

        /** Sion */
        FeelNoPain.update()
                .passive("40%の確率で{1}する。この軽減は防御力計算より先に行われる。レベル1、7、13で軽減DMが上昇する。")
                .variable(1, AttackDamageReduction, new Per6Level(30, 10));
        CrypticGaze.update()
                .active("対象の敵ユニットに{1}と{2}を与える。")
                .variable(1, MagicDamage, 70, 55, ap(0.9))
                .variable(2, Stun, 1.5)
                .mana(100)
                .cd(12, -1)
                .range(550);
        DeathsCaress.update()
                .active("{1}を得る。10秒間シールドが残っていた場合、シールドが破裂し{2}の敵ユニットに{3}を与える。使用から4秒後以降に再度使用で、即座にシールドを破裂させる。")
                .variable(1, Shield, 100, 50, ap(0.9))
                .variable(2, Radius, 550)
                .variable(3, MagicDamage, 100, 50, ap(0.9))
                .mana(70, 10);
        Enrage.update()
                .passive("{1}を得る。使用中にLHをとるとSionの最大HPが{2}増加する。対象が敵Champion/SiegeまたはSuperMinion/Buffを持った中立クリープの場合、増加値は2倍になる。")
                .variable(1, AD, 25, 10)
                .variable(2, Count, 1, 0.5)
                .cost(Health, 6, 2)
                .type(SkillType.Toggle);
        Cannibalism.update()
                .active("20秒間{1}を得て{2}し、通常攻撃をするたびに{4}の味方ユニットは{3}する。")
                .variable(1, LS, 50, 25)
                .variable(2, ASRatio, 50)
                .variable(3, RestoreHealth, 0, 0, amplify(AttackDamageRatio, 25, 12.5))
                .variable(4, Radius, 200)
                .mana(100)
                .cd(90);

        /** Sivir */
        FleetOfFoot.update().passive("敵Championに通常攻撃でダメージを与えると、2秒間{1}する。").variable(1, MSRatio, 50).conditional(1);
        BoomerangBlade.update()
                .active("指定方向にブーメランを投げ、当たった敵ユニットに{1}を与える。ダメージは敵に当たるごとに20%ずつ減り、最大で40%まで低下する。行きと帰りそれぞれに攻撃判定がある。")
                .variable(1, PhysicalDamage, 60, 45, ap(0.5), bounusAD(1.1))
                .mana(70, 10)
                .cd(9)
                .range(1200);
        Ricochet.update()
                .active("次に行う通常攻撃に追加{1}が付与され、5回跳ね返る({2})ようになる。この追加ダメージはCriticalHitによって増幅される。On-Hit Effectsの効果は最初に当たったユニットにのみ発動し、跳ね返る毎にダメージが20%ずつ低下する。")
                .variable(1, PhysicalDamage, 20, 15)
                .variable(2, Radius, 450)
                .mana(40)
                .cd(7, -1);
        SpellShield.update()
                .active("Sivirに3秒間{1}を付与し、その間一度だけ敵のスキルを無効化する。無効化した場合{2}する。")
                .variable(1, Status.SpellShield)
                .variable(2, RestoreMana, 150)
                .mana(75)
                .cd(22, -3);
        OnTheHunt.update()
                .active("10秒間以下の能力をもつ{1}のオーラを発生させる。{2}し、自身は{3}、近くの味方ユニットは{4}する。一度範囲内に入れば、Sivirから離れても効果が持続する。")
                .variable(1, Radius, 1200)
                .variable(2, MSRatio, 20)
                .variable(3, ASRatio, 30, 15)
                .variable(4, ASRatio, 15, 7.5)
                .mana(100)
                .cd(100, -10)
                .range(1200);

        /** Skarner */
        Energize.update()
                .passive("通常攻撃を行うたびに、 Skarnerのすべてのスキルの{1}される(対象がChampionの場合は{2})。建物に対しては無効。")
                .variable(1, CDDecrease, 0.5)
                .variable(2, CDDecrease, 1);
        CrystalSlash.update()
                .active("Skarnerの近くにいるすべての敵ユニットに{1}を与える。このスキルが敵ユニットにヒットした場合、5秒間Crystal Energyのスタックを得る。スタックがある状態で再度このスキルを使用すると、追加{2}と2秒間{3}が付与される。")
                .variable(1, PhysicalDamage, 25, 15, bounusAD(0.8))
                .variable(2, MagicDamage, 24, 12, ap(0.4))
                .variable(3, MSSlowRatio, 20, 5)
                .mana(20, 2)
                .cd(3.5)
                .range(350);
        CrystallineExoskeleton.update()
                .active("6秒間{1}を張る。シールドが残っている間{2}し、{3}する。")
                .variable(1, Shield, 70, 45, ap(0.6))
                .variable(2, MSRatio, 15, 2)
                .variable(3, ASRatio, 30, 5)
                .mana(60)
                .cd(18);
        Fracture.update()
                .active("指定方向に貫通するエネルギーを飛ばし、当たった敵ユニットに{1}と6秒間持続するマークを付与する。自身がマークが付いた敵ユニットを攻撃するか、このスキルで敵ユニットを倒した場合、マークを消費して{2}する。HP回復量はマークを消費する度に50%ずつ低下していく。")
                .variable(1, MagicDamage, 80, 40, ap(0.7))
                .variable(2, RestoreHealth, 30, 15, ap(0.3))
                .mana(50, 5)
                .cd(10)
                .range(600);
        Impale.update()
                .active("対象の敵Championに{1}と{2}を与える。効果中は対象の敵Championを引っ張る事が出来る。また、効果終了時に追加{3}を与える。")
                .variable(1, MagicDamage, 100, 50, ap(0.5))
                .variable(2, Suppression, 1.75)
                .variable(3, MagicDamage, 100, 50, ap(0.5))
                .mana(100, 25)
                .cd(130, -10)
                .range(350);

        /** Sona */
        PowerChord.update()
                .passive("Auraを切り替えても、以前のAuraの効果が1秒間持続する。切替時は他のAuraスキルが0.5秒間CDになる。また、3回スキルを使用した後の通常攻撃に追加魔法DMと、そのとき展開しているAuraに応じた追加効果が発生する。");
        HymnOfValor.update()
                .passive("Aura:周囲の味方ChampionのAD,APを増加させる。増加AD,AP: 4/8/12/16/20")
                .active("最も近い敵ユニット2体に魔法DMを与える。Sonaの通常攻撃範囲内に敵Championがいる場合、それらを優先して狙う。魔法DM: 50/100/150/200/250 (+0.7)消費MN: 45/50/55/60/65 CD: 7s Range: 700Power Chord - Stacatto:追加魔法DMが2倍になる。")
                .mana(45, 5)
                .cd(7)
                .range(700);
        AriaOfPerseverance.update()
                .passive("Aura:周囲の味方ChampionのAR,MRを増加させる。増加AR,MR: 3/6/9/12/15")
                .active("近くにいる最もHPが減っている味方Champion1体とSonaのHPを回復する。HPを回復した対象と自身は3秒間、AuraのAR,MR増加が2倍になる。回復HP: 40/60/80/100/120 (+0.25)消費MN: 60/65/70/75/80 CD: 7s Range: 900Power Chord - Diminuendo:対象が与えるダメージを20%低下するDebuffを付与する。この効果は4秒間持続する。")
                .mana(60, 5)
                .cd(7)
                .range(900);
        SongOfCelerity.update()
                .passive("Aura:周囲の味方Championの移動速度を増加させる。増加移動速度: 4/8/12/16/20")
                .active("1.5秒間周囲の味方ユニットの移動速度を増加させる。増加移動速度: 6/8/10/12/14% 効果範囲: 850消費MN: 65 CD: 7sPower Chord - Tempo:スロー(40%,2s)効果を付与する。")
                .mana(65)
                .cd(7);
        Crescendo.update().active("前方範囲の敵Championに魔法DMを与え、1.5秒間強制的に踊らせる(スタン)。").mana(100, 50).cd(140, -20).range(1000);

        /** Soraka */
        Consecration.update().passive("{1}の味方Championは{2}を得る。").variable(1, Radius, 1000).variable(2, MR, 16);
        Starcall.update()
                .active("{1}の敵ユニットに{2}を与え、スタックを1つ増加させる。スタック1つにつき{3}を与える。スタックは5秒間持続し最大10まで増加する。")
                .variable(1, Radius, 675)
                .variable(2, MagicDamage, 60, 25, ap(0.4))
                .variable(3, MRReduction, 8, 1)
                .mana(20, 15)
                .cd(2.5);
        AstralBlessing.update()
                .active("対象の味方ユニットは{1}し、3秒間{2}を得る。")
                .variable(1, RestoreHealth, 70, 70, ap(0.45))
                .variable(2, AR, 25, 20)
                .mana(80, 30)
                .cd(20)
                .range(750);
        Infuse.update()
                .active("対象の味方Championに使用すると{1}する。敵ユニットに使用すると{2}と{3}を与える。このスキルはSoraka自身を対象とすることが出来ない。")
                .variable(1, RestoreMana, 40, 40)
                .variable(2, MagicDamage, 50, 50, ap(0.6))
                .variable(3, Silence, 1.5, 0.25)
                .mana(40, 40)
                .cd(10)
                .range(725);
        Wish.update()
                .active("全ての味方Championは{1}する。")
                .variable(1, RestoreHealth, 200, 120, ap(0.7))
                .mana(100, 75)
                .cd(160, -15);

        /** Swain */
        CarrionRenewal.update().passive("敵ユニットを倒す毎に{1}する。").variable(1, RestoreMana, 9, 0, amplify(Lv, 1));
        Decrepify.update()
                .active("Swainの位置にビームを吐くカラスを設置し、対象の敵ユニットに毎秒{1}と{2}を与える。3秒経つか対象のユニットがカラスの有効範囲外({3})に移動すると効果が切れる。")
                .variable(1, MagicDamage, 25, 15, ap(0.3))
                .variable(2, MSSlowRatio, 20, 5)
                .variable(3, Radius, 900)
                .mana(60, 10)
                .cd(8)
                .range(625);
        Nevermove.update()
                .active("地点を指定した0.5秒後に範囲内の敵ユニットに{1}と{2}を与える。")
                .variable(1, MagicDamage, 80, 40, ap(0.7))
                .variable(2, Snare, 2)
                .mana(80, 10)
                .cd(18, -2)
                .range(900);
        Torment.update()
                .active("対象の敵ユニットに4秒かけて{1}を与える。また、効果中はSwainが対象のユニットに与えるダメージが増加する。")
                .variable(1, MagicDamage, 75, 40, ap(0.8))
                .mana(65, 5)
                .cd(10)
                .range(625);
        RavenousFlock.update()
                .passive("n:Swainがカラスに変身し、近くにいる敵ユニット3体(敵Championを優先)に毎秒魔法DMを与える。また、与えたダメージの25%(Championに対しては75%)が回復する。")
                .cd(8)
                .range(700);

        /** Syndra */
        Transcendent.update()
                .passive("各スキルを最高ランクまで上げると追加効果が発生する。・Dark Sphere：Championに対するDMが15%増加する。・Force of Will：スローの効果時間が2sになる。・Scatter the Weak：扇形範囲の横幅が50%増加する。・Unleashed Power：射程が75増加する。");
        DarkSphere.update()
                .active("指定地点にDark Sphereを召喚し、周囲の敵ユニットに魔法DMを与える。Dark Sphereはその後6秒間持続する。")
                .mana(40, 10)
                .cd(4)
                .range(800);
        ForceOfWill.update()
                .active("指定したDark Sphereか敵minionまたは中立モンスター(DragonとBaronには無効)のいずれかを自身まで引き寄せ、最大5秒間引っ張り続ける。この際Dark Sphereを引き寄せた場合、そのDark Sphereの持続時間は引き寄せてから6秒間に更新される。その後再度地点を指定することで指定地点に引き寄せたものを投げ、その周囲の敵ユニットに魔法DMとスロー(1.5s)を与える。また指定地点の視界を得る。")
                .mana(60, 10)
                .cd(12, -1);
        ScatterTheWeak.update()
                .active("指定方向扇形の範囲にいる敵ユニットに魔法DMを与え、ノックバックさせる。ノックバック距離はSyndraに近い地点にいるほど増加する。扇形の範囲内にDark Sphereがあった場合同様にノックバックさせ、それに当たった敵ユニットに同様の魔法DM(このスキルのDMとは重複しない)とスタン(1.5s)を与える。")
                .mana(50)
                .cd(18, -1.5)
                .range(650);
        UnleashedPower.update()
                .active("自身の周辺にDark Sphereを3つ召喚し、指定した敵Championに向けて自身の周囲に存在する全てのDark Sphereを向かわせ、魔法DMを与える。この際に召喚したDark Sphereは6秒間持続する。")
                .mana(100)
                .cd(100, -10)
                .range(675);

        /** Talon */
        Mercy.update().passive("スロー、スタン、スネア、サプレッションを受けている状態の敵ユニットに対しては通常攻撃のダメージが10%上昇する。");
        NoxianDiplomacy.update()
                .active("次の通常攻撃に追加物理DMが付与される。対象がChampionの場合、更に6秒間対象を出血させその間毎秒物理DMを与える。また出血中の対象の視界を得る。")
                .mana(40, 5)
                .cd(8, -1);
        Rake.update()
                .active("前方指定範囲に飛んだ後すぐ戻る刃を投げ当たった敵ユニットに物理DMとスロー(2s)を与える。刃は戻り際にも判定がある。")
                .mana(60, 5)
                .cd(10)
                .range(600);
        Cutthroat.update()
                .active("対象の敵ユニットに跳躍しサイレンス(1s)とマーク(3s)を与える。マークがついた敵ユニットに対してはTalonが与えるダメージが増加する。")
                .mana(35, 5)
                .cd(18, -2)
                .range(700);
        ShadowAssault.update()
                .active("Talonの周囲に8本の刃を投げ当たった敵ユニットに物理DMを与え、同時に2.5秒間ステルスになりその間移動速度が40%増加する。ステルスが解除される、または敵ユニットを攻撃すると刃がTalonの元に戻ってくる。刃は戻り際にも判定がある。")
                .mana(80, 10)
                .cd(75, -10)
                .range(1000);

        /** Taric */
        Gemcraft.update().passive("通常攻撃をすると{1}する。").variable(1, RestoreMana, 0, 0, amplify(DealtDamageRatio, 0.075));
        Imbue.update()
                .active("対象の味方ユニットとTaricの{1}する。自身に使用した場合は{2}する。このスキルは自身が通常攻撃を行う毎にCDが1秒解消される。対象が敵Championの場合は3秒解消される。")
                .variable(1, RestoreHealth, 60, 40, ap(0.6))
                .variable(2, RestoreHealth, 84, 56, ap(0.84))
                .mana(80, 15)
                .cd(20, -1)
                .range(750);
        Shatter.update()
                .passive("Taricは{1}を得て、更に近くの味方Championの{1}を増加させる{2}のAuraを得る。(Taric自身はAuraと合わせて2倍の効果を得る)")
                .variable(1, AR, 10, 5)
                .variable(2, Radius, 1000)
                .active("{3}の敵ユニットに{4}を与え、4秒間{5}を与える。効果後はCDが解消されるまでPassiveのTaric自身の増加ARが無くなる。")
                .variable(3, Radius, 400)
                .variable(4, MagicDamage, 60, 45, ap(0.6))
                .variable(5, ARReduction, 10, 5)
                .mana(50, 10)
                .cd(10);
        Dazzle.update()
                .active("対象の敵ユニットに{1}と{2}を与える。魔法DMは対象との距離が近いほど増加し、距離が遠いほど低下する。最大DMは{3}。")
                .variable(1, MagicDamage, 80, 60, ap(0.8))
                .variable(2, Stun, 1.5)
                .variable(3, MagicDamage, 40, 30, ap(0.3))
                .mana(95)
                .cd(14, -1)
                .range(625);
        Radiance.update()
                .active("{1}の敵ユニットに{2}を与える。スキル使用後の10秒間、{3}と{4}を得て、更に近くの味方Championの{5}と{6}を増加させるAura({7})を展開する。")
                .variable(1, Radius, 400)
                .variable(2, MagicDamage, 150, 100, ap(0.7))
                .variable(3, AD, 30, 20)
                .variable(4, AP, 30, 20)
                .variable(5, AD, 15, 10)
                .variable(6, AP, 15, 10)
                .variable(7, Radius, 1000)
                .mana(100)
                .cd(60);

        /** Teemo */
        Camouflage.update().passive("2秒間動かないとステルス状態になる。何か行動を行うか、強制的に移動させられるとステルスが解除され、ステルス解除後3秒間攻撃速度が40%増加する。");
        BlindingDart.update().active("対象の敵ユニットに魔法DMとブラインドを与える。").mana(70, 10).cd(8).range(680);
        MoveQuick.update()
                .passive("Teemoの移動速度が増加する。敵Championかタワーからダメージを受けると5秒間効果が切れる。増加移動速度: 10/14/18/22/26%")
                .active("3秒間Passiveの増加移動速度が倍になる。移動速度が倍の間は敵Championやタワーからのダメージで効果が切れない。")
                .mana(40)
                .cd(17);
        ToxicShot.update().passive("通常攻撃時に追加魔法DMと毒が付与される。毒は4秒間持続し、毎秒魔法DMを与える。");
        NoxiousTrap.update()
                .active("指定地点に10分間持続するキノコの罠を仕掛ける(設置後1秒でステルス状態となる)。使用時にスタックを消費する。敵ユニットがステルス状態の罠を踏むと破裂し、周囲のユニットに4秒かけて魔法DMとスロー(4s)を与える。一定時間毎にスタック数が1つ増加し最大3つまでスタックされる。スタック増加時間はCD低減の影響を受ける。設置したキノコはChampionの通常攻撃でのみダメージを与えられる。")
                .cd(1)
                .range(230);

        /** Thresh */
        Damnation.update()
                .passive("レベルアップでArmorを得る事ができない。自身の周囲で敵ユニットが死んだ場合、一定の確率で魂（Soul）を落とす。魂へ近づくかDark Passageのランタンを魂の近くに置くとその魂を回収する事ができ、自身のArmorとAbility Powerが上昇する。落とした魂は15秒間持続し、敵チームがThreshの視界を得ていた場合、敵チームからも視認することができる。");
        DeathSentence.update()
                .passive("通常攻撃に追加魔法DMを付与する。このDMはDamnationのスタック数と、自身が前回敵ユニットに通常攻撃をしてから経過した時間に比例して増加する。建物を攻撃した場合は追加魔法DMは発生せず、時間がリセットされる事もない。追加魔法DM(最小): Damnationのスタック数追加魔法DM(最大): Damnationのスタック数 + [攻撃力 × 80/110/140/170/200%]")
                .active("指定方向に鎌を投げ、命中した敵ユニットに魔法DMとスタン(1.5s)を与え、対象を1.5秒かけて自身の方へ引き寄せる。このスキルを再度使用すると対象のユニットへ飛びつく。")
                .mana(80)
                .cd(18, -1.5)
                .range(1075);
        DarkPassage.update()
                .active("指定地点に6秒間持続するランタンを設置する。味方Championがランタンを指定すると、ランタンとその味方Championが自身の方へと引き寄せられる。更にランタンの周囲にいる魂を自動的に回収し、味方Championにダメージを一定値軽減するシールドを付与する。シールドを得られるのは1ユニットにつき1回のみ。自身がランタンから距離1500以上離れるとランタンは自動的に自身の下へと戻る。")
                .mana(40)
                .cd(22, -1.5)
                .range(950);
        Flay.update()
                .active("自身後方から前方への帯状領域内の敵ユニットに魔法DMを与える。また自身後方にいる敵ユニットは自身に近づく向きに、自身前方にいる敵ユニットには自身から遠ざかる向きにノックバックさせ、スロー(1.5s)を与える。")
                .mana(60, 5)
                .cd(9);
        TheBox.update()
                .active("自身の周囲に五角形の壁を創り出し、最初に壁に触れた敵Championに魔法DMとスロー(99%,2s)を与える。2つ目以降の壁に触れた敵championには半分の魔法DMとスロー(99%,1s)を与える。敵が触れた部分の壁は破壊され消滅する。")
                .mana(100)
                .cd(150, -10);

        /** Tristana */
        DrawaBead.update().passive("レベルに比例し通常攻撃とExplosive Shot(E)の射程が上昇する。");
        RapidFire.update().active("7秒間Tristanaの攻撃速度が大幅に上昇する。").mana(50).cd(20);
        RocketJump.update()
                .active("指定地点にジャンプしジャンプ先の周囲の敵ユニットに魔法DMとスロー(60%,2.5s)を与える。キルかアシストをとるとこのスキルのCDが解消される。")
                .mana(80)
                .cd(22, -2)
                .range(800);
        ExplosiveShot.update()
                .passive("通常攻撃で敵ユニットを倒した時にそのユニットの周囲の敵ユニットに魔法DMを与える。魔法DM: 50/75/100/125/150 (+0.25)")
                .active("対象の敵ユニットに5秒かけて魔法DMを与え、その間HP回復量を50%低下させる。")
                .mana(50, 10)
                .cd(16)
                .range(616);
        BusterShot.update().active("対象の敵ユニットに魔法DMを与え、対象とその周囲(範囲200)の敵ユニットをノックバックさせる。").mana(100).cd(60).range(700);

        /** Trundle */
        Decompose.update()
                .passive("{1}以内で敵ユニットが死んだとき、{2}する。レベル1、5、9、12、15で回復する割合が上昇する。")
                .variable(1, Radius, 1000)
                .variable(2, RestoreHealth, 0, 0, amplify(Health, new Per4LevelForTrundle(0.02, 0.01)));
        RabidBite.update()
                .active("次の通常攻撃のダメージが変更され、攻撃後自身の攻撃力が8秒間増加し、攻撃を受けたユニットの攻撃力をその半分だけ減少させる。建物には無効。")
                .mana(30)
                .cd(4);
        Contaminate.update()
                .active("指定範囲に8秒間持続する呪いを振りまく。範囲内に入っている間、自身の攻撃速度と移動速度が上昇し、受けるCC(Stun, Slow, Taunt, Fear, Snare, Silence, Blind)の効果時間が低減される。")
                .mana(60)
                .cd(15)
                .range(900);
        PillarOfFilth.update()
                .active("指定地点に7秒間持続する通行不可能なビーコンを設置し、ビーコンの周囲にいる敵ユニットにスローを与える。また、指定地点の視界を得る。")
                .mana(60)
                .cd(23, -3)
                .range(1000);
        Agony.update()
                .active("対象の敵ユニットのHPを一定量(魔法DM)、ARとMRを割合で奪う。その後6秒間かけて、合計で最初に吸収した量と同じだけのHP、AR、MRを奪う(つまり合計で2倍)。この効果は対象が吸収を受けている間続く。")
                .mana(75)
                .cd(80, -10)
                .range(700);

        /** Tryndamere */
        BattleFury.update()
                .passive("{1}する。通常攻撃時に5Fury、クリティカル時に10Fury、Spinning Slashが敵ユニットに命中するたびに2Furyを得る。敵ユニットを倒すと追加で10Furyを得る。Furyの上限は100、8秒間戦闘を行わないと毎秒5Furyずつ減少していく。建物を攻撃した場合はFuryは増加しない。")
                .variable(1, Critical, 0, 0, amplify(Stack, 0.35))
                .conditional(1);
        Bloodlust.update()
                .passive("{1}を得る。")
                .variable(1, AD, 5, 5, amplify(MissingHealthPercentage, 0.15, 0.05))
                .active("Furyをすべて消費し{2}する。")
                .variable(2, RestoreHealth, 30, 10, ap(1.5), amplify(Stack, 0.5, 0.45))
                .cd(12);
        MockingShout.update()
                .active("4秒間近くの敵Championに{1}を与え、後ろを向いている敵Championには更に4秒間{2}を与える。")
                .variable(1, ASSlowRatio, 20, 15)
                .variable(2, MSSlowRatio, 30, 7.5)
                .cd(14)
                .range(850);
        SpinningSlash.update()
                .active("指定地点まで武器を振り回しながら移動し、当たった敵ユニットに{1}を与える。このスキルはクリティカルが発生するたびに{2}する。")
                .variable(1, PhysicalDamage, 70, 30, ap(1), bounusAD(1.2))
                .variable(2, CDDecrease, 2)
                .cd(13, -1)
                .range(660);
        UndyingRage.update()
                .active("5秒間HPが1未満にならなくなる(死ななくなる)。また、このスキル使用時にFuryが{1}増加する。このスキルの使用は状態異常によって阻害されない。")
                .variable(1, Count, 50, 25)
                .cd(110, -10);

        /** Twisted Fate */
        LoadedDice.update().passive("味方Championが敵を倒した時に追加で{1}を得るようになる。").variable(1, Gold, 2);
        WildCards.update()
                .active("指定向き3方向に貫通するカードを飛ばし、当たった敵ユニットに{1}を与える。")
                .variable(1, MagicDamage, 60, 50, ap(0.65))
                .mana(60, 10)
                .cd(6)
                .range(1450);
        PickACard.update()
                .active("使用するとBlue、Red、Goldの3種のカードを選択し始め、再度使用でカードが決定する。決定したカードにより次の通常攻撃が魔法DMに変換され、以下の効果が追加される。Blue Card: {2}を与え{3}する。　Red Card: {4}の敵に{5}と2.5秒間{6}を与える。　Gold Card: {7}と{8}を与える。")
                .variable(2, MagicDamage, 40, 20, ap(0.4), ad(1))
                .variable(3, RestoreMana, 26, 13, ad(0.65))
                .variable(4, Radius, 100)
                .variable(5, MagicDamage, 30, 15, ap(0.4), ad(1))
                .variable(6, MSSlowRatio, 30, 5)
                .variable(7, MagicDamage, 15, 7.5, ap(0.4), ad(1))
                .variable(8, Stun, 1.2, 0.2)
                .mana(40, 15)
                .cd(6);
        StackedDeck.update()
                .passive("{1}を得て{2}する。通常攻撃4回毎に追加{3}を与える。")
                .variable(1, CDR, 3, 3)
                .variable(2, ASRatio, 3, 3)
                .variable(3, MagicDamage, 55, 25, ap(0.4));
        Destiny.update()
                .active("{1}間すべての敵Champion(ステルス中のChampion含む)の視界を得る。効果中に再度使用すると2秒間移動・攻撃が不可能になった後、指定した地点にワープする。。効果中は敵Championの頭上にアイコンが表示される。")
                .variable(1, Time, 6, 2)
                .mana(150, -25)
                .cd(150, -15)
                .range(5500);

        /** Twitch */
        DeadlyVenom.update().passive("通常攻撃時に毒を付与し、毎秒Trueダメージを与える。毒は6回までスタックし、6秒持続する。レベル1、6、11、16で毎秒ダメージが増加する。");
        Ambush.update()
                .active("使用から1.25秒後にステルス状態になる。ステルス状態ではTwitchの移動速度が20%増加し、ステルスを解除すると5秒間攻撃速度が増加する。ステルス準備中に攻撃を行うかダメージを受けると、ステルス状態になるのに再度1.25秒必要になる。ステルス準備開始から5秒経過するとダメージを受けていてもステルス状態になる。")
                .mana(60);
        VenomCask.update()
                .active("指定地点に毒の入った瓶を投げつけ、範囲内の敵ユニットにスロー(3s)と毒を2スタック分与える。また、指定した地点の視界を得る。")
                .mana(50)
                .cd(13, -1)
                .range(950);
        Expunge.update().active("毒をスタックされている近くの敵ユニットに物理DMを与える。ダメージはスタック数に比例し増加する。").mana(50, 10).cd(12, -1).range(1200);
        SprayandPray.update()
                .active("7秒間Twitchの攻撃力が増加し射程が850に長くなり、通常攻撃が敵ユニットを貫通するようになる。対象との直線上にいる敵ユニットにもダメージと毒スタックを与える。ダメージは敵に当たるごとに20%減少する。最小で40%。")
                .mana(100, 25)
                .cd(120, -10);

        /** Udyr */
        MonkeysAgility.update()
                .passive("スキルを使用する度に攻撃速度が10%増加し、ARとMRが4%増加する。この効果は5秒間持続し、3回までスタックする。また、スキルを使用するとその他のCD待ちでないスキルが1秒間のCDになる。");
        TigerStance.update()
                .passive("Persistent Effect:Udyrの攻撃速度が増加する。増加攻撃速度: 20/25/30/35/40%Activation:5秒間Udyrの攻撃速度が増加する(Persistent Effectと重複)。また、次の通常攻撃に追加魔法DMを付与する。このダメージは2秒かけて与えられる。建物には無効。")
                .mana(55, -5)
                .cd(6);
        TurtleStance.update()
                .passive("Persistent Effect:通常攻撃でクリティカルが発生しなくなるが、通常攻撃で与えたダメージの一定割合のHPとMNが回復する。回復HP: 10/12/14/16/18% 回復MN: 5/6/7/8/9%Activation:自身に5秒間持続するシールドを付与しダメージを軽減する。")
                .mana(55, -5)
                .cd(6);
        BearStance.update()
                .passive("Persistent Effect:通常攻撃にスタン(1s)が付与される。この効果は同一の対象に6秒に1度しか発動しない。Activation:一定時間移動速度が増加する。")
                .mana(55, -5)
                .cd(6);
        PhoenixStance.update()
                .passive("Persistent Effect:通常攻撃を3回行うごとに火を吹き前方の敵ユニットに魔法DMを与える。魔法DM: 40/80/120/160/200 (+0.25)Activation:5秒間周囲の敵ユニットに毎秒魔法DMを与える。またその間は攻撃力とAPが増加する。")
                .mana(55, -5)
                .cd(6);

        /** Urgot */
        ZaunTouchedBoltAugmenter.update()
                .passive("通常攻撃またはAcid Hunterでダメージを与えた対象に、与えるダメージを15%低下するDebuffを付与する。この効果は2.5秒間持続する。");
        AcidHunter.update()
                .active("指定方向にミサイルを飛ばし当たった敵ユニットに物理DMを与える。Noxian Corrosive Chargeの効果を受けている敵ユニットの近くを指定して使用すると、その敵ユニット目掛けてミサイルが飛んでいく。")
                .mana(40)
                .cd(2)
                .range(1000);
        TerrorCapacitor.update()
                .active("自身にシールドを付与し、7秒間ダメージを軽減する。シールドが残っている間は通常攻撃とAcid Hunterにスロー(1s)が付与される。")
                .mana(55, 5)
                .cd(16, -1);
        NoxianCorrosiveCharge.update()
                .active("指定範囲に爆弾を飛ばし当たった敵ユニットに5秒間かけて物理DMを与え、その間対象のARを下げる。また指定地点の視界を得る。")
                .mana(50, 5)
                .cd(15, -1)
                .range(900);
        HyperKineticPositionReverser.update()
                .active("対象の敵Championにサプレッションを付与しUrgotのAR,MRが5秒間上昇し、1s詠唱後に敵Championと自分の位置を入れ替える。入れ替わった後は敵Championにスロー(40%,3s)を与える。サプレッションはUrgotが詠唱している間持続する。")
                .mana(120)
                .cd(120)
                .range(550, 150);

        /** Varus */
        LivingVengeance.update()
                .passive("敵ユニットを倒すと3秒間攻撃速度が20%増加する。敵Championをキル/アシストした場合は効果時間が6秒、増加攻撃速度が40%に増加する。この効果はスタックしない。");
        PiercingArrow.update()
                .active("最初にスキルを使用すると狙いを付ける。この間は通常攻撃や他のスキルを使用できなくなり、Varusの移動速度が20%低下するが、狙いを付けている間は徐々にこのスキルのダメージと射程距離が増加していく(2秒で最大)。再度このスキルを使用することで指定方向に貫通する矢を放ち、当たった敵ユニットに物理DMを与える。ダメージは敵に当たるごとに15%減少する。最小で33%。狙いを付けて4秒間経過した場合はこのスキルは失敗しCDになるが、消費したマナの半分が回復する。")
                .mana(70, 5)
                .cd(16, -2)
                .range(850);
        BlightedQuiver.update()
                .passive("通常攻撃に追加魔法DMと6秒間持続する疫病が付与される。疫病は3回までスタックする。疫病のスタックが付与されている敵ユニットにVarusの他のスキルが命中した場合、疫病のスタックを消費して対象の最大HPに比例した魔法DMを与える。");
        HailOfArrows.update()
                .active("指定地点に矢の雨を放ち、範囲内の敵ユニットに物理DMを与える。指定地点は4秒間呪われ、範囲内の敵ユニットにスローとHP回復量-50%を付与する。")
                .mana(80)
                .cd(18, -2)
                .range(925);
        ChainOfCorruption.update()
                .active("指定方向に腐敗の蔓を投げつけ、当たった敵Championに魔法DMとSnare(2s)を与える。当たった敵Championからは徐々に腐敗が広がり、腐敗に触れた敵Championにも蔓が当たった敵Championと同様の効果を与える。")
                .mana(120)
                .cd(120, -15)
                .range(1075);

        /** Vayne */
        NightHunter.update().passive("敵Championに向かって移動する際に移動速度が30増加する。");
        Tumble.update().active("指定地点にローリングし、次の通常攻撃のダメージが上昇する。6秒間攻撃を行わないとCDになる。").mana(40).cd(6, -1);
        SilverBolts.update().passive("同じターゲットに3回連続して通常攻撃またはスキルで攻撃すると、ターゲットの最大HPに比例したTrue Damageを与える。");
        Condemn.update()
                .active("対象の敵ユニットをヘビークロスボウで狙撃し物理DMとノックバックを与える。ノックバックした敵が壁等に当たると追加で物理DMとスタン(1.5s)を与える。")
                .mana(90)
                .cd(20, -2)
                .range(450);
        FinalHour.update()
                .active("一定時間Vayneの攻撃力が上昇し、Tumbleのローリングにステルス(1s)が付与され、Night Hunterの移動速度増加が3倍になる。")
                .mana(80)
                .cd(70);

        /** Veigar */
        Equilibrium.update().passive("{1}を得る。").variable(1, MregRatio, 0, 0, amplify(MissingManaPercentage, 1));
        BalefulStrike.update()
                .passive("敵Championを倒すとAPが増加する。増加AP: 1/2/3/4/5")
                .active("対象の敵ユニットに魔法DMを与える。このスキルでLHを取るとAPが1増加する。対象が敵Champion/SiegeまたはSuperMinion/Buffを持った中立クリープの場合、増加値は2倍(+2)になる。")
                .mana(60, 5)
                .cd(8, -1)
                .range(650);
        DarkMatter.update()
                .active("指定地点に1.2秒後に隕石を降らし、当たった敵ユニットに魔法DMを与える。また隕石が落下するまでの間、指定地点の視界を得る。")
                .mana(70, 10)
                .cd(10)
                .range(900);
        EventHorizon.update().active("指定範囲に3秒間魔法陣を呼び出し、魔法陣の縁に触れた敵ユニットにスタンを与える。").mana(80, 10).cd(20, -1).range(650);
        PrimordialBurst.update()
                .active("対象の敵Championに魔法DMを与える。敵ChampionのAPに比例してダメージが増加する。")
                .mana(125, 50)
                .cd(130, -20)
                .range(650);

        /** Vi */
        BlastShield.update()
                .passive("敵ユニットにスキルでダメージを与えると、3秒間自身に最大HPの10%のダメージを軽減するシールドが展開される。レベル1/7/12でCDが減少する。")
                .cd(18, -5);
        VaultBreaker.update()
                .active("発動すると自身の移動速度が15%減少し、このスキルのダメージと射程が徐々に増加する(1.25sで最大)。再度使用で指定した方向へとダッシュし、命中した全ての敵ユニットに物理DMを与える。ダッシュ中に敵Championに衝突するとその時点で停止し、対象をノックバックさせる。このスキルにはDenting Blowsの効果が適用され、Minionや中立クリープに与えるダメージは75%に減少する。")
                .mana(50, 10)
                .cd(18, -2.5)
                .range(50);
        DentingBlows.update().passive("同一対象に3回連続して通常攻撃を行うと、対象の最大HPに比例した追加物理DMを与えると共に4秒間対象のARを20%減少させ、自身の攻撃速度が4秒間増加する。");
        ExcessiveForce.update()
                .active("チャージを1つ消費することで、次の通常攻撃のダメージが増加し、対象とその後方扇形範囲にいる敵ユニットにもダメージを与える範囲攻撃になる。チャージは一定時間ごとに増加し、最大2つまでスタックされる。")
                .mana(60)
                .cd(1)
                .range(600);
        AssaultandBattery.update()
                .active("対象の敵Championに突撃し、物理DMと打ち上げ(1.25s)を与える。一連の動作中は自身が受けるCCを無効化し、また対象のChampion以外でViに触れた敵ユニットには75%のダメージを与え、左右に吹き飛ばす。")
                .mana(100, 25)
                .cd(130, -25)
                .range(700);

        /** Viktor */
        EvolvingTechnology.update()
                .passive("Viktorは最初からHex Coreという、自身のステータスとスキルの効果を強化するアイテムを所持している。Hex Coreは1度だけショップで1000Gを消費して以下の３通りのいずれかにアップグレードすることが出来る。Hex CoreはViktorのアイテムスロットを1つ占有し、売却することは出来ない。Hex Core : +3 ability power per levelAugment: Power+3 ability power per level、+220 health、+6 health regen per 5sを得る。また、Power Transfer使用・命中時に移動速度が3秒間30%増加する。Augment: Gravity+3 ability power per level、+200 mana、+10% cooldown reduction、+5 mana regen per 5sを得る。また、Gravity Fieldの射程が30%増加する。Augment: Death+3 ability power per level、+45 ability powerを得る。また、Death Rayにダメージの30%分の追加魔法DMが付与される。このダメージは4秒間かけて与える。");
        PowerTransfer.update()
                .active("対象の敵ユニットに魔法DMを与え、ダメージの40%をシールドとして得る。シールドは3秒間持続する。")
                .mana(45, 5)
                .cd(9, -1)
                .range(600);
        GravityField.update()
                .active("0.25秒詠唱後、指定範囲に4秒間持続する重力束縛装置を呼び出し、範囲内の敵ユニットにスローを与え、0.5秒毎にスタックを付与する。スタックが3溜まった敵ユニットにはスタン(1.5s)を与える。")
                .mana(65)
                .cd(17, -1)
                .range(625);
        DeathRay.update()
                .active("指定地点から指定方向にビームを発射し、ビームが通過する線上の敵ユニットに魔法DMを与える。また、ビームが通過した地点の視界を得る。")
                .mana(70, 10)
                .cd(13, -1)
                .range(700);
        ChaosStorm.update()
                .active("指定地点に7秒間持続する特異点を呼び出し、範囲内の敵ユニットに魔法DMとサイレンス(0.5s)を与える。特異点は周囲の敵ユニットに毎秒魔法DMを与え、また近くにいる敵Championを自動的に追尾する。このスキルがActiveの間に再度地点を指定することで、特異点を指定した地点に手動で移動させる事が出来る。")
                .mana(125, 50)
                .cd(120)
                .range(700);

        /** Vladimir */
        CrimsonPact.update()
                .passive(BounusHealth + "に比例して{1}を、" + AP + "に比例して{2}を得る。")
                .variable(1, AP, 0, 0, amplify(BounusHealth, 0.025))
                .variable(2, Health, 0, 0, ap(1.4));
        Transfusion.update()
                .active("対象の敵ユニットに{1}を与え、{2}する。")
                .variable(1, MagicDamage, 90, 35, ap(0.6))
                .variable(2, RestoreHealth, 15, 10, ap(0.25))
                .cd(10, -1.5)
                .range(600);
        SanguinePool.update()
                .active("Vladimirが2秒間血の海に沈む。その間はターゲットされなくなり、{1}内にいる敵ユニットに0.5秒毎に{2}と{3}間の{4}を与え続ける。また、与えたダメージの12.5%Healthが回復する。")
                .variable(1, Radius, 300)
                .variable(2, MagicDamage, 20, 13.75, amplify(BounusHealth, 0.00375))
                .variable(3, Time, 1)
                .variable(4, MSSlowRatio, 40)
                .cost(CurrentHealthRatio, 20, 0)
                .cd(26, -3);
        TidesOfBlood.update()
                .active("{1}の敵ユニットに{2}を与える。使用する度にスタックが増加し、1スタックにつきこのスキルの基礎魔法DMとHPコストが25%増加し、{4}と{3}を得るく(最大4スタック)。スタックは10秒増加が無いと0になる。このスキルは周囲に敵ユニットがいなくても使用可能。")
                .variable(1, Radius, 0)
                .variable(2, MagicDamage, 60, 25, ap(0.45))
                .variable(3, HregRatio, 4, 1)
                .variable(4, RestoreHealthRatio, 4, 1)
                .cost(Health, 30, 10)
                .cd(4.5)
                .range(620);
        Hemoplague.update()
                .active("指定地点の{1}の敵ユニットに疫病を付与し、その敵ユニットは5秒間受けるダメージが12%増加する。効果終了時に{2}を与える。ダメージ増加効果のため実際には{3}を与える。")
                .variable(1, Radius, 300)
                .variable(2, MagicDamage, 150, 100, ap(0.7))
                .variable(3, MagicDamage, 168, 112, ap(0.784))
                .cd(150, -15)
                .range(700);

        /** Volibear */
        ChosenOftheStorm.update()
                .passive("VolibearのHPが30%以下になったとき、6秒間かけて{1}する。")
                .variable(1, RestoreHealth, 0, 0, amplify(Health, 0.3))
                .cd(120);
        RollingThunder.update()
                .active("4秒間{1}する。敵Championに向かって移動する場合は{2}する。また次の通常攻撃に追加{3}を付与し、対象をVolibearの後ろに投げ飛ばす。4秒間攻撃を行わないとCDになる。")
                .variable(1, MSRatio, 15)
                .variable(2, MSRatio, 45)
                .variable(3, PhysicalDamage, 30, 30)
                .conditional(2)
                .mana(40)
                .cd(12, -1);
        Frenzy.update()
                .passive("通常攻撃でダメージを与える度にスタックが1増加し(最大3スタック)、{1}する。スタックは4秒持続する。")
                .variable(1, ASRatio, 0, 0, amplify(Stack, 8, 3))
                .conditional(1)
                .active("スタックが最大まで溜まった時のみ使用可能。対象の敵ユニットに{2}を与える。対象が失っているHP1%につきダメージが1%上昇する。")
                .variable(2, PhysicalDamage, 80, 45, amplify(BounusHealth, 0.15))
                .mana(35)
                .cd(17)
                .range(400);
        MajesticRoar.update()
                .active("{1}の敵ユニットに{2}と3秒間{3}を与える。対象がMinionの場合、さらに{4}を与える。")
                .variable(1, Radius, 425)
                .variable(2, MagicDamage, 60, 45, ap(0.6))
                .variable(3, MSSlowRatio, 30, 5)
                .variable(4, Fear, 3)
                .mana(60, 5)
                .cd(11);
        ThunderClaws.update()
                .active("12秒間Volibearが通常攻撃した対象に雷を放ち{1}を与える。雷は対象の{2}の敵ユニット(敵Championを優先)3体にも連鎖し同様のダメージを与える。建物を攻撃する時は効果は発生しない。")
                .variable(1, MagicDamage, 75, 40, ap(0.3))
                .variable(2, Radius, 300)
                .mana(100)
                .cd(100, -10);

        /** Warwick */
        EternalThirst.update()
                .passive("通常攻撃で対象にスタックを付与し、追加{1}を与え{2}する。スタックは4秒持続し、最大3つまでスタックされる。建物を攻撃した場合は無効。")
                .variable(1, MagicDamage, 0, 0, amplify(Stack, new Per1Level(new double[] {3, 3.5, 4, 4.5, 5, 5.5, 6,
                        6.5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16})))
                .variable(2, RestoreHealth, 0, 0, amplify(Stack, new Per1Level(new double[] {3, 3.5, 4, 4.5, 5, 5.5, 6,
                        6.5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16})));
        HungeringStrike.update()
                .active("対象の敵ユニットに{1}を与え、{2}する。対象がChampionの場合は{3}と比較し大きいほうのDMを与える。")
                .variable(1, MagicDamage, 75, 50, ap(1))
                .variable(2, RestoreHealth, 0, 0, amplify(DealtDamageRatio, 80))
                .variable(3, MagicDamage, 0, 0, ap(1), amplify(TargetMaxHealthRatio, 8, 2))
                .mana(70, 10)
                .cd(10, -1)
                .range(400);
        HuntersCall.update()
                .active("10秒間{2}し、{1}の味方Championは{3}する。")
                .variable(1, Radius, 1200)
                .variable(2, ASRatio, 40, 10)
                .variable(3, ASRatio, 20, 5)
                .conditional(3)
                .mana(35)
                .cd(24, -2);
        BloodScent.update()
                .passive("{1}して、{2}内にいるHPが50%以下の敵Championの{3}。このスキルで敵のステルスを看破する事はできず、ステルス中の敵Championの視界を得ることもできない。")
                .variable(1, MSRatio, 20, 5)
                .variable(2, Radius, 1500, 800)
                .variable(3, Visionable)
                .cd(4);
        InfiniteDuress.update()
                .active("対象の敵Championに突撃し{2}を与えて、その間{3}を得て0.36秒毎に{1}を、計5回で{4}を与える。")
                .variable(1, MagicDamage, 50, 17, bounusAD(0.4))
                .variable(2, Suppression, 1.8)
                .variable(3, LS, 30)
                .variable(4, MagicDamage, 250, 85, bounusAD(2))
                .type(SkillType.Channel)
                .type(SkillType.OnHitEffectable)
                .mana(100, 25)
                .cd(90, -10)
                .range(700);

        /** Wukong */
        StoneSkin.update()
                .passive("Wukongの視界内(範囲1400)にいる敵Championの数に比例して、WukongのArmorとMagic Resistが増加する。レベル1、7、13で増加値が上昇する。");
        CrushingBlow.update()
                .active("次の通常攻撃の射程とダメージが増加し、対象のArmorを30%低下させる効果が付与される。Armor低下は3秒間持続する。")
                .mana(40)
                .cd(9, -1)
                .range(300);
        Decoy.update()
                .active("Wukongが1.5秒間ステルス状態になり、その間ユニットを通過できるようになる。同時にWukongがいた場所に分身(操作不可能)を作り出す。分身は1.5秒経過すると消滅し、その際に分身の周囲の敵に魔法DMを与える。")
                .mana(50, 5)
                .cd(18, -2);
        NimbusStrike.update()
                .active("対象の敵ユニットまでダッシュし物理DMを与える。対象の敵ユニットの近くにいる敵ユニット2体にもWukongの幻影が飛び、同様のダメージを与える。また、スキル使用後4秒間攻撃速度が上昇する。")
                .mana(45, 5)
                .cd(8)
                .range(625);
        Cyclone.update()
                .active("4秒間Wukongが回転する。回転中は近くにいる敵ユニットに0.5秒毎に物理DMと打ち上げ(1.5s)を与える(最大8hit)。打ち上げ効果は同一の対象に1度までしか発生しない。また、このスキルを使用してから0.5秒毎にWukongの移動速度が5%ずつ上昇していく(最大40%)。")
                .mana(100)
                .cd(120, -15);

        /** Xerath */
        AscendedForm.update().passive("APの15%分、ARが上昇する。");
        Arcanopulse.update()
                .active("0.75秒詠唱後指定方向にビームを放ち、直線状の敵ユニットすべてに魔法DMを与える。")
                .mana(65, 5)
                .cd(7, -0.5)
                .range(900, 400);
        LocusOfPower.update()
                .active("0.5秒詠唱後に移動が不可能になる代わりに、全てのスキルの射程が400増加し、Magic Penetrationが増加する。この効果は8秒経過するか、再度このスキルを使用する事で解除される。このスキルが解除された時に自身の移動速度が2秒間35%増加する。")
                .cd(20, -4);
        MageChains.update()
                .active("対象の敵ユニットに魔法DMとマーク(3s)を与える。マークがついている敵ユニットにXerathのスキルが命中した場合、マークを消費して対象にスタン(1.5s)を与える。")
                .mana(70, 5)
                .cd(12, -1)
                .range(600, 400);
        ArcaneBarrage.update()
                .active("地点を指定して0.5秒後に範囲内の敵ユニットに魔法DMを与える。このスキルは12秒の間、3回まで連続して使用できる(但し、一度使用する度に0.35秒のCDが発生する)。2〜3発目はマナコスト無しで使用可能。また、指定地点の視界を得る。")
                .mana(150, 30)
                .cd(80, -10)
                .range(900, 400);

        /** Xin Zhao */
        Challenge.update()
                .passive("通常攻撃または" + AudaciousCharge + "で指定した敵ユニットに{1}を与える。この効果はスタックせず、3秒間持続し、また1体の敵ユニットにしか発動しない。")
                .variable(1, ARReductionRatio, 15);
        ThreeTalonStrike.update()
                .active("次の3回の通常攻撃に{1}が追加され、3回目の攻撃で{2}を与える。効果中に通常攻撃を行う度に、このスキル以外のCDが1秒解消される。")
                .variable(1, PhysicalDamage, 15, 15, ad(0.2))
                .variable(2, Knockup, 1)
                .mana(30)
                .cd(9, -1);
        BattleCry.update()
                .passive("通常攻撃3回ごとに{1}する。")
                .variable(1, RestoreHealth, 26, 6, ap(0.7))
                .active("5秒間{2}増加する。")
                .variable(2, ASRatio, 40, 10)
                .mana(40)
                .cd(16, -1);
        AudaciousCharge.update()
                .active("対象の敵ユニットに突進し、{1}の敵ユニットに{2}と2秒間{3}を与える。")
                .variable(1, Radius, 225)
                .variable(2, MagicDamage, 70, 40, ap(0.6))
                .variable(3, MSSlowRatio, 25, 5)
                .mana(70)
                .cd(13, -1)
                .range(600);
        CrescentSweep.update()
                .active("槍を振り回し{3}の敵ユニットに{1}と{2}を与え、このスキルを命中させた敵Championの数に比例して6秒間{4}と{5}を得る。" + Challenge + "効果中の敵ユニットに対しては" + Knockback + "は発動しない。")
                .variable(1, PhysicalDamage, 125, 100, bounusAD(1), amplify(TargetCurrentHealthRatio, 0.15))
                .variable(2, Knockback, 0)
                .variable(3, Radius, 375)
                .variable(4, AR, 15, 5)
                .variable(5, MR, 15, 5)
                .mana(100)
                .cd(100, -10);

        /** Yorick */
        UnholyCovenant.update()
                .passive("(召喚中のGhoulsの数×5)%の被ダメージ軽減および通常攻撃のダメージ増加効果を得る。召喚したGhoulは5秒間持続し、また毎秒最大HPの20%が減少していく。同じ種類のGhoulを召喚した場合、先に召喚したGhoulが消滅する。ペット「Ghouls」HP: [YorickのHP × 35%] 攻撃力: [Yorickの攻撃力 × 35%]AR: 10 + (2 × Lv) MR: 20 + (2 × Lv)AS: 0.670 MS: 300/340/410/433 (レベル1、6、9、12で移動速度が上昇する。)【備考】任意の操作不可、スロー無効化、AoEスキルのダメージを50%低減。Ghoulsは敵ユニットの通行を妨げない（引っかからずにすり抜ける）。");
        OmenOfWar.update()
                .active("次の通常攻撃時のダメージが増加し、通常攻撃時と同時にSpectral Ghoulを召喚する。Spectral GhoulはYorickの他のGhoulと比べて攻撃力が高く、移動速度が速い。Spectral Ghoulが生存している間、Yorick自身の移動速度も上昇する。")
                .mana(40)
                .cd(9, -1);
        OmenOfPestilence.update()
                .active("指定範囲を爆発させ範囲内の敵ユニットに魔法DMとスロー(1.5s)を与え、同時にその地点にDecaying Ghoulを召喚する。Decaying Ghoulは近くの敵ユニットに継続的にスローを与える。")
                .mana(55, 5)
                .cd(12)
                .range(600);
        OmenOfFamine.update()
                .active("対象の敵ユニットに魔法DMを与え、与えたダメージの40%を回復し、対象の背後にRavenous Ghoulを召喚する。Ravenous Ghoulは、通常攻撃を行うたびにYorickのHPを回復する。回復量はRavenous Ghoulの攻撃力の半分となり、敵Championを攻撃した場合は回復量が2倍になる。")
                .mana(55, 5)
                .cd(10, -1)
                .range(550);
        OmenOfDeath.update()
                .active("対象の味方Champion一人の姿形を持ったRevenant(死霊)を召喚する（RまたはALT押しながら右クリックで任意の操作可能）。Revenantは10秒間持続し、対象の味方Championの一定割合の攻撃力を持つ。Revenantが生存している間に対象となった味方Championが死亡した場合、Revenantが消滅し、死亡した味方ChampionはHPとMNが最大の状態で蘇生される。蘇生した味方Championは10秒経過すると消滅する。消費MN: 100 CD: 120/105/90s Range: 900ペット「Revenant」攻撃力: 元になったChampの45/60/75%【備考】元になったChampionのステータス・一部アイテムとスキルの効果を引き継ぐ。")
                .mana(100)
                .cd(120, -15)
                .range(900);

        /** Zed */
        ContemptforTheWeak.update()
                .passive("HPが50%以下の敵ユニットへの通常攻撃に対象の最大HPに比例した追加魔法DMを付与する。同一の対象には10秒に一度しか発動しない。レベル1、7、17でDMが上昇する。");
        RazorShuriken.update()
                .active("Zedと「影」から指定方向に貫通する手裏剣を飛ばし、当たった敵ユニットに物理DMを与える。手裏剣は一度敵ユニットに当たるとそれ以降の敵ユニットには80%のDMを与える。物理DM: 75/110/145/180/215 + [増加攻撃力 × 100%]Living Shadow:Zedと「影」が同一の対象に手裏剣を命中させた場合、「影」が与えるDMは半分になり「気」が回復する。")
                .cd(6)
                .range(900);
        LivingShadow.update()
                .passive("{1}を得る。")
                .variable(1, AD, 0, 0, amplify(BounusAD, 0.05, 0.05))
                .active("Zedの「影」が指定方向にダッシュし、4秒間その場に留まる。再度このスキルを使用するとZedと「影」の位置が入れ替わる。「影」はZedが通常スキルを使用すると同時に同じスキルを使用する。この時スキルがZedのスキルと同一の敵ユニットに命中した場合、「気」が回復する。回復効果はスキル1回毎に1度のみ発動する。")
                .cd(22, -1.5)
                .range(550);
        ShadowSlash.update()
                .active("Zedと「影」から衝撃波を発生させ、周囲の敵ユニットに物理DMを与える。ZedのShadow Slashは敵ユニットに当たる度にLiving ShadowのCDを1秒解消させる。「影」のShadow Slashはダメージと共に周囲の敵ユニットにスロー(1.5s)を与える。物理DM: 60/90/120/150/180 + [増加攻撃力 × 90%]スロー: 20/25/30/35/40%Living Shadow:Zedと「影」が同一の対象にShadow Slashを命中させた場合、DMは重複しないがスローの効果が上昇し、「気」が回復する。")
                .cd(3)
                .range(290);
        DeathMark.update()
                .active("対象の敵Championにダッシュしマークを付与する。ダッシュ中はターゲット不可状態になる。また対象の背後に4秒間持続する「影」を召喚する。再度このスキルを使用するとZedと「影」の位置が入れ替わる。付与から3秒後にマークは消費され、対象にマークが付与されている間にZedと「影」が与えた物理DMと魔法DMの合計に比例した物理DMを与える。")
                .cd(120, -20)
                .range(625);

        /** Ziggs */
        ShortFuse.update().passive("12秒毎に通常攻撃に追加魔法DMが付与される。Ziggsがスキルを使う度にCDが4秒低減される。建物に対しては150%のDMを与える。").cd(12);
        BouncingBomb.update()
                .active("指定地点に跳ねながら転がる爆弾を投げ、爆発時に周囲の敵ユニットに魔法DMを与える。敵に当たらなかった場合には投げた方向に2回までバウンドする。")
                .mana(50, 10)
                .cd(6, -0.5)
                .range(850);
        SatchelCharge.update()
                .active("指定地点に火薬を投げ、爆発時に周囲の敵ユニットに魔法DMを与え、ノックバックさせる。Ziggsが範囲内にいた場合は自分もノックバックを受ける(Ziggsにダメージは無し)。火薬は4秒経つか、スキルを再度使用すると爆発する。また火薬は視界を確保する。")
                .mana(65)
                .cd(26, -2)
                .range(1000);
        HexplosiveMinefield.update()
                .active("指定範囲に11個の近接地雷を円形にばら撒き、敵ユニットが地雷に触れると魔法DMとスロー(1.5s)を与える。同ユニットが2個目以降に踏む地雷のダメージは本来の40%となる。地雷は爆発するか10秒経つと消滅する。")
                .mana(70, 10)
                .cd(16)
                .range(900);
        MegaInfernoBomb.update()
                .active("指定地点に巨大な爆弾を投下し、範囲内の敵ユニットに魔法DMを与える。爆発の中心点から離れた位置にいる敵ユニットには80%のDMを与える。")
                .mana(100)
                .cd(120, -15)
                .range(5300);

        /** Zilean */
        HeightenedLearning.update().passive("すべての味方Championが取得する{1}する。").variable(1, ExperimentRatio, 8);
        TimeBomb.update()
                .active("対象のユニットに爆弾をつけ、4秒後に対象(味方ユニットの場合ダメージ無し)とその周辺{1}の敵ユニットに{2}を与える。対象が死亡した場合は即座に爆発する。")
                .variable(1, Radius, 330)
                .variable(2, MagicDamage, 90, 55, ap(0.9))
                .mana(70, 15)
                .cd(10)
                .range(700);
        Rewind.update().active(Rewind + "以外のスキルのCDを10秒解消する。").mana(50).cd(18, -3);
        TimeWarp.update()
                .active("味方Championに使用した場合は{2}間{1}増加し、敵Championに使用した場合は{2}間{3}を与える。")
                .variable(1, MSRatio, 55)
                .variable(2, Time, 2.5, 0.75)
                .variable(3, MSSlowRatio, 55)
                .mana(80)
                .cd(20)
                .range(700);
        ChronoShift.update()
                .active("対象の味方Championが使用してから7秒以内に死亡した場合、2秒後にその場で{1}して復活する。")
                .variable(1, RestoreHealth, 600, 150, ap(2))
                .mana(125, 25)
                .cd(180)
                .range(900);

        /** Zyra */
        RiseOftheThorns.update()
                .passive("死亡すると2秒後にその場で植物に変形し、指定方向に一度だけ貫通する光線を放つことができる。光線に当たった敵ユニットに{1}を与える。")
                .variable(1, TrueDamage, 80, 0, amplify(Lv, 20))
                .range(1500);
        DeadlyBloom.update()
                .active("地面から棘を出現させ、指定範囲内の敵ユニットに{1}を与える。種にHitした場合Thorn Spitterに成長し、{3}の敵を自動攻撃して{2}を与える。Thorn Spitterは10秒間持続する。")
                .variable(1, MagicDamage, 75, 40, ap(0.6))
                .variable(2, MagicDamage, 26, 0, amplify(Lv, 6), ap(0.6))
                .variable(3, Radius, 650)
                .mana(75, 5)
                .cd(7, -0.5)
                .range(825);
        RampantGrowth.update()
                .passive("{1}を得る。")
                .variable(1, CDR, 4, 4)
                .active("指定地点に30秒持続する種を植える。他のスキルを種に当てることで成長させることができる。敵Championが種を踏んだ場合、視界を2秒間得る。{2}毎にチャージが1つ増加し最大2つまでチャージされる。種が植物に成長し、同一ユニットに複数の植物がDMを与える場合、2体目以降は50%のダメージを与える。")
                .variable(2, CDRAwareTime, 17, -1)
                .range(825)
                .cost(Charge, 1, 0);
        GraspingRoots.update()
                .active("指定方向に蔓を放ち、当たった全ての敵ユニットに{1}と{2}を与える。種にHitした場合Vine Lasherに成長し、{3}の敵を自動攻撃して{4}と2秒間{5}を与える。Vine Lasherは10秒間持続する。")
                .variable(1, MagicDamage, 60, 35, ap(0.5))
                .variable(2, Snare, 0.75, 0.25)
                .variable(3, Radius, 250)
                .variable(4, MagicDamage, 26, 0, amplify(Lv, 6), ap(0.2))
                .variable(5, MSSlowRatio, 30)
                .mana(70, 5)
                .cd(14, -1)
                .range(1100);
        Stranglethorns.update()
                .active("指定地点に藪を召還し、{1}の全ての敵に{2}を与え、2秒後に{3}。成長した植物にHitした場合、その植物の攻撃速度が50%増加する。")
                .variable(1, Radius, 700)
                .variable(2, MagicDamage, 180, 85, ap(0.75))
                .variable(3, Knockup, 0)
                .mana(100, 20)
                .cd(130, -10)
                .range(700);

    }
}
