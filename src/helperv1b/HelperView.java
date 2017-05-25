package helperv1b;

import FileOps.FileOps;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class HelperView extends JFrame {
    
    //Background
    private final Background bg;
    private String lastBackground;
    private String backgroundDirectory;
    private List<String> backgroundImageNames;
    
    //JFileChooser fields
    private JFileChooser openChooser;
    private JFileChooser saveChooser;

    //file related fields
    private File f;
    private final boolean appendStatus;

    //Panels
    private final CenterPanel centerPanel;
    private final RightPanel rightPanel;
    private final LogoPanel logoPanel;
    private final OptionsPanel optionsPanel;

    public HelperView() {

        appendStatus = false;

        this.setTitle("Email Helper");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setBounds(int x, int y, int width, int height)
        this.setBounds(10, 10, 1250, 420);

        //background panel
        backgroundDirectory = "backgrounds/";
        FileOps fo = new FileOps("lastBackground.txt", false);
        try {
            lastBackground = fo.readSingleLineFromFile();
        }
        catch (IOException ioe) {
            displayErrorMessage(ioe.getMessage());
        }
        String backgroundFilepath = backgroundDirectory + lastBackground;
        bg = new Background(backgroundFilepath);

        //for adding a border
        JPanel backPanel = new JPanel(new BorderLayout());
        backPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        backPanel.setOpaque(false);

        //for centerPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setOpaque(false);

        //for holding options panel
        JPanel westPanel = new JPanel(new GridLayout(1, 1, 20, 20));
        westPanel.setOpaque(false);

        //for holding rightPanel
        JPanel eastPanel = new JPanel();
        eastPanel.setOpaque(false);

        //panels from left to right
        optionsPanel = new OptionsPanel();
        centerPanel = new CenterPanel();
        rightPanel = new RightPanel();
        logoPanel = new LogoPanel();

        //attach main and east panels
        westPanel.add(optionsPanel);
        mainPanel.add(centerPanel);
        eastPanel.add(rightPanel);
        eastPanel.add(logoPanel);
        
        //attach all to backpanel
        backPanel.add(westPanel, BorderLayout.LINE_START);
        backPanel.add(mainPanel, BorderLayout.CENTER);
        backPanel.add(eastPanel, BorderLayout.LINE_END);
     
        bg.add(backPanel);
        getContentPane().add(bg);

    }//end constructor

    void addButtonListener(ActionListener buttonListener, JButton aButton) {
        aButton.addActionListener(buttonListener);
    }

    void addMouseAdapter(MouseAdapter mouseListener, JTextArea textArea) {
        textArea.addMouseListener(mouseListener);
    }
    
    void addMouseAdapter(MouseAdapter mouseListener, JLabel aLabel) {
        aLabel.addMouseListener(mouseListener);
    }

    public void inventoryBackgroundImages() {
        //read files located in the /bacgrounds directory to a list
        File backgroundPath = new File(backgroundDirectory);
        String imageNamesArr[] = backgroundPath.list();
        List<String> imageList = Arrays.asList(imageNamesArr);
        setBackgroundImageNames(imageList);
    }
    
    public List<String> getBackgroundImageNames() {
        return backgroundImageNames;
    }

    public String getBackgroundImageName(int index) {
        return backgroundImageNames.get(index);
    }
    
    private void setBackgroundImageNames(List<String> backgroundImageNames) {
        this.backgroundImageNames = new ArrayList<>(backgroundImageNames);
    }
    
    Background getBg() {
        return bg;
    }
  
    public JFileChooser getOpenChooser() {
        return openChooser;
    }

    public JFileChooser getSaveChooser() {
        return saveChooser;
    }

    public File getF() {
        return f;
    }

    public boolean hasAppendStatus() {
        return appendStatus;
    }

    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    public RightPanel getRightPanel() {
        return rightPanel;
    }

    public LogoPanel getLogoPanel() {
        return logoPanel;
    }

    public OptionsPanel getOptionsPanel() {
        return optionsPanel;
    }

    public void setF(File f) {
        this.f = f;
    }

    final void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}