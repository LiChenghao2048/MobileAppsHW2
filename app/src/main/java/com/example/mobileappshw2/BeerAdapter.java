package com.example.mobileappshw2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cz.msebera.android.httpclient.client.cache.Resource;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.ViewHolder> {

    private List<Beer> beers;

    public BeerAdapter(List<Beer> beers) {
        this.beers = beers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View beerView = inflater.inflate(R.layout.item_beer, parent, false);
        ViewHolder viewHolder = new ViewHolder(beerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Beer beer = beers.get(position);
        holder.textView_name.setText(beer.getName());
        holder.textView_description.setText(beer.getDescription());
        Picasso.get().load(beer.getImage_url()).into(holder.imageView_beer);

        if (beer.isLike()) {
            Picasso.get().load("https://png.pngtree.com/png-vector/20190223/ourlarge/pngtree-vector-like-icon-png-image_695769.jpg").into(holder.imageView_like);
        } else {
            Picasso.get().load("https://cdn.iconscout.com/icon/free/png-256/like-1767386-1505250.png").into(holder.imageView_like);
        }

        holder.imageView_like.setOnClickListener(v -> {
            if (beer.isLike()) {
                beer.setLike(false);
            } else {
                beer.setLike(true);
            }
            this.notifyItemChanged(position);
        });

        holder.imageView_beer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ForthActivity.class);
                intent.putExtra("id", beer.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_name;
        TextView textView_description;
        ImageView imageView_beer;
        ImageView imageView_like;

        public ViewHolder (View itemView) {
            super(itemView);

            textView_name = itemView.findViewById(R.id.textView_name);
            textView_description = itemView.findViewById(R.id.textView_description);
            imageView_beer = itemView.findViewById(R.id.imageView_beer);
            imageView_like = itemView.findViewById(R.id.imageView_like);

        }

    }

}
