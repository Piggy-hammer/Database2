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
        switch (authority){
            case 2:
                FXMLLoader loader = new FXMLLoader((Main.class.getResource("/manager.fxml")));
                TabPane pane = loader.load();
                AdministratorController administratorController = loader.getController();
                administratorController.init(manager);
                Scene sceneMain = new Scene(pane);
                stage.setScene(sceneMain);
                stage.centerOnScreen();break;
            case 3:
                FXMLLoader loader1 = new FXMLLoader((Main.class.getResource("/Supermanager.fxml")));
                TabPane pane1 = loader1.load();
                AdministratorController administratorController1 = loader1.getController();
                administratorController1.init(manager);
                Scene sceneMain1 = new Scene(pane1);
                stage.setScene(sceneMain1);
                stage.centerOnScreen();break;
            case 1:
                FXMLLoader loader2 = new FXMLLoader((Main.class.getResource("/guest.fxml")));
                splitPane = loader2.load();
                Scene sceneMain2 = new Scene(splitPane);
                stage.setScene(sceneMain2);
                stage.centerOnScreen();
        }
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
