package com.truffs.firebaseatmokapp;

/**
 * Created by Luca on 4/26/15.
 */
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Query;

import java.text.SimpleDateFormat;
import java.util.Date;


public class HistoryListAdapter extends FirebaseListAdapter<Device> {

    public HistoryListAdapter(Query ref, Activity activity, int layout) {
        super(ref, Device.class, layout, activity);
    }

    @Override
    protected void populateView(View view, Device device) {
        // Map a Chat object to an entry in our listview
        String name = device.getName();
        String timestampIn = device.getTimestampIn();
        String timestampOut = device.getTimestampOut();

        TextView nameText = (TextView) view.findViewById(R.id.historyDeviceName);
        TextView timestampInText = (TextView) view.findViewById(R.id.historyDeviceTimestampIn);
        TextView timestampOutText = (TextView) view.findViewById(R.id.historyDeviceTimestampOut);
        nameText.setText(name);
        if (name.indexOf(":") >= 0) {
            nameText.setTextColor(Color.RED);
        } else {
            nameText.setTextColor(Color.BLUE);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy 'at' HH:mm:ss");
        if(timestampIn.equals("unknown")){
            timestampInText.setText(timestampIn);
        } else {
            timestampInText.setText(sdf.format(new Date(Long.valueOf(timestampIn).longValue())));
        }
        if(timestampOut != null) {
            if (timestampOut.equals("unknown")) {
                timestampOutText.setText(timestampOut);
            } else {
                timestampOutText.setText(sdf.format(new Date(Long.valueOf(timestampOut).longValue())));
            }
        }
    }
}
