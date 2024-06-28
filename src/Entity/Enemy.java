package Entity;

import combate.inimigos.*;
import world.World;

import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    
    private World world; // O jogador/personagem precisa saber em qual mundo ele está

    private int x;
    private int y;

    private String className;
    private char icon;

    // Para usar no combate
    private ArrayList<InimigoClasse> inimigos = new ArrayList<>();

    private int index_i_atual = 0;

    public Enemy(World world, int x, int y, String className, char icon) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.className = className;
        this.icon = icon;
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

    public int getIndex_i_atual() {
        return index_i_atual;
    }

    public void setIndex_i_atual(int index_i_atual) {
        this.index_i_atual = index_i_atual;
    }

    public ArrayList<InimigoClasse> getInimigos() {
        return inimigos;
    }

    public void setInimigos(ArrayList<InimigoClasse> inimigos) {
        this.inimigos = inimigos;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public char getIcon() {
        return icon;
    }

    public void setIcon(char icon) {
        this.icon = icon;
    }

    // Gerar party de inimigos aleatoriamente
    public void createParty() {
        int i = (int) Math.random() * 2;
        int lvl = 1 + (int) Math.random() * 10;
        Random rand = new Random();
        int j = 0;

        switch(className){
            case "Goblin":
                int numGoblins = rand.nextInt(5) + 1;
                for(j = 0; j < numGoblins; j++){
                    int goblinRand = rand.nextInt(100) + 1;
                    if(goblinRand >= 85)
                        inimigos.add(new GoblinForte(lvl));
                    else
                        inimigos.add(new Goblin(lvl));
                }
                break;

            case "Goblin Forte":
                int numGoblinsFortes = rand.nextInt(3) + 1;
                for(j = 0; j < numGoblinsFortes; j++){
                        inimigos.add(new GoblinForte(lvl));
                }
                break;

            case "Slime":
                int numSlimes = rand.nextInt(6) + 1;
                for(j = 0; j < numSlimes; j++){
                    inimigos.add(new Slime(lvl));
                }
                break;

            case "Esqueleto":
                int numEsqueletos = rand.nextInt(3) + 1;
                for(j = 0; j < numEsqueletos; j++){
                    inimigos.add(new Esqueleto(lvl));
                }
                break;

            case "Fantasma":
                int numFanstasma = rand.nextInt(2) + 1;
                for(j = 0; j < numFanstasma; j++){
                    inimigos.add(new Esqueleto(lvl));
                }
                break;
        }
    }

}
