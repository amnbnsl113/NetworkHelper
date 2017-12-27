package network.manage.networkhelper.retrofit;

import android.os.Environment;
import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aman on 27/12/17.
 */

public class RetrofitAdapter {


    private final static String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final static int DEFAULT_READ_TIMEOUT = 90; //90 Seconds

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    public static final int CONNECT_TIME_OUT = 30;
    public static final int KEEP_ALIVE_DURATION = 3000;
    public static final int MAX_IDLE_CONNECTIONS = 4;
    public static final int NETWORK_READ_TIME_OUT = 30;

    public static Retrofit getRetrofit(String baseUrl) {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request());
            }
        };
        builder.addNetworkInterceptor(interceptor);
        builder.connectionPool(new okhttp3.ConnectionPool(CORE_POOL_SIZE, KEEP_ALIVE_DURATION, TimeUnit.MILLISECONDS));
        builder.readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        builder.cache(new Cache(Environment.getDownloadCacheDirectory(), 1024 * 2 * 1024)); // 2MB Cache size
        builder.dispatcher(new Dispatcher(Executors.newFixedThreadPool(MAX_IDLE_CONNECTIONS)));
        builder.cache(new Cache(Environment.getDataDirectory(), 1024 * 5));

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
        OkHttpClient client = builder.build();

        return new Retrofit.Builder()
                .baseUrl(!TextUtils.isEmpty(baseUrl) ? baseUrl : BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).client(client).build();
    }

}
