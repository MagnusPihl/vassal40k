package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;

public class DifficultTerrainButton extends BaseButton
{
    private boolean moveThroughCover = false;
    
    public DifficultTerrainButton()
    {
        Initialize("Difficult Terrain", "Difficult Terrain", "Difficult Terrain", true);
        AddAttribute("moveThroughCover", "Move Through Cover ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(moveThroughCover); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    moveThroughCover = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    moveThroughCover = o.equals("true");
            }
        });
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Difficult Terrain:");
        if (moveThroughCover)
        {
            int[] tests = DiceRoll(6, 3);
            int distance = tests[tests.length - 1]; //Beware of dice sorting!
            Chatbox.WriteLine("* Difficult Terrain with Move Through Cover (" + CommaSeparateIntegers(tests) + "): Can move " + distance + " " + Pluralize("inch", distance));
        }
        else
        {
            int[] tests = DiceRoll(6, 2);
            int distance = tests[tests.length - 1]; //Beware of dice sorting!
            Chatbox.WriteLine("* Difficult Terrain (" + CommaSeparateIntegers(tests) + "): Can move " + distance + " " + Pluralize("inch", distance));
        }
    }   
}