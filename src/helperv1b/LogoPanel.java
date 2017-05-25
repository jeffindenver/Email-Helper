package helperv1b;

//@author Jeff

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class LogoPanel extends JPanel {
    private BufferedImage javaLogo;
    private final JLabel picLabel;

    public LogoPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
        
        JPanel fillerPanel = new JPanel();
        fillerPanel.setOpaque(false);
        Dimension filler = new Dimension(80, 240);
        fillerPanel.setPreferredSize(filler);

        try {
            javaLogo = ImageIO.read(new File("images/blueJavaCup.png"));
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage() + 
                    "An image could not be found or read.");
        }
        picLabel = new JLabel(new ImageIcon(javaLogo));
        
        add(fillerPanel, BorderLayout.PAGE_START);
        
        add(picLabel, BorderLayout.PAGE_END);
    }

    public JLabel getPicLabel() {
        return picLabel;
    }
}
