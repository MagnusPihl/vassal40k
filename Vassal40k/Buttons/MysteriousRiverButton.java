package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;

public class MysteriousRiverButton extends BaseButton
{
    public MysteriousRiverButton()
    {
        Initialize("Mysterious River", "Mysterious River", "Mysterious River", false);
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Mysterious River:");
        int roll = DiceRoll(6);
        if (roll == 1)
            Chatbox.WriteLine("* (" + roll + "): Calm Waters (nothing happens)");
        else if (roll == 2)
            Chatbox.WriteLine("* (" + roll + "): Daemon bile: (a model moving through loses D3 Warp Charges and cannot Deny the Witch until start of next turn)");
        else if (roll == 3)
            Chatbox.WriteLine("* (" + roll + "): Hyperslime (models gain Feel No Pain but must pass Leadership test to move or shoot - Falling Back requires no test)");
        else if (roll == 4)
            Chatbox.WriteLine("* (" + roll + "): Fireblood (Dangerous Terrain - a vehicle Immobilised in terrain is immediately Wrecked)");
        else if (roll == 5)
            Chatbox.WriteLine("* (" + roll + "): Industrial Ooze (models gain 5+ Cover Save, but if they Go To Ground they must pass a Toughness test or take a Wound with no Cover or Armour Saves allowed)");
        else if (roll == 6)
            Chatbox.WriteLine("* (" + roll + "): Iceblood (Dangerous Terrain - models that moved through or end in terrain may re-roll failed Armour Saves until start of next turn)");
    }    
}