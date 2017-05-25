package helperv1b;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.PAGE_START;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class TextAreaWithButton extends JPanel {

    private final JTextArea textArea;
    private final JButton btnClipboard;
    private final JButton openButton;
    private final JButton saveButton;
    private final JButton clearButton;

    public JTextArea getTextArea() {
        return textArea;
    }

    public JButton getBtnClipboard() {
        return btnClipboard;
    }

    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }
    
    public TextAreaWithButton() {
        setOpaque(false);
        setLayout(new GridBagLayout());

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane jsp = new JScrollPane(textArea);
        Dimension d = new Dimension(400, 300);
        jsp.setPreferredSize(d);

        //set textArea border
        Border loweredBevel;
        loweredBevel = BorderFactory.createLoweredBevelBorder();
        textArea.setBorder(loweredBevel);

        //**********************************************************************
        // Clipboard, open, save, and clear buttons
        //**********************************************************************
        ImageIcon clipBoard = new ImageIcon("images/clipBoard.png", "copy to clipboard");
        Dimension tinyDim = new Dimension(32, 32);
        btnClipboard = new JButton(clipBoard);
        btnClipboard.setPreferredSize(tinyDim);
        
        ImageIcon openIcon = new ImageIcon("images/openIcon.png", "open a file");
        openButton = new JButton(openIcon);
        openButton.setPreferredSize(tinyDim);
        
        ImageIcon saveIcon = new ImageIcon("images/saveIcon.png", "save a file");
        saveButton = new JButton(saveIcon);
        saveButton.setPreferredSize(tinyDim);
        
        ImageIcon clearIcon = new ImageIcon("images/clearIcon.png", "clear the field");
        clearButton = new JButton(clearIcon);
        clearButton.setPreferredSize(tinyDim);
        
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnClipboard);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(clearButton);

        //jsp GridBag values
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0,0,0,5);
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        add(jsp, c);

        c = new GridBagConstraints();
        c.anchor = PAGE_START;
        c.gridx = 1;
        c.gridy = 0;
        add(buttonPanel, c);

    }// end constructor
}
