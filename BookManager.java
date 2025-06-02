package reading_program;

import javax.swing.table.DefaultTableModel;
import java.io.*;

public class BookManager {
    private static final String FILE_PATH = "books.txt";

    public static void save(DefaultTableModel model) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    writer.write(model.getValueAt(i, j).toString());
                    if (j < model.getColumnCount() - 1) writer.write("|");
                }
                writer.newLine();
            }
        }
    }

    public static void load(DefaultTableModel model) throws IOException {
        model.setRowCount(0); // clear existing
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                model.addRow(line.split("\\|"));  // FIX: escape pipe correctly
            }
        }
    }
}