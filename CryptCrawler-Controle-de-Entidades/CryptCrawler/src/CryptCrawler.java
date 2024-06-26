import Entity.Entidade;
import Ui.Interface;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;

public class CryptCrawler extends JFrame implements KeyListener {

    private boolean rodando;
    private int framesPerSecond = 60;
    private int timePerLoop = 1000000000 / framesPerSecond;

    private JFrame frame;
    private ImageIcon img;

    private Queue<InputEvent> inputQueue;

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

        this.rodando = true;

        this.frame = new JFrame("Crypt Crawler");
        this.img = new ImageIcon("Icon\\icon.jpg");
        this.frame.setIconImage(img.getImage());

        this.interfaceJogo = new Interface();
        this.frame.add(interfaceJogo);
        this.frame.setSize(1280, 720);
        super.addKeyListener(this);

    }

    public void run(){

        // Colocar a lógica do frame rate
        // 1. Verificar input
        // 2. Atualizar matriz, valores, dados
        // 3. Renderizar na tela

        while (true){
            this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLayout(null);

            long startTime = System.nanoTime();

            // Tela cheia
            // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // frame.setUndecorated(true);

            getInput();
            if(!this.getRodando())
                System.exit(0);

            // update(); -> atualizar matriz, relatório, vida, etc...
            // render(); -> imprimir todas as telas, mesmo que não tenha mudado nada

            long endTime = System.nanoTime();

            long sleepTime = timePerLoop - (endTime-startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime/1000000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    public void getInput(){
        InputEvent event = this.interfaceJogo.getNextInput();
        if (event instanceof KeyEvent keypress){
            switch (keypress.getKeyCode()){
                case KeyEvent.VK_PAGE_UP:
                    interfaceJogo.getTelaDeJogo().printTexto("Voce apertou a tecla: PAGE UP" , 1, 1);
                    break;

                case KeyEvent.VK_W:
                    interfaceJogo.getTelaDeJogo().printTexto("Voce apertou a tecla: W", 1, 1);
                    break;

                case KeyEvent.VK_ESCAPE:
                    this.setRodando(false);
                    break;
            }
        } else if (event instanceof MouseEvent) {
            //
        }
    }

    public void setRodando(boolean rodando){ this.rodando = rodando; }

    public boolean getRodando() { return this.rodando; }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

}
