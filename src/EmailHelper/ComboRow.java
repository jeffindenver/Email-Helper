package EmailHelper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class ComboRow extends JPanel {
    private final JComboBox comboBox;
    private final JButton button;
    // to do: create an implementation of the ComboBoxModel interface so 
    // vectors can be replaced. Or replace vectors with arrays.
    private Vector<String> keyList;

    public ComboRow(String title, Vector keys) {
        setOpaque(false);
        ImageIcon rightArrow = new ImageIcon("images/rightArrow.png", 
                                                        "send to text area");
        Dimension smallDim = new Dimension(24, 24);
        Dimension rowDim = new Dimension(200, 64);
        Dimension comboDim = new Dimension(130, 24);

        TitledPanel aRow = new TitledPanel(title);
        aRow.setPreferredSize(rowDim);
        
        setKeyList(keys);
        
        comboBox = new JComboBox(keyList);
        comboBox.setPreferredSize(comboDim);
        
        button = new JButton(rightArrow);
        button.setOpaque(false);
        button.setPreferredSize(smallDim);
        
        aRow.add(comboBox, BorderLayout.CENTER);
        aRow.add(button, BorderLayout.LINE_END);
        
        add(aRow);
    }
    
    public JComboBox getComboBox() {
        return comboBox;
    }

    public JButton getButton() {
        return button;
    }

    public Vector getKeyList() {
        return keyList;
    }

    private void setKeyList(Vector keys) {
        this.keyList = keys;
    }
}
