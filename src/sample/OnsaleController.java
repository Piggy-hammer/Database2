package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class OnsaleController {
    static Manager manager;
    static SplitPane splitPane;
    static String user;
    
    public OnsaleController(){
    }
    public void init(SplitPane tPane, Manager manager, String user) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/onsale.fxml"));
        System.out.println(loader.getLocation());
        AnchorPane anchorPane = loader.load();
        splitPane = tPane;
        this.manager = manager;
        this.user = user;
        splitPane.getItems().set(1,anchorPane);
    }

    @FXML
    public void Ping() throws IOException {
        FXMLLoader loader1 = new FXMLLoader(Main.class.getResource("/Timepicker.fxml"));
        AnchorPane pane = loader1.load();
        DateController dateController = loader1.getController();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        dateController.init(stage);
        stage.show();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/ping.fxml"));
        ScrollPane anchorPane = loader.load();
        splitPane.getItems().set(1,anchorPane);
        GridPane gridPane = (GridPane)anchorPane.getContent();
        List<House> list = manager.getPing(dateController.getDatefrom(),dateController.getDateto());
        int i = 1;
        for (House e: list
             ) {
            Image image = new Image(e.Pic);
            ImageView imageView = new ImageView(image);
            Label rent = new Label();
            Text Loc = new Text();
            Button button = new Button();
            button.setText("立即入住");
            button.getStyleClass().add("labe-x");
            button.setOnMouseClicked(event -> {
                try {
                    deal(e,user,dateController.getDatefrom(),dateController.getDateto());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            Loc.setText(e.Loca);
            Loc.setWrappingWidth(120);
            Loc.setFont(Font.font("system",15));
            Loc.setFill(Color.WHITE);
            rent.setText(e.Size+"m²"+"\n"+"￥"+e.rent);
            rent.getStyleClass().add("label-bright");
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            gridPane.add(imageView,0,i);
            gridPane.add(rent,1,i);
            gridPane.add(Loc,2,i);
            gridPane.add(button,3,i);
            i++;
        }
    }

    private void deal(House e, String user, String datefrom, String dateto) throws IOException {
        FXMLLoader loader1 = new FXMLLoader(Main.class.getResource("/T.fxml"));
        AnchorPane pane = loader1.load();
        TController dateController = loader1.getController();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        dateController.init(e,user,datefrom,dateto,stage,manager);
        stage.show();
    }

    @FXML
    public void Yue() throws IOException {
        FXMLLoader loader1 = new FXMLLoader(Main.class.getResource("/Timepicker.fxml"));
        AnchorPane pane = loader1.load();
        DateController dateController = loader1.getController();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        dateController.init(stage);
        stage.show();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/ping.fxml"));
        ScrollPane anchorPane = loader.load();
        splitPane.getItems().set(1,anchorPane);
        GridPane gridPane = (GridPane)anchorPane.getContent();
        List<House> list = manager.getYue(dateController.getDatefrom(),dateController.getDateto());
        int i = 1;
        for (House e: list
        ) {
            Image image = new Image(e.Pic);
            ImageView imageView = new ImageView(image);
            Label rent = new Label();
            Text Loc = new Text();
            Button button = new Button();
            button.setText("立即入住");
            button.getStyleClass().add("labe-x");
            button.setOnMouseClicked(event -> {
                try {
                    deal(e,user,dateController.getDatefrom(),dateController.getDateto());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            Loc.setText(e.Loca);
            Loc.setWrappingWidth(120);
            Loc.setFont(Font.font("system",15));
            Loc.setFill(Color.WHITE);
            rent.setText(e.Size+"m²"+"\n"+"￥"+e.rent);
            rent.getStyleClass().add("label-bright");
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            gridPane.add(imageView,0,i);
            gridPane.add(rent,1,i);
            gridPane.add(Loc,2,i);
            gridPane.add(button,3,i);
            i++;
        }
    }
    @FXML
    public void Shu() throws IOException {
        FXMLLoader loader1 = new FXMLLoader(Main.class.getResource("/Timepicker.fxml"));
        AnchorPane pane = loader1.load();
        DateController dateController = loader1.getController();
        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setScene(scene);
        dateController.init(stage);
        stage.show();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/ping.fxml"));
        ScrollPane anchorPane = loader.load();
        splitPane.getItems().set(1,anchorPane);
        GridPane gridPane = (GridPane)anchorPane.getContent();
        List<House> list = manager.getShu(dateController.getDatefrom(),dateController.getDateto());
        int i = 1;
        for (House e: list
        ) {
            Image image = new Image(e.Pic);
            ImageView imageView = new ImageView(image);
            Label rent = new Label();
            Text Loc = new Text();
            Button button = new Button();
            button.setText("立即入住");
            button.getStyleClass().add("labe-x");
            button.setOnMouseClicked(event -> {
                try {
                    deal(e,user,dateController.getDatefrom(),dateController.getDateto());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            Loc.setText(e.Loca);
            Loc.setWrappingWidth(120);
            Loc.setFont(Font.font("system",15));
            Loc.setFill(Color.WHITE);
            rent.setText(e.Size+"m²"+"\n"+"￥"+e.rent);
            rent.getStyleClass().add("label-bright");
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            gridPane.add(imageView,0,i);
            gridPane.add(rent,1,i);
            gridPane.add(Loc,2,i);
            gridPane.add(button,3,i);
            i++;
        }
    }


}
