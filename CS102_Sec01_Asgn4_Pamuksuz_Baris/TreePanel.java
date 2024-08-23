import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TreePanel extends JPanel implements ActionListener {

    int direction = 1;
    int direction1 = 1;
    int currentSeason = 1;
    int windSpeed = 0;
    Random rand = new Random();
    int[] xCoordinatesTree = {156, 195, 217, 208, 115, 114, 128, 215, 228, 211, 245, 235, 260, 294, 276, 318, 285, 310, 347, 372, 357, 268, 281, 328};
    int[] yCoordinatesTree = { 470, 445, 386, 301, 257, 159, 242, 274, 196, 132, 191, 283, 331, 314, 220, 123, 220, 314, 280, 186, 294, 392, 450, 470};
    int[] xCoordinatesLeaves = {187, 90, 22, 71, 138, 166, 188, 242, 361, 436, 403, 314};
    int[] yCoordinatesLeaves = {245, 284, 214, 114, 94, 123, 57, 47, 77, 162, 247, 273};
    int[] xCoordinatesApples = { 76, 174, 175, 266, 330, 388 };
    int[] yCoordinatesApples = { 189, 217, 165, 142, 211, 141 };
    int[] originalXCoordinatesLeaves;
    int[] originalYCoordinatesLeaves;
    int[] originalXCoordinatesTree;
    int[] originalYCoordinatesTree;
    int numberOfSnowFlakes = 70;
    int[] xCoordinatesSnowFlake = new int[numberOfSnowFlakes];
    int[] yCoordinatesSnowFlake = new int[numberOfSnowFlakes];
    int[] xSpeedSnowFlake = new int[numberOfSnowFlakes];
    int[] ySpeedSnowFlake = new int[numberOfSnowFlakes];
    Color treeColor = new Color(153, 76, 0);
    Color snowColor = new Color(255,255,255);
    Timer windTimer;
    Timer snowTimer;
    

    public TreePanel() {
        windTimer = new Timer(150, this);
        snowTimer = new Timer(150, this);
        windTimer.start();
        originalXCoordinatesLeaves = xCoordinatesLeaves.clone();
        originalYCoordinatesLeaves = yCoordinatesLeaves.clone();
        originalXCoordinatesTree = xCoordinatesTree.clone();
        originalYCoordinatesTree = yCoordinatesTree.clone();
        setBackground(new Color(102,255,255));

        

        JPanel buttonPanel = new JPanel();
        

        JButton springButton = new JButton("Spring");
        JButton summerButton = new JButton("Summer");
        JButton fallButton = new JButton("Fall");
        JButton winterButton = new JButton("Winter");

        buttonPanel.add(springButton);
        buttonPanel.add(summerButton);
        buttonPanel.add(fallButton);
        buttonPanel.add(winterButton);
        
        
        

        springButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentSeason = 1;
                setBackground(new Color(102,255,255));
                resetTreePositions();
                windTimer.start();
                
                repaint();
            }
        });

        summerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentSeason = 2;
                setBackground(new Color(153,204,255));
                resetTreePositions();
                
                repaint();
            }
        });

        fallButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentSeason = 3;
                setBackground(new Color(0,102,102));
                resetTreePositions();
                windTimer.start();
                
                repaint();
            }
        });

        winterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentSeason = 4;
                setBackground(new Color(96,96,96));
                windTimer.start();
                snowTimer.start();
                initializeSnowFlakes();
                repaint();
            }
        });

        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    private void initializeSnowFlakes() {
        for (int i = 0; i < numberOfSnowFlakes; i++) {
            xCoordinatesSnowFlake[i] = rand.nextInt(getWidth());
            yCoordinatesSnowFlake[i] = rand.nextInt(getHeight());
            xSpeedSnowFlake[i] = rand.nextInt(3) - 2;
            ySpeedSnowFlake[i] = rand.nextInt(2) + 1;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == windTimer) {
            windEffect();
            repaint();
        } else if (e.getSource() == snowTimer) {
            if (currentSeason == 4) {
                snowEffect();
            }
            repaint();
        }
    }

    public void resetTreePositions() {
        
        xCoordinatesLeaves = originalXCoordinatesLeaves.clone();
        yCoordinatesLeaves = originalYCoordinatesLeaves.clone();
        xCoordinatesTree = originalXCoordinatesTree.clone();
        yCoordinatesTree = originalYCoordinatesTree.clone();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (currentSeason == 1) {
            g2.setColor(new Color(153, 153, 0));
            g2.fillRect(0, 470, 500, 50);
            g2.setColor(new Color(0, 204, 102));
            g2.fillPolygon(xCoordinatesLeaves, yCoordinatesLeaves, 12);
            g2.setColor(treeColor);
            g2.fillPolygon(xCoordinatesTree, yCoordinatesTree, 24);
        } else if (currentSeason == 2) {
            g2.setColor(new Color(0, 204, 0));
            g2.fillRect(0, 470, 500, 50);
            g2.setColor(new Color(0, 255, 0));
            g2.fillPolygon(xCoordinatesLeaves, yCoordinatesLeaves, 12);
            g2.setColor(Color.RED);
            for (int i = 0; i < 5; i++) {
                g2.fillOval(xCoordinatesApples[i], yCoordinatesApples[i], 20, 20);
            }
            g2.setColor(treeColor);
            g2.fillPolygon(xCoordinatesTree, yCoordinatesTree, 24);
        } else if (currentSeason == 3) {
            g2.setColor(new Color(204, 102, 0));
            g2.fillRect(0, 470, 500, 50);
            g2.setColor(new Color(255, 188, 0));
            g2.fillPolygon(xCoordinatesLeaves, yCoordinatesLeaves, 12);
            g2.setColor(treeColor);
            g2.fillPolygon(xCoordinatesTree, yCoordinatesTree, 24);
        } else if (currentSeason == 4) {
            g2.setColor(new Color(192, 192, 192));
            g2.fillRect(0, 470, 500, 50);
            g2.setColor(treeColor);
            g2.fillPolygon(xCoordinatesTree, yCoordinatesTree, 24);

            if (currentSeason == 4) {
                g.setColor(snowColor);
                for (int i = 0; i < numberOfSnowFlakes; i++) {
                    g.fillOval(xCoordinatesSnowFlake[i], yCoordinatesSnowFlake[i], 5, 5);
                }
            }
        }
    }

    public void windEffect(){

        
        if (currentSeason == 1) {
            int windSpeed = 2;
            for (int i = 0; i < 12; i++) {
                xCoordinatesLeaves[i] += direction * windSpeed*3;
            }
            direction = direction * (-1);
        } else if (currentSeason == 3) {
            int windSpeed = 4;
            for (int i = 0; i < 12; i++) {
                xCoordinatesLeaves[i] += direction * windSpeed*3;
            }
            direction = direction * (-1);
        }
        direction = (-1)* direction;
        if(currentSeason == 1){
            
            windSpeed = 2;
            for(int i = 1; i < 23; i++){
                if(yCoordinatesTree[i] < 200){
                    xCoordinatesTree[i] += direction*windSpeed*3;
                    
                }
                else if(yCoordinatesTree[i] < 300){
                    xCoordinatesTree[i] += direction*windSpeed*2;
                    
                }
                else{
                    xCoordinatesTree[i] += direction*windSpeed;
                }
            }
            direction = direction*(-1);
        }
        if(currentSeason == 3){
            
            windSpeed = 4;
            for(int i = 1; i < 23; i++){
                 if(yCoordinatesTree[i] < 200){
                    xCoordinatesTree[i] += direction*windSpeed*3;
                    
                }
                else if(yCoordinatesTree[i] < 300){
                    xCoordinatesTree[i] += direction*windSpeed*2;
                    
                }
                else{
                    xCoordinatesTree[i] += direction*windSpeed;
                }
            
            }
            direction = direction*(-1);
        }
        if(currentSeason == 4){
            
            windSpeed = 7;
            for(int i = 1; i < 23; i++){
                 if(yCoordinatesTree[i] < 200){
                    xCoordinatesTree[i] += direction*windSpeed*3;
                    
                }
                else if(yCoordinatesTree[i] < 300){
                    xCoordinatesTree[i] += direction*windSpeed*2;
                    
                }
                else{
                    xCoordinatesTree[i] += direction*windSpeed;
                }
            
            }
            direction1 = direction1*(-1);
        }
    }

    public void snowEffect() {
        for (int i = 0; i < numberOfSnowFlakes; i++) {
            xCoordinatesSnowFlake[i] += xSpeedSnowFlake[i];
            yCoordinatesSnowFlake[i] += ySpeedSnowFlake[i];

            if (yCoordinatesSnowFlake[i] > getHeight() || xCoordinatesSnowFlake[i] < 0 || xCoordinatesSnowFlake[i] > getWidth()) {
                xCoordinatesSnowFlake[i] = rand.nextInt(getWidth());
                yCoordinatesSnowFlake[i] = 0;
            }
        }
    }
}
