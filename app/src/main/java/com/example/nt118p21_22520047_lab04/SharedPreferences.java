package com.example.nt118p21_22520047_lab04;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferences extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    CheckBox checkboxRemember;
    Button btnLogin;
    android.content.SharedPreferences sharedPreferences;
    public static final String PREFS_NAME = "dataLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferences);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        checkboxRemember = findViewById(R.id.checkboxRemember);
        btnLogin = findViewById(R.id.btnLogin);

        //Lấy SharePreferences
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        // Load dữ liệu đã lưu (nếu có)
        boolean isChecked = sharedPreferences.getBoolean("checked", false);
        if (isChecked) {
            String username = sharedPreferences.getString("username", "");
            String password = sharedPreferences.getString("password", "");

            edtUsername.setText(username);
            edtPassword.setText(password);
            checkboxRemember.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUsername.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();

                if (user.equals("kieuanh") && pass.equals("1234")) {

                    Toast.makeText(SharedPreferences.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    // nếu tick nhớ đăng nhập thì lưu vào SharedPreferences
                    if (checkboxRemember.isChecked()) {
                        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", user);
                        editor.putString("password", pass);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    } else {
                        // không nhớ thì xóa dữ liệu đã lưu
                        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("username");
                        editor.remove("password");
                        editor.remove("checked");
                        editor.commit();
                    }

                } else {
                    Toast.makeText(SharedPreferences.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
