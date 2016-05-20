package graduationdesign.muguihai.com.c4_19_hm74_weixin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_contact=(Button)findViewById(R.id.btn_contact);
        Button btn_find=(Button)findViewById(R.id.btn_find);
        Button btn_wx=(Button)findViewById(R.id.btn_wx);

        btn_contact.setOnClickListener(this);
        btn_find.setOnClickListener(this);
        btn_wx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        switch (v.getId()){

            case R.id.btn_contact:
                transaction.replace(R.id.ll,new ContactFragment());
                break;
            case R.id.btn_find :
                transaction.replace(R.id.ll,new FindFragment());
                break;
            case R.id.btn_wx :
                transaction.replace(R.id.ll,new WxFragment());
                break;
            default:
                break;
        }

        transaction.commit();
    }
}
