package Model.VerifyModel;
import Model.Entity.Users;

//This class is the model about verify user identity by implementing the verify interface
public class VerifyModel implements VerifyModelInt {
    //declare variable
    private static String isRece="";
    private static String isHealth = "";
    //receptionist
    public String getIsRece() {
        return isRece;
    }
    //healthcare worker
    public static String getIsHealth() {
        return isHealth;
    }
    //implement method in verify interface and override
    public void Verify(){
        Users users = new Users();
        isRece= users.getIsRecep();
        isHealth = users.getIsHealth();

    }
}

