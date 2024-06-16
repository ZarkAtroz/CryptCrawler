package Ui;

import Entity.Player;
import asciiPanel.AsciiFont;

public class TelaDeJogo extends Tela {

    public TelaDeJogo(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
    }

    public void printTexto(String texto, int x, int y){

        getTela().write(texto, x, y);
        // getTela().write("TELA DE JOGO 45 x 30", x, y);
        // getTela().write("45 x 30", x, y + 1);
    }

    public void printMundo(char[][] dungeonTiles, Player player){


        // Desenha um mapa estático
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if(player.getX() == x && player.getY() == y)
                    getTela().write((char)14, x, y);
                else
                    getTela().write(dungeonTiles[x][y], x, y);
            }
        }

    }
}
