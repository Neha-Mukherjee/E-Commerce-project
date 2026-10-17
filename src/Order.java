import java.util.List;

public class Order {
    private int orderId;
    private String customerName;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(int orderId, String customerName, List<OrderItem> items, OrderStatus status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderStatus getStatus() {
        return status;
    }


    public String toString(){
        return "Product{" + "orderId=" + orderId + ", customerName='" + customerName+ '\'' + ", items='" + items + '\'' + ", status=" + status + '}';
    }
}
