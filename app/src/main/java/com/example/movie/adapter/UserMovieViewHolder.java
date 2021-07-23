package com.example.movie.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.model.Movie;

public class UserMovieViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivLogoMovie;
    private TextView tvNameMovie, tvDuration, tvDescription;

    public UserMovieViewHolder(@NonNull View itemView) {
        super(itemView);

        ivLogoMovie = itemView.findViewById(R.id.ivLogoMovie);
        tvNameMovie = itemView.findViewById(R.id.tvNameMovie);
        tvDuration = itemView.findViewById(R.id.tvDuration);
        tvDescription = itemView.findViewById(R.id.tvDescription);
    }

    public void loadData(Movie movie) {
        ivLogoMovie.setImageBitmap(movie.getImage());
        tvNameMovie.setText(movie.getName());
        tvDuration.setText(movie.getDuration());
        tvDescription.setText(movie.getDescription());
    }
}
