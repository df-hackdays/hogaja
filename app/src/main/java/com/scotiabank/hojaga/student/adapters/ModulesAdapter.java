package com.scotiabank.hojaga.student.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.models.ModulesInfo;

import java.util.ArrayList;

/**
 * Created by gauravmalvankar on 2018-09-28.
 */

public class ModulesAdapter extends ArrayAdapter<ModulesInfo> {

    private ArrayList<ModulesInfo> modules;
    Context mContext;
    Drawable[] background_drawables = new Drawable[6];

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView desc;
        RelativeLayout background;
    }

    public ModulesAdapter(ArrayList<ModulesInfo> modules, Context context) {
        super(context, R.layout.row_modules, modules);
        this.modules = modules;
        this.mContext = context;
        background_drawables[0] = mContext.getResources().getDrawable(R.drawable.modules_blue_row);
        background_drawables[1] = mContext.getResources().getDrawable(R.drawable.modules_purple_row);
        background_drawables[2] = mContext.getResources().getDrawable(R.drawable.modules_green_row);
        background_drawables[3] = mContext.getResources().getDrawable(R.drawable.modules_pink_row);
        background_drawables[4] = mContext.getResources().getDrawable(R.drawable.modules_yellow_row);

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
            viewHolder.title = convertView.findViewById(R.id.txt_title);
            viewHolder.desc = convertView.findViewById(R.id.txt_desc);
            viewHolder.background = convertView.findViewById(R.id.layout_background);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.title.setText(modules.get(position).getTitle().replaceAll("_"," "));
        viewHolder.desc.setText(modules.get(position).getTitle());
        viewHolder.background.setBackground(background_drawables[position]);

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.title.setText(modules.get(position).getTitle());
        // Return the completed view to render on screen
        return convertView;
    }
}
