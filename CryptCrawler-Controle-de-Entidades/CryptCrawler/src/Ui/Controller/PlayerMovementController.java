package Ui.Controller;

import Entity.Aliado;
import Entity.Entidade;
import Entity.Inimigo;
import Entity.Player;
import combate.Combate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Queue;

public class PlayerMovementController {

    private final Player player;
    private final ArrayList<Aliado> aliados = new ArrayList<>();
    private final ArrayList<Inimigo> inimigos = new ArrayList<>();

    public PlayerMovementController(Player player, ArrayList<Entidade> entidades) {
        this.player = player;
        for (Entidade entidade : entidades) {
            if (entidade instanceof Aliado) {
                aliados.add((Aliado) entidade);
            }
            else if (entidade instanceof Inimigo) {
                inimigos.add((Inimigo) entidade);
            }
        }
    }

    public int getPlayerX() {
        return player.getX();
    }
    public int getPlayerY() {
        return player.getY();
    }

    public void processKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_W:
                player.moveUp();
                break;
            case KeyEvent.VK_S:
                player.moveDown();
                break;
            case KeyEvent.VK_A:
                player.moveLeft();
                break;
            case KeyEvent.VK_D:
                player.moveRight();
                break;
        }

        Queue<Point> posicoesAnteriores = player.getPosicoes();
        for(Aliado aliado : aliados) {
            aliado.seguirJogador(posicoesAnteriores);
        }
    }
    public void processCombatMenu(KeyEvent keyEvent, CombatController controller, Combate  combate){
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_1:
                controller.setCombatState(CombatController.CombatState.SELECIONAR_ALIADO);
                break;
            case KeyEvent.VK_2:
                controller.setCombatState(CombatController.CombatState.SELECIONAR_HABILIDADE);
                break;
            case KeyEvent.VK_3:
                break;
            case KeyEvent.VK_4:
                break;
        }

    }

    public boolean verificadorCombate(){
        for (Inimigo inimigo : inimigos) {
            if (inimigo.getX() == getPlayerX() && inimigo.getY() == getPlayerY()) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Aliado> getAliados() {
        return aliados;
    }
    public ArrayList<Inimigo> getInimigos() {
        return inimigos;
    }
}