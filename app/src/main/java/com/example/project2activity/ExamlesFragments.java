package com.example.project2activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project2activity.ex1.Ex1MainActivity;
import com.example.project2activity.ex2.Ex2MainActivity;

import androidx.annotation.Nullable;

public class ExamlesFragments extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
    }

    public void click(View view) {

        Intent intent;
        switch (view.getId()){
            case R.id.btn_ex_1:
                intent = new Intent(this, Ex1MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_ex2:
                intent = new Intent(this, Ex2MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_back:
                intent = new Intent(this, TimerActivity.class);
                finish();
                break;
        }
    }
}
