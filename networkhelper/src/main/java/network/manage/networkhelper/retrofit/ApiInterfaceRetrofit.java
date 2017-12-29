package network.manage.networkhelper.retrofit;

import java.util.List;

import io.reactivex.Observable;
import network.manage.networkhelper.model.BaseResponse;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by aman on 27/12/17.
 */

public interface ApiInterfaceRetrofit {

    @GET
    Observable<Response<ResponseBody>> getList(@Url String url);

    @GET
    Observable<Response<ResponseBody>> get(@Url String url);

}
