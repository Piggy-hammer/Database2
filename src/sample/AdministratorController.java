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
import java.time.format.DateTimeFormatter;


public class AdministratorController {
    Manager manager;
    DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
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
        DC1.setCellValueFactory(cellData -> cellData.getValue().Dealid);
        DC2.setCellValueFactory(cellData -> cellData.getValue().Renterid);
        DC3.setCellValueFactory(cellData -> cellData.getValue().Holderid);
        DC4.setCellValueFactory(cellData -> cellData.getValue().Loc);
        DC5.setCellValueFactory(cellData -> cellData.getValue().Timefrom);
        DC6.setCellValueFactory(cellData -> cellData.getValue().Timeto);
        DC7.setCellValueFactory(cellData -> cellData.getValue().Price.asObject());
        AC1.setCellValueFactory(cellData -> cellData.getValue().Id);
        AC2.setCellValueFactory(cellData -> cellData.getValue().Code);
        AC3.setCellValueFactory(cellData -> cellData.getValue().Authority.asObject());
        HouseRefresh();
        Rrefresh();
        Drefresh();
        Arefresh();
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
            manager.delete(selectedItem.getLocation(),selectedItem.getOwner());
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Fedit.fxml")));
            AnchorPane pane = loader.load();
            FeditController feditController = loader.getController();
            Scene scene = new Scene(pane);
            Stage add = new Stage();
            feditController.init(manager,selectedItem,this,add);
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
        Stage add = new Stage();
        feditController.init1(manager,this,add);
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
    public void Rrefresh() {
        ObservableList<RenterInformation> list =  manager.getRenter("所有ID","所有姓名",Rsex.getText(),"所有手机号","所有微信号");
        Rtable.setItems(list);
    }
    @FXML
    private void Rsearch(){
        if (!Rsousuo.getText().equals("")){
            switch(RsousuoT.getText()) {
                case "ID":
                    ObservableList<RenterInformation> list = manager.getRenter(Fsousuo.getText(), "所有姓名", "所有性别", "所有手机号", "所有微信号");
                    Rtable.setItems(list);break;
                case "姓名":
                    ObservableList<RenterInformation> list1 = manager.getRenter("所有ID", Fsousuo.getText(), "所有性别", "所有手机号", "所有微信号");
                    Rtable.setItems(list1);break;
                case "手机号":
                    ObservableList<RenterInformation> list2 = manager.getRenter("所有ID", "所有姓名", "所有性别", Fsousuo.getText(), "所有微信号");
                    Rtable.setItems(list2);break;
                case "微信号":
                    ObservableList<RenterInformation> list3 = manager.getRenter("所有ID", "所有姓名", "所有性别", "所有手机号", Fsousuo.getText());
                    Rtable.setItems(list3);break;
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
            manager.deleteR(selectedItem.getID());
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Redit.fxml")));
            AnchorPane pane = loader.load();
            ReditController reditController = loader.getController();
            Scene scene = new Scene(pane);
            Stage add = new Stage();
            reditController.init(manager,selectedItem,this,add);
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
        try {
            RenterInformation selectedItem = Rtable.getSelectionModel().getSelectedItem();
            manager.deleteR(selectedItem.getID());
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("编辑目标缺失");
            alert.setContentText("您必须选择一个目标以删除");
            alert.showAndWait();
        }
    }
    @FXML
    private void Radd() throws IOException {
        FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Redit.fxml")));
        AnchorPane pane = loader.load();
        ReditController feditController = loader.getController();
        Scene scene = new Scene(pane);
        Stage add = new Stage();
        feditController.init1(manager,this,add);
        add.setScene(scene);
        add.setTitle("添加租户");
        add.getIcons().add(new Image(Main.class.getResourceAsStream("/1.png")));
        add.show();
    }


    //合同管理
    @FXML DatePicker Datefrom;
    @FXML TableView<DealInformation> Dtable;
    @FXML TableColumn<DealInformation,String> DC1;
    @FXML TableColumn<DealInformation,String> DC2;
    @FXML TableColumn<DealInformation,String> DC3;
    @FXML TableColumn<DealInformation,String> DC4;
    @FXML TableColumn<DealInformation,String> DC5;
    @FXML TableColumn<DealInformation,String> DC6;
    @FXML TableColumn<DealInformation,Integer> DC7;
    @FXML TextField Dsousuo;
    @FXML MenuButton DsousuoT;
    @FXML private void Did(){DsousuoT.setText("合约号");}
    @FXML private void Drid(){DsousuoT.setText("租户ID");}
    @FXML private void Dhid(){DsousuoT.setText("房东ID");}
    @FXML private void Dloc(){DsousuoT.setText("房源地址");}
    @FXML MenuButton Dprice;
    @FXML private void D_2000(){Dprice.setText("<2000");}
    @FXML private void D2000_4000(){Dprice.setText("2000~4000");}
    @FXML private void D4000_6000(){Dprice.setText("4000~6000");}
    @FXML private void D_6000(){Dprice.setText(">6000");}
    @FXML DatePicker Dateto;

    @FXML
    public void Drefresh(){
        System.out.println(Datefrom.getValue().toString());
        ObservableList<DealInformation> list = manager.RentingSearch("所有合约号","所有租户ID","所有房东ID","所有地址",Datefrom.getValue().format(yyyyMMdd),Dateto.getValue().format(yyyyMMdd),Dprice.getText());
        Dtable.setItems(list);
    }
    @FXML
    private void Dsearch() {
        if (!Dsousuo.getText().equals("")) {
            switch (DsousuoT.getText()) {
                case "合约号":
                    ObservableList<DealInformation> list = manager.RentingSearch(Dsousuo.getText(), "所有租户ID", "所有房东ID", "所有地址", "所有日期", "所有日期", "所有价格");
                    Dtable.setItems(list);
                    break;
                case "租户ID":
                    ObservableList<DealInformation> list1 = manager.RentingSearch("所有合约号", Dsousuo.getText(), "所有房东ID", "所有地址", "所有日期", "所有日期", "所有价格");
                    Dtable.setItems(list1);
                    break;
                case "房东ID":
                    ObservableList<DealInformation> list2 = manager.RentingSearch("所有合约号", "所有租户ID", Dsousuo.getText(), "所有地址", "所有日期", "所有日期", "所有价格");
                    Dtable.setItems(list2);
                    break;
                case "房源地址":
                    ObservableList<DealInformation> list3 = manager.RentingSearch("所有合约号", "所有租户ID", "所有房东ID", Dsousuo.getText(), "所有日期", "所有日期", "所有价格");
                    Dtable.setItems(list3);
                    break;
                case "搜索条件":
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("搜索条件缺失");
                    alert.setContentText("选择一个您的搜索条件");
                    alert.showAndWait();
            }
        } else {
            error1();
        }
    }
    @FXML
    private void Dedit(){
        try {
            DealInformation selectedItem = Dtable.getSelectionModel().getSelectedItem();
            manager.deleteD(selectedItem.getDealid());
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Dedit.fxml")));
            AnchorPane pane = loader.load();
            DeditController reditController = loader.getController();
            Scene scene = new Scene(pane);
            Stage add = new Stage();
            reditController.init(manager,selectedItem,this,add);
            add.setScene(scene);
            add.setTitle("编辑合约");
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
    private void Ddelete(){
        try {
            DealInformation selectedItem = Dtable.getSelectionModel().getSelectedItem();
            manager.deleteD(selectedItem.getDealid());
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("编辑目标缺失");
            alert.setContentText("您必须选择一个目标以删除");
            alert.showAndWait();
        }
    }
    @FXML
    private void Dadd() throws IOException {
        FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Dedit.fxml")));
        AnchorPane pane = loader.load();
        DeditController feditController = loader.getController();
        Scene scene = new Scene(pane);
        Stage add = new Stage();
        feditController.init1(manager,this,add);
        add.setScene(scene);
        add.setTitle("添加合约");
        add.getIcons().add(new Image(Main.class.getResourceAsStream("/1.png")));
        add.show();
    }

    //权限管理
    @FXML TableView<AuthorityInformation> Atable;
    @FXML TableColumn<AuthorityInformation, String> AC1;
    @FXML TableColumn<AuthorityInformation, String> AC2;
    @FXML TableColumn<AuthorityInformation, Integer> AC3;
    @FXML TextField Asousuo;
    @FXML MenuButton Aset;
    @FXML Label l;
    int a = 9;
    @FXML private void Aset1(){Aset.setText("1 用户");a = 1;}
    @FXML private void Aset2(){Aset.setText("2 管理员"); a = 2;}
    @FXML private void Aset3(){Aset.setText("3 超级管理员"); a=3;}
    @FXML private void Aset4(){Aset.setText("所有权限等级");a=9;}
    @FXML
    public void Arefresh(){
        ObservableList<AuthorityInformation> list = manager.AuthoritySearch("所有用户名", a);
        Atable.setItems(list);
    }
    @FXML
    private void Asearch() {
        if (!Asousuo.getText().equals("")) {
            ObservableList<AuthorityInformation> list = manager.AuthoritySearch(Asousuo.getText(), a);
            Atable.setItems(list);
        }else{
            error1();
        }
    }
    @FXML
    private void Aedit(){
        try {
            AuthorityInformation selectedItem = Atable.getSelectionModel().getSelectedItem();
            manager.deleteA(selectedItem.getId());
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Aedit.fxml")));
            AnchorPane pane = loader.load();
            AeditController reditController = loader.getController();
            Scene scene = new Scene(pane);
            Stage add = new Stage();
            reditController.init(manager,selectedItem,this,add);
            add.setScene(scene);
            add.setTitle("编辑登录名");
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
    private void Adelete(){
        try {
           AuthorityInformation selectedItem = Atable.getSelectionModel().getSelectedItem();
            manager.deleteA(selectedItem.getId());
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("编辑目标缺失");
            alert.setContentText("您必须选择一个目标以删除");
            alert.showAndWait();
        }
    }
    @FXML
    private void Aadd() throws IOException {
        FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Aedit.fxml")));
        AnchorPane pane = loader.load();
        AeditController feditController = loader.getController();
        Scene scene = new Scene(pane);
        Stage add = new Stage();
        feditController.init1(manager,this,add);
        add.setScene(scene);
        add.setTitle("添加登录名");
        add.getIcons().add(new Image(Main.class.getResourceAsStream("/1.png")));
        add.show();
    }

}
