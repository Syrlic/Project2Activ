package com.example.project2activity.ex1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project2activity.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Ex1FirstFragment extends Fragment {

    public static final String TAG = "FirstFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

      View view = inflater.inflate(R.layout.ex1_first, null);
      return view;
    }
}
