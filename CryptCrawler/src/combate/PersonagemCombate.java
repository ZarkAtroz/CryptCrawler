package combate;

import java.util.ArrayList;

public abstract class PersonagemCombate {

    // Status principais
    protected int forca;
    protected int inteligencia;
    protected int agilidade;
    protected int sorte;
    protected int critico;
    protected int resistencia;
    protected int exp;
    protected int lvl;

    // Habilidades
    protected ArrayList<Habilidade> hbs = new ArrayList<>();

    // Construtores
    public PersonagemCombate(int lvl) {
        this.lvl = lvl;
    }

    // Funcões
    public abstract void leveling();

    public abstract void addHabs(int cd_mp, int tp, int status, float mod, String nome);

    public int getForca() {
        return forca;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public int getSorte() {
        return sorte;
    }

    public int getCritico() {
        return critico;
    }

    public int getResistencia() {
        return resistencia;
    }

    public int getExp() {
        return exp;
    }

    public int getLvl() {
        return lvl;
    }

    public ArrayList<Habilidade> getHbs() {
        return hbs;
    }

}
