import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends Frame implements ActionListener 
{
    Button a[][] = new Button[3][3];
    Button resetRound, resetGame;
    TextField tf1, tf2, tfwin,tfr;
    boolean player1Turn = true;
    int player1Point = 0, player2Point = 0 , rounds = 0; 

    public TicTacToe() 
    {
        setLayout(null);
        setSize(900, 600);
        setTitle("Tic Tac Toe");

        Panel p1 = new Panel(new GridLayout(3, 3, 2, 2));
        p1.setBounds(50, 50, 350, 350);

        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                a[i][j] = new Button("");
                a[i][j].setFont(new Font("Arial", Font.BOLD, 36));
                a[i][j].addActionListener(this);
                p1.add(a[i][j]);
            }
        }

        Label lb1 = new Label("Player 1:");
        Label lb2 = new Label("Player 2:");
        Label lbwin = new Label("Winner:");
        Label lbr = new Label("Rounds");

        lb1.setFont(new Font("Arial", Font.BOLD, 16));
        lb2.setFont(new Font("Arial", Font.BOLD, 16));
        lbwin.setFont(new Font("Arial", Font.BOLD, 16));
        lbr.setFont(new Font("Arial",Font.BOLD,16));

        lbwin.setBounds(450, 160, 100, 30);
        lb1.setBounds(450, 80, 100, 30);
        lb2.setBounds(450, 120, 100, 30);
        lbr.setBounds(450,200,100,30);

        tf1 = new TextField("0");
        tf2 = new TextField("0");
        tfr = new TextField("0");

        tf1.setBounds(550, 80, 80, 30);
        tf2.setBounds(550, 120, 80, 30);
        tfr.setBounds(550,200,80,30);

        tf1.setFont(new Font("Arial", Font.BOLD, 16));
        tf2.setFont(new Font("Arial", Font.BOLD, 16));

        tf1.setEditable(false);
        tf2.setEditable(false);
        tfr.setEditable(false);

        tfwin = new TextField("");
        tfwin.setBounds(550, 160, 120, 30);
        tfwin.setFont(new Font("Arial", Font.BOLD, 16));
        tfwin.setEditable(false);

        tfr.setFont(new Font("Arial", Font.BOLD, 16));


        resetGame = new Button("Reset Game");
        resetRound = new Button("Reset Round");

        resetGame.setBounds(450, 260, 110, 40);
        resetRound.setBounds(570, 260, 110, 40);

        resetGame.setBackground(Color.RED);
        resetGame.setForeground(Color.WHITE);
        resetRound.setBackground(Color.RED);
        resetRound.setForeground(Color.WHITE);

        resetRound.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent ae) 
            {
                resetRound();
            }
        });

        resetGame.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent ae) 
            {
                resetGame();
            }
        });




        add(lb1);
        add(lb2);
        add(lbwin);
        add(tf1);
        add(tf2);
        add(tfwin);
        add(resetGame);
        add(resetRound);
        add(p1);
        add(lbr);
        add(tfr);

        setVisible(true);
    }

    void highlight(Button b1 , Button b2, Button b3)
    {
        b1.setBackground(Color.GREEN);
        b2.setBackground(Color.GREEN);
        b3.setBackground(Color.GREEN);

    }

    boolean checkWin() 
    {
        for (int i = 0; i < 3; i++) 
        {
            if (a[i][0].getLabel().equals(a[i][1].getLabel()) &&
                a[i][1].getLabel().equals(a[i][2].getLabel()) &&
                !a[i][0].getLabel().equals("")) 
            {
                highlight(a[i][0], a[i][1], a[i][2]);
                return true;
            }
          else  if (a[0][i].getLabel().equals(a[1][i].getLabel()) &&
                a[1][i].getLabel().equals(a[2][i].getLabel()) &&
                !a[0][i].getLabel().equals("")) 
            {
                highlight(a[0][i], a[1][i], a[2][i]);
                return true;
            }
        }
       if (a[0][0].getLabel().equals(a[1][1].getLabel()) &&
            a[1][1].getLabel().equals(a[2][2].getLabel()) &&
            !a[0][0].getLabel().equals("")) 
        {
            highlight(a[0][0], a[1][1], a[2][2]);
            return true;
        }
      else  if (a[0][2].getLabel().equals(a[1][1].getLabel()) &&
            a[1][1].getLabel().equals(a[2][0].getLabel()) &&
            !a[0][2].getLabel().equals("")) 
        {
            highlight(a[0][2], a[1][1], a[2][0]);

            return true;
        }
        return false;
    }

    boolean isBoardFull() 
    {
        for (Button[] row : a) 
        {
            for (Button btn : row) 
            {
                if (btn.getLabel().equals("")) 
                {
                    return false;
                }
            }
        }
        return true;
    }

    void disableButtons() 
    {
        for (Button[] row : a) 
        {
            for (Button btn : row) 
            {
                btn.setEnabled(false);
            }
        }
    }

    void resetRound() 
    {
        for (Button[] row : a) 
        {
            for (Button btn : row) 
            {
                btn.setLabel("");
                btn.setEnabled(true);
                btn.setBackground(null);

            }
        }
        tfwin.setText("");
        player1Turn = true;
        rounds++;
        tfr.setText(String.valueOf(rounds));

    }

    void resetGame() 
    {
        resetRound();
        player1Point = 0;
        player2Point = 0;
        tf1.setText("0");
        tf2.setText("0");
    }

    public void actionPerformed(ActionEvent ae) 
    {
        Button btnclicked = (Button) ae.getSource();
        if (!btnclicked.getLabel().equals(""))
        {
            return;
        }

        btnclicked.setLabel(player1Turn ? "X" : "O");
        btnclicked.setForeground(player1Turn ? Color.RED : Color.BLUE);

        if (checkWin()) 
        {
            if (player1Turn) 
            {
                player1Point++;
                tf1.setText(String.valueOf(player1Point));
                tfwin.setText("Player 1");
            } 
            else 
            {
                player2Point++;
                tf2.setText(String.valueOf(player2Point));
                tfwin.setText("Player 2");
            }
            disableButtons();
        } 
        else if (isBoardFull()) 
        {
            tfwin.setText("Draw");
            disableButtons();
        } 
        else 
        {
            player1Turn = !player1Turn;
        }
    }

    public static void main(String[] args) 
    {
        new TicTacToe();
    }
}
