package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HouseInformation {
    StringProperty Location;
    StringProperty Structure;
    IntegerProperty Size;
    IntegerProperty Price;
    StringProperty Owner;
    String Pic1;
    String Pic2;
    String Pic3;
    String Describe;
    int Breakfast;
    int Wifi;
    int Park;
    int Tv;
    int Pot;
    int Subway;
    int Bus;
    int HID;

    public HouseInformation(String location, String structure, int size, int price, String pic1, String pic2, String pic3,String owner, int breakfast, int wifi, int subway, int park, int tv, int pot, int bus, String describe){
        Location = new SimpleStringProperty(location);
        Structure = new SimpleStringProperty(structure);
        Size = new SimpleIntegerProperty(size);
        Price = new SimpleIntegerProperty(price);
        Pic1 = pic1 ;
        Pic2 = pic2 ;
        Pic3 = pic3 ;
        Owner = new SimpleStringProperty(owner);
        Breakfast = breakfast;
        Park = park;
        Tv = tv;
        Pot = pot;
        Subway = subway;
        Bus = bus;
        Wifi = wifi;
        Describe = describe;
        HID = Location.hashCode();
    }

    public String getLocation() {
        return Location.get();
    }

    public StringProperty locationProperty() {
        return Location;
    }

    public String getStructure() {
        return Structure.get();
    }

    public StringProperty structureProperty() {
        return Structure;
    }

    public int getSize() {
        return Size.get();
    }

    public IntegerProperty sizeProperty() {
        return Size;
    }

    public int getPrice() {
        return Price.get();
    }

    public IntegerProperty priceProperty() {
        return Price;
    }

    public String getOwner() {
        return Owner.get();
    }

    public StringProperty ownerProperty() {
        return Owner;
    }

}

