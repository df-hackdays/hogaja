package com.scotiabank.hojaga.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.scotiabank.hojaga.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentLandingActivity extends AppCompatActivity {


    @BindView(R.id.btn_next)
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_landing);
        ButterKnife.bind(this);

        // subscribe to poll topics for group notification
        FirebaseMessaging.getInstance().subscribeToTopic("poll")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Log.d("FAIL", "FAILED SUBSCRIPTION");
                        }
                        Log.d("PASS", "PASSED SUBSCRIPTION");
                    }
                });
    }

    @OnClick(R.id.btn_next)
    void OnNextClick() {
        startActivity(new Intent(StudentLandingActivity.this, StudentWelcomeActivity.class));
    }
}
