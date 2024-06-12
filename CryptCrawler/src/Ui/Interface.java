package Ui;

import javax.swing.*;
import java.awt.*;

// Para funcionar, vá para o IntelliJ, clique com o botão direito no arquivo .jar e clique em "add as a library" (ultima opção)
import asciiPanel.*;

public class Interface extends JPanel {

    private final TelaDeJogo telaDeJogo;
    private final StatusJogador statusJogador;
    private final RelatorioJogo relatorioJogo;
    private final MiniMapa miniMapa;

    private final int telaDeJogoWidth = 45; // largura do painel ASCII (em caracteres)
    private final int telaDeJogoHeight = 30;
    private final int statusJogadorWidht = 80;
    private final int statusJogadorHeight = 12;
    private final int relatorioJogoWidht = 49;
    private final int relatorioJogoHeight = 30;
    private final int miniMapaWidht = 49;
    private final int miniMapaHeight = 35;

    private static final int FONT_AEROSMATICA_SIZE = 16;
    private static final int FONT_DRAKE_SIZE = 9;

    public Interface() {
        this.setFocusable(true);

        telaDeJogo = new TelaDeJogo(45, 30, AsciiFont.AEROSMATICA_16_16, 16);
        this.add(telaDeJogo.getTela());

        statusJogador = new StatusJogador(72, 11, AsciiFont.DRAKE_10x10, 10);
        this.add(statusJogador.getTela());

        relatorioJogo = new RelatorioJogo(44, 27, AsciiFont.DRAKE_10x10, 10);
        this.add(relatorioJogo.getTela());

        miniMapa = new MiniMapa(44, 32, AsciiFont.DRAKE_10x10, 10);
        this.add(miniMapa.getTela());

    }

    public void updateAsciiPanel(){

        char[][] matriz = new char[45][30];
        for (int i = 0; i < 45; i++) {
            for (int j = 0; j < 30; j++) {
                matriz[i][j] = '.';
            }
        }

        telaDeJogo.printMundo(matriz);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        telaDeJogo.printTexto("TELA DE JOGO 45 x 30", 1, 1);

        // Teste da lógica para adicionar informações
        relatorioJogo.textoUnico("t", 0, 0);

        for(int i = 0; i < 40; i++){
           relatorioJogo.atualizarInformacao("texto " + i, 1, 0);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        relatorioJogo.textoUnico("RELATORIO JOGO", 1, 1);

        statusJogador.printTela("STATUS JOGADOR", 1 , 1);

        miniMapa.printMatriz();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        miniMapa.printTela("MINI MAPA", 1, 1);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha fundo preto
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.GRAY);
        g.fillRect(20, 20, telaDeJogoWidth * FONT_AEROSMATICA_SIZE + 20, telaDeJogoHeight * FONT_AEROSMATICA_SIZE + 20);

        g.fillRect(20, 535, statusJogadorWidht * FONT_DRAKE_SIZE + 20, statusJogadorHeight * FONT_DRAKE_SIZE + 20);

        g.fillRect(780, 20, relatorioJogoWidht * FONT_DRAKE_SIZE + 20, relatorioJogoHeight * FONT_DRAKE_SIZE + 20);

        g.fillRect(780, 325, miniMapaWidht * FONT_DRAKE_SIZE + 20, miniMapaHeight * FONT_DRAKE_SIZE + 20 + 3);

        telaDeJogo.setBounds(30, 30);
        statusJogador.setBounds(30, 545);
        relatorioJogo.setBounds(790, 30);
        miniMapa.setBounds(790, 333);


    }
}
