package world;

import log.Log;

public class MiniMap {
    private char[][] tiles;
    private int scale;

    public MiniMap(int width, int height, int scale) {
        this.tiles = new char[width][height];
        this.scale = scale;
    }

    public char[][] getTiles() {
        return tiles;
    }

    public int getScale() {
        return scale;
    }

    public void updateTiles(int x, int y, char tile) {
        int miniMapX = x / scale;
        int miniMapY = y / scale;
        if(tile != (char)32) {
            if(tiles[miniMapX][miniMapY] != (char)219) {
                Log.logDebug("Tile ["+ miniMapX +"]["+ miniMapY +"] updated");
            }
            tiles[miniMapX][miniMapY] = (char)219;
        }
    }
}
