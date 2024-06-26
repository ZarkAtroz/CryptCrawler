package Entity;

import log.Log;
import world.World;

import java.util.ArrayList;

import Ui.Exceptions.OutOfMapException;
import combate.herois.Guerreiro;
import combate.herois.Healer;
import combate.herois.Heroi;
import combate.herois.MagoElemental;
import combate.herois.Rogue;

public class Player {

    private World world; // O jogador/personagem precisa saber em qual mundo ele está
    private int x;
    private int y;

    private String name;

    // Para o combate
    private ArrayList<Heroi> party = new ArrayList<>();
    private int index_h_atual = 0;

    public Player(String name, int x, int y, World world) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.world = world;

        party.add(new Guerreiro(3));
        party.add(new Healer(3));
        party.add(new MagoElemental(3));
        party.add(new Rogue(3));
    }

    // Set and Get
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Heroi> getParty() {
        return party;
    }

    public void setParty(ArrayList<Heroi> party) {
        this.party = party;
    }

    public void moveUp() {
        try {
            if(world.isPassable(x, y-1) && !world.isOutOfBounds(x, y-1))
                y--;
        } catch(OutOfMapException e) {
            Log.logWarning("Is not possible to move up");
            Log.logExceptionMessage(e);
        }
    }
    public void moveDown() {
        try {
            if(world.isPassable(x, y+1) && !world.isOutOfBounds(x, y+1))
                y++;
        } catch(OutOfMapException e) {
            Log.logWarning("Is not possible to move down");
            Log.logExceptionMessage(e);
        }
    }
    public void moveLeft() {
        try {
            if(world.isPassable(x-1, y) && !world.isOutOfBounds(x-1, y))
                x--;
        } catch(OutOfMapException e) {
            Log.logDebug("Player tried to move out of bounds");
            Log.logWarning("Is not possible to move left");
            Log.logExceptionMessage(e);
        }
    }
    public void moveRight() {
        try {
            if(world.isPassable(x+1, y) && !world.isOutOfBounds(x+1, y))
                x++;
        } catch(OutOfMapException e) {
            Log.logWarning("Is not possible to move right");
            Log.logExceptionMessage(e);
        }
    }
}
