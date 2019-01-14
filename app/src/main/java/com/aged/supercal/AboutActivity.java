package com.aged.supercal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

public class AboutActivity extends AppCompatActivity {

    private LinearLayout LinearLayout_aboutteam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        LinearLayout_aboutteam = (LinearLayout)findViewById(R.id.abouteam);
        LinearLayout_aboutteam.setOnClickListener(new TextViewListener());

    }

    class TextViewListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(AboutActivity.this,AboutTeamActivity.class);
            startActivity(intent);
        }
    }
}
