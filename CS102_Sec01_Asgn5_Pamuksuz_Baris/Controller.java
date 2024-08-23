/**
 * @author Barış Pamuksuz ID: 22202238
 * CS102-01
 * Lab05- Paint with recursive laser fill
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Controller {

    static int width;
    static int height;

    JLabel settingsWidth = new JLabel("Width:   ");
    JLabel settingsHeight = new JLabel("Height: ");
    JTextField settingsWidthField = new JTextField(10);
    JTextField settingsHeightField = new JTextField(10);
    JButton settingsButton = new JButton("Continue");

    ActionListener listener = new continueListener();
    ActionListener clearListen = new clearListener();
    ActionListener penSizeListen = new penSizeListener();
    ActionListener penListen = new penListener();
    ActionListener penColorListen = new colorListener();
    ActionListener laserAllowListen = new laserAllowListener();
    ActionListener toleranceListen = new toleranceListener();
    
    SettingsFrame SETTINGS;
    ToolsFrame TOOLS;

    static Color currentMouseColor = Color.BLACK; 
    static BufferedImage image;
    static PaintFrame PAINT;
    static Color currentPenColor = Color.BLACK;
    static boolean isDraw = true;
    static boolean isLaser = false;
    static int PaintSize = 1;
    static int tolerance = 100;
    

    JButton toolsClear = new JButton("Clear");
    JButton toolsPen = new JButton("Pen");
    JButton toolsPenSize = new JButton("Pen Size");
    JButton toolsLaser = new JButton("Laser");
    JButton toolsColor = new JButton("Color");
    JButton toolsTolerance = new JButton("Tolerance");

    class continueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String widthIn = settingsWidthField.getText();
            String heightIn = settingsHeightField.getText();
            try {
                width = Integer.parseInt(widthIn);
                height = Integer.parseInt(heightIn);
                if (width <= 0 || width > 1000 || height <= 0 || height > 1000) {
                    JOptionPane.showMessageDialog(null, "Enter integers between 0-1000");
                } else {
                    SETTINGS.setVisible(false);
                    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    
                    PAINT = new PaintFrame(width, height,image);
                    PAINT.clear();
                   
                    TOOLS = new ToolsFrame(Controller.this);
                    TOOLS.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Enter valid integers");
            }
        }
        
    }
    class clearListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            PAINT.clear();
        }
    }
    class penListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(isDraw){
                isDraw = false;
            }else{
                isDraw = true;
            }
        }
    }
    class penSizeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String input = JOptionPane.showInputDialog("Enter pen size: ","");
            try{
                PaintSize = Integer.parseInt(input);
            }catch(NumberFormatException eks){
                if(input == null){
                    //cancel
                }else{
                JOptionPane.showMessageDialog(null, "Enter valid integers");
                }
            }
        }
    }
    class colorListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            currentPenColor = JColorChooser.showDialog(null, "Pick a Color!", Color.WHITE);
        }
    }
    class laserAllowListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(!isLaser){
                isLaser = true;
            }else{
                isLaser = false;
            }
        }
    }
    class toleranceListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String tol = JOptionPane.showInputDialog("Enter Tolerance Level(0-255): ","");
            try{
                tolerance = Integer.parseInt(tol);
                if(tolerance > 255 || tolerance < 0){
                    tolerance = 0;
                    JOptionPane.showMessageDialog(null, "Enter valid integers");
                }
            }catch(NumberFormatException eks){
                if(tol == null){
                    // pressed cancel
                }else{
                JOptionPane.showMessageDialog(null, "Enter valid integers");
                }
            }
        }
    }
    
    public int setPenSize(int pensize){
        PaintSize = pensize;
        return PaintSize;
    }
    

    public static void main(String[] args) {
        Controller cont = new Controller();
        cont.SETTINGS = new SettingsFrame(cont);
        cont.settingsButton.addActionListener(cont.listener);
        cont.toolsClear.addActionListener(cont.clearListen);
        cont.toolsPenSize.addActionListener(cont.penSizeListen);
        cont.toolsPen.addActionListener(cont.penListen);
        cont.toolsColor.addActionListener(cont.penColorListen);
        cont.toolsLaser.addActionListener(cont.laserAllowListen);
        cont.toolsTolerance.addActionListener(cont.toleranceListen);
    }
}
