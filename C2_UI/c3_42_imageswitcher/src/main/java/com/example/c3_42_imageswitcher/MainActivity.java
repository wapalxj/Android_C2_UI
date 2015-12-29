package com.example.c3_42_imageswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory{
    private int []mImages=new int[]{
            R.drawable.btnb,
            R.drawable.btng,
            R.drawable.btno,
            R.drawable.circledraw,
            R.drawable.seekbar,
            R.drawable.seekbar2,
            R.drawable.starfull,
            R.drawable.staroff,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //switcher
        final ImageSwitcher imageSwitcher=(ImageSwitcher)findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(this);
        imageSwitcher.setImageResource(mImages[0]);//设置默认的图片

        Gallery gallery=(Gallery)findViewById(R.id.gallery);
        final ImageAdapter imageAdapter=new ImageAdapter();
        gallery.setAdapter(imageAdapter);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_SHORT).show();
                    imageSwitcher.setImageResource(mImages[position]);
            }
        });
    }

    @Override
    public View makeView() {
        return new ImageView(this);
    }

    class ImageAdapter extends BaseAdapter {

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
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView _imageView=new ImageView(MainActivity.this);
            _imageView.setImageResource(mImages[position]);
            _imageView.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.FILL_PARENT,
                    Gallery.LayoutParams.FILL_PARENT));
            return _imageView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
}
