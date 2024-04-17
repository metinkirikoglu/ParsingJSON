package com.mh.parsingjson.FromLocalToListview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mh.parsingjson.R;
import com.mh.parsingjson.databinding.ActivityFromLocaleToListviewBinding;
import com.mh.parsingjson.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class FromLocaleToListview extends AppCompatActivity {
    private ActivityFromLocaleToListviewBinding binding;
    private ListView listView;

    private String JSON_STRING = "{\n" +
            "  \"students\": [\n" +
            "    {\n" +
            "      \"name\": \"Garnet\",\n" +
            "      \"surname\": \"Goddard\",\n" +
            "      \"age\": 15\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Pip\",\n" +
            "      \"surname\": \"Schuhmacher\",\n" +
            "      \"age\": 14\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Kendall\",\n" +
            "      \"surname\": \" Aleshire\",\n" +
            "      \"age\": 13\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"Microsoft\",\n" +
            "      \"surname\": \"sadasdasd\",\n" +
            "      \"age\": 14\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"River\",\n" +
            "      \"surname\": \"Doctor\",\n" +
            "      \"age\": 15\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFromLocaleToListviewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        listView = binding.listviewStudents;
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.from_local_to_listview));
        //get the json objects
        try {
            // create a list to store the students
            ArrayList<StringBuffer> students = new ArrayList<>();
            // create a JSON object to get the object in the json string
            JSONObject jsonObject = new JSONObject(JSON_STRING);
            // create a jsonArray to get the array in the jsonObject
            JSONArray jsonArray = jsonObject.getJSONArray("students");
            // add the jsonArray into the arraylist
            for (int i = 0; i < jsonArray.length(); i++) {
                //for i get the i. create an json object
                JSONObject object = jsonArray.getJSONObject(i);
                //to display student info, it can be done with a custom adapter,
                // but here the goal is to keep it simple
                StringBuffer  studentInfoString = new StringBuffer();
                studentInfoString.append(object.getString("name"))
                        .append(" ")
                        .append(object.getString("surname"))
                        .append(" - ")
                        .append(String.valueOf(object.getInt("age")));
                // add the StringBuffer to the array list
                students.add(studentInfoString);

            }
            // create the adapter for listview
            ArrayAdapter<StringBuffer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);

            if (listView != null) {
                listView.setAdapter(adapter);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }
}