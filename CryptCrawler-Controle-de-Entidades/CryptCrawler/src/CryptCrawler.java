import Entity.Aliado;
import Entity.Entidade;
import Entity.Inimigo;
import Entity.Player;
import Ui.Controller.*;
import combate.Combate;
import combate.herois.Guerreiro;
import combate.herois.MagoElemental;
import combate.herois.Rogue;
import combate.inimigos.Goblin;
import log.Log;
import world.World;
import Ui.Interface;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Game.CryptCrawler is the main class of the game. It extends JFrame and implements KeyListener and GameEventListener.
 * It handles the game loop, input events, and the game interface.
 */
public class CryptCrawler extends JFrame implements GameEventListener {
    private final BlockingQueue<KeyEvent> keyEvents = new LinkedBlockingQueue<>();
    // The running state of the game.
    private boolean rodando;

    // The desired frames per second of the game (Target FPS).
    private final int framesPerSecond = 15;

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
    private enum GameState{
        EXPLORING,
        COMBAT
    }
    private GameState gameState;

    private enum GamePause{
        PAUSED,
        UNPAUSED
    }
    private GamePause gamePause;
    /**
     * The main game loop.
     * It handles the frame rate, input events, game updates, and rendering.
     */
    public void run(){

        // Criacao de instancias
        // Mundo
        World dungeonMap = new World(450, 300);
        // Entidades
        ArrayList<Entidade> entidades = new ArrayList<>();
        // Player
        Player playerOnMap = new Player("Player", 22, 15, dungeonMap, 1, 0, new Rogue(1));
        entidades.add(playerOnMap);

        Aliado mago = new Aliado("Mago", playerOnMap.getX(), playerOnMap.getY(), 2, playerOnMap, new MagoElemental(1));
        Aliado barbaro = new Aliado("Barbaro", playerOnMap.getX(), playerOnMap.getY(), 3, playerOnMap, new Guerreiro(1));

        entidades.add(mago);
        entidades.add(barbaro);

        Inimigo gob = new Inimigo("Gob", 3, 3, 18, playerOnMap, new Goblin(2));
        entidades.add(gob);

        playerOnMap.setMaximoTrilha(2);

        // The player is set on the map.
        dungeonMap.setPlayerOnMap(playerOnMap);

        // An instance of the KeyEventController class is created, that controls the game's keyboard events.
        KeyEventController keyEventController = new KeyEventController(this, playerOnMap, entidades);
        PlayerMovementController playerMovementController = new PlayerMovementController(playerOnMap, entidades);
        // The start time of the game loop, used to calculate the necessary sleep time
        // to maintain the desired frame rate.
        long startTime;
        long endTime;
        long sleepTime;

        ArrayList<Inimigo> inimigos = playerMovementController.getInimigos();
        ArrayList<Aliado> aliados = playerMovementController.getAliados();


        gameState = GameState.EXPLORING;
        gamePause = GamePause.UNPAUSED;
        Scanner scanner = new Scanner(System.in);
        Combate combate = new Combate(playerOnMap);
        CombatController.CombatState combatState = CombatController.CombatState.SELECIONAR_ACAO_COMBATE;
        CombatController controller = new CombatController(combatState, interfaceJogo, combate);
        while (this.rodando) {
            startTime = System.nanoTime();
            interfaceJogo.getMiniMapa().printMiniMapa(dungeonMap, playerOnMap);
            if (gameState == GameState.EXPLORING) {
                // Verifica se há combate
                interfaceJogo.getTelaDeJogo().printMundo(dungeonMap.getTiles(), playerOnMap, entidades, dungeonMap);
                if (tick % (framesPerSecond / 15) == 0) {
                    /*
                     * by: @john
                     * MUDAR O MINI MAPA!
                     * Eu tinha feito um mini mapa, como tinha falado no grupo. A logica desse mini mapa esta tudo dentro
                     * da funcao que foi chamada, e eu mudei a fonte de DRAKE_10x10 para a CP437_10x10, quando for printar
                     * as paredes, verificar o caracter!
                     */

                    interfaceJogo.getStatusJogador().clear();
                    interfaceJogo.getStatusJogador().printTela(String.format("HP %d/%d", playerOnMap.getClasse().getHp_atual(), playerOnMap.getClasse().getHp_max()), 1, 1);
                    interfaceJogo.getStatusJogador().printTela(String.format("Mana %d/%d", playerOnMap.getClasse().getMp_atual(), playerOnMap.getClasse().getMp_max()), 1, 2);
                }
                interfaceJogo.refresh();

                keyEventController.executeKeyEvent(this.interfaceJogo);

                // The end time of the game loop is recorded in nanoseconds.
                interfaceJogo.refresh();
                endTime = System.nanoTime();

                // The sleep time is calculated as the difference between the desired time per loop (based on the desired frame rate)
                // and the actual time the game loop took. This is done to maintain a consistent frame rate across different hardware.
                sleepTime = timePerLoop - (endTime - startTime);

                // A cada frame acrescente +1 no tick, para continuar a logica dos 3 paineis secundarios
                tick++;

                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime / 1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(playerMovementController.verificadorCombate()){
                    interfaceJogo.getTelaDeJogo().clear();
                    interfaceJogo.getStatusJogador().clear();
                    interfaceJogo.getMiniMapa().clear();
                    interfaceJogo.getRelatorioJogo().clear();
                    interfaceJogo.refresh();
                    for(Aliado aliado: aliados){
                        combate.addAliado(aliado);
                    }
                    combate.addInimigo(gob);
                    gameState = GameState.COMBAT;
                }
            }
            else if(gameState == GameState.COMBAT){
                interfaceJogo.getTelaDeCombate().printCombatScreen(aliados, inimigos, playerOnMap);
                interfaceJogo.getTelaDeCombate().getTela().repaint();
                controller.modo_de_combate(controller.getCombatState());
                keyEventController.executeKeyCombatEvent(this.interfaceJogo, controller, aliados, inimigos, combate);
                interfaceJogo.refreshCombate();
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
