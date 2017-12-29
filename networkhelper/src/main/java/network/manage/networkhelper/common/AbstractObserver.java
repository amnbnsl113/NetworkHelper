package network.manage.networkhelper.common;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by aman on 27/12/17.
 */

public abstract class AbstractObserver<T> implements Observer<T> {


    public abstract void onSuccess(T t);

    public abstract void onFailure(NetworkError error);


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailure(new NetworkError(e));
    }

    @Override
    public void onComplete() {

    }
}
