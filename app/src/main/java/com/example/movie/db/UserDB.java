package com.example.movie.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.movie.activities.MainActivity;
import com.example.movie.model.Movie;
import com.example.movie.model.Rent;
import com.example.movie.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB extends SQLiteOpenHelper {
    public static int userLoggedID;
    public static User userLogged;


    private static final String NAME_DB = "movie.db";
    private static final int VERSION_DB = 1;

    private static final String ID = "id";
    private static final String TABLE_USER = "User";
    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" + ID + " INTEGER NOT NULL, fullName TEXT NOT NULL, phoneNumber  TEXT NOT NULL,mail TEXT NOT NULL, password TEXT NOT NULL, PRIMARY KEY(id AUTOINCREMENT))";

    private static final String TABLE_MOVIE = "Movie";
    private static final String CREATE_TABLE_MOVIE = "CREATE TABLE " + TABLE_MOVIE + " (" + ID + " INTEGER NOT NULL, name TEXT NOT NULL, description  TEXT NOT NULL, image TEXT NOT NULL, duration TEXT NOT NULL, movieCode NOT NULL,  PRIMARY KEY(id AUTOINCREMENT))";

    private static final String TABLE_RENT = "Rent";
    private static final String RENT_ID_MOVIE = "idMovie";
    private static final String RENT_ID_USER = "idUser";
    private static final String CREATE_TABLE_RENT = "CREATE TABLE " + TABLE_RENT + " (" + ID + " INTEGER NOT NULL, " + RENT_ID_USER + " INTEGER NOT NULL," + RENT_ID_MOVIE + " INTEGER NOT NULL, FOREIGN KEY (" + RENT_ID_USER + ") REFERENCES " + TABLE_USER + " (" + ID + "), FOREIGN KEY (" + RENT_ID_MOVIE + ") REFERENCES " + TABLE_MOVIE + " (" + ID + "), PRIMARY KEY (id AUTOINCREMENT))";


    public UserDB(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_MOVIE);
        db.execSQL(CREATE_TABLE_RENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_RENT);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_MOVIE);
        db.execSQL(CREATE_TABLE_RENT);
    }

    public boolean authUser(String mail, String password) {
        Cursor cursor = authUser2(mail, password);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }


    public Cursor authUser2(String mail, String password) throws SQLException {
        Cursor cursor = null;
        cursor = this.getReadableDatabase().query(TABLE_USER, null, "mail like '" + mail + "' " + "and password like '" + password + "'", null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                userLogged = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
//                userLoggedID = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return cursor;
    }

    public void addUser(String fullName, String phoneNumber, String mail, String password) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO " + TABLE_USER + " (fullName,phoneNumber,mail,password) VALUES('" + fullName + "', '" + phoneNumber + "', '" + mail + "', '" + password + "')");
            db.close();
        }
    }

//    public String list() {
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + "", null);
//        List<User> users = new ArrayList<>();
//        if (cursor.moveToFirst()) {
//            do {
//                users.add(new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
//            } while (cursor.moveToNext());
//        }
//
//        String result = "";
//        for (User u : users
//        ) {
//            result += u.toString() + "\n";
//        }
//        return result;
//    }
//    /////////////

    public void addMovie(String name, String description, String image, String duration, String movieCode) throws SQLException {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO " + TABLE_MOVIE + " (name,description,image,duration,movieCode) VALUES('" + name + "', '" + description + "', '" + image + "', '" + duration + "','" + movieCode + "')");
            db.close();
        }
    }

    public List<Movie> listMovies() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MOVIE + "", null);
        List<Movie> movies = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                movies.add(new Movie(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
            } while (cursor.moveToNext());
        }
        return movies;
    }

//    ///////////

    public void addRent(int idUser, int idMovie) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO " + TABLE_RENT + " ( " + RENT_ID_USER + ", " + RENT_ID_MOVIE + ") VALUES ( " + idUser + ", " + idMovie + ")");
            db.close();
        }
    }

    public void deleteRent(int idUser, int idMovie) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_RENT + " WHERE " + RENT_ID_USER + "=" + idUser + " AND " + RENT_ID_MOVIE + "=" + idMovie);
        db.close();
    }


    public List<Rent> listRent(int idUser) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TABLE_RENT + "." + ID + ", " + TABLE_RENT + "." + RENT_ID_USER + ","
                + TABLE_RENT + "." + RENT_ID_MOVIE + ", " + TABLE_MOVIE + "." + "name" + ", " + TABLE_MOVIE + "." + "description" + ", " + TABLE_MOVIE + "." + "image" + ", " + TABLE_MOVIE + "." + "duration" + ", " + TABLE_MOVIE + "." + "movieCode"
                + " FROM " + TABLE_MOVIE + " INNER JOIN " + TABLE_RENT + " ON " + TABLE_MOVIE + "." + ID + " = " + TABLE_RENT + "." + RENT_ID_MOVIE
                + " INNER JOIN " + TABLE_USER + " ON " + TABLE_USER + "." + ID + " = " + TABLE_RENT + "." + RENT_ID_USER
                + " WHERE " + TABLE_USER + "." + ID
                + " LIKE '" + idUser + "'", null);
        List<Rent> rents = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                rents.add(new Rent(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)));
            } while (cursor.moveToNext());
        }
        return rents;
    }

    public String list(int idUser) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + TABLE_RENT + "." + ID + ", " + TABLE_RENT + "." + RENT_ID_USER + ","
                + TABLE_RENT + "." + RENT_ID_MOVIE + ", " + TABLE_MOVIE + "." + "name" + ", " + TABLE_MOVIE + "." + "description" + ", " + TABLE_MOVIE + "." + "image" + ", " + TABLE_MOVIE + "." + "duration" + ", " + TABLE_MOVIE + "." + "movieCode"
                + " FROM " + TABLE_MOVIE + " INNER JOIN " + TABLE_RENT + " ON " + TABLE_MOVIE + "." + ID + " = " + TABLE_RENT + "." + RENT_ID_MOVIE
                + " INNER JOIN " + TABLE_USER + " ON " + TABLE_USER + "." + ID + " = " + TABLE_RENT + "." + RENT_ID_USER
                + " WHERE " + TABLE_USER + "." + ID
                + " LIKE '" + idUser + "'", null);
        List<Rent> rents = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                rents.add(new Rent(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7)));
            } while (cursor.moveToNext());
        }

        String result = "";
        for (Rent r : rents
        ) {
            result += r.toString() + "\n";
        }
        return result;
    }
}
