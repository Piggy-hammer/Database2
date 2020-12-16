package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class HolderController {
    Manager manager;
    String user;
    @FXML
    private GridPane gridPane;

    public void init(Manager manager, String user) throws IOException {
        this.manager = manager;
        this.user = user;
        List<House> list = manager.getHolder(user);
        int i = 1;
        for (House e : list
        ) {
            Image image = new Image(e.Pic);
            ImageView imageView = new ImageView(image);
            Label rent = new Label();
            Text Loc = new Text();
            Button button = new Button();
            button.setText("删除房屋");
            button.getStyleClass().add("labe-x");
            button.setOnMouseClicked(event -> {
                manager.delete(e.Loca, user);
            });
            Loc.setText(e.Loca);
            Loc.setWrappingWidth(120);
            Loc.setFont(Font.font("system", 15));
            Loc.setFill(Color.WHITE);
            rent.setText(e.Size+"m²"+"\n"+"￥"+e.rent);
            rent.getStyleClass().add("label-bright");
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            gridPane.add(imageView, 0, i);
            gridPane.add(rent, 1, i);
            gridPane.add(Loc, 2, i);
            gridPane.add(button, 3, i);
            i++;
        }
    }

    @FXML
    public void addnew() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/add.fxml"));
        AnchorPane anchorPane = loader.load();
        AddController addController = loader.getController();
        Stage stage = new Stage();
        addController.init(manager,stage,user,this);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/1.png")));
        Scene scene = new Scene(anchorPane);
        stage.setTitle("添加您的新房产");
        stage.setScene(scene);
        stage.show();
    }

}
