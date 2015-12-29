package c4_4_fragment_04_replace_and_remove;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import c4_4_fragment_04_replace_and_remove.R;

/**
 * Created by vero on 2015/11/5.
 */
public class FirstFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.firstframent,container,false);
    }
}
