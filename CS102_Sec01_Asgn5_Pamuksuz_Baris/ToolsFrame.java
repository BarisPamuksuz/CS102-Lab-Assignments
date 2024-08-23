/**
 * @author Barış Pamuksuz ID: 22202238
 * CS102-01
 * Lab05- Paint with recursive laser fill
 */

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class ToolsFrame extends JFrame{
    
    public ToolsFrame(Controller controller){
        
        setLayout(new GridLayout(6,0));
        setSize(new Dimension(30,300));
        add(controller.toolsClear);
        add(controller.toolsPen);
        add(controller.toolsPenSize);
        add(controller.toolsLaser);
        add(controller.toolsColor);
        add(controller.toolsTolerance);

        setVisible(false);
    }
}