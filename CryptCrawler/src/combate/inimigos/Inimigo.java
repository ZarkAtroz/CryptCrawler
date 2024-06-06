package combate.inimigos;

import combate.Combatente;
import combate.Habilidade;
import combate.PersonagemCombate;

public abstract class Inimigo extends PersonagemCombate implements Combatente {

    protected int hp_atual;
    protected int hp_max;

    public Inimigo(int lvl) {
        super(lvl);
    }

    @Override
    public void addHabs(int cd, int tp, int status, float mod, String nome) {
        Habilidade hab = new Habilidade(tp, status, mod, nome);
        hab.inimigoHab(cd);

        hbs.add(hab);
    }

    public int getHp_atual() {
        return hp_atual;
    }

    public void setHp_atual(int hp_atual) {
        this.hp_atual = hp_atual;
    }

    public int getHp_max() {
        return hp_max;
    }

    public void setHp_max(int hp_max) {
        this.hp_max = hp_max;
    }


    
}
