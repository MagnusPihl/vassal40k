package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;

public class MissionDeploymentButton extends BaseButton
{
    public MissionDeploymentButton()
    {
        Initialize("Mission & Deployment", "Mission & Deployment", "Mission & Deployment", false);
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Mission, Deployment and Night Fighting:");
        
        int mission = DiceRoll(6);
        int deployment = DiceRoll(3);
        int nightFighting = DiceRoll(6);
        
        if (mission == 1)
            Chatbox.WriteLine("* Mission = (" + mission + "): Crusade (p. 126)");
        else if (mission == 2)
            Chatbox.WriteLine("* Mission = (" + mission + "): Purge the Alien (p. 127)");
        else if (mission == 3)
            Chatbox.WriteLine("* Mission = (" + mission + "): Big Guns Never Tire (p. 128)");
        else if (mission == 4)
            Chatbox.WriteLine("* Mission = (" + mission + "): The Scouring (p. 129)");
        else if (mission == 5)
            Chatbox.WriteLine("* Mission = (" + mission + "): The Emperor's Will (p. 130)");
        else if (mission == 6)
            Chatbox.WriteLine("* Mission = (" + mission + "): The Relic (p. 131)");
        
        if (deployment == 1)
            Chatbox.WriteLine("* Deployment = (" + deployment + "): Dawn of War (p. 119)");
        else if (deployment == 2)
            Chatbox.WriteLine("* Deployment = (" + deployment + "): Hammer and Anvil (p. 119)");
        else if (deployment == 3)
            Chatbox.WriteLine("* Deployment = (" + deployment + "): Vanguard Strike (p. 119)");
        
        if (nightFighting >= 4)
            Chatbox.WriteLine("* Night Fighting = (" + nightFighting + "): Night Fighting is active");
        else
            Chatbox.WriteLine("* Night Fighting = (" + nightFighting + "): Night Fighting is not active - at the end of turn 5 and every turn after, Night Fighting comes into play on a 4+");
    }
}