package Ui.Controller;

import Ui.Interface;
import combate.Combate;
import log.Log;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Stack;

public class CombateEventController {

    private Combate combate;
    private int type_event = 0;
    private Stack<Integer> menuStack = new Stack<>();
    private int index_hb = 0;

    /*
     * 0 = Selecionar opcao
     * 1 = Selecionar Hab
     * 2 = Trocar personagem ativo
     */

    public CombateEventController(Combate combate) {
        this.combate = combate;
    }

    public int getTypeEvent(){
        return this.type_event;
    }

    public int getIndexHb() {
        return this.index_hb;
    }

    public void processesKeyEvent(KeyEvent keyEvent, Interface interfaceJogo) {


        if(keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(!menuStack.isEmpty()){
                type_event = menuStack.pop();
            } else {
                Log.log("NAO HA MENU ANTERIOR");
            }
        }else if (type_event == 0) {

            index_hb = 0;

            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_Q:
                    menuStack.push(type_event);
                    type_event = 1;
                    break;
                case KeyEvent.VK_E:
                    menuStack.push(type_event);
                    type_event = 2;
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
                default:
                    Log.log("NAO SELECIONOU NENHUM OPCAO");
                    type_event = 0;
                    break;
            }
        } else if (type_event == 1) {
            try {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_1:
                        type_event = 3;
                        index_hb = 0;
                        break;
                    case KeyEvent.VK_2:
                        type_event = 3;
                        index_hb = 1;
                        break;
                    case KeyEvent.VK_3:
                        index_hb = 2;
                        type_event = 3;
                        break;
                    case KeyEvent.VK_4:
                        type_event = 3;
                        index_hb = 3;
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
                    default:
                        break;
                }
            } catch (IndexOutOfBoundsException i) {
                interfaceJogo.getRelatorioJogo().atualizarInformacao("ALIADO SEM ACESSO A HABILIDADE", Color.RED);
            }
        } else if (type_event == 2) {
            try {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_1:
                        combate.trocaPersonagem(0, 0);
                        type_event = 0;
                        break;
                    case KeyEvent.VK_2:
                        combate.trocaPersonagem(1, 0);
                        type_event = 0;
                        break;
                    case KeyEvent.VK_3:
                        combate.trocaPersonagem(2, 0);
                        type_event = 0;
                        break;
                    case KeyEvent.VK_4:
                        combate.trocaPersonagem(3, 0);
                        type_event = 0;
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
                    default:
                        break;
                }
                interfaceJogo.getRelatorioJogo().atualizarInformacao("ALIADO SELECIONADO: " + combate.heroi_atual.getClass().getSimpleName().toUpperCase(), Color.WHITE);
            } catch (IndexOutOfBoundsException i) {
                interfaceJogo.getRelatorioJogo().atualizarInformacao("ALIADO INEXISTENTE...", Color.RED);
            }
        } else if(type_event == 3){
            try {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_1:
                        combate.atacar(index_hb, 0, 0, interfaceJogo);
                        type_event = 0;
                        break;
                    case KeyEvent.VK_2:
                        combate.atacar(index_hb, 0, 1, interfaceJogo);
                        type_event = 0;
                        break;
                    case KeyEvent.VK_3:
                        combate.atacar(index_hb, 0, 2, interfaceJogo);
                        type_event = 0;
                        break;
                    case KeyEvent.VK_4:
                        combate.atacar(index_hb, 0, 3, interfaceJogo);
                        type_event = 0;
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
                    default:
                        break;
                }
            } catch (IndexOutOfBoundsException i) {
                interfaceJogo.getRelatorioJogo().atualizarInformacao("INIMIGO INEXISTENTE...", Color.RED);
            }
        }
        combate.updateInterface(interfaceJogo, type_event);
    }


    // Getter Setter

    public Combate getCombate() {
        return combate;
    }

    public void setCombate(Combate combate) {
        this.combate = combate;
    }

}
