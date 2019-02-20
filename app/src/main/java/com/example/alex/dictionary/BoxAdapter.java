package com.example.alex.dictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Type> objects;

    BoxAdapter(Context context, ArrayList<Type> type) {
        ctx = context;
        objects = type;

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = lInflater.inflate(R.layout.item, parent, false);
        }
        Type t = getType(position);
        ((TextView) view.findViewById(R.id.word)).setText(t.word);
        ((TextView) view.findViewById(R.id.translate)).setText(t.translate);
        return view;
    }

    private Type getType(int position) {
        return ((Type) getItem(position));
    }
}
