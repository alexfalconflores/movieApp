package com.example.movie.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.R;
import com.example.movie.db.UserDB;

public class CreateAccountActivity extends AppCompatActivity {
    ImageButton ibBackArrow;
    TextView tvTitle, tvSubtitle, tvSignIn;
    EditText etFullName, etPhone, etMail, etPassword;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        final UserDB userDB = new UserDB(getApplicationContext());

        ibBackArrow = findViewById(R.id.ibBackArrow);
        ibBackArrow.setOnClickListener(v -> {
            onBackPressed();
        });

        tvTitle = findViewById(R.id.tvTitle);

        tvSubtitle = findViewById(R.id.tvSubtitle);

        etFullName = findViewById(R.id.etFullName);

        etPhone = findViewById(R.id.etPhone);

        etMail = findViewById(R.id.etMail);

        etPassword = findViewById(R.id.etPassword);

        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(v -> {
            userDB.addUser(etFullName.getText().toString(), etPhone.getText().toString(), etMail.getText().toString(), etPassword.getText().toString());
            Toast.makeText(this, "Se agregó correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

        tvSignIn = findViewById(R.id.tvSignIn);
        tvSignIn.setText(getText(R.string.sign_in));
        tvSignIn.setOnClickListener(v -> {
            onBackPressed();
        });
    }
}