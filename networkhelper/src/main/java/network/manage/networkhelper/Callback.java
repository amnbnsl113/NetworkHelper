package network.manage.networkhelper;

import network.manage.networkhelper.common.NetworkError;

/**
 * Created by aman on 27/12/17.
 */

public interface Callback<T> {
    void onSuccess(T t);

    void onFailure(NetworkError error);
}
