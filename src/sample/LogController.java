package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.io.IOException;


public class LogController {
    @FXML
    TextField username;
    @FXML
    TextField code;
    private Manager manager;
    private Stage stage;

    public void init(Stage stage,Manager manager1){
        manager = manager1;
        this.stage = stage;
    }

    @FXML
    private void Login() throws IOException {
        int authority = manager.login(username.getText(),code.getText());
        if(authority != 0){
            MainController mainController = new MainController();
            stage.setTitle("范德豪斯  我们用爱守护您家的温暖");
            mainController.init(stage,manager,authority);
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("不存在该用户");
            alert.setContentText("请检查您的用户名和密码是否输入准确");
            alert.showAndWait();
        }
    }

    @FXML
    private void Refresh(){
        username.setText("");
        code.setText("");
    }
}
