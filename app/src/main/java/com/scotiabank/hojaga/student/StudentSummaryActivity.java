package com.scotiabank.hojaga.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.scotiabank.hojaga.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentSummaryActivity extends AppCompatActivity {

    @BindView(R.id.btn_back_to_lessons)
    Button btn_back_to_lessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_summary);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_back_to_lessons)
    void onBackClick(){
        Intent intent = new Intent(StudentSummaryActivity.this, StudentModulesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
