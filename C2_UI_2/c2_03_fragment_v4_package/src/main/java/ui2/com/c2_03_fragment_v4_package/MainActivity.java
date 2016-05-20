package ui2.com.c2_03_fragment_v4_package;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 兼容低版本(3.0以下)写法:使用v4包
 */
public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //动态创建
        //v4包获取manager的方式:获取v4的support
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();

        transaction.add(R.id.f11,new ContactFragment());
        transaction.add(R.id.f22,new DiscoveryFragment());
        transaction.add(R.id.f33,new WxFragment());
        transaction.commit();
        }
    }
