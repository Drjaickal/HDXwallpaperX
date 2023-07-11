package com.example.hdxwallpaper;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hdxwallpaper.databinding.ActivitySetWallpaperBinding;

import java.io.IOException;

public class SetWallpaper extends AppCompatActivity {
    private ActivitySetWallpaperBinding binding;
    private Intent intent;
    private WallpaperManager wallpaperManager;
    private int selectedWallpaperType;

    private static final String EXTRA_IMAGE_URL = "image_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetWallpaperBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        intent = getIntent();

        String url = intent.getStringExtra(EXTRA_IMAGE_URL);
        Glide.with(this).load(url).into(binding.finalimage);

        binding.btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable) binding.finalimage.getDrawable()).getBitmap();
                setWallpaper(bitmap);
            }
        });

        Button btnHomeScreen = findViewById(R.id.btnHomeScreen);
        Button btnLockScreen = findViewById(R.id.btnLockScreen);

        btnHomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedWallpaperType = WallpaperManager.FLAG_SYSTEM;
                toggleButtonSelection(true, false);
            }
        });

        btnLockScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedWallpaperType = WallpaperManager.FLAG_LOCK;
                toggleButtonSelection(false, true);
            }
        });
    }

    public void setWallpaper(Bitmap bitmap) {
        try {
            wallpaperManager.setBitmap(bitmap, null, true, selectedWallpaperType);
            Toast.makeText(this, "Wallpaper set successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to set wallpaper", Toast.LENGTH_SHORT).show();
        }
    }

    private void toggleButtonSelection(boolean homeScreenSelected, boolean lockScreenSelected) {
        Button btnHomeScreen = findViewById(R.id.btnHomeScreen);
        Button btnLockScreen = findViewById(R.id.btnLockScreen);

        btnHomeScreen.setSelected(homeScreenSelected);
        btnLockScreen.setSelected(lockScreenSelected);
    }
}
