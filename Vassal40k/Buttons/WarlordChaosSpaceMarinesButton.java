package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;
public class WarlordChaosSpaceMarinesButton extends BaseButton
{
    public WarlordChaosSpaceMarinesButton()
    {
        Initialize("Warlord Traits - Chaos Space Marines", "Chaos Space Marines", "Warlord Traits - Chaos Space Marines", false);
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Chaos Space Marines Warlord Traits:");
        int roll = DiceRoll(6);
        if (roll == 1)
            Chatbox.WriteLine("* (1) = Black Crusader: The Warlord and all friendly units within 12\" of the Warlord have the Preferred Enemy (Space Marines) special rule.");
        else if (roll == 2)
            Chatbox.WriteLine("* (2) = Flames of Spite: All Melee weapons belonging to the Warlord and his unit have the Soul Blaze special rule.");
        else if (roll == 3)
            Chatbox.WriteLine("* (3) = Master of Deception: Nominate up to D3 Infantry units in your army before deployment. Those units gain the Infiltrate special rule.");
        else if (roll == 4)
            Chatbox.WriteLine("* (4) = Hatred Incarnate: The Warlord and his unit have the Hatred special rule. ");
        else if (roll == 5)
            Chatbox.WriteLine("* (5) = Lord of Terror: The Warlord has the Fear special rule. ");
        else if (roll == 6)
            Chatbox.WriteLine("* (6) = Exalted Champion: The Warlord may re-roll his rolls on the Chaos Boon table, including any rolls he makes before the game begins as a result of the Gift of Mutation.");
    }
}
