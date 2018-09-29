package com.scotiabank.hojaga.student.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.models.ModulesInfo;

import java.util.ArrayList;

/**
 * Created by gauravmalvankar on 2018-09-28.
 */

public class ModulesAdapter extends ArrayAdapter<ModulesInfo>{

    private ArrayList<ModulesInfo> modules;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
       TextView title;
    }

    public ModulesAdapter(ArrayList<ModulesInfo> modules, Context context) {
        super(context, R.layout.row_modules, modules);
        this.modules = modules;
        this.mContext=context;

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
      //  DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_modules, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.txt_title);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.title.setText(modules.get(position).getTitle());
        // Return the completed view to render on screen
        return convertView;
    }
}
