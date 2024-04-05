import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

// fucked beyond repair

public class Main {
    public static void main(String[] args) {

        char[][] grid = {
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };

        char player = 'X';

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  // Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //creating gui

        JFrame frame = new JFrame();

        JButton b00 = button();
        JButton b01 = button();
        JButton b02 = button();
        JButton b10 = button();
        JButton b11 = button();
        JButton b12 = button();
        JButton b20 = button();
        JButton b21 = button();
        JButton b22 = button();

        frame.add(b00);
        frame.add(b01);
        frame.add(b02);
        frame.add(b10);
        frame.add(b11);
        frame.add(b12);
        frame.add(b20);
        frame.add(b21);
        frame.add(b22);

        frame.setLayout(new GridLayout(3, 3, 3, 3));
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public static JButton button() {
        JButton button = new JButton();

        button.setFocusable(false);

        return button;
    }

}