package Entity;

import combate.herois.Heroi;
import log.Log;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Aliado extends Entidade{
    private Player player;
    private Heroi classe;


    public Aliado(String Nome, int x, int y, int icone, Player player, Heroi classe) {
        super(true, Nome, x, y,icone);
        this.player = player;
        this.classe = classe;
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
    public Heroi getClasse() {
        return classe;
    }
    }

