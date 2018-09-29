package com.scotiabank.hojaga.student.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scotiabank.hojaga.R;
import com.scotiabank.hojaga.student.models.ModulesInfo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by gauravmalvankar on 2018-09-28.
 */

public class KeywordsAdapter extends BaseAdapter{

    Context context;
    ArrayList<ModulesInfo> keywordsList;
    LayoutInflater inflter;

    Drawable[] blue_background = new Drawable[2];
    Drawable[] orange_background = new Drawable[2];
    Drawable[] purple_background = new Drawable[2];
    Drawable[] green_background = new Drawable[2];
    Drawable[] pink_background = new Drawable[2];
    Drawable[] yellow_background = new Drawable[2];

    private int COLOR = 0;
    private int counter = 3;

    int[] color_index;

    public KeywordsAdapter(Context applicationContext, ArrayList<ModulesInfo> keywordsList) {
        this.context = applicationContext;
        this.keywordsList = keywordsList;
        inflter = (LayoutInflater.from(applicationContext));

        color_index = new int[keywordsList.size()];

        blue_background[0] = context.getResources().getDrawable(R.drawable.modules_blue_row);
        blue_background[1] = context.getResources().getDrawable(R.drawable.keyword_blue_tile);

        orange_background[0] = context.getResources().getDrawable(R.drawable.modules_blue_row);
        orange_background[1] = context.getResources().getDrawable(R.drawable.keyword_blue_tile);

        purple_background[0] = context.getResources().getDrawable(R.drawable.modules_blue_row);
        purple_background[1] = context.getResources().getDrawable(R.drawable.keyword_blue_tile);

        green_background[0] = context.getResources().getDrawable(R.drawable.modules_blue_row);
        green_background[1] = context.getResources().getDrawable(R.drawable.keyword_blue_tile);

        pink_background[0] = context.getResources().getDrawable(R.drawable.modules_blue_row);
        pink_background[1] = context.getResources().getDrawable(R.drawable.keyword_blue_tile);

        yellow_background[0] = context.getResources().getDrawable(R.drawable.modules_blue_row);
        yellow_background[1] = context.getResources().getDrawable(R.drawable.keyword_blue_tile);
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

        RelativeLayout background = view.findViewById(R.id.background);

        switch(COLOR){
            case 0:
               /* Random rand = new Random();
                int x = rand.nextInt();

                if(x%2 == 0)
                    background.setBackground(orange_background[0]);
                else
                    background.setBackground(orange_background[1]);*/

                background.setBackground(orange_background[0]);
                color_index[i] = 0;
                if(i>0){
                    if(counter<2){
                        background.setBackground(blue_background[color_index[i-1]]);
                        color_index[i] = color_index[i-1];
                        counter++;
                    }
                    else{
                        if(color_index[i-1] == 0){
                            background.setBackground(blue_background[1]);
                            color_index[i] = 1;
                        }
                        else{
                            background.setBackground(blue_background[0]);
                            color_index[i] = 0;
                        }
                        counter=1;
                    }

                }



                break;

            case 1:
                if(i%2 == 0)
                    background.setBackground(orange_background[0]);
                else
                    background.setBackground(orange_background[1]);

                break;

            case 2:
                if(i%2 == 0)
                    background.setBackground(purple_background[0]);
                else
                    background.setBackground(purple_background[1]);

                break;

            case 3:
                if(i%2 == 0)
                    background.setBackground(green_background[0]);
                else
                    background.setBackground(green_background[1]);

                break;

            case 4:
                if(i%2 == 0)
                    background.setBackground(pink_background[0]);
                else
                    background.setBackground(pink_background[1]);

                break;

            case 5:
                if(i%2 == 0)
                    background.setBackground(yellow_background[0]);
                else
                    background.setBackground(yellow_background[1]);

                break;
        }


        return view;
    }
}
