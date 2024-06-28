package Entity;

import combate.inimigos.InimigoClasse;

public class Inimigo extends Entidade{

    private Player player;
    private InimigoClasse classe;

    public Inimigo(String Nome, int x, int y, int icone, Player player){
        super(true, Nome, x, y, icone);
        this.player = player;
    }
    public void setClasse(InimigoClasse classe) {
        this.classe = classe;
    }
    public InimigoClasse getClasse() {
        return classe;
    }
}
