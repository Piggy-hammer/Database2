package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Wssb {
    @FXML
    Label code;
    Stage stage;
    public void init(String s, Stage stage1){
        code.setText(s);
        stage = stage1;
    }
    @FXML
    private void cancel(){
        stage.close();
    }

}
