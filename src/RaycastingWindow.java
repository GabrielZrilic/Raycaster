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
    private boolean keyUp, keyDown, keyRight, keyLeft;
    private Timer timer;

    public RaycastingWindow() {
        Raycasting.numberOfRays = Constants.windowWidth;

        this.setPreferredSize(new Dimension(Constants.windowWidth, Constants.windowHeight));
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
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawRays(g2d);

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    private void drawRays(Graphics2D g2d) {
        Ray[] rays = Raycasting.createUnitVectors();
        RayLines[] lines = new RayLines[Raycasting.numberOfRays];
        lines = Raycasting.dda(rays);

        for (int i = 0, x = Constants.windowWidth; i < Raycasting.numberOfRays; i++, x -= Constants.windowWidth
                / Raycasting.numberOfRays) {
            g2d.setColor(lines[i].color);
            g2d.drawLine(x, (int) (Constants.windowHeight / 2 - lines[i].height / 2), x,
                    (int) (Constants.windowHeight / 2 + lines[i].height / 2));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (keyUp)
            Camera.movement = Movement.FORWARD;
        else if (keyDown)
            Camera.movement = Movement.BACK;
        else
            Camera.movement = Movement.STILL;

        if (keyLeft)
            Camera.rotation = Rotation.LEFT;
        else if (keyRight)
            Camera.rotation = Rotation.RIGHT;
        else
            Camera.rotation = Rotation.STILL;

        Camera.updateRotation();
        Camera.updateLocation();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            keyUp = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            keyDown = true;

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            keyLeft = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            keyRight = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            keyUp = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            keyDown = false;

        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            keyLeft = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            keyRight = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}