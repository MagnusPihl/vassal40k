package Vassal40k.Army;

import Vassal40k.Army.Loader.Converters.AbstractModel;
import Vassal40k.Army.Loader.Converters.Unit;
import Vassal40k.Army.Loader.ModelPackager;
import Vassal40k.Army.Loader.RosterLoader;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import javax.swing.JFileChooser;

public class ArmyLoaderTest
{
    public static void main (String[] args)
    {
        JFileChooser fileDlg = new JFileChooser();
        fileDlg.showOpenDialog(null);
        File file = fileDlg.getSelectedFile();
        List<Unit> units = RosterLoader.Load(file, null);
        HashSet<String> errors = new HashSet<>();
        for (Unit unit : units)
        {
            System.out.println("Found unit w/ " + unit.Models.size() + " model(s)");
            for (AbstractModel model : unit.Models)
            {
                String id = model.getVassalID();
                if (id != null)
                    System.out.println("  Model ID: " + id);
                else
                    errors.add(model.getOriginalID());
            }
        }
        if (errors.size() > 0)
        {
            System.out.println("Did not recognize the following models:");
            for (String error : errors)
            {
                System.out.println("  " + error);
            }
        }
    }
}