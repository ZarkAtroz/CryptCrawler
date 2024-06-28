import Ui.MainMenu;
import Ui.SelectCharacterClass;

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
        SelectCharacterClass charMenu = new SelectCharacterClass();
        String selectedClassName = " ";
        while(!charMenu.isReady()){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            selectedClassName = charMenu.getSelectedClassName();
        }
        CryptCrawler jogo = new CryptCrawler();
        jogo.run(selectedClassName);
    }

}