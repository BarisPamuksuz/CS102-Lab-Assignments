
/**
 * @author Barış Pamuksuz ID: 22202238
 * CS102-2 Lab06 fantastic pixel sorting xd
 */

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.awt.event.*;

public class Application extends JFrame {
    public static List<List<List<Integer>>> ListOfdiagonalArrays = new ArrayList<>();
    public static int operationalIndex = 0;
    public static double Wfactor;
    public static double Hfactor;
    private BufferedImage[] imgs = new BufferedImage[3];
    private int[] iterationCounts = new int[] { 0, 0, 0 };
    public ImagePanel[] imagePanels = new ImagePanel[3];
    public String[] imagePaths = new String[3];

    public Application(String[] imagePaths) throws IOException {

        this.imagePaths[0] = imagePaths[0];
        this.imagePaths[1] = imagePaths[1];
        this.imagePaths[2] = imagePaths[2];

        for (int i = 0; i < 3; i++) {
            ListOfdiagonalArrays.add(new ArrayList<>());
        }
        for (int i = 2; i >= 0; i--) {
            imgs[i] = ImageIO.read(new File(this.imagePaths[i]));
            fillChains(i);
            imagePanels[i] = new ImagePanel(imgs[i]);

        }
        this.add(imagePanels[operationalIndex]);
        this.setSize(700, 650);
        Wfactor = this.getWidth() / imgs[operationalIndex].getWidth();
        Hfactor = this.getHeight() / imgs[operationalIndex].getHeight();
        this.setVisible(true);

        TimerListener listener = new TimerListener();
        Timer t = new Timer(1, listener);
        t.start();

        this.addKeyListener(new keyListen());
    }

    public void fillChains(int operationalIndex) throws IOException {
        imgs[operationalIndex] = ImageIO.read(new File(imagePaths[operationalIndex]));
        List<List<Integer>> diagonalArray = ListOfdiagonalArrays.get(operationalIndex);

        diagonalArray.clear();
        for (int d = 1; d <= imgs[operationalIndex].getWidth() + imgs[operationalIndex].getHeight() - 1; d++) {
            List<Integer> diagonal = new ArrayList<>();

            int startX = Math.max(0, d - imgs[operationalIndex].getHeight());
            int endX = Math.min(d - 1, imgs[operationalIndex].getWidth() - 1);
            int startY = Math.max(0, imgs[operationalIndex].getHeight() - d);
            for (int x = startX; x <= endX; x++) {
                int rgb = imgs[operationalIndex].getRGB(x, startY);
                diagonal.add(rgb);
                startY++;
            }

            diagonalArray.add(diagonal);
        }
    }

    public double getBrightness(int pixelF) {
        int redF = (pixelF >> 16) & 0xFF;
        int greenF = (pixelF >> 8) & 0xFF;
        int blueF = pixelF & 0xFF;

        return 0.2126 * redF + 0.7152 * greenF + 0.0722 * blueF;
    }

    class TimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < imgs[operationalIndex].getWidth() + imgs[operationalIndex].getHeight() - 1; i++) {
                sortForAChain(ListOfdiagonalArrays.get(operationalIndex).get(i), iterationCounts[operationalIndex]);
            }
            iterationCounts[operationalIndex]++;
            imagePanels[operationalIndex].repaint();
            Wfactor = (double) Application.this.getWidth() / imgs[operationalIndex].getWidth();
            Hfactor = (double) Application.this.getHeight() / imgs[operationalIndex].getHeight();
        }
    }

    class WhitePanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    class keyListen implements KeyListener {

        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_R:
                    try {
                        fillChains(operationalIndex);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    iterationCounts[operationalIndex] = 0;
                    break;
                case KeyEvent.VK_LEFT:
                    Application.this.clearFrame();
                    operationalIndex = (operationalIndex + 2) % 3;
                    Application.this.add(imagePanels[operationalIndex]);
                    break;
                case KeyEvent.VK_RIGHT:
                    Application.this.clearFrame();
                    ;
                    operationalIndex = (operationalIndex + 1) % 3;
                    Application.this.add(imagePanels[operationalIndex]);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    public void clearFrame() {
        WhitePanel whitePanel = new WhitePanel();
        this.getContentPane().removeAll();
        this.add(whitePanel);
        this.repaint();
    }

    public void sortForAChain(List<Integer> aChain, int iterationCount) {
        int maxIteration = aChain.size();
        int targetIndex = iterationCount;
        if (iterationCount < maxIteration) {

            for (int m = iterationCount + 1; m < maxIteration; m++) {
                if (getBrightness(aChain.get(m)) > getBrightness(aChain.get(targetIndex))) {
                    targetIndex = m;
                }
            }
            int temp = aChain.get(iterationCount);
            aChain.set(iterationCount, aChain.get(targetIndex));
            aChain.set(targetIndex, temp);
        }
    }

    public static void main(String[] args) throws IOException {
        String[] imagePaths = new String[] { "airplane.png", "forest.png", "sky.png" };
        Application app = new Application(imagePaths);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
