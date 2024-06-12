package Ui;

import asciiPanel.AsciiFont;

public class TelaDeJogo extends Tela{

    public TelaDeJogo(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
    }

    public void printTexto(String texto, int x, int y){
        getTela().clear();
        getTela().write("TELA DE JOGO 45 x 30", x, y);
        getTela().write("45 x 30", x, y + 1);
        getTela().repaint();
    }

    public void printMundo(char[][] matriz){
        getTela().clear();
        int x = 0, y = 0;
        for(int j = 0; j < 30; j++, y++){
            x = 0;
            for (int i = 0; i < 45; i++, x++) {
                getTela().write(matriz[i][j], x, y);
            }
        }
        getTela().repaint();
    }

}
