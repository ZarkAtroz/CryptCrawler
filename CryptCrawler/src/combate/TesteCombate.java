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
        relatorio_jogo.setEditable(false);
        texto.setSize(500, 500);
        texto.setMargin(new Insets(0, 0, 0, 0));
        texto.setEditable(false);
        status(c.statusPersonagens());
        add(texto);
        add(relatorio_jogo);

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e){
                int keyCode = e.getKeyCode();
                if (event == 0) {
                    switch (keyCode) {
                        case KeyEvent.VK_UP:
                            String str_1 = "Escolha uma das habilidades do herói atual: \n";
                            int j = 1;
                            for (Habilidade h : c.heroi_atual.getHbs()) {
                                str_1 += "["+j+"] " + h.getNome_hab() + "\n";
                                j++;
                            }
                            relatorioJogo(str_1);
                            event = 2;
                            break;
                        case KeyEvent.VK_DOWN:
                            event = 1;
                            String str = "Escolha entre uma das opções abaixo para trocar:\n";
                            int i = 1;
                            for (Heroi h : c.getHerois()) {
                                str += "["+i+"] " + h.getClass().getSimpleName() + "\n";
                                i++;
                            }
                            relatorioJogo(str);
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
                        case KeyEvent.VK_3:
                            c.trocaPersonagem(2);
                            event = 0;
                            break;
                        case KeyEvent.VK_4:
                            c.trocaPersonagem(3);
                            event = 0;
                            break;
                        default:
                            break;
                    }
                    relatorioJogo("Troca de personagem feita para " + c.heroi_atual.getClass().getSimpleName());
                } else if (event == 2) {
                    switch (keyCode) {
                        case KeyEvent.VK_1:
                            c.atacar(0);
                            event = 0;
                            break;
                        case KeyEvent.VK_2:
                            c.atacar(1);
                            event = 0;
                            break;
                        case KeyEvent.VK_3:
                            c.atacar(2);
                            event = 0;
                            break;
                        case KeyEvent.VK_4:
                            c.atacar(3);
                            event = 0;
                            break;
                        default:
                            break;
                    }
                    status(c.statusPersonagens());
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

    public static void status(String str) {
        texto.setText(str);
    }

    public static void closeWindow() {
        System.exit(0);
    }
}
