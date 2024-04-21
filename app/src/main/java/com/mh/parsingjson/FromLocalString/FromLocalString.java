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
            // create a new JSONObject instance from the JSON_STRING
            JSONObject jsonObject = new JSONObject(JSON_STRING);
            // retrieve the nested object named "student" from the main JSON object.
            JSONObject object = jsonObject.getJSONObject("student");
            //extract the value for the key "name" within the "student" object and
            //store it in the name variable. Similar calls are made for surname and
            //age using getString and getInt methods respectively, based on their data types.
            String name = object.getString("name");
            String surname = object.getString("surname");
            int age = object.getInt("age");

            //set the text of the text view with the ID txtNameValue to the
            // value stored in the name variable. Similar calls are made for
            // txtSurnameValue and txtAgeValue to display the extracted data.
            binding.txtNameValue.setText(name);
            binding.txtSurnameValue.setText(surname);
            binding.txtAgeValue.setText(String.valueOf(age));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}