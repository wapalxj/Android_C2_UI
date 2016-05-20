package ui2.com.c2_01_fragment_first;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态创建
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.add(R.id.f1,new ContactFragment());
        transaction.add(R.id.f2,new DiscoveryFragment());
        transaction.add(R.id.f3,new WxFragment());
        transaction.commit();
    }
}
