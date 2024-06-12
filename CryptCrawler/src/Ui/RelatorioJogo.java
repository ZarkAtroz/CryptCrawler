package Ui;

import asciiPanel.AsciiFont;

import java.util.ArrayList;

public class RelatorioJogo extends Tela{

    private ArrayList<String> linhas;
    private ArrayList<String> historico;

    public RelatorioJogo(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
        this.linhas = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public ArrayList<String> getLinhas() {
        return linhas;
    }

    public ArrayList<String> getHistorico() {
        return historico;
    }

    public void setLinhas(ArrayList<String> linhas) {
        this.linhas = linhas;
    }

    public void setHistorico(ArrayList<String> historico) {
        this.historico = historico;
    }

    public void atualizarInformacao(String texto, int x, int y){

        if(linhas.size() == 26){
            historico.add(linhas.get(0));
            linhas.remove(0);
        }
        linhas.add(texto);

        getTela().clear();
        for (String linha : linhas) {
            getTela().write(linha, x, y++);
        }

        getTela().repaint();
    }

    public void textoUnico(String texto, int x, int y){
        getTela().clear();
        getTela().write(texto, x, y);
        // getTela().write(getWidth() + " x " + getHeight(), 1, 2);
        // getTela().write("QUANTIDADE REAL LINHAS = 26" , 1, 3);
        // getTela().write("TAMANHO MAXIMO LINHA = 48 COMECANDO PELO x0" , 1, 4);
        // getTela().write("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",0,6);
        getTela().repaint();
    }

}
