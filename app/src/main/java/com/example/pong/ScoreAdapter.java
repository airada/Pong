package com.example.pong;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreAdapter extends ArrayAdapter<Score> {
    private Context mContext;
    private List<Score> ScoreList = new ArrayList<Score>();

    public ScoreAdapter(Context context, ArrayList<Score> list)
    {
        super( context, 0, list);
        mContext = context;
        ScoreList = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        // Associates the list with the XML Layout file "Score_view"
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.score_view,parent,false);

        // Individually handles each Score in the ScoreList
        Score currentScore = ScoreList.get(position);

        // Extracts the name of the Score
        TextView name = (TextView) listItem.findViewById(R.id.textView_Name);
        name.setText(currentScore.getName());

        // Extracts the phone number of the Score
        TextView number = (TextView) listItem.findViewById(R.id.textView_Number);
        number.setText(Integer.toString(currentScore.getNumber()));

        return listItem;
    }
}
