package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;

public class WarlordPersonalButton extends BaseButton
{
    public WarlordPersonalButton()
    {
        Initialize("Warlord Traits - Personal", "Personal", "Warlord Traits - Personal", false);
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Personal Warlord Traits:");
        int roll = DiceRoll(6);
        if (roll == 1)
            Chatbox.WriteLine("* (1) = Master of Defence: Your Warlord, and his unit, have the  Counter-attack special rule while they are in their own deployment zone.");
        else if (roll == 2)
            Chatbox.WriteLine("* (2) = Master of Offence: Your Warlord, and his unit, have the Furious Charge special rule while they are in the enemy's deployment zone.");
        else if (roll == 3)
            Chatbox.WriteLine("* (3) = Master of Manoeuvre: Your Warlord, and the unit he joins during deployment, have the Outflank special rule.");
        else if (roll == 4)
            Chatbox.WriteLine("* (4) = Legendary Fighter: Your army gains 1 Victory Point for each enemy character slain by your Warlord in a challenge.");
        else if (roll == 5)
            Chatbox.WriteLine("* (5) = Tenacity: Your Warlord, and his unit, have the Feel No Pain special rule whilst within 3\" of an objective.");
        else if (roll == 6)
            Chatbox.WriteLine("* (6) = Immovable Object: Your Warlord is a scoring unit, even if he is a vehicle.");
    }
}