public abstract class ToHitBaseButton extends BaseButton
{
    protected int numberOfAttacks = 1;
    protected int toHit = 4;
    protected boolean bsHigherThan5 = false;
    protected int toHitOnRerollFromBS = 6;
    protected boolean rerollOneMissedHit = false;
    protected boolean rerollToHitOf1 = false;
    protected boolean rerollAllMissedHits = false;
    protected boolean tesla = false;

    @Override
    protected void Initialize(String name, String text, String tooltip, boolean showDialog)
    {
        super.Initialize(name, text, tooltip, showDialog);

        AddAttribute("numberOfAttacks", "Number of Attacks ", Integer.class, new BaseButton.ButtonAttributeMethods()
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
        AddAttribute("toHit", "To Hit ", Integer.class, new BaseButton.ButtonAttributeMethods()
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
        AddAttribute("bsHigherThan5", "BS is 6+ ", Boolean.class, new BaseButton.ButtonAttributeMethods()
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
        AddAttribute("toHitOnRerollFromBS", "To Hit on re-roll due to 6+ BS ", Integer.class, new BaseButton.ButtonAttributeMethods()
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
        AddAttribute("rerollOneMissedHit", "Re-roll one missed hit ", Boolean.class, new BaseButton.ButtonAttributeMethods()
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
        AddAttribute("rerollToHitOf1", "Re-roll To Hit rolls of 1 ", Boolean.class, new BaseButton.ButtonAttributeMethods()
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
        AddAttribute("rerollAllMissedHits", "Re-roll all missed hits ", Boolean.class, new BaseButton.ButtonAttributeMethods()
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
        AddAttribute("tesla", "Tesla ", Boolean.class, new BaseButton.ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(tesla); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    tesla = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    tesla = o.equals("true");
            }
        });
    }

    @Override
    protected abstract void OnClick ();

    //Returns number of hits
    protected int RollToHit(int attacks)
    {
        int[] rolls = DiceRoll(6, attacks);
        int sixes = NumberOfSuccesses(rolls, 6);
        String out = "* To-Hit needing " + toHit + "+ (" + CommaSeparateIntegers(rolls) + ")";

        int hits = NumberOfSuccesses(rolls, toHit);
        int misses = attacks - hits;

        if (misses > 0 && bsHigherThan5)
        {
            int[] rerolls = DiceRoll(6, misses);
            out += ", re-rolling " + misses + " misses due to 6+ BS needing " + toHitOnRerollFromBS + "+ (" + CommaSeparateIntegers(rerolls) + ")";
            hits += NumberOfSuccesses(rerolls, toHitOnRerollFromBS);
            misses = attacks - hits;
            sixes += NumberOfSuccesses(rerolls, 6);
        }

        if (misses > 0 && rerollAllMissedHits)
        {
            int[] rerolls = DiceRoll(6, misses);
            out += ", re-rolling " + misses + " misses needing " + toHit + "+ (" + CommaSeparateIntegers(rerolls) + ")";
            hits += NumberOfSuccesses(rerolls, toHit);
            misses = attacks - hits;
            sixes += NumberOfSuccesses(rerolls, 6);
        }
        else if (misses > 0)
        {
            if (rerollToHitOf1 && NumberOfFailures(rolls, 2) > 0)
            {
                int ones = NumberOfFailures(rolls, 2);
                int[] rerolls = DiceRoll(6, ones);
                out += ", re-rolling " + ones + " 1s needing " + toHit + "+ (" + CommaSeparateIntegers(rerolls) + ")";
                hits += NumberOfSuccesses(rerolls, toHit);
                misses = attacks - hits;
                sixes += NumberOfSuccesses(rerolls, 6);
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
                if (reroll == 6)
                    sixes++;
            }
        }

        if (tesla && sixes > 0)
        {
            hits += (sixes * 2);
            out += ", getting " + (sixes * 2) + " automatic hits from Tesla";
        }

        if (hits == 1)
            out += " = " + hits + " hit";
        else
            out += " = " + hits + " hits";

        Chatbox.WriteLine(out);
        return hits;
    }
}