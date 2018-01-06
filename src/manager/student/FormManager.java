package manager.student;
/*
    Amir Maleki
*/
import java.io.IOException;
import java.util.logging.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;

public class FormManager {
    private Scene scene;

    public FormManager(Scene scene) {
        this.scene = scene;
    }
    
    public void authenticated(String messageString) {
        showMainView(messageString);
    }
 
    public void back() {
        showFormScreen();
    }

    public void showFormScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("form.fxml")
            );
            scene.setRoot((Parent) loader.load());
            FormController controller = 
                loader.<FormController>getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(FormManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMainView(String message) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("mainview.fxml")
            );
            scene.setRoot((Parent) loader.load());
            MainViewController controller = 
                loader.<MainViewController>getController();
            controller.init(this, message);
        } catch (IOException ex) {
            Logger.getLogger(FormManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}