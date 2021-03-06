package com.scotiabank.hojaga.student;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.scotiabank.hojaga.FirebaseUtility;
import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.adapters.ModulesAdapter;
import com.scotiabank.hojaga.student.models.Keywords;
import com.scotiabank.hojaga.student.models.ModulesInfo;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentModulesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //Hello
    @BindView(R.id.list_modules)
    ListView list_modules;
    @BindView(R.id.btn_help)
    ImageView btn_help;
    @BindView(R.id.layout_help)
    RelativeLayout layout_help;


    private ModulesAdapter modulesAdapter;
    private ArrayList<ModulesInfo> modulesList = new ArrayList<>();
    ModulesInfo[] modulesArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_modules);
        ButterKnife.bind(this);
        readModuleFromFirebase();
        list_modules.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ModulesInfo module = modulesArray[position];
        Keywords[] keywords = module.getKeywords();
        startActivity(new Intent(StudentModulesActivity.this, StudentKeywordsActivity.class));
    }

    private void setModules() {

        Log.d("TAG","modules; "+modulesArray.length);
        for(ModulesInfo modulesInfo1 : modulesArray){
            modulesList.add(modulesInfo1);
        }
        modulesAdapter = new ModulesAdapter(modulesList, this);
        list_modules.setAdapter(modulesAdapter);
    }

    @OnClick(R.id.btn_help)
    void OnHelpClick() {
        btn_help.setVisibility(View.GONE);
        layout_help.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout_help.setVisibility(View.GONE);
                btn_help.setVisibility(View.VISIBLE);
            }
        }, 30000);
    //    readModuleFromFirebase();
    }

    void readModuleFromFirebase() {
        // Read from the database
        FirebaseUtility.getModulesReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object resultObject =  dataSnapshot.getValue();
                String stringObject = resultObject.toString();
                JsonReader jr = new JsonReader(new StringReader(stringObject.trim()));
                jr.setLenient(true);
                Gson gson = new Gson();
                modulesArray = gson.fromJson(stringObject, ModulesInfo[].class);
                setModules();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Test fail", "Failed to read value.", error.toException());
            }
        });
    }
}
