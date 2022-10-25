package Model;

import API.API_Service;
import Controller.LoginController;
import Model.Entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.util.ArrayList;

//This class is the model of user login
public class UserLoginModel {
    private static String username = "";

    API_Service api_service = new API_Service();
    HttpResponse<String> response;
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserLoginModel.username = username;
    }

    public void LoginVerify(String jsonString) throws IOException, InterruptedException, ParseException {

        LoginController loginController =new LoginController();

        response = api_service.Login(jsonString);//call the API service
        ObjectNode jsonNode = new ObjectMapper().readValue(response.body(), ObjectNode.class);
        //If success, it will jump to the next line of code
        try{jsonString = "{\"jwt\":\"" + jsonNode.get("jwt").textValue() + "\"}";}
        //if error, it will return to the Login choice page
        catch(Exception e){
            loginController.UpdateLoginView();

        }
        // Note the POST() method being used here, and the request body is supplied to it.
        // A request body needs to be supplied to this endpoint, otherwise a 400 Bad Request error will be returned.
        response = api_service.Verify(jsonString);
        if(response.body().length()==0){
            loginController.SuccessLoginView();

        }

    }
    public void UserInformation(String username) throws IOException, InterruptedException {

        String Id = "";
        String IsRecep ="";
        String IsCustomer ="";
        String IsHealth = "";
        String PhoneNumber = "";
        HttpResponse<String> response = api_service.getuser();
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        // This process will use the for loop to find if there is any userName so that identify if they are registered
        for (int i = 0; i < jsonNodes.length; i++) {
            String getName = jsonNodes[i].findValue("userName").toString().toLowerCase();

            if (getName.equals(username.toLowerCase())){
                Id = jsonNodes[i].findValue("id").toString();
                IsRecep= jsonNodes[i].findValue("isReceptionist").toString();
                IsCustomer = jsonNodes[i].findValue("isCustomer").toString();
                IsHealth = jsonNodes[i].findValue("isHealthcareWorker").toString();
                PhoneNumber = jsonNodes[i].findValue("phoneNumber").toString();

            }
        }
        Users user = new Users();
        user.setId(Id);
        user.setIsCustomer(IsCustomer);
        user.setIsHealth(IsHealth);
        user.setIsRecep(IsRecep);
        user.setName(username);
        user.setPhoneNumber(PhoneNumber);
        response = api_service.getBooking();
        jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        ArrayList<String> UserInfor = new ArrayList<>();
        // This process will use the for loop to find if there is any userName so that identify if they are registered
        for (int i = 0; i < jsonNodes.length; i++) {
            String getName = jsonNodes[i].findValue("userName").toString().toLowerCase();

            if (getName.equals(username.toLowerCase())){
                UserInfor.add(jsonNodes[i].toString());

            }
        }
        user.setUserInfo(UserInfor);





    }
}
