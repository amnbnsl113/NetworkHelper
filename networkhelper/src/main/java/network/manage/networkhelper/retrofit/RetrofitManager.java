package network.manage.networkhelper.retrofit;

import android.os.Looper;

import java.util.List;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import network.manage.networkhelper.NetworkManager;
import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.model.BaseRequest;
import network.manage.networkhelper.model.BaseResponse;
import network.manage.networkhelper.parser.ParseFunction;
import network.manage.networkhelper.parser.ParseFunctionList;
import network.manage.networkhelper.util.MySchedulers;

/**
 * Created by aman on 27/12/17.
 */

public class RetrofitManager implements NetworkManager {

    private static ApiInterfaceRetrofit requestInterface = RetrofitAdapter.getRetrofit(null).create(ApiInterfaceRetrofit.class);

    @Override
    public <T extends BaseResponse> void postWithFormData(String url, final AbstractObserver<T> observer, Class<T> clazz, Map<String, String> params, boolean synchronous) {
        requestInterface.postWithFormData(url, params)
                .flatMap(new ParseFunction<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }

    @Override
    public <T extends BaseResponse> void post(String url, final AbstractObserver<T> observer, Class<T> clazz, Object body, boolean synchronous) {
        requestInterface.post(url, body)
                .flatMap(new ParseFunction<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }

    @Override
    public <T extends BaseResponse> void get(String url, final AbstractObserver<T> observer, Class<T> clazz, boolean synchronous) {
        requestInterface.get(url)
                .flatMap(new ParseFunction<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }

    @Override
    public <T extends BaseResponse> void getList(String url, final AbstractObserver<List<T>> observer, Class<T> clazz, boolean synchronous) {

        requestInterface.get(url)
                .flatMap(new ParseFunctionList<>(clazz))
                .observeOn(MySchedulers.getScheduler(synchronous))
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }


}
