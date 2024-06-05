package combate;

import java.util.ArrayList;

import combate.herois.Guerreiro;
import combate.herois.Healer;
import combate.herois.Heroi;
import combate.herois.MagoElemental;
import combate.herois.Rogue;

public class TesteCombate {
    public static void main(String[] args) {
        ArrayList<Heroi> h = new ArrayList<>();
        h.add(new Guerreiro(3));
        h.add(new Rogue(1));

        ArrayList<Heroi> i = new ArrayList<>();
        i.add(new MagoElemental(1));
        i.add(new Healer(1));
        Combate c = new Combate(0, 0, h, i);

        while (true) {
            c.teste();
        }
    }
}
