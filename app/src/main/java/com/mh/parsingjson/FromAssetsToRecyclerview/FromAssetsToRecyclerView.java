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

        // initialize the students ArrayList to store the parsed student data.
        students = new ArrayList<>();

        //configure recycleView

        //This method handles retrieving and processing the JSON data.
        getJSON();

    }

    private void getJSON() {
        try {

            //parse the retrieved JSON string into a JSONObject object. Null checks ensure the string is valid before parsing.
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(loadJSONFromAssets()));
            // retrieve the JSON array named "students" from the main JSON object.
            JSONArray jsonArray = jsonObject.getJSONArray("students");

            for (int i = 0; i < jsonArray.length(); i++) {
                // extract a specific student object (JSONObject) from the array.
                JSONObject studentInfo = jsonArray.getJSONObject(i);
                //create a new Student object.
                Student student = new Student();
                // create the student object by calling setter methods (setId, setName, etc.)
                // with data extracted from the studentInfo object using appropriate methods (getString for strings).
                //Notice how nested data within the "contact" object is accessed using getJSONObject and then retrieving specific values.
                student.setId(studentInfo.getString("id"));
                student.setName(studentInfo.getString("name"));
                student.setEmail(studentInfo.getString("email"));
                student.setContactMobile(studentInfo.getJSONObject("contact").getString("mobile"));
                student.setContactHome(studentInfo.getJSONObject("contact").getString("home"));
                //add the newly created Student object to the students ArrayList.
                students.add(student);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        // create a new instance of the StudentAdapter class (likely defined elsewhere),
        //passing the activity context and the students ArrayList containing parsed data.
        StudentAdapter adapter = new StudentAdapter(FromAssetsToRecyclerView.this, students);
        //set a LinearLayoutManager as the layout manager for the RecyclerView, which dictates how items are positioned within the list.
        binding.recyclerviewFromAssets.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //set the StudentAdapter instance as the adapter for the RecyclerView.
        //The adapter is responsible for providing data and views for each item displayed in the list.
        binding.recyclerviewFromAssets.setAdapter(adapter);


    }

    // load json from assets
    private String loadJSONFromAssets() {
        //declare a string variable jsonString and initializes it to null.
        //This variable will hold the loaded JSON data as a string.
        String jsonString = null;
        //This block wraps the code responsible for file access and potential exceptions.
        try {
            //retrieve an InputStream object from the assets folder using the getAssets() method.
            //The "students.json" argument specifies the filename to open.
            InputStream inputStream = getAssets().open("students.json");
            //check the size (number of bytes) available in the input stream,
            //which represents the size of the JSON file.
            int size = inputStream.available();
            //create a byte array with a size equal to the file size retrieved earlier.
            //This buffer will hold the raw bytes of the JSON data.
            byte[] buffer = new byte[size];
            //read the entire content of the input stream (JSON file) into the byte array buffer.
            inputStream.read(buffer);
            //close the input stream to release resources associated with the file access.
            inputStream.close();
            //convert the byte array buffer containing the raw data into a String object
            //using the UTF-8 character encoding. UTF-8 is a common encoding for handling text data in JSON files.
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
      //If no exceptions occur, the method returns the loaded JSON data as a string stored in the jsonString variable.
        return jsonString;
    }


}