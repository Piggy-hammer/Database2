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
    static String user ;

    public void init(Stage stageIn, Manager managerIn, int authority,String user) throws IOException {
        stage = stageIn;
        manager = managerIn;
        this.user = user;
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
        onsaleController.init(splitPane,manager,user);
    }

    @FXML
    private void Add() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/holder.fxml"));
        System.out.println(loader.getLocation());
        AnchorPane anchorPane = loader.load();
        splitPane.getItems().set(1, anchorPane);
        HolderController addController = loader.getController();
        addController.init(manager,user);
    }
}
