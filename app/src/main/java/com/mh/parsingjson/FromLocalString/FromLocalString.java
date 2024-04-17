package com.mh.parsingjson.FromLocalString;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.mh.parsingjson.R;
import com.mh.parsingjson.databinding.ActivityFromLocalStringBinding;

import org.json.JSONObject;

import java.util.Objects;


public class FromLocalString extends AppCompatActivity {
    private ActivityFromLocalStringBinding binding;
    private String JSON_STRING = "{ \"student\": { \"name\": \"Ellen\", \"surname\": \"Gorman\", \"age\": 17 } }";

    // the string is given clearly below, there are three types of data in the main object -->
    // an object (student), two strings (name and surname) and an int (age)
    // "{
    // "student":
    // {
    // "name":"Ellen",
    // "surname":Gorman,
    // "age": 17
    // }
    // }";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFromLocalStringBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.from_local_string));
        try {
            // create the JSON object from the string above
            JSONObject jsonObject = new JSONObject(JSON_STRING);
            //get the student object
            JSONObject object = jsonObject.getJSONObject("student");
            //get the employee object's name
            String name = object.getString("name");
            //get the employee object's name
            String surname = object.getString("surname");
            //get the employee object's salary
            int age = object.getInt("age");

            //set values
            binding.txtNameValue.setText(name);
            binding.txtSurnameValue.setText(surname);
            binding.txtAgeValue.setText(String.valueOf(age));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}