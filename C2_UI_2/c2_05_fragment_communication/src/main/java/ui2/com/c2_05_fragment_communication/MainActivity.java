package ui2.com.c2_05_fragment_communication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();

        transaction.replace(R.id.ll1,new Fragment1(),"f1");//指定tag
        transaction.replace(R.id.ll2,new Fragment2(),"f2");//指定tag
        transaction.commit();
    }
}
