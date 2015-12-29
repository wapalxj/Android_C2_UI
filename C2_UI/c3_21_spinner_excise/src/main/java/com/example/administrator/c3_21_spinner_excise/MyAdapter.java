package com.example.administrator.c3_21_spinner_excise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Administrator on 2015/9/23.
 */
public class MyAdapter <T>extends BaseAdapter {
    private Context context;
    private List<T> list;
    private LayoutInflater inflater;
    private int resource;

    public MyAdapter(Context context, List<T> ele,int resource) {
        this.context = context;
        this.list = ele;
        this.inflater = LayoutInflater.from(this.context);
        this.resource=resource;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=this.inflater.inflate(this.resource, null);
        TextView textView=(TextView)convertView.findViewById(R.id.textView);
        TextView textView2=(TextView)convertView.findViewById(R.id.textView2);
        User u=(User)getItem(position);
        textView.setText(u.getName().toString());
        textView2.setText(u.getAge()+"");


        return convertView;
    }

}
