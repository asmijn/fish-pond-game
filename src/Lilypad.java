import javax.swing.*; // Import Swing for ImageIcon and JPanel.

import java.awt.*; // Import Graphics, Image, and Rectangle.


public class Lilypad { // Create the Lilypad class.


    private Image background; // Store the lilypad image.


    private int x; // Store the lilypad's horizontal position.

    private int y; // Store the lilypad's vertical position.

    private int width; // Store the lilypad's drawing width.

    private int height; // Store the lilypad's drawing height.


    public Lilypad(int x, int y, int width, int height) { // Constructor for creating a lilypad.


        background = new ImageIcon(
                getClass().getResource("/Lilypad.png")
        ).getImage(); // Load the lilypad image.


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


    public Rectangle getBounds() { // Create the lilypad's collision box.


        int hitboxX = x + 10; // Move the collision box 10 pixels inside horizontally.

        int hitboxY = y + 10; // Move the collision box 10 pixels inside vertically.

        int hitboxWidth = width - 20; // Make the collision box slightly narrower.

        int hitboxHeight = height - 20; // Make the collision box slightly shorter.


        return new Rectangle(
                hitboxX,
                hitboxY,
                hitboxWidth,
                hitboxHeight
        ); // Return the collision rectangle.
    }


    public void moveDown(int amount) { // Move the lilypad downward.


        y += amount; // Increase the y position.
    }


    public void draw(Graphics g, JPanel panel) { // Draw the lilypad.


        g.drawImage(
                background,
                x,
                y,
                width,
                height,
                panel
        ); // Draw the image at its position and size.
    }
}