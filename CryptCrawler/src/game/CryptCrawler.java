package game;

import Entity.Entidade;
import Entity.Player;
import Ui.Controller.GameEventListener;
import log.Log;
import world.World;
import Ui.Interface;
import Ui.Controller.KeyEventController;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Game.game.CryptCrawler is the main class of the game. It extends JFrame and implements KeyListener and GameEventListener.
 * It handles the game loop, input events, and the game interface.
 */
public class CryptCrawler extends JFrame implements GameEventListener {

    // The running state of the game.
    private boolean rodando;

    // The desired frames per second of the game (Target FPS).
    private int framesPerSecond = 60;

    // The time per loop in nanoseconds, calculated based on Target FPS
    private int timePerLoop = 1000000000 / framesPerSecond;

    // The main game frame.
    private JFrame frame;

    // The game icon.
    private ImageIcon img;

    // The game interface.
    private final Interface interfaceJogo;

    // The input event queue.
    private Queue<InputEvent> inputQueue;

    private int tick;

    /**
     * This method reads a file and stores the entities in an ArrayList.
     * @param arquivo The file to be read.
     */
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

    /**
     * The constructor of the Game.game.CryptCrawler class.
     * It initializes the game interface and the game frame.
     */
    public CryptCrawler(){

        /*
         * by: @icaro
         * Sobre o SaveGame, vou criar uma classe auxiliar "SaveGame".
         * Ela terá dois métodos principais:
         * - saveGame(): Salva o jogo em um arquivo (ObjectOutputStream)
         * - loadGame(): Carrega o jogo do arquivo (ObjectInputStream)
         *
         * Terá uma outra classe auxiliar chamada "GameState". Ela terá todos os atributos que serão salvos.
         * Eles serão atribuídos ao GameState inGame, e quando o jogo for salvo, o SaveGame vai salvar SOMENTE o GameState.
         * Todos os atributos do jogo deverão ser atualizados no GameState. Os atributos do jogo funcionarão com base nas informações do GameState.
         * Ou seja, para atualizar um atributo do jogo, seria preciso somente modificar ele no GameState, portanto, só precisar salvar ele no arquivo.
         * Quando usar o loadGame(), o SaveGame vai carregar o objeto GameState para a classe principal game.CryptCrawler, com todas as informações, que serão
         * distribuídas nos respectivos espaços.
         */
        //if(lerSave == true){
        // importar do save
        // passar pra classes
        //} else {
        // ler direto do .txt
        //}

        // lerArquivo("blocos.txt");
        this.rodando = true;
        Log.logInfo("Game Started");

        this.frame = new JFrame("Crypt Crawler");
        this.img = new ImageIcon("Icon\\icon.jpg");
        this.frame.setIconImage(img.getImage());

        this.interfaceJogo = new Interface();
        this.frame.add(interfaceJogo);

        this.frame.setSize(1280, 720);
        //super.addKeyListener(this);

        this.tick = 0;

    }

    /**
     * The main game loop.
     * It handles the frame rate, input events, game updates, and rendering.
     */
    public void run(){

        // Colocar a lógica do frame rate
        // 1. Verificar input
        // 2. Atualizar matriz, valores, dados
        // 3. Renderizar na tela

        // An instance of the World class is created.
        // The third parameter is the scale of the minimap, which determines the size of the minimap relative to the game world.
        World dungeonMap = new World(450, 300, 2);

        // An instance of the Player class is created, that represents the player on the map.
        Player playerOnMap = new Player(null, 22, 15, dungeonMap);

        // The player is set on the map.
        dungeonMap.setPlayerOnMap(playerOnMap);

        // An instance of the KeyEventController class is created, that controls the game's keyboard events.
        KeyEventController keyEventController = new KeyEventController(this, playerOnMap);

        // The start time of the game loop, used to calculate the necessary sleep time
        // to maintain the desired frame rate.
        long startTime;

        // The end time of the game loop, used together with startTime to calculate the necessary sleep time
        // to maintain the desired frame rate.
        long endTime;

        // The necessary sleep time to maintain the desired frame rate. Calculated as the difference between
        // the time per loop (based on the desired frame rate) and the time the game loop actually took.
        long sleepTime;

        while (true){
            this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLayout(null);

            // The start time of the game loop, used to calculate the necessary sleep time
            // to maintain the desired frame rate.
            startTime = System.nanoTime();

            // Tela cheia
            // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // frame.setUndecorated(true);

            if(!this.getRodando())
                System.exit(0);

            // update(); -> atualizar matriz, relatório, vida, etc...
            // render(); -> imprimir todas as telas, mesmo que não tenha mudado nada (aplicar tudo nesse tópico no render())

            // Print the minimap and the game world.
            //interfaceJogo.getTelaDeJogo().printMundo(dungeonMap.getTiles(), playerOnMap); subtituido por lookAt, onde vai centralizar a camera no persongem
            interfaceJogo.getTelaDeJogo().lookAt(dungeonMap.getTiles(), playerOnMap);

            // Aqui printa o MiniMapa
            interfaceJogo.getMiniMapaInterface().printMiniMap(dungeonMap.getMiniMap(), playerOnMap);

            // Teste do display
            // Parece que os erros nos caracteres ocorrem quando está ocorrendo algum tipo de input
            // Ao fazer um teste com uma nova informacao a cada input computado, ocorrera de alguns caracteres sumirem, sendo o mais provavel com caracter repetido

            // Outra maneira de contornar a situacao é criar um sistema de tickRate baseado no frameRate
            // Neste exemplo a cada 120 frames ocorrera uma verificao, no exemplo, se o player estiver na posicao (1,1) ira atualizar a informacao no relatorio
            // E pela bateria de testes nao aconteceu nos paines, porem ainda aconteceu no painel da tela de jogo, minha sugestao é evitar usar o chao / vazio como um espaco em branco
            if(tick % (framesPerSecond * 2) == 0){
                tick = 0;
                if(playerOnMap.getX() == 1 && playerOnMap.getY() == 1){
                    interfaceJogo.getRelatorioJogo().atualizarInformacao("> Jogador na posicao (1,1)", 0, 0);
                }
                if(playerOnMap.getX() == 10 && playerOnMap.getY() == 10){
                    interfaceJogo.getRelatorioJogo().atualizarInformacao("> Jogador na posicao (10,10)", 0, 0);
                }
                if(playerOnMap.getX() == 20 && playerOnMap.getY() == 20){
                    interfaceJogo.getRelatorioJogo().atualizarInformacao("> Jogador na posicao (20,20)", 0, 0);
                }

                interfaceJogo.getStatusJogador().printTela("VIDA: 07/15", 1, 1);
                interfaceJogo.getStatusJogador().printTela("MANA: 19/30", 1, 2);
            }

            // Executes the next key event in the queue. This method is responsible for processing
            // the user's keyboard input and performing the corresponding actions in the game.
            // It takes the game interface as a parameter, which is used to retrieve the next input event.
            keyEventController.executeKeyEvent(this.interfaceJogo);

            // The end time of the game loop is recorded in nanoseconds.
            endTime = System.nanoTime();

            // The sleep time is calculated as the difference between the desired time per loop (based on the desired frame rate)
            // and the actual time the game loop took. This is done to maintain a consistent frame rate across different hardware.
            sleepTime = timePerLoop - (endTime-startTime);

            tick++;

            // If the calculated sleep time is greater than zero (meaning the game loop finished faster than the desired time per loop),
            // the thread is put to sleep for the calculated sleep time. This is done in milliseconds, hence the division by 1000000.
            // This effectively limits the frame rate to the desired frames per second.
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * This method is called when the game is exited.
     * It sets the running flag to false.
     */
    @Override
    public void onGameExit() {
        Log.logInfo("Game Closed");
        this.rodando = false;
    }

    /**
     * This method returns the running state of the game.
     * @return The running state of the game.
     */
    public boolean getRodando() { return this.rodando; }

}
