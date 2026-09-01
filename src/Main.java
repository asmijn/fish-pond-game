import javax.swing.*;
public class Main {

    public static void main(String[] args) {

        int boardWidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Fish Pond Game");
        frame.setSize(boardWidth, boardHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);
        Pond pond = new Pond();
        frame.add(pond);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        pond.requestFocusInWindow();
    }
}