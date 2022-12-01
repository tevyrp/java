package pojo;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Product {
    private int idProduct;
    private int idDepartment;
    private String nameProduct;
    private Double priceProduct;

    public Product(int idProduct, int idDepartment, String nameProduct, Double priceProduct) {
        this.idProduct = idProduct;
        this.idDepartment = idDepartment;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }
    public int getIdDepartment() {
        return idDepartment;
    }
    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }
    public String getNameProduct() {
        return nameProduct;
    }
    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
    public Double getPriceProduct() {
        return priceProduct;
    }
    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public static Product findProduct(ObservableList<Product> searchList, int id) {
        ArrayList<Product> findList = new ArrayList<>();
        for(Product item : searchList){
            if(item.getIdProduct() == id){
                findList.add(item);
            }
        }
        return findList.get(0);
    }
}
