package reading_program;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class ReviewPanel extends JFrame {
    public ReviewPanel(String userID) {
        setTitle("독서 감상문 작성");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("책 제목:");
        label.setBounds(50, 30, 100, 25);
        add(label);
        JTextField titleField = new JTextField();
        titleField.setBounds(130, 30, 300, 25);
        add(titleField);

        JLabel reviewLabel = new JLabel("감상문:");
        reviewLabel.setBounds(50, 70, 100, 25);
        add(reviewLabel);

        JTextArea reviewArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(reviewArea);
        scrollPane.setBounds(130, 70, 300, 200);
        add(scrollPane);

        JButton saveBtn = new JButton("저장");
        saveBtn.setBounds(130, 290, 100, 30);
        add(saveBtn);

        JButton backBtn = new JButton("뒤로가기");
        backBtn.setBounds(250, 290, 100, 30);
        add(backBtn);

        saveBtn.addActionListener(e -> {
            String title = titleField.getText();
            String review = reviewArea.getText();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(userID + "_review.txt", true))) {
                writer.write("[" + title + "]\n" + review + "\n---\n");
                JOptionPane.showMessageDialog(this, "감상문 저장 완료!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "저장 중 오류 발생");
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new MainMenuPanel(userID).setVisible(true);
        });
    }
}