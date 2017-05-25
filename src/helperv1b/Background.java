package helperv1b;

import javax.swing.*;
import java.awt.*;

class Background extends JPanel {

    private Image bg;

    public Background(String backgroundFilepath) {
        setOpaque(false);
        if (backgroundFilepath == null || 
                backgroundFilepath.equalsIgnoreCase("") ||
                    backgroundFilepath.equalsIgnoreCase("backgrounds/")) {
            bg = new ImageIcon("backgrounds/default.png").getImage();
        }
        else {
            bg = new ImageIcon(backgroundFilepath).getImage();
        }
    }

    public void setBg(String str) {
        this.bg = new ImageIcon("backgrounds/" + str).getImage();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
