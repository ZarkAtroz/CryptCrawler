import Entity.*;
import Ui.Controller.GameEventListener;
import combate.Combate;
import log.Log;
import world.World;
import Ui.Interface;
import Ui.Controller.KeyEventController;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CryptCrawler extends JFrame implements GameEventListener {

    private boolean rodando;
    private final int framesPerSecond = 60;
    private final int timePerLoop = 1000000000 / framesPerSecond;
    private final Interface interfaceJogo;
    private boolean in_combat = false;

    public CryptCrawler(){
        this.rodando = true;
        Log.logInfo("Game Started");

        JFrame frame;
        frame = new JFrame("Crypt Crawler");
        frame.setIconImage((new ImageIcon("Icon\\icon.jpg")).getImage());

        interfaceJogo = new Interface();
        frame.add(interfaceJogo);
        frame.setSize(1280, 720);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    public void run(){

        World dungeonMap = null;

        String userHome = System.getProperty("user.home");
        String desktopPath = userHome + File.separator + "Desktop";

        Path worldPath = Paths.get(desktopPath + "\\World\\world.save").getParent();

        File newFolder = new File(desktopPath + "\\World");

        if (newFolder.exists()) {
            if (Files.exists(worldPath)) {
                try {
                    ObjectInputStream os = new ObjectInputStream(new FileInputStream(desktopPath + "\\World\\world.save"));
                    dungeonMap = (World) os.readObject();

                    dungeonMap.readTiles();
                    dungeonMap.drawAllCharacters();
                    dungeonMap.createEnemiesList();

                } catch (ClassNotFoundException e){
                    e.printStackTrace();

                } catch (FileNotFoundException e){
                    e.printStackTrace();

                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        if(dungeonMap == null){
            dungeonMap = new World(450, 300);
            dungeonMap.drawAllCharacters();
        }

        ArrayList<Entidade> entidades = new ArrayList<>();

        Player playerOnMap = new Player("Player", 22, 15, dungeonMap, 1, 0);
        entidades.add(playerOnMap);

        Aliado mago = new Aliado("Mago", playerOnMap.getX(), playerOnMap.getY(), 2, playerOnMap);
        Aliado barbaro = new Aliado("Barbaro", playerOnMap.getX(), playerOnMap.getY(), 3, playerOnMap);

        entidades.add(mago);
        entidades.add(barbaro);

        playerOnMap.setMaximoTrilha(2);

        /*
         * FAZER OS INIMIGOS APARECEM NO MAPA!
         * Mudar a classe Inimigo.java para adicionar funcoes da classe Enemy.java, pois a classe Enemy nao aparece na tela de jogo, pelo motivo de nao ser
         * uma entidade em si.
         *
         * TELA PISCANDO AO FINZALIZAR COMBATE!
         * Para entrar no combate, é preciso ficar na mesma posicao X e Y do inimigo, porem ao matar os inimigos (finalizando o combate), a tela fica piscando,
         * pois o inimigo ainda existe na posicao X e Y, mas ele está morto, fazendo com o que o combate inicie e logo em seguida termine. Para sair dessa piscada, precisa mover
         * o personagem para outra posicao.
         *
         * HIPOTESE DE SOLUCAO
         * Ao terminar o combate, remover o inimigo do ArrayList de combate, pegando as informacoes de X e Y (LEMBRAR QUE O PLAYER TAMBEM ESTA NA POSICAO, usar o getClass() na condicao),
         * pode ocasionar em algum tipo de erro, como aconteceu na sala.
         * Colocar um variavel booleana em Inimigo, isAlive como um exemplo, caso a vida do inimigo for menor ou igual a 0, mudar a condicao para false, e na funcao colisaoPLayerEnemy, adicionar
         * na condicao se esta variavel é verdadeira.
         */
        Inimigo vilao = new Inimigo("Vilao", 20, 10, (char)6, playerOnMap);
        entidades.add(vilao);

        Enemy enemyOnMap = new Enemy(dungeonMap, 36, 26, null);
        dungeonMap.addEnemyToList(enemyOnMap);

        enemyOnMap.createParty();

        Combate c = new Combate(0, 0, playerOnMap.getParty(), enemyOnMap.getInimigos());

        dungeonMap.setPlayerOnMap(playerOnMap);
        dungeonMap.setEnemyOnMap(enemyOnMap);

        KeyEventController keyEventController = new KeyEventController(this, playerOnMap, entidades, c);

        long startTime;
        long endTime;
        long sleepTime;

        interfaceJogo.getRelatorioJogo().atualizarInformacao("BEM-VINDO AO CRYPT CRAWLER!");

        while (true){
            startTime = System.nanoTime();

            if(!this.getRodando())
                System.exit(0);

            c.statusHerois(interfaceJogo);
            interfaceJogo.getMiniMapa().printMiniMapa(dungeonMap, playerOnMap);
            if(in_combat){

                if(c.isEmptyEnemyies()){

                    interfaceJogo.setCombate();
                    in_combat = !in_combat;

                    dungeonMap.deleteEnemyAt(playerOnMap.getX(), playerOnMap.getY());

                } else {
                    if (!c.isTurno_heroi()) {
                        c.atacar(0, 0, interfaceJogo);
                    }

                    interfaceJogo.refreshCombate();
                }

            } else {
                interfaceJogo.getTelaDeJogo().printMundo(playerOnMap, entidades, dungeonMap);
                interfaceJogo.getStatusJogador().printCoords("POSICAO X = " + playerOnMap.getX() + " / Y = " + playerOnMap.getY());

                // Checa colisao de inimigo
                if(!dungeonMap.isEnemiesEmpty())
                    colisaoPlayerEnemy(dungeonMap);

                interfaceJogo.refresh();
            }

            interfaceJogo.getRelatorioJogo().imprimirRelatorios();

            keyEventController.executeKeyEvent(this.interfaceJogo, in_combat);

            endTime = System.nanoTime();
            sleepTime = timePerLoop - (endTime-startTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void colisaoPlayerEnemy(World world) {

        int playerX = world.getPlayerOnMap().getX();
        int playerY = world.getPlayerOnMap().getY();

        int enemyX = world.getEnemyOnMap().getX();
        int enemyY = world.getEnemyOnMap().getY();

        if(world.getEnemyAt(playerX, playerY)){
            interfaceJogo.setCombate();
            in_combat = true;
            interfaceJogo.getRelatorioJogo().atualizarInformacao("HEROIS ENTRARAM EM COMBATE!");
        }

    }

    @Override
    public void onGameExit() {
        Log.logInfo("Game Closed");
        this.rodando = false;
    }

    public boolean getRodando() { return this.rodando; }

}
