package Controller.TestingController;

import Model.Entity.Symptom;
import Model.TestingModel.TestingModel;
import View.HealthCareView;
import View.ServiceDecideView;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//This is the controller of testing
public class TestingController {
    //declare variables
    private static String Familyname;
    private static String Givenname;
    private static String Symptoms;
    private static String notes;
    Scanner scan = new Scanner(System.in);

    //set the family name
    public void TestingFamilyName() throws IOException, InterruptedException, ParseException {
        HealthCareView healthCareView = new HealthCareView();
        Familyname =  "\""+scan.nextLine().toLowerCase() +"\"";
        healthCareView.GivenName();
    }
    //set the given name
    public void GiveName() throws IOException, InterruptedException, ParseException {
        HealthCareView healthCareView = new HealthCareView();
        Givenname =  "\""+scan.nextLine().toLowerCase() +"\"";
        healthCareView.getSymptoms();

    }
    //set the symptoms, then decide to do PCR or RAT test
    public void Symptoms() throws IOException, InterruptedException, ParseException {
        Symptom PostiveSymptom = Symptom.PCR;
        Symptom NegativeSymptom = Symptom.RAT;
        HealthCareView healthCareView =new HealthCareView();
        TestingModel testingModel = new TestingModel();
        String in =  scan.nextLine();
        //select 1. user has symptoms, recommend to do PCR test
        if(in.equals("1")){
            Symptoms = "\""+PostiveSymptom+"\"";
            notes = "\""+"Have Symptoms"+"\"";
           healthCareView.PCR();
           testingModel.Checking( Familyname,Givenname,notes, Symptoms);

        }
        //Select 2. User has no symptoms, recommend to do RAT test
        else if(in.equals("2")){
            Symptoms = "\""+NegativeSymptom+"\"";
            notes = "\""+"Don't have symptoms"+"\"";
            healthCareView.RAT();
            testingModel.Checking( Familyname,Givenname,notes, Symptoms);
            }
    }
    //failed booking, not booking yet
    public void Failbooking(){

        HealthCareView healthCareView =new HealthCareView();
        healthCareView.bookchecking();
    }
    //failed ID, not registered
    public void FailId(){
        HealthCareView healthCareView =new HealthCareView();
        healthCareView.FailIdchecking();
    }
    //start testing
    public void TestingStart(){
        HealthCareView healthCareView =new HealthCareView();
        healthCareView.testingStart();
    }
    //failed testing without booking
    public void TestingFail() throws IOException, InterruptedException, ParseException {
        HealthCareView healthCareView =new HealthCareView();
        healthCareView.testingFail();
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();
    }
    //successful completed the testing
    public void TestingSuccess(String in) throws IOException, InterruptedException, ParseException {
        HealthCareView healthCareView =new HealthCareView();
        healthCareView.testingSuccess(in);
        ServiceDecideView serviceDecideView = new ServiceDecideView();
        serviceDecideView.ServiceDecide();

    }
}


