package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HouseholderInformation {
    StringProperty ID;
    StringProperty Name;
    StringProperty Sex;
    StringProperty Tel;
    StringProperty Wechat;

    public HouseholderInformation(String id, String name, String sex, String tel, String wechat){
        ID = new SimpleStringProperty(id);
        Name = new SimpleStringProperty(name);
        Sex = new SimpleStringProperty(sex);
        Tel = new SimpleStringProperty(tel);
        Wechat = new SimpleStringProperty(wechat);
    }

    public String getID() {
        return ID.get();
    }

    public StringProperty IDProperty() {
        return ID;
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public String getSex() {
        return Sex.get();
    }

    public StringProperty sexProperty() {
        return Sex;
    }

    public String getTel() {
        return Tel.get();
    }

    public StringProperty telProperty() {
        return Tel;
    }

    public String getWechat() {
        return Wechat.get();
    }

    public StringProperty wechatProperty() {
        return Wechat;
    }
}
