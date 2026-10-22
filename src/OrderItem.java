public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
    public String toString(){
        return  "OrderItem{"+"product=" + product + ", quantity=" + quantity + "}" ;
    }
}
