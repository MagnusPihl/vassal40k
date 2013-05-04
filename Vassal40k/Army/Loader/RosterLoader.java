package Vassal40k.Army.Loader;

import VASSAL.build.GameModule;
import VASSAL.build.module.Map;
import VASSAL.build.module.PieceWindow;
import VASSAL.build.widget.PieceSlot;
import VASSAL.command.AddPiece;
import VASSAL.counters.GamePiece;
import Vassal40k.Army.Loader.Converters.AbstractModel;
import Vassal40k.Army.Loader.Converters.Unit;
import Vassal40k.Utility.Chatbox;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class RosterLoader
{
    protected abstract List<Unit> Load(File file);
    
    public static List<Unit> Load(File file, Map map)
    {
        GamePiece[] oldPieces = map.getAllPieces(); //Get these so we can remove them
        for (GamePiece oldPiece : oldPieces)
            map.removePiece(oldPiece);
        
        RosterLoader loader = DetectFileType(file);
        if (loader == null)
            return new ArrayList<Unit>();
        List<Unit> units = loader.Load(file);
        
        int modelCount = 0;
        for (Unit unit : units)
        {
            modelCount += unit.Models.size();
            for (AbstractModel model : unit.Models)
            {
                map.addPiece(model.getGamePiece());
            }
        }
        
        Chatbox.WriteLine("Successfully loaded " + units.size() + " units containing a total of " + modelCount + " models to " + map.getMapName() + "'s window.");
        
        return units;
    }
    
    public static RosterLoader DetectFileType(File file)
    {
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            
            Element root = doc.getDocumentElement();
            if (root.getNodeName().equals("roster"))
            {
                Chatbox.WriteLine("- Detected BattleScribe roster. Loading...");
                return new BattleScribeRosterLoader();
            }
            else if (root.getNodeName().equals("document"))
            {
                String signature = root.getAttribute("signature");
                if (signature != null)
                {
                    if (signature.equals("Army Builder Roster"))
                    {
                        Chatbox.WriteLine("- Detected Army Builder roster. Loading...");
                        return new ArmyBuilderRosterLoader();
                    }
                    else if (signature.equals("Vassal 40k Army"))
                    {
                        Chatbox.WriteLine("- Detected Vassal40k roster. Loading...");
                        return new Vassal40kRosterLoader();
                    }
                }
            }
        }
        catch(Exception ex)
        {
        }
        
        Chatbox.WriteLine("- Error: File type not recognized.");
        return null;
    }
    
    public static GamePiece GetPieceFromPalette(String id)
    {
        GameModule gameModule = GameModule.getGameModule();
        PieceWindow palette = gameModule.getAllDescendantComponentsOf(PieceWindow.class).get(0);
        List<PieceSlot> slots = palette.getAllDescendantComponentsOf(PieceSlot.class);
        for (PieceSlot slot : slots)
        {
            if (slot.getGpId().equals(id))
                return ((AddPiece)gameModule.decode(gameModule.encode(new AddPiece(slot.getPiece())))).getTarget();
        }
        
        return null;
    }
}