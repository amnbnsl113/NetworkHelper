package network.manage.networkhelper.common;

/**
 * Created by aman on 27/12/17.
 */

public class NetworkError {
    private Throwable throwable;

    public NetworkError(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
