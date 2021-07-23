package com.example.movie.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.Serializable;

public class Rent implements Serializable {
    private int id;
    private int idUser;
    private int idMovie;
    private String name;
    private String description;
    private String image;
    private String duration;
    private String movieCode;

    public Rent(int idUser, int idMovie) {
        this.idUser = idUser;
        this.idMovie = idMovie;
    }

    public Rent(int id, int idUser, int idMovie, String name, String description, String image, String duration, String movieCode) {
        this.id = id;
        this.idUser = idUser;
        this.idMovie = idMovie;
        this.name = name;
        this.description = description;
        this.image = image;
        this.duration = duration;
        this.movieCode = movieCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        String base64String = image;
        String base64Image = base64String.split(",")[1];

        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMovieCode() {
        return movieCode;
    }

    public void setMovieCode(String movieCode) {
        this.movieCode = movieCode;
    }

    @Override
    public String toString() {
        return "id: " + id + " idUSer: " + idUser + " IdMovie " + idMovie + " name: " + name + " description: " + description + " duration " + duration + " MovieCode: " + movieCode;
    }
}
