package Ui;

import asciiPanel.AsciiFont;

public class TelaCombate extends Tela{

    public TelaCombate(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
    }

    public void printPersonagens(){
        drawMage();
        drawKnight();
        drawWizard();
    }

    public void drawMage(){
        int x = 1, y = 11;
        char c = (char)64;
        for(int j = 0; j < 3; j++, y++){
            x = 1;
            for (int i = 0; i < 3; i++, x++) {
                getTela().write(c++, x, y);
            }
            c += 13;
        }
    }

    public void drawKnight(){
        int x = 4, y = 11;
        char c = (char)67;
        for(int j = 0; j < 3; j++, y++){
            x = 4;
            for (int i = 0; i < 3; i++, x++) {
                getTela().write(c++, x, y);
            }
            c += 13;
        }
    }

    public void drawWizard(){
        int x = 7, y = 11;
        char c = (char)70;
        for(int j = 0; j < 3; j++, y++){
            x = 7;
            for(int i = 0; i < 3; i++, x++){
                getTela().write(c++, x, y);
            }
            c+= 13;
        }
    }

}
