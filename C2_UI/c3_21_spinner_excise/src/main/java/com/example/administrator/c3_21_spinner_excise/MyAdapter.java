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
        View view=null;
        ViewHolder holder;
        if (convertView==null){
            view=inflater.inflate(this.resource, null);
            holder=new ViewHolder();
            holder.textView=(TextView)view.findViewById(R.id.textView);
            holder.textView2=(TextView)view.findViewById(R.id.textView2);
            view.setTag(holder);
        }else {
            view=convertView;
            holder= (ViewHolder) view.getTag();
        }
        User u=(User)getItem(position);
        holder.textView.setText(u.getName().toString());
        holder.textView2.setText(u.getAge()+"");
        return view;
    }
    class ViewHolder{
        TextView textView;
        TextView textView2;
    }

}
