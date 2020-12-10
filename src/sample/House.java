package sample;

import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class House {

    String huXing;
    int rent;
    String Pic;
    String Loca;


    public House(String huXing,int rent,String Pic,String Loc) throws IOException {
        this.huXing = huXing;
        this.rent = rent;
        this.Pic = Pic;
        this.Loca = Loc;

    }

    public String getHuXing() {
        return huXing;
    }

    public String getPic() {
        return Pic;
    }

    public int getRent() {
        return rent;
    }

}
