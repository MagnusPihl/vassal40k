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
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Chaos Boons (p. 29):");

        RollChaosBoon();
    }

    protected void RollChaosBoon()
    {
        int roll = DiceRoll(6)*10 + DiceRoll(6);
        if (roll >= 11 && roll <= 16)
            Chatbox.WriteLine("* [" + roll + "] Unworthy Offering: Nothing happens.");
        else if (roll >= 21 && roll <= 22 && !rerollApotheosisSpawnhood)
            Chatbox.WriteLine("* [" + roll + "] Spawnhood");
        else if (roll == 23)
            Chatbox.WriteLine("* [" + roll + "] Warp Frenzy: +1 Attack");
        else if (roll == 24)
            Chatbox.WriteLine("* [" + roll + "] Fragment of Immortality: Gain Eternal Warrior");
        else if (roll == 25)
            Chatbox.WriteLine("* [" + roll + "] Strength of the Berzerker: +1 Strength");
        else if (roll == 26)
            Chatbox.WriteLine("* [" + roll + "] Arcane Occulum: +1 Ballistic Skill");
        else if (roll == 31)
            Chatbox.WriteLine("* [" + roll + "] Cerebral Cogitator: +1 Initiative");
        else if (roll == 32)
            Chatbox.WriteLine("* [" + roll + "] Bloated: Returned to full Wounds, or +1 Wound if at maximum");
        else if (roll == 33)
            Chatbox.WriteLine("* [" + roll + "] Crystalline Body: +1 Toughness");
        else if (roll == 34)
            Chatbox.WriteLine("* [" + roll + "] Shield of Force: Gain Shrouded");
        else if (roll == 35)
            Chatbox.WriteLine("* [" + roll + "] Mechanoid: Armour Save improves by 1");
        else if (roll == 36)
            Chatbox.WriteLine("* [" + roll + "] Blade of Chaos: One melee weapon gains Fleshbane");
        else if (roll == 41)
            Chatbox.WriteLine("* [" + roll + "] Witch-eater: When champion or his unit pass a Deny the Witch roll, the enemy Psyker immediately takes a S6 AP2 hit");
        else if (roll == 42)
            Chatbox.WriteLine("* [" + roll + "] Cosmic Fate: May re-roll failed Armour Saves");
        else if (roll == 43)
            Chatbox.WriteLine("* [" + roll + "] Venomous: Melee attacks gain Poisoned");
        else if (roll == 44)
            Chatbox.WriteLine("* [" + roll + "] Unholy Crusader: Gain Crusader");
        else if (roll == 45)
            Chatbox.WriteLine("* [" + roll + "] Meteoric Charge: Gain Hammer of Wrath");
        else if (roll == 46)
            Chatbox.WriteLine("* [" + roll + "] Icy Aura: Enemy models in base contact take an S4 AP5 hit at Initiative Step 1");
        else if (roll == 51)
            Chatbox.WriteLine("* [" + roll + "] Mind of Metal: Gain Adamantium Will");
        else if (roll == 52)
            Chatbox.WriteLine("* [" + roll + "] Gun Morph: One ranged weapon gains +1 Strength (applies to both in a Combi-Weapon)");
        else if (roll == 53)
            Chatbox.WriteLine("* [" + roll + "] All-consuming Hatred: Gain Hatred(everything)");
        else if (roll == 54)
            Chatbox.WriteLine("* [" + roll + "] Warp Claws: Gain Shred");
        else if (roll == 55)
            Chatbox.WriteLine("* [" + roll + "] Lifetaker: Melee attacks gain Instant Death");
        else if (roll == 56)
            Chatbox.WriteLine("* [" + roll + "] Blademaster: +1 Weapon Skill");
        else if (roll == 61)
            Chatbox.WriteLine("* [" + roll + "] Voice of Horus: Gain Stubborn");
        else if (roll == 62)
            Chatbox.WriteLine("* [" + roll + "] Temporal Distortion: Gain Fleet");
        else if (roll == 63)
            Chatbox.WriteLine("* [" + roll + "] Masochism: Gain Feel No Pain");
        else if (roll >= 65 && roll <= 66 && !rerollApotheosisSpawnhood)
            Chatbox.WriteLine("* [" + roll + "] Dark Apotheosis");
        else if (roll >= 21 && roll <= 22 && rerollApotheosisSpawnhood)
        {
            Chatbox.WriteLine("* [" + roll + "] Not accepting Spawnhood roll. Re-rolling:");
            RollChaosBoon();
        }
        else if (roll >= 65 && roll <= 66 && rerollApotheosisSpawnhood)
        {
            Chatbox.WriteLine("* [" + roll + "] Not accepting Dark Apotheosis roll. Re-rolling:");
            RollChaosBoon();
        }
        else if (roll == 64)
        {
            int numBoons = DiceRoll(3)+1;
            Chatbox.WriteLine("* [" + roll + "] Multiple Boons: Gain D3+1 (=" + numBoons + ") boons, re-rolling Spawnhood and Dark Apotheosis.");
            boolean rerollApotheosisSpawnhoodBackup = rerollApotheosisSpawnhood;
            rerollApotheosisSpawnhood = true;
            for (int i = 0; i < numBoons; i++)
                RollChaosBoon();
            rerollApotheosisSpawnhood = rerollApotheosisSpawnhoodBackup;
        }
    }
}