package Model.BookingModel;

import API.API_Service;
import Controller.ProfileController.AdminController;
import Model.Entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.ParseException;
//This class is the model for admin by implementing the booking interface
public class AdminModel implements BookingInt {
    //declare variables
    private static String Choice;
    private static String Id;
    //set id
    public static void setId(String id) {
        Id = id;
    }

    public static void setChoice(String choice) {
        Choice = choice;
    }
    //verify is an admin or not
    public void verify() throws IOException, InterruptedException, ParseException {
        Users users = new Users();
        AdminController adminController = new AdminController();
        //if the user is a receptionist or a healthcare worker, the user is an admin
        if(users.getIsRecep().equals("true")||users.getIsHealth().equals("true")){

            adminController.verifySuccess();
        }
        //if not a receptionist or a healthcare worker, the user is not an admin
        else{
            adminController.verifyFail();
        }
    }


    public void Initial() throws IOException, InterruptedException, ParseException {
        API_Service api_service = new API_Service();
        AdminController adminController = new AdminController();
        HttpResponse<String> response = api_service.getBooking();
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        for (int i = 0; i < jsonNodes.length; i++) {
            //get pin code and booking information, convert to string and lowercase
            String Information = jsonNodes[i].toString();
           String status = jsonNodes[i].findValue("status").toString().toLowerCase();
           if(status.equals("\""+"changed"+"\"")){
               adminController.Changed(i+" "+Information);
           }
           else if(status.equals("\""+"cancel"+"\"")){
               adminController.Cancel(i+" "+Information);
           }
           else if(status.equals("\""+"initiated"+"\"")){
               adminController.INITIAL(i+" "+Information);
           }

        }
        adminController.nextStep();
    }
    //implement method in booking interface and override
    public void booking() throws IOException, InterruptedException, ParseException {
        API_Service api_service = new API_Service();
        AdminController adminController = new AdminController();
        //1. delete
        if(Choice.equals("1")){

            adminController.ReturnChoiceToView();
            HttpResponse<String> response = api_service.DeleteBooking(Id);
            //test has done before
            if(response.statusCode()==409){
                adminController.testDoneBefore();
            }
            //delete successfully
            else if(response.statusCode()==204){
                adminController.DeleteSuccess();
            }
            //failed with invalid ID
            else{
                adminController.InvaildId();
            }


        }
        //2. cancel
        else if(Choice.equals("2")){
            adminController.ReturnChoiceToView();
            String jsonString = "{" +"\"status\":" + "\""+ "CANCEL" + "\"" +
                    "}";

            HttpResponse<String> response = api_service.PatchBooking(jsonString,Id);
            //cancel successful
            if(response.statusCode()==200){
                adminController.CancelSuccess();
            }
            //failed with invalid ID
            else{
                adminController.InvaildId();
            }


        }
        //3. back to service decide
        else if(Choice.equals("3")){
            adminController.Choice3();
        }
        //refresh the page
        else if(Choice.equals("4")){
            Initial();
        }
        else{
            Initial();
        }
    }
}
