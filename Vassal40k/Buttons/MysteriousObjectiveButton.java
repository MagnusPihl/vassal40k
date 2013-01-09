package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;

public class MysteriousObjectiveButton extends BaseButton
{
    public MysteriousObjectiveButton()
    {
        Initialize("Mysterious Objective", "Mysterious Objective", "Mysterious Objective", false);
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Mysterious Objective:");
        int roll = DiceRoll(6);
        if (roll == 1)
            Chatbox.WriteLine("* (" + roll + "): Sabotaged (at the end of this and every following round, roll a D6; on a 1, the objective explodes as a Large Blast with S4 AP-; objective is not destroyed and can continue to explode every round)");
        else if (roll == 2)
            Chatbox.WriteLine("* (" + roll + "): Nothing of Note (no additional effect)");
        else if (roll == 3)
            Chatbox.WriteLine("* (" + roll + "): Skyfire Nexus (controlling unit can choose whether or not to have the Skyfire special rule whenevery they shoot)");
        else if (roll == 4)
            Chatbox.WriteLine("* (" + roll + "): Targeting Relay (controlling unit re-rolls failed To-Hit rolls of 1 when shooting)");
        else if (roll == 5)
            Chatbox.WriteLine("* (" + roll + "): Scatterfield (controlling unit improves Cover Saves by 1, cumulative with Stealth and Shrouded special rules)");
        else if (roll == 6)
            Chatbox.WriteLine("* (" + roll + "): Grav Wave Generator (anyone attempting to Charge controlling unit must halve their Charge range)");
    }    
}