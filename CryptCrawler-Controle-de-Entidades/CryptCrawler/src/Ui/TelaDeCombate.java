package Ui;
import Entity.Aliado;
import Entity.Inimigo;
import Entity.Player;
import asciiPanel.AsciiFont;

import java.util.ArrayList;

public class TelaDeCombate extends Tela {
    public TelaDeCombate(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
    }
    public void printCombatScreen(ArrayList<Aliado> aliados, ArrayList<Inimigo> inimigos, Player player){
        int yPosition = 0;
        // Display player stats
        getTela().write((char) player.getIcone() + " HP: " + player.getClasse().getHp_atual() + "/" + player.getClasse().getHp_max() + " Mana: " + player.getClasse().getMp_atual() + "/" + player.getClasse().getMp_max(), 1, yPosition++);

        // Display allies stats
        for(Aliado aliado : aliados){
            getTela().write((char) aliado.getIcone() + " HP: " + aliado.getClasse().getHp_atual() + "/" + aliado.getClasse().getHp_max() + " Mana: " + aliado.getClasse().getMp_atual() + "/" + aliado.getClasse().getMp_max(), 1, yPosition++);
        }
        yPosition++;
        // Display enemies stats
        for(Inimigo inimigo : inimigos){
            getTela().write((char) inimigo.getIcone() + " HP: " + inimigo.getClasse().getHp_atual() + "/" + inimigo.getClasse().getHp_max(), 1, yPosition++);
        }
    }
}
