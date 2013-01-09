package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;

public class DifficultTerrainButton extends BaseButton
{
    //Fleet, Move Through Cover, etc.
    
    public DifficultTerrainButton()
    {
        Initialize("Difficult Terrain", "Difficult Terrain", "Difficult Terrain", false);
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Difficult Terrain:");
        int[] tests = DiceRoll(6, 2);
        int distance = tests[tests.length - 1]; //Beware of dice sorting!
        Chatbox.WriteLine("* Difficult Terrain (" + CommaSeparateIntegers(tests) + "): Can move " + distance + " " + Pluralize("inch", distance));
    }   
}