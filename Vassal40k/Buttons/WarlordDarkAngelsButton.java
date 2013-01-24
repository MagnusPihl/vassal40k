package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;
public class WarlordDarkAngelsButton extends BaseButton
{
    public WarlordDarkAngelsButton()
    {
        Initialize("Warlord Traits - Dark Angels", "Dark Angels", "Warlord Traits - Dark Angels", false);
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Dark Angels Warlord Traits:");
        int roll = DiceRoll(6);
        if (roll == 1)
            Chatbox.WriteLine("* (1) = Rapid Manoeuvre: Your Warlord, and any unit he joins, can either roll 2 dice when they Run, using the highest roll, or can add D6\" to any Turbo-Boost they make (or Flat Out move, in the case of Master Sammael on his Land Speeder).");
        else if (roll == 2)
            Chatbox.WriteLine("* (2) = The Hunt: If your Warlord, or the unit he is with, slays the enemy Warlord in the Assault phase, you score an additional Victory Point.");
        else if (roll == 3)
            Chatbox.WriteLine("* (3) = Courage of the Lion: The Warlord, and all friendly units within 12\" of him, roll an additional dice when making Leadership tests, discarding the highest.");
        else if (roll == 4)
            Chatbox.WriteLine("* (4) = For the Lion!: The Warlord and his unit have the Furious Charge special rule.");
        else if (roll == 5)
            Chatbox.WriteLine("* (5) = Brilliant Planning: While the Warlord is alive, you can increase or decrease each of your Reserve rolls by 1 (choose after you roll the dice).");
        else if (roll == 6)
            Chatbox.WriteLine("* (6) = Hold At All Costs: The Warlord, and any Dark Angels unit he is in, has the Feel No Pain specual rule whilst they are within 3\" of an objective.");
    }
}