package network.manage.networkmanager;

import android.app.Application;

import network.manage.networkhelper.NetworkInteractor;
import network.manage.networkhelper.NetworkManager;
import network.manage.networkhelper.volley.VolleyManager;

/**
 * Created by aman on 9/1/18.
 */

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkInteractor.getInstance().setNetworkManager(new VolleyManager(getApplicationContext()));
    }
}
