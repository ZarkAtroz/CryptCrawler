package Ui;

import java.util.ArrayList;

import Entity.Enemy;
import Entity.Player;
import asciiPanel.AsciiFont;
import combate.Combate;
import combate.herois.Heroi;
import combate.inimigos.Inimigo;

public class TelaDeJogo extends Tela {

    public TelaDeJogo(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
    }

    public void printTexto(String texto, int x, int y){

        getTela().write(texto, x, y);
        // getTela().write("TELA DE JOGO 45 x 30", x, y);
        // getTela().write("45 x 30", x, y + 1);
    }

    public void printMundo(char[][] dungeonTiles, Player player, Enemy inimigo){


        // Desenha um mapa estático
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if(player.getX() == x && player.getY() == y)
                    getTela().write((char)14, x, y);
                else if(inimigo.getX() == x && inimigo.getY() == y)
                    getTela().write((char)15, x, y);
                else
                    getTela().write(dungeonTiles[x][y], x, y);
            }
        }

    }

    public void printCombate(ArrayList<Heroi> hrs, ArrayList<Inimigo> enes) {
        Combate c = new Combate();


    }
}
