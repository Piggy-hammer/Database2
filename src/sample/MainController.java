package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    static Stage stage;
    static Manager manager;
    static SplitPane splitPane;

    public void init(Stage stageIn, Manager managerIn, int authority) throws IOException {
        stage = stageIn;
        manager = managerIn;
        FXMLLoader loader;
        if (authority == 2) {
             loader = new FXMLLoader((Main.class.getResource("/manager.fxml")));

        }else {
             loader = new FXMLLoader((Main.class.getResource("/guest.fxml")));

        }
        splitPane = loader.load();
        Scene sceneMain = new Scene(splitPane);
        stage.setScene(sceneMain);
        stage.centerOnScreen();
    }

    @FXML
    private void Onsale() throws IOException {
        OnsaleController onsaleController = new OnsaleController();
        onsaleController.init(splitPane,manager);
    }
}
