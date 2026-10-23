package ar.edu.davinci.a262_am_lessons_bv;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiRequest extends AsyncTask<String, Integer, String> {
    private OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        String response = null;
        try {
            response = this.run(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //query
        return response;
    }

    @Override
    protected void onPostExecute(String stringResponse) {
        super.onPostExecute(stringResponse);
        if(stringResponse == null) return;
        Gson gson = new Gson();
        ApiResponse response = gson.fromJson(stringResponse, ApiResponse.class);
        //actualizo ui
        Character character = response.getResults().get(18);
        String name = character.getName();
        String gender = character.getGender();
        Log.i("api-request", String.format("%s (%s)", name, gender));
    }
}
