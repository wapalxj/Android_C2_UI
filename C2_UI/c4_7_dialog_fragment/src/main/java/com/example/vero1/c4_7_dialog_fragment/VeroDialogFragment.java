package com.example.vero1.c4_7_dialog_fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.YuvImage;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Administrator on 2015/11/5.
 */
public class VeroDialogFragment extends DialogFragment {

    //建立一个静态方法，实例化自己，并且给自己设置参数
    public static VeroDialogFragment getInatance(int type){
        VeroDialogFragment veroDialogFragment =new VeroDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("Dialog type",type);
        veroDialogFragment.setArguments(bundle);
        return veroDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle =getArguments();
        int type=bundle.getInt("Dialog type");

        switch (type){
            case MainActivity.ALERT_DIALOG_TYPE:
                //fragment中的getActivity()可以寻找到fragment附着的Activity引用
                return  new AlertDialog.Builder(getActivity())
                        .setTitle(getTag())
                        .setIcon(R.drawable.rabtn2)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "OK", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    dismiss();
                            }
                        })
                        .create();
            case MainActivity.DATA_DIALOG_TYPE:
                Calendar c=Calendar.getInstance();
                int year =c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_MONTH);
                return new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Toast.makeText(getActivity(), year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日", Toast.LENGTH_SHORT).show();
                        ((TextView)getActivity().findViewById(R.id.textView)).setText( year+"年"+(monthOfYear+1)+"月"+dayOfMonth+"日");
                    }

                }, year,month,day);
            case MainActivity.TIME_DIALOG_TYPE:
                Calendar c2=Calendar.getInstance();
                int hour=c2.get(Calendar.HOUR_OF_DAY);
                int min=c2.get(Calendar.MINUTE);
                return  new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(getActivity(), hourOfDay+"时"+minute+"分", Toast.LENGTH_SHORT).show();
                        ((TextView)getActivity().findViewById(R.id.textView)).setText(hourOfDay+"时"+minute+"分");
                    }
                },hour,min,true);
            default:

                break;
        }
        return null;
    }
}
