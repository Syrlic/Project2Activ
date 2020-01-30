package com.example.project2activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project2activity.R;
import com.example.project2activity.pojo.FamilyNames;

import java.util.List;

public class NameAdapter extends BaseAdapter {

    private List<FamilyNames> names;
    private LayoutInflater layoutInflater;

    public NameAdapter(Context context, List<FamilyNames> names) {
        this.names = names;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = layoutInflater.inflate(R.layout.names_layout, parent,false);
        }

        FamilyNames names = (FamilyNames) getItem(position);
        TextView surname = (TextView) view.findViewById(R.id.surname);
        TextView name = (TextView) view.findViewById(R.id.name);
        TextView middlername = (TextView) view.findViewById(R.id.middlename);

        surname.setText("Surname: "+ names.getSurname());
        name.setText("Name: "+ names.getName());
        middlername.setText("Middlename: "+ names.getMiddlename());

        return view;
    }
}
