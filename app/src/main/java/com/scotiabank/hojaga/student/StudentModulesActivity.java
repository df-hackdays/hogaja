package com.scotiabank.hojaga.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.scotiabank.hojaga.FirebaseUtility;
import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.adapters.ModulesAdapter;
import com.scotiabank.hojaga.student.models.ModulesInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentModulesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //Hello
    @BindView(R.id.list_modules)
    ListView list_modules;
    @BindView(R.id.btn_help)
    Button btn_help;

    private ModulesAdapter modulesAdapter;
    private ArrayList<ModulesInfo> modulesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_modules);
        ButterKnife.bind(this);
        readModuleFromFirebase();
        setModules();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private void setModules() {
        ModulesInfo modulesInfo = new ModulesInfo();
        modulesInfo.setTitle("Title");
        modulesList.add(modulesInfo);

        modulesAdapter = new ModulesAdapter(modulesList, this);
        list_modules.setAdapter(modulesAdapter);
    }

    @OnClick(R.id.btn_help)
    void OnHelpClick() {
        startActivity(new Intent(StudentModulesActivity.this, StudentModulesActivity.class));
        readModuleFromFirebase();
    }

    void readModuleFromFirebase() {
        // Read from the database
        FirebaseUtility.getModulesReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object resultObject =  dataSnapshot.getValue();
                String stringObject = resultObject.toString();
                Gson gson = new Gson();
                ModulesInfo[] founderArray = gson.fromJson(stringObject, ModulesInfo[].class);

                for (ModulesInfo module: founderArray) {
                    Log.d("modules", "Module is: " + module.getTitle());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Test fail", "Failed to read value.", error.toException());
            }
        });
    }
}
