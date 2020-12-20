package sample;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.security.MessageDigest;

public class TController {
    @FXML
    Label code;
    Stage stage;
    Manager manager;
    House e;
    String user;
    String datefrom;
    String dateto;

    public void init(House e, String user, String datefrom, String dateto, Stage stage, Manager manager) {
        code.setText(getMD5(e.Loca + user + datefrom + dateto));
        this.stage = stage;
        this.manager = manager;
        this.e = e;
        this.user = user;
        this.datefrom = datefrom;
        this.dateto = dateto;
    }

    @FXML private void confirm(){
        manager.rent(e, user, datefrom, dateto);
        stage.close();
    }

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            System.err.println("MD5加密出现错误");
        }
        return str;
    }
}
