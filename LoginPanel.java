package reading_program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JFrame {
    private JTextField txtID;
    private JPasswordField txtPW;

    public static String staticID = "user";
    public static String staticPW = "1234";
    public static String adminID = "admin";
    public static String adminPW = "admin";

    public LoginPanel() {
        setTitle("Login");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblID = new JLabel("ID:");
        lblID.setBounds(100, 80, 80, 25);
        add(lblID);

        txtID = new JTextField();
        txtID.setBounds(180, 80, 150, 25);
        add(txtID);

        JLabel lblPW = new JLabel("Password:");
        lblPW.setBounds(100, 120, 80, 25);
        add(lblPW);

        txtPW = new JPasswordField();
        txtPW.setBounds(180, 120, 150, 25);
        add(txtPW);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(180, 160, 100, 30);
        add(loginBtn);

        JButton signUpBtn = new JButton("Sign Up");
        signUpBtn.setBounds(180, 200, 100, 30);
        add(signUpBtn);

        loginBtn.addActionListener(e -> {
            String inputID = txtID.getText();
            String inputPW = new String(txtPW.getPassword());

            if ((inputID.equals(staticID) && inputPW.equals(staticPW)) ||
                (inputID.equals(adminID) && inputPW.equals(adminPW))) {
                JOptionPane.showMessageDialog(this, "로그인에 성공하셨습니다.");
                dispose();
                new MainMenuPanel(inputID).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "ID와 PW를 확인해주세요");
                txtID.setText("");
                txtPW.setText("");
            }
        });

        signUpBtn.addActionListener(e -> {
            dispose();
            new SignUpPanel().setVisible(true);
        });
    }
}