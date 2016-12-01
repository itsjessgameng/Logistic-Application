package se350.logistics;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ItemCatalog {
    public void printCatalog(ArrayList<Item> catalogList){
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        for(Item item: catalogList){
            if(item.getID().length() > 7) {
                System.out.println(item.getID() + "\t:\t$" + formatter.format(item.getCost()));
            } else {
                System.out.println(item.getID() + "\t\t:\t$" + formatter.format(item.getCost()));
            }
        }
    }

}
