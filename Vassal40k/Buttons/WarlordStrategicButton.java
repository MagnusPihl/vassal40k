package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;
public class WarlordStrategicButton extends BaseButton
{
    public WarlordStrategicButton()
    {
        Initialize("Warlord Traits - Strategic", "Strategic", "Warlord Traits - Strategic", false);
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Strategic Warlord Traits:");
        int roll = DiceRoll(6);
        if (roll == 1)
            Chatbox.WriteLine("* (1) = Conqueror of Cities: Your units have the Move Through Cover special rule if moving through Ruins, and the Stealth(Ruins) special rule.");
        else if (roll == 2)
            Chatbox.WriteLine("* (2) = Night Attacker: You can choose to use the Night Fighting rules in your game. If you do, there is no need to roll - it is Night for the first turn.");
        else if (roll == 3)
            Chatbox.WriteLine("* (3) = Master of Ambush: Treat all your Outflanking units as having the Acute Senses special rule whilst the warlord is alive.");
        else if (roll == 4)
            Chatbox.WriteLine("* (4) = Strategic Genius: Whilst your Warlord is alive, you can re-roll any Reserves rolls (failed or successful).");
        else if (roll == 5)
            Chatbox.WriteLine("* (5) = Divide to Conquer: Whilst your Warlord is alive, your opponent has a -1 modifier to Reserves rolls.");
        else if (roll == 6)
            Chatbox.WriteLine("* (6) = Princeps of Deceit: During deployment, before deploying Infiltrators and before Scouts redeploy, you can do one of the following: redeploy one of your units within 3D6\" of its current position, or redeploy 3 of your units, each within D6\" of their current positions (roll separately for each). These redeployments cannot take a unit out of its deployment zone.");
    }
}
