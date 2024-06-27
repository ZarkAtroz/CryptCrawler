package world;

import log.Log;

public class MiniMap {
    private char[][] miniTiles;
    private int scale;

    public MiniMap(int width, int height, int scale) {
        this.miniTiles = new char[width][height];
        this.scale = scale;
    }

    public char[][] getTiles() {
        return miniTiles;
    }

    public int getScale() {
        return scale;
    }

    public void updateTiles(int x, int y, Tiles tile) {
        int miniMapX = x / scale;
        int miniMapY = y / scale;

        // Colocar os tiles que necessitam ser desenhados
        if(tile.getIcon() == (char)254) {
            miniTiles[miniMapX][miniMapY] = (char)219;
        }
    }
}