package com.mh.parsingjson.FromUrlToListview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.mh.parsingjson.R;
import com.mh.parsingjson.databinding.ActivityFromUrlToListViewBinding;
import com.mh.parsingjson.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class FromUrlToListView extends AppCompatActivity {
    private ActivityFromUrlToListViewBinding binding;
    private static String JSON_URL = "https://run.mocky.io/v3/0c664730-33cd-4358-a488-221e91e5f94e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFromUrlToListViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.from_url_into_listview));

    // will be added soon

    }
}