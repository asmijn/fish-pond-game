import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Pond extends JPanel {


    private Image background;
    private int backgroundY = 0;
    private int scrollSpeed = 1;
    private boolean gameOver = false;

    private Fish fish;
    private ArrayList<Lilypad> lilypads = new ArrayList<>();
    private ArrayList<Worm> worms = new ArrayList<>();

    private Timer scrollTimer;
    private Timer lilyGen;
    private Timer wormGen;

    private int T1 = 4500;
    private int T2 = 4500;

    private JButton restartButton;

    public Pond() {

        background = new ImageIcon(
                getClass().getResource("/water.png")
        ).getImage();

        fish = new Fish(150, 400, 100, 150);

        setFocusable(true);
        setLayout(null);
        restartButton = new JButton("Restart");
        restartButton.setBounds(130, 300, 100, 40);
        restartButton.setVisible(false);
        restartButton.addActionListener(e -> restartGame());
        add(restartButton);

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (gameOver) {
                    return;
                }

                int key = e.getKeyCode();

                if (key == KeyEvent.VK_LEFT) {
                    fish.moveLeft(getWidth());
                } else if (key == KeyEvent.VK_RIGHT) {
                    fish.moveRight(getWidth());
                } else if (key == KeyEvent.VK_UP) {
                    fish.moveUp(getHeight());
                } else if (key == KeyEvent.VK_DOWN) {
                    fish.moveDown(getHeight());
                }

                repaint();
            }
        });


        scrollTimer = new Timer(10, e -> {
            if (gameOver) {
                return;
            }

            backgroundY += scrollSpeed;

            for (Lilypad lp : lilypads) {
                lp.moveDown(scrollSpeed);
            }

            for (Worm wr : worms) {
                wr.moveDown(scrollSpeed);
            }

            touchedPad();
            if (!gameOver) {
                touchedWorm();
            }

            repaint();
        });

        lilyGen = new Timer(T1, e -> {
            if (gameOver) {
                return;
            }

            int maxX = Math.max(1, getWidth() - 100);
            int x = (int) (Math.random() * maxX);
            Lilypad lilypad = new Lilypad(x, -100, 100, 100);

            boolean unique = true;

            for (Lilypad li : lilypads) {
                if (Math.abs(li.getX() - x) < 80 && Math.abs(li.getY() - (-100)) < 100) { // Check whether they are too close.
                    unique = false;
                    break;
                }
            }

            if (unique) {
                lilypads.add(lilypad);
            }

            repaint();
        });

        wormGen = new Timer(T2, e -> {
            if (gameOver) {
                return;
            }

            int maxX = Math.max(1, getWidth() - 100);
            int x = (int) (Math.random() * maxX);

            Worm newWorm = new Worm(x, -100, 100, 100); /
            boolean unique = true;

            for (Worm wr : worms) {
                if (Math.abs(wr.getX() - x) < 80 && Math.abs(wr.getY() - (-100)) < 100) {
                    unique = false;
                    break;
                }
            }

            if (unique) { /
                worms.add(newWorm);
            }

            repaint();
        });

        scrollTimer.start();
        lilyGen.start();
        wormGen.start();
    }

    public void touchedPad() {
        Rectangle fishBounds = fish.getBounds();

        for (Lilypad lily : lilypads) {
            Rectangle lilyBounds = lily.getBounds();
            if (fishBounds.intersects(lilyBounds)) { /
                gameOver = true;
                scrollTimer.stop();
                lilyGen.stop();
                wormGen.stop();

                JOptionPane.showMessageDialog(
                        this,
                        "Game Over!"
                );

                restartButton.setVisible(true);
                requestFocusInWindow();

                break;
            }
        }
    }


    public void touchedWorm() {
        Rectangle fishBounds = fish.getBounds();

        for (int i = 0; i < worms.size(); i++) {
            Worm worm = worms.get(i);
            Rectangle wormBounds = worm.getBounds();

            if (fishBounds.intersects(wormBounds)) {
                fish.changeSpeed(5);
                scrollSpeed++;
                T1 = Math.max(1000, T1 - 500);
                T2 = Math.max(1000, T2 - 500);
                lilyGen.setDelay(T1);
                wormGen.setDelay(T2);
                worms.remove(i); // Remove the worm because the fish ate it.


                break; // Stop checking worms after one is eaten.
            }
        }
    }


    private void restartGame() { // Method that completely restarts the game.


        gameOver = false; // Set the game back to running.


        restartButton.setVisible(false); // Hide the Restart button.


        fish = new Fish(150, 400, 100, 150); // Create a new fish at the starting position.


        lilypads.clear(); // Remove every existing lilypad.


        worms.clear(); // Remove every existing worm.


        backgroundY = 0; // Reset the background position.


        scrollSpeed = 1; // Reset the scrolling speed.


        T1 = 4500; // Reset lilypad spawn time.


        T2 = 4500; // Reset worm spawn time.


        lilyGen.setDelay(T1); // Reset the lilypad timer.


        wormGen.setDelay(T2); // Reset the worm timer.


        scrollTimer.start(); // Restart the game movement.


        lilyGen.start(); // Restart lilypad generation.


        wormGen.start(); // Restart worm generation.


        requestFocusInWindow(); // Give keyboard control back to the game.


        repaint(); // Redraw the game.
    }


    @Override // Tell Java that this method overrides JPanel's paintComponent method.

    protected void paintComponent(Graphics g) { // Method used to draw everything.


        super.paintComponent(g); // Clear the previous frame.


        int height = getHeight(); // Get the height of the game panel.


        if (height <= 0) { // Make sure the panel has a valid height.

            return; // Stop drawing if the height is invalid.
        }


        int y = backgroundY % height; // Calculate the background's current position.


        g.drawImage(
                background,
                0,
                y - height,
                getWidth(),
                height,
                this
        ); // Draw the first copy of the background.


        g.drawImage(
                background,
                0,
                y,
                getWidth(),
                height,
                this
        ); // Draw the second copy of the background.


        fish.draw(g, this); // Draw the fish.


        for (Lilypad lp : lilypads) { // Go through every lilypad.

            lp.draw(g, this); // Draw the current lilypad.
        }


        for (Worm wr : worms) { // Go through every worm.

            wr.draw(g, this); // Draw the current worm.
        }


        restartButton.repaint(); // Make sure the Restart button is drawn correctly.
    }
}