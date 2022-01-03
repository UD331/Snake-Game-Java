import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;

public class Snake extends JPanel {

    static final int screenWidth = 900;
    static final int screenHeight = 750;
    static final int unitSize = 25;
    static final int gameUnits = (screenWidth * screenHeight) / (unitSize * unitSize);
    static final int delay = 70;
    final int x[] = new int[gameUnits];
    final int y[] = new int[gameUnits];
    int bodyParts = 6;
    int applesEaten;
    int appleXPosition;
    int appleYPosition;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    JFrame jFrame;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(running) {
                move();
                checkApple();
                checkCollision();
            }
            repaint();
        }
    };

    KeyListener keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    };

    public Snake() {
        timer = new Timer(delay, actionListener);
        applesEaten = 0;
        jFrame = new JFrame("Snake Game");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setFocusable(true);
        this.addKeyListener(keyListener);
        jFrame.add(this);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.pack();
        gameStart();
    }

    public void gameStart() {
        appleCreation();
        running = true;
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStuff(g);
    }

    public void drawStuff(Graphics g) {
        if (running) {
            g.setColor(Color.red);
            g.fillOval(appleXPosition, appleYPosition, unitSize, unitSize);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], unitSize, unitSize);
                } else {
                    g.setColor(new Color(50, 180, 0));
                    g.fillRect(x[i], y[i], unitSize, unitSize);
                }

                g.setColor(Color.red);
                g.setFont( new Font("Ink Free",Font.BOLD, 40));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: " + applesEaten,
                        (screenWidth - metrics.stringWidth("Score: " + applesEaten))
                                / 2, g.getFont().getSize());
            }
        } else {
            gameOver(g);
        }
    }

    public void appleCreation() {
        Random random = new Random();
        appleXPosition = random.nextInt(screenWidth / unitSize) * unitSize;
        appleYPosition = random.nextInt(screenHeight / unitSize) * unitSize;
    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        if (direction == 'L') {
            x[0] = x[0] - unitSize;
        } else if (direction == 'R') {
            x[0] = x[0] + unitSize;
        } else if (direction == 'U') {
            y[0] = y[0] - unitSize;
        } else if (direction == 'D') {
            y[0] = y[0] + unitSize;
        }
    }

    public void checkApple() {
        if((x[0] == appleXPosition) && (y[0] == appleYPosition)) {
            bodyParts++;
            applesEaten++;
            appleCreation();
        }
    }

    public void checkCollision() {
        if (x[0] > screenWidth || y[0] > screenHeight || x[0] < 0 || y[0] < 0) {
            running = false;
        }
        for(int i = bodyParts; i > 0; i--) {
            if((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        if(!running) {
            timer.stop();
        }
    }

    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+applesEaten, (screenWidth - metrics1.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        //Game Over text
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free",Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (screenWidth - metrics2.stringWidth("Game Over")) / 2, screenHeight/2);
    }

}
