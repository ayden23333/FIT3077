package Model.TestingModel;

import API.API_Service;
import Controller.LoginController;
import Controller.TestingController.HomeTestingController;
import View.ServiceDecideView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.ParseException;
//This class is the model about home testing by implementing the testing interface
public class HomeTestingModel implements TestingInt{
    //declare and initialize variables
    private static String tool;
    private String TestingType="";
    private HttpResponse<String> response;
    API_Service api_service =new API_Service();
    private static String bookingid = "";
    private static String Patientid="";
    private static String Notes = "";
    private static String AdminId ="";
    private String result="INITIATED";
    private String status="CREATED";

        public void homeTesting(String in) throws InterruptedException, ParseException, IOException {
            //select 1. user has a test tool, it will show the status and testing type
            HomeTestingController homeTestingController = new HomeTestingController();
            if(in.equals("1")) {
                Notes = "\""+"Have test tool"+"\"";
                TestingType = "HomeTesting";

            }
            //Select 2. user has no test tool, it will show the status and testing type
            else if(in.equals("2")){
                Notes = "\""+"Don't have test tool"+"\"";
                TestingType = "HomeTesting";
            }
            //if user type anything wrong or an invalid number, it will show an error and back to previous page
            else{
               homeTestingController.BacktoService();

        }

        }
    //checking if booked
    public void Checking(String Familyname, String GivenName) throws IOException, InterruptedException, ParseException {
        boolean flag = false;
        HomeTestingController testingController = new HomeTestingController();
        //get the response by calling the API service
        response = api_service.getBooking();
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        //use for loop to determine if the user book before and get their booking id
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
               }
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

        for (int i = 0; i < jsonNodes.length; i++) {
            String getName = jsonNodes[i].findValue("userName").toString().toLowerCase();
            // This process will use the for loop to find if there is any userName so that find the id of the admin
            if (getName.equals(AdminName)) {
                AdminId = jsonNodes[i].findValue("id").toString();
            }

        }
        testing();
}
    //implement method in testing interface and override
    public void testing() throws IOException, InterruptedException, ParseException {
        String jsonString= "";
        HomeTestingController homeTestingController = new HomeTestingController();
        //generate the random QR code;
        String QRcode ="Here is your QR code:" + (int)(Math.random()*9000+1000);
        String link="https://testing.com.au";
        jsonString =
                "{" + "\"type\":" + "\""+"RAT" +"\""+ "," +
                        "\"patientId\":" + Patientid + "," +
                        "\"administererId\":"  + AdminId + "," +
                        "\"bookingId\":"  + bookingid  + "," +
                        "\"result\": "+ "\"" +  result +"\"" +  "," +
                        "\"status\":" + "\"" + status + "\"" + "," +
                        "\"notes\":"  + Notes +  "," +
                        "\"additionalInfo\":" + " {"+"\"QR\""+":"+
                        "\""+ QRcode + "\"" + ","
                        +"\"link\""+":"+"\""+ link +"\""+"}"+
                        "}";
        //calling the API service
        response  = api_service.postTesting(jsonString);
        //Not found ID warning
        if(response.statusCode()==404){
            homeTestingController.TestingFail();

        }
    //success testing conduct
    else if(response.statusCode()==201){
        homeTestingController.TestingSuccess(response.body());

    }
    }
}


