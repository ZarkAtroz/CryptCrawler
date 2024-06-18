package Ui.Controller;

import Entity.Player;

import java.awt.event.KeyEvent;

public class PlayerMovementController {

    private final Player player;

    public PlayerMovementController(Player player) {
        this.player = player;
    }

    public int getPlayerX() {
        return player.getX();
    }
    public int getPlayerY() {
        return player.getY();
    }

    public void processKeyEvent(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_W:
                player.moveUp();
                break;
            case KeyEvent.VK_S:
                player.moveDown();
                break;
            case KeyEvent.VK_A:
                player.moveLeft();
                break;
            case KeyEvent.VK_D:
                player.moveRight();
                break;
        }
    }
}