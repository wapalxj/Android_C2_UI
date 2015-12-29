package com.example.vero1.c5_1_swiprefreshlayout_2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {
    private  PullToRefreshListView pullToRefreshListView;
    private  ArrayAdapter adapter ;
    private ArrayList<String> data=new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullToRefreshListView =(PullToRefreshListView)findViewById(R.id.pull_refresh_list);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        pullToRefreshListView.setAdapter(adapter);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            //下拉后刷新执行
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new GetDataTask().execute();
            }
        });

    }

    private class GetDataTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
            return generateColorBalls();
        }

        @Override
        protected void onPostExecute(String result) {
            data.add(result);
            adapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
            pullToRefreshListView.onRefreshComplete();
            super.onPostExecute(result);
        }
    }

    public String generateColorBalls(){
        Set<String> set=new TreeSet<>();
        String[] arr=new String[6];
        StringBuilder result=new StringBuilder("");

        //生成红球
        do {
            set.add(generateRed());
        }while (set.size()!=6);
        arr=set.toArray(arr);

        //加入红球
        result.append("第" + (data.size()+1) + "组: 红球：");
        for (int i=0;i<arr.length;i++){
            result.append(arr[i]);
            result.append(" ");
        }
        //加入蓝球
        result.append(" " + "蓝球：");
        result.append(generateBlue());
        //返回一组球
        return result.toString();
    }
    //生成一个红球
    public String generateRed(){
        int r=(int)(Math.random()*33+1);
        if (r<10){
            return "0"+String.valueOf(r);
        }else {
            return String.valueOf(r);
        }
    }
    //生成一个蓝球
    public String generateBlue(){
        int r=(int)(Math.random()*16+1);
        if (r<10){
            return "0"+String.valueOf(r);
        }else {
            return String.valueOf(r);
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
