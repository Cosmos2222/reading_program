package reading_program;

import javax.swing.*;
import java.awt.event.*;

public class MainMenuPanel extends JFrame {
    private String userID;

    public MainMenuPanel(String userID) {
        this.userID = userID;
        setTitle("Main Menu - " + userID);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("환영합니다, " + userID);
        welcomeLabel.setBounds(200, 50, 300, 30);
        add(welcomeLabel);

        JButton bookManageBtn = new JButton("책 관리");
        bookManageBtn.setBounds(200, 100, 150, 30);
        add(bookManageBtn);

        JButton myPageBtn = new JButton("마이페이지");
        myPageBtn.setBounds(200, 150, 150, 30);
        add(myPageBtn);

        JButton logoutBtn = new JButton("로그아웃");
        logoutBtn.setBounds(200, 200, 150, 30);
        add(logoutBtn);

        bookManageBtn.addActionListener(e -> {
            dispose();
            new AdminPanel(userID).setVisible(true);
        });

        myPageBtn.addActionListener(e -> {
            dispose();
            new MyPagePanel(userID).setVisible(true);
        });

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginPanel().setVisible(true);
        });
    }
}