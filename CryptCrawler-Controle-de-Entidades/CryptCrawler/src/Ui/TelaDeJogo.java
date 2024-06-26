package Ui;

import Entity.Entidade;
import Entity.Player;
import Ui.Cor.CoresInfo;
import asciiPanel.AsciiFont;
import world.Tiles;
import world.World;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelaDeJogo extends Tela {

    private Map<String, CoresInfo> colorsPalete;

    public TelaDeJogo(int width, int height, AsciiFont font, int FONT_SIZE) {

        super(width, height, font, FONT_SIZE);
        colorsPalete = new HashMap<>();
        String[] colorNames = {
                "Vermelho Escuro", "Vermelho Medio", "Vermelho Claro", "Laranja Escuro", "Laranja Medio", "Laranja Queimado", "Amarelo Claro", "Amarelo Medio",
                "Verde EScuro", "Verde Medio", "Verde Claro", "Azul Escuro", "Azul Medio", "Azul Claro",
                "Roxo Escuro", "Roxo Claro", "Rosa Claro", "Rosa Medio", "Marrom Escuro", "Marrom Claro", "Bege Claro", "Bege Medio",
                "Cinza Escuro", "Cinza Claro", "Preto", "Preto Medio", "Branco", "Branco Sujo"
        };

        Color[] colors = {
                // Vermelho
                new Color(133, 0, 0),
                new Color(179, 32, 18),
                new Color(255, 241, 186),
                // Laranja
                new Color(250, 167, 41),
                new Color(253, 193, 62),
                new Color(102, 51, 0),
                // Amarelo
                new Color(254, 255, 78),
                new Color(253, 255, 60),
                // Verde
                new Color(15, 84, 10),
                new Color(73, 173, 58),
                new Color(125, 207, 106),
                // Azul
                new Color(17, 9, 129),
                new Color(33, 17, 158),
                new Color(65, 34, 216),
                // Roxo
                new Color(79, 11, 75),
                new Color(204, 126, 200),
                // Rosa
                new Color(255, 213, 226, 255),
                new Color(182, 105, 131),
                // Marrom
                new Color(33, 18, 16),
                new Color(225, 177, 167),
                // Bege
                new Color(250, 251, 228),
                new Color(246, 246, 202),
                // Cinza
                new Color(86, 86, 86),
                new Color(223, 223, 223),
                // Preto
                new Color(0, 0, 0),
                new Color(14, 14, 14),
                // Branco
                new Color(255, 255, 255),
                new Color(236, 233, 236)
        };

        boolean[] printInBlackChar = {
                // Vermelho
                false, false, true,
                // Laranja
                true, true, false,
                // Amarelo
                true, true,
                // Verde
                false, false, true,
                // Azul
                false, false, false,
                // Roxo
                false, true,
                // Rosa
                true, false,
                // Marrom
                false, true,
                // Bege
                true, true,
                // Cinza
                false, true,
                // Preto
                false, false,
                // Branco
                true, true
        };
        for (int i = 0; i < colorNames.length; i++) {
            colorsPalete.put(colorNames[i], new CoresInfo(colors[i], printInBlackChar[i]));
        }

    }

    public void printTexto(String texto, int x, int y){
        getTela().write(texto, x, y);
        // getTela().write("TELA DE JOGO 45 x 30", x, y);
        // getTela().write("45 x 30", x, y + 1);
    }

    public void printMundo(Player player, List<Entidade> entidades, World world){
        getTela().clear();
        lookAt(player, entidades, world);
    }

    public Point GetCameraOrigin(int xPlayer, int yPlayer, World world) {
        // Calculo para caso, o player esteja nas extremidades do mapa, nao ocorra nenhum erro de OutOfBounds
        int x = Math.max(0, Math.min(xPlayer - this.getWidth() / 2, world.getWidth() - 45));
        int y = Math.max(0, Math.min(yPlayer - this.getHeight() / 2, world.getHeight() - 30));
        return new Point(x, y);
    }

    public void lookAt(Player player, List<Entidade> entidades, World world){

        Tiles tile;
        Point origin;

        // Vai calcular o ponto de origem da camera, baseado na posicao do player
        origin = GetCameraOrigin(player.getX(), player.getY(), world);

        for (int x = 0; x < getWidth(); x++){
            for (int y = 0; y < getHeight(); y++){
                tile = world.getTileAt(origin.x + x,origin.y + y);
                //tile = dungeonTiles[origin.x + x][origin.y + y];

                boolean isEntityPresent = false;

                for (Entidade entidade : entidades) {
                    if (entidade.getX() == (origin.x + x) && entidade.getY() == (origin.y + y)) {

                        Color atualBackground = tile.getBackgroundColor();

                        for(CoresInfo coresInfo : colorsPalete.values()){
                            if(coresInfo.getCor().equals(atualBackground)){
                                if(coresInfo.getPrintBlackChar())
                                    getTela().write((char) entidade.getIcone(), x, y, Color.BLACK, tile.getBackgroundColor());
                                else
                                    getTela().write((char) entidade.getIcone(), x, y, Color.WHITE, tile.getBackgroundColor());
                            }
                        }

                        isEntityPresent = true;
                        break;  // Exit the loop once an entity is found at this position
                    }
                }
                if (!isEntityPresent) {
                    getTela().write(tile.getIcon(), x, y, tile.getForegroundColor(), tile.getBackgroundColor());
                }
            }
        }
    }
}
