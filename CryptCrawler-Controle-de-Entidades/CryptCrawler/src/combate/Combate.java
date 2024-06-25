package combate;

import java.util.ArrayList;

import Entity.Aliado;
import Entity.Inimigo;
import Entity.Player;
import combate.herois.Heroi;
import combate.inimigos.Vilao;

public class Combate {

    private ArrayList<Aliado> aliados = new ArrayList<>();
    private ArrayList<Inimigo> inimigos = new ArrayList<>();
    private ArrayList<Heroi> herois = new ArrayList<>();
    private ArrayList<Vilao> viloes = new ArrayList<>();
    private Player player;

    private int index_heroi_atual; // Colocar no construtor depois
    private int index_inimigo_atual;

    public Heroi heroi_atual;
    public Vilao inimigo_atual;

    private boolean turno_heroi;

    public Combate(Player player) {
        for(Aliado aliado : aliados){
            herois.add(aliado.getClasse());
        }
        for(Inimigo inimigo : inimigos){
            viloes.add(inimigo.getClasse());
        }
        this.player = player;
        turno_heroi = true;
    }


    public void habilidade(int index_hab) {
        if(turno_heroi){
            heroi_atual.dano(heroi_atual.get_Hab(index_hab), 1, inimigo_atual.getResistencia(), inimigo_atual.getAgilidade());
            turno_heroi = false;
        }
        else{
            inimigo_atual.dano(inimigo_atual.get_Hab(index_hab), 1, heroi_atual.getResistencia(), heroi_atual.getAgilidade());
            turno_heroi = true;
        }
    }

    public int Vencedor(){
        if (herois.size() == 0){
            return 0;
        }
        if(inimigos.size() == 0){
            return 1;
        }
        return -1;
    }

    public void addAliado(Aliado aliado){
        aliados.add(aliado);
        herois.add(aliado.getClasse());
    }

    public void addInimigo(Inimigo inimigo){
        inimigos.add(inimigo);
        viloes.add(inimigo.getClasse());
    }



    // Getter Setter
    public ArrayList<Heroi> getHerois() {
        return herois;
    }
    public ArrayList<Vilao> getViloes() {
        return viloes;
    }
    public ArrayList<Aliado> getAliados() {
        return aliados;
    }
    public ArrayList<Inimigo> getInimigos() {
        return inimigos;
    }
    public Heroi getAliado_Atual(int index) {
        return herois.get(index);
    }
    public Vilao getVilao_Atual(int index) {
        return viloes.get(index);
    }
    public void setAliadoAtual(int index) {
        heroi_atual = herois.get(index);
    }
    public void setInimigoAtual(int index) {
        inimigo_atual = viloes.get(index);
    }
    public void setPlayerAsAliado(){
        heroi_atual = player.getClasse();
    }
    public Aliado getAliado(int index) {
        return aliados.get(index);
    }
    public Inimigo getInimigo(int index) {
        return inimigos.get(index);
    }
    public Player getPlayer() {
        return player;
    }
}
