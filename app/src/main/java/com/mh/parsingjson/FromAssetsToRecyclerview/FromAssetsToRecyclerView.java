package com.mh.parsingjson.FromAssetsToRecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.mh.parsingjson.R;
import com.mh.parsingjson.databinding.ActivityFromAssetsToRecyclerViewBinding;
import com.mh.parsingjson.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class FromAssetsToRecyclerView extends AppCompatActivity {
    private ActivityFromAssetsToRecyclerViewBinding binding;
    private ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFromAssetsToRecyclerViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.from_assets_into_recyclerview));
        // ArrayList to store the student objects
        students = new ArrayList<>();

        //configure recycleView

        //method to get the data from JSON
        getJSON();

    }

    // load json from assets
    private String loadJSONFromAssets() {
        String jsonString = null;
        try {

            InputStream inputStream = getAssets().open("students.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }

    private void getJSON() {
        try {

            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(loadJSONFromAssets()));
            JSONArray jsonArray = jsonObject.getJSONArray("students");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject studentInfo = jsonArray.getJSONObject(i);
                Student student = new Student();
                student.setId(studentInfo.getString("id"));
                student.setName(studentInfo.getString("name"));
                student.setEmail(studentInfo.getString("email"));
                student.setContactMobile(studentInfo.getJSONObject("contact").getString("mobile"));
                student.setContactHome(studentInfo.getJSONObject("contact").getString("home"));
                students.add(student);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        // insert data to the recyclerView
        StudentAdapter adapter = new StudentAdapter(FromAssetsToRecyclerView.this, students);
        binding.recyclerviewFromAssets.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerviewFromAssets.setAdapter(adapter);



    }
}