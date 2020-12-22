package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DeditController {
    Manager manager;
    Stage stage;
    AdministratorController administratorController;
    DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    @FXML
    TextField ID;
    @FXML
    TextField RID;
    @FXML
    TextField HID;
    @FXML
    TextField Loc;
    @FXML
    TextField Price;
    @FXML
    DatePicker Datefrom;
    @FXML
    DatePicker Dateto;
    public void init(Manager manage, DealInformation dealInformation,AdministratorController administratorController1,Stage stage1) {
        manager = manage;
        administratorController = administratorController1;
        ID.setText(dealInformation.getDealid());
        RID.setText(dealInformation.getRenterid());
        HID.setText(dealInformation.getHolderid());
        Loc.setText(dealInformation.getLoc());
        Price.setText(dealInformation.getPrice()+"");
        Datefrom.setValue(LocalDate.parse(dealInformation.getTimefrom(), DateTimeFormatter.ofPattern("yyyyMMdd")));
        Dateto.setValue(LocalDate.parse(dealInformation.getTimeto(), DateTimeFormatter.ofPattern("yyyyMMdd")));
        stage = stage1;
    }
    public void init1(Manager manager1,AdministratorController administratorController1,Stage stage1){
        manager = manager1;
        stage = stage1;
        administratorController = administratorController1;
    }
    @FXML
    private void confirm(){
       String id = ID.getText();
       String rid = RID.getText();
       String hid = HID.getText();
       String loc = Loc.getText();
       String price = Price.getText();
       String datefrom = Datefrom.getValue().format(yyyyMMdd);
       String dateto = Dateto.getValue().format(yyyyMMdd);
        if (!id.equals("") && AddController.isNumeric(price) && !loc.equals("") && !rid.equals("") && !hid.equals("") && !datefrom.equals("") && !dateto.equals("")) {
            if (Integer.parseInt(dateto) > Integer.parseInt(datefrom)) {
                if(manager.insertD(new DealInformation(id,rid,hid,loc,datefrom,dateto,Integer.parseInt(price)))){
                    stage.close();
                    administratorController.Drefresh();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("已存在该合约记录");
                    alert.setContentText("请检查您的合约信息");
                    alert.showAndWait();
                }
            }else error2();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("合约信息缺失或格式问题");
            alert.setContentText("请检查您的房产信息格式与完整性");
            alert.showAndWait();
        }
    }

    static void error2(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("合约开始时间与结束时间冲突");
        alert.setContentText("请检查您的合约时间设置");
        alert.showAndWait();
    }
}
