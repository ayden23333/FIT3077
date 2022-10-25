package Model.TestingModel;

import API.API_Service;
import Controller.LoginController;
import Controller.TestingController.TestingController;
import View.ServiceDecideView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.ParseException;
//This class is the model about testing by implementing the testing interface
public class TestingModel implements TestingInt{
    //declare and initialize variables
    private  HttpResponse<String> response;
    API_Service api_service =new API_Service();
    private static String bookingid = "";
    private static String Patientid="";
    private static String Notes = "";
    private static String AdminId ="";
    private String result="INITIATED";
    private String status="CREATED";
    private static String TypeSelect ="";

    //checking if booked
    public void Checking(String Familyname, String GivenName,String notes, String Sym) throws IOException, InterruptedException, ParseException {
        boolean flag = false;
        Notes = notes;
        TypeSelect = Sym;
        TestingController testingController = new TestingController();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        //get the response by calling the API service
        response = api_service.getBooking();
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        //use the loop to determine if the user book before and get their booking id
        for (int i = 0; i < jsonNodes.length; i++) {
            String getName = jsonNodes[i].findValue("familyName").toString().toLowerCase();
            String getGivenName = jsonNodes[i].findValue("givenName").toString().toLowerCase();
            // This process will use the for loop to find if there is any userName so that identify if they are registered
            if (getName.equals(Familyname.toLowerCase())&&getGivenName.equals(GivenName.toLowerCase())) {
                bookingid = jsonNodes[i].findValue("id").toString();
                flag = true;
                break;
            }
            //if there is no information then will return to the service decide page
            else if (i == jsonNodes.length - 1 && flag == false) {
                testingController.Failbooking();
                serviceDecideView.ServiceDecide();}
    }
        //calling the API method to get the user information.
        response  = api_service.getuser();
        jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        // This process will use the for loop to find if there is any userName so that identify if they are registered
        for (int i = 0; i < jsonNodes.length; i++) {
            String getName = jsonNodes[i].findValue("familyName").toString().toLowerCase();
            String getGivenName = jsonNodes[i].findValue("givenName").toString().toLowerCase();
            if (getName.equals(Familyname.toLowerCase())&&getGivenName.equals(GivenName.toLowerCase())) {
                Patientid = jsonNodes[i].findValue("id").toString();
                flag = true;
                break;
            } else if (i == jsonNodes.length - 1 && flag == false) {
               testingController.FailId();
            }
        }

        LoginController loginController = new LoginController();
        loginController.getUsername();
        String AdminName = "\""+loginController.getUsername()+"\"";
        jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        // This process will use the for loop to find if there is any userName so that find the id of the admin
        for (int i = 0; i < jsonNodes.length; i++) {
            String getName = jsonNodes[i].findValue("userName").toString().toLowerCase();
            if (getName.equals(AdminName)) {
                AdminId = jsonNodes[i].findValue("id").toString();
            }
        }
        testing();
    }
    //implement method in testing interface and override
    public void testing() throws IOException, InterruptedException, ParseException {
        TestingController testingController = new TestingController();
        testingController.TestingStart();
        String jsonString= "";
        jsonString =
                "{" + "\"type\":" + TypeSelect + "," +
                        "\"patientId\":" + Patientid + "," +
                        "\"administererId\":"  + AdminId + "," +
                        "\"bookingId\":"  + bookingid  + "," +
                        "\"result\": "+ "\"" +  result +"\"" +  "," +
                        "\"status\":" + "\"" + status + "\"" + "," +
                        "\"notes\":"  + Notes +  "," +
                        "\"additionalInfo\":" + "{" + "" + "}" +
                        "}";
        //calling the API service
        response  = api_service.postTesting(jsonString);
        //Not found ID warning
        if(response.statusCode()==404){
            testingController.TestingFail();

        }
        //success testing conduct
        else if(response.statusCode()==201){
            testingController.TestingSuccess(response.body());

        }
    }
}

