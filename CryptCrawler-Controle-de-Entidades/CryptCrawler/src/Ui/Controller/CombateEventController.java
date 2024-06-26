package Ui.Controller;

import Ui.Interface;
import combate.Combate;
import log.Log;

import java.awt.event.KeyEvent;

public class CombateEventController {
    
    private Combate combate;
    private int type_event = 0;
    /*
     * 0 = Selecionar opcao
     * 1 = Selecionar Hab
     * 2 = Trocar personagem ativo
     */
    

    public CombateEventController(Combate combate) {
        this.combate = combate;
    }

    
    public void processesKeyEvent(KeyEvent keyEvent, Interface interfaceJogo) {
        if (type_event == 0) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    type_event = 1;
                    break;
            
                case KeyEvent.VK_B:
                    type_event = 2;
                    break;
                default:
                    Log.log("Não selecionou nenhuma opcao");
                    type_event = 0;
                    break;
            }
        } else if (type_event == 1) {
            try {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_1:
                        combate.atacar(0, 0, interfaceJogo);
                        type_event = 0;
                        break;
                    case KeyEvent.VK_2:
                        combate.atacar(1, 0, interfaceJogo);
                        type_event = 0;
                        break;
                    case KeyEvent.VK_3:
                        combate.atacar(2, 0, interfaceJogo);
                        type_event = 0;
                        break;
                    case KeyEvent.VK_4:
                        combate.atacar(3, 0, interfaceJogo);
                        type_event = 0;
                        break;
                    default:
                        break;
                }
            } catch (IndexOutOfBoundsException i) {
                interfaceJogo.getRelatorioJogo().atualizarInformacao("Personagem não tem acesso a essa hab");
            }
            
        } else if (type_event == 2) {
            try {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_1:
                        combate.trocaPersonagem(0, 0);;
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
                    default:
                        break;
                }
                interfaceJogo.getRelatorioJogo().atualizarInformacao("Troca de personagem para: " + combate.heroi_atual.getClass().getSimpleName());
            } catch (IndexOutOfBoundsException i) {
                interfaceJogo.getRelatorioJogo().atualizarInformacao("Não existe esse personagem");
            }
        }
    }


    // Getter Setter

    public Combate getCombate() {
        return combate;
    }

    public void setCombate(Combate combate) {
        this.combate = combate;
    }

}
