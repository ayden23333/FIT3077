package API;
//This class is used to store the API key and URL
public class APIStore {
    private static final String myApiKey ="" ;


    private static final String rootUrl = "https://fit3077.com/api/v2";
    public static String getMyApiKey(){
        return myApiKey;

    }
    public static String getRootUrl(){
        return rootUrl;

    }

}
