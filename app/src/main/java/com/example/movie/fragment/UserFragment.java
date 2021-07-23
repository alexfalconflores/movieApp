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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.R;
import com.example.movie.activities.MainActivity;
import com.example.movie.adapter.RentAdapter;
import com.example.movie.adapter.UserMovieAdapter;
import com.example.movie.db.UserDB;
import com.example.movie.model.Rent;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {

//    private String userId;

    private TextView tvUserName, tvWatchingMovie;
    private Button btnSignOff;
//    RecyclerView rvMoviesList;


    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserDB rentDB = new UserDB(getActivity());

        RecyclerView rvMoviesList = view.findViewById(R.id.rvMoviesList);
        rvMoviesList.setLayoutManager(new LinearLayoutManager(getContext()));
        View containerParent = (View) view.getParent();
        containerParent.setPadding(0, 0, 0, 200);
        String id = rentDB.userLogged.toString();
        List<Rent> rents = new ArrayList<>(rentDB.listRent(Integer.parseInt(id)));
        String count = String.valueOf(rents.size());

        RentAdapter adapter = new RentAdapter(rents);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
        rvMoviesList.setLayoutManager(gridLayoutManager);

        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserName.setText(rentDB.userLogged.getFullName());

        tvWatchingMovie = view.findViewById(R.id.tvWatchingMovie);
        tvWatchingMovie.setText(count + " Películas");

        btnSignOff = view.findViewById(R.id.btnSignOff);
        btnSignOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        rvMoviesList.setAdapter(adapter);
        layoutAnimation(rvMoviesList);

//        RentRecycleView(rentDB.listRent(UserDB.userLogged.getId()));

//        UserMovieAdapter adapter = new UserMovieAdapter()


    }

//    private void RentRecycleView(List<Rent> rents) {
//        RentAdapter adapter = new RentAdapter(rents);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);

//        rvMoviesList.setLayoutManager(gridLayoutManager);
//        rvMoviesList.setAdapter(adapter);

//        adapter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), rents.get(rvMoviesList.getChildAdapterPosition(v)).getName(), Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getContext(), VerPelicula.class);
//                Pelicula p = peliculas.get(rcvPeliculasUsuario.getChildAdapterPosition(v));
//                intent.putExtra("pelicula", p);
//                intent.putExtra("codigo", p.getCodigoPelicula());
//                startActivity(intent);
//
//            }
//        });
//    }

    private void layoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_slide_left);
        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

}