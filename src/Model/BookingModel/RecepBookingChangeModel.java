package Model.BookingModel;

import API.API_Service;
import Controller.BookingController.RecepController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
//This class is model about receptionist change the booking information by implementing the interface
public class RecepBookingChangeModel implements BookingInt {
    private static String ChangePinOrID;
    API_Service api_service = new API_Service();
    private HttpResponse<String> response;
    private boolean flag = false;
    private static String siteID;

    private static String timestamp;
    private static String Id;
    private static String Id1;
    private static String locationId = "";
    private static String ChangeType;
    private static String originalNotes;
    private static String changeBackNotes;
    private static String ChangeInfo;
    private static String UserId;
    static Date date2;
    //set changing booking information
    public void setChangeInfo(String changeInfo) {
        ChangeInfo = changeInfo;
    }
    //set changing info type
    public static void setChangeType(String changeType) {
        ChangeType = changeType;
    }
    //set changing pin code or ID
    public static void setChangePinOrID(String changePinOrID) {
        ChangePinOrID = changePinOrID;
    }

    public void ChangeInit() throws IOException, InterruptedException, ParseException {
        RecepController recepController = new RecepController();

        response = api_service.getBooking();
        //The request will return the object
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        for (int i = 0; i < jsonNodes.length; i++) {
            //get pin code and booking information, convert to string and lowercase
            String getPin = jsonNodes[i].findValue("smsPin").toString().toLowerCase();
            String getId = jsonNodes[i].findValue("id").toString().toLowerCase();
            //if the pin code exist, it will show the booking information of the user
            if (getPin.equals("\"" + ChangePinOrID + "\"") || getId.equals("\"" + ChangePinOrID + "\"")) {
                locationId = jsonNodes[i].findValue("testingSite").get("id").toString();
                timestamp = jsonNodes[i].findValue("startTime").toString();
                Id1 = jsonNodes[i].findValue("id").toString();
                String Id2 = Id1.substring(0, Id1.length() - 1);
                Id = Id2.substring(1);
                changeBackNotes = jsonNodes[i].findValue("notes").toString();
                UserId = jsonNodes[i].findValue("customer").get("id").toString();
                flag = true;
            }
            //if the pin code is not exist, it will show a hints which is a invalid pin code or id
            else if (i == jsonNodes.length - 1 && flag == false) {
                recepController.InvaildPinOrId();

            }


        }
        response = api_service.getTesting();
        //The request will return the object
        jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);

        for (int i = 0; i < jsonNodes.length; i++) {

            //get pin code and booking information, convert to string and lowercase
            String getId = jsonNodes[i].findValue("booking").get("id").toString();
            String getPin = jsonNodes[i].findValue("booking").get("smsPin").toString();
            if(getId.equals("\"" + ChangePinOrID + "\"")||getPin.equals("\"" + ChangePinOrID + "\"")){
                recepController.testDoneBefore();
                flag=true;

            }
        }
        recepController.ChangeAsk();
        booking();



    }
    //implement method in booking interface and override
    public void booking() throws IOException, InterruptedException, ParseException {
        API_Service api_service = new API_Service();
        RecepController recepController = new RecepController();
        //1. changing testing site
        if(ChangeType.equals("1")){
            HttpResponse<String> response = api_service.getSite();
            ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
            for (int i = 0; i <jsonNodes.length; i++) {
                // The boolean is used to determine when to loop should stop
                boolean flag = false;
                String getName = jsonNodes[i].findValue("name").toString().toLowerCase();
                String suburbName = jsonNodes[i].findValue("suburb").toString().toLowerCase();
                String getType = jsonNodes[i].findValue("description").toString().toLowerCase();
                //If there is a corresponding test site
                if(ChangeInfo.toLowerCase().equals(suburbName)||getName.contains(ChangeInfo)||getType.contains(ChangeInfo)){
                    //print the information
                    siteID = jsonNodes[i].findValue("id").toString();
                    flag = true;
                    break;}
                //If there is no corresponding test site
                else if(i == jsonNodes.length-1&& !flag){
                   recepController.InvalidInformation();
                }
            }
            String jsonString ;
            originalNotes = locationId;
            jsonString =
                    "{" +"\"customerId\":" +  UserId + "," +
                            "\"testingSiteId\":" +  siteID + "," +
                            "\"startTime\":" + timestamp +  "," +
                            "\"status\":" + "\""+ "Changed" +"\""+"," +
                            "\"notes\":" + originalNotes +"," +
                            "\"additionalInfo\":" + " {"+""+
                            "}"+
                            "}";

            response = api_service.PatchBooking(jsonString,Id);

            //changing successfully
            if(response.statusCode()==200){
                recepController.Success();
            }
            //changing failed
            else{
               recepController.Fail();
            }

        }
        //2. changing testing date
        else if(ChangeType.equals("2")){
            originalNotes = timestamp;
            LocalTime time = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timestamps = "2022-"+ChangeInfo+"T"+time.format(formatter)+".609Z";
            String jsonString =
                    "{" +"\"customerId\":" +  UserId + "," +
                            "\"testingSiteId\":" +  locationId + "," +
                            "\"startTime\":" + "\""+timestamps + "\""+  "," +
                            "\"status\":" + "\""+ "Changed" +"\""+"," +
                            "\"notes\":" +  originalNotes +"," +
                            "\"additionalInfo\":" + " {"+""+
                            "}"+
                            "}";

            HttpResponse<String> response = api_service.PatchBooking(jsonString,Id);

            //change successfully
            if(response.statusCode()==200){
                recepController.Success();
            }
            //change failed
            else{
                recepController.Fail();
            }
        }
        //3. changing testing time
        else if(ChangeType.equals("3")){
                originalNotes = timestamp;
                String timechange = timestamp.substring(0,12);
                timechange = timechange + ChangeInfo+":01.918Z\"";
                String jsonString =
                        "{" +"\"customerId\":" + UserId + "," +
                                "\"testingSiteId\":" +  locationId + "," +
                                "\"startTime\":" + timechange +  "," +
                                "\"status\":" + "\""+ "Changed" +"\""+"," +
                                "\"notes\":" +  originalNotes +"," +
                                "\"additionalInfo\":" + " {"+""+
                                "}"+
                                "}";
                HttpResponse<String> response = api_service.PatchBooking(jsonString,Id);
                //change successfully
                if(response.statusCode()==200){

                    recepController.Success();
                }
                //change failed
                else{
                    recepController.Fail();
                }
        }
        //change to previous modification record
        else if(ChangeType.equals("4")){
            recepController.ask();

        }
        //enter some invalid value
        else{
            recepController.InvaildMethod();

        }
    }
    //Select the previously amended booking information
    public void changeBack(String in) throws IOException, InterruptedException, ParseException {
        RecepController recepController = new RecepController();
        //select testing site to previous one
        if(in.equals("1")){
            String jsonString =
                    "{" +"\"customerId\":" +  UserId + "," +
                            "\"testingSiteId\":" +  changeBackNotes + "," +
                            "\"startTime\":" + timestamp +  "," +
                            "\"status\":" + "\""+ "INITIAL" +"\""+"," +
                            "\"notes\":" + "\""+" "+"\"" +"," +
                            "\"additionalInfo\":" + " {"+""+
                            "}"+
                            "}";


            HttpResponse<String> response = api_service.PatchBooking(jsonString,Id);
            //change successfully
            if(response.statusCode()==200){
                recepController.Success();
            }
            //change failed
            else{
                recepController.Fail();

            }

        }
        //select date to previous one
        else if(in.equals("2")){
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            //current date
            Date date = new Date(System.currentTimeMillis());
            Date date1 = df2.parse(df2.format(date));
            //previous date
            String time2 = changeBackNotes.substring(0,changeBackNotes.length()-1);
            String time3 = time2.substring(1);
            try{date2 = df2.parse(time3);}
            catch (Exception e){
                recepController.IncorrectTime();
            }
            //date 1 compare with date 2, if bigger than 0, the date 1 is bigger than date 2, so it can change to previous one
            if(date1.compareTo(date2)<0){
                String jsonString =
                        "{" +"\"customerId\":" +  UserId + "," +
                                "\"testingSiteId\":" + locationId + "," +
                                "\"startTime\":" + changeBackNotes +  "," +
                                "\"status\":" + "\""+ "INITIAL" +"\""+"," +
                                "\"notes\":" + "\""+" "+"\"" +"," +
                                "\"additionalInfo\":" + " {"+""+
                                "}"+
                                "}";

                HttpResponse<String> response = api_service.PatchBooking(jsonString,Id);

                //change successfully

                    if(response.statusCode()==200){
                    recepController.Success();
                }
                //change failed
                else{
                    recepController.Fail();
                }


            }
            //can not change to previous date
            else{
                recepController.IncorrectTime();
            }

        }
        //select an invalid information
        else{
            recepController.InvaildMethod();
        }
    }

}