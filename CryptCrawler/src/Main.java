import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Interface");
        Interface interfaceJogo = new Interface();
        frame.add(interfaceJogo);
        frame.setSize(1366, 768);

        // frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // frame.setUndecorated(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
