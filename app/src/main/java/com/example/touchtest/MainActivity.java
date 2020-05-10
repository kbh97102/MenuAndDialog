package com.example.touchtest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private MyView myView;
    private Map<String, Integer> colorMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view);

        initialize();
        Button clearButton = findViewById(R.id.clearButton);
        clearButton.setOnClickListener(v-> myView.clear());
    }

    private void initialize() {
        myView = findViewById(R.id.myView);
        colorMap = new HashMap<>();
        colorMap.put(getString(R.string.red), Color.RED);
        colorMap.put(getString(R.string.blue), Color.BLUE);
        colorMap.put(getString(R.string.green), Color.GREEN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_color){
            ColorPicker colorPicker = new ColorPicker(MainActivity.this, this::changeColor);
            colorPicker.displayDialog();
        }
        else{
            BrushPicker brushPicker = new BrushPicker(MainActivity.this
                    , this::changeBrushType, this::changeBrushSize);
            brushPicker.displayDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeColor(int color){
        myView.setPaintColor(color);
    }

    private void changeBrushType(int type){
        myView.setBrushType(type);
    }

    private void changeBrushSize(int size){
        myView.setBrushSize(size);
    }
}

