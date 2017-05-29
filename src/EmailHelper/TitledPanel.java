package EmailHelper;

import java.awt.FlowLayout;
import static java.awt.Color.orange;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class TitledPanel extends JPanel {

    public TitledPanel(String title) {
        int hgap = 5;
        int vgap = 10;
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, hgap, vgap));
        TitledBorder titleBorder;
        titleBorder = BorderFactory.createTitledBorder(title);
        titleBorder.setTitlePosition(TitledBorder.BOTTOM);
        titleBorder.setTitleColor(orange);
        this.setBorder(titleBorder);

    }
}
