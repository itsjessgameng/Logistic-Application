package se350.logistics;

public class Main {

    public static void main(String[] args){
        FacilitySvc facilitySvc = new FacilitySvc();
        facilitySvc.facilityLoader();
        facilitySvc.getAllFacilityStatus();

        System.out.println("\n");
        System.out.println("Item Catalog");

        ItemSvc itemSvc = new ItemSvc();
        itemSvc.itemLoader();
        itemSvc.getCatalog();

        System.out.println("\n");
        System.out.println("Shortest Path Test");

        System.out.println("a) Santa Fe, NM to Chicago, IL");
        facilitySvc.getShortestPath("Santa Fe, NM","Chicago, IL");
        System.out.println();

        System.out.println("b) Atlanta, GA to St. Louis, MO");
        facilitySvc.getShortestPath("Atlanta, GA","St. Louis, MO");
        System.out.println();

        System.out.println("c) Seattle, Wa to Nashville, TN");
        facilitySvc.getShortestPath("Seattle, WA","Nashville, TN");
        System.out.println();

        System.out.println("d) New York City, NY to Phoenix, AZ");
        facilitySvc.getShortestPath("New York City, NY","Phoenix, AZ");
        System.out.println();

        System.out.println("e) Fargo, ND to Austin, TX");
        facilitySvc.getShortestPath("Fargo, ND","Austin, TX");
        System.out.println();

        System.out.println("f) Denver, CO to Miami, FL");
        facilitySvc.getShortestPath("Denver, CO","Miami, FL");
        System.out.println();

        System.out.println("g) Austin, TX to Norfolk, VA");
        facilitySvc.getShortestPath("Austin, TX", "Norfolk, VA");
        System.out.println();

        System.out.println("h) Miami, FL to Seattle, WA");
        facilitySvc.getShortestPath("Miami, FL","Seattle, WA");
        System.out.println();

        System.out.println("i) Los Angeles, CA to Chicago, IL");
        facilitySvc.getShortestPath("Los Angeles, CA","Chicago, IL");
        System.out.println();

        System.out.println("j) Detroit, MI to Nashville, TN");
        facilitySvc.getShortestPath("Detroit, MI","Nashville, TN");
        System.out.println();

        OrderSvc orderSvc = new OrderSvc();
        orderSvc.OrderLoader();
        orderSvc.getOrderSummary();

        // Currently not printing anything but runs, see processOrder() in OrderSVC and OrderProcessing
        orderSvc.processOrder();




    }

}

