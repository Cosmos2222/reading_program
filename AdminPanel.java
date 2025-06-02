package reading_program;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class AdminPanel extends JFrame {
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JTextField titleField, authorField, publisherField, priceField, ratingField;
    private static ArrayList<String[]> likedBooks = new ArrayList<>();
    private String userID;

    public static ArrayList<String[]> getLikedBooks() {
        return likedBooks;
    }

    public static void clearLikedBooks() {
        likedBooks.clear();
    }

    public AdminPanel(String userID) {
        this.userID = userID;

        setTitle("책 관리");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        String[] columns = {"제목", "저자", "출판사", "가격", "별점"};
        tableModel = new DefaultTableModel(columns, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setBounds(20, 20, 740, 200);
        add(scrollPane);

        JLabel[] labels = {
            new JLabel("제목:"), new JLabel("저자:"), new JLabel("출판사:"),
            new JLabel("가격:"), new JLabel("별점:")
        };

        JTextField[] fields = {
            titleField = new JTextField(), authorField = new JTextField(),
            publisherField = new JTextField(), priceField = new JTextField(),
            ratingField = new JTextField()
        };

        for (int i = 0; i < labels.length; i++) {
            labels[i].setBounds(50 + i * 140, 240, 60, 25);
            fields[i].setBounds(50 + i * 140, 270, 120, 25);
            add(labels[i]);
            add(fields[i]);
        }

        JButton addBtn = new JButton("추가");
        JButton editBtn = new JButton("수정");
        JButton deleteBtn = new JButton("삭제");
        JButton likeBtn = new JButton("찜하기");
        JButton reviewBtn = new JButton("감상문 작성");
        JButton backBtn = new JButton("뒤로가기");

        addBtn.setBounds(20, 320, 100, 30);
        editBtn.setBounds(140, 320, 100, 30);
        deleteBtn.setBounds(260, 320, 100, 30);
        likeBtn.setBounds(380, 320, 100, 30);
        reviewBtn.setBounds(500, 320, 120, 30);
        backBtn.setBounds(640, 320, 100, 30);

        add(addBtn);
        add(editBtn);
        add(deleteBtn);
        add(likeBtn);
        add(reviewBtn);
        add(backBtn);

        loadBooks();

        addBtn.addActionListener(e -> {
            String[] row = {
                titleField.getText(), authorField.getText(), publisherField.getText(),
                priceField.getText(), ratingField.getText()
            };
            tableModel.addRow(row);
            saveBooks();
            clearFields();
        });

        editBtn.addActionListener(e -> {
            int row = bookTable.getSelectedRow();
            if (row >= 0) {
                tableModel.setValueAt(titleField.getText(), row, 0);
                tableModel.setValueAt(authorField.getText(), row, 1);
                tableModel.setValueAt(publisherField.getText(), row, 2);
                tableModel.setValueAt(priceField.getText(), row, 3);
                tableModel.setValueAt(ratingField.getText(), row, 4);
                saveBooks();
                clearFields();
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = bookTable.getSelectedRow();
            if (row >= 0) {
                tableModel.removeRow(row);
                saveBooks();
                clearFields();
            }
        });

        likeBtn.addActionListener(e -> {
            int row = bookTable.getSelectedRow();
            if (row >= 0) {
                String[] liked = new String[5];
                for (int i = 0; i < 5; i++) {
                    liked[i] = (String) tableModel.getValueAt(row, i);
                }
                likedBooks.add(liked);
                JOptionPane.showMessageDialog(this, "찜한 책에 추가되었습니다!");
            }
        });

        reviewBtn.addActionListener(e -> {
            dispose();
            new ReviewPanel(userID).setVisible(true);
        });

        backBtn.addActionListener(e -> {
            dispose();
            new MainMenuPanel(userID).setVisible(true);
        });

        bookTable.getSelectionModel().addListSelectionListener(e -> {
            int row = bookTable.getSelectedRow();
            if (row >= 0) {
                titleField.setText((String) tableModel.getValueAt(row, 0));
                authorField.setText((String) tableModel.getValueAt(row, 1));
                publisherField.setText((String) tableModel.getValueAt(row, 2));
                priceField.setText((String) tableModel.getValueAt(row, 3));
                ratingField.setText((String) tableModel.getValueAt(row, 4));
            }
        });
    }

    private void clearFields() {
        titleField.setText("");
        authorField.setText("");
        publisherField.setText("");
        priceField.setText("");
        ratingField.setText("");
    }

    private void saveBooks() {
        try {
            BookManager.save(tableModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "파일 저장 실패");
        }
    }

    private void loadBooks() {
        try {
            BookManager.load(tableModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "파일 불러오기 실패");
        }
    }
}