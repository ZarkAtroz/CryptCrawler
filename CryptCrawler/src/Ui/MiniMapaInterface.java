package Ui;

import Entity.Player;
import asciiPanel.AsciiFont;
import world.MiniMap;

import java.awt.*;

public class MiniMapaInterface extends Tela {

    public MiniMapaInterface(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);

    }

    public Point getCameraOrigin(int xfocus, int yfocus) {
        int spx = Math.max(0, Math.min(xfocus - getWidth() / 2, getWidth() - 1));
        int spy = Math.max(0, Math.min(yfocus - getHeight() / 2, getHeight() - 1));
        return new Point(spx, spy);
    }

    public void printMiniMap(MiniMap miniMap, Player player) {
        char[][] tiles = miniMap.getTiles();
        Point origin = getCameraOrigin(player.getX()/miniMap.getScale(), player.getY()/miniMap.getScale());

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if(origin.x + x < tiles.length && origin.y + y < tiles[0].length) {
                    if(player.getX()/miniMap.getScale() == origin.x + x && player.getY()/miniMap.getScale() == origin.y + y)
                        getTela().write((char)219, x, y);
                    else
                        getTela().write(tiles[origin.x + x][origin.y + y], x, y);
                }
            }
        }
        getTela().repaint();
    }

    public void printMatriz(){
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                tiles[i][j] = '.';
            }
        }

        int x = 0, y = 0;

        for(int j = 0; j < getHeight(); j++, y++){
            x = 0;
            for (int i = 0; i < getWidth(); i++, x++) {
                getTela().write(tiles[i][j], x, y);
            }
        }

        getTela().repaint();
    }

    public void printTela(String texto, int x, int y){
        getTela().clear();
        getTela().write("MINI MAPA", x, y);
        getTela().write("49 x 35", x, y + 1);
        getTela().repaint();
    }
}
