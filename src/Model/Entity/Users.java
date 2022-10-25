package Model.Entity;

import java.util.ArrayList;

//This class is about all users
public class Users {

    //Declaring variables of user
    private  static String Name;
    private static String Id;
    private static String PhoneNumber;

    //declaring three type of user
    private static  String IsCustomer;
    private static String IsHealth;
    private static String IsRecep;
    private static ArrayList<String> userInfo;

    public static ArrayList<String> getUserInfo() {
        return userInfo;
    }

    public static void setUserInfo(ArrayList<String> userInfo) {
        Users.userInfo = userInfo;
    }

    //username
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    //user ID
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
    //user phone number
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
    //as a customer
    public String getIsCustomer() {
        return IsCustomer;
    }

    public void setIsCustomer(String isCustomer) {
        IsCustomer = isCustomer;
    }
    //as a healthcare worker
    public String getIsHealth() {
        return IsHealth;
    }

    public void setIsHealth(String isHealth) {
        IsHealth = isHealth;
    }
    //as a receptionist
    public String getIsRecep() {
        return IsRecep;
    }

    public void setIsRecep(String isRecep) {
        IsRecep = isRecep;
    }



}
