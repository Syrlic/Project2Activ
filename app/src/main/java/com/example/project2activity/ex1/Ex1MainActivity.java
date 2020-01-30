package com.example.project2activity.ex1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project2activity.R;
import com.example.project2activity.TimerActivity;

import androidx.annotation.Nullable;

public class Ex1MainActivity extends Activity {

    private FragmentManager fragmentManager;
    private Ex1FirstFragment firstFragment;
    private Ex1SecondFragment secondFragment;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ex1_main);

        firstFragment =  new Ex1FirstFragment();
        secondFragment = new Ex1SecondFragment();

        fragmentManager = getFragmentManager();

    }

    public void click(View view) {
       fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()){
            case R.id.btn_back:
                Intent intent = new Intent(this, TimerActivity.class);
                finish();
            case R.id.btn_add:
                if(fragmentManager.findFragmentByTag(Ex1FirstFragment.TAG) == null  &&
                        fragmentManager.findFragmentByTag(Ex1SecondFragment.TAG) == null){
                    fragmentTransaction.add(R.id.frag_layout, firstFragment, Ex1FirstFragment.TAG);
                }
                break;
            case R.id.btn_remove:
                if(fragmentManager.findFragmentByTag(Ex1FirstFragment.TAG) != null){
                    fragmentTransaction.remove(firstFragment);
                }
                if(fragmentManager.findFragmentByTag(Ex1SecondFragment.TAG) != null){
                    fragmentTransaction.remove(secondFragment);
                }
                break;
            case R.id.btn_replace:
                if(fragmentManager.findFragmentByTag(Ex1SecondFragment.TAG) != null){
                    fragmentTransaction.replace(R.id.frag_layout, firstFragment, Ex1FirstFragment.TAG);
                }
                if(fragmentManager.findFragmentByTag(Ex1FirstFragment.TAG) != null){
                    fragmentTransaction.replace(R.id.frag_layout, secondFragment, Ex1SecondFragment.TAG);
                }
        }
        fragmentTransaction.commit();
    }
}
