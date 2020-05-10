package com.example.touchtest;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class BrushPicker {

    private Consumer<Integer> changeBrushType;
    private Consumer<Integer> changeBrushSize;
    private Context context;
    private int currentBrushType;
    private Button okButton;
    private Button cancelButton;
    private Dialog dialog;
    private ImageButton brushType1;
    private ImageButton brushType2;
    private ImageButton brushType3;
    private ImageButton brushType4;
    private ImageButton brushType5;
    private ImageView selectedBrush;
    private SeekBar sizeBar;
    private Map<Integer, Integer> brushMap;
    private int currentBrushSize;


    public BrushPicker(Context context, Consumer<Integer> changeBrushType, Consumer<Integer> changeBrushSize) {
        this.context = context;
        this.changeBrushType = changeBrushType;
        this.changeBrushSize = changeBrushSize;
        currentBrushType = R.drawable.brush_type1;
    }

    public void displayDialog() {
        dialog = new Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.brush_picker);
        dialog.show();

        initialize();
        setButtonEvent();
        setButtonImage();
    }

    private void initialize() {
        okButton = dialog.findViewById(R.id.okButton);
        cancelButton = dialog.findViewById(R.id.cancelButton);
        brushType1 = dialog.findViewById(R.id.brush_1);
        brushType2 = dialog.findViewById(R.id.brush_2);
        brushType3 = dialog.findViewById(R.id.brush_3);
        brushType4 = dialog.findViewById(R.id.brush_4);
        brushType5 = dialog.findViewById(R.id.brush_5);
        selectedBrush = dialog.findViewById(R.id.selectedBrushImageView);
        sizeBar = dialog.findViewById(R.id.size);
        brushMap = new HashMap<>();
        brushMap.put(R.id.brush_1, R.drawable.brush_type1);
        brushMap.put(R.id.brush_2, R.drawable.brush_type2);
        brushMap.put(R.id.brush_3, R.drawable.brush_type3);
        brushMap.put(R.id.brush_4, R.drawable.brush_type4);
        brushMap.put(R.id.brush_5, R.drawable.brush_type5);
        selectedBrush.setImageResource(R.drawable.brush_type1);
        currentBrushSize = sizeBar.getProgress();
    }

    private void setButtonEvent() {
        okButton.setOnClickListener(v -> dialog.dismiss());
        cancelButton.setOnClickListener(v -> dialog.dismiss());
        brushType1.setOnClickListener(this::selectButtonEvent);
        brushType2.setOnClickListener(this::selectButtonEvent);
        brushType3.setOnClickListener(this::selectButtonEvent);
        brushType4.setOnClickListener(this::selectButtonEvent);
        brushType5.setOnClickListener(this::selectButtonEvent);
        setSizeBarEvent();
    }

    private void setButtonImage() {
        brushType1.setImageResource(R.drawable.brush_type1);
        brushType2.setImageResource(R.drawable.brush_type2);
        brushType3.setImageResource(R.drawable.brush_type3);
        brushType4.setImageResource(R.drawable.brush_type4);
        brushType5.setImageResource(R.drawable.brush_type5);
    }

    private void selectButtonEvent(View v) {
        currentBrushType = brushMap.get(v.getId());
        selectedBrush.setImageResource(currentBrushType);
        changeBrushType.accept(currentBrushType);
        changeBrushSize.accept(currentBrushSize);
    }

    private void setSizeBarEvent() {
        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentBrushSize = progress;
                changeBrushSize.accept(currentBrushSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }


}
