/**
 * 
 */
package com.mattmayers.arcoiris;

import orbotix.robot.base.Robot;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * @author matt
 *
 */
public class RobotAdapter extends ArrayAdapter<Robot> {

    public RobotAdapter(Context context, int textViewResourceId, Robot[] robots) {
        super(context, textViewResourceId, robots);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    }

}
