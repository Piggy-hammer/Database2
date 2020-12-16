package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class AdministratorController {
    Manager manager;
    public void init(Manager manager1) {
        manager = manager1;
        HouseRefresh();
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
    @FXML private void sDizhi(){ Fsousuo.setText("地址");}
    @FXML private void sHuxing(){ Fsousuo.setText("户型");}
    @FXML private void sPrice(){Fsousuo.setText("价格");}
    @FXML private void sSize(){Fsousuo.setText("面积");}
    @FXML private void sOwner(){Fsousuo.setText("房东");}

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
    private void HouseRefresh() {
        ObservableList<HouseInformation> list = (ObservableList<HouseInformation>) manager.HouseSearch("所有地址",Huxing.getText(),Size.getText(),Price.getText(),"所有房东");
        FC1.setCellValueFactory(cellData -> cellData.getValue().Location);
        FC2.setCellValueFactory(cellData -> cellData.getValue().Structure);
        FC3.setCellValueFactory(cellData -> cellData.getValue().Size.asObject());
        FC4.setCellValueFactory(cellData -> cellData.getValue().Price.asObject());
        FC5.setCellValueFactory(cellData -> cellData.getValue().Owner);
        FangXing.setItems(list);

    }

    @FXML
    public void Fsearch() {

    }
}
