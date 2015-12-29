package com.example.c3_65_self_define;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2015/11/8.
 */
public class SubButton extends MyButton {
    public SubButton(Context context) {
        super(context);
    }

    @Override
    protected void disPlayName() {
        super.disPlayName();
        Toast.makeText(getContext(),"ssssssssss",Toast.LENGTH_SHORT).show();
    }
}
