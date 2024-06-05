package combate;

public class Habilidade {
    private int cd; // Colldown da habilidade
    private boolean em_cd; // está em colldown a habilidade

    private int custo_mp;

    private int tipo_hb;
    /*
     * 1 = dano
     * 2 = buff
     */

    private int status;
    private float modficador;
    private String nome_hab;


    public Habilidade(int tipo_hb, int status, float modficador, String nome_hab) {
        this.tipo_hb = tipo_hb;
        this.status = status;
        this.modficador = modficador;
        this.nome_hab = nome_hab;
    }


// Funcoes
    public void heroiHab(int mp) {
        custo_mp = mp;
    }

    public void inimigoHab(int cd) {
        this.cd = cd;
    }


// Getters e Setters
    public int getCd() {
        return cd;
    }
    public void setCd(int cd) {
        this.cd = cd;
    }


    public boolean isEm_cd() {
        return em_cd;
    }
    public void setEm_cd(boolean em_cd) {
        this.em_cd = em_cd;
    }


    public int getCusto_mp() {
        return custo_mp;
    }
    public void setCusto_mp(int custo_mp) {
        this.custo_mp = custo_mp;
    }


    public int getTipo_hb() {
        return tipo_hb;
    }
    public void setTipo_hb(int tipo_hb) {
        this.tipo_hb = tipo_hb;
    }


    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }


    public float getModficador() {
        return modficador;
    }
    public void setModficador(float modficador) {
        this.modficador = modficador;
    }


    public String getNome_hab() {
        return nome_hab;
    }
    public void setNome_hab(String nome_hab) {
        this.nome_hab = nome_hab;
    }


    @Override
    public String toString() {
        return "Habilidade [cd=" + cd + ", em_cd=" + em_cd + ", custo_mp=" + custo_mp + ", tipo_hb=" + tipo_hb
                + ", status=" + status + ", modficador=" + modficador + ", nome_hab=" + nome_hab + "]";
    }
}
