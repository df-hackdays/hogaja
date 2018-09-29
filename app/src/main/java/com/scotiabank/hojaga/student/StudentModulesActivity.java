package com.scotiabank.hojaga.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.scotiabank.hojaga.FirebaseUtility;
import com.scotiabank.hojaga.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentModulesActivity extends AppCompatActivity {

    //Hello
    @BindView(R.id.list_modules)
    ListView list_modules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_modules);
        ButterKnife.bind(this);
        readModuleFromFirebase();
    }

    void readModuleFromFirebase() {
        // Read from the database
        FirebaseUtility.getModulesReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("Test", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Test fail", "Failed to read value.", error.toException());
            }
        });
    }
}
