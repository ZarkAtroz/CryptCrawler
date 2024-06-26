package game;

import Entity.Enemy;
import Entity.Entidade;
import Entity.Player;
import Ui.Controller.GameEventListener;
import log.Log;
import world.World;
import Ui.Interface;
import Ui.Controller.KeyEventController;
import combate.Combate;
import combate.inimigos.Inimigo;

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
 * Game.CryptCrawler is the main class of the game. It extends JFrame and implements KeyListener and GameEventListener.
 * It handles the game loop, input events, and the game interface.
 */
public class CryptCrawler extends JFrame implements KeyListener, GameEventListener {

    // The running state of the game.
    private boolean rodando;

    // The desired frames per second of the game (Target FPS).
    private int framesPerSecond = 30;

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

    private boolean in_combat = false;

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
     * The constructor of the Game.CryptCrawler class.
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
        * Quando usar o loadGame(), o SaveGame vai carregar o objeto GameState para a classe principal CryptCrawler, com todas as informações, que serão
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
        super.addKeyListener(this);

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
        World dungeonMap = new World(450, 300);


        // An instance of the Player class is created, that represents the player on the map.
        Player playerOnMap = new Player(null, 22, 15, dungeonMap);


        Enemy enemyOnMap = new Enemy(dungeonMap, 36, 26, null);

        enemyOnMap.createParty();

        Combate c = new Combate(0, 0, playerOnMap.getParty(), enemyOnMap.getInimigos());

        // The player is set on the map.
        dungeonMap.setPlayerOnMap(playerOnMap);

        dungeonMap.setEnemyOnMap(enemyOnMap);

        // An instance of the KeyEventController class is created, that controls the game's keyboard events.
        KeyEventController keyEventController = new KeyEventController(this, playerOnMap, c);

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
            c.statusHerois(interfaceJogo);
            interfaceJogo.getMiniMapa().printMatriz();
            if (in_combat) {

                if (!c.isTurno_heroi()) {
                    c.atacar(0, 0, interfaceJogo);
                }

            } else {
                interfaceJogo.getTelaDeJogo().printMundo(dungeonMap.getTiles(), playerOnMap, enemyOnMap);     

                // Checa colisao de inimigo
                colisaoPlayerEnemy(dungeonMap);
                
            }
            /*interfaceJogo.getRelatorioJogo().textoUnico("RELATORIO JOGO", 0, 0);
            interfaceJogo.getRelatorioJogo().atualizarInformacao("OWNDOA", 0, 0);*/


            // Executes the next key event in the queue. This method is responsible for processing
            // the user's keyboard input and performing the corresponding actions in the game.
            // It takes the game interface as a parameter, which is used to retrieve the next input event.
            keyEventController.executeKeyEvent(this.interfaceJogo, in_combat);

            // The end time of the game loop is recorded in nanoseconds.
            endTime = System.nanoTime();

            interfaceJogo.getTelaDeJogo().getTela().repaint();
            // The sleep time is calculated as the difference between the desired time per loop (based on the desired frame rate)
            // and the actual time the game loop took. This is done to maintain a consistent frame rate across different hardware.
            sleepTime = timePerLoop - (endTime-startTime);

            // If the calculated sleep time is greater than zero (meaning the game loop finished faster than the desired time per loop),
            // the thread is put to sleep for the calculated sleep time. This is done in milliseconds, hence the division by 1000000.
            // This effectively limits the frame rate to the desired frames per second.
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime/1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void colisaoPlayerEnemy(World world) {
        if (world.getEnemyOnMap().getX() == world.getPlayerOnMap().getX() 
            && world.getEnemyOnMap().getY() == world.getPlayerOnMap().getY()) {

            in_combat = true;
            interfaceJogo.getRelatorioJogo().textoUnico("Herois entraram em combate!!", 0, 5);
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

    /**
     * This method is called when a key is typed.
     * It is currently empty and can be overridden to handle key typed events.
     * @param e The key event.
     */
    @Override
    public void keyTyped(KeyEvent e) { }

    /**
     * This method is called when a key is pressed.
     * It is currently empty and can be overridden to handle key pressed events.
     * @param e The key event.
     */
    @Override
    public void keyPressed(KeyEvent e) { }

    /**
     * This method is called when a key is released.
     * It is currently empty and can be overridden to handle key released events.
     * @param e The key event.
     */
    @Override
    public void keyReleased(KeyEvent e) { }

}
