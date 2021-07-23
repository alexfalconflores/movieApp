package com.example.movie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.model.Rent;

import java.util.ArrayList;
import java.util.List;

public class RentAdapter extends RecyclerView.Adapter<RentViewHolder> implements View.OnClickListener {
    private List<Rent> rents;
    private View.OnClickListener listener;

    public RentAdapter() {
        this.rents = new ArrayList<>();
    }

    public RentAdapter(List<Rent> rents) {
        this.rents = rents;
    }


    @NonNull
    @Override
    public RentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_movies_layout, parent, false);
        view.setOnClickListener(this);
        return new RentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RentViewHolder holder, int position) {
        Rent rent = rents.get(position);
        holder.loadData(rent);
    }

    @Override
    public int getItemCount() {
        return rents.size();
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
