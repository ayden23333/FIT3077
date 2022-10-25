package View;

import Controller.ServiceDecideController.ServiceDecideInt;
import Controller.ServiceDecideController.ServiceDecideContorller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
//This class is the view of service decide page
public class ServiceDecideView {
    public void ServiceDecide() throws IOException, InterruptedException, ParseException {
        System.out.println("|----------------------------------------------|");
        System.out.println(" You are now in the service selection page");
        System.out.println("|----------------------------------------------|");
        System.out.println(" You can select the following service you want");
        //There are 6 options of services that users can select
        System.out.println(" For searching site, type 1\n For booking and booking change, type 2\n For On-siteTesting, Type 3\n For QRcode scan type 4\n For homeBooking type 5\n For exit type 6\n For profile and booking Information checking type 7");
        System.out.println(" For assistant booking Information Checking type 8");
        System.out.println(" For Admin surface type 9 (For delete or cancel the booking)");
        System.out.println("|----------------------------------------------|");

        ServiceDecideInt serviceDecide = new ServiceDecideContorller();
        serviceDecide.serviceDecide();
    }
}
