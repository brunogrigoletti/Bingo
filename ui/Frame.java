package ui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import game.Bingo;

public class Frame{
    Bingo bingo;
    String roundNumber;
    JFrame frame;
    JPanel buttonsPanel;
    JPanel cardButtonsPanel1;
    JPanel cardButtonsPanel2;
    JPanel nextButtonPanel;
    JPanel roundNumberPanel;
    JButton[] cardButtons;
    JButton roundButton;

    public Frame(){
        bingo = new Bingo();
        roundNumber = String.valueOf(bingo.newRoundNumber());
    }

    public void newFrame(){
        frame = new JFrame("Bingo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(940, 650);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());

        buttonsPanel = new JPanel(new FlowLayout());
        cardButtonsPanel1 = new JPanel(new GridLayout(5, 5));
        cardButtonsPanel2 = new JPanel(new GridLayout(5, 5));
        cardButtonsPanel1.setBackground(Color.BLACK);
        cardButtonsPanel2.setBackground(Color.BLACK);
        this.addButtons(cardButtonsPanel1);
        this.addButtons(cardButtonsPanel2);
        buttonsPanel.add(cardButtonsPanel1, BorderLayout.WEST);
        buttonsPanel.add(cardButtonsPanel2, BorderLayout.EAST);

        roundNumberPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 48));
                g.drawString(roundNumber, getWidth()/2-g.getFontMetrics().stringWidth(roundNumber)/2,getHeight()/2+g.getFontMetrics().getHeight()/4);
            }
        };
        roundNumberPanel.setPreferredSize(new Dimension(1000, 100));

        nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nextButtonPanel.setBackground(Color.BLACK);
        roundButton = new JButton("Next");
        roundButton.setBackground(Color.BLACK);
        roundButton.setForeground(Color.WHITE);
        roundButton.setPreferredSize(new Dimension(100, 30));
        roundButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                roundNumber = String.valueOf(bingo.newRoundNumber());
                roundNumberPanel.repaint();
            }
        });
        nextButtonPanel.add(roundButton);
        nextButtonPanel.setPreferredSize(new Dimension(50, 50));
        
        frame.add(roundNumberPanel, BorderLayout.NORTH);
        frame.add(buttonsPanel, BorderLayout.CENTER);
        frame.add(nextButtonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void addButtons(JPanel panel){
        cardButtons = new JButton[25];
        for (int i=1; i<=25; i++){
            cardButtons[i-1] = new JButton(String.valueOf(bingo.newCardNumber()));
            cardButtons[i-1].setPreferredSize(new Dimension(90, 90));
            cardButtons[i-1].setBackground(Color.BLACK);
            cardButtons[i-1].setForeground(Color.WHITE);
            cardButtons[i-1].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    if (button.getText().equals(roundNumber)){
                        button.setBackground(Color.RED);
                    }
                    if (Arrays.stream(cardButtonsPanel1.getComponents()).filter(component -> component instanceof JButton).map(component -> (JButton) component).filter(b -> b.getBackground().equals(Color.RED)).count()==25){
                        JOptionPane.showMessageDialog(button,"Você venceu!");
                    }           
                }
            });
            panel.add(cardButtons[i-1]);
        }
    }
}