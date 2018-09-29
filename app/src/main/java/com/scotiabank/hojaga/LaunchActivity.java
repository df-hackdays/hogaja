package com.scotiabank.hojaga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LaunchActivity extends AppCompatActivity {

    //Hello
    @BindView(R.id.edit_student_id)
    EditText edit_student_id;
    @BindView(R.id.btn_next)
    EditText btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_next)
    void OnNextClick(){
        startActivity(new Intent(LaunchActivity.this, LaunchActivity.class));
    }
}
