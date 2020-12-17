package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class AdministratorController {
    Manager manager;
    public void init(Manager manager1) {
        manager = manager1;
        FC1.setCellValueFactory(cellData -> cellData.getValue().Location);
        FC2.setCellValueFactory(cellData -> cellData.getValue().Structure);
        FC3.setCellValueFactory(cellData -> cellData.getValue().Size.asObject());
        FC4.setCellValueFactory(cellData -> cellData.getValue().Price.asObject());
        FC5.setCellValueFactory(cellData -> cellData.getValue().Owner);
        RC1.setCellValueFactory(cellData -> cellData.getValue().ID);
        RC2.setCellValueFactory(cellData -> cellData.getValue().Name);
        RC3.setCellValueFactory(cellData -> cellData.getValue().Sex);
        RC4.setCellValueFactory(cellData -> cellData.getValue().Tel);
        RC5.setCellValueFactory(cellData -> cellData.getValue().Wechat);
        HouseRefresh();
        Rrefresh();
    }



    // 房源管理
    @FXML
    TableView<HouseInformation> FangXing;
    @FXML TableColumn<HouseInformation, String> FC1;
    @FXML TableColumn<HouseInformation, String> FC2;
    @FXML TableColumn<HouseInformation, Integer>FC3;
    @FXML TableColumn<HouseInformation, Integer>FC4;
    @FXML TableColumn<HouseInformation, String>FC5;

    @FXML
    TextField Fsousuo;

    @FXML
    MenuButton FsousuoT;
    @FXML private void sDizhi(){ FsousuoT.setText("地址");}
    @FXML private void sHuxing(){ FsousuoT.setText("户型");}
    @FXML private void sPrice(){FsousuoT.setText("价格");}
    @FXML private void sSize(){FsousuoT.setText("面积");}
    @FXML private void sOwner(){FsousuoT.setText("房东");}

    @FXML
    Button Fsearch;

    @FXML
    MenuButton Huxing;
    @FXML private void selectP(){
        Huxing.setText("平层");
    }
    @FXML private void selectY(){
        Huxing.setText("跃层");
    }
    @FXML private void selectS(){
        Huxing.setText("雅墅");
    }
    @FXML private void selectA(){
        Huxing.setText("所有户型");
    }

    @FXML
    MenuButton Price;
    @FXML private void select_100(){
        Price.setText("<100");
    }
    @FXML private void s100_150(){
        Price.setText("100~150");
    }
    @FXML private void s150_200(){
        Price.setText("150~200");
    }
    @FXML private void select_200(){
        Price.setText(">200");
    }
    @FXML private void selectAP(){
        Price.setText("所有价格");
    }

    @FXML
    MenuButton Size;
    @FXML private void select_2000(){
        Size.setText("<2000");
    }
    @FXML private void s2000_4000(){
        Size.setText("2000~4000");
    }
    @FXML private void s4000_6000(){
        Size.setText("4000~6000");
    }
    @FXML private void select_6000(){
        Size.setText(">6000");
    }
    @FXML private void selectAS(){Size.setText("所有面积");}

    @FXML
    public void HouseRefresh() {
        ObservableList<HouseInformation> list = manager.HouseSearch("所有地址",Huxing.getText(),Size.getText(),Price.getText(),"所有房东");
        FangXing.setItems(list);

    }

    @FXML
    public void Fsearch() {
        if (!Fsousuo.getText().equals("")) {
            switch (FsousuoT.getText()) {
                case "地址":
                    ObservableList<HouseInformation> list = manager.HouseSearch(Fsousuo.getText(), "所有户型", "所有面积", "所有价格", "所有房东");
                    FangXing.setItems(list);break;
                case "户型":
                    ObservableList<HouseInformation> list1 = manager.HouseSearch("所有地址", Fsousuo.getText(), "所有面积", "所有价格", "所有房东");
                    FangXing.setItems(list1);break;
                case "面积":
                    if (AddController.isNumeric(Fsousuo.getText())) {
                        ObservableList<HouseInformation> list2 = manager.HouseSearch("所有地址", "所有户型", Fsousuo.getText(), "所有价格", "所有房东");
                        FangXing.setItems(list2);break;
                    }else {
                        error1(); break;
                    }
                case "价格":
                    if (AddController.isNumeric(Fsousuo.getText())) {
                        ObservableList<HouseInformation> list3 = manager.HouseSearch("所有地址", "所有户型", "所有面积", Fsousuo.getText(), "所有房东");
                        FangXing.setItems(list3);break;
                    }else {
                        error1(); break;
                    }
                case "房东":
                    ObservableList<HouseInformation> list4 = manager.HouseSearch("所有地址", "所有户型", "所有面积","所有价格", Fsousuo.getText());
                    FangXing.setItems(list4);break;
                case "搜索条件":
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("搜索条件缺失");
                    alert.setContentText("选择一个您的搜索条件");
                    alert.showAndWait();
            }
        }else {
            error1();
        }
    }

    private void error1() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("搜索条件缺失");
        alert.setContentText("请检查您是否在搜索框中输入格式准确的信息");
        alert.showAndWait();
    }

    @FXML
    private void Fedit() throws IOException {
        try {
            HouseInformation selectedItem = FangXing.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Fedit.fxml")));
            AnchorPane pane = loader.load();
            FeditController feditController = loader.getController();
            Scene scene = new Scene(pane);
            feditController.init(manager,selectedItem,this);
            Stage add = new Stage();
            add.setScene(scene);
            add.setTitle("编辑房屋");
            add.getIcons().add(new Image(Main.class.getResourceAsStream("/1.png")));
            add.show();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("编辑目标缺失");
            alert.setContentText("您必须选择一个目标以编辑");
            alert.showAndWait();
        }
    }

    @FXML
    private void Fadd() throws IOException {
        FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Fedit.fxml")));
        AnchorPane pane = loader.load();
        FeditController feditController = loader.getController();
        Scene scene = new Scene(pane);
        feditController.init1(manager,this);
        Stage add = new Stage();
        add.setScene(scene);
        add.setTitle("添加房屋");
        add.getIcons().add(new Image(Main.class.getResourceAsStream("/1.png")));
        add.show();
    }

    @FXML
    private void Fdelete(){
        try {
            HouseInformation selectedItem = FangXing.getSelectionModel().getSelectedItem();
            manager.delete(selectedItem.getLocation(),selectedItem.getOwner());
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("编辑目标缺失");
            alert.setContentText("您必须选择一个目标以删除");
            alert.showAndWait();
        }
    }


    //住户管理
    @FXML
    TableView<RenterInformation> Rtable;
    @FXML TableColumn <RenterInformation,String> RC1;
    @FXML TableColumn <RenterInformation,String> RC2;
    @FXML TableColumn <RenterInformation,String> RC3;
    @FXML TableColumn <RenterInformation,String> RC4;
    @FXML TableColumn <RenterInformation,String> RC5;

    @FXML TextField Rsousuo;

    @FXML MenuButton RsousuoT;
    @FXML private void Rsid(){RsousuoT.setText("ID");}
    @FXML private void Rsname(){RsousuoT.setText("姓名");}
    @FXML private void Rstel(){RsousuoT.setText("手机号");}
    @FXML private void Rswechat(){RsousuoT.setText("微信号");}

    @FXML MenuButton Rsex;
    @FXML private void Rman(){Rsex.setText("男");}
    @FXML private void Rwoman(){Rsex.setText("女");}
    @FXML private void Rallsex(){Rsex.setText("所有性别");}

    @FXML
    private void Rrefresh() {
        ObservableList<HouseInformation> list =  manager.getRenter("所有ID","所有姓名",Rsex.getText(),"所有手机号","所有微信号");
        FangXing.setItems(list);
    }
    @FXML
    private void Rsearch(){
        if (!Rsousuo.getText().equals("")){
            switch(RsousuoT.getText()) {
                case "ID":
                    ObservableList<HouseInformation> list = manager.getRenter(Fsousuo.getText(), "所有姓名", "所有性别", "所有手机号", "所有微信号");
                    FangXing.setItems(list);break;
                case "姓名":
                    ObservableList<HouseInformation> list1 = manager.getRenter("所有ID", Fsousuo.getText(), "所有性别", "所有手机号", "所有微信号");
                    FangXing.setItems(list1);break;
                case "手机号":
                    ObservableList<HouseInformation> list2 = manager.getRenter("所有ID", "所有姓名", "所有性别", Fsousuo.getText(), "所有微信号");
                    FangXing.setItems(list2);break;
                case "微信号":
                    ObservableList<HouseInformation> list3 = manager.getRenter("所有ID", "所有姓名", "所有性别", "所有手机号", Fsousuo.getText());
                    FangXing.setItems(list3);break;
                case "搜索条件":
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("搜索条件缺失");
                    alert.setContentText("选择一个您的搜索条件");
                    alert.showAndWait();
            }
        }else {
            error1();
        }
    }
    @FXML
    private void Redit(){
        try {
            RenterInformation selectedItem = Rtable.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Redit.fxml")));
            AnchorPane pane = loader.load();
            ReditController reditController = loader.getController();
            Scene scene = new Scene(pane);
            reditController.init(manager,selectedItem,this);
            Stage add = new Stage();
            add.setScene(scene);
            add.setTitle("编辑租户");
            add.getIcons().add(new Image(Main.class.getResourceAsStream("/1.png")));
            add.show();
        }catch (NullPointerException | IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("编辑目标缺失");
            alert.setContentText("您必须选择一个目标以编辑");
            alert.showAndWait();
        }
    }
    @FXML
    private void Rdelete(){

    }

    @FXML
    private void Radd(){

    }

}
