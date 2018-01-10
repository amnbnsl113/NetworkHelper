package network.manage.networkhelper.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import network.manage.networkhelper.parser.ParseUtil;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

/**
 * Created by aman on 9/1/18.
 */

public class VolleyReqMaker {
    private VolleyQueueManager queueManager;

    VolleyReqMaker(Context context) {
        this.queueManager = new VolleyQueueManager(context);
    }

    Single<String> fireRequest(final int method, final String url, final Map<String, String> header, final Map<String, String> params, final Object requestBody) {


        final JSONObject jsonObject = ParseUtil.getJSONObject(requestBody);

        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(final SingleEmitter<String> emitter) throws Exception {

                JsonObjectRequest strReq = new JsonObjectRequest(method,
                        url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        emitter.onSuccess(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        emitter.onError(error.getCause());
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        return header == null ? super.getHeaders() : header;
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return params == null ? super.getParams() : params;
                    }
                };
                queueManager.addToRequestQueue(strReq);
            }
        });
    }

}