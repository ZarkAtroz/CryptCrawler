package Ui.Cor;

import java.awt.*;

public class CoresInfo {

    private Color cor;
    private boolean printBlackChar;

    public CoresInfo(Color cor, boolean printBlackChar){
        this.cor = cor;
        this.printBlackChar = printBlackChar;
    }

    public Color getCor() {
        return cor;
    }

    public boolean getPrintBlackChar() {
        return printBlackChar;
    }

    @Override
    public String toString() {
        return "Info Cor: " +
                "Cor: " + getCor() +
                "Print In Black: " + getPrintBlackChar() + "\n";
    }
}
