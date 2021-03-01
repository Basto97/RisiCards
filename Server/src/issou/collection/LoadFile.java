package issou.collection;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class LoadFile {

    private static final Dotenv dotenv;

    static {
        dotenv = Dotenv.load();
    }

    private static String url(){
        String protocol = dotenv.get("API_SERVER_PROTOCOL");
        String host = dotenv.get("API_SERVER_HOST");
        String port = dotenv.get("API_SERVER_PORT");
        return  protocol + "://" + host + ":"+ port;
    }

    public static JSONObject readJsonFromUrl() throws IOException, JSONException {
        URL url = new URL(url() + '/' + dotenv.get("API_SERVER_API_GAMEDATA"));
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return new JSONObject( response.body().string());
        }
    }
}
