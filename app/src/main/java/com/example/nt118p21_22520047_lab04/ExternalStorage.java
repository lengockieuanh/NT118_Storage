package com.example.nt118p21_22520047_lab04;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalStorage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);

        EditText edtInput = findViewById(R.id.edtInput);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnLoad = findViewById(R.id.btnLoad);
        Button btnShare = findViewById(R.id.btnShare);
        TextView txtResult = findViewById(R.id.txtResult);

        // Kiểm tra xem bộ nhớ ngoài có sẵn hay không
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "External Storage không sẵn sàng", Toast.LENGTH_SHORT).show();
            return;
        }

        // Lưu dữ liệu vào External Storage
        btnSave.setOnClickListener(view -> {
            String data = edtInput.getText().toString().trim();
            if (data.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập dữ liệu", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lưu dữ liệu vào file "external.txt" trong External Storage
            File file = new File(getExternalFilesDir(null), "external.txt");
            try (FileOutputStream fos = new FileOutputStream(file, true)) {
                fos.write(data.getBytes());
                Toast.makeText(this, "Dữ liệu đã lưu vào External Storage", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Đọc dữ liệu từ External Storage
        btnLoad.setOnClickListener(view -> {
            File file = new File(getExternalFilesDir(null), "external.txt");
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] data = new byte[(int) file.length()];
                    fis.read(data);
                    txtResult.setText(new String(data));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                txtResult.setText("Không có dữ liệu trong file external.txt");
            }
        });
    }

    // Kiểm tra xem có thể ghi vào External Storage không
    private boolean isExternalStorageWritable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}