package se350.logistics;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderLoader {

    private File file;

    public OrderLoader(File file) {
        this.file = file;
    }

    public List<OrderImpl> load () {
        List<OrderImpl> orderImplList = new ArrayList<>();

        OrderFactory factory = new OrderFactory();

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList orderEntries = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < orderEntries.getLength(); i++) {
                if (orderEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = orderEntries.item(i).getNodeName();
                if (!entryName.equals("order")) {
                    try {
                        throw new Exception("Unexpected node found: " + entryName);
                    } catch (Exception nodeNotFound) {
                        nodeNotFound.printStackTrace();
                    }
                    return null;
                }

                // Get a node attribute

                NamedNodeMap aMap = orderEntries.item(i).getAttributes();
                String orderNumber = aMap.getNamedItem("number").getNodeValue();

                // Get a named nodes
                Element elem = (Element) orderEntries.item(i);
                String orderID = elem.getElementsByTagName("orderID").item(0).getTextContent();
                String orderTime = elem.getElementsByTagName("time").item(0).getTextContent();
                String destination = elem.getElementsByTagName("destination").item(0).getTextContent();
                HashMap<String, Integer> orderList = new HashMap<>();

                    NodeList items = elem.getElementsByTagName("item");
                    for(int m = 0; m < items.getLength(); m++){
                        if(items.item(m).getNodeType() == Node.TEXT_NODE){
                            continue;
                        }
                        entryName = items.item(m).getNodeName();
                        if (!entryName.equals("item")){
                            try {
                                throw new Exception("Unexpected node found: " + entryName);
                            } catch (Exception nodeNotFound) {
                                nodeNotFound.printStackTrace();
                            }
                            return null;
                        }
                        elem = (Element) items.item(m);
                        String itemID = elem.getElementsByTagName("itemID").item(0).getTextContent();
                        String quantity = elem.getElementsByTagName("quantity").item(0).getTextContent();


                        try {
                            orderList.put(itemID, Integer.parseInt(quantity));
                        }    catch (Exception e){
                            e.printStackTrace();
                        }


                    }
                OrderInfo orderInfo = factory.buildOrderInfo(orderID, Integer.parseInt(orderTime), destination, orderList);
                OrderImpl order = factory.buildOrderImpl(Integer.parseInt(orderNumber), orderInfo);
                orderImplList.add(order);
                }

            return orderImplList;

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
        return null;
    }

}




