import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/*
            First of all.
            I do want to make it very clear that I am not proud of this creation.
            Infact, I hate every singular line of it. But I am too far in this to stop now.

            At this point, this is fucked beyond repair.

            Actually, it isn't even that bad anymore. I think I have successfully unfucked it.

            Okay I even cleaned up the repetitive code for the buttons with for loops, I think we good now.
*/

public class Main {

    static JButton[][] b = new JButton[3][3];
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

        b[0][0] = new JButton();

        for (int i1 = 0; i1<3; i1++) {
            for (int i2 = 0; i2<3; i2++) {
                b[i1][i2] = button(new JButton(),i1,i2);
                frame.add(b[i1][i2]);
            }
        }

        frame.setLayout(new GridLayout(3, 3, 3, 3));
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
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

        int indexX = 0;
        int indexY = 0;
        int spots = 0;

        // Here you are able to wittness the process of unfucking my own copied code.

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
        for (int i1 = 0; i1<3; i1++) {
            for (int i2 = 0; i2<3; i2++) {
                b[i1][i2].setText("");
                grid[i1][i2] = '-';
            }
        }
    }

    public static JButton button(JButton button, int i1,int i2) {

        button.setFocusable(false);
        button.setFont(new Font("Arial", Font.PLAIN, 35));
        button.setForeground(new Color(26,26,26));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turn(button,i1,i2);
            }
        });

        return button;
    }

}