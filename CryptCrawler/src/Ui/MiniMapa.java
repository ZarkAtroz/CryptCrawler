package Ui;

import asciiPanel.AsciiFont;

public class MiniMapa extends Tela{

    public MiniMapa(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
    }

    public void printMatriz(){
        char[][] matriz = new char[49][35];
        for (int i = 0; i < 49; i++) {
            for (int j = 0; j < 35; j++) {
                matriz[i][j] = '.';
            }
        }

        getTela().clear();
        int x = 0, y = 0;

        for(int j = 0; j < 35; j++, y++){
            x = 0;
            for (int i = 0; i < 49; i++, x++) {
                getTela().write(matriz[i][j], x, y);
            }
        }


        getTela().repaint();
    }

    public void printTela(String texto, int x, int y){
        getTela().clear();
        getTela().write("MINI MAPA", x, y);
        getTela().write("49 x 35", x, y + 1);
        getTela().repaint();
    }
}
