public class Product {
    private int productId;
    private String productName;
    private String category;
    private long priceInRupees;

    public Product(int productId,String productName,  String category,long priceInRupees) {
        this.productId = productId;
        this.priceInRupees = priceInRupees;
        this.category = category;
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public long getPriceInRupees() {
        return priceInRupees;
    }
    public String toString(){
        return  "productId=" + productId + ", productName='" + productName + '\'' + ", category='" + category + '\'' + ", priceInRupees=" + priceInRupees;
    }
}

