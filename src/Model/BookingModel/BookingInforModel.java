package Model.BookingModel;

import API.API_Service;
import Controller.BookingController.RecepController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.ParseException;
//This class is about the model of booking information
public class BookingInforModel implements BookingInt {
    private boolean flag = false;
    private static String pinCode;

    public static void setPinCode(String pin) {
        BookingInforModel.pinCode = pin;
    }

    static String result;

    public static String getResult() {
        return result;
    }

    public static void setResult(String result) {
        BookingInforModel.result = result;
    }
    //implement method in booking interface and override
    public void booking() throws IOException, InterruptedException, ParseException {
        API_Service api_service =new API_Service();
        HttpResponse<String> response = api_service.getBooking();
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);

        //use for loop to check the pin code by findValue
        for (int i = 0; i < jsonNodes.length; i++) {
            //get pin code and booking information, convert to string and lowercase
            String getPin = jsonNodes[i].findValue("smsPin").toString().toLowerCase();
            String getBookingInfo = jsonNodes[i].toString().toLowerCase();
            //if the pin code exist, it will show the booking information of the user
            if (getPin.contains(pinCode)) {
               setResult("Your booking information is here: "+getBookingInfo);
                flag = true;
            }
            //if the pin code is not exist, it will show a hints
            else if (i == jsonNodes.length - 1 && flag == false) {
                setResult(" No booking information detected");
            }

        }
        RecepController recepController = new RecepController();
        recepController.updateView();

    }

}
