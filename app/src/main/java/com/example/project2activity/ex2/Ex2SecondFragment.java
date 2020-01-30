package com.example.project2activity.ex2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project2activity.R;

import androidx.annotation.Nullable;

@SuppressLint("ValidFragment")
class Ex2SecondFragment extends Fragment implements View.OnClickListener {

    private Button yes;
    private TextView view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ex2_second, null);

        yes = (Button) view.findViewById(R.id.btn_sorry);
        view = (TextView) view.findViewById(R.id.txt_ex2second);
        yes.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Button activbtn = (Button) getActivity().findViewById(R.id.btn_sorry);
        activbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setText("Hmm");
            }
        });
    }

    @Override
    public void onClick(View v) {
        Activity activity = getActivity();
        FragmentManager fm = activity.getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frg_ex2first);
        TextView textView = (TextView) fragment.getView().findViewById(R.id.txt_ex2first);
        textView.setText("You don't understand nothing John Snow");

    }
}
