package ui2.com.c2_05_fragment_communication;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by vero on 2016/4/10.
 */
public class Fragment2 extends Fragment {

    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmen2,null);
        textView = (TextView) view.findViewById(R.id.tv1);
        return view;
    }

    public void setText(String text){
        textView.setText(text);
    }


}
