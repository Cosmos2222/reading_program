package reading_program;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class MyPagePanel extends JFrame {
    private JTextArea reviewArea;
    private JTextArea likedArea;

    public MyPagePanel(String userID) {
        setTitle("My Page - " + userID);
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel idLabel = new JLabel("ID 변경:");
        idLabel.setBounds(50, 20, 80, 25);
        add(idLabel);
        JTextField idField = new JTextField(LoginPanel.staticID);
        idField.setBounds(130, 20, 150, 25);
        add(idField);

        JLabel pwLabel = new JLabel("PW 변경:");
        pwLabel.setBounds(50, 60, 80, 25);
        add(pwLabel);
        JPasswordField pwField = new JPasswordField(LoginPanel.staticPW);
        pwField.setBounds(130, 60, 150, 25);
        add(pwField);

        JButton changeBtn = new JButton("변경하기");
        changeBtn.setBounds(300, 40, 100, 30);
        add(changeBtn);

        JButton backBtn = new JButton("뒤로가기");
        backBtn.setBounds(420, 40, 100, 30);
        add(backBtn);

        changeBtn.addActionListener(e -> {
            LoginPanel.staticID = idField.getText();
            LoginPanel.staticPW = new String(pwField.getPassword());
            JOptionPane.showMessageDialog(this, "정보가 변경되었습니다.");
        });

        backBtn.addActionListener(e -> {
            dispose();
            new MainMenuPanel(LoginPanel.staticID).setVisible(true);
        });

        JLabel reviewLabel = new JLabel("내 감상문");
        reviewLabel.setBounds(50, 110, 100, 25);
        add(reviewLabel);

        reviewArea = new JTextArea();
        JScrollPane reviewScroll = new JScrollPane(reviewArea);
        reviewScroll.setBounds(50, 140, 480, 120);
        add(reviewScroll);

        JButton deleteReviews = new JButton("감상문 전체 삭제");
        deleteReviews.setBounds(400, 110, 150, 25);
        add(deleteReviews);

        deleteReviews.addActionListener(e -> {
            File reviewFile = new File(userID + "_review.txt");
            if (reviewFile.exists()) {
                reviewFile.delete();
                reviewArea.setText("");
                JOptionPane.showMessageDialog(this, "감상문을 모두 삭제했습니다.");
            }
        });

        JLabel likedLabel = new JLabel("찜한 목록");
        likedLabel.setBounds(50, 280, 100, 25);
        add(likedLabel);

        likedArea = new JTextArea();
        likedArea.setEditable(false);
        JScrollPane likedScroll = new JScrollPane(likedArea);
        likedScroll.setBounds(50, 310, 480, 120);
        add(likedScroll);

        JButton clearLikes = new JButton("찜한 목록 초기화");
        clearLikes.setBounds(400, 280, 150, 25);
        add(clearLikes);

        clearLikes.addActionListener(e -> {
            likedArea.setText("");
            AdminPanel.clearLikedBooks();  // requires static method in AdminPanel
            JOptionPane.showMessageDialog(this, "찜한 목록을 비웠습니다.");
        });

        loadReviews(userID);
        loadLikedBooks();
    }

    private void loadReviews(String userID) {
        File reviewFile = new File(userID + "_review.txt");
        if (reviewFile.exists()) {
            try (Scanner sc = new Scanner(reviewFile)) {
                StringBuilder sb = new StringBuilder();
                while (sc.hasNextLine()) {
                    sb.append(sc.nextLine()).append("\n");
                }
                reviewArea.setText(sb.toString());
            } catch (IOException e) {
                reviewArea.setText("감상문을 불러오지 못했습니다.");
            }
        } else {
            reviewArea.setText("작성된 감상문이 없습니다.");
        }
    }

    private void loadLikedBooks() {
        StringBuilder sb = new StringBuilder();
        for (String[] book : AdminPanel.getLikedBooks()) {
            sb.append(String.join(" | ", book)).append("\n");
        }
        likedArea.setText(sb.toString());
    }
}