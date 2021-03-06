package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddController {
    static Manager manager;
    Stage stage;
    String user;
    HolderController holderController;
    @FXML
    MenuButton Huxing;
    @FXML
    TextField Rent;
    @FXML
    TextField Loc;
    @FXML
    TextField Pic;
    @FXML
    TextField Pic2;
    @FXML
    TextField Pic3;
    @FXML
    TextField Size;
    @FXML
    TextArea Describe;
    @FXML
    CheckBox Breakfast;
    @FXML
    CheckBox Tv;
    @FXML
    CheckBox Wifi;
    @FXML
    CheckBox Bus;
    @FXML
    CheckBox Subway;
    @FXML
    CheckBox Park;
    @FXML
    CheckBox Pot;

    public void init(Manager manage,Stage stage,String user,HolderController holderController) {
        manager = manage;
        Pic.setOnDragOver(event -> {
            Dragboard dragboard = event.getDragboard();
            String s = dragboard.getFiles().get(0).getPath();
            Pic.setText(s);
        });
        Pic2.setOnDragOver(event -> {
            Dragboard dragboard = event.getDragboard();
            String s = dragboard.getFiles().get(0).getPath();
            Pic2.setText(s);
        });
        Pic3.setOnDragOver(event -> {
            Dragboard dragboard = event.getDragboard();
            String s = dragboard.getFiles().get(0).getPath();
            Pic3.setText(s);
        });
        this.stage = stage;
        this.user = user;
        this.holderController = holderController;
    }

    @FXML
    private void selectP(){
        Huxing.setText("平层");
    }
    @FXML
    private void selectY(){
        Huxing.setText("跃层");
    }
    @FXML
    private void selectS(){
        Huxing.setText("雅墅");
    }
    @FXML
    private void selectPic(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "选择预览图片文件 (*.png/*.jpg)", "*.jpg","*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("选择预览图片文件");
        Pic.setText(fileChooser.showOpenDialog(new Stage()).getPath());
    }
    @FXML
    private void selectPic2(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "选择预览图片文件 (*.png/*.jpg)", "*.jpg","*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("选择预览图片文件");
        Pic2.setText(fileChooser.showOpenDialog(new Stage()).getPath());
    }
    @FXML
    private void selectPic3(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "选择预览图片文件 (*.png/*.jpg)", "*.jpg","*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setTitle("选择预览图片文件");
        Pic3.setText(fileChooser.showOpenDialog(new Stage()).getPath());
    }
    @FXML
    private void confirm() throws IOException {
        String huxing = Huxing.getText();
        String rent = Rent.getText();
        String loc = Loc.getText();
        String pic = Pic.getText();
        String size = Size.getText();
        String pic2 = Pic2.getText();
        String pic3 = Pic3.getText();
        int breakfast = Breakfast.isSelected() ? 1 : 0;
        int wifi = Wifi.isSelected() ? 1 : 0 ;
        int subway = Subway.isSelected() ? 1 : 0;
        int park = Park.isSelected() ? 1 : 0;
        int tv = Park.isSelected() ? 1 : 0;
        int pot = Pot.isSelected() ? 1: 0;
        int bus = Bus.isSelected() ? 1: 0;
        if(!huxing.equals("选择户型") && isNumeric(rent) && !loc.equals("") && !pic.equals("") && isNumeric(size) && !pic2.equals("") && !pic3.equals("")){
            if (manager.insertF(new HouseInformation(loc,huxing,Integer.parseInt(size),Integer.parseInt(rent),pic,pic2,pic3,manager.getRenter("所有ID","所有姓名","所有性别",user,"所有微信号").get(0).getID(),breakfast,wifi,subway,park,tv,pot,bus,Describe.getText()))) {
                holderController.init(manager, user);
                stage.close();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("已存在该房产");
                alert.setContentText("请检查您的房产信息");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("房产信息缺失或格式问题");
            alert.setContentText("请检查您的房产信息格式与完整性");
            alert.showAndWait();
        }
        System.out.println(Huxing.getText()+"  "+Rent.getText()+" "+Pic.getText()+" "+Loc.getText());
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
