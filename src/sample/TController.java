package sample;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Struct;


public class TController {
    @FXML Label Price;
    @FXML Label Holder;
    @FXML Label Structure;
    @FXML Label Size;
    @FXML Label Breakfast;
    @FXML Label Wifi;
    @FXML Label Park;
    @FXML Label Tv;
    @FXML Label Pot;
    @FXML Label Subway;
    @FXML Label Bus;
    @FXML Text Describe;


    Stage stage;
    Manager manager;
    HouseInformation e;
    String user;
    String datefrom;
    String dateto;

    public void init(HouseInformation e, String user, String datefrom, String dateto, Stage stage, Manager manager) {
        this.stage = stage;
        this.manager = manager;
        this.e = e;
        this.user = user;
        this.datefrom = datefrom;
        this.dateto = dateto;
        Price.setText(e.getPrice()+"");
        Holder.setText(e.getOwner());
        Structure.setText(e.getStructure());
        Size.setText(e.getSize()+"");
        Breakfast.setText(e.Breakfast == 1 ? "提供早餐":"无早餐");
        Wifi.setText(e.Wifi == 1 ? "免费WIFI":"无WIFI");
        Park.setText(e.Park == 1 ? "提供车位":"无车位");
        Tv.setText(e.Tv == 1 ? "内有电视":"无电视");
        Pot.setText(e.Pot == 1 ? "内有厨具":"无厨具");
        Subway.setText(e.Subway == 1?"临近地铁":"远离地铁");
        Bus.setText(e.Bus == 1 ?"临近公交":"远离公交");
        Describe.setText(e.Describe);
    }

    @FXML private void confirm(){
        String ID = manager.rent(e, user, datefrom, dateto);
        stage.close();
    }
    @FXML private void cancel(){
        stage.close();
    }


}
