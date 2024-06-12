import Entity.Entidade;
import Ui.Interface;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CryptCrawler extends JFrame {


    public static void lerArquivo(String arquivo){

        String linha;
        ArrayList<Entidade> entidades = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {

            // InputStreamReader
            linha = br.readLine();

            while((linha = br.readLine()) != null) {

            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private final Interface interfaceJogo;

    public CryptCrawler(){

        //if(lerSave == true){
            // importar do save
            // passar pra classes
        //} else {
            // ler direto do .txt
        //}

        // lerArquivo("blocos.txt");

        JFrame frame = new JFrame("Crypt Crawler");
        ImageIcon img = new ImageIcon("Icon\\icon.jpg");
        frame.setIconImage(img.getImage());

        this.interfaceJogo = new Interface();
        frame.add(interfaceJogo);
        frame.setSize(1280, 720);

        // Tela cheia
        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    public void run(){

        // Colocar a lógica do frame rate
        // 1. Verificar input
        // 2. Atualizar matriz, valores, dados
        // 3. Renderizar na tela

        this.interfaceJogo.updateAsciiPanel();

    }

}
