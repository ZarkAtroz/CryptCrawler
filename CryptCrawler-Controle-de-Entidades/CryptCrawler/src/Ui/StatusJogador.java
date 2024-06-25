package Ui;

import Entity.Aliado;
import Entity.Inimigo;
import Entity.Player;
import asciiPanel.AsciiFont;
import combate.Combate;
import combate.inimigos.Vilao;

import java.util.ArrayList;

public class StatusJogador extends Tela{

    public StatusJogador(int width, int height, AsciiFont font, int FONT_SIZE) {
        super(width, height, font, FONT_SIZE);
    }

    public void printTela(String texto, int x, int y){
        getTela().write(texto, x, y);
        getTela().repaint();
        // getTela().write(getWidth() + " x " + getHeight(), 1, 2);
    }
    public void printOptCombate(){
        getTela().write("Atacar", 3, 1);
        getTela().write("Usar Magia", 3, 2);
        getTela().write("Usar Item", 3, 3);
        getTela().write("Fugir", 3, 4);
    }
    public void printInimigos(ArrayList<Inimigo> inimigos){
        int y=1;
        for(Inimigo inimigo : inimigos){
            getTela().write((y-1) + " " + inimigo.getNome(), 3, y++);
        }
    }
    public void printAliados(ArrayList<Aliado> aliados, Player player){
        int y=1;
        getTela().write("0 " + player.getNome(), 3, y++);
        for(Aliado aliado: aliados){
            getTela().write(y-1 + " " + aliado.getNome(), 3, y++);
        }
    }
}
