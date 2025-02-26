package vn.viettuts.qlsv.controller;

import vn.viettuts.qlsv.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {
    private final MainMenuView mainMenuView;


    public MainMenuController(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;

        mainMenuView.setBtnProduct(new ProductListener());
        mainMenuView.setBtnLogout(new LogoutListener());
        mainMenuView.setBtnExporter(new ExporterListener());
        mainMenuView.setBtnStatics(new StaticListener());

    }

    public void showMainMenuView() {
        mainMenuView.setVisible(true);
    }

    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int result = JOptionPane.showConfirmDialog(mainMenuView, "Bạn có chắc chắn muốn đăng xuất không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if(result != JOptionPane.YES_OPTION) {
                return;
            }

            LoginController loginController = new LoginController(new Login());
            loginController.showLoginView();

            mainMenuView.dispose();
        }
    }

    class ProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductController productController = new ProductController(new ProductView());
            productController.showFrm();
        }
    }

    class ExporterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
          OrderController orderController = new OrderController(new OrderView());
          orderController.showFrm();
        }
    }

    class StaticListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Chart chart = new Chart();
            chart.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            chart.pack();
            chart.setLocationRelativeTo(null);
            chart.setVisible(true);
        }
    }
}
