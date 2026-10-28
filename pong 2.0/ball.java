import java.awt.*;
import java.util.*;

public class ball {

    // Ball position
    private int x;
    private int y;

    // Scanner (currently unused)
    public Scanner pen = new Scanner(System.in);

    // Ball size
    private int width;
    private int height;

    // Ball movement direction
    private String dirrection_x;
    private String dirrection_y;

    // Used to signal a point has been scored
    private boolean reset;

    public ball() {

        this.reset = false;

        this.x = Main.Screen_Width / 2;
        this.y = Main.Screen_height / 2;

        this.width = 10;
        this.height = 10;

        this.dirrection_x =
                new String[]{"left", "right"}[(int) (Math.random() * 2)];

        this.dirrection_y =
                new String[]{"down", "up"}[(int) (Math.random() * 2)];
    }

    // Getters

    public boolean getReset() {
        return reset;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getDirrection_x() {
        return dirrection_x;
    }

    public String getDirrection_y() {
        return dirrection_y;
    }

    // Setters

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDirrection_x(String dirrection_x) {
        this.dirrection_x = dirrection_x;
    }

    public void setDirrection_y(String dirrection_y) {
        this.dirrection_y = dirrection_y;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    // Ball movement

    public void ball_mover() {

        // Move horizontally
        if (this.dirrection_x.equals("left")) {
            this.x -= 2;
        } else {
            this.x += 2;
        }

        // Move vertically
        if (this.dirrection_y.equals("up")) {
            this.y -= 2;
        } else {
            this.y += 2;
        }

        // Top wall collision
        if (this.y <= 0) {
            this.y = 0;
            this.dirrection_y = "down";
        }

        // Bottom wall collision
        if (this.y >= Main.Screen_height - this.height - 40) {
            this.y = Main.Screen_height - this.height - 40;
            this.dirrection_y = "up";
        }

        // Ball passed left side
        if (this.x < 0) {
            this.reset = true;
        }

        // Ball passed right side
        if (this.x > Main.Screen_Width - this.width) {
            this.reset = true;
        }
    }

    // Draw ball

    public void drawer(Graphics draw) {

        draw.setColor(Color.WHITE);
        draw.fillRect(this.x, this.y, this.width, this.height);
    }
}