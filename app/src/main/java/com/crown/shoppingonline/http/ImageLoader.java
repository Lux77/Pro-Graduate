package com.crown.shoppingonline.http;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Crown on 2016/4/23.
 */
public class ImageLoader extends AsyncTask<String, Void, Bitmap> {
    private ImageView mImageView;

    public ImageLoader(ImageView imageView) {
        this.mImageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5*1000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            InputStream is = connection.getInputStream();
            return BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap != null)
            mImageView.setImageBitmap(bitmap);
    }
}
