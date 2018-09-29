package com.scotiabank.hojaga.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scotiabank.hojaga.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentWelcomeActivity extends AppCompatActivity {

    //Hello
    @BindView(R.id.txt_name_value)
    TextView txt_name_value;
    @BindView(R.id.txt_date_value)
    TextView txt_date_value;
    @BindView(R.id.btn_next)
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_welcome);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_next)
    void OnNextClick(){
        startActivity(new Intent(StudentWelcomeActivity.this, StudentModulesActivity.class));
    }
}