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
    StringProperty Pic;

    public HouseInformation(String location, String structure, int size, int price, String picture, String owner){
        Location = new SimpleStringProperty(location);
        Structure = new SimpleStringProperty(structure);
        Size = new SimpleIntegerProperty(size);
        Price = new SimpleIntegerProperty(price);
        Pic = new SimpleStringProperty(picture);
        Owner = new SimpleStringProperty(owner);
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

    public String getPic() {
        return Pic.get();
    }

    public StringProperty picProperty() {
        return Pic;
    }
}

