package Ui;

import Entity.*;
import asciiPanel.AsciiFont;
import world.MiniMap;
import world.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MiniMapaInterface extends Tela {

    public MiniMapaInterface(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
    }

    public Point getCameraOrigin(int xfocus, int yfocus) {
        int spx = Math.max(0, Math.min(xfocus - getWidth() / 2, getWidth() - 1));
        int spy = Math.max(0, Math.min(yfocus - getHeight() / 2, getHeight() - 1));
        return new Point(spx, spy);
    }

    public void printMiniMap(MiniMap miniMap, List<Entidade> entities, List<Enemy> enemies) {
        char[][] tiles = miniMap.getTiles();
        Player player = null;

        for(Entidade entity : entities) {
            if(entity instanceof Player) {
                player = (Player) entity;
                break;
            }
        }

        getTela().clear();

        Point origin = getCameraOrigin(player.getX()/miniMap.getScale(), player.getY()/miniMap.getScale());

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if(origin.x + x < tiles.length && origin.y + y < tiles[0].length) {
                    boolean isEntity = false;
                    boolean isEnemy = false;
                    for (Entidade entity : entities) {
                        int entityX = entity.getX() / miniMap.getScale();
                        int entityY = entity.getY() / miniMap.getScale();
                        if (entityX == origin.x + x && entityY == origin.y + y) {
                            if(entity instanceof Player) {
                                getTela().write((char) 219, x, y, Color.BLUE);
                            }
                            isEntity = true;
                            break;
                        }
                    }
                    for(Enemy enemy : enemies) {
                        int enemyX = enemy.getX() / miniMap.getScale();
                        int enemyY = enemy.getY() / miniMap.getScale();
                        if (enemyX == origin.x + x && enemyY == origin.y + y) {
                            getTela().write((char) 219, x, y, Color.RED);
                            isEnemy = true;
                            break;
                        }
                    }
                    if(!isEntity && !isEnemy) {
                        if(tiles[origin.x + x][origin.y + y] == (char)219) {
                            getTela().write(tiles[origin.x + x][origin.y + y], x, y);
                        } else {
                            getTela().write(tiles[origin.x + x][origin.y + y], x, y, Color.BLACK, Color.BLACK);
                        }
                    }
                }
            }
        }
        getTela().repaint();
    }

    public void printTela(String texto, int x, int y){
        getTela().write("MINI MAPA", x, y);
        getTela().write("49 x 35", x, y + 1);
    }
}
