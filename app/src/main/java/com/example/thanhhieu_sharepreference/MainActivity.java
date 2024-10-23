package com.example.thanhhieu_sharepreference;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    EditText name;
    EditText classText;
    CheckBox checkBox;
    RatingBar ratingBar;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initComponents();
        SharedPreferences sharedPref = getSharedPreferences(
                "dataRating", Context.MODE_PRIVATE);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPref.edit();
                if (checkBox.isChecked()){
                    Log.e("CHECKBOX CHECKED!", "HIHI");
                    editor.putString("name", name.getText().toString());
                    editor.putString("classText",  classText.getText().toString());
                    editor.putBoolean("checkBox", checkBox.isChecked());
                    editor.putFloat("ratingBar", ratingBar.getRating());
                }
                else {
                    Log.e("UNCHECKED!", "Heee00Hee");
                    editor.remove("name");
                    editor.remove("classText");
                    editor.remove("checkBox");
                    editor.remove("ratingBar");
                }
                editor.apply();
                loadSavedData(sharedPref);
            }
        });
    }

    private void loadSavedData(SharedPreferences sharedPref) {
        String savedName = sharedPref.getString("name", "");
        String savedClassText = sharedPref.getString("classText", "");
        boolean savedCheckBox = sharedPref.getBoolean("checkBox", false);
        float savedRating = sharedPref.getFloat("ratingBar", 0.0f);
        Log.e("Saved Name", savedName);
        name.setText(savedName);
        classText.setText(savedClassText);
        checkBox.setChecked(savedCheckBox);
        ratingBar.setRating(savedRating);
    }

    private void initComponents() {
        name = findViewById(R.id.editTextName);
        classText = findViewById(R.id.editTextPassword);
        checkBox = findViewById(R.id.checkBox);
        ratingBar = findViewById(R.id.ratingBar);
        saveButton = findViewById(R.id.saveButton);
    }
}