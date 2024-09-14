import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/*
            First of all.
            I do want to make it  very clear that I am not proud of this creation.
            Infact, I hate every singular line of it. But I am too far in this to stop now.

            At this point, this is fucked beyond repair.
*/

public class Main {

    static JButton b00;
    static JButton b01;
    static JButton b02;
    static JButton b10;
    static JButton b11;
    static JButton b12;
    static JButton b20;
    static JButton b21;
    static JButton b22;

    static char currentTurn = 'O';
    static char[][] grid = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'}
    };

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  // Windows Look and feel
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //creating gui

        JFrame frame = new JFrame("Tic Tac Toe");

        b00 = button(new JButton(""),0,0);
        b01 = button(new JButton(""),0,1);
        b02 = button(new JButton(""),0,2);
        b10 = button(new JButton(""),1,0);
        b11 = button(new JButton(""),1,1);
        b12 = button(new JButton(""),1,2);
        b20 = button(new JButton(""),2,0);
        b21 = button(new JButton(""),2,1);
        b22 = button(new JButton(""),2,2);

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

    static void turn(JButton button, int i1,int i2) {

        if(grid[i1][i2] == '-') {
            grid[i1][i2] = currentTurn;

            button.setText(Character.toString(currentTurn));

            System.out.println(Arrays.deepToString(grid).replace("], ", "]\n"));

            if(checkGrid()) {

                String[] typeOptions = {"Play Again","Close"};
                int choice = JOptionPane.showOptionDialog(null, Character.toString(currentTurn)+" has won the game.", "The worlds worst Tic Tac Toe", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, typeOptions, 0);

                if(choice == 0) {
                    resetGame();
                }
                else {System.exit(420);}

            }

            else {if(currentTurn == 'O') {currentTurn = 'X';} else if(currentTurn == 'X') {currentTurn = 'O';}}

        }

    }

    static boolean checkGrid() {

        // This was just straight up ripped from my worlds worst connect 4, but I unfucked it.

        int diagMode = 0;
        int indexX = 0;
        int indexY = 0;
        int spots = 0;
        boolean resetSpots = false;
        boolean win = false;

        // 13.09.2024 20:05 | I give up for today, I hate that logic.
        // 14.09.2024 14:08 | The problem was incredibly simple, crazy what a lil sleep can do. Oh yea, now I have to fix this garbage known as my diagonal logic.
        // 14.09.2024 14:23 | The diagonal logic is genuinely fucked beyond repair. I am rewriting it. (because it desperately needs that rewrite)
        // 14.09.2024 14:31 | It works. Nice.
        // 14.09.2024 14:45 | Gonna start writing a check for a tie.

        for(int i = 0; i<=8; i++) {

            int debugIX = indexX;
            int debugIY = indexY;

            if (grid[indexY][indexX] == currentTurn) {spots++;}
            else {spots = 0;}
            if (spots >= 3) {return true;}
            if (indexX == 2) {indexY++; indexX = 0; spots = 0;}
            else {indexX++;}

            System.out.println("[HORIZONTAL] S: "+spots+" | X: "+debugIX+" | Y: "+debugIY);
        }

        indexX = 0;
        indexY = 0;
        spots = 0;

        System.out.println();

        for(int i = 0; i<=8; i++) {

            int debugIX = indexX;
            int debugIY = indexY;

            if (grid[indexY][indexX] == currentTurn) {spots++;}
            else {spots = 0;}
            if (spots >= 3) {return true;}
            if (indexY == 2) {indexX++; indexY = 0; spots = 0;}
            else {indexY++;}

            System.out.println("[VERTICAL] S: "+spots+" | X: "+debugIX+" | Y: "+debugIY);
        }

        indexX = 0;
        indexY = 0;
        spots = 0;

        while (true) {

            if (grid[indexY][indexX] == currentTurn) {spots++;}
            else {spots = 0;}
            if (spots >= 3) {return true;}
            if (spots > 0) {indexX++; indexY++;}
            else {break;}

        }

        indexX = 2;
        indexY = 0;
        spots = 0;

        while(true) {

            if (grid[indexY][indexX] == currentTurn) {spots++;}
            else {spots = 0;}
            if (spots >= 3) {return true;}
            if (spots > 0) {indexX--; indexY++;}
            else {break;}

        }

        boolean tie = true;

        indexX = 0;
        indexY = 0;

        for (int i = 0; i<=8; i++) {

            if (grid[indexY][indexX] == '-') {tie = false; break;}
            if (indexX == 2) {indexY++; indexX = 0;}
            else {indexX++;}

        }

        if (tie) {

            String[] typeOptions = {"Play Again","Close"};
            int choice = JOptionPane.showOptionDialog(null, "Nobody has won the game. It's a tie.", "The worlds worst Tic Tac Toe", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, typeOptions, 0);

            if(choice == 0) {
                resetGame();
            }
            else {System.exit(420);}

        }

        return false;
    }

    static void resetGame() {

        grid = new char[][]{
                {'-', '-', '-'},
                {'-', '-', '-'},
                {'-', '-', '-'}
        };

        b00.setText("");
        b01.setText("");
        b02.setText("");
        b10.setText("");
        b11.setText("");
        b12.setText("");
        b20.setText("");
        b21.setText("");
        b22.setText("");

    }

    public static JButton button(JButton button, int i1,int i2) {

        button.setFocusable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turn(button,i1,i2);
            }
        });

        return button;
    }

}