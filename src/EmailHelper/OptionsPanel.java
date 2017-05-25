package EmailHelper;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class OptionsPanel extends JPanel {

    //combo box rows
    private final ArrayList<ComboRow> accountRows;
    private final JPanel oneColumn;

    public OptionsPanel() {
        accountRows = new ArrayList<>();
        setOpaque(false);
        oneColumn = new JPanel(new GridLayout(4, 1, 2, 2));
        oneColumn.setOpaque(false);
        add(oneColumn);

    }//end constructor
    
    public void addAccount(ArrayList<Account> accounts) {
        for (Account account : accounts) {
            ComboRow aRow = new ComboRow(account.getName(), account.getKeys());
            accountRows.add(aRow);
        }
    }
    
    public void addAccountsToPanel () {
        for (ComboRow aRow : accountRows) {
            oneColumn.add(aRow);
        }
    }
    
    public JButton getButton(int index) {
        return accountRows.get(index).getButton();
    }
    
    public JComboBox getComboBox(int index) {
        return accountRows.get(index).getComboBox();
    }
    
}//end class
