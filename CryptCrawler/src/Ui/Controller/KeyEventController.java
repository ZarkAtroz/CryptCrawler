package Ui.Controller;

import Entity.Entidade;
import Entity.Player;
import Ui.Interface;
import combate.Combate;
import log.Log;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/*
* Classe responsável por controlar os eventos de teclado
* É chamada na classe principal Game.CryptCrawler
* @see Game.CryptCrawler
* Na classe principal Game.CryptCrawler, o método executeNextKeyEvent() é chamado
* Exemplo de uso: Jogador clica W e essa classe detecta isso, informando a classe de movimento do personagem e realizando a ação
*  */
public class KeyEventController {

    private final GameEventListener listener;
    private final PlayerMovementController playerMovementController;
    private CombateEventController combateEventController;

    /**
     * Constructor for the KeyEventController class.
     * Initializes the listener and playerMovementController.
     *
     * @param listener The game event listener
     * @param playerOnMap The player on the map
     */
    public KeyEventController(GameEventListener listener, Player playerOnMap, ArrayList<Entidade> entidades, Combate combate) {
        this.listener = listener;
        this.playerMovementController = new PlayerMovementController(playerOnMap, entidades);
        this.combateEventController = new CombateEventController(combate);
    }

    /**
     * This method is used to execute a key event.
     * It checks the next input from the interface and processes it if it's a KeyEvent.
     * If the key event is W, A, S, or D, it prints the player's position on log and processes the key event.
     * If the key event is ESCAPE, it calls the onGameExit method from the listener.
     *
     * @param interfaceJogo The game interface
     */
    public void executeKeyEvent(Interface interfaceJogo, boolean em_combate, ArrayList<Entidade> entidades) {
        InputEvent event = interfaceJogo.getNextInput();
        if (event instanceof KeyEvent keypress){
            if(!em_combate){
                switch (keypress.getKeyCode()){
                    case KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D:
                        Log.logInfo("Posição do jogador: (" + playerMovementController.getPlayerX() + ", " + playerMovementController.getPlayerY() + ")");
                        playerMovementController.processKeyEvent(keypress);
                        break;

                    case KeyEvent.VK_ESCAPE:
                        this.listener.onGameExit();
                        break;

                    case KeyEvent.VK_PAGE_UP:
                        interfaceJogo.getRelatorioJogo().decrementarLinha();
                        break;

                    case KeyEvent.VK_PAGE_DOWN:
                        interfaceJogo.getRelatorioJogo().encrementarLinha();
                        break;

                    case KeyEvent.VK_END:
                        interfaceJogo.getRelatorioJogo().setLinhaFim();
                        break;
                }
            } else {
                combateEventController.processesKeyEvent(keypress, interfaceJogo);
            }
        } else if (event instanceof MouseEvent) {
            //
        }
    }

    public int getCombateTypeEvent(){
        return combateEventController.getTypeEvent();
    }
}
