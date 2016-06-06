package vero.com.c3_self_03_spinner;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * Created by vero on 2016/6/3.
 */
public class SpinnerView extends RelativeLayout implements View.OnClickListener {
    private EditText et_input;
    private ImageView iv_arrow;
    private PopupWindow window;
    private ListAdapter adapter;
    private AdapterView.OnItemClickListener listener;
    private ListView contentView;

    public SpinnerView(Context context) {
        this(context,null);
    }

    public SpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //xml与class绑定
        View.inflate(context,R.layout.spinner,this);//this:父容器就是自己

        et_input= (EditText) findViewById(R.id.et_input);
        iv_arrow= (ImageView) findViewById(R.id.iv_arrow);
        iv_arrow.setOnClickListener(this);

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
        if (adapter ==null){
            throw new RuntimeException("adapter没有绑定！！");
        }
        if (window==null){
            contentView = new ListView(getContext());
            contentView.setAdapter(adapter);

            int width=et_input.getWidth();
            int height=580;
            window=new PopupWindow(contentView,width,height);
            //设置获取焦点
            window.setFocusable(true);//可以获得焦点
            window.setOutsideTouchable(true);//设置边界外可点击
            window.setBackgroundDrawable(new ColorDrawable());//透明的背景
        }
        window.showAsDropDown(et_input);
        contentView.setOnItemClickListener(listener);

    }

    public void setAdapter(ListAdapter adapter){
        this.adapter =adapter;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        this.listener=listener;
    }

    public  void setText(String data) {
        et_input.setText(data);
    }

    public  void setSelection(int length) {
        et_input.setSelection(length);
    }

    public  void dismiss() {
        if (window!=null){
            window.dismiss();
        }
    }
}
