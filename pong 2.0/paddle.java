import java.awt.*;

public class paddle {

    // Position
    private int x;
    private int y;

    // Size
    private int width;
    private int height;

    // Starting side (used to stop paddles crossing the middle)
    private int start;

    private int pionts;

    public void setPionts(int pionts) {
        this.pionts = pionts;
    }

    public int getPionts() {
        return this.pionts;
    }

    public paddle(int x, int y, int width, int height) {
        this.pionts = 0;

        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        this.start = x;
    }

    // Setters

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Getters

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

    // Movement

    public void paddle_mover(int Pressed, int Pressed2) {

        int middle = Main.Screen_Width / 2;

        // Horizontal movement

        if ((Pressed == 1 && this.x > 2 && this.start < middle)
                || (Pressed == 1 && this.x > middle - 6)) {
            this.x -= 2;
        }

        if ((Pressed == 2 && this.x < Main.Screen_Width - 26 && this.start > middle)
                || (Pressed == 2 && this.x < middle - 36)) {
            this.x += 2;
        }

        // Vertical movement

        if (Pressed2 == 1 && this.y > 0) {
            this.y -= 2;
        }

        if (Pressed2 == 2 && this.y < Main.Screen_height - 140) {
            this.y += 2;
        }
    }

    // Draw paddle

    public void drawer(Graphics draw) {

        draw.setColor(Color.WHITE);
        draw.fillRect(this.x, this.y, this.width, this.height);
    }
}