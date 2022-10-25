package Model.TestingModel;

import API.API_Service;
import Controller.QRcodeController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.TimeZone;
//This class is the model of QR code scan
public class QRcodeScanModel {
    HttpResponse<String> response;
    //Declare and initialize the user information
    private  String UserId ="";
    private String name = "";
    private  String siteID = "";

    public void QR(String qrCode) throws IOException, InterruptedException, ParseException {

        API_Service api_service = new API_Service();
        QRcodeController qRcodeController= new QRcodeController();
        // calling the API service of testing information get
        response = api_service.getTesting();
        boolean flag = false;
        //use loop to get the id and name
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        for (int i = 0; i < jsonNodes.length; i++) {
            String getQRCode= "";
            getQRCode = jsonNodes[i].toString().toLowerCase();

            if (getQRCode.contains(qrCode)) {
                qRcodeController.SuccressScan();
                UserId = jsonNodes[i].findValue("id").toString().toLowerCase();
                name = jsonNodes[i].findValue("name").toString().toLowerCase();
                flag = true;
                break;
            }
            //for if can't get the information
            else if (i == jsonNodes.length - 1 && flag == false) {
                qRcodeController.UpdateFail();
            }
        }
        //for delete the quotation mark for added to the jsonString.
        String SiteName;
        String test = UserId.substring(1);
        test = test.substring(0, test.length()-1);
        response  = api_service.getTesting();
        jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        //Find the testing site name
        for (int i = 0; i <jsonNodes.length; i++) {
            SiteName = jsonNodes[i].findValue("name").toString().toLowerCase();
            if(name.equals(SiteName)){
                siteID = jsonNodes[i].findValue("id").toString().toLowerCase();}
        }
        // for the continue step and form the jsonString
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");//generate the date
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String NewQrcode = "Expire";
        //initialize the link and QR code
        String timestamp = df.format(new Date());
        String link = "https://testing.com.au";
        String note = "They have get the tool";
        // create the jsonString

        String jsonString =
                "{" +"\"customerId\":" +  UserId + "," +
                        "\"testingSiteId\":" + siteID + "," +
                        "\"startTime\":" + "\""+ timestamp + "\""+ "," +
                        "\"notes\":" + "\""+ note + "\""+"," +
                        "\"additionalInfo\":" + " {"+"\"QR\""+":"+
                        "\""+ NewQrcode + "\"" + ","
                        +"\"link\""+":"+"\""+ link +"\""+"}"+
                        "}";
        response = api_service.PatchTesting(jsonString, test);
        //receive the status code to identify the error or the success
        if(response.statusCode()==200)
           qRcodeController.updateSuccess();
        else
            qRcodeController.updateFail();
        qRcodeController.nextStep();
    }
}
