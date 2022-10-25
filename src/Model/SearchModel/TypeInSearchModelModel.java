package Model.SearchModel;

import API.API_Service;
import Controller.SearchController.SearchController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.ParseException;
//This class is the model about searching testing site by implementing the search interface
public class TypeInSearchModelModel implements SearchModelInt {
    private static String sitename = "";
    private static String result = "";

    public static String getSitename() {
        return sitename;
    }

    public static void setSitename(String sitename) {
        TypeInSearchModelModel.sitename = sitename;
    }

    public static String getResult() {
        return result;
    }

    //implement method in search interface and override
    public void search() throws IOException, InterruptedException, ParseException {
        API_Service api_service = new API_Service();
        HttpResponse<String> response = api_service.getSite();
        ObjectNode[] jsonNodes = new ObjectMapper().readValue(response.body(), ObjectNode[].class);
        for (int i = 0; i <jsonNodes.length; i++) {
            // The boolean is used to determine when to loop should stop
            boolean flag = false;
            String getName = jsonNodes[i].findValue("name").toString().toLowerCase();
            String suburbName = jsonNodes[i].findValue("suburb").toString().toLowerCase();
            String getType = jsonNodes[i].findValue("description").toString().toLowerCase();
            //If there is a corresponding test site
            if(sitename.equals(suburbName)||getName.contains(sitename)||getType.contains(sitename)){
                //print the information
                result = jsonNodes[i].findValue("name").toString()+"\n"+jsonNodes[i].findValue("description").toString();
                flag = true;
                break;}
            //If there is no corresponding test site
            else if(i == jsonNodes.length-1&& !flag){
               result = " There is no suitable test Facility ";
            }
        }
        //display the information of test site or facility
        SearchController searchController = new SearchController();
        searchController.updateSearchResult();
    }

}
