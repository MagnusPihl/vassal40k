package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;

public class VehicleDamageButton extends BaseButton
{
    protected int numberOfHits = 1;
    protected boolean ap1 = false;
    protected boolean ap2 = false;
    protected boolean openTopped = false;
    protected boolean holoField = false;
    protected boolean rollExplosionDistance = true;
    
    public VehicleDamageButton()
    {
        Initialize("Vehicle Damage", "Vehicle Damage", "Vehicle Damage", true);
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
        AddAttribute("openTopped", "Open-topped ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(openTopped); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    openTopped = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    openTopped = o.equals("true");
            }

        });
        AddAttribute("holoField", "Holo-field ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(holoField); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    holoField = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    holoField = o.equals("true");
            }

        });
        AddAttribute("rollExplosionDistance", "Auto-roll explosion distance ", Boolean.class, new ButtonAttributeMethods()
        {
            @Override public String getter() { return String.valueOf(rollExplosionDistance); }
            @Override public void setter (Object o)
            {
                if ((o instanceof Boolean))
                    rollExplosionDistance = ((Boolean)o).booleanValue();
                else if ((o instanceof String))
                    rollExplosionDistance = o.equals("true");
            }

        });
    }
    
    @Override
    protected void OnClick ()
    {
        String declaration = "<" + Chatbox.GetPlayerName() + "> rolls " + numberOfHits + " " + Pluralize("time", numberOfHits) + " on the vehicle damage chart";
        Chatbox.WriteLine(declaration);
        for (int i = 0; i < numberOfHits; i++)
        {
            int roll = DiceRoll(6);
            String rollStr = "* ";

            if (holoField)
            {
                int secondRoll = DiceRoll(6);
                rollStr += "Holo-field rolls (" + roll + ", " + secondRoll + "), taking lowest (";
                if (secondRoll < roll)
                    roll = secondRoll;
                rollStr += roll + ") ";
            }
            else
            {
                rollStr += "(" + roll + ") ";
            }
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

            if (openTopped)
            {
                roll += 1;
                //declaration += ", vs. open-topped";
                rollStr += "+ 1 (Open-topped) ";
            }

            if (roll != baseRoll)   //No point in adding additional result if it hasn't changed
                rollStr += "= (" + roll + ") ";

            if (roll <= 2)
                rollStr += "= Crew Shaken";
            else if (roll == 3)
                rollStr += "= Crew Stunned";
            else if (roll == 4)
                rollStr += "= Weapon Destroyed";
            else if (roll == 5)
                rollStr += "= Immobilised";
            else if (roll >= 6)
            {
                int explodesRoll = DiceRoll(6);
                rollStr += "= Explodes!";
                if (rollExplosionDistance)
                    rollStr += " (" + explodesRoll + " inches).";
            }

            Chatbox.WriteLine(rollStr);
        }
    }   
}