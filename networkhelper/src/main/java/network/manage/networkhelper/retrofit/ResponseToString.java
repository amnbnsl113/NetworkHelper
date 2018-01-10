package network.manage.networkhelper.retrofit;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by aman on 9/1/18.
 */

public class ResponseToString implements Function<Response<ResponseBody>, Observable<String>> {
    @Override
    public Observable<String> apply(final Response<ResponseBody> networkResponse) throws Exception {
        return Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                String str = null;
                ResponseBody body = null;
                try {
                    body = networkResponse.body();
                    if (body != null) {
                        str = body.string();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (body != null) {
                        body.close();
                    }
                }
                emitter.onNext(str);
                emitter.onComplete();
            }
        });
    }

}
