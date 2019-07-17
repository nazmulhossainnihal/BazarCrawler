package webcrawler;

import java.util.ArrayList;

public class Sorting {

    public static ArrayList<Product> sortBySource( ArrayList<Product> list) {
        return list;
    }

    static void sortByPrice( ArrayList<Product> list) {
        for(int i = 0; i<list.size(); i++) {
            for(int j = 0; j< list.size() - 1 ; j++){
                if(list.get(j).getPrice()>list.get(j+1).getPrice()) {
                    Product temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1 , temp);
                }
            }
        }
    }

    static void sortByOffer( ArrayList<Product> list) {
        for(int i = 0; i<list.size(); i++) {
            for(int j = 0; j< list.size() - 1 ; j++){
                if(list.get(j).getPercentage()<list.get(j+1).getPercentage()) {
                    Product temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1 , temp);
                }
            }
        }
    }


}
