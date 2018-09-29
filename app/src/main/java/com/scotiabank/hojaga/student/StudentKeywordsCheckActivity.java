package com.scotiabank.hojaga.student;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.scotiabank.hojaga.FirebaseUtility;
import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.adapters.KeywordsAdapter;
import com.scotiabank.hojaga.student.adapters.KeywordsCheckAdapter;
import com.scotiabank.hojaga.student.models.Keywords;
import com.scotiabank.hojaga.student.models.ModulesInfo;

import java.io.StringReader;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentKeywordsCheckActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //Hello
    @BindView(R.id.grid_definitions)
    GridView grid_definitions;
    @BindView(R.id.btn_submit)
    Button btn_submit;

    private KeywordsCheckAdapter keywordsAdapter;
    private ArrayList<ModulesInfo> modulesList = new ArrayList<>();
    private ArrayList<Keywords> keywordsArrayList = new ArrayList<>();
    ModulesInfo[] modulesArray;

    private int selectedModule = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_keywords_check);
        ButterKnife.bind(this);

        readModuleFromFirebase();
        grid_definitions.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        keywordsArrayList.get(position).setSelected(!keywordsArrayList.get(position).isSelected());
        keywordsAdapter.notifyDataSetChanged();
    }

    private void setKeywords() {
        for(ModulesInfo modulesInfo1 : modulesArray){
            modulesList.add(modulesInfo1);
        }

        Keywords[] keywordsArray = modulesList.get(selectedModule).getKeywords();
        for(Keywords keywords : keywordsArray){
            keywordsArrayList.add(keywords);
        }
        keywordsAdapter = new KeywordsCheckAdapter( this, keywordsArrayList);
        grid_definitions.setAdapter(keywordsAdapter);
    }

    @OnClick(R.id.btn_submit)
    void OnSubmitClick() {
        startActivity(new Intent(StudentKeywordsCheckActivity.this, StudentSummaryActivity.class));
    }

    void showDefinition(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_definition);
        dialog.setTitle("Title...");

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.txt_title);
        text.setText("Android custom dialog example!");


        dialog.show();
    }

    void readModuleFromFirebase() {
        // Read from the database
        FirebaseUtility.getModulesReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Object resultObject = dataSnapshot.getValue();
                String stringObject = resultObject.toString();
                JsonReader jr = new JsonReader(new StringReader(stringObject.trim()));
                jr.setLenient(true);
                Gson gson = new Gson();
                modulesArray = gson.fromJson(stringObject, ModulesInfo[].class);


                setKeywords();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Test fail", "Failed to read value.", error.toException());
            }
        });
    }
}
