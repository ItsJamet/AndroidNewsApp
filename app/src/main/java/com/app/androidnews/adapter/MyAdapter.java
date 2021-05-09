package com.app.androidnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.androidnews.DetailBerita;
import com.app.androidnews.R;
import com.app.androidnews.retrofitjson.News;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<News> newsList;

    public MyAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    class MyAdapterClass extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView title;
        private ImageLoader imageLoader;
        private LinearLayout click;

        public MyAdapterClass(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.megumin);
            title = itemView.findViewById(R.id.title);
            imageLoader = ImageLoader.getInstance();
            click = itemView.findViewById(R.id.click);

        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_news, viewGroup, false);

        return new MyAdapterClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        ((MyAdapterClass) viewHolder).title.setText(newsList.get(position).getTitle());
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .build();

        ((MyAdapterClass) viewHolder).imageLoader.init(configuration);
        ((MyAdapterClass) viewHolder).imageLoader.displayImage(newsList.get(position).getUrlToImage(), ((MyAdapterClass) viewHolder).img);
        ((MyAdapterClass) viewHolder).click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Anda Klik" + newsList.get(position).getTitle(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, DetailBerita.class);
                intent.putExtra("title", newsList.get(position).getTitle());
                intent.putExtra("urlToImage", newsList.get(position).getUrlToImage());
                intent.putExtra("content", newsList.get(position).getContent());
                intent.putExtra("url", newsList.get(position).getUrl());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
