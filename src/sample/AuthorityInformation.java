package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AuthorityInformation {
    StringProperty Id;
    StringProperty Code;
    IntegerProperty Authority;
    public AuthorityInformation(String id, String code, int authority){
        Id = new SimpleStringProperty(id);
        Code = new SimpleStringProperty(code);
        Authority = new SimpleIntegerProperty(authority);
    }

    public String getId() {
        return Id.get();
    }

    public StringProperty idProperty() {
        return Id;
    }

    public String getCode() {
        return Code.get();
    }

    public StringProperty codeProperty() {
        return Code;
    }

    public int getAuthority() {
        return Authority.get();
    }

    public IntegerProperty authorityProperty() {
        return Authority;
    }
}