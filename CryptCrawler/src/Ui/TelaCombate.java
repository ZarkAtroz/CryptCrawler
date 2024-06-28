package Ui;

import Entity.Aliado;
import Entity.Entidade;
import Entity.Player;
import asciiPanel.AsciiFont;
import combate.herois.AliadoClasse;
import combate.inimigos.InimigoClasse;

import java.util.ArrayList;

public class TelaCombate extends Tela{

    public TelaCombate(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
    }

    public void printPersonagens(ArrayList<AliadoClasse> entidades){
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                getTela().write((char) 255, i, j);
            }
        }

        int x = 1;
        for(AliadoClasse e : entidades){
            String nome_classe = e.getClass().getSimpleName();

            switch (nome_classe){
                case "Healer":
                    drawhealer(x);
                    break;
                case "Guerreiro":
                    drawKnight(x);
                    break;
                case "MagoElemental":
                    drawMagoElemental(x);
                    break;
                case "Rogue":
                    drawRogue(x);
                    break;
            }
            x+=3;
        }
    }

    public void printInimigos(ArrayList<InimigoClasse> inimigos){

        int x = 21;
        for(InimigoClasse ini : inimigos){

            String nomeClasse = ini.getClass().getSimpleName();

            switch(nomeClasse){
                case "Goblin":
                    drawGoblin(x);
                    x -= 2;
                    break;

                case "GoblinForte":
                    drawStrongGoblin(x);
                    x -= 3;
                    break;

                case "Slime":
                    drawSlime(x);
                    x -= 2;
                    break;

                case "Esqueleto":
                    drawSkeleton(x);
                    x -= 2;
                    break;

                case "Fantasma":
                    drawGhost(x);
                    x -= 2;
                    break;
            }

        }

    }

    public void drawGoblin(int x){
        int var = x;
        int y = 2;
        char c = (char)109;
        for(int j = 0; j < 2; j++, y++){
            x = var;
            for (int i = 0; i < 2; i++, x--) {
                getTela().write(c--, x, y);
            }
            c += 18;
        }
    }

    public void drawStrongGoblin(int x){
        int var = x;
        int y = 1;
        char c = (char)114;
        for(int j = 0; j < 3; j++, y++){
            x = var;
            for (int i = 0; i < 3; i++, x--) {
                getTela().write(c--, x, y);
            }
            c += 19;
        }
    }

    public void drawSlime(int x){
        int var = x;
        int y = 2;
        char c = (char)77;
        for(int j = 0; j < 2; j++, y++){
            x = var;
            for (int i = 0; i < 2; i++, x--) {
                getTela().write(c--, x, y);
            }
            c += 18;
        }
    }

    public void drawSkeleton(int x){
        int var = x;
        int y = 2;
        char c = (char)111;
        for(int j = 0; j < 2; j++, y++){
            x = var;
            for (int i = 0; i < 2; i++, x--) {
                getTela().write(c--, x, y);
            }
            c += 18;
        }
    }

    public void drawGhost(int x){
        int var = x;
        int y = 2;
        char c = (char)79;
        for(int j = 0; j < 2; j++, y++){
            x = var;
            for (int i = 0; i < 2; i++, x--) {
                getTela().write(c--, x, y);
            }
            c += 18;
        }
    }

    public void drawhealer(int x){
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

    public void drawMagoElemental(int x){
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

    public void drawRogue(int x){
        int var = x;
        int y = 11;
        char c = (char)73;
        for(int j = 0; j < 3; j++, y++){
            x = var;
            for(int i = 0; i < 3; i++, x++){
                getTela().write(c++, x, y);
            }
            c+= 13;
        }
    }

}
