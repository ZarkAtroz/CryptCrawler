package combate.herois;

import combate.Combatente;
import combate.Habilidade;

public class Guerreiro extends Heroi implements Combatente{

    // Construtor
    public Guerreiro(int lvl) {
        super(lvl);
        leveling();
        this.hp_atual = this.hp_max;
        this.mp_atual = this.mp_max;

        addHabs(0, 1, getForca(), 0.25f, "ATK BASICO");
        addHabs(4, 1, getForca(), 0.40f, "Roda roda Jeckit");
    }

    // Funções da classe PersonagemCombate
    @Override
    public void leveling() {
        this.forca = 2 + (3 * this.lvl) + 2;
        this.agilidade = 2 + (1 * this.lvl);
        this.inteligencia = 1 + (1 * this.lvl) + 1;
        this.sorte = 1 + (1 * this.lvl);
        this.critico = 1;

        this.hp_max = (20 + this.forca) + (3 * lvl);
        this.mp_max = (13 + this.inteligencia) + (2 * lvl);
    }

    // Funções da interface
    @Override
    public int dano(Habilidade hb, float buff, int res_ini, int agi_def) {
        if (acerto(this.agilidade, agi_def)) {
            int res = res_ini / 2;
            int dmg = (int) (hb.getStatus() * hb.getModficador() * buff);

            if (txcrit()) {
                dmg = dmg * this.critico;
            }

            return (int) (dmg / res);
        }
        return 0;
    }

    @Override
    public boolean acerto(int agi_atk, int agi_def) {
        int total = agi_atk + agi_def;

        int necessario = 100 * agi_atk / total;
        int num_random = (int) (1 + (Math.random() * 100));

        System.out.println("Acerto: " + (necessario > num_random));
        return necessario > num_random;
    }

    @Override
    public boolean txcrit() {
        int num_random = (int) (1 + (Math.random()) * 100);

        return this.sorte > num_random;
    }

}

