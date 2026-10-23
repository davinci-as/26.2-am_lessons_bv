package ar.edu.davinci.a262_am_lessons_bv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {
    ImageView image;

    public ImageDownloader(ImageView image) {
        this.image = image;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String urlDownload = strings[0];
        try {
            URL query = new URL(urlDownload);
            InputStream content = (InputStream) query.getContent();
            return BitmapFactory.decodeStream(content);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        if(bitmap == null) return;
        this.image.setImageBitmap(bitmap);
    }
}
