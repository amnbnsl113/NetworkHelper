package network.manage.networkhelper.parser;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import network.manage.networkhelper.model.BaseResponse;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by aman on 28/12/17.
 */

public class ParseFunctionList<T> implements Function<String, Observable<List<T>>> {

    private Class<T> clazz;

    public ParseFunctionList(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Observable<List<T>> apply(final String networkResponse) throws Exception {

        return Observable.create(new ObservableOnSubscribe<List<T>>() {
            @Override
            public void subscribe(ObservableEmitter<List<T>> subscriber) throws Exception {
                List<T> response = null;
                if (networkResponse != null) {
                    response = ParseUtil.getObjectList(networkResponse, clazz);
                }

                subscriber.onNext(response);
                subscriber.onComplete();
            }
        }).subscribeOn(Schedulers.computation());

    }
}
