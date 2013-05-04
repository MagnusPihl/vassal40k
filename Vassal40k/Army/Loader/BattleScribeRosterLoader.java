package Vassal40k.Army.Loader;

import Vassal40k.Army.Loader.Converters.AbstractModel;
import Vassal40k.Army.Loader.Converters.BattleScribeModel;
import Vassal40k.Army.Loader.Converters.Unit;
import Vassal40k.Utility.Chatbox;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BattleScribeRosterLoader extends RosterLoader
{
    @Override
    public List<Unit> Load (File file)
    {
        ArrayList<Unit> result = new ArrayList<Unit>();
        
        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            
            NodeList units = null;
            NodeList nodes = doc.getFirstChild().getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++)
            {
                if (nodes.item(i).getNodeName().equals("selections"))
                {
                    units = nodes.item(i).getChildNodes();
                    break;
                }
            }
            if (units == null)
                return result;
            
            for (int i = 0; i < units.getLength(); i++)
            {
                Unit unit = new Unit();
                if (units.item(i) != null)
                    getModelsRecursively(units.item(i), unit.Models);
                if (unit.Models.size() > 0)
                    result.add(unit);
            }
            
            try
            {
                for (Unit unit : result)
                    ModelPackager.Pack(unit.Models);
                ModelPackager.Pack(result, false);
            }
            catch(Exception ex)
            {
                Chatbox.WriteLine("Exception in Packaging: " + ex.toString());
            }
        }
        catch (Exception ex)
        {
            Chatbox.WriteLine("- Error occurred: " + ex.toString());
        }
        
        return result;
    }
    
    protected void getModelsRecursively(Node node, List<AbstractModel> modelList)
    {
        try
        {
            if (node.getAttributes() != null)
            {
                if (node.getAttributes().getNamedItem("entryId") != null)
                {
                    String id = node.getAttributes().getNamedItem("entryId").getNodeValue();
                    int number = 1;
                    if (node.getAttributes().getNamedItem("number") != null)
                        number = Integer.parseInt(node.getAttributes().getNamedItem("number").getNodeValue());
                    for (int n = 0; n < number; n++)
                    {
                        BattleScribeModel model = new BattleScribeModel(id);
                        if (model.getGamePiece() != null)
                        {
                            modelList.add(model);
                            List<String> wargearList = new ArrayList<String>();
                            getWargearRecursively(node, wargearList);
                            for (String wargear : wargearList)
                                model.AddWargear(wargear);
                        }
                    }
                }
            }

            if (node.getChildNodes() != null)
            {
                for (int i = 0; i < node.getChildNodes().getLength(); i++)
                    getModelsRecursively(node.getChildNodes().item(i), modelList);
            }
        }
        catch(Exception ex)
        {
            Chatbox.WriteLine("Exception in getModelsRecursively: " + ex.toString());
        }
    }
    
    protected void getWargearRecursively(Node container, List<String> wargearIDs)
    {
        try
        {
            NodeList children = container.getChildNodes();
            for (int i = 0; i < children.getLength(); i++)
            {
                Node child = children.item(i);
                if (child.getNodeName().equals("selection"))
                {
                    if (child.getAttributes().getNamedItem("entryID") != null)
                        wargearIDs.add(child.getAttributes().getNamedItem("entryID").getNodeValue());
                }

                if (child.getChildNodes().getLength() > 0)
                {
                    getWargearRecursively(child, wargearIDs);
                }
            }
        }
        catch(Exception ex)
        {
            Chatbox.WriteLine("Exception in getWargearRecursively: " + ex.toString());
        }
    }
}