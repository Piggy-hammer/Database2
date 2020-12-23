package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateController {
    @FXML
    DatePicker Datefrom;
    @FXML
    DatePicker Dateto;
    DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    Stage stage;
    int mode;
    OnsaleController onsaleController;

    public void init(Stage stage1,int mode1,OnsaleController onsaleController1){stage = stage1;mode = mode1; onsaleController = onsaleController1;}

    public String getDatefrom() {
        return Datefrom.getValue().format(yyyyMMdd);
    }
    public String getDateto() {
        return Dateto.getValue().format(yyyyMMdd);
    }
    @FXML private void confirm() throws IOException {
        try {
        if (Integer.parseInt(Datefrom.getValue().format(yyyyMMdd)) > Integer.parseInt(Dateto.getValue().format(yyyyMMdd))){
            DeditController.error2();
        }else {
            stage.close();
            switch (mode){
                case 1: onsaleController.P1(this);break;
                case 2: onsaleController.Y1(this);break;
                case 3: onsaleController.S1(this);break;
            }
        }
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("时间缺失");
            alert.setContentText("请检查您的合约开始与结束时间");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
