package network.manage.networkhelper;

import java.lang.ref.WeakReference;
import java.util.List;

import network.manage.networkhelper.model.BaseResponse;

/**
 * Created by aman on 27/12/17.
 */

public interface NetworkManager {

    void get(String url, final WeakReference<Callback<BaseResponse>> weakReference);

    void getList(String url, final WeakReference<Callback<List<BaseResponse>>> weakReference);
}
