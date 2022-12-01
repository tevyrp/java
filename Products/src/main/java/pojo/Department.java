package pojo;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Department {
    private int idDepartment;
    private String nameDepartment;
    private String openingHours;

    public Department(int idDepartment, String nameDepartment, String openingHours) {
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
        this.openingHours = openingHours;
    }

    public int getIdDepartment() {
        return idDepartment;
    }
    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }
    public String getNameDepartment() {
        return nameDepartment;
    }
    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }
    public String getOpeningHours() {
        return openingHours;
    }
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public static Department findDepartment(ObservableList<Department> searchList, int id) {
        ArrayList<Department> findList = new ArrayList<>();
        for(Department item : searchList){
            if(item.getIdDepartment() == id){
                findList.add(item);
            }
        }
        return findList.get(0);
    }
}
