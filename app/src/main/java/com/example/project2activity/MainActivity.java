package com.example.project2activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    private TextView textView;
    private ImageView imageMain;
    private ImageView virtualImage;
    private LinearLayout linearLayout;
    private TextView textCenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    textView = (TextView) findViewById(R.id.textTop);
    imageMain = (ImageView) findViewById(R.id.image1);
    virtualImage = (ImageView) findViewById(R.id.image2);
    linearLayout = (LinearLayout) findViewById(R.id.layout);
    textCenter = (TextView) findViewById(R.id.text2);

}
        //слушательно на кнопки написанный через установку метода в view ( android:onClick="click"/>)
        public void click (View view) {
            switch (view.getId()){
                case R.id.button: textView.setText(getResources().getText(R.string.text_top));
                    ImageView image = (ImageView) findViewById(R.id.image1);
                    image.setImageDrawable(getResources().getDrawable(R.drawable.bu));
                    break;
                case R.id.buttonblue: textView.setTextColor(getResources().getColor(R.color.blue));
                    break;
                case R.id.buttonchange: setNewImage(view);
                    break;
                case R.id.button2: changeBackgroundColor();
                break;
                case R.id.image1: imageClick(view);
                break;
                case R.id.image2: imageClick(view);
                break;
            }

        }

        public void changeBackgroundColor(){
            Drawable background = linearLayout.getBackground();
            int color = getResources().getColor(R.color.white); // зачем эта строка непонятно

            if(background.getClass() == ColorDrawable.class)
                color = ((ColorDrawable) background).getColor();
            if(color != R.color.black){
                linearLayout.setBackgroundColor(getResources().getColor(R.color.black));
            }else {
                linearLayout.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
        public void imageClick(View view){
        String text = (String) imageMain.getTag();
        textCenter.setText(text);
        }

        public void setNewImage(View view){
        textCenter.setText(R.string.empty_txt);
        Drawable drawableNew = virtualImage.getDrawable();
        Drawable drawableOld = imageMain.getDrawable();
        String tagNew = (String) virtualImage.getTag();
        String tagOld = (String) imageMain.getTag();
        virtualImage.setTag(tagOld);
        virtualImage.setImageDrawable(drawableOld);
        imageMain.setTag(tagNew);
        imageMain.setImageDrawable(drawableNew);
        }
}
