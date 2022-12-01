import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {
    private double xOffset;
    private double yOffset;
    @Override
    public void start(final Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects
                .requireNonNull(getClass()
                        .getResource("views/mainWindow.fxml")
                )
        );
        Scene scene = new Scene(root, Color.TRANSPARENT);
        scene.setOnMousePressed(event -> {
            xOffset = primaryStage.getX() - event.getScreenX();
            yOffset = primaryStage.getY() - event.getScreenY();
        });
        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() + xOffset);
            primaryStage.setY(event.getScreenY() + yOffset);
        });
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(true);
        primaryStage.getIcons().add(new Image("/img/icon.png"));
        primaryStage.setTitle("Товары в магазине");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}
