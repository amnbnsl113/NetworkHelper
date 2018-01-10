package network.manage.networkhelper;

import network.manage.networkhelper.retrofit.RetrofitManager;

/**
 * Created by aman on 28/12/17.
 */

public class NetworkInteractor {
    private NetworkManager networkManager;
    private static NetworkInteractor interactor = new NetworkInteractor();

    private NetworkInteractor() {

    }

    public static NetworkInteractor getInstance() {
        return interactor;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }

    public void setNetworkManager(NetworkManager networkManager) {
        this.networkManager = networkManager;
    }
}
