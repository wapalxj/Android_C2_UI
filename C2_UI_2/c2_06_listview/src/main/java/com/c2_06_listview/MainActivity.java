package com.c2_06_listview;

/**
 * listview复习
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initData(){
        datas=new ArrayList<>();
        for (int num=0;num<1000;num++){
            datas.add("vero-"+num);
        }
    }
    private void initView() {
        lv= (ListView) findViewById(R.id.listview);
        lv.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("getView",position+"--->显示了");
            //动态代码
//            LinearLayout ll=new LinearLayout(MainActivity.this);
//            ll.setOrientation(LinearLayout.HORIZONTAL);
//            TextView tv=new TextView(MainActivity.this);
//            tv.setText(getItem(position).toString());
//            ImageView iv=new ImageView(MainActivity.this);
//            iv.setImageResource(R.mipmap.ic_launcher);
//            ll.addView(iv,80,80);
//            ll.addView(tv);
//            return ll;

            //XML加载
//            View view=View.inflate(MainActivity.this,R.layout.item,null);
//            ImageView iv= (ImageView) view.findViewById(R.id.iv);
//            iv.setImageResource(R.mipmap.head_1);
//            TextView tv= (TextView) view.findViewById(R.id.tv);
//            tv.setText(getItem(position).toString());
//            if (convertView!=null){
//                Log.i("getView---convertView",convertView.toString());
//            }
//            Log.i("getView---view",view.toString());
//            return view;

            //优化写法
            View view;
            ViewHolder viewHolder;
            if (convertView==null){
                view=View.inflate(MainActivity.this,R.layout.item,null);
                viewHolder=new ViewHolder();
                viewHolder.iv=(ImageView) view.findViewById(R.id.iv);
                viewHolder.tv=(TextView) view.findViewById(R.id.tv);
                view.setTag(viewHolder);
            }else {
                view=convertView;
                viewHolder= (ViewHolder) view.getTag();
            }
            viewHolder.iv.setImageResource(R.mipmap.head_1);
            viewHolder.tv.setText(getItem(position).toString());
            return view;
        }
    }
    class ViewHolder{
        ImageView iv;
        TextView tv;
    }

}
