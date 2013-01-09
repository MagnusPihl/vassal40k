package Vassal40k.Utility;

import VASSAL.build.GameModule;
import VASSAL.build.module.Chatter;
import VASSAL.command.Command;

public class Chatbox
{
    private Chatbox() {}    //We're not interested in instances of this class
    
    public static String GetPlayerName()
    {
        return GameModule.getGameModule().getPrefs().getValue("RealName").toString();
    }
    
    public static void WriteLine(String output)
    {
        Command displayText = new Chatter.DisplayText(GameModule.getGameModule().getChatter(), output);
        
        displayText.execute();
        GameModule.getGameModule().sendAndLog(displayText);
    }
}