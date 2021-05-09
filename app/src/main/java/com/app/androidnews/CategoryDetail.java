package com.app.androidnews;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.androidnews.adapter.MyAdapter;
import com.app.androidnews.retrofitconfig.GetJsonAll;
import com.app.androidnews.retrofitconfig.RetrofitConfigToJson;
import com.app.androidnews.retrofitjson.News;
import com.app.androidnews.retrofitjson.NewsList;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDetail extends AppCompatActivity {

    RecyclerView category_detail;
    String business;
    GridLayoutManager glm;
    MyAdapter adapter;

    GetJsonAll getJsonAll;
    List<News> news;
    String title;
    SpinKitView spinKitView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_detail);

        spinKitView = findViewById(R.id.spin_kit);
        category_detail = findViewById(R.id.rv_detail);
        glm = new GridLayoutManager(this, 1);
        category_detail.setLayoutManager(glm);
        business = getIntent().getStringExtra("business");
        news = new ArrayList<>();
        adapter= new MyAdapter(this, news);

        getJsonAll = RetrofitConfigToJson.getResponse();

        switch (business){

            case "business":
                tampilkanCategory("business");
                break;
            case "entertainment":
                tampilkanCategory("entertainment");
                break;
            case "health":
                tampilkanCategory("health");
                break;
            case "science":
                tampilkanCategory("science");
                break;
            case "sports":
                tampilkanCategory("sports");
                break;
            case "technology":
                tampilkanCategory("technology");
                //category_detail.setText(business);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Kategori Tidak Ada", Toast.LENGTH_LONG).show();
                break;
        }

    }

    private void tampilkanCategory(String category){

        getJsonAll.getNewsListCategory("id", category, "ac4489d7bdff4efb82d08313579b0b76").enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Log.d("Berhasil", response + "");

                spinKitView.setVisibility(View.VISIBLE);
                if (response.isSuccessful()){

                    spinKitView.setVisibility(View.GONE);
                    news = response.body().getArticles();
                    title = news.get(0).getTitle();

                    Log.d("myTitle", title + "");
                    adapter = new MyAdapter(getApplicationContext(), news);
                    category_detail.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("Gagal", t + "");
            }
        });

    }

}
