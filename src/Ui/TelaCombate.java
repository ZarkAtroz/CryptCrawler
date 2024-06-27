package Ui;

import Entity.Aliado;
import Entity.Entidade;
import Entity.Player;
import asciiPanel.AsciiFont;
import combate.herois.AliadoClasse;

import java.util.ArrayList;

public class TelaCombate extends Tela{

    public TelaCombate(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
    }

    public void printPersonagens(ArrayList<Entidade> entidades){
        int x = 1;
        for(Entidade e : entidades){
            if(e instanceof Aliado | e instanceof Player){
                String nome_classe = null;
                if(e instanceof Aliado) {
                    nome_classe = ((Aliado) e).getClasse().getClass().getSimpleName();
                }else {
                    nome_classe = ((Player) e).getClasse().getClass().getSimpleName();
                }
                switch (nome_classe){
                    case "Healer":
                        drawMage(x);
                        break;
                    case "Guerreiro":
                        drawKnight(x);
                        break;
                    case "MagoElemental":
                        drawWizard(x);
                        break;
                    case "Rogue":
                        //drawRogue(x);
                        break;
                }
                x+=3;
            }
        }
    }

    public void drawMage(int x){
        int var = x;
        int y = 11;
        char c = (char)64;
        for(int j = 0; j < 3; j++, y++){
            x = var;
            for (int i = 0; i < 3; i++, x++) {
                getTela().write(c++, x, y);
            }
            c += 13;
        }
    }

    public void drawKnight(int x){
        int var = x;
        int y = 11;
        char c = (char)67;
        for(int j = 0; j < 3; j++, y++){
            x = var;
            for (int i = 0; i < 3; i++, x++) {
                getTela().write(c++, x, y);
            }
            c += 13;
        }
    }

    public void drawWizard(int x){
        int var = x;
        int y = 11;
        char c = (char)70;
        for(int j = 0; j < 3; j++, y++){
            x = var;
            for(int i = 0; i < 3; i++, x++){
                getTela().write(c++, x, y);
            }
            c+= 13;
        }
    }

}
