package com.scotiabank.hojaga.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.scotiabank.hojaga.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentLandingActivity extends AppCompatActivity {

    //Hello
    @BindView(R.id.edit_student_id)
    EditText edit_student_id;
    @BindView(R.id.btn_next)
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_landing);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_next)
    void OnNextClick(){
        startActivity(new Intent(StudentLandingActivity.this, StudentLandingActivity.class));
    }
}
