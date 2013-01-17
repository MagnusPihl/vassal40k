package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;

public class ToHitAndWoundButton extends ToHitBaseButton
{
    private int toWound = 4;
    private boolean rerollToWoundOf1 = false;
    private boolean rerollFailedToWound = false;
    private boolean precisionShots = false;
    private boolean rending = false;   //Always wounds on a 6 resolved at AP2
    
    public ToHitAndWoundButton()
    {
        Initialize("To Hit/Wound", "To Hit/Wound", "To Hit/Wound", true);
        AddAttribute("toWound", "To Wound ", Integer.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(toWound); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Integer))
                    toWound = ((Integer)o).intValue();
                else if ((o instanceof String))
                    toWound = Integer.parseInt((String)o);
            }
        });
        AddAttribute("rerollToWoundOf1", "Re-roll To-Wound rolls of 1 ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rerollToWoundOf1); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rerollToWoundOf1 = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rerollToWoundOf1 = o.equals("true");
            }
        });
        AddAttribute("rerollFailedToWound", "Re-roll failed To-Wound rolls ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rerollFailedToWound); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rerollFailedToWound = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rerollFailedToWound = o.equals("true");
            }
        });
        AddAttribute("precisionShots", "Precision Shots ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(precisionShots); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    precisionShots = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    precisionShots = o.equals("true");
            }
        });
        AddAttribute("rending", "Rending ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rending); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rending = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rending = o.equals("true");
            }
        });
    }

    @Override
    protected void OnClick ()
    {
        if (numberOfAttacks <= 0)
        {
            Chatbox.WriteLine("Number of Attacks must be 1 or more.");
            return;
        }
        
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls To-Hit for " + numberOfAttacks + " " + Pluralize("attack", numberOfAttacks) + ":");
        
        int hits = RollToHit(numberOfAttacks);

        if (hits > 0)
        {
            Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls To-Wound for " + hits + " " + Pluralize("hit", hits) + ":");
            RollToWound(hits);
        }
    }
    
    //Returns number of wounds
    protected int RollToWound(int hits)
    {
        int[] rolls = DiceRoll(6, hits);
        String out = "* To-Wound needing " + toWound + "+ (" + CommaSeparateIntegers(rolls) + ")";
        int successes = NumberOfSuccesses(rolls, toWound);
        int failures = hits - successes;
        int sixes = NumberOfSuccesses(rolls, 6);
        int ones = NumberOfFailures(rolls, 2);

        if (failures > 0)
        {
            int numRerolls = 0;
            if (rerollFailedToWound)
                numRerolls = failures;
            else if (rerollToWoundOf1)
                numRerolls = ones;
            
            if (numRerolls > 0)
            {
                int[] rerolls = DiceRoll(6, numRerolls);
                out += ", re-rolling " + numRerolls + " failures (" + CommaSeparateIntegers(rerolls) + ")";
                successes += NumberOfSuccesses(rerolls, toWound);
                failures = hits - successes;
                sixes += NumberOfSuccesses(rerolls, 6);
            }
        }
        
        out += " = " + successes + " Wounds";

        if (sixes > 0)
        {
            if (precisionShots && rending)
                out += ", " + sixes + " of which " + PluralizeIsAre(sixes) + " Rending (AP 2) and Precision Shots";
            else if (precisionShots)
                out += ", " + sixes + " of which " + PluralizeIsAre(sixes) + " Precision Shots";
            else if (rending)
                out += ", " + sixes + " of which " + PluralizeIsAre(sixes) + " Rending (AP 2)";
        }

        Chatbox.WriteLine(out);

        return successes;
    }
}