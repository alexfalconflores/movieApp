package com.example.movie.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.movie.R;
import com.example.movie.activities.DetailActivity;
import com.example.movie.adapter.MovieAdapter;
import com.example.movie.db.UserDB;
import com.example.movie.model.Movie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {
    boolean clickGridOrList = true;

    ImageButton ibGridOrList;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserDB movieDB = new UserDB(getActivity());


        RecyclerView rvMovies = view.findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        View containerParent = (View) view.getParent();
        containerParent.setPadding(0, 0, 0, 200);
        List<Movie> movies = new ArrayList<>(movieDB.listMovies());

        MovieAdapter adapter = new MovieAdapter(movies);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        rvMovies.setLayoutManager(gridLayoutManager);

        ibGridOrList = view.findViewById(R.id.ibGridOrList);
        ibGridOrList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              Grid
                if (clickGridOrList) {
                    clickGridOrList = false;
                    ibGridOrList.setImageResource(R.drawable.ic_grid_view);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
                    rvMovies.setLayoutManager(linearLayoutManager);
                    rvMovies.setAdapter(adapter);
                    layoutAnimation(rvMovies);
                }
//              List
                else {
                    clickGridOrList = true;
                    ibGridOrList.setImageResource(R.drawable.ic_list_view);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                    rvMovies.setLayoutManager(gridLayoutManager);
                    rvMovies.setAdapter(adapter);
                    layoutAnimation(rvMovies);
                }
            }


        });

        adapter.setOnClickListener(v -> {
//            Toast.makeText(getContext(), movies.get(rvMovies.getChildAdapterPosition(v)).getName(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getContext(), DetailActivity.class);
            Movie movie = movies.get(rvMovies.getChildAdapterPosition(v));
            intent.putExtra("movie", movie);
            intent.putExtra("name", movies.get(rvMovies.getChildAdapterPosition(v)).getName().toString());
            intent.putExtra("description", movies.get(rvMovies.getChildAdapterPosition(v)).getDescription().toString());
//            intent.putExtra("image", movies.get(rvMovies.getChildAdapterPosition(v)).getImage());
            intent.putExtra("duration", movies.get(rvMovies.getChildAdapterPosition(v)).getDuration());
            startActivity(intent);
        });
        rvMovies.setAdapter(adapter);
        layoutAnimation(rvMovies);

    }

    private void layoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_left);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

}