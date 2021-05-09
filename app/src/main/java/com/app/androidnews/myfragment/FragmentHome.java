package com.app.androidnews.myfragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.androidnews.R;
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

public class FragmentHome extends Fragment {

    GetJsonAll getJsonAll;
    List<News> newsList;
    String title, description;
    RecyclerView rView;
    MyAdapter myAdapter;
    GridLayoutManager glm;

    View view;
    SpinKitView spinKitView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        spinKitView = view.findViewById(R.id.spin_kit);

        rView = view.findViewById(R.id.rView);
        glm = new GridLayoutManager(getContext(), 1);
        rView.setLayoutManager(glm);
        newsList = new ArrayList<>();
        myAdapter = new MyAdapter(getContext(),newsList);
        rView.setAdapter(myAdapter);


        getJsonAll = RetrofitConfigToJson.getResponse();

        getJsonAll.getNewsList("id", "ac4489d7bdff4efb82d08313579b0b76").enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Log.d("Berhasil", response + "");

                spinKitView.setVisibility(View.VISIBLE);
                if (response.isSuccessful()){

                    spinKitView.setVisibility(View.GONE);

                    newsList = response.body().getArticles();

                    title = newsList.get(0).getTitle();
                    title = newsList.get(0).getDescription();
                    Log.d("Title Berita", " Judul: " + title + " " + "Descripsi: " + description);

                    myAdapter = new MyAdapter(getContext(), newsList);
                    rView.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("Gagal", t + "");
            }
        });

        return view;

    }

}
