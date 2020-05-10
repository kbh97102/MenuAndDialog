package com.example.touchtest;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.function.Consumer;

public class ColorPicker {

    private int currentColor;
    private int currentRed;
    private int currentBlue;
    private int currentGreen;
    private int currentAlpha;
    private Context context;
    private Consumer<Integer> changeColor;
    private SeekBar red;
    private SeekBar blue;
    private SeekBar green;
    private SeekBar alpha;
    private TextView colorInt;
    private ImageView image;
    private Dialog dialog;

    public ColorPicker(Context context, Consumer<Integer> changeColor) {
        this.context = context;
        this.changeColor = changeColor;
    }

    public void displayDialog() {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.color_picker);
        dialog.show();
        initialize();
        setButtonEvent();
    }

    private void initialize() {
        red = dialog.findViewById(R.id.red);
        blue = dialog.findViewById(R.id.blue);
        green = dialog.findViewById(R.id.green);
        alpha = dialog.findViewById(R.id.alpha);
        colorInt = dialog.findViewById(R.id.rgbToInt);
        image = dialog.findViewById(R.id.colorView);
        currentAlpha = 255;
        currentRed = 0;
        currentBlue = 0;
        currentGreen = 0;
        currentColor = Color.argb(currentAlpha, currentRed, currentGreen, currentBlue);
        colorInt.setText(String.format("%02X", currentColor));
    }

    private void setButtonEvent() {
        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentRed = progress;
                currentColor = Color.argb(currentAlpha, currentRed, currentGreen, currentBlue);
                image.setBackgroundColor(currentColor);
                colorInt.setText(String.format("%02X", currentColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentBlue = progress;
                currentColor = Color.argb(currentAlpha, currentRed, currentGreen, currentBlue);
                image.setBackgroundColor(currentColor);
                colorInt.setText(String.format("%02X", currentColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentGreen = progress;
                currentColor = Color.argb(currentAlpha, currentRed, currentGreen, currentBlue);
                image.setBackgroundColor(currentColor);
                colorInt.setText(String.format("%02X", currentColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentAlpha = progress;
                currentColor = Color.argb(currentAlpha, currentRed, currentGreen, currentBlue);
                image.setBackgroundColor(currentColor);
                colorInt.setText(String.format("%02X", currentColor));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        Button okButton = dialog.findViewById(R.id.okButton);
        okButton.setOnClickListener(v -> {
            currentColor = Color.argb(currentAlpha, currentRed, currentGreen, currentBlue);
            changeColor.accept(currentColor);
            dialog.dismiss();
        });
    }


}
