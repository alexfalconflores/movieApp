package com.example.movie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movie.R;
import com.example.movie.db.UserDB;
import com.example.movie.model.Movie;
import com.example.movie.model.Rent;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;

import static com.example.movie.db.UserDB.userLogged;

public class DetailActivity extends AppCompatActivity {

    private TextView tvTitleMovie, tvDescription, tvDuration;
    private ImageView ivLogoMovie;
    private Button btnRent, btnReturn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        UserDB rentDB = new UserDB(getApplicationContext());
        String id = userLogged.toString();

        tvTitleMovie = findViewById(R.id.tvTitleMovie);
        tvDescription = findViewById(R.id.tvDescription);
        ivLogoMovie = findViewById(R.id.ivLogoMovie);
        tvDuration = findViewById(R.id.tvDuration);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        tvDuration.setText(getIntent().getSerializableExtra("duration").toString());
        tvTitleMovie.setText(getIntent().getSerializableExtra("name").toString() + "   |");
        tvDescription.setText(getIntent().getSerializableExtra("description").toString());
        String name = getIntent().getSerializableExtra("name").toString();
        switch (name) {
            case "Akira":
                ivLogoMovie.setImageResource(R.drawable.akira);
                break;
            case "American Beauty":
                ivLogoMovie.setImageResource(R.drawable.american_beauty);

                break;
            case "jurassic Park":
                ivLogoMovie.setImageResource(R.drawable.jurassic_park);
                break;
            case "Chihiro":
                ivLogoMovie.setImageResource(R.drawable.chihiro);
                break;
            case "Mi Vecino Totoro":
                ivLogoMovie.setImageResource(R.drawable.mi_vecino_totoro);
                break;
            case "La forma del agua":
                ivLogoMovie.setImageResource(R.drawable.la_forma_del_agua);
                break;
            default:
                ivLogoMovie.setImageResource(R.drawable.jisso);
                break;
        }

        btnRent = findViewById(R.id.btnRent);
        btnRent.setOnClickListener(v -> {
            rentDB.addRent(Integer.parseInt(id), movie.getId());
            onBackPressed();
        });

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            rentDB.deleteRent(Integer.parseInt(id), movie.getId());
            onBackPressed();
        });
    }
}