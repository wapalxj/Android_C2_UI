package vero.com.c3_self_03_spinner;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_input;
    private ImageView iv_arrow;
    private List<String> datas;
    private PopupWindow window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_input= (EditText) findViewById(R.id.et_input);
        iv_arrow= (ImageView) findViewById(R.id.iv_arrow);
        iv_arrow.setOnClickListener(this);

        datas=new ArrayList<>();
        for (int i=0;i<200;i++){
            datas.add(1000+i+"");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_arrow:
                clickArrow();
            default:
                break;
        }
    }

    private void clickArrow() {
        if (window==null){
            ListView contentView=new ListView(this);
            contentView.setAdapter(new MyAdapter());

            contentView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String data=datas.get(position);
                    et_input.setText(data);
                    et_input.setSelection(data.length());
                    window.dismiss();

                }
            });


            int width=et_input.getWidth();
            int height=580;
            window=new PopupWindow(contentView,width,height);
            //设置获取焦点
            window.setFocusable(true);//可以获得焦点
            window.setOutsideTouchable(true);//设置边界外可点击
            window.setBackgroundDrawable(new ColorDrawable());//透明的背景
        }
        window.showAsDropDown(et_input);

    }

    class MyAdapter extends BaseAdapter{

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
                view=View.inflate(MainActivity.this,R.layout.item,null);
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
