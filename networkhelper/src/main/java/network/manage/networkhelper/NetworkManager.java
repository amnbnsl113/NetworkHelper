package network.manage.networkhelper;

import java.util.List;
import java.util.Map;

import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.model.BaseResponse;

/**
 * Created by aman on 27/12/17.
 */

public interface NetworkManager {

    <T> void postWithFormData(String url, AbstractObserver<T> observer, Class<T> clazz, Map<String, String> params, Map<String, String> headers, boolean synchronous);

    <T> void post(String url, AbstractObserver<T> observer, Class<T> clazz, Object body, Map<String, String> headers, boolean synchronous);

    <T> void get(String url, AbstractObserver<T> observer, Class<T> clazz, Map<String, String> headers, boolean synchronous);

    <T> void getList(String url, AbstractObserver<List<T>> observer, Class<T> clazz, Map<String, String> headers, boolean synchronous);

}
