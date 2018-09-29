package com.scotiabank.hojaga.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.adapters.ModulesAdapter;
import com.scotiabank.hojaga.student.models.ModulesInfo;

import java.util.ArrayList;

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
    }
}
