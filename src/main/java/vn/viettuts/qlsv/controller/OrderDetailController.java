package vn.viettuts.qlsv.controller;

import vn.viettuts.qlsv.dao.OrderDao;
import vn.viettuts.qlsv.dao.OrderDetailDao;
import vn.viettuts.qlsv.dao.ProductDao;
import vn.viettuts.qlsv.entity.OrderDetail;
import vn.viettuts.qlsv.entity.Product;
import vn.viettuts.qlsv.view.OrderDetailView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class OrderDetailController {
    private final OrderDetailView orderDetailView;
    private final OrderDetailDao orderDetailDao;
    private final ProductDao productDao;
    private String id;

    public OrderDetailController(OrderDetailView orderDetailView, String id) {
        this.orderDetailView = orderDetailView;
        this.orderDetailDao = new OrderDetailDao();
        this.productDao = new ProductDao();
        this.id = id;
        orderDetailView.setDataToTable(orderDetailDao.getProductByOrderId(id));
        orderDetailView.setCbProductId(productDao.getAllProductsId());
        orderDetailView.setTxtOrderId(this.id);
        orderDetailView.setTxtId(orderDetailDao.getId(id));

        orderDetailView.setBtnAddActionListener(new AddOrderDetailListener());
        orderDetailView.setBtnDeleteActionListener(new DeleteOrderDetailListener());
        orderDetailView.setBtnRefreshActionListener(new RefreshOrderDetailListener());
        orderDetailView.setCbProductIdActionListener(new CbProductIdListener());
        orderDetailView.setBtnUpdateActionListener(new UpdateDetailListener());

    }

    public void showFrm() {
        orderDetailView.setVisible(true);
    }

    class AddOrderDetailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Optional<OrderDetail> orderDetailInput = Optional.ofNullable(orderDetailView.getOrderDetailInput());
                Optional<String> totalRemaining = Optional.ofNullable(orderDetailView.getRemaining());

                if (orderDetailInput.isPresent() && totalRemaining.isPresent()) {
                    if (Integer.parseInt(totalRemaining.get()) < Integer.parseInt(orderDetailInput.get().getProductQuantity())) {
                        JOptionPane.showMessageDialog(orderDetailView, "Số lượng sản phẩm trong kho không đủ");
                    } else {
                        orderDetailDao.add(orderDetailInput.get());
                        JOptionPane.showMessageDialog(orderDetailView, "Thêm mới thành công");
                        orderDetailView.clear(orderDetailDao.getId(id));
                        orderDetailView.setDataToTable(orderDetailDao.getProductByOrderId(id));
                    }
                }


            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(orderDetailView, ex.getMessage());
            }
        }
    }


    class DeleteOrderDetailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Optional<OrderDetail> orderDetailInput = Optional.ofNullable(orderDetailView.getOrderDetailInput());
                if (orderDetailInput.isPresent()) {
                    int result = JOptionPane.showConfirmDialog(orderDetailView, "Bạn có chắc chắn muốn xóa không?");
                    if (result == JOptionPane.YES_OPTION) {
                        orderDetailDao.delete(orderDetailInput.get());
                        JOptionPane.showMessageDialog(orderDetailView, "Xóa thành công");
                        orderDetailView.setDataToTable(orderDetailDao.getProductByOrderId(id));
                        orderDetailView.clear(orderDetailDao.getId(id));

                    }
                }

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(orderDetailView, ex.getMessage());
            }
        }
    }

    class RefreshOrderDetailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            orderDetailView.clear(orderDetailDao.getId(id));
        }
    }

    class UpdateDetailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Optional<OrderDetail> orderDetailInput = Optional.ofNullable(orderDetailView.getOrderDetailInput());

                if (orderDetailInput.isPresent()) {
                    orderDetailDao.update(orderDetailInput.get());
                    JOptionPane.showMessageDialog(orderDetailView, "Cập nhật thành công");
                    orderDetailView.setDataToTable(orderDetailDao.getProductByOrderId(id));
                    orderDetailView.clear(orderDetailDao.getId(id));

                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(orderDetailView, ex.getMessage());
            }
        }
    }

    class CbProductIdListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String productId = orderDetailView.getCbProductId();
            Product p = productDao.getProductById(productId);
            orderDetailView.setProductDetail(p);
        }
    }
}
