/**
 * @author Barış Pamuksuz ID: 22202238
 * CS102-01
 * Lab05- Paint with recursive laser fill
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SettingsFrame extends JFrame{
    
    public SettingsFrame(Controller controller){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        setSize(new Dimension(250,250));
        panel1.add(controller.settingsWidth);
        panel1.add(controller.settingsWidthField);
        panel2.add(controller.settingsHeight);
        panel2.add(controller.settingsHeightField);
        add(panel1,BorderLayout.NORTH);
        add(panel2,BorderLayout.CENTER);
        add(controller.settingsButton,BorderLayout.SOUTH);
        
        setVisible(true);
    } 
}