package EmailHelper;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ReplacePanel extends JPanel {
    private final JButton btnReplace;

    public ReplacePanel () {
        this.setOpaque(false);
        
        ImageIcon replaceIcon = new ImageIcon("images/text-replace-icon.png", "replace text");
        Dimension tinyDim = new Dimension(32, 32);
        btnReplace = new JButton(replaceIcon);
        btnReplace.setPreferredSize(tinyDim);
        
        this.add(btnReplace);
    }

    public JButton getBtnReplace() {
        return btnReplace;
    }

}


