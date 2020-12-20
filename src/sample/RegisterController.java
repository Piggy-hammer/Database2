package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class RegisterController {
    static Manager manager;
    Stage stage;

    @FXML TextField ID;
    @FXML TextField Name;
    @FXML TextField Wechat;
    @FXML TextField Tel;
    @FXML MenuButton Sex;
    @FXML TextField Code;
    Stage stage1;
    @FXML private void male(){Sex.setText("男");}
    @FXML private void female(){Sex.setText("女");}

    public void init1(Manager manager1,Stage stage1){
        stage = stage1;
        manager = manager1;
    }
    @FXML private void Quit(){
        stage1.close();
    }

    @FXML
    private void confirm() throws IOException {
        String id = ID.getText();
        String name = Name.getText();
        String tel = Tel.getText();
        String sex = Sex.getText();
        String wechat = Wechat.getText();
        String code = Code.getText();
        if (!id.equals("") && AddController.isNumeric(tel) && !name.equals("") && !wechat.equals("") && !code.equals("")) {
            manager.insertR(new RenterInformation(id,name,sex,tel,wechat));
            stage.close();
            manager.insertA(new AuthorityInformation(id, code,1));
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/C.fxml")));
            AnchorPane pane = loader.load();
            stage1 = new Stage();
            Scene sceneMain = new Scene(pane);
            stage1.setScene(sceneMain);
            stage1.setTitle("注册成功");
            stage1.getIcons().add(new Image(Main.class.getResourceAsStream("/1.png")));
            stage1.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("租客信息缺失或格式问题");
            alert.setContentText("请检查您的注册信息格式与完整性");
            alert.showAndWait();
        }
    }
}