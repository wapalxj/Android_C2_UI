package ui2.com.c2_05_fragment_communication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vero on 2016/4/10.
 */
public class Fragment1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmen1,null);
        view.findViewById(R.id.btn1_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //修改fragment里面的textView的内容
                //通过公共的桥梁Activity找到需要通信的fragment
                Toast.makeText(getActivity(),"Fragment1",Toast.LENGTH_SHORT).show();
                Fragment2 f2=(Fragment2)getActivity().getFragmentManager().findFragmentByTag("f2");
                f2.setText("sadsadasd");

            }
        });
        return view;
    }
}
