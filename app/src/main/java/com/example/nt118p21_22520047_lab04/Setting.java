package com.example.nt118p21_22520047_lab04;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.widget.Button;

public class Setting extends AppCompatActivity {

    Button btnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btnStatus = findViewById(R.id.btnStatus);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.setting_container, new Setting.MyPreferenceFragment())
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtonStatus();
    }

    private void updateButtonStatus() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isChecked = sharedPreferences.getBoolean("background_color", false);

        if (isChecked) {
            btnStatus.setText("checked = true");
        } else {
            btnStatus.setText("checked = false");
        }
    }

    public static class MyPreferenceFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preferences, rootKey);

            CheckBoxPreference checkboxPreference = findPreference("background_color");

            if (checkboxPreference != null) {
                checkboxPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                    boolean isChecked = (Boolean) newValue;

                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("background_color", isChecked);
                    editor.apply();

                    return true;
                });
            }
        }
    }
}
