package network.manage.networkhelper.util;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by aman on 9/1/18.
 */

public class MySchedulers {
    public static Scheduler getScheduler(boolean synchronous) {
        if (synchronous) {
            return Schedulers.trampoline();
        } else {
            return AndroidSchedulers.mainThread();
        }
    }
}
