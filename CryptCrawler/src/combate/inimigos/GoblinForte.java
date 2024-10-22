package combate.inimigos;

import combate.Habilidade;
import combate.TesteCombate;

public class GoblinForte extends Inimigo {

    public GoblinForte(int lvl) {
        super(lvl);
        leveling();
        this.hp_atual = this.hp_max;

        addHabs(0, 1, getForca(), 0.25f, "ATK BAS");
        addHabs(2, 2, getInteligencia(), 0.30f, "Hab4");
        addHabs(2, 2, getForca(), 0.40f, "Hab1");
        addHabs(3, 2, getInteligencia(), 0.50f, "Hab2");
    }

    @Override
    public void leveling() {
        this.forca = 4 + (1 * this.lvl);
        this.inteligencia = 4 + (1 * this.lvl);
        this.agilidade = 3 + (1 * this.lvl);
        this.sorte = 2 + (1 * this.lvl);
        this.critico = 1;

        this.hp_max = (12 + this.forca) + (1 * this.lvl);
    }

    @Override
    public int dano(Habilidade hb, float buff, int res_ini, int agi_def) {
        if (hb.getCd() > 0) {
            hb.setEm_cd(hb.getCd());
        }

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

        if (acertou) {
            TesteCombate.relatorioJogo(getClass().getSimpleName() + " acertou com sucesso");
        } else {
            TesteCombate.relatorioJogo(getClass().getSimpleName() + " não teve sucesso no acerto");
        }

        return acertou;
    }

    @Override
    public boolean txcrit() {
        int num_random = (int) (1 + (Math.random()) * 100);

        return this.sorte > num_random;
    }

}
