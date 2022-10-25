package Model.BookingModel;

import API.API_Service;
import Controller.BookingController.BookingController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.TimeZone;
//This class is the model of booking
public class BookingModel implements BookingInt{
    //declare variables and initialize
    API_Service api_service =new API_Service();
    private static String id = "";
    private boolean flag = false;
    private String result ="";
    private HttpResponse<String> response;
    private static String notes =" ";
    private  String jsonString;
    private  static String siteID;
    //set month
    public static void setMonth(String month) {
        Month = month;
    }
    //set day
    public static void setDay(String day) {
        BookingModel.day = day;
    }
    //booking information
    private static String siteResult ;
    private static String booking;
    private static String username;
    private static String Month;
    private static String day;

    public String getBooking() {
        return booking;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public void NameChecking() throws IOException, InterruptedException, ParseException {
        BookingController bookingController= new BookingController();
        response = api_service.getuser();
        //The request will return the object

        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        // This process will use the for loop to find if there is any username so that identify if they are registered
        for (int i = 0; i < jsonNodes.length; i++) {
            //get the username to String and lowercase
            String getName = jsonNodes[i].findValue("userName").toString().toLowerCase();
            //if user registered
            if (getName.contains(username)) {
                setId(jsonNodes[i].findValue("id").toString());
                flag = true;
                break;
            }
            else if (i == jsonNodes.length - 1 && flag == false) {
                result = " You haven't registered yet ";

            }

    }

        bookingController.updateCheckingResult(result);

}
    //set testing site
    public void setSiteResult(String siteResult) {
        this.siteResult = siteResult;
    }

    public void setId(String id) {
        this.id = id;
    }
    //set testing site id
    public void setSiteId(String id) {
        this.siteID = id;
    }
    //Verify that the test site is available or not
    public void SiteVerify(String in) throws IOException, InterruptedException {
          response = api_service.getSite();
          //Fetch the test site information first
          ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
          //use for loop to find the name matching by using the findValue
          for (int i = 0; i <jsonNodes.length; i++) {
              boolean flag = false;
              String getName = jsonNodes[i].findValue("name").toString().toLowerCase();
              //If there is exist
              if(getName.contains(in.toLowerCase())){
                  siteResult = jsonNodes[i].findValue("name").toString();
                  setSiteId( jsonNodes[i].findValue("id").toString());
                  flag = true;
                  break;}
              //If there is no exist, then provide an error and back to site verify
              else if(i == jsonNodes.length-1&&flag == false){
                  String siteResult = " There is no such a facility exit";
                  setSiteResult(siteResult);
              }
          }


    }
    //set notes
    public static void setNotes(String notes) {
        BookingModel.notes = notes;
    }

    public String getSiteResult() {
        return siteResult;
    }
    //booking
    public void booking() throws IOException, InterruptedException, ParseException {
        BookingController bookingController = new BookingController();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timestamp = "2022-"+Month+"-"+day+"T"+time.format(formatter)+".609Z";

        //The following will get all the information type in and store as a string


        jsonString =
                "{" +"\"customerId\":" +  id + "," +
                        "\"testingSiteId\":" +  siteID + "," +
                        "\"startTime\":" + "\""+ timestamp + "\""+ "," +
                        "\"notes\":" + "\""+ notes + "\""+"," +
                        "\"additionalInfo\":" + " {"+""+
                        "}"+
                        "}";
        //booking request sent and POST the request and store in the survey

        response = api_service.postBooking(jsonString);
        //booking successfully
        if(response.statusCode()==201){
            booking = response.body();
            bookingController.Sccuess();
        }
        //booking failed
        else {
            bookingController.Fail();

        }
    }
}
