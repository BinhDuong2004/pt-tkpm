package vn.viettuts.qlsv.controller;

import vn.viettuts.qlsv.dao.OrderDao;
import vn.viettuts.qlsv.dao.OrderDetailDao;
import vn.viettuts.qlsv.entity.Order;
import vn.viettuts.qlsv.entity.OrderDetail;
import vn.viettuts.qlsv.utils.ExportUtil;
import vn.viettuts.qlsv.view.OrderDetailView;
import vn.viettuts.qlsv.view.OrderView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class OrderController {
    private final OrderView orderView;
    private final OrderDao orderDao;
    private final OrderDetailDao orderDetailDao;

    public OrderController(OrderView orderView) {
        this.orderView = orderView;

        orderDao = new OrderDao();
        orderDetailDao = new OrderDetailDao();

        orderView.setBtnAddActionListener(new AddButtonListener());
        orderView.setBtnUpdateActionListener(new UpdateButtonListener());
        orderView.setBtnDeleteActionListener(new DeleteButtonListener());
        orderView.setBtnRefreshActionListener(new RefreshButtonListener());
        orderView.setDetailActionListener(new DetailButtonListener());
        orderView.setBtnExportActionListener(new ExportListener());
        orderView.setBtnMaxActionListener(e -> {
            orderView.setDataToTable(orderDao.getMaxTotalMoney());
        });
        orderView.setBtnMinActionListener(e -> {
            orderView.setDataToTable(orderDao.getMinTotalMoney());
        });

        orderView.setBtnSearchByIDActionListener(e -> {
            Optional<String> orderInput = Optional.ofNullable(orderView.getIdInput());
            orderInput.ifPresent(order -> orderView.setDataToTable(orderDao.getOrderByID(order)));
        });

        orderView.setDataToTable(orderDao.getOrders());
//        orderView.setId(orderDao.getOrders().size());
    }


    public void showFrm() {
        orderView.setVisible(true);
    }

    class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Optional<Order> orderInput = Optional.ofNullable(orderView.getOrderInput());

                if(orderInput.isPresent()){
                    orderDao.addOrder(orderInput.get());
                    JOptionPane.showMessageDialog(orderView, "Thêm mới thành công");
                    orderView.clear();
                    orderView.setDataToTable(orderDao.getOrders());
                }

            } catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(orderView, ex.getMessage());
            }
        }
    }

    class UpdateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           try{
               Optional<Order> orderInput = Optional.ofNullable(orderView.getOrderInput());

               if (orderInput.isPresent()){
                   orderDao.updateOrder(orderInput.get());
                   JOptionPane.showMessageDialog(orderView, "Cập nhật thành công");
                   orderView.setDataToTable(orderDao.getOrders());
                   orderView.clear();

               }

           }catch (IllegalArgumentException ex){
               JOptionPane.showMessageDialog(orderView, ex.getMessage());
           }
        }
    }

    class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           try{
               Optional<Order> orderInput = Optional.ofNullable(orderView.getOrderInput());

               if(orderInput.isPresent()){
                   orderDao.deleteOrder(orderInput.get());
                   JOptionPane.showMessageDialog(orderView, "Xóa thành công");
                   orderView.setDataToTable(orderDao.getOrders());
                   orderView.clear();
               }
           }catch (IllegalArgumentException ex){
               JOptionPane.showMessageDialog(orderView, ex.getMessage());
           }
        }
    }

    class RefreshButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            orderView.clear();
            orderView.setDataToTable(orderDao.getOrders());
        }
    }

    class DetailButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                Optional<Order> orderInput = Optional.ofNullable(orderView.getOrderInput());

                if(orderInput.isPresent()){
                    OrderDetailController orderDetailController = new OrderDetailController(new OrderDetailView(), orderInput.get().getId());
                    orderDetailController.showFrm();
                }
            }catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(orderView, "Vui lòng chọn đơn hàng cần xem chi tiết");
            }
        }
    }

    class ExportListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Optional<Order> orderInput = Optional.ofNullable(orderView.getOrderInput());

                if(orderInput.isPresent()){
                    List<OrderDetail> list = orderDetailDao.getProductByOrderId(orderInput.get().getId());
                    if(list.isEmpty() || list == null) {
                        JOptionPane.showMessageDialog(orderView, "Đơn hàng không có sản phẩm");
                    } else{
                        ExportUtil.export(list);
                    }
                }
            }catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(orderView, "Vui lòng chọn đơn hàng cần xuất file");
            }
        }
    }

}
