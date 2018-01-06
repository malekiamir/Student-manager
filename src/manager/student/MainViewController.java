package manager.student;
/*
    Amir Maleki
*/
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainViewController {
    @FXML private Button backButton;
    @FXML private Label  label;

    public void initialize() {}

    public void init(final FormManager formManager, String messageString) {
        label.setText(messageString);
        backButton.setOnAction((ActionEvent event) -> {
            formManager.back();
        });
    }
}