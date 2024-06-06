package combate;

import java.util.ArrayList;

import combate.herois.Guerreiro;
import combate.herois.Heroi;
import combate.herois.Rogue;
import combate.inimigos.Goblin;
import combate.inimigos.Inimigo;

public class TesteCombate {
    public static void main(String[] args) {
        ArrayList<Heroi> h = new ArrayList<>();
        h.add(new Guerreiro(1));
        h.add(new Rogue(1));

        ArrayList<Inimigo> i = new ArrayList<>();
        i.add(new Goblin(1));
        i.add(new Goblin(1));
        Combate c = new Combate(1, 0, h, i);

        while (true) {
            c.teste();
        }
    }
}
