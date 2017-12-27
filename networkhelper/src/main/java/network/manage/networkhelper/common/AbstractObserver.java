package network.manage.networkhelper.common;

import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import network.manage.networkhelper.Callback;
import network.manage.networkhelper.model.BaseResponse;

/**
 * Created by aman on 27/12/17.
 */

public class AbstractObserver<T> implements Observer<T> {
    private WeakReference<Callback<T>> weakReference;

    public AbstractObserver(WeakReference<Callback<T>> weakReference) {
        this.weakReference = weakReference;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        if (weakReference != null) {
            Callback<T> callback = weakReference.get();
            if (callback != null)
                callback.onSuccess(t);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (weakReference != null) {
            Callback<T> callback = weakReference.get();
            if (callback != null)
                callback.onFailure(new NetworkError(e));
        }
    }

    @Override
    public void onComplete() {

    }
}
