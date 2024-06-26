package world;

import Entity.Enemy;
import Entity.Player;
import Ui.Exceptions.OutOfMapException;

/*
* Mapa completo da Dungeon
* Deverá ter uma escala (número inteiro)x maior que o minimapa
*/
public class World {

    /* Size Attributes */
    private int width;
    private int height;

    /* Tiles - Character */
    private char[][] tiles;
    private boolean[][] passableTiles;

    /* Player displaying on map */
    private Player playerOnMap;

    private Enemy enemyOnMap;

    /* Constructor */
    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new char[width][height];
        drawMap();
    }

    /* Get and Set */
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public char[][] getTiles() {
        return tiles;
    }
    public void setTiles(char[][] tiles) {
        this.tiles = tiles;
    }
    public boolean[][] getPassableTiles() {
        return passableTiles;
    }
    public void setPassableTiles(boolean[][] passableTiles) {
        this.passableTiles = passableTiles;
    }
    public Player getPlayerOnMap() {
        return playerOnMap;
    }

    public void setPlayerOnMap(Player playerOnMap) {
        this.playerOnMap = playerOnMap;
    }

    public Enemy getEnemyOnMap() {
        return enemyOnMap;
    }

    public void setEnemyOnMap(Enemy inimigo) {
        this.enemyOnMap = inimigo;
    }

    /*
    * Irá desenhar o mapa inicialmente
    * O mapa será feito em ASCII
    * Cada tile do mapa será desenhado aqui
    * Opções: Criar um programa secundário que desenha o mapa e armazena em um arquivo (Utilziando os caracteres personaizados)
    * ao invés de fazer hardcode
     */
    private void drawMap() {
        this.passableTiles = new boolean[width][height];
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                drawTile(i, j, (char)32);
            }
        }
        drawTile(21, 15, (char)254);
        drawTile(21, 16, (char)254);
        drawTile(21, 17, (char)254);
        drawTile(21, 18, (char)254);
        drawTile(22, 18, (char)254);
    }

    public boolean isPassable(int x, int y) throws OutOfMapException {
        isOutOfBounds(x, y);
        return passableTiles[x][y];
    }

    public boolean isOutOfBounds(int x, int y) throws OutOfMapException {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            throw new OutOfMapException("Player getting out of bounds (dungeonMap)");
        }
        else {
            return false;
        }
    }

    /*
    * Desenha um tile no mapa
    * No char tile, COLOCAR O CARACTERE PERSONALIZADO
     */
    private void drawTile(int x, int y, char tile) {
        if(tile == (char)32) {
            tiles[x][y] = tile;
            passableTiles[x][y] = true;
        } else {
            tiles[x][y] = tile;
            passableTiles[x][y] = false;
        }
    }
}
