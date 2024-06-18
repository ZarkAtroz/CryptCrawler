import Entity.Aliado;
import Entity.Entidade;
import Entity.Player;
import Ui.Controller.GameEventListener;
import log.Log;
import world.World;
import Ui.Interface;
import Ui.Controller.KeyEventController;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Game.CryptCrawler is the main class of the game. It extends JFrame and implements KeyListener and GameEventListener.
 * It handles the game loop, input events, and the game interface.
 */
public class CryptCrawler extends JFrame implements GameEventListener {

    // The running state of the game.
    private boolean rodando;

    // The desired frames per second of the game (Target FPS).
    private final int framesPerSecond = 60;

    // The time per loop in nanoseconds, calculated based on Target FPS
    private final int timePerLoop = 1000000000 / framesPerSecond;

    // The game interface.
    private final Interface interfaceJogo;

    // Tick Rate
    private int tick;

    /**
     * The constructor of the Game.CryptCrawler class.
     * It initializes the game interface and the game frame.
     */
    public CryptCrawler(){

        /*
         * by: @john
         * Eu removi a Main.Java e CryptCrawler.java da pasta game pois estava dando conflito na hora
         * de debugar o codigo, mas deixei salvo os arquivos de save, para o icaro continuar com a logica
         * que esta no comentario embaixo.
         * Tambem removi a interface de KeyListener nesse arquivo, nao estava sendo usado. Os inputs estao sendo
         * lidos da classe Interface.java.
         */

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

        this.rodando = true;
        Log.logInfo("Game Started");

        JFrame frame;

        frame = new JFrame("Crypt Crawler");
        frame.setIconImage((new ImageIcon("Icon\\icon.jpg")).getImage());

        interfaceJogo = new Interface();
        frame.add(interfaceJogo);
        frame.setSize(1280, 720);

        this.tick = 0;

        /*
         * by: @john
         * Esta parte estava no looping do frame rate, tambem estava interferindo no bug do clear(),
         * so precisa desta logica quando se cria o jogo | JFrame.
         */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    /**
     * The main game loop.
     * It handles the frame rate, input events, game updates, and rendering.
     */
    public void run(){

        // An instance of the World class is created.
        World dungeonMap = new World(450, 300);

        // An instance of the Player class is created, that represents the player on the map.
        ArrayList<Entidade> entidades = new ArrayList<>();

        Player playerOnMap = new Player("Player", 22, 15, dungeonMap, 1, 0);
        entidades.add(playerOnMap);

        Aliado mago = new Aliado("Mago", playerOnMap.getX(), playerOnMap.getY(), 2, playerOnMap);
        Aliado barbaro = new Aliado("Barbaro", playerOnMap.getX(), playerOnMap.getY(), 3, playerOnMap);

        entidades.add(mago);
        entidades.add(barbaro);

        playerOnMap.setMaximoTrilha(2);

        // The player is set on the map.
        dungeonMap.setPlayerOnMap(playerOnMap);

        // An instance of the KeyEventController class is created, that controls the game's keyboard events.
        KeyEventController keyEventController = new KeyEventController(this, playerOnMap, entidades);

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
            // The start time of the game loop, used to calculate the necessary sleep time
            // to maintain the desired frame rate.
            startTime = System.nanoTime();

            // Tela cheia
            // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // frame.setUndecorated(true);

            if(!this.getRodando())
                System.exit(0);

            // Printar o mundo seguindo o player
            // Ao iniciar o jogo, va para a direita para ver todos os caracteres da fonte nova!!!
            interfaceJogo.getTelaDeJogo().printMundo(dungeonMap.getTiles(), playerOnMap, entidades, dungeonMap);

            if(tick % (framesPerSecond / 15) == 0){

                /*
                 * by: @john
                 * MUDAR O MINI MAPA!
                 * Eu tinha feito um mini mapa, como tinha falado no grupo. A logica desse mini mapa esta tudo dentro
                 * da funcao que foi chamada, e eu mudei a fonte de DRAKE_10x10 para a CP437_10x10, quando for printar
                 * as paredes, verificar o caracter!
                 */
                interfaceJogo.getMiniMapa().printMiniMapa(dungeonMap, playerOnMap);

                if(playerOnMap.getX() == 1 && playerOnMap.getY() == 1){
                    interfaceJogo.getRelatorioJogo().atualizarInformacao("> Jogador na posicao (01,01)", 0, 0);
                }
                if(playerOnMap.getX() == 10 && playerOnMap.getY() == 10){
                    interfaceJogo.getRelatorioJogo().atualizarInformacao("> Jogador na posicao (10,10)", 0, 0);
                }
                if(playerOnMap.getX() == 20 && playerOnMap.getY() == 20){
                    interfaceJogo.getRelatorioJogo().atualizarInformacao("> Jogador na posicao (20,20)", 0, 0);
                }

                interfaceJogo.getStatusJogador().clear();
                interfaceJogo.getStatusJogador().printTela("VIDA: 07/15", 1, 1);
                interfaceJogo.getStatusJogador().printTela("MANA: 19/30", 1, 2);
                interfaceJogo.getStatusJogador().printTela("POSICAO X = " + playerOnMap.getX() + " / Y = " + playerOnMap.getY(), 1, 3);
            }
            interfaceJogo.refresh();

            // Executes the next key event in the queue. This method is responsible for processing
            // the user's keyboard input and performing the corresponding actions in the game.
            // It takes the game interface as a parameter, which is used to retrieve the next input event.
            keyEventController.executeKeyEvent(this.interfaceJogo);

            // The end time of the game loop is recorded in nanoseconds.
            endTime = System.nanoTime();

            // The sleep time is calculated as the difference between the desired time per loop (based on the desired frame rate)
            // and the actual time the game loop took. This is done to maintain a consistent frame rate across different hardware.
            sleepTime = timePerLoop - (endTime-startTime);

            // A cada frame acrescente +1 no tick, para continuar a logica dos 3 paineis secundarios
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
