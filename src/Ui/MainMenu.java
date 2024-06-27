package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private boolean game_start;

    public MainMenu() {
        this.game_start = false;
        JFrame frame = new JFrame("Crypt Crawler");
        // Criando tela e background
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.getContentPane().setBackground(java.awt.Color.BLACK);
        frame.setLayout(null);

        // Titulo
        frame.setTitle("Crypt Crawler");

        // Botao start
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(540, 320, 200, 80);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));

        // Texto
        JLabel text = new JLabel("Crypt Crawler");
        text.setForeground(java.awt.Color.WHITE);
        text.setFont(new Font("Arial", Font.PLAIN, 50));
        int labelWidth = 500;
        int labelX = (frame.getWidth() - labelWidth) / 2;
        text.setBounds(labelX, 200, 500, 80);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(text);

        frame.setVisible(true);
        // Add an ActionListener to the button
        startButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                game_start = true;
            }
        });

        frame.add(startButton);
    }

    public boolean getGameStart(){
        return game_start;
    }
}