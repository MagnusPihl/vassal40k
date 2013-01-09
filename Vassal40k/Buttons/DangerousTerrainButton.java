package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;

public class DangerousTerrainButton extends BaseButton
{
    protected int numberOfTests = 5;
    
    public DangerousTerrainButton()
    {
        Initialize("Dangerous Terrain", "Dangerous Terrain", "Dangerous Terrain", true);
        AddAttribute("numberOfTests", "Number of Tests ", Integer.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(numberOfTests); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Integer))
                    numberOfTests = ((Integer)o).intValue();
                else if ((o instanceof String))
                    numberOfTests = Integer.parseInt((String)o);
            }
        });
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls " + numberOfTests + " Dangerous Terrain " + Pluralize("test", numberOfTests) + ":");
        int[] tests = DiceRoll(6, numberOfTests);
        int failures = NumberOfFailures(tests, 2);
        Chatbox.WriteLine("* Dangerous Terrain (" + CommaSeparateIntegers(tests) + "): Failed " + failures + " " + Pluralize("test", failures));
    }   
}