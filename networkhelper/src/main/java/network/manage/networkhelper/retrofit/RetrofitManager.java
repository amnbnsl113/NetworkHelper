package network.manage.networkhelper.retrofit;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import network.manage.networkhelper.Callback;
import network.manage.networkhelper.NetworkManager;
import network.manage.networkhelper.common.AbstractObserver;
import network.manage.networkhelper.model.BaseResponse;

/**
 * Created by aman on 27/12/17.
 */

public class RetrofitManager implements NetworkManager {

    private static ApiInterfaceRetrofit requestInterface = RetrofitAdapter.getRetrofit(null).create(ApiInterfaceRetrofit.class);

    @Override
    public void get(String url, final WeakReference<Callback<BaseResponse>> weakReference) {
        requestInterface.get(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new AbstractObserver<>(weakReference));
    }

    @Override
    public void getList(String url, final WeakReference<Callback<List<BaseResponse>>> weakReference) {
        requestInterface.getList(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new AbstractObserver<>(weakReference));
    }
}
