package com.example.hdxwallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<ImageModel> imageList;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private CardView natureCard, carCard, busCard, trainCard;
    private EditText searchEditText;
    private ImageButton searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView = findViewById(R.id.recyclerview);
        natureCard = findViewById(R.id.nature);
        carCard = findViewById(R.id.car);
        busCard = findViewById(R.id.bus);
        trainCard = findViewById(R.id.Train);
        searchEditText = findViewById(R.id.edittext);
        searchButton = findViewById(R.id.search);

        imageList = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new Adapter(this, imageList);
        recyclerView.setAdapter(adapter);

        natureCard.setOnClickListener(this);
        carCard.setOnClickListener(this);
        busCard.setOnClickListener(this);
        trainCard.setOnClickListener(this);
        searchButton.setOnClickListener(this);

        findPhotos();
    }

    private void findPhotos() {
        APIUtilities APIutilities;
        APIutilities.getAPIinterface().getimage(1, 80, API_KEY).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()) {
                    List<ImageModel> photos = response.body().getPhotos();
                    imageList.clear();
                    imageList.addAll(photos);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to fetch images", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void searchImages(String query) {
        APIutilities.getAPIinterface().getSearchimage(query, 1, 80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()) {
                    List<ImageModel> photos = response.body().getPhotos();
                    imageList.clear();
                    imageList.addAll(photos);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to search images", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nature:
                searchImages("Nature");
                break;
            case R.id.car:
                searchImages("Car");
                break;
            case R.id.bus:
                searchImages("Bus");
                break;
            case R.id.Train:
                searchImages("Train");
                break;
            case R.id.search:
                String query = searchEditText.getText().toString().trim();
                if (!query.isEmpty()) {
                    searchImages(query);
                }
                break;
        }
    }

}