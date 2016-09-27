package com.wdc.wcricledraw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.wdc.wcricledraw.comm.DrawImage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img1 = (ImageView)findViewById(R.id.img1);//圆形
        ImageView img2 = (ImageView)findViewById(R.id.img2);//正方形
        ImageView img3 = (ImageView)findViewById(R.id.img3);//矩形

        String uname="显示";

        img1.setImageBitmap(DrawImage.drawHeadimg(getApplicationContext(), 70, uname));
        img2.setImageBitmap(DrawImage.drawSquare(getApplicationContext(), 70, uname,"#D2945B"));
        img3.setImageBitmap(DrawImage.drawRect(getApplicationContext(),100, 80, 30, uname,"#B758A9"));

    }
}
