import javax.swing.*; // Import Swing for ImageIcon and JPanel.

import java.awt.*; // Import Graphics, Image, and Rectangle.


public class Fish { // Create the Fish class.


    private Image background; // Store the fish image.


    private int x; // Store the fish's horizontal position.

    private int y; // Store the fish's vertical position.

    private int width; // Store the fish's drawing width.

    private int height; // Store the fish's drawing height.


    private int speed = 20; // Store how many pixels the fish moves each key press.


    public Fish(int x, int y, int width, int height) { // Constructor for creating the fish.


        background = new ImageIcon(
                getClass().getResource("/Fish.png")
        ).getImage(); // Load the fish image.


        this.x = x; // Set the starting x position.

        this.y = y; // Set the starting y position.

        this.width = width; // Set the drawing width.

        this.height = height; // Set the drawing height.
    }


    public void changeSpeed(int amount) { // Method for increasing the fish's speed.

        speed += amount; // Add the given amount to the current speed.
    }


    public int getX() { // Method that returns the fish's x position.

        return x; // Return the x position.
    }


    public int getY() { // Method that returns the fish's y position.

        return y; // Return the y position.
    }


    public int getWidth() { // Method that returns the fish's drawing width.

        return width; // Return the actual drawing width.
    }


    public int getHeight() { // Method that returns the fish's drawing height.

        return height; // Return the actual drawing height.
    }


    public Rectangle getBounds() { // Create the fish's collision box.


        int hitboxX = x + 15; // Move the collision box 15 pixels inside the image horizontally.

        int hitboxY = y + 15; // Move the collision box 15 pixels inside the image vertically.

        int hitboxWidth = width - 30; // Make the collision box narrower than the image.

        int hitboxHeight = height - 30; // Make the collision box shorter than the image.


        return new Rectangle(
                hitboxX,
                hitboxY,
                hitboxWidth,
                hitboxHeight
        ); // Return the smaller collision rectangle.
    }


    public void draw(Graphics g, JPanel panel) { // Method for drawing the fish.


        g.drawImage(
                background,
                x,
                y,
                width,
                height,
                panel
        ); // Draw the fish image at its position and size.
    }


    public void moveRight(int boardWidth) { // Move the fish right.


        x += speed; // Increase x by the current speed.


        if (x + width > boardWidth) { // Check if the fish reached the right edge.

            x = boardWidth - width; // Keep the fish inside the right edge.
        }
    }


    public void moveLeft(int boardWidth) { // Move the fish left.


        x -= speed; // Decrease x by the current speed.


        if (x < 0) { // Check if the fish went past the left edge.

            x = 0; // Keep the fish inside the left edge.
        }
    }


    public void moveUp(int boardHeight) { // Move the fish upward.


        y -= speed; // Decrease y by the current speed.


        if (y < 0) { // Check if the fish went above the screen.

            y = 0; // Keep the fish inside the top edge.
        }
    }


    public void moveDown(int boardHeight) { // Move the fish downward.


        y += speed; // Increase y by the current speed.


        if (y + height > boardHeight) { // Check if the fish reached the bottom.

            y = boardHeight - height; // Keep the fish inside the bottom edge.
        }
    }
}