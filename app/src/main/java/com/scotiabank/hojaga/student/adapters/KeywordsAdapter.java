package com.scotiabank.hojaga.student.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.models.ModulesInfo;

import java.util.ArrayList;

/**
 * Created by gauravmalvankar on 2018-09-28.
 */

public class KeywordsAdapter extends BaseAdapter{

    Context context;
    ArrayList<ModulesInfo> keywordsList;
    LayoutInflater inflter;
    public KeywordsAdapter(Context applicationContext, ArrayList<ModulesInfo> keywordsList) {
        this.context = applicationContext;
        this.keywordsList = keywordsList;
        inflter = (LayoutInflater.from(applicationContext));
    }
    @Override
    public int getCount() {
        return keywordsList.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.tiles_cards, null); // inflate the layout
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title); // get the reference of ImageView
        txt_title.setText(keywordsList.get(i).getTitle()); // set logo images
        return view;
    }
}
