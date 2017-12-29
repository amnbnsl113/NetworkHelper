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

public class ParseFunction<T> implements Function<Response<ResponseBody>, Observable<T>> {

    private Class<T> clazz;

    public ParseFunction(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Observable<T> apply(final Response<ResponseBody> networkResponse) throws Exception {

        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                T response = null;
                ResponseBody body = null;
                try {
                    body = networkResponse.body();
                    if (body != null) {
                        String str = body.string();
                        response = ParseUtil.getObject(str, clazz);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (body != null) {
                        body.close();
                    }
                }
                subscriber.onNext(response);
                subscriber.onComplete();
            }
        }).subscribeOn(Schedulers.computation());

    }
}
