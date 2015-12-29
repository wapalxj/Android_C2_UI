package com.example.c4_18_communitating2_listfragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/11/8.
 */
public class Edit_Fragment extends Fragment {
    EditText editText;
    AddItem addItem;
    public interface AddItem{
        void add(String item);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.edit_fragment,container,false);
        editText=(EditText)view.findViewById(R.id.edittext);
         editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        addItem.add(editText.getText().toString());
                        editText.setText("");
                    }
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        addItem=(AddItem)activity;
    }
}
