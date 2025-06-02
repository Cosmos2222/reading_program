package reading_program;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class UIStyle {
    public static final Font LABEL_FONT = new Font("Malgun Gothic", Font.BOLD, 14);
    public static final Font FIELD_FONT = new Font("Malgun Gothic", Font.PLAIN, 13);
    public static final Font BUTTON_FONT = new Font("Malgun Gothic", Font.BOLD, 13);

    public static void applyLabelStyle(JLabel label) {
        label.setFont(LABEL_FONT);
    }

    public static void applyFieldStyle(JTextField field) {
        field.setFont(FIELD_FONT);
    }

    public static void applyFieldStyle(JPasswordField field) {
        field.setFont(FIELD_FONT);
    }

    public static void applyButtonStyle(JButton button) {
        button.setFont(BUTTON_FONT);
    }
}