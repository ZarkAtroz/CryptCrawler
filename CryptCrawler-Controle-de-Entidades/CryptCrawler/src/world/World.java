package world;

import Entity.Enemy;
import Entity.Player;
import Ui.Exceptions.OutOfMapException;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/*
* Mapa completo da Dungeon
* Deverá ter uma escala (número inteiro)x maior que o minimapa
*/
public class World implements Serializable {

    private static final long serialVersionUID = -3043187160444032741L;

    /* Size Attributes */
    private int width;
    private int height;

    private Tiles[][] tiles;

    /* Player displaying on map */
    private Player playerOnMap;

    private Enemy enemyOnMap;
    private ArrayList<Enemy> enemies = new ArrayList<>();

    /* Constructor */
    public World(int width, int height) {
        this.width = width;
        this.height = height;

        this.tiles = new Tiles[width][height];

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

    public Tiles getTileAt(int x, int y){
        return tiles[x][y];
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

    public Enemy getEnemyAt(int x, int y) {
        for(Enemy ens : enemies){
            if(ens.getX() == x && ens.getY() == y)
                return ens;
        }
        return null;
    }

    public void deleteEnemyAt(int x, int y){
        for(Enemy ens : enemies){
            if(ens.getX() == x && ens.getY() == y)
                enemies.remove(ens);
        }
    }

    public void addEnemyToList(Enemy newEnemy){
        enemies.add(newEnemy);
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /*
    * Irá desenhar o mapa inicialmente
    * O mapa será feito em ASCII
    * Cada tile do mapa será desenhado aqui
    * Opções: Criar um programa secundário que desenha o mapa e armazena em um arquivo (Utilziando os caracteres personaizados)
    * ao invés de fazer hardcode
     */

    public void readTiles(){
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                drawPassableTile(i, j, tiles[i][j].getIcon());
            }
        }
    }

    public void drawAllCharacters(){
        // Desenhando no mapa todos os caracteres
        int x = 80, y = 10;

        for(int i = 0; i < 256; i++){
            if(i % 16 == 0){
                x = 80;
                y++;
                drawTile(x++, y, (char)i);
            } else {
                drawTile(x++, y, (char)i);
            }
        }
    }

    private void drawMap() {
        Random rand = new Random();

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                tiles[i][j].setBackgroundColor(Color.BLACK);
            }
        }

        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                tiles[i][j].setIcon((char)0);
            }
        }

        tiles[21][15].setIcon((char)254);
        tiles[21][16].setIcon((char)254);
        tiles[21][17].setIcon((char)254);
        tiles[21][18].setIcon((char)254);
        tiles[22][18].setIcon((char)254);
    }

    public boolean isPassable(int x, int y) throws OutOfMapException {
        isOutOfBounds(x, y);
        return tiles[x][y].getIsPassable();
    }

    public boolean isOutOfBounds(int x, int y) throws OutOfMapException {
        if(x < 0 || x >= width || y < 0 || y >= height)
            throw new OutOfMapException("Player getting out of bounds (dungeonMap)");
        else
            return false;
    }

    private void drawTile(int x, int y, char tile) {
        tiles[x][y].setIcon(tile);
        tiles[x][y].setBackgroundColor(Color.BLACK);
        tiles[x][y].setForegroundColor(Color.WHITE);

        drawPassableTile(x, y, tile);
    }

    public void drawPassableTile(int x, int y, char tile) {
        tiles[x][y].setPassable(tile == (char) 0 || (tile >= (char) 245 && tile <= (char) 250) || (tile >= (char) 224 && tile <= (char) 227));
    }
}
