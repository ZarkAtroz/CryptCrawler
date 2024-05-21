package combate;

public interface Combatente {

    public int dano(int status, float hab_mod, float buff, int res_ini, int agi_def);

    public boolean acerto(int agi_atk, int agi_def);

    public boolean txcrit();

}
