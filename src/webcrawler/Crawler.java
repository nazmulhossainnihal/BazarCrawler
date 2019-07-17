package webcrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import debugging.Debug;

public class Crawler {

//     Main is for debugging only
//    public static void main(String[] args) {
//        ArrayList<Product> products = crawlAll("Watches");
//
//        Sorting.sortByOffer(products);
//        for (Product product : products) {
//            System.out.println(product.toString());
//        }
//    }

    private static ArrayList<Product> crawlDaraz(String userQuery){
        ArrayList<Product> productList = new ArrayList<>();
        String initialUrl;
        String urlString;
        int numOfProductsFound;

        initialUrl = "https://www.daraz.com.bd/catalog/?q=";
        urlString = fullUrl(initialUrl, userQuery);

        Document darazDoc = null;
        try{
            darazDoc = Jsoup.connect(urlString).get();
        }
        catch (Exception ex){
            System.out.println("Debug: Couldn't connect with the Daraz website.");
            return null;
        }

        Debug.log("Finding elements from Daraj.");
        Elements products = darazDoc.select("div.sku");

        numOfProductsFound = products.size();

        for(Element product : products){
            String name = product.select("span.name").text();
            String imageUrl = product.select("img.image").attr("data-src");

            double price = 0, oldPrice = 0;
            Elements prices = product.select("span.price").select("span[data-price]");
            if(prices.size() == 2){
                oldPrice = parsePrice( prices.last().text());
                prices.remove(1);
                price = parsePrice( prices.last().text());
            }
            else if( prices.size() == 1){
                price = parsePrice( prices.text());
                oldPrice = price;
            }

            String productPage = product.select("a.link").attr("href");

            int offerPercentage = 0;
            String offer = product.select("span.sale-flag-percent").text();
            if(offer.length()!=0){
                offerPercentage = parsePercentage(offer);
            }

            String source = "Daraj";

            Product p = new Product(name, imageUrl, price, oldPrice, productPage, offerPercentage, source);
            //System.out.println(p.toString());
            productList.add(p);
        }

        return productList;
    }

    private static ArrayList<Product> crawlKaymu(String userQuery){
        return null;    //
    }

    private static ArrayList<Product> crawlPriyoShop(String userQuery){
        ArrayList<Product> productList = new ArrayList<>();
        String initialUrl;
        String urlString;
        int numOfProductsFound;

        initialUrl = "http://www.priyoshop.com/searchproduct?q=";
        urlString = fullUrl(initialUrl, userQuery);

        Document priyoDoc = null;
        System.out.println(urlString);
        try{
            priyoDoc = Jsoup.connect(urlString).get();
        }
        catch (Exception ex){
            System.out.println("Debug: Couldn't connect with the PriyoShop website.");
            return null;
        }

        Debug.log("Finding elements from PriyoShop.");
        Elements products = priyoDoc.select("div.item-box");

        numOfProductsFound = products.size();

        for(Element product : products){
            String name = product.select("h2.product-title").text();
            String imageUrl = product.select("img").attr("src");

            double price = 0, oldPrice = 0;

            Elements prices = product.select("div.prices");
            price = parsePrice(prices.select("span.actual-price").text());
            if(prices.select("Span.old-price").size() == 0){
                oldPrice = price;
            }
            else{
                oldPrice = parsePrice(prices.select("span.old-price").text());
            }

            String productPage = "www.priyoshop.com"
                    + product.select("div.picture").select("a").attr("href");

            int offerPercentage = (int)((price - oldPrice)*100/oldPrice);

            String source = "Priyo Shop";

            Product p = new Product(name, imageUrl, price, oldPrice, productPage, offerPercentage, source);

            productList.add(p);
        }

        return productList;
    }

    public static ArrayList<Product> crawlAll(String userQuery , int sortType){
        // 0 sorts by source, 1 sort by price, 2 sort by offer

        ArrayList<Product> products = new ArrayList<>();

        try{
            products.addAll( crawlDaraz(userQuery) );
        } catch (Exception ex) {
            Debug.log("Error crawling Daraz");
        }

        try{
            products.addAll( crawlPriyoShop(userQuery) );
        } catch (Exception ex) {
            Debug.log("Error crawling PriyoShop");
        }

        switch(sortType) {
            case 0:
                Sorting.sortBySource((products));
                break;
            case 1:
                Sorting.sortByPrice(products);
                break;
            case 2:
                Sorting.sortByOffer(products);
                break;
        }


        return products;
    }

    private static String fullUrl(String initialUrl, String userQuery){
        return initialUrl + userQuery.replaceAll(" ","+");
        //Replaces all spaces with a '+'
    }

    private static double parsePrice(String priceString){
        priceString = priceString.replaceAll(",", "");
        priceString = priceString.replaceAll("Tk ", "");
        return Double.parseDouble(priceString);
    }

    private static int parsePercentage(String percentageString){
        percentageString = percentageString.replaceAll("-", "");
        percentageString = percentageString.replaceAll("%", "");
        return Integer.parseInt(percentageString);
    }

}
