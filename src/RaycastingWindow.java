import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class RaycastingWindow extends JPanel implements ActionListener, KeyListener {
    private boolean keyUp,keyDown, keyRight, keyLeft;
    private Timer timer;

    public RaycastingWindow() {
        // Best looking when divisible with 12
        // Can go over 360
        Raycasting.numberOfRays = 8;

        this.setPreferredSize(new Dimension(1000, 1000));
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        this.setBackground(Color.BLACK);

        timer = new Timer(16, this);
        timer.start();

        keyUp = false;
        keyDown = false;
        keyRight = false;
        keyLeft = false;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw here
        drawGrid(g2d);
        drawWalls(g2d);
        drawRaysAndPlayer(g2d);

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.DARK_GRAY);
        for(int i = 0; i<Constants.windowHeight; i+=Constants.cellHeight) {
            for(int j = 0; j<Constants.windowWidth; j+=Constants.cellWidth) g2d.drawRect(i, j, Constants.cellHeight, Constants.cellWidth);
        }
    }

    private void drawWalls(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        for(int i = 0; i<Constants.gridHeight; i++) {
            for(int j = 0; j<Constants.gridWidth; j++) {
                if(Constants.map[i][j]) g2d.fillRect(j*Constants.cellHeight, i*Constants.cellWidth, Constants.cellHeight, Constants.cellWidth);
            }
        }
    }

    private void drawRaysAndPlayer(Graphics2D g2d) {
        double[][] rays = Raycasting.endPointsOfVectors(Player.dirX, Player.dirY, Player.planeX, Player.planeY);
        g2d.setColor(Color.RED);
        g2d.fillOval((int) Player.x, (int) Player.y, 2, 2);

        g2d.setColor(Color.WHITE);
        for(int i = 0; i<rays.length; i++) {
            g2d.drawLine((int) Player.x, (int) Player.y, 
                         (int) (rays[i][0] + Player.x), (int) (rays[i][1] + Player.y));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(keyUp) Player.movement = Movement.FORWARD;
        else if(keyDown) Player.movement = Movement.BACK;
        else Player.movement = Movement.STILL;

        if(keyLeft) Player.rotation = Rotation.ROTATE_LEFT;
        else if(keyRight) Player.rotation = Rotation.ROTATE_RIGHT;
        else Player.rotation = Rotation.STILL;

        Player.updateRotation();
        Player.updateLocation();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) keyUp = true;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) keyDown = true;

        if(e.getKeyCode() == KeyEvent.VK_LEFT) keyLeft = true;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) keyRight = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) keyUp = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) keyDown = false;

        if(e.getKeyCode() == KeyEvent.VK_LEFT) keyLeft = false;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) keyRight = false;
    }

    @Override
    public void keyTyped(KeyEvent e) { }
}
