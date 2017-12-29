package network.manage.networkhelper.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        Type listType = new TypeToken<List<T>>() {
        }.getType();
        return gson.fromJson(str, listType);
    }
}