package Ui;

import Entity.Player;
import asciiPanel.AsciiFont;

import java.awt.*;

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
        getTela().clear();

        // Desenha um mapa estático
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if(player.getX() == x && player.getY() == y)
                    getTela().write((char)14, x, y);
                else
                    getTela().write(dungeonTiles[x][y], x, y, Color.WHITE, Color.BLACK);
            }
        }

        getTela().repaint();
    }

    public Point GetCameraOrigin(int xfocus, int yfocus)
    {
        int spx = Math.max(0, Math.min(xfocus - 45 / 2, 450 - 45));
        int spy = Math.max(0, Math.min(yfocus - 30 / 2, 300 - 30));
        return new Point(spx, spy);
    }

    public void lookAt(char[][] dungeonTiles, Player player){

        char tile;
        Point origin;

        char[][] test = new char[10][10];

        origin = GetCameraOrigin(player.getX(), player.getY());

        for (int x = 0; x < getWidth(); x++){
            for (int y = 0; y < getHeight(); y++){
                tile = dungeonTiles[origin.x + x][origin.y + y];
                if(player.getX() == (origin.x + x) && player.getY() == (origin.y + y))
                    getTela().write((char)14, x, y);
                else
                    getTela().write(tile, x, y, Color.WHITE, Color.BLACK);
            }
        }

    }
}
