package Ui.Controller;

import Entity.Aliado;
import Entity.Inimigo;
import Ui.Interface;
import combate.Combate;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CombatController {
    public enum CombatState{
        SELECIONAR_ACAO_COMBATE,
        SELECIONAR_ALIADO,
        SELECIONAR_INIMIGO,
        SELECIONAR_HABILIDADE
    }
    public CombatState combatState;
    public Interface interfaceJogo;
    public Combate combate;

    public CombatController(CombatState combatState, Interface interfaceJogo, Combate combate){
        this.combatState = combatState;
        this.interfaceJogo = interfaceJogo;
        this.combate = combate;
    }

    public void modo_de_combate(CombatState combatState){
        switch (combatState){
            case SELECIONAR_ALIADO:
                interfaceJogo.getStatusJogador().printAliados(combate.getAliados(), combate.getPlayer());
                selectAllyMenu(interfaceJogo, this, combate.getAliados(), combate.getInimigos(), combate);
                break;
            case SELECIONAR_INIMIGO:
                interfaceJogo.getStatusJogador().printInimigos(combate.getInimigos());
                break;
            case SELECIONAR_HABILIDADE:
                break;
            case SELECIONAR_ACAO_COMBATE:
                interfaceJogo.getStatusJogador().printOptCombate();
                break;
        }

    }

    public void selectAllyMenu(Interface interfaceJogo, CombatController controller, ArrayList<Aliado> aliados, ArrayList<Inimigo> inimigos, Combate combate) {
        interfaceJogo.updateCombatOptions(aliados, inimigos);
        InputEvent event = interfaceJogo.getNextInput();
        if (event instanceof KeyEvent keypress) {
            switch (keypress.getKeyCode()) {
                case KeyEvent.VK_0:
                    combate.setPlayerAsAliado();
                    interfaceJogo.getStatusJogador().clear();
                    setCombatState(CombatState.SELECIONAR_INIMIGO);
                    selectEnemyMenu(interfaceJogo, controller, aliados, inimigos, combate);
                    break;
                case KeyEvent.VK_1:
                    if (aliados.size() > 1) {
                        combate.setAliadoAtual(0);
                        interfaceJogo.getStatusJogador().clear();
                        setCombatState(CombatState.SELECIONAR_INIMIGO);
                        selectEnemyMenu(interfaceJogo, controller, aliados, inimigos, combate);
                    } else {
                        System.out.println("Invalid ally index.");
                    }
                    break;
                case KeyEvent.VK_2:
                    if (aliados.size() > 2) {
                        combate.setAliadoAtual(1);
                        interfaceJogo.getStatusJogador().clear();
                        setCombatState(CombatState.SELECIONAR_INIMIGO);
                        selectEnemyMenu(interfaceJogo, controller, aliados, inimigos, combate);
                    } else {
                        System.out.println("Invalid ally index.");
                    }
                    break;
                case KeyEvent.VK_3:
                    if (aliados.size() > 3) {
                        combate.setAliadoAtual(2);
                        interfaceJogo.getStatusJogador().clear();
                        setCombatState(CombatState.SELECIONAR_INIMIGO);
                        selectEnemyMenu(interfaceJogo, controller, aliados, inimigos, combate);
                    } else {
                        System.out.println("Invalid ally index.");
                    }
                    break;
                // Add more cases as needed
                default:
                    interfaceJogo.getStatusJogador().clear();

            }
        } else if (event instanceof MouseEvent) {
            //
        }
    }

    public void selectEnemyMenu(Interface interfaceJogo, CombatController controller, ArrayList<Aliado> aliados, ArrayList<Inimigo> inimigos, Combate combate){
        interfaceJogo.updateCombatOptions(aliados, inimigos);
        InputEvent event = interfaceJogo.getNextInput();
        if (event instanceof KeyEvent keypress) {
            switch (keypress.getKeyCode()) {
                case KeyEvent.VK_1:
                    combate.setInimigoAtual(0);
                    combate.habilidade(0);
                    interfaceJogo.getStatusJogador().clear();
                    controller.setCombatState(CombatState.SELECIONAR_ACAO_COMBATE);
                    break;
                case KeyEvent.VK_2:
                    if (aliados.size() > 1) {
                        combate.setInimigoAtual(1);
                        combate.habilidade(0);
                        interfaceJogo.getStatusJogador().clear();
                        controller.setCombatState(CombatState.SELECIONAR_ACAO_COMBATE);
                    } else {
                        System.out.println("Invalid enemy index.");
                    }
                    break;
                case KeyEvent.VK_3:
                    if (aliados.size() > 2) {
                        combate.setInimigoAtual(2);
                        combate.habilidade(0);
                        interfaceJogo.getStatusJogador().clear();
                        controller.setCombatState(CombatState.SELECIONAR_ACAO_COMBATE);

                    } else {
                        System.out.println("Invalid enemy index.");
                    }
                    break;
                case KeyEvent.VK_4:
                    if (aliados.size() > 3) {
                        combate.setInimigoAtual(3);
                        combate.habilidade(0);
                        interfaceJogo.getStatusJogador().clear();
                        controller.setCombatState(CombatState.SELECIONAR_ACAO_COMBATE);
                    } else {
                        System.out.println("Invalid enemy index.");
                    }
                    break;
                // Add more cases as needed
                default:
                    interfaceJogo.getStatusJogador().clear();

            }
        } else if (event instanceof MouseEvent) {
            //
        }
    }

    public void setCombatState(CombatState combatState){
        this.combatState = combatState;
    }
    public CombatState getCombatState(){
        return combatState;
    }


}
