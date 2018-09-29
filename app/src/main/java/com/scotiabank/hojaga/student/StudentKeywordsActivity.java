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
import android.widget.ListView;
import android.widget.TextView;

import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.adapters.KeywordsAdapter;
import com.scotiabank.hojaga.student.adapters.ModulesAdapter;
import com.scotiabank.hojaga.student.models.ModulesInfo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentKeywordsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //Hello
    @BindView(R.id.grid_definitions)
    GridView grid_definitions;
    @BindView(R.id.btn_help)
    FloatingActionButton btn_help;

    private KeywordsAdapter keywordsAdapter;
    private ArrayList<ModulesInfo> definitionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_keywords);
        ButterKnife.bind(this);

        setModules();
        grid_definitions.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showDefinition();
    }

    private void setModules() {
        ModulesInfo modulesInfo = new ModulesInfo();
        modulesInfo.setTitle("Title");
        definitionsList.add(modulesInfo);
        definitionsList.add(modulesInfo);
        definitionsList.add(modulesInfo);
        definitionsList.add(modulesInfo);
        definitionsList.add(modulesInfo);
        definitionsList.add(modulesInfo);

        keywordsAdapter = new KeywordsAdapter( this, definitionsList);
        grid_definitions.setAdapter(keywordsAdapter);
    }

    @OnClick(R.id.btn_help)
    void OnHelpClick() {
        startActivity(new Intent(StudentKeywordsActivity.this, StudentFeedbacActivity.class));
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
}
