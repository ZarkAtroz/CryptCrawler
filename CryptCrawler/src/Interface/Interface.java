package Interface;

import javax.swing.*;
import java.awt.*;

// Para funcionar, vá para o IntelliJ, clique com o botão direito no arquivo .jar e clique em "add as a library" (ultima opção)
import asciiPanel.*;

public class Interface extends JPanel {

    private AsciiPanel telaDeJogo;
    private AsciiPanel statusJogador;
    private AsciiPanel relatorioJogo;
    private AsciiPanel miniMapa;

    private int telaDeJogoWidth = 60; // largura do painel ASCII (em caracteres)
    private int telaDeJogoHeight = 40;
    private int statusJogadorWidht = 80;
    private int statusJogadorHeight = 12;
    private int relatorioJogoWidht = 49;
    private int relatorioJogoHeight = 30;
    private int miniMapaWidht = 49;
    private int miniMapaHeight = 35;

    private static final int FONT_TALRYTH_SIZE = 15;
    private static final int FONT_DRAKE_SIZE = 9;

    public Interface() {
        this.setFocusable(true);

        telaDeJogo = new AsciiPanel(telaDeJogoWidth * 12, telaDeJogoHeight * 12, AsciiFont.DULLARD_12_12);
        telaDeJogo.setSize(telaDeJogoWidth * 12, telaDeJogoHeight * 12);
        telaDeJogo.setBounds(30, 30, telaDeJogoWidth * 12, telaDeJogoHeight * 12);
        this.add(telaDeJogo);

        statusJogador = new AsciiPanel(statusJogadorWidht * FONT_DRAKE_SIZE, statusJogadorHeight * FONT_DRAKE_SIZE, AsciiFont.DRAKE_10x10);
        statusJogador.setSize(statusJogadorWidht  * FONT_DRAKE_SIZE, statusJogadorHeight * FONT_DRAKE_SIZE);
        statusJogador.setBounds(30, 545, statusJogadorWidht * FONT_DRAKE_SIZE, statusJogadorHeight * FONT_DRAKE_SIZE);
        this.add(statusJogador);

        relatorioJogo = new AsciiPanel(relatorioJogoWidht * FONT_DRAKE_SIZE, relatorioJogoHeight * FONT_DRAKE_SIZE, AsciiFont.DRAKE_10x10);
        relatorioJogo.setSize(relatorioJogoWidht * FONT_DRAKE_SIZE, relatorioJogoHeight * FONT_DRAKE_SIZE);
        relatorioJogo.setBounds(790, 30, relatorioJogoWidht * FONT_DRAKE_SIZE, relatorioJogoHeight * FONT_DRAKE_SIZE);
        this.add(relatorioJogo);

        miniMapa = new AsciiPanel(miniMapaWidht * FONT_DRAKE_SIZE, miniMapaHeight * FONT_DRAKE_SIZE, AsciiFont.DRAKE_10x10);
        miniMapa.setSize(miniMapaWidht * FONT_DRAKE_SIZE, miniMapaHeight * FONT_DRAKE_SIZE);
        miniMapa.setBounds(790, 337, miniMapaWidht * FONT_DRAKE_SIZE, miniMapaHeight * FONT_DRAKE_SIZE);
        this.add(miniMapa);

    }

    public void updateAsciiPanel(){

        telaDeJogo.clear();
        statusJogador.clear();
        relatorioJogo.clear();
        miniMapa.clear();

        telaDeJogo.write(((char) 1) + "" + ((char) 2), 0, 0);

        statusJogador.write("STATUS JOGADOR", 1, 1);
        relatorioJogo.write("RELATORIO JOGO", 1, 1);
        miniMapa.write("MINI MAPA", 1, 1);

        statusJogador.repaint();
        telaDeJogo.repaint();
        relatorioJogo.repaint();
        miniMapa.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha fundo preto
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.GRAY);
        g.fillRect(20, 20, telaDeJogoWidth * 12 + 20, telaDeJogoHeight * 12 + 20);

        g.fillRect(20, 535, statusJogadorWidht * FONT_DRAKE_SIZE + 20, statusJogadorHeight * FONT_DRAKE_SIZE + 20);

        g.fillRect(780, 20, relatorioJogoWidht * FONT_DRAKE_SIZE + 20, relatorioJogoHeight * FONT_DRAKE_SIZE + 20);

        g.fillRect(780, 327, miniMapaWidht * FONT_DRAKE_SIZE + 20, miniMapaHeight * FONT_DRAKE_SIZE + 20);

        telaDeJogo.setBounds(30, 30, telaDeJogoWidth * 12, telaDeJogoHeight * 12);
        statusJogador.setBounds(30, 545, statusJogadorWidht * FONT_DRAKE_SIZE, statusJogadorHeight * FONT_DRAKE_SIZE);
        relatorioJogo.setBounds(790, 30, relatorioJogoWidht * FONT_DRAKE_SIZE, relatorioJogoHeight * FONT_DRAKE_SIZE);
        miniMapa.setBounds(790, 337, miniMapaWidht * FONT_DRAKE_SIZE, miniMapaHeight * FONT_DRAKE_SIZE);


    }
}
