package combate;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

import combate.herois.Guerreiro;
import combate.herois.Heroi;
import combate.herois.Rogue;
import combate.inimigos.Goblin;
import combate.inimigos.Inimigo;

public class TesteCombate extends JFrame {

    static JTextArea texto = new JTextArea();

    TesteCombate() {
        setTitle("TESTE Combate");
        setSize(1280, 720);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(texto);
    }
    public static void main(String[] args) {
        new TesteCombate();
        Scanner sc = new Scanner(System.in);

        ArrayList<Heroi> h = new ArrayList<>();
        h.add(new Guerreiro(1));
        h.add(new Rogue(1));

        ArrayList<Inimigo> i = new ArrayList<>();
        i.add(new Goblin(1));
        i.add(new Goblin(1));
        Combate c = new Combate(1, 0, h, i);

    
        while (true) {
            texto.setText(c.teste());
            
            sc.nextLine();
        }
    }
}
