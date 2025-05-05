package com.example.nt118p21_22520047_lab04;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.util.ArrayList;

public class InternalStorage extends AppCompatActivity {

    private static final String FILE_NAME = "notes.txt";

    private EditText edtInput;
    private ListView listView;
    private ArrayList<String> notesList;
    private ArrayAdapter<String> adapter;
    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);

        edtInput = findViewById(R.id.edtInput);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        listView = findViewById(R.id.listNotes);

        notesList = readNotesFromFile();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesList);
        listView.setAdapter(adapter);
        // Khi chọn ghi chú từ danh sách để sửa
        listView.setOnItemClickListener((parent, view, position, id) -> {
            edtInput.setText(notesList.get(position));
            selectedIndex = position;
        });
        // Thêm ghi chú mới
        btnAdd.setOnClickListener(v -> {
            String note = edtInput.getText().toString().trim();
            if (!note.isEmpty()) {
                notesList.add(note);
                writeNotesToFile(notesList);
                adapter.notifyDataSetChanged();
                edtInput.setText("");
                selectedIndex = -1;
            }
        });
        // Cập nhật ghi chú đã chọn
        btnUpdate.setOnClickListener(v -> {
            String note = edtInput.getText().toString().trim();
            if (!note.isEmpty() && selectedIndex != -1) {
                notesList.set(selectedIndex, note);
                writeNotesToFile(notesList);
                adapter.notifyDataSetChanged();
                edtInput.setText("");
                selectedIndex = -1;
            }
        });
    }
    private ArrayList<String> readNotesFromFile() {
        ArrayList<String> list = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    private void writeNotesToFile(ArrayList<String> list) {
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            for (String note : list) {
                fos.write((note + "\n").getBytes());
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}