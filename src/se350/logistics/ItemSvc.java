package se350.logistics;

import java.io.File;
import java.util.ArrayList;


public final class ItemSvc implements Item{
    private static ItemSvc ourInstance = new ItemSvc();
    public ArrayList<Item> itemList;
    public static ItemSvc getInstance() {
        return ourInstance;
    }
    private Item delegate;
    private final File file = new File("itemCatalog.xml");

    ItemSvc() {
    }

    public ArrayList<Item> itemLoad(File file) {
        if (!file.exists()){
            try {
                throw new Exception("File does not exist. XML File " + file + " cannot be found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!file.canRead()){
            try {
                throw new Exception("Cannot read XML file" + file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ItemLoader itemLoader = new ItemLoader(file);
        itemList = itemLoader.load();
        return itemLoader.load();
    }

    @Override
    public String getID() {
        return delegate.getID();
    }

    public double getCost() {
        return delegate.getCost();
    }

    public void getCatalog(){
        ItemCatalog catalog = new ItemCatalog();
        catalog.printCatalog(itemList);
    }

    public void itemLoader(){
        ItemLoader loader = new ItemLoader(file);
        this.itemList = loader.load();
    }

}
