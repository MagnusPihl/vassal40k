package Vassal40k.Army.Saver;

import VASSAL.counters.GamePiece;
import Vassal40k.Army.Loader.Converters.ArmyBuilderModel;
import java.io.StringWriter;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Vassal40kRosterSaver
{
    public static final String VERSION = "1.1";
    
    public static String Save(List<GamePiece> models)
    {
        try
        {
            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            
            Element root = doc.createElement("document");
            root.setAttribute("signature", "Vassal 40k Army");
            root.setAttribute("version", VERSION);
            doc.appendChild(root);
            
            Element container = doc.createElement("army");
            root.appendChild(container);
            
            for(GamePiece model : models)
            {
                Element modelElement = doc.createElement("model");
                modelElement.setAttribute("posX", model.getPosition().x + "");
                modelElement.setAttribute("posY", model.getPosition().y + "");
                modelElement.setAttribute("type", model.getType());
                modelElement.setAttribute("state", model.getState());
                container.appendChild(modelElement);
            }
            
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);
            
            return sw.toString();
        }
        catch(Exception ex)
        {
            
        }
        
        return null;
    }
}