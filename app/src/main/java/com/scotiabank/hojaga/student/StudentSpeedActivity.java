package com.scotiabank.hojaga.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.adapters.KeywordsAdapter;
import com.scotiabank.hojaga.student.models.ModulesInfo;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentSpeedActivity extends AppCompatActivity {

    //Hello
    @BindView(R.id.img_smiley)
    ImageView img_smiley;
    @BindView(R.id.slider)
    IndicatorSeekBar slider;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.txt_smiley_text)
    TextView txt_smiley_text;

    private KeywordsAdapter keywordsAdapter;
    private ArrayList<ModulesInfo> definitionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_speed);
        ButterKnife.bind(this);

        slider.setOnSeekChangeListener(new OnSeekChangeListener() {
            @Override
            public void onSeeking(SeekParams seekParams) {
                Log.d("TAG","SEEK: "+seekParams.progress);
                if(seekParams.progress == 0) {
                    txt_smiley_text.setText("Toooo slowwwww");
                    img_smiley.setImageDrawable(getDrawable(R.drawable.common_google_signin_btn_icon_dark));
                }
                else if(seekParams.progress == 33) {
                    txt_smiley_text.setText("Could have been a bit fast!");
                    img_smiley.setImageDrawable(getDrawable(R.drawable.common_google_signin_btn_icon_dark));
                }
                else  if(seekParams.progress == 67) {
                    txt_smiley_text.setText("Perfect!");
                    img_smiley.setImageDrawable(getDrawable(R.drawable.common_google_signin_btn_icon_dark));
                }
                else if(seekParams.progress == 100) {
                    txt_smiley_text.setText("Faasssttttt!!!");
                    img_smiley.setImageDrawable(getDrawable(R.drawable.common_google_signin_btn_icon_dark));
                }
            }

            @Override
            public void onStartTrackingTouch(IndicatorSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(IndicatorSeekBar seekBar) {

            }
        });

    }

    @OnClick(R.id.btn_submit)
    void OnSubmitClick() {
        startActivity(new Intent(StudentSpeedActivity.this, StudentSummaryActivity.class));
    }

}