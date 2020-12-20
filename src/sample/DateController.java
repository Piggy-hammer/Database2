package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateController {
    @FXML
    DatePicker Datefrom;
    @FXML
    DatePicker Dateto;
    DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
    Stage stage;
    public void init(Stage stage1){stage = stage1;}

    public String getDatefrom() {
        return Datefrom.getValue().format(yyyyMMdd);
    }
    public String getDateto() {
        return Dateto.getValue().format(yyyyMMdd);
    }
    @FXML private void confirm(){
        if (Integer.parseInt(Datefrom.getValue().format(yyyyMMdd)) > Integer.parseInt(Dateto.getValue().format(yyyyMMdd))){
            DeditController.error2();
        }else {
            stage.close();
        }
    }


}
