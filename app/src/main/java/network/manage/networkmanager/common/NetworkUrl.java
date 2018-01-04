package network.manage.networkmanager.common;

/**
 * Created by aman on 28/12/17.
 */

public class NetworkUrl {
    private static final String PRODUCTION = "https://jsonplaceholder.typicode.com/";
    private static String BASE_URL = PRODUCTION;

    public static String getPostListUrl() {
        return BASE_URL + "posts";
    }


}
