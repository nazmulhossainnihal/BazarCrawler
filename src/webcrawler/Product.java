package webcrawler;

public class Product {

    private String name;
    //
    private String imageUrl;
    private double price;
    private double oldPrice;
    private String productPage;     //URL to the page of the product
    private int percentage;
    private String source;

    public Product() {
    }

    public Product(String name, String imageUrl, double price, double oldPrice, String productPage, int percentage, String source) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.oldPrice = oldPrice;
        this.productPage = productPage;
        this.percentage = percentage;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getProductPage() {
        return productPage;
    }

    public void setProductPage(String productPage) {
        this.productPage = productPage;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", oldPrice=" + oldPrice +
                ", productPage='" + productPage + '\'' +
                ", percentage=" + percentage +
                ", source='" + source + '\'' +
                '}';
    }
}
