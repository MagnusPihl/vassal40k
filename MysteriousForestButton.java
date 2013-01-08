public class MysteriousForestButton extends BaseButton
{
    public MysteriousForestButton()
    {
        Initialize("Mysterious Forest", "Mysterious Forest", "Mysterious Forest", false);
    }

    @Override
    protected void OnClick ()
    {
        Chatbox.WriteLine("<" + Chatbox.GetPlayerName() + "> rolls for Mysterious Forest:");
        int roll = DiceRoll(6);
        if (roll == 1)
            Chatbox.WriteLine("* (" + roll + "): Almost pleasant (nothing happens)");
        else if (roll == 2)
            Chatbox.WriteLine("* (" + roll + "): Brainleaf fronds (at the start of the Shooting Phase, unit must pass Leadership test on 3D6, or one random model makes D6 attacks the unit with a close combat weapon, no Cover Saves allowed; controlling player may distribute Wounds)");
        else if (roll == 3)
            Chatbox.WriteLine("* (" + roll + "): Carnivorous jungle (at the start of the Shooting Phase, unit suffers D3 automatic hits at S5 AP-, distributed by controlling player but only to models within terrain; vehicles are resolved against rear armour; no cover saves allowed)");
        else if (roll == 4)
            Chatbox.WriteLine("* (" + roll + "): Ironbark forest (grants a 3+ Cover Save)");
        else if (roll == 5)
            Chatbox.WriteLine("* (" + roll + "): Overgrown spinethorn (grants a 2+ Cover Save vs. grenades; grenades used by unit in terrain, or Charge moves made within terrain, have no effect)");
        else if (roll == 6)
            Chatbox.WriteLine("* (" + roll + "): Razorwing nest (if unit rolls 4+ on either die while making Difficult Terrain tests in terrain, it suffers D6 S3 AP- hits with the Rending special rule, distributed by controlling player but only to models within terrain; vehicles are resolved against rear armour; no cover saves allowed)");
    }    
}