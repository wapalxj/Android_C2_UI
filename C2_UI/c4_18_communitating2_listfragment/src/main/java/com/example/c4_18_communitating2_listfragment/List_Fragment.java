package com.example.c4_18_communitating2_listfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public class List_Fragment extends ListFragment {
    ArrayList<String> data;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data =new ArrayList<>();
        adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,data);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public List<String> getData() {
        return data;
    }


    public ArrayAdapter<String> getAdapter() {
        return adapter;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(), "" + adapter.getItem(position), Toast.LENGTH_SHORT).show();
    }
}
