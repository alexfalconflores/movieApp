package com.example.movie.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.model.Movie;

import java.nio.charset.StandardCharsets;


public class MovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivLogoMovie;
    private TextView tvNameMovie;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        ivLogoMovie = itemView.findViewById(R.id.ivLogoMovie);
        tvNameMovie = itemView.findViewById(R.id.tvNameMovie);
    }

    public void loadData(Movie movie) {
        ivLogoMovie.setImageBitmap(movie.getImage());
        tvNameMovie.setText(movie.getName());
    }
}
