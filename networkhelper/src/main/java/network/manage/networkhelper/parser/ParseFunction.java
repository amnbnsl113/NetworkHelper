package network.manage.networkhelper.parser;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by aman on 28/12/17.
 */

public class ParseFunction<T> implements Function<String, Observable<T>> {

    private Class<T> clazz;

    public ParseFunction(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Observable<T> apply(final String networkResponse) throws Exception {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                T response = ParseUtil.getObject(networkResponse, clazz);

                subscriber.onNext(response);
                subscriber.onComplete();
            }
        }).subscribeOn(Schedulers.computation());

    }
}
