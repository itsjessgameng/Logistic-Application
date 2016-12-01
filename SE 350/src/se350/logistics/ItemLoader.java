package se350.logistics;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ItemLoader {

    private File file;

    public ItemLoader (File file) {
        this.file = file;
    }

    public ArrayList<Item> load () {
        ArrayList<Item> itemList = new ArrayList<>();
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();


            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList itemEntries = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < itemEntries.getLength(); i++) {
                if (itemEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = itemEntries.item(i).getNodeName();
                if (!entryName.equals("item")) {
                    try {
                        throw new Exception("Unexpected node found: " + entryName);
                    } catch (Exception nodeNotFound) {
                        nodeNotFound.printStackTrace();
                    }
                    return null;
                }

                // Get a named nodes
                Element elem = (Element) itemEntries.item(i);
                String itemID = elem.getElementsByTagName("itemID").item(0).getTextContent();
                String itemCostString = elem.getElementsByTagName("itemCost").item(0).getTextContent();
                try {
                    double itemCost = Double.parseDouble(itemCostString);
                    ItemFactory item = new ItemFactory();
                    Item itemBuild = item.build(itemID, itemCost);
                    itemList.add(itemBuild);
                } catch (Exception e){
                    e.printStackTrace();
                }


            }

            return itemList;

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
        return null;
    }
}
