package vero.com.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 出现混乱的原因
 */
public class CauseActivity extends AppCompatActivity {
    private ArrayList<String> mStrings;
    private String mStrData;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cause);
        mStrings=new ArrayList<>();
        for (int i=0;i<10;i++){
            mStrings.add("vero"+i+""+i+""+i+""+i);
        }

        mListView= (ListView) findViewById(R.id.lv);
        mListView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mStrings.size();
        }

        @Override
        public Object getItem(int position) {
            return mStrings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHolder holder=null;
            if (convertView==null){
                view = LayoutInflater.from(CauseActivity.this).inflate(R.layout.cause_item, null);
                holder = new ViewHolder();
                holder.textView = (TextView) view.findViewById(R.id.tv1);
                holder.button = (Button) view.findViewById(R.id.btn);
                view.setTag(holder);
            }else {
                view=convertView;
                holder= (ViewHolder) convertView.getTag();
            }

            //这里拿出来数据集合里的当前这一项mStrData
            mStrData = mStrings.get(position);//错误根源：每调用一次getView(),这里都会重新赋值一次！！！！
                                              //所以mStrData的值为最后一次调用的getView()产生的
            holder.textView.setText(mStrData);
            holder.button.setText("点击");


            //这里给item的button设置了点击监听事件
//            holder.button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //这里toast出来的mStrData却不是点击的那一项
//
//                    Toast.makeText(CauseActivity.this, "您点击了-" + mStrData, Toast.LENGTH_LONG).show();
//                }
//            });

            //解决
            holder.button.setOnClickListener(new View.OnClickListener() {
                int pos=position;//将当前位置记录下来
                @Override
                public void onClick(View v) {
                    Toast.makeText(CauseActivity.this, "您点击了-" + mStrings.get(pos), Toast.LENGTH_LONG).show();
                }
            });
            return view;
        }

        class ViewHolder {
            TextView textView;
            Button button;
        }
    }
}
