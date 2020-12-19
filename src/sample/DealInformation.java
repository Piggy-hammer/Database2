package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class DealInformation{
    StringProperty Dealid;
    StringProperty Renterid;
    StringProperty Holderid;
    StringProperty Loc;
    StringProperty Timefrom;
    StringProperty Timeto;
    IntegerProperty Price;

    public String getDealid() {
        return Dealid.get();
    }

    public StringProperty dealidProperty() {
        return Dealid;
    }

    public String getRenterid() {
        return Renterid.get();
    }

    public StringProperty renteridProperty() {
        return Renterid;
    }

    public String getHolderid() {
        return Holderid.get();
    }

    public StringProperty holderidProperty() {
        return Holderid;
    }

    public String getLoc() {
        return Loc.get();
    }

    public StringProperty locProperty() {
        return Loc;
    }

    public String getTimefrom() {
        return Timefrom.get();
    }

    public StringProperty timefromProperty() {
        return Timefrom;
    }

    public String getTimeto() {
        return Timeto.get();
    }

    public StringProperty timetoProperty() {
        return Timeto;
    }

    public int getPrice() {
        return Price.get();
    }

    public IntegerProperty priceProperty() {
        return Price;
    }

    public DealInformation(String id, String rid, String hid, String loc, String datefrom, String dateto, int price){
        Dealid = new SimpleStringProperty(id);
        Renterid = new SimpleStringProperty(rid);
        Holderid = new SimpleStringProperty(hid);
        Loc = new SimpleStringProperty(loc);
        Timefrom = new SimpleStringProperty(datefrom);
        Timeto = new SimpleStringProperty(dateto);
        Price = new SimpleIntegerProperty(price);

    }
}
