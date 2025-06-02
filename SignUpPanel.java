package reading_program;

import javax.swing.*;
import java.awt.event.*;

public class SignUpPanel extends JFrame {
    private JTextField txtID;
    private JPasswordField txtPW;

    public SignUpPanel() {
        setTitle("Sign Up");
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

        JButton signUpBtn = new JButton("Sign Up");
        signUpBtn.setBounds(180, 160, 100, 30);
        add(signUpBtn);

        signUpBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String newID = txtID.getText();
                String newPW = new String(txtPW.getPassword());

                if (newID.isEmpty() || newPW.isEmpty()) {
                    JOptionPane.showMessageDialog(SignUpPanel.this, "ID/PW를 모두 입력해주세요");
                    return;
                }

                LoginPanel.staticID = newID;
                LoginPanel.staticPW = newPW;
                JOptionPane.showMessageDialog(SignUpPanel.this, "회원가입 성공");
                dispose();
                new LoginPanel().setVisible(true);
            }
        });
    }
}