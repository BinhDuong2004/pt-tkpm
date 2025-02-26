package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import vn.viettuts.qlsv.dao.UserDao;
import vn.viettuts.qlsv.entity.User;
import vn.viettuts.qlsv.view.Login;
import vn.viettuts.qlsv.view.MainMenuView;
import vn.viettuts.qlsv.view.ProductView;

import javax.swing.*;

public class LoginController {
    private final UserDao userDao;
    private final Login loginView;


    public LoginController(Login view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }


    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Optional<User> getUser = Optional.ofNullable(loginView.getUserInput());
                if (getUser.isPresent()) {
                    if(userDao.checkUser(getUser.get())){
                        JOptionPane.showMessageDialog(loginView, "Đăng nhập thành công!");
                        MainMenuView mainMenuView = new MainMenuView();
                        MainMenuController mainMenuController = new MainMenuController(mainMenuView);
                        mainMenuController.showMainMenuView();
                        loginView.dispose();
                    } else {
                        JOptionPane.showMessageDialog(loginView, "Tài khoản hoặc mật khẩu không đúng!");
                    }
                }
            } catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(loginView, ex.getMessage());
            }
        }
    }
}
