import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SnakeGame implements Runnable {
    
    private int[] xPositions;
    private int[] yPositions;
    private static final int screenWidth = 600;
    private static final int screenHeight = 500;
    private static int applesEaten;
    private static final int maxSize = 20;
    private int score;
    private int appleXPosition;
    private int appleYPosition;
    boolean started;
    boolean collision;
    
    public SnakeGame() {
        applesEaten = 0;
        score = 0;
        started = false;
        collision = false;
        xPositions = new int[maxSize];
        yPositions = new int[maxSize];
    }
    
    public void snake() {
        
    }
    
    public void apples() {
        
    }
    
    public boolean boundaryCollisions() {
        
    }
    
    public boolean snakeCollisions() {
        
    }
    
    public static void main(String[] args) {
        
    }
    
    public void run() {
        
    }
}
