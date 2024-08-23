import javax.swing.JFrame;
import javax.swing.JPanel;

public class TreeFrame extends JFrame{
    
    public TreeFrame(){
        setSize(500,550);
    }

    public static void main(String[] args) {
        
        JFrame frame = new TreeFrame();
        JPanel treePanel = new TreePanel();
        frame.add(treePanel);
        
        
        
        frame.setVisible(true);
    }
}