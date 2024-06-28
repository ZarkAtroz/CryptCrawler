package Ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainMenu extends JPanel {

    private boolean game_start;
    ImageIcon resizedIcon;

    public MainMenu() {
        this.game_start = false;
        JFrame frame = new JFrame("Crypt Crawler");
        // Criando tela e background
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);

        String imagePath = "Assets/background.png";
        File imageFile = new File(imagePath);

        ImageIcon icon = new ImageIcon(imagePath);

        Image img = icon.getImage();
        Image resized = img.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);

        this.resizedIcon = new ImageIcon(resized);
        frame.setLayout(null);

        // Titulo
        frame.setTitle("Crypt Crawler");

        Font fonteBotao = null;
        Font fonteTituloJogo = null;

        try {
            InputStream is = getClass().getResourceAsStream("/Fonts/runescape_uf.ttf");
            fonteBotao = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(33 * 1.33f);

            is = getClass().getResourceAsStream("/Fonts/alagard.ttf");
            fonteTituloJogo = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(100f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // Botao start
        JButton startButton = new JButton("Start Game");
        startButton.setBounds(540, 400, 200, 80);
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        startButton.setFocusPainted(false);
        startButton.setContentAreaFilled(false);
        if (fonteBotao != null)
            startButton.setFont(fonteBotao);

        // Texto
        JLabel text = new JLabel("Crypt Crawler");
        text.setBorder(new EmptyBorder(10, 0,0 ,0));
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        if (fonteTituloJogo != null)
            text.setFont(fonteTituloJogo);

        // Painel de fundo para o texto
        JPanel textPanel = new JPanel();
        textPanel.setBackground(new Color(0, 0, 0, 148)); // Cor de fundo
        textPanel.setBorder(new LineBorder(new Color(255, 255, 255), 5, true));
        textPanel.setBounds((frame.getWidth() - 750) / 2, 200, 750, 120);
        textPanel.setLayout(new BorderLayout());
        textPanel.add(text, BorderLayout.CENTER);

        // Adicionar componentes ao JPanel
        this.setLayout(null);
        this.add(startButton);
        this.add(textPanel);

        // Adicionar o JPanel ao JFrame
        frame.setContentPane(this);

        // Adicionar ActionListener ao botão
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game_start = true;
            }
        });

        frame.setVisible(true);
        this.repaint();
    }

    public ImageIcon getResizedIcon() {
        return resizedIcon;
    }

    public void setResizedIcon(ImageIcon resizedIcon) {
        this.resizedIcon = resizedIcon;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getResizedIcon().getImage(), 0, 0, 1280, 720, this);
    }

    public boolean getGameStart() {
        return game_start;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainMenu();
            }
        });
    }
}
