
/**
 * @author Barış Pamuksuz ID: 22202238
 * CS102-01
 * Lab05- Paint with recursive laser fill
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.event.MouseInputListener;

public class PaintFrame extends JFrame {
    
    Color startMouseColor; 
    int startX;
    int startY;
    BufferedImage image;
    Graphics g;
    boolean[][] isVisited = new boolean[Controller.width][Controller.height];
    
    public PaintFrame(int width, int height, BufferedImage im){
        for(int i = 0; i < Controller.width; i++){
            for(int k = 0; k < Controller.height; k++){
                isVisited[i][k] = false;
            }
        }
        image = im;
        g = image.getGraphics();
        setSize(width, height);
        setLocation(150, 0);
        addMouseListener(new PenListener());
        addMouseMotionListener(new PenListener());
        addMouseListener(new laserListener());
        addMouseMotionListener(new laserListener());
        setVisible(true);
    }

    public void paint(Graphics g) {
        setBackground(Color.WHITE);
        g.setColor(Controller.currentPenColor);
        g.drawImage(image, 0, 0, this);
        
    }
    class laserListener implements MouseInputListener {
        public void mouseDragged(MouseEvent e) {
            if(Controller.isLaser){
                Controller.PAINT.laserGen(e.getX(), e.getY(), startMouseColor, Controller.currentPenColor);
            }
        }
        public void mouseClicked(MouseEvent e) {
        }
        public void mousePressed(MouseEvent e) {
            if(Controller.isLaser){ 
                startMouseColor = new Color(image.getRGB(e.getX(), e.getY()));
                laserGen(e.getX(), e.getY(), startMouseColor, Controller.currentPenColor);
            }
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
        public void mouseMoved(MouseEvent e) {
        }
    }

    class PenListener implements MouseInputListener {

        public void mouseDragged(MouseEvent e) {
            if (Controller.isDraw) {
                draw(e.getX(), e.getY(),Controller.currentPenColor);
            }
        }
        public void mousePressed(MouseEvent e) {
            if(Controller.isDraw){
            startX = e.getX();
            startY = e.getY();
            draw(startX, startY,Controller.currentPenColor);
            }
        }
        public void mouseClicked(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
        public void mouseMoved(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {   
        } 
    }
    private void draw(int x, int y,Color color){
        if(Controller.isDraw){
        g.setColor(color);
        g.fillRect(startX, startY, (Controller.PaintSize)*2+1,(Controller.PaintSize)*2+1);
        
        startX = x;
        startY = y;
        repaint();
        }
    }
    public void clear(){     
        int tempW = image.getWidth();
        int tempH = image.getHeight();

        for(int i = 0; i < tempW; i++){
            for(int j = 0; j < tempH; j++){
                image.setRGB(i,j,Color.WHITE.getRGB());
            }
        }
        repaint();
    }
    public void laserGen(int x, int y, Color colorOfStartedPix, Color targetColor) {
        if (x >= 0 && x < image.getWidth() && y >= 0 && y < image.getHeight()) {
            int rgb = image.getRGB(x, y);
            int red = (rgb >> 16) & 0xFF;
            int green = (rgb >> 8) & 0xFF;
            int blue = rgb & 0xFF;
            if ((Math.abs((red-colorOfStartedPix.getRed()))+Math.abs(green-colorOfStartedPix.getGreen())+Math.abs(blue-colorOfStartedPix.getBlue()))/3 < Controller.tolerance 
            && !isVisited[x][y]) {
                isVisited[x][y] = true;
                g.setColor(targetColor);
                g.fillRect(x, y, 1, 1);
                repaint();
                laserGen(x, y - 1, colorOfStartedPix, targetColor);//yukari boya
                laserGen(x, y + 1, colorOfStartedPix, targetColor);//asagi boya
            }
        } 
    }
}
