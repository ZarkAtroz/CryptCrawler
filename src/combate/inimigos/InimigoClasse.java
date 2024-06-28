package combate.inimigos;

import combate.Combatente;
import combate.Habilidade;
import combate.PersonagemCombate;

public abstract class InimigoClasse extends PersonagemCombate implements Combatente {

    protected int hp_atual;
    protected int hp_max;

    public InimigoClasse(int lvl) {
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

    public void colldownHabilidade() {
        for (Habilidade hab : hbs) {
            if (hab.getEm_cd() > 0) {
                hab.setEm_cd(hab.getEm_cd() - 1);
            }
        }
    }

    public Habilidade returnHabilidade(int index) {

        Habilidade aux = hbs.get(index);

        if (aux.getTipo_hb() == 1 || aux.getEm_cd() == 0) {
            return aux;
        } else {
            int new_index = index + 1;
            if (new_index >= getHbs().size()) {
                new_index = 0;
            }
            // Se o novo index for maior que o tamanho do vetor de habs vai para o atk bas
            return returnHabilidade(new_index);
        }

    }
    
}
