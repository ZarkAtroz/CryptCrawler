package combate;

import java.util.ArrayList;
import java.util.Scanner;

import combate.herois.Heroi;
import combate.inimigos.Inimigo;

public class Combate {

    private ArrayList<Heroi> herois = new ArrayList<>();
    private ArrayList<Inimigo> inimigos = new ArrayList<>();

    private int index_heroi_atual; // Colocar no construtor depois
    private int index_inimigo_atual;

    public Heroi heroi_atual;
    public Inimigo inimigo_atual;

    private boolean turno_heroi;


    public Combate(int i_heroi, int i_inimigo, ArrayList<Heroi> hr, ArrayList<Inimigo> in) {
        herois = hr;
        inimigos = in;

        index_heroi_atual = i_heroi;
        index_inimigo_atual = i_inimigo;

        heroi_atual = herois.get(index_heroi_atual);
        inimigo_atual = inimigos.get(index_inimigo_atual);

        turno_heroi = (heroi_atual.getAgilidade() > inimigo_atual.getAgilidade());
    }

    public void teste() {
        Scanner sc = new Scanner(System.in);
        if (turno_heroi) {
            System.out.println("Turno do Heroi: ");
            System.out.println("Heroi atual: " + heroi_atual.getClass().getSimpleName());
            System.out.println("HP: " + heroi_atual.getHp_atual() +"/"+ heroi_atual.getHp_max());
            System.out.println(heroi_atual.getHbs().get(0));

            int dano = heroi_atual.dano(heroi_atual.getHbs().get(0), 1, 1, inimigo_atual.getAgilidade());
            dano = inimigo_atual.getHp_atual() - dano;
            inimigo_atual.setHp_atual(dano);

            turno_heroi = false;
        } else {
            System.out.println("Turno do Inimigo");
            System.out.println("Inimigo atual: " + inimigo_atual.getClass().getSimpleName());
            System.out.println("HP: " + inimigo_atual.getHp_atual() +"/"+inimigo_atual.getHp_max());
            System.out.println(inimigo_atual.getHbs().get(0));

            int dano = inimigo_atual.dano(inimigo_atual.getHbs().get(0), 1, 1, heroi_atual.getAgilidade());
            dano = heroi_atual.getHp_atual() - dano;
            heroi_atual.setHp_atual(dano);

            turno_heroi = true;
        }
        sc.nextLine();

        
    }
    
}
