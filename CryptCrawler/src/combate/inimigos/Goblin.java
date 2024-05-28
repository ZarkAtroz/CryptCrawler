package combate.inimigos;

import combate.Combatente;

public class Goblin extends Inimigo implements Combatente {

    public Goblin(int lvl) {
        super(lvl);
    }

    @Override
    public int dano(int status, float hab_mod, float buff, int res_ini, int agi_def) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dano'");
    }

    @Override
    public boolean acerto(int agi_atk, int agi_def) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acerto'");
    }

    @Override
    public boolean txcrit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'txcrit'");
    }

    @Override
    public void leveling() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'leveling'");
    }
    
}
