package com.demoji.rafa.demoji;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by rafa on 7/23/16.
 */
public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder> {
    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.pic);
        }
    }

    ArrayList<Picture> picId;
    Context context;

    public ImageRecyclerAdapter(ArrayList<Picture> picId, Context context) {
        this.context = context;
        this.picId = picId;

        Log.d("Initialized Adapter", "array adapter");
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pic_grid_item, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        String picUrl = "";
        String farm = picId.get(position).getFarm();
        String id = picId.get(position).getId();
        String secret = picId.get(position).getSecret();
        String server = picId.get(position).getServer();
        picUrl = "https://farm" + farm +".staticflickr.com/"
               + server +"/" + id + "_" + secret +"_z.jpg";

        Log.d("Pic Url", picUrl);
        Picasso.with(context).load(picUrl).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return picId.size();
    }
}
