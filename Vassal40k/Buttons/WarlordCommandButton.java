package Vassal40k.Buttons;

import Vassal40k.Utility.Chatbox;
public class WarlordCommandButton extends BaseButton
{
    public WarlordCommandButton()
    {
        Initialize("Warlord Traits - Command", "Command", "Warlord Traits - Command", false);
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Command Warlord Traits:");
        int roll = DiceRoll(6);
        if (roll == 1)
            Chatbox.WriteLine("* (1) = Inspiring Presence: Friendly units within 12\" of the Warlord can use his leadership rather than their own.");
        else if (roll == 2)
            Chatbox.WriteLine("* (2) = Intimidating Presence: Enemy units within 12\" of the Warlord must use their lowest Leadership value, not the highest.");
        else if (roll == 3)
            Chatbox.WriteLine("* (3) = The Dust of a Thousand Worlds: Your Warlord, and all friendly units within 12\", have the Move Through Cover special rule.");
        else if (roll == 4)
            Chatbox.WriteLine("* (4) = Master of the Vanguard: Your Warlord, and all friendly units within 12\", roll an extra dice when they Run, using the highest.");
        else if (roll == 5)
            Chatbox.WriteLine("* (5) = Target Priority: Your Warlord, and all friendly units within 12\", re-roll To Hit rolls of 1 when shooting at enemy units  that are within 3\" of one or more objectives.");
        else if (roll == 6)
            Chatbox.WriteLine("* (6) = Coordinated Assault: Your Warlord, and all friendly units within 12\", add 1 to the result when rolling their charge distance.");
    }
}
