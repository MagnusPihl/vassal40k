package Vassal40k.Army;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ArmyFileFilter extends FileFilter
{

    @Override
    public boolean accept (File f)
    {
        if (f.isDirectory())
            return true;
        else if (f.getName().toLowerCase().endsWith(".v40karmy"))   //Vassal40k
            return true;
        else if (f.getName().toLowerCase().endsWith(".rst"))        //ArmyBuilder
            return true;
        /*else if (f.getName().toLowerCase().endsWith(".ros"))      //BattleScribe
            return true;*/
        
        return false;
    }

    @Override
    public String getDescription ()
    {
        return "Vassal40k Army, ArmyBuilder Roster";    //BattleScribe?
    }
    
}
