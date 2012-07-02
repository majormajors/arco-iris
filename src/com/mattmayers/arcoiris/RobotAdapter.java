package com.mattmayers.arcoiris;

import orbotix.robot.base.Robot;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RobotAdapter extends ArrayAdapter<Robot> {
    public RobotAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TextView textView = new TextView(getContext());
        textView.setTextAppearance(getContext(), android.R.attr.textAppearanceLarge);
        textView.setText(getItem(position).getName());
        return textView;
    }
}
