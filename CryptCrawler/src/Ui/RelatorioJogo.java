package Ui;

import asciiPanel.AsciiFont;

import java.awt.*;
import java.util.ArrayList;

public class RelatorioJogo extends Tela {

    private ArrayList<String> linhas;
    private ArrayList<Color> cores;
    private int linhaInicial = 0;
    private static final int MAX_LINHAS_VISIVEIS = 24;

    public RelatorioJogo(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
        this.linhas = new ArrayList<>();
        this.cores = new ArrayList<>();
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

    public ArrayList<Color> getCores() {
        return cores;
    }

    public void setCores(ArrayList<Color> cores) {
        this.cores = cores;
    }

    public void decrementarLinha() {
        if (this.getLinhaInicial() != 0)
            this.setLinhaInicial(this.getLinhaInicial() - 1);
    }

    public void encrementarLinha() {
        if (getLinhaInicial() < (linhas.size() - MAX_LINHAS_VISIVEIS))
            this.setLinhaInicial(this.getLinhaInicial() + 1);
    }

    public void setLinhaFim() {
        if (!linhas.isEmpty())
            this.setLinhaInicial(linhas.size() - MAX_LINHAS_VISIVEIS);
    }

    public void atualizarInformacao(String texto, Color cor) {
        if (linhas.size() == 100) {
            linhas.remove(0);
            cores.remove(0);
        }

        if ((linhas.size() - linhaInicial) == MAX_LINHAS_VISIVEIS) {
            linhaInicial++;
        }

        linhas.add(texto);
        cores.add(cor);

    }

    public void imprimirRelatorios() {
        if (!linhas.isEmpty()) {
            getTela().clear();

            int y = 2; // Linha inicial na tela para impressão

            // Itera sobre as linhas a partir de linhaInicial até o final da lista ou até preencher a tela
            for (int i = linhaInicial; i < linhas.size() && y < MAX_LINHAS_VISIVEIS + 2; i++) {
                String linha = linhas.get(i);
                Color cor = cores.get(i);

                getTela().write("> ", 0, y);
                getTela().write(linha, 2, y++, cor, Color.BLACK);
            }

            // Exibe a linha atual e o total de linhas no topo da tela
            getTela().write((char) 25 + " LINHA ATUAL: " + getLinhaInicial() + "/" + linhas.size(), 0, 0);
        }
    }
}
