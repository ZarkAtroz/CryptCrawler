package combate;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

import combate.herois.Heroi;
import combate.inimigos.Inimigo;

public class Combate {

    private ArrayList<Heroi> herois = new ArrayList<>();

    private ArrayList<Inimigo> inimigos = new ArrayList<>();

    private int index_heroi_atual; // Colocar no construtor depois
    private int index_inimigo_atual;

    public Heroi heroi_atual;
    public Inimigo inimigo_atual;

    private boolean turno_heroi;

    public Combate(){}

    public Combate(int i_heroi, int i_inimigo, ArrayList<Heroi> hr, ArrayList<Inimigo> in) {
        herois = hr;
        inimigos = in;

        index_heroi_atual = i_heroi;
        index_inimigo_atual = i_inimigo;

        heroi_atual = herois.get(index_heroi_atual);
        inimigo_atual = inimigos.get(index_inimigo_atual);

        turno_heroi = (heroi_atual.getAgilidade() > inimigo_atual.getAgilidade());
    }

    public void atacar(int index_hb) {
        if (turno_heroi) {
            int dano = heroi_atual.dano(heroi_atual.getHbs().get(index_hb), 1, 1, inimigo_atual.getAgilidade());
            dano = inimigo_atual.getHp_atual() - dano;
            inimigo_atual.setHp_atual(dano);

            turno_heroi = false;
        } else {
            int dano = inimigo_atual.dano(inimigo_atual.getHbs().get(0), 1, 1, heroi_atual.getAgilidade());
            dano = heroi_atual.getHp_atual() - dano;
            heroi_atual.setHp_atual(dano);

            turno_heroi = true;
        }
    }

    // Gera o relatorios dos status dos Personagens
    public String statusPersonagens() {
        String str = "";

        if (turno_heroi) {
            str += "Turno atual dos hérois\n";
        } else {
            str += "Turno atual dos inimigos\n";
        }

        str += "herio atual: " + heroi_atual.getClass().getSimpleName() + " | "  + heroi_atual.getHp_atual() + "/" + heroi_atual.getHp_max() + "\n";
        str += "imigo atual: " + inimigo_atual.getClass().getSimpleName() + " | "  + inimigo_atual.getHp_atual() + "/" + inimigo_atual.getHp_max() + "\n";

        str += "Reservas: \n";

        str += "- Herois: \n";
        for (int i = 0; i < herois.size(); i++) {
            if (i != index_heroi_atual) {
                Heroi h = herois.get(i);
                str += "-- " + h.getClass().getSimpleName() + " | " + h.getHp_atual() + "/" + h.getHp_max() + "\n";
            }
        }

        str += "- Inimigos: \n";
        for (int i = 0; i < inimigos.size(); i++) {
            if (i != index_inimigo_atual) {
                Inimigo in = inimigos.get(i);
                str += "-- " + in.getClass().getSimpleName() + " | " + in.getHp_atual() + "/" + in.getHp_max() + "\n";
            }
        }

        return str;
    }

    public void trocaPersonagem(int index_h) {
        index_heroi_atual = index_h;
        heroi_atual = herois.get(index_heroi_atual);
    }
    

    // Getter Setter
    public ArrayList<Heroi> getHerois() {
        return herois;
    }
}
