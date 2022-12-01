package controllers;

import data.ExcelStatements;

import fxml.ViewLoader;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import pojo.Department;
import pojo.Product;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private final StringBuilder stringBuilder = new StringBuilder();

    @FXML
    private Pane productsPane;
    @FXML
    private Pane departmentsPane;
    @FXML
    private Pane requestsPane;


    @FXML
    private Pane paneCloseWindow;
    @FXML
    private Pane paneCollapseWindow;


    @FXML
    private Button buttonProducts;
    @FXML
    private Button buttonDepartments;
    @FXML
    private Button buttonRequests;


    @FXML
    private Button buttonToWindowAddNewProduct;
    @FXML
    private Button buttonToWindowAddNewDepartments;


    @FXML
    private TableView<Department> tableViewDepartments;
    @FXML
    private TableColumn<Department, Integer> idDepartmentColumn;
    @FXML
    private TableColumn<Department, String> nameDepartmentColumn;
    @FXML
    private TableColumn<Department, String> openingHoursColumn;


    @FXML
    private TableView<Product> tableViewProducts;
    @FXML
    private TableColumn<Product, Integer> idProductColumn;
    @FXML
    private TableColumn<Department, Integer> idDepartmentProductColumn;
    @FXML
    private TableColumn<Department, String> nameProductColumn;
    @FXML
    private TableColumn<Product, Double> priceProductColumn;


    @FXML
    private TextArea textAreaRequestResult;


    @FXML
    private ComboBox<String> comboBoxRequests;
    @FXML
    private ComboBox<Integer> comboBoxDepartmentsIds;


    @FXML
    private Label labelDepartment;


    @FXML
    void initialize(){
        mainMenuButtonHandler();
        controlPanesHandler();
        hideAllPanes();
        buttonHandler();
        tableViewHandler();
        comboBoxHandler();
        setDataInComboBoxRequests();
        requestsPane.setVisible(true);
        textAreaRequestResult.setEditable(false);
    }

    @FXML
    void hideAllPanes(){
        productsPane.setVisible(false);
        departmentsPane.setVisible(false);
        requestsPane.setVisible(false);
    }

    @FXML
    void mainMenuButtonHandler(){
        buttonProducts.setOnAction(actionEvent -> {
            hideAllPanes();
            setContentInTableViewProduct();
            productsPane.setVisible(true);
        });
        buttonDepartments.setOnAction(actionEvent -> {
            hideAllPanes();
            setContentInTableViewDepartments();
            departmentsPane.setVisible(true);
        });
        buttonRequests.setOnAction(actionEvent -> {
            hideAllPanes();
            setDataInComboBoxRequests();
            textAreaRequestResult.clear();
            requestsPane.setVisible(true);
        });
    }

    @FXML
    void controlPanesHandler(){
        paneCloseWindow.setOnMouseClicked(event ->{
            Stage currentStage = (Stage) paneCloseWindow.getScene().getWindow(); // Получаем сцену, на которой находится нажатая панель
            currentStage.close();
        });

        paneCollapseWindow.setOnMouseClicked(event ->{
            Stage currentStage = (Stage) paneCollapseWindow.getScene().getWindow();
            currentStage.setIconified(true);
        });
    }

    @FXML
    void buttonHandler(){
        buttonToWindowAddNewProduct.setOnAction(actionEvent ->
                ViewLoader.loadAddWindow("/views/itemWindow.fxml", "Добавление нового продукта", 1));
        buttonToWindowAddNewDepartments.setOnAction(actionEvent ->
                ViewLoader.loadAddWindow("/views/itemWindow.fxml", "Добавление нового отдела", 2));
    }

    @FXML
    void tableViewHandler(){
        tableViewProducts.setOnMouseClicked(mouseEvent -> {
            ViewLoader.loadSelectedProductWindow(mouseEvent, tableViewProducts.getSelectionModel().getSelectedItem());
            tableViewProductsRefresh();
        });
        tableViewDepartments.setOnMouseClicked(mouseEvent -> {
            ViewLoader.loadSelectedDepartmentWindow(mouseEvent, tableViewDepartments.getSelectionModel().getSelectedItem());
            tableViewDepartmentsRefresh();
        });
    }

    @FXML
    public void comboBoxHandler(){
        comboBoxRequests.setOnAction(actionEvent -> {
            if(comboBoxRequests.getValue() != null){
                if(comboBoxRequests.getValue().contains("1")){
                    textAreaRequestResult.clear();
                    labelDepartment.setVisible(true);
                    comboBoxDepartmentsIds.setVisible(true);
                    setDataInComboBoxDepartments();
                }
                if(comboBoxRequests.getValue().contains("2")){
                    textAreaRequestResult.clear();
                    labelDepartment.setVisible(false);
                    comboBoxDepartmentsIds.setVisible(false);
                    ExcelStatements.getProducts();
                    ExcelStatements.getDepartments();

                    boolean flag;

                    for (Department department: ExcelStatements.departmentsList){
                        flag = true;
                        for (Product product: ExcelStatements.productsList){
                            if (product.getIdDepartment() == department.getIdDepartment()) {
                                flag = false;
                                break;
                            }
                        }
                        if(flag) stringBuilder.append("Отдел №").append(department.getIdDepartment());
                    }
                    if (stringBuilder.length() != 0) textAreaRequestResult.setText(stringBuilder.toString());
                    else textAreaRequestResult.setText("Список пуст!");
                    stringBuilder.setLength(0);
                }
                if(comboBoxRequests.getValue().contains("3")){
                    textAreaRequestResult.clear();
                    labelDepartment.setVisible(false);
                    comboBoxDepartmentsIds.setVisible(false);
                    ExcelStatements.getProducts();

                    stringBuilder.append("Весь список товаров в магазине:");
                    stringBuilder.append("\nidProduct  \t  idDepartment  \t  productPrice  \t  nameProduct");
                    for (Product item: ExcelStatements.productsList){
                        stringBuilder.append("\n")
                                .append(item.getIdProduct())
                                .append("\t\t\t\t")
                                .append(item.getIdDepartment())
                                .append("\t\t\t")
                                .append(item.getPriceProduct())
                                .append("\t\t\t\t")
                                .append(item.getNameProduct());
                    }
                    if (stringBuilder.length() != 0) textAreaRequestResult.setText(stringBuilder.toString());
                    else textAreaRequestResult.setText("Список пуст!");
                    stringBuilder.setLength(0);
                }
            }
        });
        comboBoxDepartmentsIds.setOnAction(actionEvent -> {
            textAreaRequestResult.clear();
            ExcelStatements.getProducts();
            int count = 0;
            stringBuilder.append("Товары в отделе № ").append(comboBoxDepartmentsIds.getValue());
            stringBuilder.append("\nidProduct  \t  productPrice  \t  nameProduct");
            for (Product item: ExcelStatements.productsList){
                if(comboBoxDepartmentsIds.getValue() != null){
                    if(item.getIdDepartment() == comboBoxDepartmentsIds.getValue()){
                        stringBuilder.append("\n")
                                .append(item.getIdProduct())
                                .append("\t\t\t\t")
                                .append(item.getPriceProduct())
                                .append("\t\t\t")
                                .append(item.getNameProduct());
                        count++;
                    }
                }
            }
            if (count != 0) textAreaRequestResult.setText(stringBuilder.toString());
            else textAreaRequestResult.setText("Список пуст!");
            stringBuilder.setLength(0);
        });
    }

    private void setContentInTableViewProduct(){
        // Таблица "Продукты"
        ExcelStatements.getProducts();
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
        idDepartmentProductColumn.setCellValueFactory(new PropertyValueFactory<>("idDepartment"));
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
        priceProductColumn.setCellValueFactory(new PropertyValueFactory<>("priceProduct"));
        tableViewProducts.setItems(ExcelStatements.productsList);
    }

    @FXML
    void tableViewProductsRefresh(){
        ExcelStatements.getProducts();
        setContentInTableViewProduct();
    }

    private void setContentInTableViewDepartments(){
        // Таблица "Отделы"
        ExcelStatements.getDepartments();
        idDepartmentColumn.setCellValueFactory(new PropertyValueFactory<>("idDepartment"));
        nameDepartmentColumn.setCellValueFactory(new PropertyValueFactory<>("nameDepartment"));
        openingHoursColumn.setCellValueFactory(new PropertyValueFactory<>("openingHours"));
        tableViewDepartments.setItems(ExcelStatements.departmentsList);
    }

    @FXML
    void tableViewDepartmentsRefresh(){
        ExcelStatements.getDepartments();
        setContentInTableViewDepartments();
    }

    @FXML
    private void setDataInComboBoxRequests() {
        comboBoxRequests.getItems().clear();
        comboBoxRequests.getItems().addAll(
                "1. Выбрать все товары в указанном отделе.",
                "2. Отделы без товаров.",
                "3. Весь список товаров в магазине."
        );
    }
    @FXML
    private void setDataInComboBoxDepartments() {
        List<Integer> departmentsIDs = new ArrayList<>();
        ExcelStatements.getDepartments();
        comboBoxDepartmentsIds.getItems().clear();
        for (Department item: ExcelStatements.departmentsList){
            departmentsIDs.add(item.getIdDepartment());
        }
        comboBoxDepartmentsIds.setItems(FXCollections.observableArrayList(departmentsIDs));
        comboBoxDepartmentsIds.getSelectionModel().select(0);
    }

}
