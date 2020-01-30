package com.example.project2activity.ex2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project2activity.R;

import androidx.annotation.Nullable;

public class Ex2MainActivity extends Activity implements View.OnClickListener {

    private TextView textActiv;
    public static final String TAG = "Project2Activ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Ex2MainActivity: onCreate()");
        Toast.makeText(this, "starts", Toast.LENGTH_SHORT).show();
        setContentView(R.layout.ex2_main);

        textActiv = (TextView) findViewById(R.id.txt_sorry);

        initsecondfragment();
    }

    private void initsecondfragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frg_ex2second, new Ex2SecondFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        textActiv.setText("No forgiveness to you!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment fragment = getFragmentManager().findFragmentById(R.id.frg_ex2first);
        Button button = (Button) fragment.getView().findViewById(R.id.btn_no);
        button.setOnClickListener(this);
    }
}
