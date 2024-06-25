package combate.herois;

import combate.Habilidade;

public class Healer extends Heroi {

    // Construtor
    public Healer(int lvl) {
        super(lvl);
        leveling();
        this.hp_atual = this.hp_max;
        this.mp_atual = this.mp_max;

        addHabs(0, 1, getInteligencia(), 0.25f, "ATK BASICO");
        addHabs(4, 3, (int) (getInteligencia() * 0.5), 0, "Cura");
        addHabs(3, 3, (int) (getInteligencia() * 0.5), 0, "Buff Dano");
        addHabs(0, 3, (int) (getInteligencia() * 0.5), 0, "Buff Res");
    }

    // Funções da classe PersonagemCombate
    @Override
    public void leveling() {
        this.forca = 1 + (1 * this.lvl);
        this.agilidade = 2 + (1 * this.lvl) + 1;
        this.inteligencia = 3 + (3 * this.lvl) + 2;
        this.sorte = 1 + (1 * this.lvl);
        this.critico = 1;

        this.hp_max = (11 + this.forca) + (1 * lvl);
        this.mp_max = (17 + this.inteligencia) + (4 * lvl);
    }

    // Funções da interface
    @Override
    public int dano(Habilidade hb, float buff, int res_ini, int agi_def) {
        if (acerto(this.agilidade, agi_def)) {
            int res = res_ini / 2;
            if (res < 1) {
                res = 1;
            }
            
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

        boolean acertou = necessario > num_random;

        return acertou;
    }

    @Override
    public boolean txcrit() {
        int num_random = (int) (1 + (Math.random()) * 100);

        return this.sorte > num_random;
    }
}

