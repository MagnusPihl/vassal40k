package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;

public class BuildingDamageButton extends BaseButton
{
    protected boolean ap1 = false;
    protected boolean ap2 = false;
    protected boolean rollDetonationDistance = true;
    
    public BuildingDamageButton()
    {
        Initialize("Building Damage", "Building Damage", "Building Damage", true);
        AddAttribute("ap1", "AP 1 ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(ap1); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    ap1 = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    ap1 = o.equals("true");
            }

        });
        AddAttribute("ap2", "AP 2 ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(ap2); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    ap2 = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    ap2 = o.equals("true");
            }

        });
        AddAttribute("rollDetonationDistance", "Auto-roll detonation distance ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rollDetonationDistance); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rollDetonationDistance = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rollDetonationDistance = o.equals("true");
            }

        });
    }

    @Override
    protected void OnClick ()
    {
        int roll = DiceRoll(6);
        
        String declaration = "<" + Chatbox.GetPlayerName() + "> rolls on the building damage chart";
        String rollStr = "* (" + roll + ") ";
        
        int baseRoll = roll;
        
        if (ap1)
        {
            roll += 2;
            //declaration += ", at AP 1";
            rollStr += "+ 2 (AP 1) ";
        }
        else if (ap2)
        {
            roll += 1;
            //declaration += ", at AP 2";
            rollStr += "+ 1 (AP 2) ";
        }
        
        if (roll != baseRoll)   //No point in adding additional result if it hasn't changed
            rollStr += "= (" + roll + ") ";
        
        if (roll == 1)
            rollStr += "= Breach! (p. 94)";
        else if (roll == 2)
            rollStr += "= Tremor (p. 94)";
        else if (roll == 3)
            rollStr += "= Partial Collapse (p. 94)";
        else if (roll == 4)
            rollStr += "= Structural Collapse (p. 94)";
        else if (roll == 5)
            rollStr += "= Catastrophic Breach";
        else if (roll == 6)
            rollStr += "= Total Collapse";
        else if (roll >= 7)
        {
            int detonationRoll = DiceRoll(6);
            rollStr += "= Detonation!";
            if (rollDetonationDistance)
                rollStr += " (" + detonationRoll + " inches).";
        }
        
        Chatbox.WriteLine(declaration);
        Chatbox.WriteLine(rollStr);
    }
}