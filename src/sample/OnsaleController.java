package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class OnsaleController {
    static Manager manager;
    static SplitPane splitPane;
    
    public OnsaleController(){
    }
    public void init(SplitPane tPane, Manager manager) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/onsale.fxml"));
        System.out.println(loader.getLocation());
        AnchorPane anchorPane = loader.load();
        splitPane = tPane;
        this.manager = manager;
        splitPane.getItems().set(1,anchorPane);
    }

    @FXML
    public void Ping() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/ping.fxml"));
        ScrollPane anchorPane = loader.load();
        splitPane.getItems().set(1,anchorPane);
        GridPane gridPane = (GridPane)anchorPane.getContent();
       // gridPane.
    }
}
