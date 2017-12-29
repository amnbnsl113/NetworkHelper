package network.manage.networkmanager.common;

import network.manage.networkhelper.common.NetworkError;
import network.manage.networkmanager.model.viewmodel.IViewModel;

/**
 * Created by aman on 28/12/17.
 */

public interface Callback<T extends IViewModel> {

    public void onSuccess(T t);

    public void onFailure(NetworkError error);

}
