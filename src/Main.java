import Ui.MainMenu;

import javax.swing.*;

public class Main extends JFrame{

    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        boolean game_start = mainMenu.getGameStart();
        while(!game_start){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game_start = mainMenu.getGameStart();
        }
        CryptCrawler jogo = new CryptCrawler();
        jogo.run();
    }

}

// Todo:
// - Criar artes e printar inimigos
// - Implementar morte de aliados e player (remover do selecionar aliados)
// - Implementar game over
// - Criar sistema de inventário
