package com.example.nt118p21_22520047_lab04;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class SQLite extends AppCompatActivity {

    EditText edtCode, edtName, edtCount;
    ListView listView;
    DatabaseHelper dbHelper;
    SimpleCursorAdapter adapter;
    long selectedRowId = -1; // dùng để cập nhật/xoá

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        edtCode = findViewById(R.id.edtClassCode);
        edtName = findViewById(R.id.edtClassName);
        edtCount = findViewById(R.id.edtStudentCount);
        listView = findViewById(R.id.listView);

        dbHelper = new DatabaseHelper(this);
        loadData();

        Button btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(v -> {
            dbHelper.insertClass(
                    edtCode.getText().toString(),
                    edtName.getText().toString(),
                    Integer.parseInt(edtCount.getText().toString())
            );
            clearInput();
            loadData();
        });

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(v -> {
            dbHelper.updateClass(
                    edtCode.getText().toString(),
                    edtName.getText().toString(),
                    Integer.parseInt(edtCount.getText().toString())
            );
            clearInput();
            loadData();
        });

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> {
            dbHelper.deleteClass(edtCode.getText().toString());
            clearInput();
            loadData();
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Cursor cursor = (Cursor) adapter.getItem(position);
            selectedRowId = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
            edtCode.setText(cursor.getString(cursor.getColumnIndexOrThrow("malop")));
            edtName.setText(cursor.getString(cursor.getColumnIndexOrThrow("tenlop")));
            edtCount.setText(cursor.getString(cursor.getColumnIndexOrThrow("siso")));
        });
    }

    private void clearInput() {
        edtCode.setText("");
        edtName.setText("");
        edtCount.setText("");
        selectedRowId = -1;
    }

    private void loadData() {
        Cursor cursor = dbHelper.getAllClasses();
        adapter = new SimpleCursorAdapter(
                this,
                R.layout.class_list,
                cursor,
                new String[]{"malop", "tenlop", "siso"},
                new int[]{R.id.classCode, R.id.className, R.id.studentCount},
                0
        );
        listView.setAdapter(adapter);
    }
}