package combate;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import java.awt.event.KeyListener;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import combate.herois.Guerreiro;
import combate.herois.Heroi;
import combate.herois.Rogue;
import combate.inimigos.Goblin;
import combate.inimigos.Inimigo;

public class TesteCombate extends JFrame {

    static JTextArea texto = new JTextArea();
    static JTextArea relatorio_jogo = new JTextArea();
    static Combate c;
    static int event = 0;
    // 1 = normal (atk bas, escolher trocar personagem, hab de atk)
    // 2 = trocar de personagem

    TesteCombate() {
        setTitle("TESTE Combate");
        setSize(1280, 720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        relatorio_jogo.setMargin(new Insets(getHeight() - 100, 100, 0, 100));
        texto.setSize(500, 500);
        texto.setLocation(0, 0);
        add(texto);
        add(relatorio_jogo);

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if (event == 0) {
                    switch (keyCode) {
                        case KeyEvent.VK_UP:
                            texto.setText(c.jogando());
                            break;
                        case KeyEvent.VK_DOWN:
                            event = 1;
                            break;
                        default:
                            break;
                    }
                } else if (event == 1) {
                    switch (keyCode) {
                        case KeyEvent.VK_1:
                            c.trocaPersonagem(0);
                            event = 0;
                            break;
                        case KeyEvent.VK_2:
                            c.trocaPersonagem(1);
                            event = 0;
                            break;
                        default:
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        setFocusable(true);
    }
    public static void main(String[] args) {
        ArrayList<Heroi> h = new ArrayList<>();
        h.add(new Guerreiro(1));
        h.add(new Rogue(1));

        ArrayList<Inimigo> i = new ArrayList<>();
        i.add(new Goblin(1));
        i.add(new Goblin(1));

        c = new Combate(0, 0, h, i);
    
        new TesteCombate();
    }

    public static void relatorioJogo(String str) {
        relatorio_jogo.setText(str);
    }
}
