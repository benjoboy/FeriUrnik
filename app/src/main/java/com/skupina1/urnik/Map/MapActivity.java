package com.skupina1.urnik.Map;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import com.skupina1.urnik.MainActivity;
import com.skupina1.urnik.R;

public class MapActivity extends AppCompatActivity {
    ImageView2 imageView2 = null;
    ImageButton buttonZoomIn = null, buttonZoomOut = null, buttonReset = null;
    String ID = null;
    String NAME = null;

    public void refresh() {
        imageView2.invalidate();
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        NAME = intent.getStringExtra("hallName");
        ID = intent.getStringExtra("hallID");
        if (ID == null || NAME == null) {
            finish();
            Toast.makeText(MapActivity.this,
                    "Your Message", Toast.LENGTH_LONG).show();
            return;
        }
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(NAME);

        imageView2 = findViewById(R.id.imageView);
        buttonReset = findViewById(R.id.resetView);
        buttonZoomIn = findViewById(R.id.zoomPlus);
        buttonZoomOut = findViewById(R.id.zoomMinus);

        int resourceID = getResources().getIdentifier(ID, "drawable", getPackageName());
        imageView2.setBase(resourceID);

        /*
        Random random = new Random();
        imageView2.addPoint(new Point(random.nextFloat(), random.nextFloat()));
        imageView2.addPoint(new Point(random.nextFloat(), random.nextFloat()));
        imageView2.addPoint(new Point(random.nextFloat(), random.nextFloat()));
        imageView2.addPoint(new Point(random.nextFloat(), random.nextFloat()));
        imageView2.addPoint(new Point(random.nextFloat(), random.nextFloat()));
        */


        {//buttons
            buttonZoomIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView2 iv = findViewById(R.id.imageView);
                    iv.changeScale(+0.2f);

                }
            });
            buttonZoomOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView2 iv = findViewById(R.id.imageView);
                    iv.changeScale(-0.2f);
                }
            });
            buttonReset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView2 iv = findViewById(R.id.imageView);
                    iv.setScale(ImageView2.START_SCALE);
                    iv.setX(0);
                    iv.setY(0);
                }
            });
        }//buttons

    }
}
