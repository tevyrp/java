package controllers;

import data.ExcelStatements;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pojo.Department;
import pojo.Product;

public class ItemController {

    @FXML
    private Button buttonClearAllFields;
    @FXML
    private Button buttonDeleteItem;
    @FXML
    private Button buttonItemAdd;
    @FXML
    private Button buttonEditItem;

    @FXML
    private GridPane gridPaneDepartment;
    @FXML
    private GridPane gridPaneProduct;

    @FXML
    private Label labelMessage;

    @FXML
    private Text labelTitle;

    @FXML
    private Pane paneCloseWindow;
    @FXML
    private Pane paneCollapseWindow;

    @FXML
    private TextField textFieldPriceProduct;
    @FXML
    private TextField textFieldNameDepartment;
    @FXML
    private TextField textFieldNameProduct;
    @FXML
    private TextField textFieldOpeningHours;
    @FXML
    private TextField textFieldDepartmentId;
    @FXML
    private TextField textFieldDepartmentIdProduct;
    @FXML
    private TextField textFieldProductId;

    private boolean isEditWindow = false;
    private boolean isProductItem = false;
    private boolean isDepartmentItem = false;

    @FXML
    void initialize(){

        controlPanesHandler();
        buttonsHandler();
        textFieldHandler();

    }

    @FXML
    public void setWindowSettingToAddData(int id){
        switch (id) {
            case 1 -> {
                labelTitle.setText("Добавление нового продукта");
                setVisibleElements(gridPaneProduct);
                isProductItem = true;
            }
            case 2 -> {
                labelTitle.setText("Добавление нового отдела");
                setVisibleElements(gridPaneDepartment);
                isDepartmentItem = true;
            }
        }
    }

    @FXML
    public void setProductData(int idProduct, int idDepartment, String nameProduct, double priceProduct){
        isEditWindow = true;
        isProductItem = true;
        setVisibleElements(gridPaneProduct);

        labelTitle.setText(nameProduct);
        textFieldProductId.setText(String.valueOf(idProduct));
        textFieldDepartmentIdProduct.setText(String.valueOf(idDepartment));
        textFieldNameProduct.setText(nameProduct);
        textFieldPriceProduct.setText(String.valueOf(priceProduct));
    }

    @FXML
    public void setDepartmentData(int idDepartment, String nameDepartment, String openingHours){
        isEditWindow = true;
        isDepartmentItem = true;
        setVisibleElements(gridPaneDepartment);

        labelTitle.setText(nameDepartment);
        textFieldDepartmentId.setText(String.valueOf(idDepartment));
        textFieldNameDepartment.setText(nameDepartment);
        textFieldOpeningHours.setText(openingHours);
    }

    void textFieldHandler(){
        // Добавляем слушатель, который срабатывает при изменении текстового поля "Название"
        textFieldPriceProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[.\\d]+")) {
                textFieldPriceProduct.setText(newValue.replaceAll("[^.\\d]+", ""));
            }
            labelMessage.setText("");
        });
        textFieldProductId.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d+")) {
                textFieldProductId.setText(newValue.replaceAll("\\D+", ""));
            }
            labelMessage.setText("");
        });
        textFieldDepartmentId.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d+")) {
                textFieldDepartmentId.setText(newValue.replaceAll("\\D+", ""));
            }
            labelMessage.setText("");
        });
        textFieldDepartmentIdProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d+")) {
                textFieldDepartmentIdProduct.setText(newValue.replaceAll("\\D+", ""));
            }
            labelMessage.setText("");
        });
        textFieldOpeningHours.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("[- :\\d]+")) {
                textFieldOpeningHours.setText(newValue.replaceAll("[^- :\\d]+", ""));
            }
            labelMessage.setText("");
        });

        textFieldNameDepartment.textProperty().addListener((observable, oldValue, newValue)
                -> labelMessage.setText(""));
        textFieldNameProduct.textProperty().addListener((observable, oldValue, newValue)
                -> labelMessage.setText(""));
    }

    @FXML
    void setVisibleElements(GridPane selectedGridPane){
        selectedGridPane.setVisible(true);
        if (isEditWindow){
            buttonDeleteItem.setVisible(true);
            buttonDeleteItem.setDisable(false);
            buttonEditItem.setVisible(true);
            buttonEditItem.setDisable(false);
        }
        else {
            buttonItemAdd.setVisible(true);
            buttonItemAdd.setDisable(false);
        }
    }

    @FXML
    void buttonsHandler(){
        buttonClearAllFields.setOnAction(actionEvent -> {
            textFieldNameDepartment.clear();
            textFieldOpeningHours.clear();
            textFieldNameProduct.clear();
            textFieldPriceProduct.clear();
            textFieldDepartmentId.clear();
            textFieldProductId.clear();
            textFieldDepartmentIdProduct.clear();
        });

        buttonItemAdd.setOnAction(actionEvent -> {
            if(isProductItem){
                ExcelStatements.productsList.add(new Product(Integer.parseInt(textFieldProductId.getText()),
                        Integer.parseInt(textFieldDepartmentIdProduct.getText()), textFieldNameProduct.getText(),
                        Double.parseDouble(textFieldPriceProduct.getText())));
                ExcelStatements.addNewProduct();
                labelMessage.setTextFill(Color.GREEN);
                labelMessage.setText("Добавление успешно выполнено.");
            }
            if(isDepartmentItem){
                ExcelStatements.departmentsList.add(new Department(Integer.parseInt(textFieldDepartmentId.getText()),
                        textFieldNameDepartment.getText(), textFieldOpeningHours.getText()));
                ExcelStatements.addNewDepartment();
                labelMessage.setTextFill(Color.GREEN);
                labelMessage.setText("Добавление успешно выполнено.");
            }
        });

        buttonEditItem.setOnAction(actionEvent -> {
            if(isProductItem){
                Product selectedProduct = Product.findProduct(ExcelStatements.productsList,
                        Integer.parseInt(textFieldProductId.getText()));

                selectedProduct.setIdProduct(Integer.parseInt(textFieldProductId.getText()));
                selectedProduct.setIdDepartment(Integer.parseInt(textFieldDepartmentIdProduct.getText()));
                selectedProduct.setNameProduct(textFieldNameProduct.getText());
                selectedProduct.setPriceProduct(Double.parseDouble(textFieldPriceProduct.getText()));

                ExcelStatements.editProduct(ExcelStatements.productsList.indexOf(selectedProduct));
                labelMessage.setTextFill(Color.GREEN);
                labelMessage.setText("Изменение успешно выполнено.");
            }
            if(isDepartmentItem){
                Department selectedDepartment = Department.findDepartment(ExcelStatements.departmentsList,
                        Integer.parseInt(textFieldDepartmentId.getText()));

                selectedDepartment.setIdDepartment(Integer.parseInt(textFieldDepartmentId.getText()));
                selectedDepartment.setNameDepartment(textFieldNameDepartment.getText());
                selectedDepartment.setOpeningHours(textFieldOpeningHours.getText());

                ExcelStatements.editDepartment(ExcelStatements.departmentsList.indexOf(selectedDepartment));
                labelMessage.setTextFill(Color.GREEN);
                labelMessage.setText("Изменение успешно выполнено.");
            }
        });
        buttonDeleteItem.setOnAction(actionEvent -> {
            if(isProductItem){
                Product selectedProduct = Product.findProduct(ExcelStatements.productsList,
                        Integer.parseInt(textFieldProductId.getText()));
                ExcelStatements.editProduct(ExcelStatements.productsList.indexOf(selectedProduct));
                labelMessage.setTextFill(Color.GREEN);
                labelMessage.setText("Удаление успешно выполнено.");
            }
            if(isDepartmentItem){
                Department selectedDepartment = Department.findDepartment(ExcelStatements.departmentsList,
                        Integer.parseInt(textFieldDepartmentId.getText()));
                ExcelStatements.deleteDepartment(ExcelStatements.departmentsList.indexOf(selectedDepartment));
                labelMessage.setTextFill(Color.GREEN);
                labelMessage.setText("Удаление успешно выполнено.");
            }
        });
    }

    @FXML
    void controlPanesHandler(){
        paneCloseWindow.setOnMouseClicked(event ->{
            Stage currentStage = (Stage) paneCloseWindow.getScene().getWindow(); // Получаем сцену, на которой находится нажатая панель
            currentStage.close();
        });
        paneCollapseWindow.setOnMouseClicked(event ->{
            Stage currentStage = (Stage) paneCollapseWindow.getScene().getWindow(); // Получаем сцену, на которой находится нажатая панель
            currentStage.setIconified(true);
        });
    }
}
