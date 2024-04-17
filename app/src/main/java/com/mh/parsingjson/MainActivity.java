package com.mh.parsingjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mh.parsingjson.FromAssetsToRecyclerview.FromAssetsToRecyclerView;
import com.mh.parsingjson.FromLocalString.FromLocalString;
import com.mh.parsingjson.FromLocalToListview.FromLocaleToListview;
import com.mh.parsingjson.FromUrlToListview.FromUrlToListView;
import com.mh.parsingjson.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.buttonFromLocalString.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, FromLocalString.class);
            startActivity(intent);

        });
        binding.buttonFromLocalToListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FromLocaleToListview.class);
                startActivity(intent);
            }
        });
        binding.buttonFromAssetsIntoRecyclerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FromAssetsToRecyclerView.class);

                startActivity(intent);

            }
        });
        binding.buttonFromUrlIntoListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FromUrlToListView.class);
                startActivity(intent);
            }
        });
    }
}