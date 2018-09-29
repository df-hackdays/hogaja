package com.scotiabank.hojaga.student;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.scotiabank.hojaga.FirebaseUtility;
import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.adapters.KeywordsAdapter;
import com.scotiabank.hojaga.student.models.Keywords;
import com.scotiabank.hojaga.student.models.ModulesInfo;

import java.io.StringReader;
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
    @BindView(R.id.layout_help)
    RelativeLayout layout_help;

    private KeywordsAdapter keywordsAdapter;
    private ArrayList<ModulesInfo> modulesList = new ArrayList<>();
    private ArrayList<Keywords> keywordsArrayList = new ArrayList<>();
    private BroadcastReceiver pollBroadcastReceiver;
    ModulesInfo[] modulesArray;

    private int selectedModule = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_keywords);
        ButterKnife.bind(this);

        readModuleFromFirebase();
        grid_definitions.setOnItemClickListener(this);

        // poll activity broadcast receiver
        pollBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("poll", "Received poll intent");
                showNotification();
            }
        };
        registerReceiver(pollBroadcastReceiver, new IntentFilter(Constants.POLL_BROADCAST));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(pollBroadcastReceiver);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showDefinition(position);
    }

    private void setKeywords() {
        for(ModulesInfo modulesInfo1 : modulesArray){
            modulesList.add(modulesInfo1);
        }

        Keywords[] keywordsArray = modulesList.get(selectedModule).getKeywords();
        for(Keywords keywords : keywordsArray){
            keywordsArrayList.add(keywords);
        }
        keywordsAdapter = new KeywordsAdapter( this, keywordsArrayList);
        grid_definitions.setAdapter(keywordsAdapter);
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
    }

    void showDefinition(int position){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_definition);

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.txt_title);
        text.setText(keywordsArrayList.get(position).getName());
        TextView text2 = (TextView) dialog.findViewById(R.id.txt_desc);
        text2.setText(keywordsArrayList.get(position).getDefinition());

        ImageView img_clear = dialog.findViewById(R.id.img_clear);
        img_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    void showNotification(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        TextView btn_lets_do_it = dialog.findViewById(R.id.btn_lets_do_it);
        btn_lets_do_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentKeywordsActivity.this, StudentFeedbackActivity.class));
            }
        });

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
