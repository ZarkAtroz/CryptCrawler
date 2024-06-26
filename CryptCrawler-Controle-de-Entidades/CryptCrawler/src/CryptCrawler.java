import Entity.*;
import Ui.Controller.GameEventListener;
import combate.Combate;
import log.Log;
import world.World;
import Ui.Interface;
import Ui.Controller.KeyEventController;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
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
    private Combate combate;

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

        ArrayList<Enemy> inimigos = new ArrayList<>();

        Enemy goblin = new Enemy(dungeonMap, 36, 26, "Goblin", (char)21);
        Enemy goblinForte = new Enemy(dungeonMap, 40, 26, "Goblin Forte", (char)22);

        inimigos.add(goblin);
        inimigos.add(goblinForte);

        for(Enemy ens : inimigos){
            entidades.add(new Inimigo(ens.getClassName(), ens.getX(), ens.getY(), ens.getIcon(), playerOnMap));
            dungeonMap.addEnemyToList(ens);
        }

        combate = new Combate(0, 0, playerOnMap.getParty());

        dungeonMap.setPlayerOnMap(playerOnMap);

        KeyEventController keyEventController = new KeyEventController(this, playerOnMap, entidades, combate);

        long startTime;
        long endTime;
        long sleepTime;

        interfaceJogo.getRelatorioJogo().atualizarInformacao("BEM-VINDO AO CRYPT CRAWLER!");

        while (true){
            startTime = System.nanoTime();

            if(!this.getRodando())
                System.exit(0);

            interfaceJogo.getMiniMapa().printMiniMapa(dungeonMap, playerOnMap);
            if(in_combat){

                // switch(keyEventController.getCombateTypeEvent())

                // combate.statusHerois(interfaceJogo);
                combate.updateInterface(interfaceJogo, keyEventController.getCombateTypeEvent());

                if(combate.isEmptyEnemyies()){

                    deleteEnemyEntity(dungeonMap, entidades);

                    interfaceJogo.setCombate();
                    in_combat = !in_combat;

                    dungeonMap.deleteEnemyAt(playerOnMap.getX(), playerOnMap.getY());

                } else {
                    if (!combate.isTurno_heroi()) {
                        combate.atacar(0, 0, interfaceJogo);
                    }

                    interfaceJogo.refreshCombate();
                }

            } else {
                interfaceJogo.getTelaDeJogo().printMundo(playerOnMap, entidades, dungeonMap, dungeonMap.getEnemies());
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

        if(world.isEnemyAt(playerX, playerY)){
            Enemy selectedEnemy = world.getEnemyAt(playerX, playerY);
            selectedEnemy.createParty();
            combate.setInimigos(selectedEnemy.getInimigos());
            interfaceJogo.setCombate();
            in_combat = true;
            interfaceJogo.getRelatorioJogo().atualizarInformacao("HEROIS ENTRARAM EM COMBATE!");
        }

    }

    public void deleteEnemyEntity(World dungeonMap, ArrayList<Entidade> entidades){
        int playerX = dungeonMap.getPlayerOnMap().getX();
        int playerY = dungeonMap.getPlayerOnMap().getY();

        Inimigo selectedEnemy = null;

        for(Entidade ent : entidades){
            if(ent instanceof Inimigo){
                if(playerX == ent.getX() && playerY == ent.getY()){
                    selectedEnemy = (Inimigo) ent;
                }
            }
        }

        if(selectedEnemy != null)
            entidades.remove(selectedEnemy);
    }

    @Override
    public void onGameExit() {
        Log.logInfo("Game Closed");
        this.rodando = false;
    }

    public boolean getRodando() { return this.rodando; }

}
