package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.xssf.usermodel.*;
import pojo.Department;
import pojo.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelStatements {

    public static final ObservableList<Product> productsList = FXCollections.observableArrayList();
    public static final ObservableList<Department> departmentsList = FXCollections.observableArrayList();

    public static void getProducts(){
        productsList.clear();
        XSSFSheet productsSheet = getSheet("database.xlsx", "Products");
        for (int i = 1; i <= productsSheet.getLastRowNum(); i++){
            productsList.add(new Product((int)productsSheet.getRow(i).getCell(0).getNumericCellValue(),
                    (int)productsSheet.getRow(i).getCell(1).getNumericCellValue(),
                    productsSheet.getRow(i).getCell(2).toString(),
                    productsSheet.getRow(i).getCell(3).getNumericCellValue()));
        }
    }

    public static void getDepartments(){
        departmentsList.clear();
        XSSFSheet departmentsSheet = getSheet("database.xlsx", "Departments");
        for (int i = 1; i <= departmentsSheet.getLastRowNum(); i++){
            departmentsList.add(new Department((int)departmentsSheet.getRow(i).getCell(0).getNumericCellValue(),
                    departmentsSheet.getRow(i).getCell(1).toString(),
                    departmentsSheet.getRow(i).getCell(2).toString()));
        }
    }

    public static void addNewProduct(){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook excelWorkBook;
        try {
            excelWorkBook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet productsSheet = excelWorkBook.getSheet("Products");
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFRow newRow = productsSheet.createRow(productsSheet.getLastRowNum() + 1);

        XSSFCell newProductId = newRow.createCell(0);
        XSSFCell newDepartmentId = newRow.createCell(1);
        XSSFCell newProductName = newRow.createCell(2);
        XSSFCell newProductPrice = newRow.createCell(3);

        newProductId.setCellValue(productsList.get(productsList.size() - 1).getIdProduct());
        newDepartmentId.setCellValue(productsList.get(productsList.size() - 1).getIdDepartment());
        newProductName.setCellValue(productsList.get(productsList.size() - 1).getNameProduct());
        newProductPrice.setCellValue(productsList.get(productsList.size() - 1).getPriceProduct());

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            excelWorkBook.write(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addNewDepartment(){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook excelWorkBook;
        try {
            excelWorkBook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet productsSheet = excelWorkBook.getSheet("Departments");
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFRow newRow = productsSheet.createRow(productsSheet.getLastRowNum() + 1);

        XSSFCell newDepartmentId = newRow.createCell(0);
        XSSFCell newDepartmentName = newRow.createCell(1);
        XSSFCell newDepartmentOpeningHours = newRow.createCell(2);

        newDepartmentId.setCellValue(departmentsList.get(departmentsList.size() - 1).getIdDepartment());
        newDepartmentName.setCellValue(departmentsList.get(departmentsList.size() - 1).getNameDepartment());
        newDepartmentOpeningHours.setCellValue(departmentsList.get(departmentsList.size() - 1).getOpeningHours());

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            excelWorkBook.write(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editProduct(int itemIndex){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook excelWorkBook;
        try {
            excelWorkBook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet productsSheet = excelWorkBook.getSheet("Products");
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFRow selectedRow = productsSheet.getRow(itemIndex + 1);

        XSSFCell newProductId = selectedRow.createCell(0);
        XSSFCell newDepartmentId = selectedRow.createCell(1);
        XSSFCell newProductName = selectedRow.createCell(2);
        XSSFCell newProductPrice = selectedRow.createCell(3);

        newProductId.setCellValue(productsList.get(itemIndex).getIdProduct());
        newDepartmentId.setCellValue(productsList.get(itemIndex).getIdDepartment());
        newProductName.setCellValue(productsList.get(itemIndex).getNameProduct());
        newProductPrice.setCellValue(productsList.get(itemIndex).getPriceProduct());

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            excelWorkBook.write(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void editDepartment(int itemIndex){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook excelWorkBook;
        try {
            excelWorkBook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet productsSheet = excelWorkBook.getSheet("Departments");
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFRow selectedRow = productsSheet.getRow(itemIndex + 1);

        XSSFCell newDepartmentId = selectedRow.createCell(0);
        XSSFCell newDepartmentName = selectedRow.createCell(1);
        XSSFCell newOpeningHours = selectedRow.createCell(2);

        newDepartmentId.setCellValue(departmentsList.get(itemIndex).getIdDepartment());
        newDepartmentName.setCellValue(departmentsList.get(itemIndex).getNameDepartment());
        newOpeningHours.setCellValue(departmentsList.get(itemIndex).getOpeningHours());

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            excelWorkBook.write(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteProduct(int itemIndex){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook excelWorkBook;
        try {
            excelWorkBook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet productsSheet = excelWorkBook.getSheet("Products");
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFRow selectedRow = productsSheet.getRow(itemIndex + 1);
        productsSheet.removeRow(selectedRow);
        productsSheet.shiftRows(itemIndex + 2, productsList.size() + 1, -1);

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            excelWorkBook.write(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteDepartment(int itemIndex){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook excelWorkBook;
        try {
            excelWorkBook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet departmensSheet = excelWorkBook.getSheet("Departments");
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        XSSFRow selectedRow = departmensSheet.getRow(itemIndex + 1);
        departmensSheet.removeRow(selectedRow);
        departmensSheet.shiftRows(itemIndex + 2, departmentsList.size() + 1, -1);

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("database.xlsx");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            excelWorkBook.write(fileOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static XSSFSheet getSheet(String path, String sheetName){
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook excelWorkBook;
        try {
            excelWorkBook = new XSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        XSSFSheet currentSheet = excelWorkBook.getSheet(sheetName);
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return currentSheet;
    }
}
