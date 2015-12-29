package com.example.administrator.c2_3_14_wiget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  TextView textView;
    private int []mImages=new int[]{
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.image_btn1111,
            R.drawable.qqq
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView=(GridView)findViewById(R.id.gridView);
        textView=(TextView)findViewById(R.id.textView);
        //数据源
        //adapter
        //bind
        final ImageAdapter imageAdapter=new ImageAdapter();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        btn_0_C(textView);
                        break;
                    case 1:
                        btn_1_1_(textView);
                        break;
                }

            }
        });
        gridView.setAdapter(imageAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    class ImageAdapter extends BaseAdapter {
//        private  ImageButton imageButton;
        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public Object getItem(int position) {
            return mImages[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
//            imageButton=new ImageButton(MainActivity.this);
//            imageButton.setBackgroundResource(mImages[position]);
//            imageButton.setClickable(true);
            ImageView _imageView=new ImageView(MainActivity.this);
            _imageView.setImageResource(mImages[position]);
            return _imageView;
        }



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btn_0_C(TextView textView){
        if (textView.getText().length()>=1){
            textView.setText((textView.getText()).subSequence(0,textView.getText().length()-1));
        }else{
            return;
        }
    }
    public void btn_1_1_(TextView textView){
        textView.append("1");
    }
}
