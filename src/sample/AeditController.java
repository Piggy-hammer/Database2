package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AeditController {
    Manager manager;
    Stage stage;
    AdministratorController administratorController;
    @FXML TextField ID;
    @FXML TextField Code;
    @FXML MenuButton Authority;
    @FXML Label A;
    @FXML TextField Name;
    @FXML MenuButton Sex;
    @FXML private void male(){Sex.setText("男");}
    @FXML private void female(){Sex.setText("女");}
    @FXML TextField Wechat;
    @FXML TextField Tel;
    @FXML private void Set1(){Authority.setText("1");A.setText("用户");}
    @FXML private void Set2(){Authority.setText("2");A.setText("管理员");}
    @FXML private void Set3(){Authority.setText("1");A.setText("超级管理员");}
    public void init(Manager manager1, RenterInformation selectedItem, AdministratorController administratorController1, Stage stage1) {
        manager = manager1;
        administratorController = administratorController1;
        stage = stage1;
        ID.setText(selectedItem.getID());
        Code.setText(selectedItem.getCode());
        switch (selectedItem.getAuthority()){
            case 1: Set1();break;
            case 2: Set2();break;
            case 3: Set3();break;
        }
    }public void init1(Manager manager1,AdministratorController administratorController1,Stage stage1){
        manager = manager1;
        stage = stage1;
        administratorController = administratorController1;
    }
    @FXML
    private void confirm(){
        if (!ID.getText().equals("") && !Code.getText().equals("") && !Wechat.getText().equals("") && !Code.getText().equals("") && !Tel.getText().equals("") && AddController.isNumeric(Authority.getText())) {
            if(manager.insertR(new RenterInformation(ID.getText(),Name.getText(),Sex.getText(),Tel.getText(),Wechat.getText(),Code.getText(),Integer.parseInt(Authority.getText())))) {
                stage.close();
                administratorController.Arefresh();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("已存在该用户");
                alert.setContentText("请检查您的用户信息格式");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("登录名信息缺失或格式问题");
            alert.setContentText("请检查您的登录名信息格式与完整性");
            alert.showAndWait();
        }
    }
}
