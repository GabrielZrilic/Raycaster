import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JButton[][] buttonGrid;
    private JButton createButton;
    private JPanel gridPanel;

    public MainWindow() {
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttonGrid = new JButton[Constants.gridHeight][Constants.gridWidth];
        gridPanel = new JPanel(new GridLayout(Constants.gridHeight, Constants.gridWidth));
        createButton = new JButton("Create");
        mainPanel = new JPanel(new BorderLayout());

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(createButton, BorderLayout.PAGE_END);

        for(int i = 0; i<Constants.gridHeight; i++) {
            for(int j = 0; j<Constants.gridWidth; j++) {
                buttonGrid[i][j] = new JButton();
                buttonGrid[i][j].addActionListener(this);
                buttonGrid[i][j].setBackground(Color.DARK_GRAY);
                gridPanel.add(buttonGrid[i][j]);
            }
        }

        createButton.addActionListener(this);
        this.add(mainPanel);
    }

    private void startMap() {
        JFrame raycastingFrame = new JFrame("Raycasting frame");
        RaycastingWindow raycastingWindow = new RaycastingWindow();
        
        raycastingFrame.add(raycastingWindow);
        raycastingFrame.pack();
        raycastingFrame.setResizable(false);
        raycastingFrame.setVisible(true);
        raycastingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i<Constants.gridHeight; i++) {
            for(int j = 0; j<Constants.gridWidth; j++) {
                // If statment changed for 3d map
                if((i != 0 || j != 0) && e.getSource() == buttonGrid[i][j]) {
                    Constants.map[i][j] = !Constants.map[i][j];
                    buttonGrid[i][j].setBackground(Constants.map[i][j]?Color.decode("#f08e05"):Color.DARK_GRAY);
                    return;
                }
            }
        }
        if(e.getSource() == createButton)startMap();
    }
}
