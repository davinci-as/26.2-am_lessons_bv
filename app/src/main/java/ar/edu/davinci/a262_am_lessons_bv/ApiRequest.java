package ar.edu.davinci.a262_am_lessons_bv;

import android.os.AsyncTask;
import android.util.Log;

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
        //actualizo ui
        try {
            JSONObject response =  new JSONObject(stringResponse);
            JSONArray results = (JSONArray) response.get("results");
            JSONObject rickSanchez = (JSONObject) results.get(11);
            String rickName = rickSanchez.get("name").toString();
            Log.i("api-request", rickName);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
