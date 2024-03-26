package com.example.fragdemo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fragdemo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        binding.mainContent.mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));

        binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ColorDrawable colorDrawable = (ColorDrawable) binding.mainContent.mainLayout.getBackground();
                int colorId = colorDrawable.getColor(); // Stores original color, can help when doing undo action

                if(colorId == Color.LTGRAY) {
                    binding.mainContent.mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));
                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                } else {
                    binding.mainContent.mainLayout.setBackgroundColor(Color.LTGRAY);
                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAFAFA")));
                }

                Snackbar.make(view, "Like the new color? ", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(colorId != Color.LTGRAY) {
                                    binding.mainContent.mainLayout.setBackgroundColor(Color.parseColor("#FAFAFA"));
                                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.LTGRAY));
                                } else {
                                    binding.mainContent.mainLayout.setBackgroundColor(Color.LTGRAY);
                                    binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FAFAFA")));
                                }
                            }
                        }).show();
            }
        });

        List<String> colorDescriptions = new ArrayList<>(Arrays.asList("BLACK", "ORANGE", "PURPLE"));
        List<Integer> colorVals = new ArrayList<>(Arrays.asList(Color.BLACK, Color.rgb(255, 165, 0), Color.parseColor("#800080")));
        List<ColorSpec> ColorList = new ArrayList<>();

        for (int i = 0; i < colorDescriptions.size(); i++) {
            ColorSpec colorSpec = new ColorSpec(colorDescriptions.get(i), colorVals.get(i));
            ColorList.add(colorSpec);
        }
        Log.d("FRAGDEMO", ColorList.size() + "items in list");
        ColorSpecViewModel colorSpecViewModel = new ViewModelProvider(this).get(ColorSpecViewModel.class);
        colorSpecViewModel.setColorList(ColorList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}