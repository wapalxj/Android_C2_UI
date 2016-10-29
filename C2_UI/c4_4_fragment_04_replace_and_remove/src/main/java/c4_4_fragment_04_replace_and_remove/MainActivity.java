package c4_4_fragment_04_replace_and_remove;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import c4_4_fragment_04_replace_and_remove.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button replace=(Button)findViewById(R.id.replace);
        Button remove=(Button)findViewById(R.id.remove);

        final  Fragment firstFragment=new FirstFragment();
        getFragmentManager().beginTransaction().add(R.id.container,firstFragment).commit();

        replace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.container, new SecondFragment()).addToBackStack(null).commit();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(firstFragment).commit();
            }
        });
    }
}
