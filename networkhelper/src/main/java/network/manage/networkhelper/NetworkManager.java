package network.manage.networkhelper;

import java.lang.ref.WeakReference;
import java.util.List;

import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.model.BaseResponse;

/**
 * Created by aman on 27/12/17.
 */

public interface NetworkManager {

    public <T extends BaseResponse> void get(String url, final AbstractObserver<T> observer, Class<T> clazz);

    public <T extends BaseResponse> void getList(String url, final AbstractObserver<List<T>> observer, Class<T> clazz);
}
