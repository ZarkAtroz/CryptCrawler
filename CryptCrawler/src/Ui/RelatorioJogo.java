package Ui;

import asciiPanel.AsciiFont;

import java.util.ArrayList;

public class RelatorioJogo extends Tela{

    private ArrayList<String> linhas;

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

    public void adicionarInformacao(String texto){

    }

    public void atualizarInformacao(String texto, int x, int y){

        if(linhas.size() == 26){
            linhas.remove(0);
        }
        linhas.add(texto);

        getTela().clear();
        for(int i = 0; i < linhas.size(); i++){
            getTela().write(linhas.get(i), x, y++);
        }

        getTela().repaint();
    }

    public void textoUnico(String texto, int x, int y){
        getTela().clear();
        getTela().write(texto, x, y);
        getTela().write(getWidth() + " x " + getHeight(), 1, 2);
        getTela().write("QUANTIDADE REAL LINHAS = 26" , 1, 3);
        getTela().write("TAMANHO MAXIMO LINHA = 43" , 1, 4);
        getTela().repaint();
    }

}
