package com.example.project2activity.ex1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2activity.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Ex1SecondFragment extends Fragment {

    public static final String TAG = "SecondFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ex1_second, null);
        return view;
    }
}
