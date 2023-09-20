import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class RaycastingWindow extends JPanel implements ActionListener {
    private static int cellHeight = Constants.windowHeight/Constants.gridHeight;
    private static int cellWidth = Constants.windowWidth/Constants.gridWidth;

    private Timer timer;
    private Point location;

    public RaycastingWindow() {
        Raycasting.numberOfRays = 8;

        this.setPreferredSize(new Dimension(1000, 1000));
        this.setFocusable(true);
        this.requestFocus();
        this.setBackground(Color.BLACK);

        timer = new Timer(16, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw here
        drawGrid(g2d);
        drawWalls(g2d);

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.DARK_GRAY);
        for(int i = 0; i<1000; i+=cellHeight) {
            for(int j = 0; j<1000; j+=cellWidth) g2d.drawRect(i, j, cellHeight, cellWidth);
        }
    }

    private void drawWalls(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        for(int i = 0; i<Constants.gridHeight; i++) {
            for(int j = 0; j<Constants.gridWidth; j++) {
                if(Constants.map[i][j]) g2d.fillRect(j*cellHeight, i*cellWidth, cellHeight, cellWidth);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        location = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(location, this);
        repaint();
    }
}
