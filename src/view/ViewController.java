package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewController implements Initializable {
    @FXML
    Label label;
    @FXML
    Button show;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show.setOnAction(e->{label.setText("Testing");});
    }
}
