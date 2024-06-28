package Ui;

import asciiPanel.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SelectCharacterClass extends JPanel {
    CardLayout cardLayout;
    private boolean start_game = false;
    private String selectedClassName;
    private List<String> classNames;
    private int currentClassIndex;

    public SelectCharacterClass() {
        JFrame frame = new JFrame("Character Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        setLayout(cardLayout);
        setBackground(Color.BLACK); // Set the background color of the main JPanel to black

        ArrayList<String> classNames = new ArrayList<>();
        classNames.add("Healer");
        classNames.add("Guerreiro");
        classNames.add("MagoElemental");
        classNames.add("Rogue");
        currentClassIndex = 0;
        add(createClassPanel("Healer", "Um curandeiro que usa magias para curar aliados e causar dano aos inimigos.", "Assets/healer.png"), "Healer");
        add(createClassPanel("Guerreiro", "Um guerreiro que usa sua forca fisica para causar dano aos inimigos e proteger aliados.", "Assets/knight.png"), "Guerreiro");
        add(createClassPanel("MagoElemental", "Um mago que usa magias para causar dano aos inimigos.", "Assets/MagoArt.png"), "MagoElemental");
        add(createClassPanel("Rogue", "Um ladrao que usa furtividade e agilidade para causar dano aos inimigos.", "Assets/rogue.png"), "Rogue");

        // Add key bindings
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("Q"), "previous");
        actionMap.put("previous", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(SelectCharacterClass.this);
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("E"), "next");
        actionMap.put("next", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(SelectCharacterClass.this);
            }
        });

        Font fontePalavrasGrande = null;
        Font fontePalavrasPequeno = null;
        Font fonteTitulos = null;

        try {
            InputStream is = getClass().getResourceAsStream("/Fonts/runescape_uf.ttf");
            fontePalavrasGrande = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(33 * 1.33f);

            is = getClass().getResourceAsStream("/Fonts/runescape_uf.ttf");
            fontePalavrasPequeno = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);

            is = getClass().getResourceAsStream("/Fonts/alagard.ttf");
            fonteTitulos = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(100f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // Create title label
        JLabel titleLabel = new JLabel("Selecionar Personagem");
        titleLabel.setFont(fontePalavrasGrande);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Border emptyBorder = new EmptyBorder(10, 0, 10, 0);
        Border lineBorder = new MatteBorder(0, 0, 10, 0, Color.WHITE);

        // Combine the borders using CompoundBorder
        CompoundBorder compoundBorder = new CompoundBorder(lineBorder, emptyBorder);
        titleLabel.setBorder(compoundBorder);

        // Create instruction labels
        JLabel leftInstructionLabel = new JLabel("[Q] Anterior");
        leftInstructionLabel.setFont(fontePalavrasPequeno);
        leftInstructionLabel.setForeground(Color.WHITE);
        leftInstructionLabel.setBorder(new EmptyBorder(0, 20, 0, 20));

        JLabel rightInstructionLabel = new JLabel("[E] Proximo");
        rightInstructionLabel.setFont(fontePalavrasPequeno);
        rightInstructionLabel.setForeground(Color.WHITE);
        rightInstructionLabel.setBorder(new EmptyBorder(0, 20, 0, 20));

        // Create a main panel to hold everything
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK); // Set background color of the main panel to black
        mainPanel.add(this, BorderLayout.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(leftInstructionLabel, BorderLayout.WEST);
        mainPanel.add(rightInstructionLabel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createClassPanel(String className, String classDescription, String imagePath) {
        Font fontePalavrasGrande = null;
        Font fontePalavrasPequeno = null;
        Font fonteTitulos = null;

        try {
            InputStream is = getClass().getResourceAsStream("/Fonts/runescape_uf.ttf");
            fontePalavrasGrande = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(33 * 1.33f);

            is = getClass().getResourceAsStream("/Fonts/runescape_uf.ttf");
            fontePalavrasPequeno = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(20f);

            is = getClass().getResourceAsStream("/Fonts/alagard.ttf");
            fonteTitulos = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(100f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK); // Set background color of class panel to black
        Border emptyBorder = new EmptyBorder(50, 100, 50, 100);
        Border lineBorder = new MatteBorder(0, 10, 0, 10, Color.WHITE);

        // Combine the borders using CompoundBorder
        CompoundBorder compoundBorder = new CompoundBorder(lineBorder, emptyBorder);
        panel.setBorder(compoundBorder);

        JLabel nameLabel;

        if(className.equals("MagoElemental"))
            nameLabel = new JLabel("Mago Elemental");

        nameLabel = new JLabel(className);
        nameLabel.setFont(fontePalavrasGrande);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameLabel.getMinimumSize().height));

        // Replace "imagePath" with the path to the image file
        ImageIcon imageIcon = new ImageIcon(imagePath);

        Image img = imageIcon.getImage();
        Image resized = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resized);

        JLabel imageLabel = new JLabel(resizedIcon);
        imageLabel.setBorder(new LineBorder(Color.WHITE, 5));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextPane descriptionArea = new JTextPane();
        descriptionArea.setText(classDescription);
        descriptionArea.setFont(fontePalavrasPequeno);
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBackground(Color.BLACK);
        descriptionArea.setEditable(false);
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionArea.setFocusable(false);

        StyledDocument doc = descriptionArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        Border emptyBorderDescription = new EmptyBorder(17, 0, 0, 0);
        Border lineBorderDescription = new LineBorder(Color.WHITE, 2, true);

        compoundBorder = new CompoundBorder(lineBorderDescription, emptyBorderDescription);
        descriptionArea.setBorder(compoundBorder);

        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.BLACK);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        Border emptyBorderButton = new EmptyBorder(10, 10, 10, 10);
        Border lineBorderButton = new LineBorder(Color.WHITE, 2, true);
        compoundBorder = new CompoundBorder(lineBorderButton, emptyBorderButton);
        startButton.setBorder(compoundBorder);
        startButton.setFocusable(false);
        startButton.setContentAreaFilled(false);
        startButton.setFont(fontePalavrasGrande);

        panel.add(Box.createVerticalGlue());
        panel.add(nameLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(imageLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(descriptionArea);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(startButton);
        panel.add(Box.createVerticalGlue());
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassName = className;
                start_game = true;
            }
        });

        return panel;
    }

    public String getSelectedClassName() {
        return selectedClassName;
    }
    public boolean isReady(){
        return start_game;
    }
}
