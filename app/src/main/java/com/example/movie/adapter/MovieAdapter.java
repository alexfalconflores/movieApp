package com.example.movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.model.Movie;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> implements View.OnClickListener {

    private List<Movie> movies;
    private MaterialButton mbDetail;
    private View.OnClickListener listener;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_movies_layout, parent, false);
        view.setOnClickListener(this);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.loadData(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
