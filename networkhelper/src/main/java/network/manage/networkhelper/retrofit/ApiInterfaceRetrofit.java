package network.manage.networkhelper.retrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import network.manage.networkhelper.model.BaseRequest;
import network.manage.networkhelper.model.BaseResponse;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by aman on 27/12/17.
 */

public interface ApiInterfaceRetrofit {

    @GET
    Observable<Response<ResponseBody>> getList(@Url String url);

    @GET
    Observable<Response<ResponseBody>> get(@Url String url);

    @FormUrlEncoded
    @POST
    Observable<Response<ResponseBody>> postWithFormData(@Url String url, @FieldMap Map<String, Object> params);

    @POST
    Observable<Response<ResponseBody>> post(@Url String url, @Body BaseRequest body);

}
