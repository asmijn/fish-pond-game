import javax.swing.*; // Import Swing for ImageIcon and JPanel.

import java.awt.*; // Import Graphics, Image, and Rectangle.


public class Worm { // Create the Worm class.


    private Image background; // Store the worm image.


    private int x; // Store the worm's horizontal position.

    private int y; // Store the worm's vertical position.

    private int width; // Store the worm's drawing width.

    private int height; // Store the worm's drawing height.


    public Worm(int x, int y, int width, int height) { // Constructor for creating a worm.


        background = new ImageIcon(
                getClass().getResource("/worm.png")
        ).getImage(); // Load the worm image.


        this.x = x; // Set the starting x position.

        this.y = y; // Set the starting y position.

        this.width = width; // Set the drawing width.

        this.height = height; // Set the drawing height.
    }


    public int getX() { // Method that returns the x position.

        return x; // Return x.
    }


    public int getY() { // Method that returns the y position.

        return y; // Return y.
    }


    public int getWidth() { // Method that returns the width.

        return width; // Return the actual drawing width.
    }


    public int getHeight() { // Method that returns the height.

        return height; // Return the actual drawing height.
    }


    public Rectangle getBounds() { // Create the worm's collision box.


        int hitboxX = x + 15; // Move the collision box 15 pixels inside horizontally.

        int hitboxY = y + 15; // Move the collision box 15 pixels inside vertically.

        int hitboxWidth = width - 30; // Make the collision box smaller than the image.

        int hitboxHeight = height - 30; // Make the collision box shorter than the image.


        return new Rectangle(
                hitboxX,
                hitboxY,
                hitboxWidth,
                hitboxHeight
        ); // Return the collision rectangle.
    }


    public void moveDown(int amount) { // Move the worm downward.


        y += amount; // Increase the y position.
    }


    public void draw(Graphics g, JPanel panel) { // Draw the worm.


        g.drawImage(
                background,
                x,
                y,
                width,
                height,
                panel
        ); // Draw the worm image.
    }
}