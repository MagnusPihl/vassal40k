public class ToHitAndWoundButton extends ToHitBaseButton
{
    private int toWound = 4;
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
    
    //Returns number of hits
    protected int RollToHit(int attacks)
    {
        int[] rolls = DiceRoll(6, attacks);
        String out = "* To-Hit needing " + toHit + "+ (" + CommaSeparateIntegers(rolls) + ")";
        
        int hits = NumberOfSuccesses(rolls, toHit);
        int misses = attacks - hits;
        
        if (misses > 0 && bsHigherThan5)
        {
            int[] rerolls = DiceRoll(6, misses);
            out += ", re-rolling " + misses + " misses due to 6+ BS needing " + toHitOnRerollFromBS + "+ (" + CommaSeparateIntegers(rerolls) + ")";
            hits += NumberOfSuccesses(rerolls, toHitOnRerollFromBS);
            misses = attacks - hits;
        }
        
        if (misses > 0 && rerollAllMissedHits)
        {
            int[] rerolls = DiceRoll(6, misses);
            out += ", re-rolling " + misses + " misses needing " + toHit + "+ (" + CommaSeparateIntegers(rerolls) + ")";
            hits += NumberOfSuccesses(rerolls, toHit);
            misses = attacks - hits;
        }
        else if (misses > 0)
        {
            if (rerollToHitOf1 && NumberOfFailures(rolls, 2) > 0)
            {
                int ones = NumberOfFailures(rolls, 2);
                int[] rerolls = DiceRoll(6, ones);
                out += ", re-rolling " + ones + " " + Pluralize("'1'", ones) + " needing " + toHit + "+ (" + CommaSeparateIntegers(rerolls) + ")";
                hits += NumberOfSuccesses(rerolls, toHit);
                misses = attacks - hits;
            }
            if (misses > 0 && rerollOneMissedHit)
            {
                int reroll = DiceRoll(6);
                out += ", re-rolling one missed hit needing " + toHit + "+ (" + reroll + ")";
                if (reroll >= toHit)
                {
                    hits++;
                    misses--;
                }
            }
        }
        
        if (hits == 1)
            out += " = " + hits + " hit";
        else
            out += " = " + hits + " hits";
        
        Chatbox.WriteLine(out);
        return hits;
    }
    
    //Returns number of wounds
    protected int RollToWound(int hits)
    {
        int[] rolls = DiceRoll(6, hits);
        String out = "* To-Wound needing " + toWound + "+ (" + CommaSeparateIntegers(rolls) + ")";
        int successes = NumberOfSuccesses(rolls, toWound);
        int failures = hits - successes;
        int sixes = NumberOfSuccesses(rolls, 6);

        if (failures > 0 && rerollFailedToWound)
        {
            int[] rerolls = DiceRoll(6, failures);
            out += ", re-rolling " + failures + " failures (" + CommaSeparateIntegers(rerolls) + ")";
            successes += NumberOfSuccesses(rerolls, toWound);
            failures = hits - successes;
            sixes += NumberOfSuccesses(rerolls, 6);
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