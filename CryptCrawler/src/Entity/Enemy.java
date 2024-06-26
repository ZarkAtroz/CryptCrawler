package Entity;

import java.util.ArrayList;

import combate.inimigos.Goblin;
import combate.inimigos.GoblinForte;
import combate.inimigos.Inimigo;
import world.World;

public class Enemy {
    
    private World world; // O jogador/personagem precisa saber em qual mundo ele está
    private int x;
    private int y;
    private String name;

    // Para usar no combate
    private ArrayList<Inimigo> inimigos = new ArrayList<>();

    private int index_i_atual = 0;

    public Enemy(World world, int x, int y, String name) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.name = name;
    }

     //Getter e Setter
    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

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

    public int getIndex_i_atual() {
        return index_i_atual;
    }

    public void setIndex_i_atual(int index_i_atual) {
        this.index_i_atual = index_i_atual;
    }

    public ArrayList<Inimigo> getInimigos() {
        return inimigos;
    }

    public void setInimigos(ArrayList<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }



    // Gerar party de inimigos aleatoriamente
    public void createParty() {
        int i = (int) Math.random() * 2;
        int lvl = 1 + (int) Math.random() * 10;

        if (i == 0) {
            inimigos.add(new Goblin(lvl));
        } else if (i == 1) {
            inimigos.add(new GoblinForte(lvl));
        }

        if (inimigos.size() <= 4) {
            int add = (int) Math.random() * 2;
            if (add == 0) {
                createParty();
            }
        }
    }

}
