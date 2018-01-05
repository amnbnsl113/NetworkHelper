package network.manage.networkhelper;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.model.BaseRequest;
import network.manage.networkhelper.model.BaseResponse;

/**
 * Created by aman on 27/12/17.
 */

public interface NetworkManager {


    public <T extends BaseResponse> void postWithFormData(String url, AbstractObserver<T> observer, Class<T> clazz, Map<String, String> params);

    public <T extends BaseResponse> void post(String url, AbstractObserver<T> observer, Class<T> clazz, Object body);

    public <T extends BaseResponse> void get(String url, final AbstractObserver<T> observer, Class<T> clazz);

    public <T extends BaseResponse> void getList(String url, final AbstractObserver<List<T>> observer, Class<T> clazz);
}
