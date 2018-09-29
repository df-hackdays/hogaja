package com.scotiabank.hojaga.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scotiabank.hojaga.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentWelcomeActivity extends AppCompatActivity {

    @BindView(R.id.layout_background)
    RelativeLayout layout_background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_welcome);
        ButterKnife.bind(this);

        layout_background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentWelcomeActivity.this, StudentModulesActivity.class));
            }
        });
    }


}
