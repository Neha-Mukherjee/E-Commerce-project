
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Product> products = createProducts();
        List<Order> orders = createOrders(products);


        OrderAnalyticsService service = new OrderAnalyticsService();

        orders.forEach(order ->
                System.out.println("Order ID: " + order.getOrderId() + ", Total: ₹" + service.orderTotal(order)));

        Optional<Order> result = orderLookup(orders,10000);
        if(result.isPresent()){
            System.out.println(result.get());
        }
        else
            System.out.println("Order not found");

        Set<String> uniqueCustomers = new HashSet<>();
        orders.forEach(order -> uniqueCustomers.add(order.getCustomerName()));

        System.out.println("Unique customers: " + uniqueCustomers);
        System.out.println("Total unique customers: " + uniqueCustomers.size());

        List<Product> sortedProducts = products.stream()
                .sorted(Comparator.comparingLong(Product::getPriceInRupees)
                        .reversed()
                        .thenComparing(Product::getProductName))
                .toList();

        sortedProducts.forEach(System.out::println);

        List<Order> completedOrders = orders.stream().filter(order -> order.getStatus()==OrderStatus.COMPLETED).collect(Collectors.toList());
        System.out.println("\ntotal revenue:");
        System.out.println("₹" + service.totalRevenue(orders));
       // orders.forEach(System.out::println);
      completedOrders.forEach(System.out::println);


        System.out.println("\nOrders grouped by customers");
        service.getOrdersByCustomer(orders).forEach((customer, customerOrders) -> {System.out.println(customer + ":");customerOrders.forEach(order -> System.out.println("  Order ID: " + order.getOrderId()));});
       // System.out.println(orders);

        System.out.println("\ncustomer spendings");
        service.getCustomerSpending(orders).forEach((customer, amount) -> System.out.println(customer + ": ₹" + amount));


        System.out.println("\npopular products");
        service.getPopularProducts(orders)
                .forEach((product, quantity) ->
                        System.out.println(product + ": " + quantity + " sold"));

        System.out.println("\nrevenue by category");
        service.getCategoryRevenue(orders).forEach((category, revenue) ->
                        System.out.println(category + ": ₹" + revenue));

        System.out.println("\nOrders Sorted by Total ");
        service.sortOrdersByTotal(orders).forEach(order ->
                        System.out.println("Order ID: " + order.getOrderId() + ", Total: ₹" + service.orderTotal(order)
                        ));

        System.out.println("\nPartitioned Orders ");

        Map<Boolean, List<Order>> partitioned =
                service.partitionOrders(orders);

        System.out.println("Completed Orders:");
        partitioned.get(true).forEach(System.out::println);

        System.out.println("Non-Completed Orders:");
        partitioned.get(false).forEach(System.out::println);
    }



    private static List<Product> createProducts() {
        List<Product> products = new ArrayList<>();


        products.add(new Product(101, "Keyboard", "Electronics", 1200));
        products.add(new Product(102, "Mouse", "Electronics", 600));
        products.add(new Product(103, "Headphones", "Electronics", 2000));
        products.add(new Product(201, "Notebook", "Stationery", 100));
        products.add(new Product(202, "Pen", "Stationery", 20));




        return products;
    }

    private static List<Order> createOrders(List<Product> products) {
        List<Order> orders = new ArrayList<>();


        orders.add(new Order(101, "Asha", List.of(
                new OrderItem(products.get(0), 2),
                new OrderItem(products.get(1), 1)
        ), OrderStatus.COMPLETED));


        orders.add(new Order(102, "Rahul", List.of(
                new OrderItem(products.get(3), 3),
                new OrderItem(products.get(4), 5)
        ), OrderStatus.PENDING));


        orders.add(new Order(103, "Priya", List.of(
                new OrderItem(products.get(2), 1),
                new OrderItem(products.get(3), 2)
        ), OrderStatus.COMPLETED));


        orders.add(new Order(104, "Asha", List.of(
                new OrderItem(products.get(1), 2)
        ), OrderStatus.CANCELLED));


        orders.add(new Order(105, "Riya", List.of(
                new OrderItem(products.get(0), 1),
                new OrderItem(products.get(4), 10)
        ), OrderStatus.COMPLETED));


        orders.add(new Order(106, "Rahul", List.of(
                new OrderItem(products.get(1), 1),
                new OrderItem(products.get(3), 4)
        ), OrderStatus.COMPLETED));

        return orders;
    }
    private static Optional<Order> orderLookup(List<Order> orders,int orderId){
        HashMap<Integer , Order>  orderMap= new HashMap<>();
        for (Order order: orders){
            orderMap.put(order.getOrderId(),order);
        }
        return Optional.ofNullable(orderMap.get(orderId));
    }
}
