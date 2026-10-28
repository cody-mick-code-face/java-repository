import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Main {

    // Screen size
    static final int Screen_Width = 800;
    static final int Screen_height = 600;

    // Countdown shown at start and after resets
    static int countdown = 3;

    // Game objects
    paddle player1;
    paddle player2;
    ball b;

    // Player input
    static int keyX = -1;
    static int keyY = -1;

    public static Scanner pen = new Scanner(System.in);

    public static void main(String[] args) {
        paddle player1 = new paddle(0, 150, 10, 100);
        paddle player2 = new paddle(Screen_Width - 26, 150, 10, 100);

        JFrame frame = new JFrame();
        frame.setTitle("player1 pionts " + player1.getPionts() + " player2 pionts " + player2.getPionts());

        ball b = new ball();

        JPanel screen = new JPanel() {

            @Override
            protected void paintComponent(Graphics draw) {
                super.paintComponent(draw);

                draw.setColor(Color.BLACK);
                draw.fillRect(0, 0, getWidth(), getHeight());

                player1.drawer(draw);
                player2.drawer(draw);
                b.drawer(draw);

                if (countdown > 0) {
                    draw.setColor(Color.WHITE);
                    draw.setFont(new Font("Arial", Font.BOLD, 100));
                    draw.drawString(
                            String.valueOf(countdown),
                            (Screen_Width / 2) - 30,
                            (Screen_height / 2) + 20);
                }
            }
        };

        frame.add(screen);
        frame.setSize(Screen_Width, Screen_height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    keyX = 1;
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    keyX = 2;
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    keyY = 1;
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    keyY = 2;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_LEFT ||
                        e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    keyX = -1;
                }

                if (e.getKeyCode() == KeyEvent.VK_UP ||
                        e.getKeyCode() == KeyEvent.VK_DOWN) {
                    keyY = -1;
                }
            }
        });

        frame.setVisible(true);

        for (int i = 3; i > 0; i--) {

            countdown = i;
            screen.repaint();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        countdown = 0;

        int key_bot_x;
        int key_bot_y;

        while (true) {

            player1.paddle_mover(keyX, keyY);

            b.ball_mover();

            key_bot_x = -1;

            if (player2.getX() > b.getX()) {
                key_bot_x = 1;
            } else if (player2.getX() < b.getX()) {
                key_bot_x = 2;
            }

            key_bot_y = -1;

            if (player2.getY() > b.getY()) {
                key_bot_y = 1;
            } else if (player2.getY() < b.getY()) {
                key_bot_y = 2;
            }

            player2.paddle_mover(key_bot_x, key_bot_y);

            if (new Rectangle(
                    player1.getX(),
                    player1.getY(),
                    player1.getWidth(),
                    player1.getHeight())
                    .intersects(new Rectangle(
                            b.getX(),
                            b.getY(),
                            b.getWidth(),
                            b.getHeight()))) {

                b.setX(player1.getX() + player1.getWidth());
                b.setDirrection_x("right");
            }

            if (new Rectangle(
                    player2.getX(),
                    player2.getY(),
                    player2.getWidth(),
                    player2.getHeight())
                    .intersects(new Rectangle(
                            b.getX(),
                            b.getY(),
                            b.getWidth(),
                            b.getHeight()))) {

                b.setX(player2.getX() - b.getWidth());
                b.setDirrection_x("left");
            }

            screen.repaint();

            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (b.getReset()) {

                if (b.getX() < 400) {
                    player2.setPionts(player2.getPionts() + 1);
                } else {
                    player1.setPionts(player1.getPionts() + 1);
                }

                frame.setTitle(
                        "player1 points " + player1.getPionts() +
                                " player2 points " + player2.getPionts());

                int decision = JOptionPane.showConfirmDialog(
                        frame,
                        "Do you want to play again?",
                        "Play Again",
                        JOptionPane.YES_NO_OPTION);

                if (decision == JOptionPane.YES_OPTION) {

                    player1.setX(0);
                    player2.setX(Screen_Width - 26);

                    player1.setY(150);
                    player2.setY(150);

                    b.setX(Screen_Width / 2);
                    b.setY(Screen_height / 2);

                    for (int i = 3; i > 0; i--) {

                        countdown = i;
                        screen.repaint();

                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    countdown = 0;

                    b.setReset(false);

                } else {
                    System.exit(0);
                }
            }
        }
    }
}