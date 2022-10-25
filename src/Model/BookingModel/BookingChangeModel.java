package Model.BookingModel;

import API.API_Service;
import Controller.ProfileController.ProfileController;
import Model.Entity.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.rmi.server.ExportException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

//This class is the model of changing the booking
public class BookingChangeModel implements BookingInt {
    //declare the variable
    private String decision;
    private ArrayList<String> UserBooking = new ArrayList<>();
    API_Service api_service= new API_Service();
    private static String siteID;
    private static String timestamp;
    private static String Id;
    private static String Id1;
    private static String locationId;
    private static String ChangeType;
    private  String originalNotes;
    private static String changeBackNotes;
    private static String timechange;
    static Date date2;


    public static void setChangeType(String changeType) {
        ChangeType = changeType;
    }

    private static String ChangeInfo;

    public void setChangeInfo(String changeInfo) {
        ChangeInfo = changeInfo;
    }

    public void setDecison(String decision) {
        this.decision = decision;
    }


    //booking information
    public void bookingInit() throws IOException, InterruptedException, ParseException {
        ArrayList<String> BookingInfo = Users.getUserInfo();
        ProfileController profileController = new ProfileController();
        HttpResponse<String> response1 = api_service.getBooking();
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response1.body(), ObjectNode[].class);
        try{
            if(decision.length()<=3){
                int k = Integer.parseInt(decision);
                UserBooking.add(BookingInfo.get(k));
                // This process will use the for loop to find if there is any userName so that identify if they are registered
                for (int i = 0; i < jsonNodes.length; i++) {
                    String getName = jsonNodes[i].toString();
                    if(getName.equals(BookingInfo.get(k))){
                        locationId = jsonNodes[i].findValue("testingSite").get("id").toString();
                        timestamp = jsonNodes[i].findValue("startTime").toString();
                        Id1 = jsonNodes[i].findValue("id").toString();
                        String Id2 = Id1.substring(0,Id1.length()-1);
                        Id = Id2.substring(1);
                        changeBackNotes = jsonNodes[i].findValue("notes").toString();




                    }
                }
            }
            else{

                HttpResponse<String> response = api_service.getSomeoneBooking(decision);
                // This process will use the for loop to find if there is any userName so that identify if they are registered
                for (int i = 0; i < jsonNodes.length; i++) {
                    String getName = jsonNodes[i].findValue("id").toString();

                    if(getName.equals("\""+decision+"\"")){
                        locationId = jsonNodes[i].findValue("testingSite").get("id").toString();
                        timestamp  = jsonNodes[i].findValue("startTime").toString();
                        Id = decision;
                        changeBackNotes = jsonNodes[i].findValue("notes").toString();
                    }
                }
                //failed, invalid information
                if(response.statusCode()==400){
                    profileController.InvaildInfo();
                }
                }

        }
        catch(Exception e){
            profileController.InvaildInfo();
        }
        }
        //
        public void booking() throws IOException, InterruptedException, ParseException {
            API_Service api_service = new API_Service();
            ProfileController profileController = new ProfileController();

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
                   profileController.InvaildtestingInfo();
                }
            }
            String jsonString ;
            originalNotes = locationId;
            jsonString =
                    "{" +
                            "\"testingSiteId\":" +  siteID + "," +
                            "\"status\":" + "\""+ "Changed" +"\""+"," +
                            "\"notes\":" + originalNotes +"," +
                            "\"additionalInfo\":" + " {"+""+
                            "}"+
                            "}";

            response = api_service.PatchBooking(jsonString,Id);

            //changing successfully
            if(response.statusCode()==200){
                profileController.SuccessfulChange();
            }
            //changing failed
            else{

                profileController.InvaildInfo();
            }


        }
        //2. changing testing date
        else if(ChangeType.equals("2")){
            originalNotes = timestamp;

            LocalTime time = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timestamps = "2022-"+ChangeInfo+"T"+time.format(formatter)+".609Z";


            String jsonString =
                            "{" +
                                    "\"startTime\":" + "\""+timestamps + "\""+  "," +
                                    "\"status\":" + "\""+ "Changed" +"\""+"," +
                                    "\"notes\":" +  originalNotes +"," +
                                    "\"additionalInfo\":" + " {"+""+
                                    "}"+
                                    "}";

            HttpResponse<String> response = api_service.PatchBooking(jsonString,Id);
            //change successfully

            if(response.statusCode()==200){
                profileController.SuccessfulChange();
            }
            //change failed
            else{

                profileController.InvaildInfo();
            }
        }
        //3. changing testing time
        else if(ChangeType.equals("3")){
            originalNotes = timestamp;


            timechange = timestamp.substring(0,12);
            timechange = timechange + ChangeInfo+":01.918Z\"";
            String jsonString =
                    "{" +
                            "\"startTime\":" + timechange +  "," +
                            "\"status\":" + "\""+ "Changed" +"\""+"," +
                            "\"notes\":" +  originalNotes +"," +
                            "\"additionalInfo\":" + " {"+""+
                            "}"+
                            "}";
            HttpResponse<String> response = api_service.PatchBooking(jsonString,Id);
            //change successfully

            if(response.statusCode()==200){

                profileController.SuccessfulChange();
            }
            //change failed
            else{


                profileController.InvaildInfo();
            }

        }
        //change to previous modification record
        else if(ChangeType.equals("4")){
           profileController.requestTypeAsk();
        }
        //enter some invalid value
        else
            profileController.InvaildInfo();

        }
    //Select the previously amended booking information
    public void changeBack(String in) throws IOException, InterruptedException, ParseException {
        Users users = new Users();
        ProfileController profileController = new ProfileController();
        //select testing site to previous one
        if(changeBackNotes.length()<5){
            profileController.FailBack();
        }
        if(in.equals("1")){
            String jsonString =
                    "{" +"\"customerId\":" +  users.getId() + "," +
                            "\"testingSiteId\":" +  changeBackNotes + "," +
                            "\"startTime\":" + timestamp +  "," +
                            "\"status\":" + "\""+ "INITIATED" +"\""+"," +
                            "\"notes\":" + "\""+" "+"\"" +"," +
                            "\"additionalInfo\":" + " {"+""+
                            "}"+
                            "}";



            HttpResponse<String> response = api_service.PatchBooking(jsonString,Id);


                //change successfully
                if(response.statusCode()==200){

                profileController.SuccessBack();
                }
                //change failed
                else{
                    profileController.FailBack();
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
            try{date2 = df2.parse(time3);}catch (Exception e){ profileController.FailBack();}
            //date 1 compare with date 2, if bigger than 0, the date 1 is bigger than date 2, so it can change to previous one
            if(date1.compareTo(date2)<0){
                String jsonString =
                        "{" +"\"customerId\":" +  users.getId() + "," +
                                "\"testingSiteId\":" + locationId + "," +
                                "\"startTime\":" + changeBackNotes +  "," +
                                "\"status\":" + "\""+ "INITIATED" +"\""+"," +
                                "\"notes\":" + "\""+" "+"\"" +"," +
                                "\"additionalInfo\":" + " {"+""+
                                "}"+
                                "}";

                HttpResponse<String> response = api_service.PatchBooking(jsonString,Id);

                //change successfully
                if(response.statusCode()==200){
                    profileController.SuccessBack();
                }
                //change failed
                else{
                    profileController.FailBack();
                }
            }
            //can not change to previous date
            else
                profileController.incorrectDate();
    }
        //select an invalid information
        else{
            profileController.InvaildInfo() ;
        }
    }
}

