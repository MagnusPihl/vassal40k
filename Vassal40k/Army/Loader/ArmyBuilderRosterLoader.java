package Vassal40k.Army.Loader;

import Vassal40k.Army.Loader.Converters.AbstractModel;
import Vassal40k.Army.Loader.Converters.ArmyBuilderModel;
import Vassal40k.Army.Loader.Converters.Unit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ArmyBuilderRosterLoader extends RosterLoader
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
            
            NodeList units = doc.getElementsByTagName("squad");
            
            for (int i = 0; i < units.getLength(); i++)
            {
                Unit unit = new Unit();
                Node unitNode = units.item(i);
                NodeList models = unitNode.getChildNodes();
                for (int j = 0; j < models.getLength(); j++)
                {
                    Node modelNode = models.item(j);
                    if (modelNode.getNodeName().equals("entity"))
                    {
                        getModelsRecursively(modelNode, unit.Models);
                    }
                }
                result.add(unit);
            }
            
            for (Unit unit : result)
                ModelPackager.Pack(unit.Models);
            ModelPackager.Pack(result, false);
            for (Unit unit : result)
                unit.applyOffset();
        }
        catch (Exception ex) { }
        
        return result;
    }
    
    protected void getModelsRecursively(Node entityNode, List<AbstractModel> modelList)
    {
        ArrayList<ArmyBuilderModel> localModels = new ArrayList<ArmyBuilderModel>();
        NamedNodeMap attributes = entityNode.getAttributes();
        int count = Integer.parseInt(attributes.getNamedItem("count").getNodeValue());
        for (int k = 0; k < count; k++)
        {
            String id = attributes.getNamedItem("id").getNodeValue();
            ArmyBuilderModel model = new ArmyBuilderModel(id);
            if (model.getGamePiece() != null)
            {
                modelList.add(model);
                localModels.add(model);
            }
        }
        
        NodeList children = entityNode.getChildNodes();
        for (int i = 0; i < children.getLength(); i++)
        {
            Node item = children.item(i);
            if (item.getNodeName().equals("link"))
            {
                Element el = (Element)item;
                Node category = item.getAttributes().getNamedItem("category");
                if (category != null && (category.getNodeValue().equals("add") || category.getNodeValue().equals("transport")))
                {
                    NodeList grandChildren = item.getChildNodes();
                    for (int l = 0; l < grandChildren.getLength(); l++)
                    {
                        Node subEntity = grandChildren.item(l);
                        if (subEntity.getNodeName().equals("entity"))
                            getModelsRecursively(subEntity, modelList);
                    }
                }
                else if (category != null)
                {
                    for (ArmyBuilderModel m : localModels)
                        m.AddWargear(item.getAttributes().getNamedItem("id").getNodeValue());
                }
            }
        }
    }
}