package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Struct;
import java.util.ArrayList;


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
    @FXML
    ImageView Pic;

    Stage stage;
    Manager manager;
    HouseInformation e;
    String user;
    String datefrom;
    String dateto;
    String [] pics;
    int t ;

    public void init(HouseInformation e, String user, String datefrom, String dateto, Stage stage, Manager manager) {
        this.stage = stage;
        this.manager = manager;
        this.e = e;
        this.user = user;
        this.datefrom = datefrom;
        this.dateto = dateto;
        Price.setText(e.getPrice()+"");
        String name = manager.getRenter(e.getOwner(),"所有姓名","所有性别","所有手机号","所有微信号").get(0).getName();
        Holder.setText(name);
        Structure.setText(e.getStructure());
        Size.setText(e.getSize()+"平米");
        Breakfast.setText(e.Breakfast == 1 ? "提供早餐":"无早餐");
        Wifi.setText(e.Wifi == 1 ? "免费WIFI":"无WIFI");
        Park.setText(e.Park == 1 ? "提供车位":"无车位");
        Tv.setText(e.Tv == 1 ? "内有电视":"无电视");
        Pot.setText(e.Pot == 1 ? "内有厨具":"无厨具");
        Subway.setText(e.Subway == 1?"临近地铁":"远离地铁");
        Bus.setText(e.Bus == 1 ?"临近公交":"远离公交");
        Describe.setText(e.Describe);
        pics = new String[3];
        pics[0] = e.Pic1; pics[1] = e.Pic2; pics[2] = e.Pic3;
        t=0;
        Pic.setImage(new Image(pics[t]));
    }

    @FXML private void confirm() throws IOException {
            FXMLLoader loader1 = new FXMLLoader(Main.class.getResource("/Success.fxml"));
            AnchorPane pane = loader1.load();
            String s = manager.rent(e.getLocation(), user, datefrom, dateto);
            System.out.println(s);
            if (!AddController.isNumeric(s)) {
                Scene scene = new Scene(pane);
                Stage stage = new Stage();
                stage.setScene(scene);
                Wssb wssb = loader1.getController();
                wssb.init(s,stage);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("房源被占用");
                alert.setContentText("请检查您交易信息");
                alert.showAndWait();
            }
    }
    @FXML
    private void cancel(){
        stage.close();
    }


    public void last() {
        if(t == 2) t = -1;
        t++;
        Pic.setImage(new Image(pics[t]));
    }

    public void next() {
        if(t == 0) t = 2;
        t--;
        Pic.setImage(new Image(pics[t]));
    }
}
