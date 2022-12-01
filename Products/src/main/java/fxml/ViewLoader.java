package fxml;

import controllers.ItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pojo.Department;
import pojo.Product;

import java.io.IOException;

public class ViewLoader {

    private static double xOffset;
    private static double yOffset;

    public static void loadSelectedDepartmentWindow(MouseEvent mouseEvent, Department selectedDepartment){
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2) {
                if (selectedDepartment != null) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(ViewLoader.class.getResource("/views/itemWindow.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage newStage = new Stage();
                    Parent rootNewWindow = loader.getRoot();
                    Scene newScene = new Scene(rootNewWindow, Color.TRANSPARENT);
                    newScene.setOnMousePressed(event -> {
                        xOffset = newStage.getX() - event.getScreenX();
                        yOffset = newStage.getY() - event.getScreenY();
                    });
                    newScene.setOnMouseDragged(event -> {
                        newStage.setX(event.getScreenX() + xOffset);
                        newStage.setY(event.getScreenY() + yOffset);
                    });
                    newStage.setScene(newScene);
                    newStage.initStyle(StageStyle.TRANSPARENT);
                    newStage.setResizable(false);
                    newStage.getIcons().add(new Image("/img/icon.png"));
                    newStage.setTitle("Отдел: " + selectedDepartment.getNameDepartment());
                    ItemController selectedItemController = loader.getController();
                    selectedItemController.setDepartmentData(selectedDepartment.getIdDepartment(),
                            selectedDepartment.getNameDepartment(), selectedDepartment.getOpeningHours());
                    newStage.showAndWait();
                }
            }
        }
    }

    public static void loadSelectedProductWindow(MouseEvent mouseEvent, Product selectedProduct){
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2) {
                if (selectedProduct != null) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(ViewLoader.class.getResource("/views/itemWindow.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Stage newStage = new Stage();
                    Parent rootNewWindow = loader.getRoot();
                    Scene newScene = new Scene(rootNewWindow, Color.TRANSPARENT);
                    newScene.setOnMousePressed(event -> {
                        xOffset = newStage.getX() - event.getScreenX();
                        yOffset = newStage.getY() - event.getScreenY();
                    });
                    newScene.setOnMouseDragged(event -> {
                        newStage.setX(event.getScreenX() + xOffset);
                        newStage.setY(event.getScreenY() + yOffset);
                    });
                    newStage.setScene(newScene);
                    newStage.initStyle(StageStyle.TRANSPARENT);
                    newStage.setResizable(false);
                    newStage.getIcons().add(new Image("/img/icon.png"));
                    newStage.setTitle("Продукт: " + selectedProduct.getNameProduct());
                    ItemController selectedItemController = loader.getController();
                    selectedItemController.setProductData(selectedProduct.getIdProduct(),
                            selectedProduct.getIdDepartment(), selectedProduct.getNameProduct(),
                            selectedProduct.getPriceProduct());
                    newStage.showAndWait();
                }
            }
        }
    }

    public static void loadAddWindow(String fxmlPath, String title, int id){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewLoader.class.getResource(fxmlPath));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage newStage = new Stage();
        Parent rootNewWindow = loader.getRoot();
        Scene newScene = new Scene(rootNewWindow, Color.TRANSPARENT);
        newScene.setOnMousePressed(event -> {
            xOffset = newStage.getX() - event.getScreenX();
            yOffset = newStage.getY() - event.getScreenY();
        });
        newScene.setOnMouseDragged(event -> {
            newStage.setX(event.getScreenX() + xOffset);
            newStage.setY(event.getScreenY() + yOffset);
        });
        newStage.setScene(newScene);
        newStage.setTitle(title);
        newStage.initStyle(StageStyle.TRANSPARENT);
        newStage.setResizable(false);
        newStage.getIcons().add(new Image("/img/icon.png"));
        newStage.setTitle(title);
        ItemController selectedItemController = loader.getController();
        selectedItemController.setWindowSettingToAddData(id);
        newStage.showAndWait();
    }
}
