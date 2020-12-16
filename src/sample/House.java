package sample;
import java.io.IOException;

public class House {

    String huXing;
    int rent;
    String Pic;
    String Loca;//primary key
    int Size;


    public House(String huXing,int rent,String Pic,String Loc,int Size) throws IOException {
        this.huXing = huXing;
        this.rent = rent;
        this.Pic = Pic;
        this.Loca = Loc;
        this.Size = Size;

    }
}
