package network.manage.networkhelper.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import network.manage.networkhelper.parser.ParseUtil;

/**
 * Created by aman on 9/1/18.
 */

public class VolleyReqMaker {
    private VolleyQueueManager queueManager;

    VolleyReqMaker(Context context) {
        this.queueManager = new VolleyQueueManager(context);
    }

    Observable<String> fireRequest(final int method, final String url, final Map<String, String> header, final Map<String, String> params, final Object requestBody) {


        final JSONObject jsonObject = ParseUtil.getJSONObject(requestBody);

        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(final SingleEmitter<String> emitter) throws Exception {

                StringRequest strReq = new StringRequest(method,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        emitter.onSuccess(response);
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

                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return jsonObject != null ? jsonObject.toString().getBytes("UTF-8") : super.getBody();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return super.getBody();
                        }
                    }
                };
                queueManager.addToRequestQueue(strReq);
            }
        }).toObservable();
    }

}
