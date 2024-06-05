package combate.inimigos;

import combate.Habilidade;
import combate.PersonagemCombate;

public abstract class Inimigo extends PersonagemCombate {

    protected int hp_atual;
    protected int hp_max;
    protected int cooldown_hb;

    public Inimigo(int lvl) {
        super(lvl);
    }

    @Override
    public void addHabs(int cd, int tp, int status, float mod, String nome) {
        Habilidade hab = new Habilidade(tp, status, mod, nome);
        hab.inimigoHab(cd);

        hbs.add(hab);
    }
}
