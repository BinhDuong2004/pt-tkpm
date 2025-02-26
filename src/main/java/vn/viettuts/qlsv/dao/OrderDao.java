package vn.viettuts.qlsv.dao;

import vn.viettuts.qlsv.entity.Order;
import vn.viettuts.qlsv.entity.XML.OrderXML;
import vn.viettuts.qlsv.utils.FileUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderDao {
    private final String FILE_PATH = "src/main/resources/orders.xml";

    private List<Order> orders;

    private  OrderDetailDao orderDetailDao;
    public OrderDao() {

        orders = readXML();
        if (orders == null) {
            orders = new ArrayList<>();
        }
        System.out.println(orders);
    }

    public List<Order> getOrders() {
        if (orders != null && !orders.isEmpty()) {
            orders.clear();
            orders = readXML();
        }


        return orders;
    }

    private List<Order> readXML() {
        List<Order> result = new ArrayList<>();

        OrderXML orderXML = (OrderXML) FileUtils.readXMLFile(FILE_PATH, OrderXML.class);

        if (orderXML != null) {
            result = orderXML.getOrderList();
        }

        return result;
    }

    public void writeXML() {
        OrderXML orderXML = new OrderXML();
        orderXML.setOrderList(orders);
        FileUtils.writeXMLtoFile(FILE_PATH, orderXML);
    }

    private boolean checkDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate orderDate = LocalDate.parse(date, formatter);

            LocalDate currentDate = LocalDate.now();

            return orderDate.isAfter(currentDate);
        } catch (Exception e) {
            System.out.println("Invalid date format: " + e.getMessage());
            return false;
        }
    }

    private boolean isOrderExist(Order order) {
        return orders.stream().anyMatch(o -> Objects.equals(o.getId(), order.getId()));
    }

    public void addOrder(Order order) {
        if (!checkDate(order.getOrderDate())) {
            throw new IllegalArgumentException("Ngày đặt hàng phải sau ngày hiện tại!");
        }

        if (isOrderExist(order)) {
            throw new IllegalArgumentException("Mã đơn hàng đã tồn tại!");
        }

        orders.add(order);
        writeXML();
    }

    public void updateOrder(Order order) {
        orders.forEach(o -> {
            if(isOrderExist(o)){
                throw new IllegalArgumentException("Mã đơn hàng mới đã tồn tại !");
            }
        });


        if (!checkDate(order.getOrderDate())) {
            throw new IllegalArgumentException("Ngày đặt hàng phải sau ngày hiện tại!");
        }

        for (Order o : orders) {
            if (Objects.equals(o.getId(), order.getId())) {
                orders.set(orders.indexOf(o), order);
            }
        }

        writeXML();
    }

    public void deleteOrder(Order id) {
        orders.removeIf(order -> Objects.equals(order.getId(), id.getId()));
        writeXML();
        orderDetailDao = new OrderDetailDao();
        orderDetailDao.deleteOrderDetailByOrderId(id.getId());
    }

    public List<Order> getMaxTotalMoney(){
        List<Order> result = new ArrayList<>();
        float max = 0;
        for (Order order : orders) {
            if (Float.parseFloat(order.getTotalMoney()) > max) {
                max = Float.parseFloat(order.getTotalMoney());
            }
        }
        for (Order order : orders) {
            if (Float.parseFloat(order.getTotalMoney()) == max) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> getMinTotalMoney(){
        List<Order> result = new ArrayList<>();
        float min = Float.MAX_VALUE;
        for (Order order : orders) {
            if (Float.parseFloat(order.getTotalMoney()) < min) {
                min = Float.parseFloat(order.getTotalMoney());
            }
        }
        for (Order order : orders) {
            if (Float.parseFloat(order.getTotalMoney()) == min) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> getOrderByID(String id){
        if(id == null || id.isEmpty()){
            return orders;
        }

        List<Order> rs = new ArrayList<>();
        for (Order order : orders) {
            if (order.getId().toLowerCase().contains(id)) {
                rs.add(order);
                break;
            }
        }
        return rs;
    }
}
