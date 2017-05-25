package helperv1b;

import FileOps.FileOps;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HelperController {
    
    private final HelperModel model;
    private final HelperView view;
    
    public HelperController(HelperView theView, HelperModel theModel) {
        this.view = theView;
        this.model = theModel;
        
        view.getOptionsPanel().addAccount(model.getAccounts());
        view.getOptionsPanel().addAccountsToPanel();
        
        view.inventoryBackgroundImages();

        //Although 4 accounts are not required, more than 4 is not supported
        //the model tests the size, if greater than four it is set to four.
        int size = model.getAccounts().size();
        for (int i = 0; i < size; i++) {
            view.addButtonListener(new ButtonListener(), view.getOptionsPanel().getButton(i));
        }

        view.addButtonListener(new ClipboardButtonListener(), view.getCenterPanel().getBtnClipboard());
        view.addButtonListener(new ClipboardButtonListener(), view.getRightPanel().getBtnClipboard());
        
        view.addButtonListener(new OpenButtonListener(), view.getCenterPanel().getOpenButton());
        view.addButtonListener(new OpenButtonListener(), view.getRightPanel().getOpenButton());
        view.addButtonListener(new SaveButtonListener(), view.getCenterPanel().getSaveButton());
        view.addButtonListener(new SaveButtonListener(), view.getRightPanel().getSaveButton());
        view.addButtonListener(new ClearButtonListener(), view.getCenterPanel().getClearButton());
        view.addButtonListener(new ClearButtonListener(), view.getRightPanel().getClearButton());
        
        view.addMouseAdapter(new ChangeBackgroundListener(), view.getLogoPanel().getPicLabel());
        view.addMouseAdapter(new RightClickListener(), view.getCenterPanel().getTextArea());
        view.addMouseAdapter(new RightClickListener(), view.getRightPanel().getTextArea());
       
    }
    
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String stringKey;
            String outText = "";
            int size = model.getAccounts().size();
            int index = 0;
            
            while(index < size) {
                if(ae.getSource() == view.getOptionsPanel().getButton(index)) {
                    stringKey = 
                            (String) view.getOptionsPanel().
                                    getComboBox(index).getSelectedItem();
                    try {
                        outText = model.getAccounts().get(index).readFile(stringKey);
                    } catch(FileNotFoundException fnfe) {
                        view.displayErrorMessage("File not found. "
                                            + fnfe.getMessage());
                    } catch(IOException ioe) {
                        view.displayErrorMessage("There was an issue reading the file. " 
                                            + ioe.getMessage());
                    }
                    break;
                }
                index++;
            }
            view.getCenterPanel().displayText(outText);
        }
    }
    
    private class ClipboardButtonListener implements ActionListener {

        StringSelection selection;
        String str;

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == view.getCenterPanel().getBtnClipboard()) {
                str = view.getCenterPanel().getText();
                selection = new StringSelection(str);
            }
            if (ae.getSource() == view.getRightPanel().getBtnClipboard()) {
                str = view.getRightPanel().getText();
                selection = new StringSelection(str);
            }
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            if (selection != null) {
                clipboard.setContents(selection, null);
            }
        }
    }

    private class RightClickListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (SwingUtilities.isRightMouseButton(e)) {
                if (e.getComponent() == view.getCenterPanel().getTextArea()) {
                    LeftPopupMenuCCP ccpMenu = new LeftPopupMenuCCP();
                    ccpMenu.show(e.getComponent(), e.getX(), e.getY());
                    ccpMenu.setVisible(true);
                }
                if (e.getComponent() == view.getRightPanel().getTextArea()) {
                    RightPopupMenuCCP ccpMenu = new RightPopupMenuCCP();
                    ccpMenu.show(e.getComponent(), e.getX(), e.getY());
                    ccpMenu.setVisible(true);
                }
            }
        }
    }

    private class LeftPopupMenuCCP extends JPopupMenu {

        public LeftPopupMenuCCP() {
            JMenuItem mCopy = new JMenuItem("copy");
            JMenuItem mCut = new JMenuItem("cut");
            JMenuItem mPaste = new JMenuItem("paste");
            
            add(mCopy);
            add(mCut);
            add(mPaste);
            
            mCopy.addActionListener(new LeftMenuListener());
            mCut.addActionListener(new LeftMenuListener());
            mPaste.addActionListener(new LeftMenuListener());
        }

        private class LeftMenuListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent ae) {

                if (ae.getActionCommand().equals("copy")) {
                    view.getCenterPanel().getTextArea().copy();
                }
                if (ae.getActionCommand().equals("cut")) {
                    view.getCenterPanel().getTextArea().cut();
                }
                if (ae.getActionCommand().equals("paste")) {
                    view.getCenterPanel().getTextArea().paste();
                }
            }
        }
    }

    private class RightPopupMenuCCP extends JPopupMenu {

        public RightPopupMenuCCP() {
            JMenuItem mCopy = new JMenuItem("copy");
            JMenuItem mCut = new JMenuItem("cut");
            JMenuItem mPaste = new JMenuItem("paste");
            
            add(mCopy);
            add(mCut);
            add(mPaste);
            
            mCopy.addActionListener(new RightMenuListener());
            mCut.addActionListener(new RightMenuListener());
            mPaste.addActionListener(new RightMenuListener());
        }

        private class RightMenuListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //inclue copy, cut and paste actions
                if (ae.getActionCommand().equals("copy")) {
                    view.getRightPanel().getTextArea().copy();
                }
                if (ae.getActionCommand().equals("cut")) {
                    view.getRightPanel().getTextArea().cut();
                }
                if (ae.getActionCommand().equals("paste")) {
                    view.getRightPanel().getTextArea().paste();
                }
            }
        }
    }

    private class ChangeBackgroundListener extends MouseAdapter {
        
        @Override
        public void mouseClicked(MouseEvent e) {

            if (SwingUtilities.isRightMouseButton(e)) {
                BackgroundPopupMenu backgroundMenu = new BackgroundPopupMenu();
                backgroundMenu.show(e.getComponent(), e.getX(), e.getY());
                backgroundMenu.setVisible(true);
            }
        }
    }

    private class BackgroundPopupMenu extends JPopupMenu {

        public BackgroundPopupMenu() {
            int size = view.getBackgroundImageNames().size();
            
            JMenuItem[] imageMenuItems = new JMenuItem[size];
            
            for (int i = 0; i < size; i++) {
                imageMenuItems[i] = new JMenuItem(view.getBackgroundImageName(i));
                add(imageMenuItems[i]);
                imageMenuItems[i].addActionListener(new RightMenuListener());
            }
        }

        private class RightMenuListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent ae) {
                //change background here
                int size = view.getBackgroundImageNames().size();
                int index = 0;
                String imageName = "default.jpg";
                while(index < size) {
                    imageName = view.getBackgroundImageName(index);
                    if (ae.getActionCommand().equalsIgnoreCase(imageName)) {
                        view.getBg().setBg(imageName);
                        break;
                    }
                    index++;
                }
                view.getBg().repaint();
                saveLastBackground(imageName);
            }

            private void saveLastBackground(String imageName) {
                FileOps fo = new FileOps("lastBackground.txt", false);
                try {
                    fo.writeToFile(imageName);
                } 
                catch (IOException ioe) {
                    view.displayErrorMessage(ioe.getMessage());
                }
            }
        }
    }
    
    private class OpenButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String userDir = System.getProperty("user.home");
            JFileChooser openChooser = new JFileChooser(userDir + "/Desktop");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text Files", "txt");
            openChooser.setFileFilter(filter);

            int returnVal = openChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String tempString = openFile(openChooser.getSelectedFile(), false);
                if(ae.getSource() == view.getCenterPanel().getOpenButton()) {
                    view.getCenterPanel().displayText(tempString);
                }
                if(ae.getSource() == view.getRightPanel().getOpenButton()) {
                    view.getRightPanel().displayText(tempString);
                }
            }
        }
    }
    private class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            String aString = "";            
            String userDir = System.getProperty("user.home");
            JFileChooser saveChooser = new JFileChooser(userDir + "/Desktop");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Text Files", "txt");
            saveChooser.setFileFilter(filter);
            int returnVal = saveChooser.showSaveDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {

                view.setF(saveChooser.getSelectedFile());
                FileOps fo = new FileOps(view.getF(), view.hasAppendStatus());
                
                if(ae.getSource() == view.getCenterPanel().getSaveButton()) {
                    aString = view.getCenterPanel().getTextArea().getText();
                }
                
                if(ae.getSource() == view.getRightPanel().getSaveButton()) {
                    aString = view.getRightPanel().getTextArea().getText();
                }
                    try {
                        fo.writeToFile(aString);   
                    } catch (IOException ioe) {
                        view.displayErrorMessage(ioe.getMessage());
                    }
            }
        }
    }

    private String openFile(File f, Boolean appendStatus) {
        FileOps fo = new FileOps(f, appendStatus);
        String aString = "";
        try {
            aString = fo.readFromFile();
        } catch (FileNotFoundException fnfe) {
            view.displayErrorMessage("Please check the filename and directory.\n" 
                                    + fnfe.getMessage());
        } catch (IOException ioe) {
            view.displayErrorMessage(ioe.getMessage());
        }
        return aString;
    }

    private class ClearButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String empty = "";
            if(e.getSource() == view.getCenterPanel().getClearButton()) {
                view.getCenterPanel().displayText(empty);
            }
            if(e.getSource() == view.getRightPanel().getClearButton()) {
                view.getRightPanel().displayText(empty);               
            }
        }
    }
}
