package com.example.project2activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class TimerActivity extends Activity {

    private TextView hours;
    private TextView mins;
    private TextView greatings;
    private ImageView unicorn;
    private ImageView dog;
    private ImageView cat;
    private TextView nameapp;
    private final String TAG = "Project2Activ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_layout);

        hours = (TextView) findViewById(R.id.hour);
        mins = (TextView) findViewById(R.id.min);
        unicorn = (ImageView) findViewById(R.id.unicorn);
        cat = (ImageView) findViewById(R.id.framecat);
        dog = (ImageView) findViewById(R.id.framedog);
        greatings = (TextView) findViewById(R.id.note);
        nameapp = (TextView) findViewById(R.id.nameview);

        String txtname = getIntent().getStringExtra("login");
        nameapp.setText("Greatings of the day, "+txtname + ". Set your timer!");
        Log.i(TAG, "TimerActivity: onCreate()");
        Toast.makeText(this, "TimerActivity: onCreate()", Toast.LENGTH_SHORT).show();
    }

    public void click(View view){
        String hourtxt = hours.getText().toString();
        String mintxt = mins.getText().toString();
        int hour = Integer.parseInt(hourtxt);
        int min = Integer.parseInt(mintxt);

        switch (view.getId()){
            case R.id.button3: hour = resultPlus(hour, true);
                               hours.setText(String.valueOf(hour));
                               break;
            case R.id.button4: hour = resultMinus(hour, true);
                                hours.setText(String.valueOf(hour));
                                break;
            case R.id.button5: min = resultPlus(min, false);
                                mins.setText(String.valueOf(min));
                                break;
            case R.id.button6: min = resultMinus(min, false);
                mins.setText(String.valueOf(min));
                break;
            case R.id.unicorn: getImageShow(view);
            case R.id.framedog: getImageShow(view);
            case R.id.framecat: getImageShow(view);
        }
    }

    public void goToLoginScreen(View view) {

        boolean logic = view.getId() == R.id.btn_back;
        // вторая кнопка не описана и работает только один тоаст
        String messege = (logic) ? "Goodbye!" : "Time to sleeep))";
        int lenght = (logic) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, messege, lenght);
        toast.show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private int resultPlus(int value, boolean isHour) {
        int maxValue =(isHour)? 23: 59;
        value++;
        if(value > maxValue){
            value = 0;
        }
        return value;
    }

    private int resultMinus(int value, boolean isHour) {
        int maxValue =(isHour)? 23: 59;
        value--;
        if(value < 0){
            value = maxValue;
        }
        return value;
    }

    private void getImageShow(View view){
        switch (view.getId()){
            case R.id.unicorn:
                unicorn.setVisibility(View.INVISIBLE);
                dog.setVisibility(View.VISIBLE);
                cat.setVisibility(View.INVISIBLE);
                greatings.setText(R.string.text_dog);
                break;
            case R.id.framedog:
                unicorn.setVisibility(View.INVISIBLE);
                dog.setVisibility(View.INVISIBLE);
                cat.setVisibility(View.VISIBLE);
                greatings.setText(R.string.txt_cat);
                break;
            case R.id.framecat:
                unicorn.setVisibility(View.VISIBLE);
                dog.setVisibility(View.INVISIBLE);
                cat.setVisibility(View.INVISIBLE);
                greatings.setText(R.string.empty_txt);
                break;
        }
    }

    public void goToDataList(View view) {
        Intent intent = new Intent(this, AddDataFromDialogWindow.class);
        startActivity(intent);
    }

    public void goToFragments(View view) {
        Intent intent = new Intent(this, ExamlesFragments.class);
        startActivity(intent);
    }
}
