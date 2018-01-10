package network.manage.networkhelper.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by aman on 28/12/17.
 */

public class ParseUtil {
    private static Gson gson = new Gson();

    public static <T> T getObject(String str, Class<T> clazz) {
        return gson.fromJson(str, clazz);
    }

    public static <T> List<T> getObjectList(String str, Class<T> clazz) {
        Type listType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(str, listType);
    }

    public static JSONObject getJSONObject(Object object) {
        try {
            return new JSONObject(gson.toJson(object));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
