package EmailHelper;

import javax.swing.JButton;
import javax.swing.JTextArea;

public class CenterPanel extends TextAreaWithButton {

    public CenterPanel getCenterPanel() {
        return this;
    }

    @Override
    public JTextArea getTextArea() {
        return super.getTextArea();
    }

    @Override
    public JButton getBtnClipboard() {
        return super.getBtnClipboard();
    }

    @Override
    public ReplacePanel getReplacePanel() {
        return super.getReplacePanel();
    }
    
    public JButton getReplaceButton() {
        return super.getReplacePanel().getBtnReplace();
    }
    
    public String getText() {
        return getTextArea().getText();
    }

    public void displayText(String str) {
        getTextArea().setText(str);
    }
}
