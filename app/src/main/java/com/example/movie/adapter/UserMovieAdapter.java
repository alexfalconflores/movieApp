package com.example.movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.model.Movie;
import com.example.movie.model.Rent;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class UserMovieAdapter extends RecyclerView.Adapter<UserMovieViewHolder> implements View.OnClickListener {
    private List<Movie> movies;
    private MaterialButton mbDetail;
    private View.OnClickListener listener;

    public UserMovieAdapter(List<Movie> movies) {
        this.movies = new ArrayList<>();
    }
    public UserMovieAdapter() {
        this.movies = movies;
    }


    @NonNull
    @Override
    public UserMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies_layout, parent, false);
        view.setOnClickListener(this);
        return new UserMovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserMovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.loadData(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

}
