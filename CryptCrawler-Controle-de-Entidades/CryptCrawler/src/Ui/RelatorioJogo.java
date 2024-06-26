package Ui;

import asciiPanel.AsciiFont;

import java.awt.*;
import java.util.ArrayList;

public class RelatorioJogo extends Tela{

    private ArrayList<String> linhas;
    private int linhaInicial = 0;

    public RelatorioJogo(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
        this.linhas = new ArrayList<>();
    }

    public ArrayList<String> getLinhas() {
        return linhas;
    }

    public void setLinhas(ArrayList<String> linhas) {
        this.linhas = linhas;
    }

    public int getLinhaInicial() {
        return linhaInicial;
    }

    public void setLinhaInicial(int linhaInicial) {
        this.linhaInicial = linhaInicial;
    }

    public void decrementarLinha(){
        if(this.getLinhaInicial() != 0)
            this.setLinhaInicial(this.getLinhaInicial() - 1);
    }

    public void encrementarLinha(){
        if(getLinhaInicial() < (linhas.size() - 26))
            this.setLinhaInicial(this.getLinhaInicial() + 1);
    }

    public void setLinhaFim(){
        if(!linhas.isEmpty())
            this.setLinhaInicial(linhas.size() - 26);
    }

    public void atualizarInformacao(String texto){

        if(linhas.size() >= 100)
            linhas.remove(0);

        if((linhas.size() - linhaInicial) == 26){
            linhaInicial++;
        }
        linhas.add(texto);

    }

    public void imprimirRelatorios(){
        if(!linhas.isEmpty()){
            getTela().clear();

            int y = 2;
            for(int i = linhaInicial; i < linhas.size(); i++){
                String linha = linhas.get(i);
                if(y < 26){
                    getTela().write(">", 0, y);
                    getTela().write(linha, 2, y++, Color.WHITE, Color.BLACK);
                }
            }

            getTela().write((char)25 + " LINHA ATUAL: " + getLinhaInicial() + "/" + linhas.size(), 0, 0);
        }
    }
}
