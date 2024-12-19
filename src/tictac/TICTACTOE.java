/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictac;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TICTACTOE {
    int bwidth = 600;
    int bheight = 650;
    JFrame frame = new JFrame("Tic Tac Toe");
    JFrame frame2 = new JFrame("Login");
    JLabel label1 = new JLabel("Enter Username:");
    JTextField textField = new JTextField();
    JLabel pass = new JLabel("Enter Password:");
    JPasswordField textField2 = new JPasswordField();
    JButton loginButton = new JButton("Login");
    JLabel textlabel = new JLabel();
    JPanel textpanel = new JPanel();
    JPanel boardpanel = new JPanel();
    JPanel controlPanel = new JPanel();
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameover = false;
    JButton[][] board = new JButton[3][3];
    int turns = 0;
    public TICTACTOE() {
        setupLoginFrame();
        setupGameFrame();
        frame2.setVisible(true);
    }

    private void setupLoginFrame() {
        frame2.setSize(300, 200);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLayout(null);
        frame2.setLocationRelativeTo(null);
        label1.setBounds(20, 20, 120, 25);
        textField.setBounds(150, 20, 120, 25);
        pass.setBounds(20, 60, 120, 25);
        textField2.setBounds(150, 60, 120, 25);
        loginButton.setBounds(100, 120, 100, 30);

        frame2.add(label1);
        frame2.add(textField);
        frame2.add(pass);
        frame2.add(textField2);
        frame2.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(textField2.getPassword());
                if (!username.isEmpty() && !password.isEmpty()) {
                    frame2.setVisible(false);
                    frame.setVisible(true);
                    
                } else {
                    JOptionPane.showMessageDialog(frame2, "Please enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setupGameFrame() {
        frame.setSize(bwidth, bheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        textlabel.setBackground(Color.DARK_GRAY);
        textlabel.setForeground(Color.WHITE);
        textlabel.setFont(new Font("Arial", Font.BOLD, 50));
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setText("TIC-TAC-TOE");
        textpanel.setLayout(new BorderLayout());
        textpanel.add(textlabel);
        frame.add(textpanel, BorderLayout.NORTH);
        boardpanel.setLayout(new GridLayout(3, 3));
        boardpanel.setBackground(Color.BLACK);
        frame.add(boardpanel, BorderLayout.CENTER);
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton bb = new JButton();
                board[r][c] = bb;
                boardpanel.add(bb);
                bb.setBackground(Color.MAGENTA);
                bb.setForeground(Color.WHITE);
                bb.setFont(new Font("Arial", Font.BOLD, 50));
                bb.setFocusable(false);
                bb.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameover) 
                       return;
                        JButton bb = (JButton) e.getSource();
                        if (bb.getText().equals("")) {
                            bb.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameover) {
                                currentPlayer = currentPlayer.equals(playerX) ? playerO : playerX;
                                textlabel.setText(currentPlayer + " TURN");
                            }
                        }
                    }
                });
            }
        }
        controlPanel.setLayout(new GridLayout(1, 2));
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");
        resetButton.setFont(new Font("Arial", Font.BOLD, 30));
        exitButton.setFont(new Font("Arial", Font.BOLD, 30));
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        controlPanel.add(resetButton);
        controlPanel.add(exitButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

    }

    void checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals(board[i][1].getText()) &&
                board[i][1].getText().equals(board[i][2].getText()) &&
                !board[i][0].getText().equals("")) {
                gameover = true;
                setWinner(board[i][0], board[i][1], board[i][2]);
                return;
            }
            if (board[0][i].getText().equals(board[1][i].getText()) &&
                board[1][i].getText().equals(board[2][i].getText()) &&
                !board[0][i].getText().equals("")) {
                gameover = true;
                setWinner(board[0][i], board[1][i], board[2][i]);
                return;
            }
        }
        if (board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText()) &&
            !board[0][0].getText().equals("")) {
            gameover = true;
            setWinner(board[0][0], board[1][1], board[2][2]);
            return;
        }
        if (board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText()) &&
            !board[0][2].getText().equals("")) {
            gameover = true;
            setWinner(board[0][2], board[1][1], board[2][0]);
            return;
        }
        if (turns == 9 && !gameover) {
            gameover = true;
            setTie();
        }
    }

    void setWinner(JButton... tiles) {
        for (JButton tile : tiles) {
            tile.setBackground(Color.GREEN);
            tile.setForeground(Color.RED);
        }
        textlabel.setText(currentPlayer + " is the winner");
    }

    void setTie() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setBackground(Color.GRAY);
                board[r][c].setForeground(Color.YELLOW);
            }
        }
        textlabel.setText("It is a tie");
    }

    void resetGame() {
        textlabel.setText("Resetting,start he new game");
        for (int r = 0; r < 3; r++) {
            gameover=false;
            turns=0;
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(Color.MAGENTA);
                board[r][c].setForeground(Color.WHITE);
            }
       
        }
    }
}
 

