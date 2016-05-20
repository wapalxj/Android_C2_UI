package ui2.com.c2_02_fragment_weixin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_contact;
    private Button btn_discovery;
    private Button btn_wx;
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mManager = getFragmentManager();
        mTransaction = mManager.beginTransaction();
        mTransaction.add(R.id.fragment,new ContactFragment());
        mTransaction.commit();
        initView();
    }

    private void initView() {
        btn_contact= (Button) findViewById(R.id.btn_contact);
        btn_discovery= (Button) findViewById(R.id.btn_discovery);
        btn_wx= (Button) findViewById(R.id.btn_wx);
        btn_contact.setOnClickListener(this);
        btn_discovery.setOnClickListener(this);
        btn_wx.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        mTransaction = mManager.beginTransaction();
        switch (v.getId()){
            case R.id.btn_contact:
                mTransaction.replace(R.id.fragment,new ContactFragment());
                break;
            case R.id.btn_discovery:
                mTransaction.replace(R.id.fragment,new DiscoveryFragment());
                break;
            case R.id.btn_wx:
                mTransaction.replace(R.id.fragment,new WxFragment());
                break;
            default:
                break;
        }
        mTransaction.commit();
    }
}
