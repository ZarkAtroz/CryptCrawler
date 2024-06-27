package combate.herois;

import combate.Combatente;
import combate.Habilidade;
import combate.PersonagemCombate;

public abstract class AliadoClasse extends PersonagemCombate implements Combatente {

    protected int hp_atual;
    protected int hp_max;
    protected int mp_atual;
    protected int mp_max;

    public AliadoClasse(int lvl) {
        super(lvl);
    }

    // Getters e Setters

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

    public int getMp_atual() {
        return mp_atual;
    }

    public void setMp_atual(int mp_atual) {
        this.mp_atual = mp_atual;
    }

    public int getMp_max() {
        return mp_max;
    }

    public void setMp_max(int mp_max) {
        this.mp_max = mp_max;
    }

    @Override
    public void addHabs(int mp, int tp, int status, float mod, String nome) {
        Habilidade hab = new Habilidade(tp, status, mod, nome);
        hab.heroiHab(mp);

        hbs.add(hab);
    }
}
