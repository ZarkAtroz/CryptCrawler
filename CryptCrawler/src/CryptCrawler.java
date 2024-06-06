import Interface.Interface;

import javax.swing.*;

public class CryptCrawler extends JFrame {

    private JFrame frame;
    private Interface interfaceJogo;

    public CryptCrawler(){
        this.frame = new JFrame("CryptCrawler");
        ImageIcon img = new ImageIcon("src\\teste.jpg");
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
