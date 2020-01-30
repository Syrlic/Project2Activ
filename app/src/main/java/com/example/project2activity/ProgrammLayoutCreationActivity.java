package com.example.project2activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProgrammLayoutCreationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView label = new TextView(this);
        label.setText(R.string.help_txt);
        label.setTextSize(20);
        label.setGravity(Gravity.CENTER_HORIZONTAL);

        ImageView pic = new ImageView(this);
        pic.setImageResource(R.mipmap.ic_launcher);
        pic.setLayoutParams(new ActionBar.LayoutParams
                (ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
        pic.setAdjustViewBounds(true);
        pic.setScaleType(ImageView.ScaleType.FIT_XY);
        pic.setMaxHeight(250);
        pic.setMaxWidth(250);

        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new ActionBar.LayoutParams
                (ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT));
        ll.setGravity(Gravity.CENTER);
        ll.addView(label);
        ll.addView(pic);
        setContentView(ll);
    }
}
