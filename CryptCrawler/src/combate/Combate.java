package combate;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

import Ui.Interface;
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

    public void atacar(int index_hb, int index_personagem, Interface inter_jogo) {

        if (inimigo_atual.getHp_atual() <= 0) {
            proximoInimigo();
        }

        if (heroi_atual.getHp_atual() <= 0) {
            trocaPersonagem(index_heroi_atual + 1);
        }

        if (turno_heroi) {
            int dano = heroi_atual.dano(heroi_atual.getHbs().get(index_hb), 1, 1, inimigo_atual.getAgilidade());
            dano = inimigo_atual.getHp_atual() - dano;
            inimigo_atual.setHp_atual(dano);

            turno_heroi = false;

            if (dano >= 0) {
                inter_jogo.getRelatorioJogo().textoUnico(heroi_atual.getClass().getSimpleName() + " errou o ataque", 0, 2);
            } else {
                inter_jogo.getRelatorioJogo().textoUnico(heroi_atual.getClass().getSimpleName() + "acertou o ataque dando: "+ dano, 0, 2);
            }

        } else {
            index_hb = (int) Math.random() * inimigo_atual.getHbs().size();

            System.out.println("Inimigo LVL: " + inimigo_atual.getLvl());

            Habilidade hb = inimigo_atual.returnHabilidade(index_hb);
            int dano = inimigo_atual.dano(hb, 1, 1, heroi_atual.getAgilidade());
            dano = heroi_atual.getHp_atual() - dano;
            heroi_atual.setHp_atual(dano);

            turno_heroi = true;
            inimigo_atual.colldownHabilidade();

            if (dano >= 0) {
                inter_jogo.getRelatorioJogo().textoUnico(inimigo_atual.getClass().getSimpleName() + " errou o ataque", 0, 4);
            } else {
                inter_jogo.getRelatorioJogo().textoUnico(inimigo_atual.getClass().getSimpleName() + " acertou o ataque dando: "+ dano, 0, 4);
            }
        }


    }

    
    // Gera ataque dos herois
    public void statusHerois(Interface interface_jogo) {
        int y = 0;

        for (Heroi h: getHerois()) {
            String str = h.getClass().getSimpleName() + " | " + h.getHp_atual() + " / " + h.getHp_max();
            interface_jogo.getStatusJogador().printTela(str, 0, y);
            y += 2;
        }
    }

    public void trocaPersonagem(int index_h) {

        if (index_h >= herois.size()) {
            index_h = 0;
        }

        index_heroi_atual = index_h;
        heroi_atual = herois.get(index_heroi_atual);

        if (heroi_atual.getHp_atual() <= 0) {
            trocaPersonagem(index_h + 1);
        }
    }

    public boolean proximoInimigo() {
        int i = index_inimigo_atual + 1;
        if (inimigos.isEmpty()) {
            return false;
        } else {
            if (i >= inimigos.size()) {
                i = 0;
            }
            inimigo_atual = inimigos.get(i);
            index_inimigo_atual = i;
        }
        return true;
    }
    

    // Getter Setter
    public ArrayList<Heroi> getHerois() {
        return herois;
    }

    public int getIndex_heroi_atual() {
        return index_heroi_atual;
    }

    public int getIndex_inimigo_atual() {
        return index_inimigo_atual;
    }

    public boolean isTurno_heroi() {
        return turno_heroi;
    }
}
