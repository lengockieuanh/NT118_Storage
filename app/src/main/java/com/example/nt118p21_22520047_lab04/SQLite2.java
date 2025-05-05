package com.example.nt118p21_22520047_lab04;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class SQLite2 extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private UserDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite2);

        // Khởi tạo các đối tượng UI
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Khởi tạo DatabaseHelper
        dbHelper = new UserDatabaseHelper(this);

        // Thêm người dùng thử nghiệm (nếu chưa có)
        dbHelper.addUser("kieuanh", "1234");

        // Cài đặt sự kiện click cho nút đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();

                // Kiểm tra xem các trường nhập có trống không
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SQLite2.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra đăng nhập
                if (dbHelper.checkLogin(username, password)) {
                    Toast.makeText(SQLite2.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    // Có thể chuyển tới màn hình chính hoặc thực hiện các hành động khác
                } else {
                    Toast.makeText(SQLite2.this, "Thông tin đăng nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
