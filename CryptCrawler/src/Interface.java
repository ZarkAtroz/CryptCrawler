import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// Para funcionar, vá para o IntelliJ, clique com o botão direito no arquivo .jar e clique em "add as a library" (ultima opção)
import asciiPanel.AsciiPanel;

public class Interface extends JPanel {

    private int x = 200; // posição inicial x do personagem
    private int y = 200; // posição inicial y do personagem
    private int moveSpeed;
    private AsciiPanel terminal;
    private AsciiPanel teste;

    private int asciiWidth = 80; // largura do painel ASCII (em caracteres)
    private int asciiHeight = 24; // altura do painel ASCII (em caracteres)

    private static final int CHAR_WIDTH = 9;
    private static final int CHAR_HEIGHT = 16;

    public Interface() {
        this.setFocusable(true);

        terminal = new AsciiPanel();
        terminal.setSize(asciiWidth * CHAR_WIDTH, asciiHeight * CHAR_HEIGHT);
        terminal.setBounds(30, 30, asciiWidth * CHAR_WIDTH, asciiHeight * CHAR_HEIGHT);
        this.add(terminal);

        teste = new AsciiPanel();
        teste.setSize(3  * CHAR_WIDTH, 3 * CHAR_HEIGHT);
        teste.setBounds(960, 450, 40 * CHAR_WIDTH, 15 * CHAR_HEIGHT);
        this.add(teste);

        updateAsciiPanel();
    }

    private void updateAsciiPanel(){

        terminal.clear();
        teste.clear();

        terminal.write("Hello World!", 0, 0);
        teste.write("Teste", 1, 1);
        //char[][] matriz = {
        //        {'A', 'B', 'C'},
        //        {'D', 'E', 'F'}
        //};

        //for(int i = 0; i < matriz.length; i++){
            //for(int j = 0; j < matriz[i].length; j++){
            //    terminal.write(matriz[i][j]);
            //}
        //}

        //teste.write("Teste", 1, 1);

        teste.repaint();
        terminal.repaint();
    }

    public void setMoveSpeed(int moveSpeed){
        this.moveSpeed = moveSpeed;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha fundo preto
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Área mapa
        float proporcaoMapa = 1.8f;

        int larguraMapa = 900;

        int borda = larguraMapa / 90;
        int margem = borda * 2;

        int alturaMapa = (int) (larguraMapa / proporcaoMapa);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(margem, margem, larguraMapa, borda); // Cima esquerda
        g.fillRect(margem, margem, borda, alturaMapa); // Cima esquerda
        g.fillRect(margem, alturaMapa + margem, larguraMapa, borda); // Baixo esquerda
        g.fillRect(larguraMapa + margem, margem, borda, alturaMapa + borda); // Cima direita

        // Área status personagem
        float proporcaoStatus = 6.4285f;
        int larguraStatus = larguraMapa;
        int alturaStatus = (int) ( larguraStatus / proporcaoStatus);
        g.setColor(Color.PINK);
        g.fillRect(margem, alturaMapa + (2 * margem) + borda, larguraStatus, borda); // Cima esquerda
        g.fillRect(margem, alturaMapa + (2 * margem) + borda, borda, alturaStatus); // Cima esquerda
        g.fillRect(margem, alturaMapa + (2 * margem) + borda + alturaStatus, larguraStatus, borda); // Baixo esquerda
        g.fillRect(larguraStatus + margem, alturaMapa + (2 * margem) + borda, borda, alturaStatus + borda); // Cima direita

        // Área da caixa de texto
        float proporcaoLarguraTexto = 2.25f;
        float proporcaoAlturaTexto = 2.3684f;
        int larguraTexto = (int) (larguraMapa / proporcaoLarguraTexto);
        int alturaTexto = (int) (larguraMapa / proporcaoAlturaTexto);
        g.setColor(Color.RED);
        g.fillRect(larguraMapa + (2 * margem) + borda, margem, alturaTexto, borda); // Cima esquerda
        g.fillRect(larguraMapa + (2 * margem) + borda, margem, borda, larguraTexto); // Cima esquerda
        g.fillRect(larguraMapa + (2 * margem) + borda, margem + alturaTexto + borda, larguraTexto - margem, borda); // Baixo esquerda
        g.fillRect(larguraMapa + (2 * margem) + larguraTexto - margem, margem, borda, alturaTexto + margem);

        // Área mini-mapa
        float proporcaoMini = 3.46153f;
        int larguraMini = larguraTexto - margem;
        int alturaMini = (int) (larguraMapa / proporcaoMini);
        g.setColor(Color.BLUE);
        g.fillRect(larguraMapa + (2 * margem) + borda, alturaTexto + (2 * margem) + (2 * borda), larguraMini, borda);
        g.fillRect(larguraMapa + (2 * margem) + borda, alturaTexto + (2 * margem) + (2 * borda), borda, alturaMini);
        g.fillRect(larguraMapa + (2 * margem) + borda, alturaTexto + (2 * margem) + alturaMini + borda, larguraMini, borda);
        g.fillRect(larguraMapa + (2 * margem) + larguraMini, alturaTexto + (2 * margem) + (2 * borda), borda, alturaMini);

        // Desenha o personagem (vermelho)
        // g.setColor(Color.RED);
        // g.fillRect(x, y, borda, borda); // personagem

        terminal.setBounds(30, 30, asciiWidth * CHAR_WIDTH, asciiHeight * CHAR_HEIGHT);
        teste.setBounds(960, 450, 40 * CHAR_WIDTH, 15 * CHAR_HEIGHT);


    }
}
