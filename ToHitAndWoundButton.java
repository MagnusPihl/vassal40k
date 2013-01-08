public class ToHitAndWoundButton extends BaseButton
{
    private int numberOfAttacks = 1;
    private int toHit = 4;
    private boolean bsHigherThan5 = false;
    private int toHitOnRerollFromBS = 6;
    private boolean rerollOneMissedHit = false;
    private boolean rerollToHitOf1 = false;
    private boolean rerollAllMissedHits = false;
    private int toWound = 4;
    private boolean precisionShots = false;
    private boolean rending = false;   //Always wounds on a 6 resolved at AP2
    
    public ToHitAndWoundButton()
    {
        Initialize("To Hit/Wound", "To Hit/Wound", "To Hit/Wound", true);
        AddAttribute("numberOfAttacks", "Number of Attacks ", Integer.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(numberOfAttacks); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Integer))
                    numberOfAttacks = ((Integer)o).intValue();
                else if ((o instanceof String))
                    numberOfAttacks = Integer.parseInt((String)o);
            }
        });
        AddAttribute("toHit", "To Hit ", Integer.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(toHit); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Integer))
                    toHit = ((Integer)o).intValue();
                else if ((o instanceof String))
                    toHit = Integer.parseInt((String)o);
            }
        });
        AddAttribute("bsHigherThan5", "BS is 6+ ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(bsHigherThan5); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    bsHigherThan5 = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    bsHigherThan5 = o.equals("true");
            }
        });
        AddAttribute("toHitOnRerollFromBS", "To Hit on re-roll due to 6+ BS ", Integer.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(toHitOnRerollFromBS); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Integer))
                    toHitOnRerollFromBS = ((Integer)o).intValue();
                else if ((o instanceof String))
                    toHitOnRerollFromBS = Integer.parseInt((String)o);
            }
        });
        AddAttribute("rerollOneMissedHit", "Re-roll one missed hit ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rerollOneMissedHit); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rerollOneMissedHit = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rerollOneMissedHit = o.equals("true");
            }
        });
        AddAttribute("rerollToHitOf1", "Re-roll To Hit rolls of 1 ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rerollToHitOf1); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rerollToHitOf1 = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rerollToHitOf1 = o.equals("true");
            }
        });
        AddAttribute("rerollAllMissedHits", "Re-roll all missed hits ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rerollAllMissedHits); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rerollAllMissedHits = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rerollAllMissedHits = o.equals("true");
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
        
        String intro = "<" + Chatbox.GetPlayerName() + "> rolls To-Hit for " + numberOfAttacks + " attack";
        if (numberOfAttacks > 1)
            intro += " s";
        intro += ":";
        Chatbox.WriteLine(intro);
        
        int hits = RollToHit(numberOfAttacks);
        
        intro = "<" + Chatbox.GetPlayerName() + "> rolls To-Wound for " + hits + " hit";
        if (hits > 1)
            intro += " s";
        intro += ":";
        Chatbox.WriteLine(intro);
        
        int wounds = RollToWound(hits);
    }
    
    //Returns number of hits
    protected int RollToHit(int attacks)
    {
        int[] rolls = DiceRoll(6, attacks);
        String out = "* To-Hit needing " + toHit + "+ (" + rolls[0];
        for (int i = 1; i < rolls.length; i++)
            out += ", " + rolls[i];
        out += ")";
        
        int hits = NumberOfSuccesses(rolls, toHit);
        int misses = attacks - hits;
        
        if (misses > 0 && bsHigherThan5)
        {
            int[] rerolls = DiceRoll(6, misses);
            out += ", re-rolling due to 6+ BS needing " + toHitOnRerollFromBS + "+ (" + rerolls[0];
            for (int i = 1; i < rerolls.length; i++)
                out += ", " + rerolls[i];
            out += ")";
            hits += NumberOfSuccesses(rerolls, toHitOnRerollFromBS);
            misses = attacks - hits;
        }
        
        if (misses > 0 && rerollAllMissedHits)
        {
            int[] rerolls = DiceRoll(6, misses);
            out += ", re-rolling " + misses + " misses needing " + toHit + "+ (" + rerolls[0];
            for (int i = 1; i < rerolls.length; i++)
                out += ", " + rerolls[i];
            out += ")";
            hits += NumberOfSuccesses(rerolls, toHit);
            misses = attacks - hits;
        }
        else if (misses > 0)
        {
            if (rerollToHitOf1 && NumberOfFailures(rolls, 2) > 0)
            {
                int ones = NumberOfFailures(rolls, 2);
                int[] rerolls = DiceRoll(6, ones);
                out += ", re-rolling " + ones + " 1s needing " + toHit + "+ (" + rerolls[0];
                for (int i = 1; i < rerolls.length; i++)
                    out += ", " + rerolls[i];
                out += ")";
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
        return 0;
    }
}