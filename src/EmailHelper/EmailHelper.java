//******************************************************************************
// @author Jeff Shepherd
// Title: Email Helper
// Purpose: This program allows the user to organize accounts and associated
// emails then quickly load the template emails into the system clipboard to 
// be pasted into an outgoing email.
//******************************************************************************
package EmailHelper;

public class EmailHelper {

    public static void main(String[] args) {
        HelperModel model = new HelperModel();
        HelperView view = new HelperView();
        HelperController controller = new HelperController(view, model);

        view.setVisible(true);
    }
}
