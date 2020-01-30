package com.example.project2activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class InitialOnClickButton extends Activity {

    private LinearLayout linearLayout;
    private EditText sms;
    private Button ok;
    private String messege;
    private final String TAG = "Project2Activ";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_layout);
        linearLayout = (LinearLayout) findViewById(R.id.layout);
        sms = (EditText) findViewById(R.id.sms);
        ok = (Button) findViewById(R.id.btn_ok);
        Log.i(TAG, "InitialOnClickButton: onCreate()");
        Toast.makeText(this, "InitialOnClickButton: onCreate()", Toast.LENGTH_SHORT).show();
    }

    //метод не работает (это просто пример по созданию кнопки программно
    // и слушателя для новой кнопки)
    public void click(View view){
        Button button = new Button(this);
        button.setText(R.string.btn_add);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        button.setLayoutParams(params);
        final TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(R.string.text_dog);
            }
        });
        linearLayout.addView(button);
        linearLayout.addView(textView);

    }

    public void confirm(View view) {
        messege = sms.getText().toString();
        if(messege.isEmpty()){
            Toast.makeText(this, "Messege is empty", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent();
            intent.putExtra("sms", messege);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void setText(View view) {
        sms.setText("");
    }
}
