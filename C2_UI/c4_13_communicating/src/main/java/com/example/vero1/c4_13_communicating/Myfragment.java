package com.example.vero1.c4_13_communicating;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by vero on 2015/11/6.
 */
public class Myfragment extends Fragment {
    private Button button;
    private CheckBox checkBox;
    private String name;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment,container,false);
        button=(Button)view.findViewById(R.id.button);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        checkBox=(CheckBox)getActivity().findViewById(R.id.checkBox);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBox.isChecked()){
                    checkBox.setChecked(true);
                    button.setText("选中了");
                }else {
                    checkBox.setChecked(false);
                    button.setText("还没选中");
                }
            }
        });
    }

    public String getName() {
        return name;
    }
}
