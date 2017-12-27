package network.manage.networkhelper.retrofit;

import java.util.List;

import io.reactivex.Observable;
import network.manage.networkhelper.model.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by aman on 27/12/17.
 */

public interface ApiInterfaceRetrofit {

    @GET
    Observable<List<BaseResponse>> getList(@Url String url);

    @GET
    Observable<BaseResponse> get(@Url String url);

}
