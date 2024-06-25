package Ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Para funcionar, vá para o IntelliJ, clique com o botão direito no arquivo .jar e clique em "add as a library" (ultima opção)
import Entity.Aliado;
import Entity.Inimigo;
import asciiPanel.*;

public class Interface extends JPanel implements KeyListener{

    private Queue<InputEvent> inputQueue;

    /* Declarando as telas do jogo */
    private final TelaDeJogo telaDeJogo;
    private final TelaDeCombate telaDeCombate;
    private final StatusJogador statusJogador;
    private final RelatorioJogo relatorioJogo;
    private final MiniMapa miniMapa;
    private JTextArea combatOptionsText;

    /* Tamanhos dos painéis */

    /* - Tamanho da Tela do Jogo */
    private final int telaDeJogoWidth = 45; // largura do painel ASCII (em caracteres)
    private final int telaDeJogoHeight = 30;

    /* - Tamanho da Tela do Status do Jogador */
    private final int statusJogadorWidth = 80;
    private final int statusJogadorHeight = 12;

    /* - Tamanho da Tela do Relatório do Jogo */
    private final int relatorioJogoWidth = 49;
    private final int relatorioJogoHeight = 30;

    /* - Tamanho da Tela do Mini Mapa */
    private final int miniMapaWidth = 44;
    private final int miniMapaHeight = 32;

    /* Tamanho das fontes */
    private static final int FONT_AEROSMATICA_SIZE = 16;
    private static final int FONT_DRAKE_SIZE = 9;

    public Interface() {
        this.inputQueue = new LinkedList<>();

        this.setFocusable(true); // Para o KeyListener funcionar - Quando o painel é focado, ele pode receber eventos de teclado

        /* Inicializando as telas do jogo e adicionando no painel */

        telaDeJogo = new TelaDeJogo(telaDeJogoWidth, telaDeJogoHeight, AsciiFont.CP437_16x16, FONT_AEROSMATICA_SIZE);
        this.add(telaDeJogo.getTela());

        telaDeCombate = new TelaDeCombate(telaDeJogoWidth, telaDeJogoHeight, AsciiFont.CP437_16x16, FONT_AEROSMATICA_SIZE);
        this.add(telaDeCombate.getTela());

        statusJogador = new StatusJogador(statusJogadorWidth, statusJogadorHeight, AsciiFont.DRAKE_10x10, FONT_DRAKE_SIZE);
        this.add(statusJogador.getTela());

        relatorioJogo = new RelatorioJogo(relatorioJogoWidth, relatorioJogoHeight, AsciiFont.DRAKE_10x10, FONT_DRAKE_SIZE);
        this.add(relatorioJogo.getTela());

        miniMapa = new MiniMapa(miniMapaWidth, miniMapaHeight, AsciiFont.CP437_10x10 , 10);
        this.add(miniMapa.getTela());

        combatOptionsText = new JTextArea();

        super.addKeyListener(this); // Adicionando o KeyListener ao painel - "Registrando" a classe Interface como ouvinte de eventos de teclado
    }

    public MiniMapa getMiniMapa() { return miniMapa; }

    public int getMiniMapaHeight() { return miniMapaHeight; }

    public int getMiniMapaWidth() { return miniMapaWidth; }

    public RelatorioJogo getRelatorioJogo() { return relatorioJogo; }

    public int getRelatorioJogoHeight() { return relatorioJogoHeight; }

    public int getRelatorioJogoWidth() { return relatorioJogoWidth; }

    public StatusJogador getStatusJogador() { return statusJogador; }

    public int getStatusJogadorHeight() { return statusJogadorHeight; }

    public int getStatusJogadorWidth() { return statusJogadorWidth; }

    public TelaDeJogo getTelaDeJogo() { return telaDeJogo; }

    public int getTelaDeJogoHeight() { return telaDeJogoHeight; }

    public int getTelaDeJogoWidth() { return telaDeJogoWidth; }

    public TelaDeCombate getTelaDeCombate() { return telaDeCombate; }

    public InputEvent getNextInput() { return inputQueue.poll(); }

    public void updateCombatOptions(ArrayList<Aliado> aliados, ArrayList<Inimigo> inimigos) {
        StringBuilder sb = new StringBuilder();

        sb.append("Allies:\n");
        for (int i = 0; i < aliados.size(); i++) {
            sb.append((i + 1) + ". " + aliados.get(i).getNome() + "\n");  // Replace getName() with the method to get the name of the ally
        }

        sb.append("Enemies:\n");
        for (int i = 0; i < inimigos.size(); i++) {
            sb.append((i + 1) + ". " + inimigos.get(i).getNome() + "\n");  // Replace getName() with the method to get the name of the enemy
        }

        sb.append("Options:\n");
        sb.append("1. Select Hero\n");
        sb.append("2. Select Enemy\n");
        sb.append("3. Execute Attack\n");
        // ... Add more options ...

        combatOptionsText.setText(sb.toString());
    }

    public void refresh(){
        telaDeJogo.getTela().repaint();
        relatorioJogo.getTela().repaint();
        statusJogador.getTela().repaint();
        miniMapa.getTela().repaint();
    }
    public void refreshCombate(){
        telaDeCombate.getTela().repaint();
        relatorioJogo.getTela().repaint();
        statusJogador.getTela().repaint();
        miniMapa.getTela().repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha fundo preto
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Desenha as bordas das telas
        g.setColor(Color.GRAY);

        // Tela de jogo
        g.fillRect(20, 20, telaDeJogoWidth * FONT_AEROSMATICA_SIZE + 20, telaDeJogoHeight * FONT_AEROSMATICA_SIZE + 20);
        // Status jogador
        g.fillRect(20, 535, statusJogadorWidth * FONT_DRAKE_SIZE + 20, statusJogadorHeight * FONT_DRAKE_SIZE + 20);
        // Relatório jogo
        g.fillRect(780, 20, relatorioJogoWidth * FONT_DRAKE_SIZE + 20, relatorioJogoHeight * FONT_DRAKE_SIZE + 20);
        // Mini mapa
        g.fillRect(780, 325, miniMapaWidth * 10 + 20, miniMapaHeight * 10 + 20);

        telaDeJogo.setBounds(30, 30);
        telaDeCombate.setBounds(30, 30);
        statusJogador.setBounds(30, 545);
        relatorioJogo.setBounds(790, 30);
        miniMapa.setBounds(790, 333);

        telaDeJogo.getTela().repaint();
        telaDeCombate.getTela().repaint();
        relatorioJogo.getTela().repaint();
        statusJogador.getTela().repaint();
        miniMapa.getTela().repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputQueue.add(e);
        System.out.println("Pressed Key: " + e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }

}
