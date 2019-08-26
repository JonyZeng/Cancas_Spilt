package com.nice.cancas_spilt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SplitView(this));
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.girl);
//        bitmap.getWidth();
//        bitmap.getHeight();
//        int pixel = bitmap.getPixel(0, 0);

    }
}
