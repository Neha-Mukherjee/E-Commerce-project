import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderAnalyticsService {
    public Long orderTotal(Order order) {
        return order.getItems().stream().mapToLong(item ->item.getProduct().getPriceInRupees()* item.getQuantity()).sum();

    }

    public Long totalRevenue(List<Order> orders) {
        return orders.stream().filter(order -> order.getStatus()==OrderStatus.COMPLETED).mapToLong(order->orderTotal(order)).sum();

    }

    public Map<String, List<Order>> getOrdersByCustomer(List<Order> orders) {
        return orders.stream().collect(Collectors.groupingBy(Order::getCustomerName));
    }
    public Map<String, Long> getCustomerSpending(List<Order> orders) {
        return orders.stream().filter(order -> order.getStatus() == OrderStatus.COMPLETED).collect(Collectors.groupingBy(Order::getCustomerName, Collectors.summingLong(this::orderTotal)));
    }
    public Map<String, Integer> getPopularProducts(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.COMPLETED)
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(item -> item.getProduct().getProductName(),Collectors.summingInt(OrderItem::getQuantity)
                ));
    }
    public Map<String, Long> getCategoryRevenue(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.COMPLETED)
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        item -> item.getProduct().getCategory(),
                        Collectors.summingLong(item ->
                                item.getProduct().getPriceInRupees()
                                        * item.getQuantity())
                ));
    }
    public List<Order> sortOrdersByTotal(List<Order> orders) {
        return orders.stream()
                .sorted(Comparator.comparingLong(this::orderTotal)
                        .reversed()
                        .thenComparingInt(Order::getOrderId))
                .toList();
    }
    public Map<Boolean, List<Order>> partitionOrders(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.partitioningBy(
                        order -> order.getStatus() == OrderStatus.COMPLETED
                ));
    }
}
