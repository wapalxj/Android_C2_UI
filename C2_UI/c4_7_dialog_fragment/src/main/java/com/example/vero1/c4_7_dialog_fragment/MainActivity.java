package com.example.vero1.c4_7_dialog_fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private Button button;
    private VeroDialogFragment dialogFragment;

    public static final int ALERT_DIALOG_TYPE=1;
    public static final int DATA_DIALOG_TYPE=2;
    public static final int TIME_DIALOG_TYPE=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup=(RadioGroup)findViewById(R.id.radio);
        button=(Button)findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId=radioGroup.getCheckedRadioButtonId();
                switch (radioId) {
                    case R.id.alertFragment:
                         dialogFragment=VeroDialogFragment.getInatance(ALERT_DIALOG_TYPE);
                        break;
                    case R.id.datatFragment:
                        dialogFragment=VeroDialogFragment.getInatance(DATA_DIALOG_TYPE);
                        break;
                    case R.id.timeFragment:
                        dialogFragment=VeroDialogFragment.getInatance(TIME_DIALOG_TYPE);
                        break;
                    default:
                        break;
                }

                if (dialogFragment!=null){
                        dialogFragment.show(getFragmentManager(),"info");//Tag这里设置
                }
            }
        });



    }
}
