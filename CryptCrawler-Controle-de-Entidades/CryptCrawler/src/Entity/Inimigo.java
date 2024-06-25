package Entity;

import combate.inimigos.Vilao;

public class Inimigo extends Entidade{
    private Player player;
    public Vilao classe;
    public Inimigo(String Nome, int x, int y, int icone, Player player, Vilao classe){
        super(true, Nome, x, y, icone);
        this.player = player;
        this.classe = classe;
    }
    public Vilao getClasse(){
        return classe;
    }
}
