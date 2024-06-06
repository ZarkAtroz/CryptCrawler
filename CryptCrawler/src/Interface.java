import javax.swing.*;
import java.awt.*;

// Para funcionar, vá para o IntelliJ, clique com o botão direito no arquivo .jar e clique em "add as a library" (ultima opção)
import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

public class Interface extends JPanel {

    private AsciiPanel telaDeJogo;
    private AsciiPanel statusJogador;
    private AsciiPanel relatorioJogo;
    private AsciiPanel miniMapa;

    private int telaDeJogoWidth = 48; // largura do painel ASCII (em caracteres)
    private int telaDeJogoHeight = 32; // altura do painel ASCII (em caracteres)
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

        telaDeJogo = new AsciiPanel(telaDeJogoWidth * FONT_TALRYTH_SIZE, telaDeJogoHeight * FONT_TALRYTH_SIZE, AsciiFont.TALRYTH_15_15);
        telaDeJogo.setSize(telaDeJogoWidth * FONT_TALRYTH_SIZE, telaDeJogoHeight * FONT_TALRYTH_SIZE);
        telaDeJogo.setBounds(30, 30, telaDeJogoWidth * FONT_TALRYTH_SIZE, telaDeJogoHeight * FONT_TALRYTH_SIZE);
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

        updateAsciiPanel();
    }

    private void updateAsciiPanel(){

        telaDeJogo.clear();
        statusJogador.clear();
        relatorioJogo.clear();
        miniMapa.clear();

        // Exemplo de uma sala vazia
        // largura: 80 | altura: 24 (em pixels)
        char[][] matriz = new char[telaDeJogoWidth][telaDeJogoHeight];

        for(int i = 0; i < 48; i++){
            for(int j = 0; j < 32; j++){
                matriz[i][j] = '.';
            }
        }

        matriz[3][3] = (char) 9;

        telaDeJogo.write("Tela de jogo", 1, 1);
        for(int i = 0; i < 48; i++){
            for(int j = 0; j < 32; j++){
                telaDeJogo.write(matriz[i][j], i, j);
            }
        }


        telaDeJogo.write(((char) 24) + "48", 45, 0);
        telaDeJogo.write(((char) 27) + "32", 0, 31);

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
        g.fillRect(20, 20, telaDeJogoWidth * 15 + 20, telaDeJogoHeight * 15 + 20);

        g.fillRect(20, 535, statusJogadorWidht * FONT_DRAKE_SIZE + 20, statusJogadorHeight * FONT_DRAKE_SIZE + 20);

        g.fillRect(780, 20, relatorioJogoWidht * FONT_DRAKE_SIZE + 20, relatorioJogoHeight * FONT_DRAKE_SIZE + 20);

        g.fillRect(780, 327, miniMapaWidht * FONT_DRAKE_SIZE + 20, miniMapaHeight * FONT_DRAKE_SIZE + 20);

        telaDeJogo.setBounds(30, 30, telaDeJogoWidth * FONT_TALRYTH_SIZE, telaDeJogoHeight * FONT_TALRYTH_SIZE);
        statusJogador.setBounds(30, 545, statusJogadorWidht * FONT_DRAKE_SIZE, statusJogadorHeight * FONT_DRAKE_SIZE);
        relatorioJogo.setBounds(790, 30, relatorioJogoWidht * FONT_DRAKE_SIZE, relatorioJogoHeight * FONT_DRAKE_SIZE);
        miniMapa.setBounds(790, 337, miniMapaWidht * FONT_DRAKE_SIZE, miniMapaHeight * FONT_DRAKE_SIZE);


    }
}
