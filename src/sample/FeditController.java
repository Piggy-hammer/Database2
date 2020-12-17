package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.Dragboard;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class FeditController {
    static Manager manager;
    Stage stage;
    AdministratorController administratorController;
    @FXML
    MenuButton Huxing;
    @FXML
    TextField Rent;
    @FXML
    TextField Loc;
    @FXML
    TextField Pic;
    @FXML
    TextField Size;
    @FXML
    TextField Owner;

    public void init(Manager manage, HouseInformation houseInformation,AdministratorController administratorController1) {
        manager = manage;
        administratorController = administratorController1;
        Loc.setText(houseInformation.getLocation());
        Huxing.setText(houseInformation.getStructure());
        Pic.setText(houseInformation.getPic());
        Rent.setText(houseInformation.getPrice() + "");
        Size.setText(houseInformation.getSize() + "");
        Owner.setText(houseInformation.getOwner());
        Pic.setOnDragOver(event -> {
            Dragboard dragboard = event.getDragboard();
            String s = dragboard.getFiles().get(0).getPath();
            Pic.setText(s);
        });
    }

    public void init1(Manager manager1,AdministratorController administratorController1){
        manager = manager1;
        administratorController = administratorController1;
    }

    @FXML
    private void selectP() {
        Huxing.setText("平层");
    }

    @FXML
    private void selectY() {
        Huxing.setText("跃层");
    }

    @FXML
    private void selectS() {
        Huxing.setText("雅墅");
    }

    @FXML
    private void selectPic() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "选择预览图片文件 (*.png/*.jpg)", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("选择预览图片文件");
        Pic.setText(fileChooser.showOpenDialog(new Stage()).getPath());
    }

    @FXML
    private void confirm(){
        String huxing = Huxing.getText();
        String rent = Rent.getText();
        String loc = Loc.getText();
        String pic = Pic.getText();
        String size = Size.getText();
        String owner = Owner.getText();
        if (!huxing.equals("选择户型") && AddController.isNumeric(rent) && !loc.equals("") && !pic.equals("") && AddController.isNumeric(size) && !owner.equals("")) {
            manager.insertF(new HouseInformation(loc,huxing,Integer.parseInt(size),Integer.parseInt(rent),pic,owner));
            stage.close();
            administratorController.HouseRefresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("房产信息缺失或格式问题");
            alert.setContentText("请检查您的房产信息格式与完整性");
            alert.showAndWait();
        }
        System.out.println(Huxing.getText() + " " + Rent.getText() + " " + Pic.getText() + " " + Loc.getText());
    }
}