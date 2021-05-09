package com.app.androidnews;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DetailBerita extends AppCompatActivity {

    String title, image, content, url;
    TextView TVtitle;
    ImageView img;
    WebView myWeb;
    ImageLoaderConfiguration configuration;
    ImageLoader loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_berita);

        TVtitle = findViewById(R.id.TVtitle);
        img = findViewById(R.id.IV);
        myWeb = findViewById(R.id.my_web);

        title = getIntent().getStringExtra("title");
        image = getIntent().getStringExtra("urlToImage");
        content = getIntent().getStringExtra("content");
        loader = ImageLoader.getInstance();
        url = getIntent().getStringExtra("url");

        /*Log.d("Judul", title);
        Toast.makeText(getApplicationContext(), "Anda Klik " + title, Toast.LENGTH_LONG).show();
        TVtitle.setText(title);
        configuration = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .build();
        loader.init(configuration);
        loader.displayImage(image, img);
        myWeb.loadData(content, "text/html", "utf-8");
        myWeb.setWebViewClient(new WebViewClient());*/

        myWeb.loadUrl(url);
        myWeb.setWebViewClient(new WebViewClient());

    }

}
