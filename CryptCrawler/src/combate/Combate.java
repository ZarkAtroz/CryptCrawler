package combate;

import Ui.Interface;
import combate.herois.AliadoClasse;
import combate.inimigos.InimigoClasse;

import java.awt.*;
import java.util.ArrayList;

public class Combate {

    private ArrayList<AliadoClasse> herois = new ArrayList<>();

    private ArrayList<InimigoClasse> inimigos = new ArrayList<>();

    private int index_heroi_atual; // Colocar no construtor depois
    private int index_inimigo_atual;

    public AliadoClasse heroi_atual;
    public InimigoClasse inimigo_atual;

    private boolean turno_heroi;

    public Combate(int i_heroi, int i_inimigo, ArrayList<AliadoClasse> hr) {
        herois = hr;

        index_heroi_atual = i_heroi;
        index_inimigo_atual = i_inimigo;

        heroi_atual = herois.get(index_heroi_atual);
    }

    public void setInimigos(ArrayList<InimigoClasse> in){
        this.inimigos = in;
        inimigo_atual = inimigos.get(0);
        turno_heroi = (heroi_atual.getAgilidade() > inimigo_atual.getAgilidade());
    }

    public boolean isEmptyEnemyies(){
        return inimigos.isEmpty();
    }

    public void atacar(int index_hb, int index_personagem, int indexInimigo, Interface inter_jogo) {

        InimigoClasse inimigoSelecionado = inimigos.get(indexInimigo);

        if (inimigo_atual.getHp_atual() <= 0) {
            inter_jogo.getRelatorioJogo().atualizarInformacao(inimigo_atual.getClass().getSimpleName().toUpperCase() + " MORREU!", Color.GREEN);
            inimigos.remove(inimigo_atual);
            proximoInimigo();
        }

        if (heroi_atual.getHp_atual() <= 0) {
            trocaPersonagem(index_heroi_atual + 1, 0);
        }

        if (turno_heroi) {
            int dano = heroi_atual.dano(heroi_atual.getHbs().get(index_hb), 1, 1, inimigoSelecionado.getAgilidade());
            int n_hp_atual = inimigoSelecionado.getHp_atual() - dano;
            inimigoSelecionado.setHp_atual(n_hp_atual);

            if (inimigoSelecionado.getHp_atual() <= 0) {
                inter_jogo.getRelatorioJogo().atualizarInformacao(inimigoSelecionado.getClass().getSimpleName().toUpperCase() + " MORREU!", Color.GREEN);
                inimigos.remove(inimigoSelecionado);
                proximoInimigo();
            }

            turno_heroi = false;

            String nomeAliado = heroi_atual.getClass().getSimpleName().toUpperCase();
            if(nomeAliado.equals("MAGOELEMENTAL"))
                nomeAliado = "MAGO";

            if (dano <= 0) {
                inter_jogo.getRelatorioJogo().atualizarInformacao(nomeAliado + " ERROU O ATAQUE...", Color.WHITE);
            } else {
                inter_jogo.getRelatorioJogo().atualizarInformacao(nomeAliado + " ACERTOU O ATAQUE DANDO: " + dano, Color.GREEN);
            }

        } else {
            index_hb = (int) Math.random() * inimigo_atual.getHbs().size();

            Habilidade hb = inimigo_atual.returnHabilidade(index_hb);
            int dano = inimigo_atual.dano(hb, 1, 1, heroi_atual.getAgilidade());
            int n_hp_atual = heroi_atual.getHp_atual() - dano;
            heroi_atual.setHp_atual(n_hp_atual);

            turno_heroi = true;
            inimigo_atual.colldownHabilidade();

            String nomeInimigo = inimigo_atual.getClass().getSimpleName().toUpperCase();
            if(nomeInimigo.equals("GOBLINFORTE"))
                nomeInimigo = "GOBLIN";

            if (dano <= 0) {
                inter_jogo.getRelatorioJogo().atualizarInformacao(nomeInimigo + " ERROU O ATAQUE...", Color.WHITE);
            } else {
                inter_jogo.getRelatorioJogo().atualizarInformacao(nomeInimigo + " ACERTOU O ATAQUE DANDO: "+ dano, Color.RED);
            }
        }

    }

    public void trocaPersonagem(int index_h, int cont) {

        if (cont >= herois.size()) {
            return;
        }

        if (index_h >= herois.size()) {
            index_h = 0;
        }

        index_heroi_atual = index_h;
        heroi_atual = herois.get(index_heroi_atual);

        if (heroi_atual.getHp_atual() <= 0) {
            trocaPersonagem(index_h + 1, cont + 1);
        }
    }

    public boolean proximoInimigo() {
        int i = index_inimigo_atual + 1;
        if (inimigos.isEmpty()) {
            return false;
        } else {
            if (i >= inimigos.size()) {
                i = 0;
            }
            inimigo_atual = inimigos.get(i);
            index_inimigo_atual = i;
        }
        return true;
    }

    public void updateInterface(Interface interfaceJogo, int typeEvent){
        interfaceJogo.getStatusJogador().getTela().clear();

        String[] controles = new String[3];
        AliadoClasse heroi = heroi_atual;
        int num;

        switch(typeEvent){
            case 0:
                controles[0] = "CONTROLES:";
                controles[1] = "[Q] SELEICONAR HAB";
                controles[2] = "[E] TROCAR PERS";
                for(int i = 0; i < 3; i++){
                    interfaceJogo.getStatusJogador().printTela(controles[i], 1, (i + 1));
                }

                interfaceJogo.getStatusJogador().printTela("HEROI SELECIONADO:", 24, 1);
                interfaceJogo.getStatusJogador().printTela(heroi.getClass().getSimpleName().toUpperCase(), 24, 2);
                interfaceJogo.getStatusJogador().printTela("HP: " + heroi.getHp_atual() + "/" + heroi.getHp_max(), 24, 3);
                interfaceJogo.getStatusJogador().printTela("MP: " + heroi.getMp_atual() + "/" + heroi.getMp_max(), 24, 4);

                interfaceJogo.getStatusJogador().printTela("INIMIGOS INFO:", 50, 1);
                num = 1;
                for (InimigoClasse inimigo : inimigos) {
                    String nomeInimigo = inimigo.getClass().getSimpleName().toUpperCase();
                    if(nomeInimigo.equals("GOBLINFORTE")){
                        nomeInimigo = "GOBLIN";
                    }
                    interfaceJogo.getStatusJogador().printTela(nomeInimigo + " " + (num) + " - HP: " + inimigo.getHp_atual() + "/" + inimigo.getHp_max() , 45, (num++));
                }
                break;
            case 1:
                interfaceJogo.getStatusJogador().printTela("SELECIONAR HAB: ", 1, 1);
                for (int i = 0; i < heroi.getHbs().size(); i++) {
                    Habilidade hb_1 = heroi.getHbs().get(i);
                    interfaceJogo.getStatusJogador().printTela("[" + (i+1) + "]" + hb_1.getNome_hab(), 1, (i + 2));
                }

                interfaceJogo.getStatusJogador().printTela("HEROI SELECIONADO:", 24, 1);
                interfaceJogo.getStatusJogador().printTela(heroi.getClass().getSimpleName().toUpperCase(), 24, 2);
                interfaceJogo.getStatusJogador().printTela("HP: " + heroi.getHp_atual() + "/" + heroi.getHp_max(), 24, 3);
                interfaceJogo.getStatusJogador().printTela("MP: " + heroi.getMp_atual() + "/" + heroi.getMp_max(), 24, 4);

                interfaceJogo.getStatusJogador().printTela("INIMIGOS INFO:", 50, 1);
                num = 1;
                for (InimigoClasse inimigo : inimigos) {
                    String nomeInimigo = inimigo.getClass().getSimpleName().toUpperCase();
                    if(nomeInimigo.equals("GOBLINFORTE")){
                        nomeInimigo = "GOBLIN";
                    }
                    interfaceJogo.getStatusJogador().printTela(nomeInimigo + " " + (num) + " - HP: " + inimigo.getHp_atual() + "/" + inimigo.getHp_max() , 45, (num++));
                }
                break;
            case 2:
                interfaceJogo.getStatusJogador().printTela("SELECIONAR ALIADO:", 1, 1);
                int i = 1;
                for (AliadoClasse h: getHerois()) {
                    String nomeAliado = h.getClass().getSimpleName().toUpperCase();
                    if(nomeAliado.equals("MAGOELEMENTAL"))
                        nomeAliado = "MAGO ELEMENTAL";
                    interfaceJogo.getStatusJogador().printTela("[" + (i++) + "] " + nomeAliado, 1, i);
                }

                interfaceJogo.getStatusJogador().printTela("HEROI SELECIONADO:", 24, 1);
                interfaceJogo.getStatusJogador().printTela(heroi.getClass().getSimpleName().toUpperCase(), 24, 2);
                interfaceJogo.getStatusJogador().printTela("HP: " + heroi.getHp_atual() + "/" + heroi.getHp_max(), 24, 3);
                interfaceJogo.getStatusJogador().printTela("MP: " + heroi.getMp_atual() + "/" + heroi.getMp_max(), 24, 4);

                interfaceJogo.getStatusJogador().printTela("INIMIGOS INFO:", 50, 1);
                num = 1;
                for (InimigoClasse inimigo : inimigos) {
                    String nomeInimigo = inimigo.getClass().getSimpleName().toUpperCase();
                    if(nomeInimigo.equals("GOBLINFORTE")){
                        nomeInimigo = "GOBLIN";
                    }
                    interfaceJogo.getStatusJogador().printTela(nomeInimigo + " " + (num) + " - HP: " + inimigo.getHp_atual() + "/" + inimigo.getHp_max() , 45, (num++));
                }
                break;
            case 3:
                interfaceJogo.getStatusJogador().printTela("SELECIONAR INIMIGO: ", 1, 1);
                for(i = 0; i < inimigos.size(); i++){
                    InimigoClasse in = inimigos.get(i);
                    String nomeInimigo =  in.getClass().getSimpleName().toUpperCase();
                    if(nomeInimigo.equals("GOBLINFORTE"))
                        nomeInimigo = "GOBLIN";
                    interfaceJogo.getStatusJogador().printTela("[" + (i+1) + "]" + nomeInimigo + (i + 1), 1, (i + 2));
                }

                interfaceJogo.getStatusJogador().printTela("HEROI SELECIONADO:", 24, 1);
                interfaceJogo.getStatusJogador().printTela(heroi.getClass().getSimpleName().toUpperCase(), 24, 2);
                interfaceJogo.getStatusJogador().printTela("HP: " + heroi.getHp_atual() + "/" + heroi.getHp_max(), 24, 3);
                interfaceJogo.getStatusJogador().printTela("MP: " + heroi.getMp_atual() + "/" + heroi.getMp_max(), 24, 4);

                interfaceJogo.getStatusJogador().printTela("INIMIGOS INFO:", 50, 1);
                num = 1;
                for (InimigoClasse inimigo : inimigos) {
                    String nomeInimigo = inimigo.getClass().getSimpleName().toUpperCase();
                    if(nomeInimigo.equals("GOBLINFORTE")){
                        nomeInimigo = "GOBLIN";
                    }
                    interfaceJogo.getStatusJogador().printTela(nomeInimigo + " " + (num) + " - HP: " + inimigo.getHp_atual() + "/" + inimigo.getHp_max() , 45, (num++));
                }
                break;
            case 4:
                break;
        }
    }

    // Getter Setter
    public ArrayList<AliadoClasse> getHerois() {
        return herois;
    }

    public int getIndex_heroi_atual() {
        return index_heroi_atual;
    }

    public int getIndex_inimigo_atual() {
        return index_inimigo_atual;
    }

    public boolean isTurno_heroi() {
        return turno_heroi;
    }
}
