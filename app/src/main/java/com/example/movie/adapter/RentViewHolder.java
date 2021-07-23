package com.example.movie.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.R;
import com.example.movie.model.Rent;

public class RentViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivLogoMovie;
    private TextView tvNameMovie, tvDuration, tvDescription;

    public RentViewHolder(@NonNull View itemView) {
        super(itemView);

        ivLogoMovie = itemView.findViewById(R.id.ivLogoMovie);
        tvNameMovie = itemView.findViewById(R.id.tvNameMovie);
        tvDuration = itemView.findViewById(R.id.tvDuration);
        tvDescription = itemView.findViewById(R.id.tvDescription);
    }

    public void loadData(Rent rent){
        ivLogoMovie.setImageBitmap(rent.getImage());
        tvNameMovie.setText(rent.getName());
        tvDuration.setText(rent.getDuration());
        tvDescription.setText(rent.getDescription());
    }
}
