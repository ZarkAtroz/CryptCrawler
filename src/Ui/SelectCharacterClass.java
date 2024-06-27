package Ui;

import asciiPanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
        this.setBackground(new Color(0,0,0,0));
        this.setOpaque(false);
        frame.setSize(1280, 720);
        frame.setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        setLayout(cardLayout);
        ArrayList<String> classNames = new ArrayList<>();
        classNames.add("Healer");
        classNames.add("Guerreiro");
        classNames.add("MagoElemental");
        classNames.add("Rogue");
        currentClassIndex = 0;
        add(createClassPanel("Healer", "Um curandeiro que usa magias para curar aliados e causar dano aos inimigos."), "Healer");
        add(createClassPanel("Guerreiro", "Um guerreiro que usa sua força física para causar dano aos inimigos e proteger aliados."), "Guerreiro");
        add(createClassPanel("MagoElemental", "Um mago que usa magias para causar dano aos inimigos."), "MagoElemental");
        add(createClassPanel("Rogue", "Um ladrão que usa furtividade e agilidade para causar dano aos inimigos."), "Rogue");

        // Add key bindings
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("Q"), "previous");
        actionMap.put("previous", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(SelectCharacterClass.this);
                currentClassIndex = (currentClassIndex - 1 + classNames.size()) % classNames.size();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("E"), "next");
        actionMap.put("next", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(SelectCharacterClass.this);
                currentClassIndex = (currentClassIndex + 1) % classNames.size();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "select");
        actionMap.put("select", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClassName = classNames.get(currentClassIndex);
                start_game = true;
            }
        });

        frame.add(this, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private JPanel createClassPanel(String className, String classDescription) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel(className);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameLabel.getMinimumSize().height));

        // Replace "imagePath" with the path to the image file
        ImageIcon imageIcon = new ImageIcon("imagePath");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea descriptionArea = new JTextArea(classDescription);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 18));
        descriptionArea.setForeground(Color.WHITE);
        descriptionArea.setBackground(Color.BLACK);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button

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