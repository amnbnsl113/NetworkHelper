package network.manage.networkhelper.retrofit;

import java.util.Map;

import io.reactivex.Observable;
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
    Observable<Response<ResponseBody>> get(@Url String url, @HeaderMap Map<String, String> headers);

    @FormUrlEncoded
    @POST
    Observable<Response<ResponseBody>> postWithFormData(@Url String url, @FieldMap Map<String, String> params, @HeaderMap Map<String, String> headers);

    @POST
    Observable<Response<ResponseBody>> post(@Url String url, @Body Object body, @HeaderMap Map<String, String> headers);

}
