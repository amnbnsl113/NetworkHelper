package network.manage.networkhelper.retrofit;

import android.content.Context;

import java.util.List;
import java.util.Map;

import io.reactivex.schedulers.Schedulers;
import network.manage.networkhelper.NetworkManager;
import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.model.BaseResponse;
import network.manage.networkhelper.parser.ParseFunction;
import network.manage.networkhelper.parser.ParseFunctionList;
import network.manage.networkhelper.common.MySchedulers;

/**
 * Created by aman on 27/12/17.
 */

public class RetrofitManager implements NetworkManager {

    private Context context;

    public RetrofitManager(Context context) {
        this.context = context;
    }

    private static ApiInterfaceRetrofit requestInterface = RetrofitAdapter.getRetrofit(null).create(ApiInterfaceRetrofit.class);

    @Override
    public <T extends BaseResponse> void postWithFormData(String url, final AbstractObserver<T> observer, Class<T> clazz, Map<String, String> params, Map<String, String> headers, boolean synchronous) {
        requestInterface.postWithFormData(url, params, headers)
                .flatMap(new ResponseToString())
                .flatMap(new ParseFunction<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }

    @Override
    public <T extends BaseResponse> void post(String url, final AbstractObserver<T> observer, Class<T> clazz, Object body, Map<String, String> headers, boolean synchronous) {
        requestInterface.post(url, body, headers)
                .flatMap(new ResponseToString())
                .flatMap(new ParseFunction<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }

    @Override
    public <T extends BaseResponse> void get(String url, final AbstractObserver<T> observer, Class<T> clazz, Map<String, String> headers, boolean synchronous) {
        requestInterface.get(url, headers)
                .flatMap(new ResponseToString())
                .flatMap(new ParseFunction<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }

    @Override
    public <T extends BaseResponse> void getList(String url, final AbstractObserver<List<T>> observer, Class<T> clazz, Map<String, String> headers, boolean synchronous) {

        requestInterface.getList(url, headers)
                .flatMap(new ResponseToString())
                .flatMap(new ParseFunctionList<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }


}
