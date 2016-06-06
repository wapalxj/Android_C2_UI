package vero.com.c3_self_06_swipedelete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mDatas;
    private List<SwipeView> mOpenedViews=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView= (ListView) findViewById(R.id.lv);
        mDatas=new ArrayList<>();
        for (int i=0;i<200;i++){
            mDatas.add("vero"+i);
        }
        mListView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            if (mDatas==null){
                return 0;
            }
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
            if (mDatas==null){
                return null;
            }
            return mDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            View view;
            if (convertView==null){
                view=View.inflate(MainActivity.this,R.layout.item,null);
                holder=new ViewHolder();
                holder.sv= (SwipeView) view.findViewById(R.id.swipeView);
                holder.content= (TextView) view.findViewById(R.id.tv_content);
                holder.delete= (TextView) view.findViewById(R.id.tv_delete);

                view.setTag(holder);
            }else {
                view=convertView;
                holder= (ViewHolder) view.getTag();
            }
            final String data=mDatas.get(position);
            holder.content.setText(data);
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDatas.remove(data);
                    closeAll();
                    notifyDataSetChanged();
                }
            });

            holder.sv.setOnSwipeListener(new SwipeView.OnSwipeListener() {
                @Override
                public void onSwipeChanged(SwipeView sv,boolean isOpened) {
                    if (isOpened){
                        //打开的，则记录下来
                        if (!mOpenedViews.contains(sv)){
                            mOpenedViews.add(sv);
                        }
                    }else {
                        mOpenedViews.remove(sv);
                    }
                }
            });
            return view;
        }
    }

    class ViewHolder{
        SwipeView sv;
        TextView content;
        TextView delete;

    }

    public void closeAll(){
        ListIterator<SwipeView> iterator=mOpenedViews.listIterator();
        while (iterator.hasNext()){
            SwipeView sv=iterator.next();
            sv.close();
        }
    }
}
