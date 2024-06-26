package combate.herois;

import combate.Habilidade;

public class Rogue extends AliadoClasse {

    
    // Construtor
    public Rogue(int lvl) {
        super(lvl);
        leveling();
        this.hp_atual = this.hp_max;
        this.mp_atual = this.mp_max;
        addHabs(0, 1, (getAgilidade() + getSorte()), 0.25f, "ATK BASICO");
        addHabs(4, 2, (getAgilidade() + getSorte()), 0.50f, "Estocada");
    }

    // Funções da classe PersonagemCombate
    @Override
    public void leveling() {
        this.forca = 1 + (1 * this.lvl);
        this.agilidade = 2 + (2 * this.lvl);
        this.inteligencia = (int) (1 + (1.5 * this.lvl));
        this.sorte = 2 + (3 * this.lvl) + 2;
        this.critico = 3;

        this.hp_max = (16 + this.forca) + (2 * lvl);
        this.mp_max = (16 + this.inteligencia) + (3 * lvl);
    }

    // Funções da interface
    @Override
    public int dano(Habilidade hb, float buff, int res_ini, int agi_def) {
        if (this.mp_atual >= hb.getCusto_mp()) {
            this.mp_atual -= hb.getCusto_mp();
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
