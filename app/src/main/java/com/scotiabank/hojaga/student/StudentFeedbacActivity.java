package com.scotiabank.hojaga.student;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.adapters.KeywordsAdapter;
import com.scotiabank.hojaga.student.models.ModulesInfo;
import com.warkiz.widget.IndicatorSeekBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentFeedbacActivity extends AppCompatActivity {

    //Hello
    @BindView(R.id.img_smiley)
    ImageView img_smiley;
    @BindView(R.id.slider)
    IndicatorSeekBar slider;
    @BindView(R.id.btn_next)
    Button btn_next;

    private KeywordsAdapter keywordsAdapter;
    private ArrayList<ModulesInfo> definitionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_feedback);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_next)
    void OnNextClick() {
        startActivity(new Intent(StudentFeedbacActivity.this, StudentSummaryActivity.class));
    }

}
