import VASSAL.build.GameModule;
import VASSAL.build.module.Chatter;
import VASSAL.command.Command;

public class ChaosBoonButton extends BaseButton
{
    protected boolean rerollApotheosisSpawnhood = false;

    public ChaosBoonButton()
    {
        Initialize("Chaos Boons", "Chaos Boons", "Chaos Boon", false);
        /*AddAttribute("rerollApotheosisSpawnhood", "Re-roll Apotheosis/Spawnhood? ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rerollApotheosisSpawnhood); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rerollApotheosisSpawnhood = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rerollApotheosisSpawnhood = o.equals("true");
            }

        });*/
    }

    @Override
    protected void OnClick ()
    {
        GameModule mod = GameModule.getGameModule();

        Command chatter = new Chatter.DisplayText(mod.getChatter(),
                "<" + mod.getPrefs().getValue("RealName") + "> rolls for Chaos Boons (p. 29):");

        RollChaosBoon(mod, chatter);

        chatter.execute();
        mod.sendAndLog(chatter);
    }

    protected void RollChaosBoon(GameModule mod, Command chatter)
    {
        int roll = DiceRoll(6)*10 + DiceRoll(6);
        if (roll >= 11 && roll <= 16)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Unworthy Offering: Nothing happens."));
        else if (roll >= 21 && roll <= 22 && !rerollApotheosisSpawnhood)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Spawnhood"));
        else if (roll == 23)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Warp Frenzy: +1 Attack"));
        else if (roll == 24)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Fragment of Immortality: Gain Eternal Warrior"));
        else if (roll == 25)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Strength of the Berzerker: +1 Strength"));
        else if (roll == 26)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Arcane Occulum: +1 Ballistic Skill"));
        else if (roll == 31)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Cerebral Cogitator: +1 Initiative"));
        else if (roll == 32)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Bloated: Returned to full Wounds, or +1 Wound if at maximum"));
        else if (roll == 33)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Crystalline Body: +1 Toughness"));
        else if (roll == 34)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Shield of Force: Gain Shrouded"));
        else if (roll == 35)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Mechanoid: Armour Save improves by 1"));
        else if (roll == 36)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Blade of Chaos: One melee weapon gains Fleshbane"));
        else if (roll == 41)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Witch-eater: When champion or his unit pass a Deny the Witch roll, the enemy Psyker immediately takes a S6 AP2 hit"));
        else if (roll == 42)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Cosmic Fate: May re-roll failed Armour Saves"));
        else if (roll == 43)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Venomous: Melee attacks gain Poisoned"));
        else if (roll == 44)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Unholy Crusader: Gain Crusader"));
        else if (roll == 45)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Meteoric Charge: Gain Hammer of Wrath"));
        else if (roll == 46)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Icy Aura: Enemy models in base contact take an S4 AP5 hit at Initiative Step 1"));
        else if (roll == 51)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Mind of Metal: Gain Adamantium Will"));
        else if (roll == 52)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Gun Morph: One ranged weapon gains +1 Strength (applies to both in a Combi-Weapon)"));
        else if (roll == 53)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] All-consuming Hatred: Gain Hatred(everything)"));
        else if (roll == 54)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Warp Claws: Gain Shred"));
        else if (roll == 55)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Lifetaker: Melee attacks gain Instant Death"));
        else if (roll == 56)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Blademaster: +1 Weapon Skill"));
        else if (roll == 61)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Voice of Horus: Gain Stubborn"));
        else if (roll == 62)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Temporal Distortion: Gain Fleet"));
        else if (roll == 63)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Masochism: Gain Feel No Pain"));
        else if (roll >= 65 && roll <= 66 && !rerollApotheosisSpawnhood)
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Dark Apotheosis"));
        else if (roll >= 21 && roll <= 22 && rerollApotheosisSpawnhood)
        {
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Not accepting Spawnhood roll. Re-rolling:"));
            RollChaosBoon(mod, chatter);
        }
        else if (roll >= 65 && roll <= 66 && rerollApotheosisSpawnhood)
        {
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Not accepting Dark Apotheosis roll. Re-rolling:"));
            RollChaosBoon(mod, chatter);
        }
        else if (roll == 64)
        {
            int numBoons = DiceRoll(3)+1;
            chatter.append(new Chatter.DisplayText(mod.getChatter(), "* [" + roll + "] Multiple Boons: Gain D3+1 (=" + numBoons + ") boons, re-rolling Spawnhood and Dark Apotheosis."));
            boolean rerollApotheosisSpawnhoodBackup = rerollApotheosisSpawnhood;
            rerollApotheosisSpawnhood = true;
            for (int i = 0; i < numBoons; i++)
                RollChaosBoon(mod, chatter);
            rerollApotheosisSpawnhood = rerollApotheosisSpawnhoodBackup;
        }
    }
}