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
    protected ArrayList<Habilidade> hbs;

    // Status adicionais
    protected int hp_atual;
    protected int hp_max;
    protected int mp_atual;
    protected int mp_max;

    // Construtores
    public PersonagemCombate(int lvl) {
        this.lvl = lvl;
    }

    // Funcões
    public abstract void leveling();

}
