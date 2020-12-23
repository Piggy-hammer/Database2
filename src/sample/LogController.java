package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
        if (AddController.isNumeric(username.getText()) && username.getText().length()==11) {
            int authority = manager.login(username.getText(), code.getText());
            System.out.println(authority);
            if (authority != 0) {
                MainController mainController = new MainController();
                stage.setTitle("范德豪斯  我们用爱守护您家的温暖");
                mainController.init(stage, manager, authority, username.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("不存在该用户");
                alert.setContentText("请检查您的手机号和密码是否输入准确");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("用户名格式错误");
            alert.setContentText("请检查您的手机号是否输入准确");
            alert.showAndWait();
        }
    }

    @FXML
    private void Rejest() throws IOException {
        FXMLLoader loader = new FXMLLoader((Main.class.getResource("/register.fxml")));
        AnchorPane pane = loader.load();
        RegisterController Controller = loader.getController();
        Stage stage1 = new Stage();
        Controller.init1(manager,stage1);
        Scene sceneMain = new Scene(pane);
        stage1.setScene(sceneMain);
        stage1.setTitle("注册为会员");
        stage1.getIcons().add(new Image(Main.class.getResourceAsStream("/1.png")));
        stage1.show();
    }
}
