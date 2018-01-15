package network.manage.networkhelper;

import android.net.Uri;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Map;

import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.model.BaseResponse;

/**
 * Created by aman on 27/12/17.
 */

public interface NetworkManager {


    <T extends BaseResponse> void postWithFormData(String url, AbstractObserver<T> observer, Class<T> clazz, Map<String, String> params, Map<String, String> headers, boolean synchronous);

    <T extends BaseResponse> void post(String url, AbstractObserver<T> observer, Class<T> clazz, Object body, Map<String, String> headers, boolean synchronous);

    <T extends BaseResponse> void uploadFile(String url, Uri fileUri, AbstractObserver<T> observer, Class<T> clazz, Map<String, String> description, Map<String, String> headers, boolean synchronous);

    <T extends BaseResponse> void get(String url, AbstractObserver<T> observer, Class<T> clazz, Map<String, String> headers, boolean synchronous);

    <T extends BaseResponse> void getList(String url, AbstractObserver<List<T>> observer, Class<T> clazz, Map<String, String> headers, boolean synchronous);
}
