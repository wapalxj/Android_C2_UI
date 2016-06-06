package vero.com.c3_self_03_spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {
    private SpinnerView sv;
    private List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        datas=new ArrayList<>();
        for (int i=0;i<200;i++){
            datas.add(1000+i+"");
        }

        sv= (SpinnerView) findViewById(R.id.sv);
        sv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data=datas.get(position);
                sv.setText(data);
                sv.setSelection(data.length());
                sv.dismiss();
            }
        });
        sv.setAdapter(new MyAdapter());

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            if (datas!=null){
                return datas.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view=null;
            ViewHolder holder=null;
            if (convertView==null){
                view=View.inflate(DemoActivity.this,R.layout.item,null);
                holder=new ViewHolder();
                holder.ivDelete= (ImageView) view.findViewById(R.id.iv_delete);
                holder.ivUser= (ImageView) view.findViewById(R.id.iv_user);
                holder.tvTitle= (TextView) view.findViewById(R.id.tv_title);
                view.setTag(holder);
            }else {
                view=convertView;
                holder= (ViewHolder) view.getTag();
            }
            holder.tvTitle.setText(datas.get(position));
            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(datas.get(position));
                    notifyDataSetChanged();
                }
            });
            return view;
        }
    }

    class ViewHolder{
        ImageView ivUser;
        ImageView ivDelete;
        TextView tvTitle;
    }
}
