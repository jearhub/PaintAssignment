package com.example.paintassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PaintView pv = findViewById(R.id.view);
        pv.setRadius(30);

        Button undo = findViewById (R.id.btn1);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pv.onClickUndo ();
            }
        });
        Button redo = findViewById (R.id.btn2);
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pv.onClickRedo();
            }
        });
        SeekBar seekBar = findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pv.setRadius(i);
                pv.invalidate();
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
