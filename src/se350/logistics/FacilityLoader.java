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

public class FacilityLoader {

    private File file;
    private List<Graph.Edge> graph = new ArrayList<>();


    public FacilityLoader(File file) {
        this.file = file;
    }

    public List<FacilityImpl> load () {
        List<FacilityImpl> facilityList = new ArrayList<>();
        FacilityFactory factory = new FacilityFactory();

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            NodeList facilityEntries = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < facilityEntries.getLength(); i++) {
                if (facilityEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }

                String entryName = facilityEntries.item(i).getNodeName();
                if (!entryName.equals("facility")) {
                    try {
                        throw new Exception("Unexpected node found: " + entryName);
                    } catch (Exception nodeNotFound) {
                        nodeNotFound.printStackTrace();
                    }
                    return null;
                }

                // Get a node attribute

                NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                String facilityName = aMap.getNamedItem("name").getNodeValue();

                // Get a named nodes
                Element elem = (Element) facilityEntries.item(i);
                String facilityCity = elem.getElementsByTagName("city").item(0).getTextContent();
                String facilityState = elem.getElementsByTagName("state").item(0).getTextContent();
                String facilityRate = elem.getElementsByTagName("ratePerDay").item(0).getTextContent();
                String facilityCost = elem.getElementsByTagName("costPerDay").item(0).getTextContent();
                FacilityInfo facilityInfo = factory.buildFacilityInfo(facilityCity, facilityState, Double.parseDouble(facilityCost));
                FacilitySchedule facilitySchedule = factory.buildFacilitySchedule(Integer.parseInt(facilityRate));


                // Get all nodes named link
                HashMap<String, Double> facilityConnections = new HashMap<>();

                NodeList networkList = elem.getElementsByTagName("link");
                for (int j = 0; j < networkList.getLength(); j++) {
                    if(networkList.item(j).getNodeType() == Node.TEXT_NODE){
                        continue;
                    }

                    entryName = networkList.item(j).getNodeName();
                    if (!entryName.equals("link")){
                        try {
                            throw new Exception("Unexpected node found: " + entryName);
                        } catch (Exception nodeNotFound) {
                            nodeNotFound.printStackTrace();
                        }
                        return null;
                    }

                    // Get some named nodes
                    elem = (Element) networkList.item(j);
                    NodeList network = elem.getElementsByTagName("directLink");
                    for(int m = 0; m < network.getLength(); m++){
                        if(network.item(m).getNodeType() == Node.TEXT_NODE){
                            continue;
                        }
                        entryName = network.item(m).getNodeName();
                        if (!entryName.equals("directLink")){
                            try {
                                throw new Exception("Unexpected node found: " + entryName);
                            } catch (Exception nodeNotFound) {
                                nodeNotFound.printStackTrace();
                            }
                            return null;
                        }
                        elem = (Element) network.item(m);
                        String city = elem.getElementsByTagName("city").item(0).getTextContent();
                        String state = elem.getElementsByTagName("state").item(0).getTextContent();
                        String distance = elem.getElementsByTagName("distance").item(0).getTextContent();


                        try {
                            facilityConnections.put(city, Double.parseDouble(distance));
                            graph.add(new Graph.Edge(facilityName + ", " + facilityState, city + ", " + state, Integer.parseInt(distance)));
                        }    catch (Exception e){
                            e.printStackTrace();
                        }


                    }


                }

                FacilityConnections Connections = factory.buildFacilityConnections(facilityConnections);

                // Get all nodes names active inventory
                elem = (Element) facilityEntries.item(i);

                HashMap<String, Integer> facilityInventory = new HashMap<>();

                NodeList inventoryList = elem.getElementsByTagName("inventory");
                for (int j = 0; j < inventoryList.getLength(); j++) {
                    if (inventoryList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = inventoryList.item(j).getNodeName();
                    if (!entryName.equals("inventory")) {
                        try {
                            throw new Exception("Unexpected node found: " + entryName);
                        } catch (Exception nodeNotFound) {
                            nodeNotFound.printStackTrace();
                        }
                        return null;
                    }

                    // Get some named nodes
                    elem = (Element) inventoryList.item(j);
                    NodeList inventory = elem.getElementsByTagName("activeInventory");

                    for (int q = 0; q < inventory.getLength(); q++) {
                        if (inventory.item(q).getNodeType() == Node.TEXT_NODE) {
                            continue;
                        }
                        entryName = inventory.item(q).getNodeName();
                        if (!entryName.equals("activeInventory")) {
                            try {
                                throw new Exception("Unexpected node found: " + entryName);
                            } catch (Exception nodeNotFound) {
                                nodeNotFound.printStackTrace();
                            }
                            return null;
                        }

                        // Get some named nodes
                        elem = (Element) inventory.item(q);
                        String itemID = elem.getElementsByTagName("itemID").item(0).getTextContent();
                        String quantity = elem.getElementsByTagName("quantity").item(0).getTextContent();

                        try{
                            facilityInventory.put(itemID, Integer.parseInt(quantity));
                        } catch (Exception e){
                            e.printStackTrace();
                        }


                    }
                }

                FacilityInventory Inventory = factory.buildFacilityInventory(facilityInventory);
                FacilityImpl facility = factory.buildFacilityImpl(facilityName, facilityInfo, facilitySchedule, Inventory,
                                 Connections);
                facilityList.add(facility);
            }

            return facilityList;

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Graph.Edge> getGraph() {
        return graph;
    }
}
