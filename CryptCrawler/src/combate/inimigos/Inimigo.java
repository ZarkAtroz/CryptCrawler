package combate.inimigos;

import combate.PersonagemCombate;

public abstract class Inimigo extends PersonagemCombate {

    protected int hp_atual;
    protected int hp_max;
    protected int cooldown_hb;

    public Inimigo(int lvl) {
        super(lvl);
    }
    
}
