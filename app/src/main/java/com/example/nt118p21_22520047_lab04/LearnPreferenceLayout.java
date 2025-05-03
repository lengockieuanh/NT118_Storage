package com.example.nt118p21_22520047_lab04;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LearnPreferenceLayout extends AppCompatActivity {

    LinearLayout layoutMain;
    Button btnStartSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learnpreferencelayout);

        layoutMain = findViewById(R.id.layoutMain);
        btnStartSetting = findViewById(R.id.btnStartSetting);

        btnStartSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LearnPreferenceLayout.this, Setting.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isChecked = sharedPreferences.getBoolean("background_color", false);

        if (isChecked) {
            layoutMain.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            layoutMain.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        }
    }
}
