package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ReditController {
    static Manager manager;
    Stage stage;
    AdministratorController administratorController;

    @FXML TextField ID;
    @FXML TextField Name;
    @FXML TextField Wechat;
    @FXML TextField Tel;
    @FXML MenuButton Sex;
    @FXML private void male(){Sex.setText("男");}
    @FXML private void female(){Sex.setText("女");}

    public void init(Manager manager1, RenterInformation selectedItem, AdministratorController administratorController1, Stage stage1) {
        this.manager = manager1;
        this.administratorController = administratorController1;
        stage = stage1;
        ID.setText(selectedItem.getID());
        Name.setText(selectedItem.getName());
        Wechat.setText(selectedItem.getWechat());
        Sex.setText(selectedItem.getSex());
        Tel.setText(selectedItem.getTel());
    }

    public void init1(Manager manager1,AdministratorController administratorController1,Stage stage1){
        stage = stage1;
        manager = manager1;
        administratorController = administratorController1;
    }

    @FXML
    private void confirm(){
        String id = ID.getText();
        String name = Name.getText();
        String tel = Tel.getText();
        String sex = Sex.getText();
        String wechat = Wechat.getText();
        if (!id.equals("") && AddController.isNumeric(tel) && !name.equals("") && !wechat.equals("")) {
            manager.insertR(new RenterInformation(id,name,sex,tel,wechat));
            stage.close();
            administratorController.Rrefresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("租客信息缺失或格式问题");
            alert.setContentText("请检查您的租客信息格式与完整性");
            alert.showAndWait();
        }
    }
}
