package Vassal40k.Army.Loader;

import Vassal40k.Army.Loader.Converters.AbstractModel;
import Vassal40k.Army.Loader.Converters.Unit;
import Vassal40k.Army.Loader.Converters.Vassal40kModel;
import Vassal40k.Utility.Chatbox;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Vassal40kRosterLoader extends RosterLoader
{
    public static final String VERSION = "1.1";
    
    @Override
    public List<Unit> Load(File xmlFile)
    {
        List<Unit> result = new ArrayList<>();
        
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            Element root = doc.getDocumentElement();
            if (Double.parseDouble(root.getAttribute("version")) > Double.parseDouble(VERSION))
            {
                Chatbox.WriteLine("Cannot load file because this library is out of date.");
                return null;
            }
            
            Unit unit = new Unit();
            NodeList models = root.getElementsByTagName("model");
            for (int i = 0; i < models.getLength(); i++)
            {
                Element modelElement = (Element)models.item(i);
                AbstractModel model = new Vassal40kModel(
                        modelElement.getAttribute("type"),
                        modelElement.getAttribute("state"),
                        modelElement.getAttribute("posX"),
                        modelElement.getAttribute("posY"));
                unit.Models.add(model);
            }
            result.add(unit);
        }
        catch(Exception ex)
        {
        }
        
        return result;
    }
}
