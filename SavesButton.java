import VASSAL.build.GameModule;
import VASSAL.build.module.Chatter;
import VASSAL.command.Command;

public class SavesButton extends BaseButton
{
    private int numberOfHits = 1;
    private int toSave = 4;
    private boolean rerollFailed = false;
    private boolean rerollSuccessful = false;
    private boolean feelNoPain = false;
    private int toFeelNoPain = 5;
    private boolean rerollFailedFeelNoPain = false;

    public SavesButton()
    {
        Initialize("Saves", "Saves", "Armour/Invulnerable/Cover Saves", true);
        AddAttribute("numberOfHits", "Number of Hits ", Integer.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(numberOfHits); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Integer))
                    numberOfHits = ((Integer)o).intValue();
                else if ((o instanceof String))
                    numberOfHits = Integer.parseInt((String)o);
            }
        });
        AddAttribute("toSave", "To Save ", Integer.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(toSave); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Integer))
                    toSave = ((Integer)o).intValue();
                else if ((o instanceof String))
                    toSave = Integer.parseInt((String)o);
            }
        });
        AddAttribute("rerollFailed", "Re-roll Failed Saves ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rerollFailed); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rerollFailed = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rerollFailed = o.equals("true");
            }
        });
        AddAttribute("rerollSuccessful", "Re-roll Successful Saves ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rerollSuccessful); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rerollSuccessful = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rerollSuccessful = o.equals("true");
            }
        });
        AddAttribute("feelNoPain", "Feel No Pain ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(feelNoPain); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    feelNoPain = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    feelNoPain = o.equals("true");
            }
        });
        AddAttribute("toFeelNoPain", "To Feel No Pain ", Integer.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(toFeelNoPain); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Integer))
                    toFeelNoPain = ((Integer)o).intValue();
                else if ((o instanceof String))
                    toFeelNoPain = Integer.parseInt((String)o);
            }
        });
        AddAttribute("rerollFailedFeelNoPain", "Re-roll Failed Feel No Pain ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rerollFailedFeelNoPain); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rerollFailedFeelNoPain = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rerollFailedFeelNoPain = o.equals("true");
            }
        });
    }

    @Override
    protected void OnClick ()
    {
        GameModule mod = GameModule.getGameModule();

        if (numberOfHits <= 0)
        {
            Command chatter = new Chatter.DisplayText(mod.getChatter(), "Number of Hits must be 1 or more.");
            chatter.execute();
            return;
        }

        String intro = "<" + mod.getPrefs().getValue("RealName") + "> rolls " + numberOfHits;
        if (numberOfHits == 1)
            intro += " time to Save:";
        else
            intro += " times to Save:";
        Command chatter = new Chatter.DisplayText(mod.getChatter(), intro);

        RollSaves(mod, chatter);

        chatter.execute();
        mod.sendAndLog(chatter);
    }

    protected void RollSaves(GameModule mod, Command chatter)
    {
        int failures = 0;
        int[] saves = DiceRoll(6, numberOfHits);
        for (int save : saves)
        {
            if (save < toSave)
                failures++;
        }
        
        String str = "* Rolling ";
        if (numberOfHits == 1)
            str += "Save (" + saves[0] + ")";
        else
        {
            str += "Saves (" + saves[0];
            for (int i = 1; i < saves.length; i++)
                str += ", " + saves[i];
            str += ")";
        }

        int successesBecomingFailures = 0;
        if (rerollSuccessful && (numberOfHits - failures > 0))  //Re-roll successful
        {
            int[] rerolledSuccesses = DiceRoll(6, numberOfHits - failures);
            str += ", re-rolling " + (numberOfHits - failures) + " successes (" + rerolledSuccesses[0];
            for (int i = 0; i < rerolledSuccesses.length; i++)
            {
                if (i > 0)
                    str += ", " + rerolledSuccesses[i];
                if (rerolledSuccesses[i] < toSave)
                    successesBecomingFailures++;
            }
            str += "), failing " + successesBecomingFailures;
        }
        
        if (rerollFailed && failures > 0) //Re-roll failures (but not re-rolls!)
        {
            int[] rerolledFailures = DiceRoll(6, failures);
            int newSaves = 0;
            str += ", re-rolling " + failures + " failures (" + rerolledFailures[0];
            for (int i = 0; i < rerolledFailures.length; i++)
            {
                if (i > 0)
                    str += ", " + rerolledFailures[i];
                if (rerolledFailures[i] >= toSave)
                {
                    failures--;
                    newSaves++;
                }
            }
            str += "), saving " + newSaves;
        }
        
        failures += successesBecomingFailures;  //Now we add in the re-rolled failures

        if (feelNoPain && failures > 0)   //Feel No Pain
        {
            int[] fnpSaves = DiceRoll(6, failures);

            str += ", rolling " + failures + " Feel No Pain (" + fnpSaves[0];
            for (int i = 1; i < fnpSaves.length; i++)
                str += ", " + fnpSaves[i];
            str += ")";

            for (int i = 0; i < fnpSaves.length; i++)
            {
                if (fnpSaves[i] >= toFeelNoPain)
                    failures--;
            }
        }
        if (rerollFailedFeelNoPain && failures > 0)   //Re-roll Feel No Pain
        {
            int[] fnpSaves = DiceRoll(6, failures);

            str += ", re-rolling " + failures + " Feel No Pain (" + fnpSaves[0];
            for (int i = 1; i < fnpSaves.length; i++)
                str += ", " + fnpSaves[i];
            str += ")";

            for (int i = 0; i < fnpSaves.length; i++)
            {
                if (fnpSaves[i] >= toFeelNoPain)
                    failures--;
            }
        }

        str += ", resulting in " + failures + " total unsaved Wounds.";

        chatter.append(new Chatter.DisplayText(mod.getChatter(), str));
    }
}