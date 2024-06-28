package Entity;

import combate.herois.AliadoClasse;
import log.Log;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Aliado extends Entidade {
    private Player player;
    private AliadoClasse classe;

    public Aliado(String Nome, int x, int y, int icone, Player player) {
        super(true, Nome, x, y, icone);
        this.player = player;
    }

    public void seguirJogador(Queue<Point> posicoesAnteriores) {
        if (!posicoesAnteriores.isEmpty()) {
            Point proximaPosicao = posicoesAnteriores.poll();
            if (proximaPosicao != null) {
                setX(proximaPosicao.x);
                setY(proximaPosicao.y);
            }
        }
    }

    public void setClasse(AliadoClasse classe) {
        this.classe = classe;
    }
    public AliadoClasse getClasse() {
        return classe;
    }
}

