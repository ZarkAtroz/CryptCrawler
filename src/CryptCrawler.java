import Entity.*;
import Ui.Controller.GameEventListener;
import combate.Combate;
import combate.herois.AliadoClasse;
import combate.herois.Guerreiro;
import combate.herois.Healer;
import combate.herois.MagoElemental;
import log.Log;
import world.World;
import Ui.Interface;
import Ui.Controller.KeyEventController;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

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


        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream("src/world.save"));
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

        if(dungeonMap == null){
            dungeonMap = new World(450, 300);
            dungeonMap.drawAllCharacters();
        }

        ArrayList<Entidade> entidades = new ArrayList<>();

        Player playerOnMap = new Player("Player", 22, 15, dungeonMap, 1, 0);
        playerOnMap.setClasse(new Guerreiro(1));
        entidades.add(playerOnMap);

       Aliado aliado1 = new Aliado("Ronaldo", playerOnMap.getX(), playerOnMap.getY(), 2, playerOnMap);
       aliado1.setClasse(new MagoElemental(1));
       playerOnMap.addParty(aliado1.getClasse());
       entidades.add(aliado1);
       playerOnMap.setMaximoTrilha(1);

        ArrayList<Enemy> inimigos = new ArrayList<>();

        Enemy goblin = new Enemy(dungeonMap, 36, 26, "Goblin", (char)21);
        Enemy goblinForte = new Enemy(dungeonMap, 40, 26, "Goblin Forte", (char)22);
        Enemy goblinDois = new Enemy(dungeonMap, 25, 25, "Goblin", (char)22);

        inimigos.add(goblin);
        inimigos.add(goblinForte);
        inimigos.add(goblinDois);

        for(Enemy ens : inimigos){
            entidades.add(new Inimigo(ens.getClassName(), ens.getX(), ens.getY(), ens.getIcon(), playerOnMap));
            dungeonMap.addEnemyToList(ens);
        }

        combate = new Combate(0, 0, playerOnMap.getParty(), playerOnMap);

        dungeonMap.setPlayerOnMap(playerOnMap);

        KeyEventController keyEventController = new KeyEventController(this, playerOnMap, entidades, combate);

        long startTime;
        long endTime;
        long sleepTime;

        interfaceJogo.getRelatorioJogo().atualizarInformacao("BEM-VINDO AO CRYPT CRAWLER!", Color.WHITE);

        while (true){
            startTime = System.nanoTime();

            if(!this.getRodando())
                System.exit(0);

            interfaceJogo.getMiniMapa().printMiniMapa(dungeonMap, playerOnMap);
            interfaceJogo.getRelatorioJogo().imprimirRelatorios();

            if (in_combat) {
                if (combate.isEmptyEnemyies()) {
                    deleteEnemyEntity(dungeonMap, entidades);
                    deleteAllyEntity(entidades, playerOnMap);
                    interfaceJogo.setCombate(entidades);
                    in_combat = !in_combat;

                    dungeonMap.deleteEnemyAt(playerOnMap.getX(), playerOnMap.getY());

                    interfaceJogo.getRelatorioJogo().atualizarInformacao("BATALHA VENCIDA!", Color.BLUE);

                } else {
                    if (!combate.isTurno_heroi()) {
                        combate.atacar(0, 0, 0, interfaceJogo);
                    }

                    combate.updateInterface(interfaceJogo, keyEventController.getCombateTypeEvent());
                    interfaceJogo.refreshCombate();
                }
            } else {
                interfaceJogo.getTelaDeJogo().printMundo(playerOnMap, entidades, dungeonMap, dungeonMap.getEnemies());
                interfaceJogo.getStatusJogador().printCoords("POSICAO X = " + playerOnMap.getX() + " / Y = " + playerOnMap.getY());

                // Checa colisao de inimigo
                if (!dungeonMap.isEnemiesEmpty()) {
                    colisaoPlayerEnemy(dungeonMap, entidades);
                }

                interfaceJogo.refresh();
            }

            keyEventController.executeKeyEvent(this.interfaceJogo, in_combat, entidades);

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

    public void colisaoPlayerEnemy(World world, ArrayList<Entidade> entidades){

        int playerX = world.getPlayerOnMap().getX();
        int playerY = world.getPlayerOnMap().getY();

        if(world.isEnemyAt(playerX, playerY)){
            Enemy selectedEnemy = world.getEnemyAt(playerX, playerY);
            selectedEnemy.createParty();
            combate.setInimigos(selectedEnemy.getInimigos());
            interfaceJogo.setCombate(entidades);
            in_combat = true;
            interfaceJogo.getRelatorioJogo().atualizarInformacao("HEROIS ENTRARAM EM COMBATE!", Color.WHITE);
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

    public void deleteAllyEntity(ArrayList<Entidade> entidades, Player player){
        Iterator<Entidade> iterator = entidades.iterator();
        while(iterator.hasNext()){
            Entidade entidade = iterator.next();
            if(entidade instanceof Aliado){
                AliadoClasse aliadoClasse = ((Aliado) entidade).getClasse();
                if(!player.getParty().contains(aliadoClasse)){
                    iterator.remove();
                    player.setMaximoTrilha(player.getMaximoTrilha() - 1);
                }
            }
        }
    }

    @Override
    public void onGameExit() {
        Log.logInfo("Game Closed");
        this.rodando = false;
    }

    public boolean getRodando() { return this.rodando; }

}
