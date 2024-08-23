/**
 * @author Barış Pamuksuz ID: 22202238
 * CS102-2 Lab06
 */


import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public class ImagePanel extends JPanel {
    BufferedImage image;

    public ImagePanel(BufferedImage img) {
        this.image = img;
        this.setSize(image.getWidth(), image.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        double f = Math.min(Application.Hfactor, Application.Wfactor);
        if(f <=1){
            g.drawImage(image, 0, 0,  (int)(f*image.getWidth()),  (int)(f*image.getHeight()),null);
        }else{
            g.drawImage(image, 0, 0,  image.getWidth(),  image.getHeight(),null); 
        }
        for (int d = 1; d <= Application.ListOfdiagonalArrays.get(Application.operationalIndex).size(); d++) {
            List<Integer> diagonal = Application.ListOfdiagonalArrays.get(Application.operationalIndex).get(d-1);

            int startX = Math.max(0, d - image.getHeight());
            int endX = Math.min(image.getWidth()-1, d-1);
            int startY = Math.max(0, image.getHeight()-d);
            int index = 0;
            for (int x = startX; x < endX; x++) {
                int rgb = diagonal.get(index++);
                
                image.setRGB(x,startY,rgb);
                startY++;
            }
        }
    }
}
