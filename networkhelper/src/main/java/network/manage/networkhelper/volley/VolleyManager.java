package network.manage.networkhelper.volley;

import android.content.Context;

import java.util.List;
import java.util.Map;

import io.reactivex.schedulers.Schedulers;
import network.manage.networkhelper.NetworkManager;
import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.common.MySchedulers;
import network.manage.networkhelper.model.BaseResponse;
import network.manage.networkhelper.parser.ParseFunction;
import network.manage.networkhelper.parser.ParseFunctionList;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

/**
 * Created by aman on 9/1/18.
 */

public class VolleyManager implements NetworkManager {
    private VolleyReqMaker reqMaker;

    public VolleyManager(Context applicationContext) {
        reqMaker = new VolleyReqMaker(applicationContext);
    }


    @Override
    public <T extends BaseResponse> void postWithFormData(String url, AbstractObserver<T> observer, Class<T> clazz, Map<String, String> params, Map<String, String> headers, boolean synchronous) {

        reqMaker.fireRequest(POST, url, headers, params, null)
                .toObservable()
                .flatMap(new ParseFunction<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }

    @Override
    public <T extends BaseResponse> void post(String url, AbstractObserver<T> observer, Class<T> clazz, Object body, Map<String, String> headers, boolean synchronous) {

        reqMaker.fireRequest(POST, url, headers, null, body)
                .toObservable()
                .flatMap(new ParseFunction<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);

    }

    @Override
    public <T extends BaseResponse> void get(String url, AbstractObserver<T> observer, Class<T> clazz, Map<String, String> headers, boolean synchronous) {

        reqMaker.fireRequest(GET, url, headers, null, null)
                .toObservable()
                .flatMap(new ParseFunction<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);

    }

    @Override
    public <T extends BaseResponse> void getList(String url, AbstractObserver<List<T>> observer, Class<T> clazz, Map<String, String> headers, boolean synchronous) {

        reqMaker.fireRequest(GET, url, headers, null, null)
                .toObservable()
                .flatMap(new ParseFunctionList<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);

    }
}
