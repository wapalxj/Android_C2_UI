package com.example.vero1.c900_excise_chartroom;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Msg> list=new ArrayList<>();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView =(ListView)findViewById(R.id.listView);
        listView.setDividerHeight(0);

        Button btnSend =(Button)findViewById(R.id.btn_send);
        Button btnRe=(Button)findViewById(R.id.btn_receive);
        //编辑消息
        final EditText send=(EditText)findViewById(R.id.editSend);
        final EditText re=(EditText)findViewById(R.id.editReceive);

        //消息
        Msg m1=new Msg("vero",Msg.SEND_TYPE);
        Msg m2=new Msg("vero",Msg.RE_TYPE);
        //将消息加入列表
        list.add(m1);
        list.add(m2);

        //设置adpter
        final MyAdapter adapter=new MyAdapter(MainActivity.this,list,getLayoutInflater(), R.layout.adapter2);
        listView.setAdapter(adapter);

        //发送消息
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!send.getText().toString().equals("")) {
                    Msg temp = new Msg(send.getText().toString(), Msg.SEND_TYPE);
                    list.add(temp);
                    adapter.notifyDataSetChanged();//刷新view
                    listView.setSelection(list.size());//显示最后一条内容
                    send.setText("");//重置表单为空
                }
            }
        });
        //接受消息
        btnRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!re.getText().toString().equals("")) {
                    Msg temp = new Msg(re.getText().toString(), Msg.RE_TYPE);
                    list.add(temp);
                    adapter.notifyDataSetChanged();//刷新view
                    listView.setSelection(list.size());//显示最后一条内容
                    re.setText("");//重置表单为空
                }
            }
        });

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

    //内部类 派生Adapter
    class MyAdapter extends BaseAdapter{
        private Context context;//消息
        private List<Msg> list;//消息列表
        private LayoutInflater inflater;
        private int resource;//view

        public MyAdapter(Context context, List<Msg> list, LayoutInflater inflater, int resource) {
            this.context = context;
            this.list = list;
            this.inflater = inflater;
            this.resource = resource;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        //消息列表view
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=inflater.inflate(resource,null);
            //发出的消息和接受的消息
            TextView textR=(TextView)(convertView.findViewById(R.id.textR));
            TextView textS=(TextView)(convertView.findViewById(R.id.textS));
            //获取消息并且设置给列表
            Msg msg=((Msg)getItem(position));
            textR.setText(msg.getContent());
            textS.setText(msg.getContent());

            if (msg.getType()==Msg.SEND_TYPE){
                ((LinearLayout)textR.getParent()).setVisibility(View.GONE);//隐藏
//                textR.setVisibility(View.GONE);
            }else
            if (msg.getType()==Msg.RE_TYPE){
//                textS.setVisibility(View.GONE);
                ((LinearLayout)textS.getParent()).setVisibility(View.GONE);//隐藏
            }
            return convertView;
        }
    }
}

