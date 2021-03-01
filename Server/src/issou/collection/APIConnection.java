package issou.collection;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class APIConnection {

    private static final Dotenv dotenv = Dotenv.load();

    private static String url(){
        String protocol = dotenv.get("API_SERVER_PROTOCOL");
        String host = dotenv.get("API_SERVER_HOST");
        String port = dotenv.get("API_SERVER_PORT");
        return  protocol + "://" + host + ":"+ port;
    }

    public static JSONObject getData() {
        try {
            URL url = new URL(url() + '/' + dotenv.get("API_SERVER_CMD_DATA"));
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            return new JSONObject( response.body().string());
        }  catch (JSONException | IOException e){
            e.printStackTrace();
            return new JSONObject();
        }
    }

    public static boolean getRefresh(){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url() + '/' + dotenv.get("API_SERVER_CMD_COMMANDS"))
                .addHeader("Authorization", Credentials.basic(dotenv.get("API_SERVER_USERNAME"), dotenv.get("API_SERVER_PASSWORD")))
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return new JSONObject(response.body().string()).getBoolean("refresh");
        } catch (JSONException | IOException e){
            e.printStackTrace();
            return false;
        }
    }
}
