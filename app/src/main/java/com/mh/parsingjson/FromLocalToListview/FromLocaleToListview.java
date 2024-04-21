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

    // define a string variable containing the JSON data directly embedded in the code.
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

        try {
            // create an ArrayList to store student information,
            //but uses StringBuffer (a mutable string buffer) instead of
            //more common data structures like Student objects or String arrays.
            ArrayList<StringBuffer> students = new ArrayList<>();
            //parse the local JSON string into a JSONObject object.
            JSONObject jsonObject = new JSONObject(JSON_STRING);
            // retrieve the JSON array named "students" from the main JSON object.
            JSONArray jsonArray = jsonObject.getJSONArray("students");
            // add the jsonArray into the arraylist
            for (int i = 0; i < jsonArray.length(); i++) {
                //extract a specific student object (JSONObject) from the array.
                JSONObject object = jsonArray.getJSONObject(i);
                //Here, instead of creating custom objects, a StringBuffer is used
                //to build a formatted string containing student information.
                //create a new StringBuffer object.
                StringBuffer  studentInfoString = new StringBuffer();
                //append the student's name, surname, age, and separators to the
                //studentInfoString using appropriate methods from StringBuffer.
                studentInfoString.append(object.getString("name"))
                        .append(" ")
                        .append(object.getString("surname"))
                        .append(" - ")
                        .append(String.valueOf(object.getInt("age")));
                //add the created StringBuffer containing formatted student information to the students ArrayList.
                students.add(studentInfoString);

            }
            //create an ArrayAdapter for the ListView.
            ArrayAdapter<StringBuffer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
           //checks if the ListView reference is valid and then sets the created
            //ArrayAdapter as the adapter for the ListView. The adapter is responsible
            //for providing data and views for each item displayed in the list.
            if (listView != null) {
                listView.setAdapter(adapter);
            }

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }
}